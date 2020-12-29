package de.dungeon.game.character.enemy.behavior.factory;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.dungeon.game.character.enemy.behavior.BehaviorProperty;

public class BehaviorMapper implements BehaviorProperty {

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

    public static BehaviorMapper build(String text, int min, int max) {
        final var mapper = new BehaviorMapper();
        mapper.setText(text);
        mapper.setMin(min);
        mapper.setMax(max);
        return mapper;
    }

    public static BehaviorMapper build(final String text, final int min, final int max, final String type) {
        final var mapper = build(text, min, max);
        mapper.setType(type);
        return mapper;
    }

    public static BehaviorMapper build(
            final String text,
            final int min,
            final int max,
            final String type,
            final DamageMapper damageMapper
    ) {
        final var mapper = build(text, min, max, type);
        mapper.setDamageMapper(damageMapper);
        return mapper;
    }
}
