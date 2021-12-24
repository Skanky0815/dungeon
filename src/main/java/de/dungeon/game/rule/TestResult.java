package de.dungeon.game.rule;

public class TestResult {
    private final int diceValue;
    private final int comparativeValue;

    public TestResult(final int diceValue, final int comparativeValue) {
        this.diceValue = diceValue;
        this.comparativeValue = comparativeValue;
    }

    public boolean isCriticalSuccess() {
        return 1 == diceValue;
    }

    public boolean isSuccess() {
        return diceValue <= comparativeValue;
    }

    public boolean isFailureSuccess() {
        return 20 == diceValue;
    }
}
