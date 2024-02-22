package zad5.actions;

import zad5.Listener;

import java.util.List;

public class AverageAction implements Listener {
    @Override
    public void update(List<Integer> sequence) {
        if(!sequence.isEmpty()){
            int sum = 0;
            double avg;
            for (Integer num : sequence) {
                sum += num;
            }
            avg = (double) sum / sequence.size();
            System.out.println("Average is: " + avg);
        }
    }
}
