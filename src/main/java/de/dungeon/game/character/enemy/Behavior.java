package de.dungeon.game.character.enemy;

import de.dungeon.game.rule.Damage;

public final class Behavior {

    private final String text;
    private final int min;
    private final int max;
    private final Damage damage;

    public Behavior(final String text, final int min, final int max) {
      this(text, min, max, null);
    }

    public Behavior(final String text, final int min, final int max, final Damage damage) {
        this.text = text;
        this.min = min;
        this.max = max;
        this.damage = damage;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public Damage getDamage() {
        return damage;
    }

    public String getText() {
        return text;
    }

    public boolean isInRange(int value) {
        return min <= value && value <= max;
    }
}
