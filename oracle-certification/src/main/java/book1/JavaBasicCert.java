package book1;

public class JavaBasicCert extends Alpha {
    public static void main(String[] args) {
        short s = 9;
        System.out.println(new Alpha().add(s, 6));
    }
}

/* a// a*/
class Alpha {
    int add(int i, int j) {
        return i + j;
    }
}