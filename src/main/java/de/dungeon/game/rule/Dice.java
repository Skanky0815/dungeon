package de.dungeon.game.rule;

import com.google.inject.Inject;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class Dice {

    private final Random random;

    @Inject
    public Dice(@NotNull final Random random) {
        this.random = random;
    }

    public int rollD20() {
        return random.nextInt((20 - 1)) + 1;
    }

    public int rollD6() {
        return random.nextInt((6 - 1)) + 1;
    }
}
