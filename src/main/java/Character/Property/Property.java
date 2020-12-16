package Character.Property;

import Rule.Dice;
import Rule.TestResult;

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
