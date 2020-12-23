package de.dungeon.game.rule;

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

    public int calculateDamage() throws UnknownDiceException {
        var damage = modifier;
        for (int i = 0; i < diceCount; i++) {
            damage += rollForDamage();
        }
        return damage;
    }

    private int rollForDamage() throws UnknownDiceException {
        return switch (diceType) {
            case 6 -> Dice.rollD6();
            case 20 -> Dice.rollD20();
            default -> throw new UnknownDiceException(diceType);
        };
    }
}
