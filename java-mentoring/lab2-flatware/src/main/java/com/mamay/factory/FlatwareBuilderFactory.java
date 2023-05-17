package com.mamay.factory;

import com.mamay.builder.AbstractFlatwareBuilder;
import com.mamay.builder.FlatwareDOMBuilder;
import com.mamay.builder.FlatwareSAXBuilder;
import com.mamay.builder.FlatwareStAXBuilder;
import com.mamay.type.ParserType;

public class FlatwareBuilderFactory {
  public AbstractFlatwareBuilder createFlatwareBuilder(ParserType type) {
    return switch (type) {
      case DOM -> new FlatwareDOMBuilder();
      case STAX -> new FlatwareStAXBuilder();
      case SAX -> new FlatwareSAXBuilder();
    };
  }
}
