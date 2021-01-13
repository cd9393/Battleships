package com.craig;

import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Board board = new Board(10, 10);
        board.printBoard();
        Scanner scanner = new Scanner(System.in);
        String startCoordinates;
        String endCoordinates;

        for (Ships ship : Ships.values()) {
            System.out.println(ship.getMessage());
            do {
                 startCoordinates = scanner.next();
                 endCoordinates = scanner.next();
            } while(areCoordinatesOkay(board,startCoordinates, endCoordinates, ship.getLength()));
            board.populateShipOnboard(startCoordinates,endCoordinates);
        }
//        System.out.println("Enter the coordinates of the Aircraft carrier (5 cells)");
//        populateShipOnboard(board,scanner, 5);
//
//        System.out.println("Enter the coordinates of the Battleship (4 cells)");
//        populateShipOnboard(board,scanner, 4);
//
//        System.out.println("Enter the coordinates of the Submarine (3 cells)");
//        populateShipOnboard(board,scanner, 3);
//
//        System.out.println("Enter the coordinates of the Cruiser (3 cells)");
//        populateShipOnboard(board,scanner, 3);
//
//        System.out.println("Enter the coordinates of the Destroyer (2 cells)");
//        populateShipOnboard(board,scanner, 2);
    }

    public static boolean areCoordinatesOkay(Board board, String startCoordinates, String endCoordinates, int shipLength) {
        if(board.isCoordinateDiagonal(startCoordinates, endCoordinates)) {
            System.out.println("Error ship can't be placed diagonally.");
            return false;
        } else if (!board.isLengthOkay(startCoordinates, endCoordinates, shipLength)){
            System.out.println("Error wrong length, please enter coordinates with length " + shipLength);
            return false;
        } else if (board.isCellFree(startCoordinates,endCoordinates)) {
            System.out.println("Error coordinates are too close to other ship");
            return false;
        } else {
            return true;
        }
    }

    public static void populateNewBoard(char[][] board) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = '~';
            }
        }
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

    public static boolean checkCoordinatesFreeRow(int row, int start, int end, char[][]board) {
        boolean coordinatesFree = true;

        if(start >= 1) {
            if(board[row][start-1] == 'O'){
                coordinatesFree = false;
            }
        }
        if(end <= 8) {
            if(board[row][end + 1] == 'O') {
                coordinatesFree = false;
            }
        }

        for (int i = start; i <= end; i++) {
            if(board[row][i] == 'O') {
                coordinatesFree = false;
                break;
            }
        }

        if (row > 0) {
            for (int i = start; i <= end; i++) {
                if(board[row - 1][i] == 'O') {
                    coordinatesFree = false;
                    break;
                }
            }
        }

        if (row < 9) {
            for (int i = start; i <= end; i++) {
                if(board[row + 1][i] == 'O') {
                    coordinatesFree = false;
                    break;
                }
            }
        }
        return coordinatesFree;
    }

    public static boolean checkCoordinatesFreeColumn(int column, int start, int end, char[][]board) {
        boolean coordinatesFree = true;

        for (int i = start; i <= end; i++) {
            if(board[i][column] == 'O') {
                coordinatesFree = false;
                break;
            }
        }
        if(start >= 1) {
            if(board[start-1][column] == 'O'){
                coordinatesFree = false;
            }
        }
        if(end <= 8) {
            if(board[end + 1][column] == 'O') {
                coordinatesFree = false;
            }
        }

        for (int i = start; i <= end; i++) {
            if(board[i][column] == 'O') {
                coordinatesFree = false;
                break;
            }
        }

        if (column > 0) {
            for (int i = start; i <= end; i++) {
                if(board[i][column - 1] == 'O') {
                    coordinatesFree = false;
                    break;
                }
            }
        }

        if (column < 9) {
            for (int i = start; i <= end; i++) {
                if(board[i][column + 1] == 'O') {
                    coordinatesFree = false;
                    break;
                }
            }
        }
        return coordinatesFree;
    }


    public static void populateShipOnboard(char[][] board, Scanner scanner, int lengthOfShip) {
        boolean coordinatesOkay = false;
        while(!coordinatesOkay) {
            String startCoordinates = scanner.next();
            String endCoordinates = scanner.next();

            if (Objects.equals(startCoordinates.charAt(0), endCoordinates.charAt(0))) {
                int row = startCoordinates.charAt(0) - 65;
                int start = Math.min(Integer.parseInt(endCoordinates.substring(1)) - 1, Integer.parseInt(startCoordinates.substring(1)) - 1);
                int end = Math.max(Integer.parseInt(endCoordinates.substring(1)) - 1, Integer.parseInt(startCoordinates.substring(1)) - 1);
                if (end - start + 1 != lengthOfShip) {
                    System.out.println("Error - Please enter co ordinates with the correct length- correct length should be " + lengthOfShip);
                } else {
                    if (checkCoordinatesFreeRow(row, start, end, board)) {
                        for (int i = start; i <= end; i++) {
                            board[row][i] = 'O';
                        }
                        coordinatesOkay = true;
                    } else {
                        System.out.println("Error - These coordinates overlap an existing ship, please choose different coordinates");
                    }
                }
            } else if (Objects.equals(startCoordinates.substring(1), endCoordinates.substring(1))) {
                int column = Integer.parseInt(startCoordinates.substring(1)) - 1;
                int start = Math.min(startCoordinates.charAt(0) - 65, endCoordinates.charAt(0) - 65);
                int end = Math.max(startCoordinates.charAt(0) - 65, endCoordinates.charAt(0) - 65);
                if (end - start + 1 != lengthOfShip) {
                    System.out.println("Error - Please enter co ordinates with the correct length- correct length should be " + lengthOfShip);
                }else {
                    if (checkCoordinatesFreeColumn(column, start, end, board)) {
                        for (int i = start; i <= end; i++) {
                            board[i][column] = 'O';
                        }
                        coordinatesOkay = true;
                    } else {
                        System.out.println("Error - These coordinates overlap an existing ship, please choose different coordinates");
                    }
                }
            } else {
                System.out.println("Error cannot be placed diagonally");
            }
        }
        printBoard(board);
        }
}
