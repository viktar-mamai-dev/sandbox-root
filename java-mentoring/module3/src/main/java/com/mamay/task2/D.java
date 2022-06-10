package com.mamay.task2;

public class D {

    private static int nextId = 1;
    private Boolean valid;

    public void init() {
        ++nextId;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "D [valid=" + valid + ", id=" + nextId + "]";
    }

}
