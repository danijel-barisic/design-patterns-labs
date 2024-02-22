package zad5.actions;

import zad5.Listener;

import java.util.List;

public class SumAction implements Listener {
    @Override
    public void update(List<Integer> sequence) {
        if (!sequence.isEmpty()){
            int sum = 0;
            for (Integer num : sequence) {
                sum += num;
            }
            System.out.println("Sum is: " + sum);
        }
    }
}
