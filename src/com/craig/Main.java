package com.craig;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Board board = new Board(10, 10);
        Board player2 = new Board(10, 10);
        boolean isGameOver = false;

        board.printBoard();
        Scanner scanner = new Scanner(System.in);
        String startCoordinates;
        String endCoordinates;
        String shotCoordinates;

        System.out.println("Player 1, place your ships on the game field");
        for (Ships ship : Ships.values()) {
            boolean isCoordinatesOkay = false;
            do {
                System.out.println(ship.getMessage());
                startCoordinates = scanner.next();
                endCoordinates = scanner.next();
                isCoordinatesOkay = areShipCoordinatesOkay(board,startCoordinates,endCoordinates, ship.getLength());
            } while(!isCoordinatesOkay);
            board.populateShipOnboard(startCoordinates,endCoordinates, ship);
        }
        promptEnterKey();
        System.out.println("Player 2, place your ships to the game field");
        player2.printBoardFogOfWar();

        for (Ships ship : Ships.values()) {
            boolean isCoordinatesOkay = false;
            do {
                System.out.println(ship.getMessage());
                startCoordinates = scanner.next();
                endCoordinates = scanner.next();
                isCoordinatesOkay = areShipCoordinatesOkay(player2,startCoordinates,endCoordinates, ship.getLength());
            } while(!isCoordinatesOkay);
            player2.populateShipOnboard(startCoordinates,endCoordinates, ship);
        }

        promptEnterKey();

        while (!isGameOver) {
            boolean areShotCoordinatesOkay = false;
            player2.printBoardFogOfWar();
            System.out.println("---------------------");
            board.printBoard();
            System.out.println("Player 1, it's your turn:");
            do {
                shotCoordinates = scanner.next();
                areShotCoordinatesOkay = isShotCoordinatesOkay(shotCoordinates);

            } while(!areShotCoordinatesOkay);
            player2.takeShot(shotCoordinates);
            isGameOver = player2.isAllShipsDestroyed();
            if (isGameOver) {
                break;
            }

            promptEnterKey();

            areShotCoordinatesOkay = false;
            board.printBoardFogOfWar();
            System.out.println("---------------------");
            player2.printBoard();
            System.out.println("Player 2, it's your turn:");
            do {
                shotCoordinates = scanner.next();
                areShotCoordinatesOkay = isShotCoordinatesOkay(shotCoordinates);

            } while(!areShotCoordinatesOkay);
            board.takeShot(shotCoordinates);
            isGameOver = board.isAllShipsDestroyed();
            if (isGameOver) {
                break;
            }
        }

        System.out.println("You sank the last ship. You won. Congratulations!");
    }

    public static void promptEnterKey() {
        System.out.println("Press Enter and pass the move to another player");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
