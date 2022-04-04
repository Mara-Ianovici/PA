package app;

import frame.MainFrame;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter the games rules (0 = user starts, 1 = computer starts)\n");

        int rule= keyboard.nextInt();

        new MainFrame("Frame 1", rule).setVisible(true);
    }
}
