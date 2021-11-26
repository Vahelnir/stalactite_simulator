package fr.valenstophe.simugrotte.util;

import java.util.SplittableRandom;

public class RandomUtils {
    public static final SplittableRandom random = new SplittableRandom();

    public static int randomBetween(int min, int max) {
        return min + random.nextInt(max - min + 1);
    }

    public static SplittableRandom getRandom() {
        return random;
    }
}
