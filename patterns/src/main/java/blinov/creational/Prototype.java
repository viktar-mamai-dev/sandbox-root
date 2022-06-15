package blinov.creational;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

interface Client3<T> {
    T cloneElementById(Integer id);

    List<T> cloneElements(Integer... param);
}

class IssueCachePrototype implements Client3<Issue> { // Prototype
    private List<Issue> cache;

    public IssueCachePrototype() {
        cache = new ArrayList<Issue>();
    }

    public IssueCachePrototype(List<Issue> issueList) {
        this.cache = issueList;
    }

    @Override
    public Issue cloneElementById(Integer id) {
        for (Issue issue : cache) {
            if (issue.getId().equals(id)) {
                return issue.clone();
            }
        }
        throw new IllegalArgumentException("illegal ID " + id);
    }

    @Override
    public List<Issue> cloneElements(Integer... param) {
        ArrayList<Issue> cloneList = new ArrayList<Issue>();
        for (Integer p : param) {
            Issue issue = cloneElementById(p);
            cloneList.add(issue);
        }
        return cloneList;
    }
}

@Getter
@Setter
@ToString
abstract class Issue implements Cloneable {
    private Integer id;
    private String name;
    private int year;

    public Issue(Integer id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    @Override
    protected Issue clone() {
        Issue cloned = null;
        try {
            cloned = (Issue) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return cloned;
    }
}

@Getter
@Setter
@ToString
class Book extends Issue {
    private String author;

    public Book(Integer id, String author, String name, int year) {
        super(id, name, year);
        this.author = author;
    }

}

@Getter
@Setter
@ToString
class Magazine extends Issue {
    private int number;

    public Magazine(Integer id, int number, String name, int year) {
        super(id, name, year);
        this.number = number;
    }

}

class PrototypeRunner {
    public static void main(String[] args) {
        ArrayList<Issue> issueList = new ArrayList<Issue>() {
            {
                this.add(new Book(615, "Steve McConnell", "Code Complete", 2012));
                this.add(new Book(453, "Bruce Eckel", "Thinking in Java", 2006));
                this.add(new Book(721, "Joshua Bloch", "Effective Java", 2008));
                this.add(new Magazine(1009, 9, "PC Magazine", 2012));
            }
        };
        IssueCachePrototype cache = new IssueCachePrototype(issueList);
        Issue copy = cache.cloneElementById(453);
        copy.setYear(2015);
        copy.setName("Arnold Gossling");
        PrototypePrinter.print(issueList);
        out.println(copy);

        List<Issue> cloneList = cache.cloneElements(1009, 453, 615);
        PrototypePrinter.print(cloneList);
    }
}

class PrototypePrinter {
    public static void print(List<Issue> printedList) {
        out.println("**********************************************");
        for (Issue issue : printedList) {
            out.println(issue);
        }
        out.println("**********************************************");
    }
}