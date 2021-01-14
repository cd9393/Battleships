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
            boolean isCoordinatesOkay = false;
            do {
                System.out.println(ship.getMessage());
                 startCoordinates = scanner.next();
                 endCoordinates = scanner.next();
                 isCoordinatesOkay = areCoordinatesOkay(board,startCoordinates,endCoordinates, ship.getLength());
            } while(!isCoordinatesOkay);
            board.populateShipOnboard(startCoordinates,endCoordinates);
        }
    }

    public static boolean areCoordinatesOkay(Board board, String startCoordinates, String endCoordinates, int shipLength) {
        if(board.isCoordinateDiagonal(startCoordinates, endCoordinates)) {
            System.out.println("Error ship can't be placed diagonally.");
            return false;
        } else if (!board.isLengthOkay(startCoordinates, endCoordinates, shipLength)){
            System.out.println("Error wrong length, please enter coordinates with length " + shipLength);
            return false;
        } else if (!board.isCellFree(startCoordinates,endCoordinates)) {
            System.out.println("Error coordinates are too close to other ship");
            return false;
        } else {
            return true;
        }
    }
}
