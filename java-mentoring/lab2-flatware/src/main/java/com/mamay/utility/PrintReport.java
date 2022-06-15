package com.mamay.utility;

import com.mamay.entity.Flatware;
import lombok.extern.log4j.Log4j2;

import java.util.Set;

@Log4j2
public class PrintReport {

    public static void printFlatware(Set<Flatware> flatwares) {
        StringBuilder sb = new StringBuilder();
        for (Flatware fw : flatwares) {
            sb.append(fw).append("\n");
        }
        log.debug(sb);
    }
}
