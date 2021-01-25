package com.craig;
import java.util.Objects;

public class Board {
    private int rows;
    private int columns;
    private char[][] board;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        populateBoard();
    }
    private void populateBoard() {
        this.board = new char[this.rows][this.columns];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                this.board[i][j] = '~';
            }
        }
    }

    public void printBoardFogOfWar() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < 10; i++) {
            char letter = (char) (65 + i);
            System.out.print(letter + " ");
            for (int j = 0; j < 10; j++) {
                if (board[i][j] == 'O') {
                    System.out.print('~' + " ");
                }else {
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
    public void printBoard() {
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

    public boolean isAllShipsDestroyed() {
        int hits = 0;
        for (int i =0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board[i][j] == 'X') {
                    hits ++;
                }
            }
        }
        return hits == 17;
    }

    public void takeShot(String coordinates) {
        // check coordinates are within limits
        // convert coordinates to [row][column]
        int row = coordinates.charAt(0) - 65;
        int column = Integer.parseInt(coordinates.substring(1)) -1;
        // if [row][column] == 'O' Output "you hit a ship" set [row][column] to 'X'
        if (this.board[row][column] == 'O' || this.board[row][column] == 'X') {
            this.board[row][column] = 'X';
            printBoardFogOfWar();
            System.out.println("You hit a ship!");
            //printBoard();
        } else {
            this.board[row][column] = 'M';
            printBoardFogOfWar();
            System.out.println("You missed!");
            //printBoard();
        }
        //else Output "You missed", set [row][column] to 'M'
    }

    public  boolean checkCoordinatesFreeRow(int row, int start, int end) {
        boolean coordinatesFree = true;

        if(start >= 1) {
            if(this.board[row][start-1] == 'O'){
                coordinatesFree = false;
            }
        }
        if(end <= 8) {
            if(this.board[row][end + 1] == 'O') {
                coordinatesFree = false;
            }
        }

        for (int i = start; i <= end; i++) {
            if(this.board[row][i] == 'O') {
                coordinatesFree = false;
                break;
            }
        }

        if (row > 0) {
            for (int i = start; i <= end; i++) {
                if(this.board[row - 1][i] == 'O') {
                    coordinatesFree = false;
                    break;
                }
            }
        }

        if (row < 9) {
            for (int i = start; i <= end; i++) {
                if(this.board[row + 1][i] == 'O') {
                    coordinatesFree = false;
                    break;
                }
            }
        }
        return coordinatesFree;
    }

    public  boolean checkCoordinatesFreeColumn(int column, int start, int end) {
        boolean coordinatesFree = true;

        for (int i = start; i <= end; i++) {
            if(this.board[i][column] == 'O') {
                coordinatesFree = false;
                break;
            }
        }
        if(start >= 1) {
            if(this.board[start-1][column] == 'O'){
                coordinatesFree = false;
            }
        }
        if(end <= 8) {
            if(this.board[end + 1][column] == 'O') {
                coordinatesFree = false;
            }
        }

        for (int i = start; i <= end; i++) {
            if(this.board[i][column] == 'O') {
                coordinatesFree = false;
                break;
            }
        }

        if (column > 0) {
            for (int i = start; i <= end; i++) {
                if(this.board[i][column - 1] == 'O') {
                    coordinatesFree = false;
                    break;
                }
            }
        }

        if (column < 9) {
            for (int i = start; i <= end; i++) {
                if(this.board[i][column + 1] == 'O') {
                    coordinatesFree = false;
                    break;
                }
            }
        }
        return coordinatesFree;
    }

    public boolean isLengthOkay(String startCoordinates, String endCoordinates, int lengthOfShip) {
        int start;
        int end;
        if (Objects.equals(startCoordinates.charAt(0), endCoordinates.charAt(0))) {
            start = Math.min(Integer.parseInt(endCoordinates.substring(1)) - 1, Integer.parseInt(startCoordinates.substring(1)) - 1);
            end = Math.max(Integer.parseInt(endCoordinates.substring(1)) - 1, Integer.parseInt(startCoordinates.substring(1)) - 1);
        } else {
            start = Math.min(startCoordinates.charAt(0) - 65, endCoordinates.charAt(0) - 65);
            end = Math.max(startCoordinates.charAt(0) - 65, endCoordinates.charAt(0) - 65);
        }
        return end - start + 1 == lengthOfShip ? true: false;
    }

    public boolean isCellFree(String startCoordinates, String endCoordinates) {
        int start;
        int end;

        if (Objects.equals(startCoordinates.charAt(0), endCoordinates.charAt(0))) {
            int row = startCoordinates.charAt(0) - 65;
            start = Math.min(Integer.parseInt(endCoordinates.substring(1)) - 1, Integer.parseInt(startCoordinates.substring(1)) - 1);
            end = Math.max(Integer.parseInt(endCoordinates.substring(1)) - 1, Integer.parseInt(startCoordinates.substring(1)) - 1);
            return checkCoordinatesFreeRow(row, start, end);
        } else {
            int column = Integer.parseInt(startCoordinates.substring(1)) - 1;
            start = Math.min(startCoordinates.charAt(0) - 65, endCoordinates.charAt(0) - 65);
            end = Math.max(startCoordinates.charAt(0) - 65, endCoordinates.charAt(0) - 65);
            return checkCoordinatesFreeColumn(column, start, end);
        }
    }

    public boolean isCoordinateDiagonal(String startCoordinates, String endCoordinates) {
        if (Objects.equals(startCoordinates.charAt(0), endCoordinates.charAt(0))) {
            return false;
        } else if (Objects.equals(startCoordinates.substring(1), endCoordinates.substring(1))) {
            return false;
        } else {
            return true;
        }
    }

    public  void populateShipOnboard(String startCoordinates, String endCoordinates, Ships ship) {
        int start;
        int end;

        if (Objects.equals(startCoordinates.charAt(0), endCoordinates.charAt(0))) {
            int row = startCoordinates.charAt(0) - 65;
            start = Math.min(Integer.parseInt(endCoordinates.substring(1)) - 1, Integer.parseInt(startCoordinates.substring(1)) - 1);
            end = Math.max(Integer.parseInt(endCoordinates.substring(1)) - 1, Integer.parseInt(startCoordinates.substring(1)) - 1);
            for (int i = start; i <= end; i++) {
                this.board[row][i] = 'O';
            }
        } else{
            int column = Integer.parseInt(startCoordinates.substring(1)) - 1;
            start = Math.min(startCoordinates.charAt(0) - 65, endCoordinates.charAt(0) - 65);
            end = Math.max(startCoordinates.charAt(0) - 65, endCoordinates.charAt(0) - 65);
            for (int i = start; i <= end; i++) {
                this.board[i][column] = 'O';
            }
        }
        printBoard();
    }
}

