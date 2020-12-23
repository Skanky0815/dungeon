package de.dungeon.game.rule;

public class UnknownDiceException extends Exception {
    public UnknownDiceException(final int diceType) {
        super("Dice %d does not exists!".formatted(diceType));
    }
}
