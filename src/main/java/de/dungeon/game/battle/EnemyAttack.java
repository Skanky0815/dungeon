package de.dungeon.game.battle;

import com.google.inject.Inject;
import de.dungeon.game.character.enemy.Enemy;
import de.dungeon.game.rule.Dice;
import org.jetbrains.annotations.NotNull;

public class EnemyAttack {

    private final Dice dice;
    private Enemy enemy;

    @Inject
    public EnemyAttack(@NotNull final Dice dice) {
        this.dice = dice;
    }

    public void setEnemy(@NotNull Enemy enemy) {
        this.enemy = enemy;
    }

    public void attack() {
        var actions = enemy.getActions();
        for (int action = 1; action <= actions; action++) {
            actions = rollFroBehavior(actions);
        }
    }

    private int rollFroBehavior(int actions) {
        final var diceResult = dice.rollD20();

        if (20 == diceResult) {
            return 0;
        }

        return handleSuccess(diceResult, actions);
    }

    private int handleSuccess(int diceResult, int actions) {
        enemy.getBehavior(diceResult).doBehavior();

        return handleCriticalSuccess(diceResult, actions);
    }

    private int handleCriticalSuccess(final int diceResult, int actions) {
        if (1 == diceResult) {
            actions++;
        }

        return actions;
    }
}
