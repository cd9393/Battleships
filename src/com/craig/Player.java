package com.craig;

public class Player {
    Board board;
    String name;

    public Player(String name) {
        this.name = name;
        board = new Board(10);
    }
}
