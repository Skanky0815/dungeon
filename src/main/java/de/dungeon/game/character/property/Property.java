package de.dungeon.game.character.property;

import de.dungeon.game.rule.Dice;
import de.dungeon.game.rule.TestResult;

public class Property {

    final private int value;
    private int modifier;

    public Property(int value, int modifier) {
        this.value = value;
        this.modifier = modifier;
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
        return new TestResult(Dice.rollD20(), (value + this.modifier + modifier));
    }
}
