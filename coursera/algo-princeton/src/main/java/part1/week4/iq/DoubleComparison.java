package part1.week4.iq;

public class DoubleComparison {
    public static void main(String[] args) {

        Double x = Double.NaN;
        Double y = Double.NaN;
        double a = x.doubleValue();
        double b = y.doubleValue();

        System.out.println("double " + a + " equals primitive " + b + " : " + (a == b));
        System.out.println("Double " + x + " equals wrapper " + b + " : " + (x.equals(y)));

        x = new Double(+0.0);
        y = new Double(-0.0);
        a = x.doubleValue();
        b = y.doubleValue();
        System.out.println("double " + a + " equals primitive " + b + " : " + (a == b));
        System.out.println("Double " + x + " equals wrapper " + b + " : " + (x.equals(y)));
    }
}
