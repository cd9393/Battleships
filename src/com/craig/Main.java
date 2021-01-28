package com.craig;

public class Main {

    // It's good keeping your entry point away from any logic and just instantiating your Game object,
    // keeps the entry point clean. Only recommendation would be to move the main to the Game class
    public static void main(String[] args) {
        // write your code here
        Game game = new Game();
        game.play();

    }
}
