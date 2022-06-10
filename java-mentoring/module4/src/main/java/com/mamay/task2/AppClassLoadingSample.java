package com.mamay.task2;

import org.apache.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class AppClassLoadingSample {

    private static final String CAT_CLASS = "com.mamay.entity.Cat";
    private static final String DOG_CLASS = "com.mamay..entity.Dog";

    private final static Logger LOGGER = Logger.getLogger(AppClassLoadingSample.class);

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException,
            IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InstantiationException {

        try {
            Scanner scanner = new Scanner(System.in);

            CustomClassloader ccl = new CustomClassloader(AppClassLoadingSample.class.getClassLoader());
            Class catClass = ccl.loadClass(CAT_CLASS);
            Class dogClass = ccl.loadClass(DOG_CLASS);

            LOGGER.info("Enter name for a cat");
            String name = scanner.next();
            Constructor<?> constructorCat = catClass.getConstructor(String.class);

            Object cat = constructorCat.newInstance(name);
            Method catPlay = catClass.getMethod("play");
            catPlay.invoke(cat, new Object[]{});
            Method catVoice = catClass.getMethod("voice", String.class);
            catVoice.invoke(cat, args[0]);

            LOGGER.info("Enter name for a dog");
            name = scanner.next();
            Constructor<?> constructorDog = dogClass.getConstructor(String.class);

            Object dog = constructorDog.newInstance(name);
            Method dogPlay = dogClass.getMethod("play");
            dogPlay.invoke(dog, new Object[]{});
            Method dogVoice = dogClass.getMethod("voice", String.class);
            dogVoice.invoke(dog, args[1]);

            // Below method is used to check that the Foo is getting loaded
            // by our custom class loader i.e CCLoader
            /*
             * Method printCL = clas.getMethod("printCL", null); printCL.invoke(null, new
             * Object[0]);
             */
        } finally {

        }

    }
}
