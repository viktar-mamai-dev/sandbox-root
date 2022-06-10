package com.mamay.task3.entity;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class TextLeaf implements TextComponent {

    private String data;

    public TextLeaf(String data) {
        this.data = data;
    }

    @Override
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public void operation() {
        log.debug(data);
    }

    @Override
    public void addElement(TextComponent c) {
    }

    @Override
    public void removeElement(TextComponent c) {
    }

    @Override
    public TextComponent getChild(int i) {
        return null;
    }

    @Override
    public int size() {
        return 1;
    }

}
