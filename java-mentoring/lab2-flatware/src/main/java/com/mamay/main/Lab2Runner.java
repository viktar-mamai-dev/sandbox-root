package com.mamay.main;

import com.mamay.builder.AbstractFlatwareBuilder;
import com.mamay.entity.Flatware;
import com.mamay.exception.LogicalException;
import com.mamay.factory.FlatwareBuilderFactory;
import com.mamay.type.ParserType;
import com.mamay.utility.PrintReport;
import lombok.extern.log4j.Log4j2;

import java.net.URL;
import java.util.Set;

@Log4j2
public class Lab2Runner {

    private static final String INPUT_FILE = "flatware.xml";

    private final static FlatwareBuilderFactory factory = new FlatwareBuilderFactory();

    public static void main(String[] args) {
        try {
            builderRunAndGet(ParserType.DOM);
            builderRunAndGet(ParserType.SAX);
            builderRunAndGet(ParserType.STAX);
        } catch (LogicalException e) {
            log.error(e);
        }
    }

    private static void builderRunAndGet(ParserType type) throws LogicalException {
        AbstractFlatwareBuilder builder = factory.createFlatwareBuilder(type);
        builder.buildFlatwares(returnFile(INPUT_FILE));
        Set<Flatware> flatSet = builder.getFlatwares();
        PrintReport.printFlatware(flatSet);
    }

    private static String returnFile(String fileName) {
        URL resource = Lab2Runner.class.getClassLoader().getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("File not found");
        } else {
            return resource.getFile();
        }
    }
}
