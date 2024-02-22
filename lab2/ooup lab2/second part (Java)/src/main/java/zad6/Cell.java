package zad6;

import java.util.ArrayList;
import java.util.List;

public class Cell implements Listener {
    private final Sheet sheet;
    private String exp;
    private Double value = 0.0;

    public Cell(Sheet sheet, String exp) {
        this.sheet = sheet;
        this.exp = exp;
    }

    @Override
    public void update() {
        List<Cell> refs = sheet.getRefs(this); //i.e. get listeners
        value = sheet.evaluate(this);
        for (Cell ref : refs) {
            ref.update();
        }
    }

    public Double getValue() {
        return value;
    }


    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
        update();
    }
}
