package de.dungeon.game.rule;

import com.google.inject.Inject;
import org.jetbrains.annotations.NotNull;

public class Damage {

    private final Dice dice;

    private int diceCount;
    private int diceType;
    private int modifier;

    @Inject
    public Damage(@NotNull Dice dice) {
        this.dice = dice;
    }

    public Damage init(final int diceCount, final int diceType, final int modifier) {
        this.diceCount = diceCount;
        this.diceType = diceType;
        this.modifier = modifier;
        return this;
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
            case 6 -> dice.rollD6();
            case 20 -> dice.rollD20();
            default -> throw new UnknownDiceException(diceType);
        };
    }
}
