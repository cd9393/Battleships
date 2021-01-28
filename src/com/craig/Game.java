package com.craig;

import java.io.IOException;
import java.util.Scanner;

public class Game {


    // RE: Scanner
    // THe scanner is opened in multiple places and never properly closed. You should create a single instance of the Scanner
    // outside of the game loop, and wrap it in a try-with-resources. This ensure that the scanner is ALWAYS closed even in the
    // event of an error occurring.
    // e.g.
    //
    //  try (Scanner scanner = new Scanner(System.in)) {
    //      ...
    //  } catch (Exception ex) {
    //      ...
    //  }
    //
    // or with a regular try-catch-finally:
    // e.g.
    //
    //  Scanner scanner;
    //  try {
    //      scanner = new Scanner(System.in);
    //      ...
    //  } catch (Exception ex) {
    //      ...
    //  } finally {
    //      if(scanner != null) {
    //         scanner.close();
    //      }
    //  }

    public void play(){
        boolean isGameOver = false;
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        System.out.println("Player 1, place your ships on the game field.");

        // Object fields should be accessed via getters & setters
        player1.board.printBoardFogOfWar();
        player1.board.populateShips();

        // I'd pass the one instance of a scanner into this method
        changeTurn();

        System.out.println("Player 2, place your ships on the game field.");
        player2.board.printBoardFogOfWar();
        player2.board.populateShips();

        changeTurn();

        while(!isGameOver) {
            player2.board.printBoardFogOfWar();
            System.out.println("---------------------");
            player1.board.printBoard();
            System.out.println("Player 1, it's your turn:");
            makeShot(player2.board);
            isGameOver = player2.board.isAllShipsDestroyed();
            if (isGameOver) {
                break;
            }

            changeTurn();

            player1.board.printBoardFogOfWar();
            System.out.println("---------------------");
            player2.board.printBoard();
            System.out.println("Player 2, it's your turn:");
            makeShot(player1.board);
            isGameOver = player1.board.isAllShipsDestroyed();
            if (isGameOver) {
                break;
            }

            changeTurn();
        }
        System.out.println("You sank the last ship. You won. Congratulations!");
    }

    public static void makeShot(Board board){
        Scanner scanner = new Scanner(System.in);
        boolean coordinatesOkay = false;
        int row = 0;
        int column = 0;
        while (!coordinatesOkay) {
            System.out.println("Take a shot!");
            String coordinate = scanner.next();
            row = coordinate.charAt(0) - 65; // Not very elegant, you'd be better mapping row chars to ints manually. Either through a utility method, or enum or something
            column = Integer.parseInt(coordinate.substring(1)) - 1;
            System.out.println("row = " + row + " column = " + column);
            coordinatesOkay = shotCoordinatesOkay(row , column);
        }
        scanner.close();
        board.takeShot(row,column);
    }

    // If we make a Coordinate class, this could be used when creating the Coordinate object.
    public static boolean shotCoordinatesOkay(int row, int column){
        if (row < 0 || row > 9 || column < 0 || column > 9) {
            System.out.println("Incorrect coordinates! Try again");
            return false;
        }
        return true;
    }

    public void changeTurn(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();
    }
}
