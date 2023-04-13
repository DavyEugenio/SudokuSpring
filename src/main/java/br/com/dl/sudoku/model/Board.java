package br.com.dl.sudoku.model;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<Cell> cells;

    public Board() {
        cells = new ArrayList<>();
        for (int i = 0; i < 81; i++) {
            this.cells.add(new Cell(i, new ArrayList<Integer>()));
        }
    }

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }

    public List<Cell> getLine(int index) {
        List<Cell> r = new ArrayList<>();
        index *= 9;
        for (int i = index; i <= index + 8; i++) {
            r.add(cells.get(i));
        }
        return r;
    }

    public List<Cell> getColumn(int index) {
        List<Cell> r = new ArrayList<>();
        for (int i = index; i <= index + 72; i += 9) {
            r.add(cells.get(i));
        }
        return r;
    }
    
    public void defCell(int index, Integer n) {
        cells.get(index).defCell(n);
    }

    public List<Cell> getSquare(int index) {
        List<Cell> r = new ArrayList<>();
        int mod = index % 3;

        if (index >= 3 && index <= 5) {
            index = 27 + mod * 3;
        } else if (index >= 6) {
            index = 54 + mod * 3;
        } else {
            index = mod * 3;
        }

        for (int i = index; i <= index + 18; i += 9) {
            for (int j = i; j <= i + 2; j++) {
                r.add(cells.get(j));
            }
        }
        return r;
    }

    public int getSquareIndex(int index) {
        int iCol = getColIndex(index),
                iLin = getLineIndex(index),
                r;
        if (iLin <= 2) {
            if (iCol < 3) {
                r = 0;
            } else if (iCol >= 3 & iCol < 6) {
                r = 1;
            } else {
                r = 2;
            }
        } else if (iLin >= 3 & iLin < 6) {
            if (iCol < 3) {
                r = 3;
            } else if (iCol >= 3 & iCol < 6) {
                r = 4;
            } else {
                r = 5;
            }
        } else {
            if (iCol < 3) {
                r = 6;
            } else if (iCol >= 3 & iCol < 6) {
                r = 7;
            } else {
                r = 8;
            }
        }
        return r;
    }
    
    public int getLineIndex(int index) {
        return (index - getColIndex(index)) / 9;
    }
    
    public int getColIndex(int index) {
        return index % 9;
    }
}
