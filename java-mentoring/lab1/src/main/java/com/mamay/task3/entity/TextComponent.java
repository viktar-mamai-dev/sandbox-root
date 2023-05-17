package com.mamay.task3.entity;

public interface TextComponent {
  void operation();

  void addElement(TextComponent c);

  void removeElement(TextComponent c);

  TextComponent getChild(int index);

  int size();

  String getData();
}
