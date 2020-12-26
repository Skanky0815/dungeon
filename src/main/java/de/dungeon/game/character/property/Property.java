package de.dungeon.game.character.property;

import de.dungeon.game.rule.Dice;
import de.dungeon.game.rule.TestResult;

public class Property {

    private final Dice dice;
    private int value;
    private int modifier;

    public Property(final Dice dice) {
        this.dice = dice;
    }

    public Property init(int value, int modifier) {
        this.value = value;
        this.modifier = modifier;
        return this;
    }

    public Property init(int value) {
        return this.init(value, 0);
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        return value + (0 == modifier ? "" : " (+" + modifier + ")");
    }

    public int getModifier() {
        return modifier;
    }

    public void setModifier(int modifier) {
        this.modifier = modifier;
    }

    public TestResult test(final int modifier) {
        return new TestResult(dice.rollD20(), (value + this.modifier + modifier));
    }

    public TestResult test() {
       return test(0);
    }

    public boolean isTheTestSuccessfully() {
        return test().isSuccess();
    }
}
