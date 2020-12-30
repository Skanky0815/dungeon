package de.dungeon.game.character.enemy.behavior;

import com.google.inject.Inject;
import de.dungeon.game.character.enemy.Enemy;
import de.dungeon.game.view.View;
import org.jetbrains.annotations.NotNull;

public class Behavior {

    protected Enemy enemy;

    private final View view;

    private String text;
    private int min;
    private int max;

    @Inject
    public Behavior(@NotNull final View view) {
        this.view = view;
    }

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
        view.render(text);
    }

    public String getText() {
        return text;
    }

    public boolean isInRange(int value) {
        return min <= value && value <= max;
    }
}
