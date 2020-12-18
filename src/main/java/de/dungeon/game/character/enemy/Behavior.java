package de.dungeon.game.character.enemy;

import de.dungeon.game.rule.Damage;

final public class Behavior {

    final private String text;
    final private int min;
    final private int max;
    final private Damage damage;

    public Behavior(final String text, final int min, final int max) {
      this(text, min, max, null);
    }

    public Behavior(final String text, final int min, final int max, final Damage damage) {
        this.text = text;
        this.min = min;
        this.max = max;
        this.damage = damage;
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
