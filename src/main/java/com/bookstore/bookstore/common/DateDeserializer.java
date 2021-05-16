package com.bookstore.bookstore.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class DateDeserializer extends StdDeserializer<Date> {
  private List<DateTimeFormatter> formatterList;
  private static final Logger log = LoggerFactory.getLogger(DateDeserializer.class);

  public DateDeserializer() {
    this((Class) null);
  }

  public DateDeserializer(Class t) {
    super(t);
    this.formatterList = Arrays.asList(DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.US)
            , DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.US)
            , DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.US)
            , DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US)
            , DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.US)
            , DateTimeFormatter.ofPattern("yyyy-MM-dd ", Locale.US));
  }

  public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    JsonNode node = (JsonNode) p.getCodec().readTree(p);
    Date returnValue = null;
    if (null != node && null != node.textValue()) {
      Iterator var5 = this.formatterList.iterator();

      while (var5.hasNext()) {
        DateTimeFormatter format = (DateTimeFormatter) var5.next();

        try {
          LocalDate datetime = LocalDate.parse(node.textValue(), format);
          returnValue = Date.valueOf(datetime);
          break;
        } catch (Exception var) {
        }
      }

      if (null == returnValue) {
        log.info("TimestampDeserializer::deserialize invalid format: " + node.textValue());
      }
    }

    return returnValue;
  }
}