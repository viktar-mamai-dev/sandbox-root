package com.mamay.hackerrank.javamodules;

import java.util.*;

class Student implements Comparable<Student> {

    private final int id;
    private final String name;
    private final double cgpa;

    Student(int id, String name, double cgpa) {
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCgpa() {
        return cgpa;
    }

    @Override
    public int compareTo(Student o) {
        if (this.cgpa != o.getCgpa()) {
            return Double.compare(o.getCgpa(), this.cgpa);
        }
        if (!this.name.equals(o.getName())) {
            return this.name.compareTo(o.getName());
        }
        return Integer.compare(this.id, o.getId());
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cgpa=" + cgpa +
                '}';
    }
}

class Priorities {

    List<Student> getStudents(List<String> events) {
        java.util.PriorityQueue<Student> q = new java.util.PriorityQueue<>(20);
        for (String line : events) {
            String[] eventDetails = line.split(" ");
            if (eventDetails[0].equalsIgnoreCase("ENTER")) {
                String name = eventDetails[1];
                double cgpa = Double.parseDouble(eventDetails[2]);
                int id = Integer.parseInt(eventDetails[3]);
                q.offer(new Student(id, name, cgpa));
            } else {
                if (q.isEmpty()) {
                    // TODO
                } else {
                    q.poll();
                }
            }
        }
        LinkedList<Student> list = new LinkedList<>();
        while (!q.isEmpty()) {
            list.add(q.poll());
        }
        return list;
    }
}

public class PriorityQueue {

    private final static Scanner scan = new Scanner(System.in);
    private final static Priorities priorities = new Priorities();

    public static void main(String[] args) {
        int totalEvents = Integer.parseInt(scan.nextLine());
        List<String> events = new ArrayList<>();

        while (totalEvents-- != 0) {
            String event = scan.nextLine();
            events.add(event);
        }

        List<Student> students = priorities.getStudents(events);

        if (students.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            for (Student st : students) {
                System.out.println(st.getName());
            }
        }
    }
}
