package de.dungeon.game.rule;

import java.util.Random;

public final class Dice {

    private final static Random random = new Random();

    public static int rollD6() {
        return random.nextInt((6 - 1)) + 1;
    }

    public static int rollD20() {
        return random.nextInt((20 - 1)) + 1;
    }
}
