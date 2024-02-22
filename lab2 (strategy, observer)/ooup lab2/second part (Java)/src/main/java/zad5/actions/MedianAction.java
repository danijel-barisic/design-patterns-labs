package zad5.actions;

import zad5.Listener;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MedianAction implements Listener {
    @Override
    public void update(List<Integer> sequence) {
        double median;
        if (!sequence.isEmpty()){
            List<Integer> sorted = sequence.stream().sorted().toList();
            int mid = sorted.size() / 2;
            if (sorted.size() % 2 == 0) {
                median = (sorted.get(mid - 1) + sorted.get(mid)) / 2.0;
            } else {
                median = sorted.get(mid);
            }
            System.out.println("Median is: " + median);
        }
    }
}
