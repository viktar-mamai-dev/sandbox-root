package com.mamay.task3.util;

import com.mamay.task3.entity.TextComponent;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class PrintReport {

    public static void printComponent(TextComponent c) {
        StringBuilder sb = new StringBuilder("\n*****************************");
        sb.append("\n").append(c.getData());
        sb.append("*****************************\n");
        log.debug(sb.toString());
    }

}
