package com.mamay.task1;

public class BigObject {

    private BigObject obj;
    String str = new String(getClass().getName());

    public BigObject(BigObject obj) {

        this.obj = obj;
        if (obj != null) {
            str = obj.str.concat(obj.str);
        }
    }

    public BigObject getObj() {
        return obj;
    }

}
