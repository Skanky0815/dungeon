package Rule;

import java.util.Random;

final public class Dice {

    final private static Random random = new Random();

    public static int rollD6() {
        return random.nextInt((6 - 1)) + 1;
    }

    public static int rollD20() {
        return random.nextInt((20 - 1)) + 1;
    }
}
