package com.craig;

import java.util.Arrays;

// Overall a well structured class, the only thing it does is represent the Ship (Classes that do too much are bad and hard to test)
public class Ship {

    // These fields should be private & final
    // Also should be using getters + setters for access (encapsulation)
    // I see some getters have been used at the bottom, but basically no fields in a class should be directly accessible from
    // outside of the class they are defined.
    int size;
    String name;
    char[] cells;
    boolean isPlaced;
    boolean isSank;

    // instead of storing various ints, you could create a Coordinates class that stores an (X,Y) aka (row,column) and have these
    // Coordinates constructed and passed into the Ship
    // e.g.
    //
    // private final Coordinate begin;
    // private final Coordinate end;
    //
    // with them being built outside the constructor and passed as params, (or set like via setCoordinates) - preference being on the first,
    // final fields MUST be intialised at the time of the object being instansiated, so this forces the Ship objects to
    // have coordinates at runtime
    int rowBegin;
    int rowEnd;
    int columnBegin;
    int columnEnd;

    public Ship(String name, int size) {
        this.size = size;
        this.name = name;
        this.cells = new char[size];
        Arrays.fill(this.cells,'O');
    }

    public void setCoordinates(int rowBegin, int columnBegin, int rowEnd, int columnEnd) {
        this.rowBegin = rowBegin;
        this.rowEnd = rowEnd;
        this.columnBegin = columnBegin;
        this.columnEnd = columnEnd;
        this.isPlaced = true;
    }

    public void setHit(int index){
        cells[index] = 'X';
    }

    public boolean allCellsHit(){
        for(char cell: cells) {
            if (cell == 'O') {
                return false;
            }
        }
        this.isSank = true;
        return true;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public boolean isPlaced() {
        return isPlaced;
    }

    public void setPlaced(boolean placed) {
        isPlaced = placed;
    }

    public boolean isSank() {
        return isSank;
    }

    public void setSank(boolean sank) {
        isSank = sank;
    }

    public int getRowBegin() {
        return rowBegin;
    }

    public void setRowBegin(int rowBegin) {
        this.rowBegin = rowBegin;
    }

    public int getRowEnd() {
        return rowEnd;
    }

    public void setRowEnd(int rowEnd) {
        this.rowEnd = rowEnd;
    }

    public int getColumnBegin() {
        return columnBegin;
    }

    public void setColumnBegin(int columnBegin) {
        this.columnBegin = columnBegin;
    }

    public int getColumnEnd() {
        return columnEnd;
    }

    public void setColumnEnd(int columnEnd) {
        this.columnEnd = columnEnd;
    }
}
