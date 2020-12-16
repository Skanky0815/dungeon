package Rule;

public class Damage {

    final private int diceCount;
    final private int diceType;
    final private int modifier;

    public Damage(final int diceCount, final int diceType, final int modifier) {
        this.diceCount = diceCount;
        this.diceType = diceType;
        this.modifier = modifier;
    }

    public int getDiceCount() {
        return diceCount;
    }

    public int getDiceType() {
        return diceType;
    }

    public int getModifier() {
        return modifier;
    }
}
