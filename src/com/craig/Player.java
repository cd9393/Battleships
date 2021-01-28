package com.craig;

public class Player {

    // These fields should be private & final
    // Also should be using getters + setters for access (encapsulation)
    Board board;
    String name;

    public Player(String name) {
        this.name = name;
        // Having each board be associated with/belong to a Player is fine, but I'd maybe construct the board and pass
        // it into the constructor instead
        board = new Board(10);
    }
}
