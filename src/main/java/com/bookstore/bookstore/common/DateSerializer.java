package com.bookstore.bookstore.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DateSerializer extends StdSerializer<Date> {
  private SimpleDateFormat formatter;
  private static final Logger log = LoggerFactory.getLogger(DateSerializer.class);

  public DateSerializer() {
    this((Class)null);
  }

  public DateSerializer(Class t) {
    super(t);
    this.formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
  }

  public void serialize(Date value, JsonGenerator gen, SerializerProvider provider) throws IOException {
    if (null != value) {
      gen.writeString(this.formatter.format(value));
    }

  }
}
