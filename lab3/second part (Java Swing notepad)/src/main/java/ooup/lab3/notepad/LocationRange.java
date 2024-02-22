package ooup.lab3.notepad;

public class LocationRange {
    private Location start;
    private Location end;

    public LocationRange(Location start, Location end) {
        if (start.getRow() > end.getRow() || start.getRow() == end.getRow() && start.getCol() > end.getCol()) {
            Location tmp = start;
            start = end;
            end = tmp;
        }
        this.start = start;
        this.end = end;
    }

    public Location getStart() {
        return start;
    }

//    public void setStart(Location start) {
//        this.start = start;
//    }

    public Location getEnd() {
        return end;
    }

//    public void setEnd(Location end) {
//        this.end = end;
//    }
}