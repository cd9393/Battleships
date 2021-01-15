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
        String shotCoordinates;

        for (Ships ship : Ships.values()) {
            boolean isCoordinatesOkay = false;
            do {
                System.out.println(ship.getMessage());
                 startCoordinates = scanner.next();
                 endCoordinates = scanner.next();
                 isCoordinatesOkay = areShipCoordinatesOkay(board,startCoordinates,endCoordinates, ship.getLength());
            } while(!isCoordinatesOkay);
            board.populateShipOnboard(startCoordinates,endCoordinates);
        }

        System.out.println("The game starts!");
        boolean areShotCoordinatesOkay = false;
        do {
            System.out.println("Take a shot");
            shotCoordinates = scanner.next();
            areShotCoordinatesOkay = isShotCoordinatesOkay(shotCoordinates);

        } while(!areShotCoordinatesOkay);
        board.takeShot(shotCoordinates);
    }

    public static boolean isShotCoordinatesOkay(String shotCoordinates) {
        int row = shotCoordinates.charAt(0) - 65;
        int column = Integer.parseInt(shotCoordinates.substring(1));
        if (row < 0 || row > 10 || column < 0 || column > 10) {
            System.out.println("You have entered incorrect coordinates");
            return false;
        }else {
            return true;
        }
    }
    public static boolean areShipCoordinatesOkay(Board board, String startCoordinates, String endCoordinates, int shipLength) {
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
