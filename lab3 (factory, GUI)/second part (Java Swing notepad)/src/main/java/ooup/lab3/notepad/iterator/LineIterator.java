package ooup.lab3.notepad.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class LineIterator implements Iterator<String> {
    private final List<String> lines;
    private int currIndex;
    private final int end;

    public LineIterator(List<String> lines) {
        this.lines = lines;
        this.currIndex = 0;
        this.end = lines.size();
    }

    public LineIterator(List<String> lines, int index1, int index2) throws IndexOutOfBoundsException { //range
        if (index2 > lines.size()) throw new IndexOutOfBoundsException();
        this.lines = lines;
        this.currIndex = index1;
        this.end = index2;
    }

    @Override
    public boolean hasNext() {
        return currIndex < end;
    }

    @Override
    public String next() {
        if(hasNext()) {
            return lines.get(currIndex++);
        } else {
            throw new NoSuchElementException();
        }
    }
}
