package zad4;

import java.util.List;

public class LinearInterpolationPercentile implements PercentileStrategy {
    @Override
    public int calculatePercentile(List<Integer> values, double p) {
        int n = values.size();

        if (p < 100.0 * 0.5 / n) {
            return values.get(0);
        }
        if (p > 100.0 * (n - 0.5) / n) {
            return values.get(n - 1);
        }

        int index = (int) Math.floor((n - 1) * p / 100.0); // indeks elementa kod traženog percentila (iznad)
        double w = (n - 1) * p / 100.0 - index; // interpolacijski faktor
        int v1 = values.get(index);
        int v2 = values.get(index + 1);

        return (int) Math.round(v1 + w * (v2 - v1));
    }
}
