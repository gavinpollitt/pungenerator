package uk.gav.pun;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import uk.gav.pun.service.MathRandomiser;
import uk.gav.pun.service.Randomiser;

public class PunGeneratorRandomishTest {
    private Randomiser randomiser = new MathRandomiser();

    @Test
    public void ensureRandomisinBoundsTest() {
        Metric m = getRandom(0, 100, 10000);
        assertTrue(m.max <= 100 && m.min >= 0,
                "At least one random number has falled out of range: (" + m.min + "," + m.max + ")");
    }

    @Test
    public void ensureRandomisinSingleNumberRangeTest() {
        Metric m = getRandom(1500, 1500, 10000);
        assertTrue(m.max == 1500 && m.min == 1500,
                "At least one random number has falled out of range: (" + m.min + "," + m.max + ")");
    }

    private Metric getRandom(long ll, long ul, int iter) {
        Metric m = new Metric();
        for (int i = 0; i < iter; i++) {
            long num = randomiser.getValue(ll, ul);

            if (num > m.max) {
                m.max = num;
            }

            if (num < m.min) {
                m.min = num;
            }
        }

        return m;
    }

    private static class Metric {
        private long min = Long.MAX_VALUE;
        private long max = Long.MIN_VALUE;
    }
}
