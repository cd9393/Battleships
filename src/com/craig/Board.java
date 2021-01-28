package com.craig;

import java.util.Scanner;

public class Board {
    // These fields should be private & final
    // Also should be using getters + setters for access (encapsulation)
    int size; // This could also be a constant, instead of being set in the constructor.
    private char[][] board; // I'd rename this to something like grid, too similar to class name

    // Lists are generally better and easier to use over primitive arrays
    // the multi-dimensional array for the board fits in nicely though, so the list comment is only for one-dimensional structures
    Ship[] ships;

    // public Board(int size) {
    public Board(int size) {
        this.size = size;
        board = new char[size][size];
        populateBoard();
        ships = new Ship[5];
        ships[0] = new Ship("Aircraft carrier", 5);
        ships[1] = new Ship("Battleship", 4);
        ships[2] = new Ship("Submarine", 3);
        ships[3] = new Ship("Cruiser", 3);
        ships[4] = new Ship("Destroyer", 2);
    }

    // This is fine in here since it's a utility that helps to construct the Board
    private void populateBoard() {
        this.board = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board[i][j] = '~';
            }
        }
    }

    // I'd extract the populating of the ships outside the Board class. This class is doing enough, a class should ideally only
    // perform a single use or represent an object as a POJO (Plain old java object)
    // Board to me would be a POJO that would model what the board looks like and what it contains
    // The populateShips would be better suited as a separate utility, where you pass in the Board to be populated
    public void populateShips() {
        Scanner scanner = new Scanner(System.in);
        for (Ship ship : ships) {
            System.out.printf("Enter the coordinates of the %s (%d cells):\n", ship.getName(), ship.getSize());
            boolean areCoordinatesOkay = false;
            int rowStart = 0;
            int rowEnd = 0;
            int columnStart = 0;
            int columnEnd = 0;
            while (!areCoordinatesOkay) {
                String startCoordinates = scanner.next();
                String endCoordinates = scanner.next();
                rowStart = Math.min(startCoordinates.charAt(0) - 65, endCoordinates.charAt(0) - 65);
                rowEnd = Math.max(startCoordinates.charAt(0) - 65, endCoordinates.charAt(0) - 65);
                columnStart = Math.min(Integer.parseInt(endCoordinates.substring(1)) - 1, Integer.parseInt(startCoordinates.substring(1)) - 1);
                columnEnd = Math.max(Integer.parseInt(endCoordinates.substring(1)) - 1, Integer.parseInt(startCoordinates.substring(1)) - 1);
                areCoordinatesOkay = areShipCoordinatesOkay(rowStart, rowEnd, columnStart, columnEnd, ship.getSize());
            }
            addToBoard(rowStart, rowEnd, columnStart, columnEnd);
            ship.setCoordinates(rowStart, columnStart, rowEnd, columnEnd);
            printBoard();
        }
    }

    // Fine in here, but I'd go with the grid name, and pass in Coordinate objects instead
    public void addToBoard(int rowStart, int rowEnd, int columnStart, int columnEnd) {
        for (int i = rowStart; i <= rowEnd; i++) {
            for (int j = columnStart; j <= columnEnd; j++) {
                board[i][j] = 'O';
            }
        }
    }

    public boolean isLengthOkay(int start, int end, int shipLength) {
        if (!((end - start) + 1 == shipLength)) {
            System.out.println("Error! Length is not correct. Length should be " + shipLength);
            return false;
        }
        return true;
    }

    public boolean isCellTaken(int rowBegin, int rowEnd, int columnBegin, int columnEnd) {
        int minRow = rowBegin > 0 ? rowBegin - 1 : 1;
        int maxRow = rowEnd < 9 ? rowEnd + 1 : 9;
        int minColumn = columnBegin > 0 ? columnBegin - 1 : 1;
        int maxColumn = columnEnd < 9 ? columnEnd + 1 : 9;
        for (int i = minRow; i <= maxRow; i++) {
            for (int j = minColumn; j <= maxColumn; j++) {
                if (board[i][j] == 'O') {
                    System.out.println("Error! Coordinates are too close to another ship");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean areShipCoordinatesOkay(int rowStart, int rowEnd, int columnStart, int columnEnd, int shipLength) {
        int start;
        int end;

        if (rowEnd > rowStart) {
            start = rowStart;
            end = rowEnd;
        } else {
            start = columnStart;
            end = columnEnd;
        }

        if (!(rowStart == rowEnd || columnStart == columnEnd)) {
            System.out.println("Error! Ship can't be placed diagonally");
            return false;
        } else if (isCellTaken(rowStart, rowEnd, columnStart, columnStart) || !isLengthOkay(start, end, shipLength)) {
            return false;
        }
        return true;
    }

    // All printing methods can be placed in a BoardPrinter utility class full of static methods
    public void printBoardFogOfWar() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < size; i++) {
            char letter = (char) (65 + i);
            System.out.print(letter + " ");
            for (int j = 0; j < size; j++) {
                if (board[i][j] == 'O') {
                    System.out.print('~' + " ");
                } else {
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public void printBoard() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < size; i++) {
            char letter = (char) (65 + i);
            System.out.print(letter + " ");
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // TODO:
    public void takeShot(int row, int column) {
        if (board[row][column] == '~' || board[row][column] == 'M') {
            board[row][column] = 'M';
            printBoardFogOfWar();
            System.out.println("You missed!");
            printBoard();
        } else {
            board[row][column] = 'X';
            for (Ship ship : ships) {
                if (column == ship.getColumnBegin() && column == ship.getColumnEnd()) {
                    if (row >= ship.getRowBegin() && row <= ship.getRowEnd()) {
                        ship.setHit(row - ship.getRowBegin());
                        if (ship.allCellsHit()) {
                            printBoardFogOfWar();
                            System.out.println("You sank a ship!");
                            printBoard();
                            break;
                        } else {
                            printBoardFogOfWar();
                            System.out.println("You hit a ship!");
                            printBoard();
                            break;
                        }
                    }
                }
                if (row == ship.getRowBegin() && row == ship.getRowEnd()) {
                    System.out.println("rowBegin = " + "rowEnd = ");
                    if (column >= ship.getColumnBegin() && column <= ship.getColumnEnd()) {
                        ship.setHit(column - ship.getColumnBegin());
                        if (ship.allCellsHit()) {
                            printBoardFogOfWar();
                            System.out.println("You sank a ship!");
                            printBoard();
                            break;
                        } else {
                            printBoardFogOfWar();
                            System.out.println("You hit a ship!");
                            printBoard();
                            break;
                        }
                    }
                }
            }
        }
    }

    // Fine in this class
    public boolean isAllShipsDestroyed() {
        for (Ship ship : ships) {
            if (!ship.isSank) {
                return false;
            }
        }
        return true;
    }
}

