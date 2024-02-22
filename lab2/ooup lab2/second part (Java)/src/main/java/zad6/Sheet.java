package zad6;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Sheet {

    private final int numRows;
    private final int numCols;
    private final Cell[][] cells;
    private final Set<Cell> evalSet;

    public Sheet(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.evalSet = new HashSet();
        this.cells = new Cell[numRows][numCols];
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                cells[row][col] = new Cell(this, "0");
            }
        }
    }

    public void set(String ref, String content){
        cell(ref).setExp(content);
    }

    public Double evaluate(Cell cell) {
        if (evalSet.contains(cell)) {
            throw new RuntimeException("Circular dependency detected");
        }
        evalSet.add(cell);

        String exp = cell.getExp();
        String[] parts = exp.split("\\+");
        Double result = 0.0;
        for (String part : parts) {
            part = part.trim();
            if (part.matches("[A-Z]+[0-9]+")) {
                Cell ref = cell(part);
                result += evaluate(ref);
            } else {
                result += Double.parseDouble(part);
            }
        }
        evalSet.clear();
        return result;
    }

    public Cell cell(String ref) {
        int col = ref.charAt(0) - 'A';
        int row = Integer.parseInt(ref.substring(1)) - 1;
        return cells[row][col];
    }

    public void print() {
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                System.out.printf("%5s", cells[row][col].getValue());
            }
            System.out.println();
        }
    }

    public List<Cell> getRefs(Cell cell) {
        List<Cell> refs = new ArrayList<>();
        String[] parts = cell.getExp().split("\\+");
        for (String part : parts) {
            part = part.trim();
            if (part.matches("[A-Z]+[0-9]+")) {
                Cell ref = cell(part);
                refs.add(ref);
            }
        }
        return refs;
    }

}
