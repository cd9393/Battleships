package com.craig;

public enum Ships {
    AIRCRAFT_CARRIER("Aircraft Carrier", 5, "Enter the coordinates of the Aircraft Carrier (5 cells):"),
    BATTLESHIP("Battleship", 4 , "Enter the coordinates of the Battleship (4 cells):"),
    SUBMARINE("Submarine", 3, "Enter the coordinates of the Submarines (3 cells):"),
    CRUISER("Cruiser", 3, "Enter the coordinates of the Cruiser (3 cells):"),
    DESTROYER("Destroyer", 2 , "Enter the coordinates of the Destroyer (2 cells):");

    private String name;
    private int length;
    private String message;

    private Ships(String name, int length, String message) {
        this.length = length;
        this.name = name;
        this.message = message;
    }
    public String getName() {
        return this.name;
    }

    public int getLength() {
        return this.length;
    }

    public String getMessage() {
        return this.message;
    }
}
