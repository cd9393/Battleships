package com.craig;

import java.util.Arrays;

public class Ship {
    int size;
    String name;
    char[] cells;
    boolean isPlaced;
    boolean isSank;
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
