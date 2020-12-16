package Rule;

public class TestResult {
    final private int diceValue;
    final private int comparativeValue;

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
