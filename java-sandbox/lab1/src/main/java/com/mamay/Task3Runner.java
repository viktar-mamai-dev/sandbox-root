package com.mamay;

import com.mamay.task3.entity.TextComponent;
import com.mamay.task3.entity.TextComposite;
import com.mamay.Lab1Exception;
import com.mamay.task3.parser.TextParser;
import com.mamay.task3.service.TestService;
import com.mamay.task3.util.PrintReport;
import com.mamay.task3.util.TextReader;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Task3Runner {
  private static final String INPUT_FILE = "task3/input.txt";

  private static final TestService action = new TestService();
  private static final TextParser parser = new TextParser();

  private static final TextReader reader = new TextReader();

  public static void main(String[] args) {
    CharSequence sequence;
    try {
      sequence = reader.readFromFile(INPUT_FILE);
      TextComposite rootComponent = parser.parseToTextfile(sequence);

      TextComponent uniqueWords = action.findUniqueWords(rootComponent);
      TextComponent sortedSentences = action.sortSentences(rootComponent);

      PrintReport.printComponent(uniqueWords);
      PrintReport.printComponent(sortedSentences);
    } catch (Lab1Exception e) {
      log.error(e.getMessage());
    }
  }
}
