package zad6;

public class Main {
    public static void main(String[] args) {
        Sheet s = new Sheet(5, 5);
        s.set("A1", "2");
        s.set("A2", "5");
        s.set("A3", "A1+A2");
        s.print();
        System.out.println();

        s.set("A1", "4");
        s.set("A4", "A1+A3");
        s.print();
        System.out.println();

        System.out.println("Intentional exception (circular dependency): ");
        try {
            s.set("A1", "A3");
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        s.print();
        System.out.println();
    }
}
