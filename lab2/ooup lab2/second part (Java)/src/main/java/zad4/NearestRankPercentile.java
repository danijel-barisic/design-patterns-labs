package zad4;

import java.util.List;

public class NearestRankPercentile implements PercentileStrategy{
    @Override
    public int calculatePercentile(List<Integer> values, double p) {
        int n = values.size();
        int rank = (int) Math.ceil(p * n / 100.0);

        return values.get(rank);
    }
}
