package com.bookstore.bookstore.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Slf4j
public class CurrencyDeserializer extends StdDeserializer<BigDecimal> {
  private List<DecimalFormat> formatterList;

  public CurrencyDeserializer() {
    this((Class) null);
  }

  public CurrencyDeserializer(Class t) {
    super(t);
    this.formatterList = Arrays.asList(new DecimalFormat("#,###.##")
            ,new DecimalFormat("#")
            ,new DecimalFormat("#,###.00"));
  }

  public BigDecimal deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    JsonNode node = (JsonNode) p.getCodec().readTree(p);
    BigDecimal returnValue = null;
    if (null != node && null != node.textValue()) {
      Iterator var5 = this.formatterList.iterator();

      while (var5.hasNext()) {
        DecimalFormat format = (DecimalFormat) var5.next();
        format.setParseBigDecimal(true);

        try {
          returnValue = (BigDecimal) format.parse(node.textValue());
          break;
        } catch (Exception var13) {
        }
      }

      if (null == returnValue) {
        log.info("CurrencyDeserializer::deserialize invalid format: " + node.textValue());
      }
    } else if(!StringUtils.isEmpty(node)) {
      returnValue = new BigDecimal(node.toString());
    }
    return returnValue;
  }
}