package com.mamay.task2;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class CustomClassloader extends ClassLoader {

    public CustomClassloader(ClassLoader parent) {
        super(parent);
    }

    /**
     * Every request for a class passes through this method. If the class is in package we will use
     * this classloader or else delegate the request to parent classloader.
     *
     * @param name Full class name
     */
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        System.out.println("Loading Class '" + name + "'");
        if (name.startsWith("com.mamay.entity")) {
            System.out.println("Class loaded using CustomCLoader");
            return getClass(name);
        }
        return super.loadClass(name);
    }

    /**
     * Loads the class from the file system. The class file should be located in the
     * file system. The name should be relative to get the file location
     *
     * @param name Fully Classified name of class, for example com.mamay.Foo
     */
    private Class<?> getClass(String name) throws ClassNotFoundException {
        String file = name.replace('.', File.separatorChar) + ".class";
        byte[] b = null;
        try {
            // This loads the byte code data from the file
            b = loadClassFileData(file);
            // defineClass is inherited from the ClassLoader class that converts byte array into a Class.
            // defineClass is Final so we cannot override it
            Class<?> c = defineClass(name, b, 0, b.length);
            resolveClass(c);
            return c;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Reads the file (.class) into a byte array. The file should be accessible as a
     * resource and make sure that its not in Classpath to avoid any confusion.
     *
     * @param name File name
     * @return Byte array read from the file
     * @throws IOException if any exception comes in reading the file
     */
    private byte[] loadClassFileData(String name) throws IOException {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(name);
        int size = stream.available();
        byte[] buff = new byte[size];
        try (DataInputStream in = new DataInputStream(stream)) {
            in.readFully(buff);
            return buff;
        }
    }
}
