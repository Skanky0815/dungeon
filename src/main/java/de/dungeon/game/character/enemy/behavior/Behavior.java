package de.dungeon.game.character.enemy.behavior;

import de.dungeon.game.character.enemy.Enemy;

public class Behavior {

    protected Enemy enemy;

    private String text;
    private int min;
    private int max;

    public void init(final String text, final int min, final int max) {
        this.text = text;
        this.min = min;
        this.max = max;
    }

    public void setEnemy(final Enemy enemy) {
        this.enemy = enemy;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public void doBehavior() {
        System.out.println(text);
    }

    public String getText() {
        return text;
    }

    public boolean isInRange(int value) {
        return min <= value && value <= max;
    }
}
