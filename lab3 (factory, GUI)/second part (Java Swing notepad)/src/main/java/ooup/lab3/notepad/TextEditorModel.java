package ooup.lab3.notepad;

import ooup.lab3.notepad.iterator.LineIterator;
import ooup.lab3.notepad.observer.CursorObserver;
import ooup.lab3.notepad.observer.TextObserver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TextEditorModel {
    private List<String> lines;
    private LocationRange selectionRange;
    private Location cursorLocation;

    private final List<CursorObserver> cursorObservers;
    private List<TextObserver> textObservers;

    public TextEditorModel(String text) {
        lines = new ArrayList<>();
        selectionRange = null;
        cursorLocation = new Location(0, 0);

        String[] linesArray = text.split("\n");
        lines.addAll(Arrays.asList(linesArray));
        cursorObservers = new ArrayList<>();
        textObservers = new ArrayList<>();
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public void setLine(int lineNum, String line) {
        if (lineNum >= 0 && lineNum < lines.size()) {
            lines.set(lineNum, line);
        } else {
            throw new IndexOutOfBoundsException("Invalid line number");
        }
    }

    public void removeLine(int lineNumber) {
        if (lineNumber >= 0 && lineNumber < lines.size()) {
            lines.remove(lineNumber);
        } else {
            throw new IndexOutOfBoundsException("Invalid line number");
        }
    }

    public LocationRange getSelectionRange() {
        return selectionRange;
    }

    public void setSelectionRange(LocationRange selectionRange) {
        this.selectionRange = selectionRange;
    }

    public Location getCursorLocation() {
        return cursorLocation;
    }

    public void setCursorLocation(Location cursorLocation) {
        this.cursorLocation = cursorLocation;
    }

    public String getSelectedText() {
        if (selectionRange != null) {
            StringBuilder sb = new StringBuilder();
            for (int i = selectionRange.getStart().getRow(); i <= selectionRange.getEnd().getRow(); i++) {
                String line = lines.get(i);
                int startCol = (i == selectionRange.getStart().getRow()) ? selectionRange.getStart().getCol() : 0;
                int endCol = (i == selectionRange.getEnd().getRow()) ? selectionRange.getEnd().getCol() : line.length();
                sb.append(line, startCol, endCol);
                if (i != selectionRange.getEnd().getRow()) {
                    sb.append("\n");
                }
            }
            return sb.toString();
        }
        return null;
    }


    public Iterator<String> allLines() {
        //return lines.iterator();
        return new LineIterator(lines);
    }

    public Iterator<String> linesRange(int index1, int index2) {
        //return lines.subList(index1, index2).iterator();
        return new LineIterator(lines, index1, index2);
    }

    public void moveCursorLeft() {
        if (cursorLocation.getCol() > 0) {
            cursorLocation.setCol(cursorLocation.getCol() - 1);
        } else if (cursorLocation.getRow() > 0) {
            cursorLocation.setCol(lines.get(cursorLocation.getRow() - 1).length());
            cursorLocation.setRow(cursorLocation.getRow() - 1);
        }
        notifyCursorObservers();
    }


    public void moveCursorRight() {
        String currentLine = lines.get(cursorLocation.getRow());
        if (cursorLocation.getCol() < currentLine.length()) {
            cursorLocation.setCol(cursorLocation.getCol() + 1);
        } else if (cursorLocation.getRow() < lines.size() - 1) {
            cursorLocation.setCol(0);
            cursorLocation.setRow(cursorLocation.getRow() + 1);
        }
        notifyCursorObservers();
    }

    public void moveCursorUp() {
        if (cursorLocation.getRow() > 0) {
            int newRow = cursorLocation.getRow() - 1;
            int newCol = cursorLocation.getCol();
            if (cursorLocation.getCol() > lines.get(cursorLocation.getRow()-1).length()) {
                newCol = lines.get(cursorLocation.getRow()-1).length();
            }
            cursorLocation.set(newRow, newCol);
            notifyCursorObservers();
        }
    }

    public void moveCursorDown() {
        if (cursorLocation.getRow() < lines.size() - 1) {
            cursorLocation.setRow(cursorLocation.getRow() + 1);
            String currentLine = lines.get(cursorLocation.getRow());
            if (cursorLocation.getCol() > currentLine.length()) {
                cursorLocation.setCol(currentLine.length());
            }
            notifyCursorObservers();
        }
    }

    public void moveCursorLeftWithSelection() {
//        Location selectionStart = (selectionRange == null || selectionRange.getStart()==null ?
//                cursorLocation : selectionRange.getStart());
        if (cursorLocation.getCol() > 0) {
            cursorLocation.setCol(cursorLocation.getCol() - 1);
        } else if (cursorLocation.getRow() > 0) {
            cursorLocation.setCol(lines.get(cursorLocation.getRow() - 1).length());
            cursorLocation.setRow(cursorLocation.getRow() - 1);
        }
        //selectionRange = new LocationRange(selectionStart, cursorLocation);
        //selectionRange= new LocationRange((selectionRange!=null ? selectionRange.getEnd() : selectionStart),cursorLocation );
        selectionRange= new LocationRange(new Location(0,0), cursorLocation);
        notifyCursorObservers();
    }

    public void moveCursorRightWithSelection() {

        String currentLine = lines.get(cursorLocation.getRow());

        if (cursorLocation.getCol() < currentLine.length()) {
            cursorLocation.setCol(cursorLocation.getCol() + 1);
        } else if (cursorLocation.getRow() < lines.size() - 1) {
            cursorLocation.setCol(0);
            cursorLocation.setRow(cursorLocation.getRow() + 1);
        }

        selectionRange= new LocationRange(new Location(0,0), cursorLocation);

        notifyCursorObservers();
    }

    public void moveCursorUpWithSelection() {
        Location oldCursorLocation = cursorLocation;

        if (cursorLocation.getRow() > 0) {
            int newRow = cursorLocation.getRow() - 1;
            int newCol = cursorLocation.getCol();
            if (cursorLocation.getCol() > lines.get(cursorLocation.getRow()-1).length()) {
                newCol = lines.get(cursorLocation.getRow()-1).length();
            }
            cursorLocation.set(newRow, newCol);
            selectionRange= new LocationRange(new Location(0,0), cursorLocation);

            notifyCursorObservers();
        }

    }

    public void moveCursorDownWithSelection() {

        if (cursorLocation.getRow() < lines.size() - 1) {
            cursorLocation.setRow(cursorLocation.getRow() + 1);
            String currentLine = lines.get(cursorLocation.getRow());
            if (cursorLocation.getCol() > currentLine.length()) {
                cursorLocation.setCol(currentLine.length());
            }
            selectionRange= new LocationRange(new Location(0,0), cursorLocation);


            notifyCursorObservers();
        }
    }

    public void addCursorObserver(CursorObserver observer) {
        cursorObservers.add(observer);
    }

    public void removeCursorObserver(CursorObserver observer) {
        cursorObservers.remove(observer);
    }

    public void notifyCursorObservers() {
        for (CursorObserver cursorObserver : cursorObservers) {
            cursorObserver.updateCursorLocation(cursorLocation);
        }
    }

    public void addTextObserver(TextObserver observer) {
        textObservers.add(observer);
    }

    public void removeTextObserver(TextObserver observer) {
        textObservers.remove(observer);
    }

    public void notifyTextObservers() {
        for (TextObserver textObserver : textObservers) {
            textObserver.updateText();
        }
    }

    public void deleteBefore() {
        if (cursorLocation.getCol() > 0) {
            String currentLine = lines.get(cursorLocation.getRow());
            StringBuilder sb = new StringBuilder(currentLine);
            sb.deleteCharAt(cursorLocation.getCol() - 1);
            lines.set(cursorLocation.getRow(), sb.toString());
            moveCursorLeft();
            notifyTextObservers();
            notifyCursorObservers();
        } else if (cursorLocation.getRow() > 0) {
            String currentLine = lines.get(cursorLocation.getRow());
            String previousLine = lines.get(cursorLocation.getRow() - 1);
            String sb = previousLine + currentLine;
            lines.remove(cursorLocation.getRow());
            lines.set(cursorLocation.getRow() - 1, sb);
            cursorLocation.setRow(cursorLocation.getRow() - 1);
            cursorLocation.setCol(previousLine.length());
            notifyTextObservers();
            notifyCursorObservers();
        }
    }

    public void insertCharacter(char keyChar) {
        String currentLine = lines.get(cursorLocation.getRow());
        StringBuilder sb = new StringBuilder(currentLine);
        sb.insert(cursorLocation.getCol(), keyChar);
        lines.set(cursorLocation.getRow(), sb.toString());
        cursorLocation.setCol(cursorLocation.getCol() + 1);
        notifyTextObservers();
        notifyCursorObservers();
    }

    public void addNewLine() {
        //insertCharacter('\n');
        String currentLine = lines.get(cursorLocation.getRow());
        String linePart1 = currentLine.substring(0, cursorLocation.getCol());
        String linePart2 = currentLine.substring(cursorLocation.getCol());

//        StringBuilder sb = new StringBuilder(currentLine);
//        sb.delete(cursorX, currentLine.length());
        lines.set(cursorLocation.getRow(), linePart1);
        lines.add(cursorLocation.getRow() + 1, linePart2);

        cursorLocation.setCol(0);
        cursorLocation.setRow(cursorLocation.getRow() + 1);

    }

//    public void deleteBefore() {
//        int row = cursorLocation.getRow();
//        int col = cursorLocation.getCol();
//
//        if (row >= 0 && row < lines.size()) {
//            StringBuilder line = new StringBuilder(lines.get(row));
//            if (col > 0) {
//                line.deleteCharAt(col - 1);
//                moveCursorLeft();
//            } else if (col == 0 && row > 0) {
//                StringBuilder prevLine = new StringBuilder(lines.get(row - 1));
//                //int prevLineLength = prevLine.length();
//                prevLine.append(line);
//                lines.remove(row);
//                moveCursorUp();
//            }
//            notifyTextObservers();
//            notifyCursorObservers();
//        }
//    }

    public void deleteAfter() {
        int row = cursorLocation.getRow();
        int col = cursorLocation.getCol();

        if (row >= 0 && row < lines.size()) {
            StringBuilder line = new StringBuilder(lines.get(row));
            if (col < line.length()) {
                line.deleteCharAt(col);
                notifyTextObservers();
                notifyCursorObservers();
            } else if (col == line.length() && row < lines.size() - 1) {
                line.append(lines.get(row + 1));
                lines.remove(row + 1);
                notifyTextObservers();
                notifyCursorObservers();
            }
            lines.set(row,line.toString());
        }
    }

    public void deleteRange(LocationRange range) {
        if (range.getStart().getRow() < 0 || range.getStart().getRow() >= lines.size()
                || range.getEnd().getRow() < 0 || range.getEnd().getRow() >= lines.size()) {
            throw new IllegalArgumentException("Invalid range.");
        }

        Location start = range.getStart();
        Location end = range.getEnd();

        int startRow = start.getRow();
        int startCol = start.getCol();
        int endRow = end.getRow();
        int endCol = end.getCol();

        if (startRow == endRow) {
            StringBuilder line = new StringBuilder(lines.get(startRow));
            line.delete(startCol, endCol);
        } else {
            StringBuilder startLine = new StringBuilder(lines.get(startRow));
            StringBuilder endLine = new StringBuilder(lines.get(endRow));

            startLine.delete(startCol, startLine.length());
            endLine.delete(0, endCol);

            for (int i = startRow + 1; i < endRow; i++) {
                lines.remove(startRow + 1);
            }

            startLine.append(endLine);
        }

        cursorLocation.set(startRow,startCol);
        notifyTextObservers();
        notifyCursorObservers();
    }
}
