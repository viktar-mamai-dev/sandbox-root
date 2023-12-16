package com.mamay.task3.service;

import com.mamay.Lab1Exception;
import com.mamay.task3.comparator.HashMapValueComparator;
import com.mamay.task3.entity.TextComponent;
import com.mamay.task3.entity.TextComposite;
import com.mamay.task3.entity.TextLeaf;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.IntStream;

public class TestService {
  public TextComponent sortSentences(TextComponent c) {
    HashMap<TextComponent, Integer> initSentences = parseToParagraphs(c);

    Set<TextComponent> sortedSet = sortMapByValue(initSentences);
    TextComponent result = new TextComposite();
    for (TextComponent sentence : sortedSet) {
      result.addElement(sentence);
      result.addElement(new TextLeaf("\n"));
    }
    return result;
  }

  private HashMap<TextComponent, Integer> parseToParagraphs(TextComponent c) {
    HashMap<TextComponent, Integer> sentences = new HashMap<>();
    for (int i1 = 0; i1 < c.size(); i1++) {
      TextComponent paragraph = c.getChild(i1);
      if (!(paragraph instanceof TextLeaf)) {
        sentences.putAll(parseToSentences(paragraph));
      }
    }
    return sentences;
  }

  private HashMap<TextComponent, Integer> parseToSentences(TextComponent paragraph) {
    HashMap<TextComponent, Integer> sentences = new HashMap<>();
    for (int i2 = 0; i2 < paragraph.size(); i2++) {
      TextComponent sentence = paragraph.getChild(i2);
      sentences.putAll(parseToWords(sentence));
    }
    return sentences;
  }

  private HashMap<TextComponent, Integer> parseToWords(TextComponent sentence) {
    HashMap<TextComponent, Integer> sentences = new HashMap<>();
    for (int i3 = 0; i3 < sentence.size(); i3++) {
      TextComponent word = sentence.getChild(i3);
      if (!(word instanceof TextLeaf)) {
        if (sentences.containsKey(sentence)) {
          Integer value = sentences.get(sentence);
          sentences.put(sentence, ++value);
        } else {
          sentences.put(sentence, 1);
        }
      }
    }
    return sentences;
  }

  private Set<TextComponent> sortMapByValue(HashMap<TextComponent, Integer> map) {
    HashMapValueComparator vc = new HashMapValueComparator(map);
    TreeMap<TextComponent, Integer> sortedMap = new TreeMap<TextComponent, Integer>(vc);
    sortedMap.putAll(map);
    return sortedMap.keySet();
  }

  public TextComponent findUniqueWords(TextComponent c) throws Lab1Exception {
    TextComponent onlyParagraphs = findParagraphs(c);
    TextComponent firstSenWords = firstSentence(onlyParagraphs.getChild(0).getChild(0));

    for (int j = 1; j < onlyParagraphs.getChild(0).size(); j++) {
      TextComponent sentence = onlyParagraphs.getChild(0).getChild(j);
      for (int k = firstSenWords.size() - 1; k >= 0; k--) {
        TextComponent word = firstSenWords.getChild(k);
        if (isWordInSentence(sentence, word)) {
          firstSenWords.removeElement(word);
        }
      }
    }

    for (int j = 1; j < onlyParagraphs.size(); j++) {
      TextComponent paragraph = onlyParagraphs.getChild(j);
      for (int k = firstSenWords.size() - 1; k >= 0; k--) {
        TextComponent word = firstSenWords.getChild(k);
        if (isWordInParagraph(paragraph, word)) {
          firstSenWords.removeElement(word);
        }
      }
    }

    TextComponent result = new TextComposite();
    for (int k = 0; k < firstSenWords.size(); k++) {
      TextComponent word = firstSenWords.getChild(k);
      if (!(word instanceof TextLeaf)) {
        result.addElement(word);
        result.addElement(new TextLeaf("\r\n"));
      }
    }
    return result;
  }

  private TextComponent firstSentence(TextComponent c) {
    TextComponent result = new TextComposite();
    for (int k = 0; k < c.size(); k++) {
      result.addElement(c.getChild(k));
    }
    return result;
  }

  private TextComponent findParagraphs(TextComponent c) throws Lab1Exception {
    TextComponent paragraphs = new TextComposite();
    for (int i = 0; i < c.size(); i++) {
      TextComponent paragraph = c.getChild(i);
      if (!(paragraph instanceof TextLeaf)) {
        paragraphs.addElement(paragraph);
      }
    }
    if (paragraphs.size() != 0) {
      return paragraphs;
    } else {
      throw new Lab1Exception("Such text does not contain any paragraphs!");
    }
  }

  private boolean isWordInParagraph(TextComponent paragraph, TextComponent word) {
    return IntStream.range(0, paragraph.size())
        .mapToObj(paragraph::getChild)
        .anyMatch(d -> isWordInSentence(d, word));
  }

  private boolean isWordInSentence(TextComponent sentence, TextComponent word) {
    return IntStream.range(0, sentence.size())
        .mapToObj(i -> sentence.getChild(i).getData())
        .anyMatch(d -> d.equals(word.getData()));
  }
}
