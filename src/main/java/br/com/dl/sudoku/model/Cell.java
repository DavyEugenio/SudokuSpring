package br.com.dl.sudoku.model;

import java.util.Arrays;
import java.util.List;

public class Cell {
    
    private int id;
    private List<Integer> numbers;
    private boolean def;

    public Cell(int id, List<Integer> numbers) {
        this.id = id;
        this.numbers = numbers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public boolean isDef() {
        return def;
    }

    public void setDef(boolean def) {
        this.def = def;
    }

    public void defCell(Integer number) {
        numbers = Arrays.asList(number);
        def = true;
    }

    public void removeNumber(Integer number) {
        if (!def){
            this.numbers.remove(number);
            if(this.numbers.size() == 1)
                def = true;
            }
    }

    public void addNumber(Integer number) {
        this.numbers.add(number);
    }

    public void fillNumbers() {
        if (!def) {
            this.numbers.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cell other = (Cell) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        if(def)
            return "Cell{" + "id=" + id + ", numbers=" + numbers + ", def=" + def + '}';
        else
            return "Cell{" + "id=" + id + ", def=" + def + '}';
    }
}
