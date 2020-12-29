package de.dungeon.game.character.enemy.behavior;

import de.dungeon.game.character.enemy.Enemy;
import org.jetbrains.annotations.NotNull;

public class Behavior {

    protected Enemy enemy;

    private String text;
    private int min;
    private int max;

    public void init(@NotNull final BehaviorProperty property) {
        this.text = property.getText();
        this.min = property.getMin();
        this.max = property.getMax();
    }

    public void setEnemy(@NotNull final Enemy enemy) {
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
