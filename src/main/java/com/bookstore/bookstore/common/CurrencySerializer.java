package com.bookstore.bookstore.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class CurrencySerializer extends StdSerializer<BigDecimal> {
  private DecimalFormat formatter;
  private static final Logger log = LoggerFactory.getLogger(CurrencySerializer.class);

  public CurrencySerializer() {
    this((Class)null);
  }

  public CurrencySerializer(Class t) {
    super(t);
    this.formatter = new DecimalFormat("#,###.00");
  }

  public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider provider) throws IOException {
    if (null != value) {
      gen.writeString(this.formatter.format(value));
    }

  }
}
