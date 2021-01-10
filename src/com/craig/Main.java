package com.craig;

import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        char[][] board = new char[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = '~';
            }
        }
        printBoard(board);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the coordinates of the Aircraft carrier (5 cells)");
        String coordinates = scanner.nextLine();
        if (Objects.equals(coordinates.charAt(0), coordinates.charAt(3))) {
            int row = coordinates.charAt(0) - 65;
            int start = Character.getNumericValue(coordinates.charAt(1)) - 1;
            int end = Character.getNumericValue(coordinates.charAt(4)) - 1;
            for (int i = start; i <= end; i++) {
                board[row][i] = 'O';
            }
        } else if (Objects.equals(coordinates.charAt(1), coordinates.charAt(4))) {
            int column = Character.getNumericValue(coordinates.charAt(1)) - 1;
            int start = coordinates.charAt(0) - 65;
            int end = coordinates.charAt(3) - 65;
            for (int i = start; i <= end; i++) {
                board[i][column] = 'O';
            }
        } else {
            System.out.println("Error cannot be placed diagonally");
        }

        printBoard(board);
    }

    public static void printBoard(char[][] board) {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < 10; i++) {
            char letter = (char) (65 + i);
            System.out.print(letter + " ");
            for (int j = 0; j < 10; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
