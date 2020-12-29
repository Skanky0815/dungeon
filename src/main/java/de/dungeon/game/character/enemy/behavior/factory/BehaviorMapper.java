package de.dungeon.game.character.enemy.behavior.factory;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BehaviorMapper {

    private String text;
    private int min;
    private int max;
    private String type;
    @JsonProperty("damage") private DamageMapper damageMapper;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public boolean hasType() {
        return null != type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DamageMapper getDamageMapper() {
        return damageMapper;
    }

    public void setDamageMapper(DamageMapper damageMapper) {
        this.damageMapper = damageMapper;
    }
}
