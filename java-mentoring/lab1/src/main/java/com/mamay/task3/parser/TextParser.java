package com.mamay.task3.parser;

import com.mamay.task3.constant.RegexConstant;
import com.mamay.task3.entity.TextComposite;
import com.mamay.task3.entity.TextLeaf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser {

    public TextComposite parseToTextfile(CharSequence sequence) {
        Matcher m1 = Pattern.compile(RegexConstant.PARAGRAPH + "|"
                        + RegexConstant.PARAGRAPH_SEPARATOR + "|"
                        + RegexConstant.LISTING)
                .matcher(sequence);
        TextComposite c = new TextComposite();
        while (m1.find()) {
            String paragraph = m1.group();
            if (Pattern.matches(RegexConstant.PARAGRAPH, paragraph)) {
                c.addElement(parseToSentence(paragraph));
            } else if (Pattern.matches(RegexConstant.PARAGRAPH_SEPARATOR, paragraph)) {
                TextLeaf leaf = new TextLeaf(paragraph);
                c.addElement(leaf);
            } else if (Pattern.matches(RegexConstant.LISTING, paragraph)) {
                TextLeaf leaf = new TextLeaf(paragraph);
                c.addElement(leaf);
            }
        }
        return c;
    }

    private TextComposite parseToSentence(CharSequence text) {
        Matcher m1 = Pattern.compile(RegexConstant.SENTENCE).matcher(text);
        TextComposite c = new TextComposite();
        while (m1.find()) {
            String sentence = m1.group();
            c.addElement(parseToWordAndSeparator(sentence));
        }
        return c;
    }

    private TextComposite parseToWordAndSeparator(CharSequence text) {
        Matcher m1 = Pattern.compile(RegexConstant.WORD + "|" + RegexConstant.WORD_SEPARATOR).matcher(text);
        TextComposite c = new TextComposite();
        while (m1.find()) {
            String word = m1.group();
            if (Pattern.matches(RegexConstant.WORD, word)) {
                c.addElement(parseToSymbol(word));
            } else if (Pattern.matches(RegexConstant.WORD_SEPARATOR, word)) {
                TextLeaf leaf = new TextLeaf(word);
                c.addElement(leaf);
            }
        }
        return c;
    }

    private TextComposite parseToSymbol(CharSequence text) {
        Pattern pat = Pattern.compile(RegexConstant.SYMBOL);
        Matcher m = pat.matcher(text);

        TextComposite c = new TextComposite();
        while (m.find()) {
            String symbol = m.group();
            TextLeaf leaf = new TextLeaf(symbol);
            c.addElement(leaf);
        }
        return c;
    }

}
