package zad4;

import java.util.List;

interface PercentileStrategy {
    int calculatePercentile(List<Integer> values, double p);
}