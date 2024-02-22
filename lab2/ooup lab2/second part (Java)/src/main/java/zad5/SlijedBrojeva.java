package zad5;

import java.util.ArrayList;
import java.util.List;

public class SlijedBrojeva {
    private final SourceStrategy source;
    private final List<Integer> sequence = new ArrayList<>();
    private final List<Listener> actions = new ArrayList<>();

    public SlijedBrojeva(SourceStrategy source) {
        this.source = source;
    }

    public void addAction(Listener action) {
        actions.add(action);
    }

    public void removeAction(Listener action) {
        actions.remove(action);
    }

    public void notifyActions() {
        for (Listener action : actions) {
            action.update(sequence);
        }
    }

    public void addToSequence(int num) {
        sequence.add(num);
        notifyActions();
    }

    public void kreni() {
        int num;
        while ((num = source.getNextNumber()) != -1) {
            addToSequence(num);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
