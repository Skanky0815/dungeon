package de.dungeon.game.character.enemy.behavior.factory;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.dungeon.game.rule.DamageProperty;

public class DamageMapper implements DamageProperty {

    @JsonProperty("dice_count") private int diceCount;
    @JsonProperty("dice_type") private int diceType;
    private int modifier;

    public int getDiceCount() {
        return diceCount;
    }

    public void setDiceCount(int diceCount) {
        this.diceCount = diceCount;
    }

    public int getDiceType() {
        return diceType;
    }

    public void setDiceType(int diceType) {
        this.diceType = diceType;
    }

    public int getModifier() {
        return modifier;
    }

    public void setModifier(int modifier) {
        this.modifier = modifier;
    }

    public static DamageMapper build(final int diceCount, final int diceType, final int modifier) {
        final var mapper = new DamageMapper();
        mapper.setDiceCount(diceCount);
        mapper.setDiceType(diceType);
        mapper.setModifier(modifier);
        return mapper;
    }
}
