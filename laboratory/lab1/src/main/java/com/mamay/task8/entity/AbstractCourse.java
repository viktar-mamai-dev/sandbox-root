package com.mamay.task8.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by admin on 9/24/2014.
 */
public abstract class AbstractCourse {
    final static int MAX_STUDENTS = 150;
    public static String faculty;

    static {
        System.out.println("A");
    }

    final int MAX_HOURS;// = new Random().nextInt();
    private int id;
    private String name;
    private ArrayList<String> lectors;

    {
        MAX_HOURS = 100;
        System.out.println("B");
    }

    public AbstractCourse() {
        super();
        this.id = id;
        this.name = name;
        //  this.lectors = lectors;
        //MAX_HOURS = 101;
    }

    public AbstractCourse(int id) {

        super();
        this.id = id;
        this.name = name;
        //  this.lectors = lectors;
        //MAX_HOURS = 101;
    }

    public AbstractCourse(int id, String name) {

        super();
        this.id = id;
        this.name = name;
        //  this.lectors = lectors;
        //MAX_HOURS = 101;
    }

    public static void changeFaculty(String fac) {
        faculty = fac + "Z";
    }

    public void changeFaculty(String fac, int index) {
        faculty = fac + index;
    }

    public boolean add(String s) {
        return lectors.add(s);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name.intern();
        final int MAX = 12;
        this.name = name;
    }

    public List<String> getLectors() {
        return Collections.unmodifiableList(lectors);
    }

    public void setLectors(ArrayList<String> lectors) {
        //lectors.clone();
        // this.lectors = new ArrayList<String>(lectors);
        this.lectors = lectors;
    }

    public String get(int index) {
        return lectors.get(index);
    }

    @Override
    public String toString() {
        return "id=" + id + ", name='" + name + '\'' + '}';
    }
}

