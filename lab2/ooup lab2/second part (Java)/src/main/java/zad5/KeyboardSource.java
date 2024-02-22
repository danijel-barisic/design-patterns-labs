package zad5;

import java.util.Scanner;

public class KeyboardSource implements SourceStrategy{

    private final Scanner scanner;

    public KeyboardSource() {
        scanner = new Scanner(System.in);
    }

    @Override
    public int getNextNumber() {
        System.out.print("\nEnter a number: ");
        return scanner.nextInt();
    }
}
