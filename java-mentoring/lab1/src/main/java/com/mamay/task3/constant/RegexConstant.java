package com.mamay.task3.constant;

public class RegexConstant {
  public static final String PARAGRAPH = "(\\p{Upper})([\\w \\p{Punct}\\r\\n]+)([.!?])";
  public static final String PARAGRAPH_SEPARATOR = "(([\\r\\n]*)(\\t))";
  public static final String LISTING = "(//##)(([,\\r\\n\\w\\p{Punct}\\s]+?))(##//)";

  public static final String SENTENCE = "(\\p{Upper})[\\w ,:;\\r\\n\'\"-]*[?!.]";

  public static final String WORD = "((\\r\\n\\p{Upper})?)([\\w\'-]+)";
  public static final String WORD_SEPARATOR = "([ ,.!?;:\"\\r\\n]+)";

  public static final String SYMBOL = "[\\r\\n\\w\'\"-]";
}
