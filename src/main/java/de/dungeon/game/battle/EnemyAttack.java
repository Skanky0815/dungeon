package de.dungeon.game.battle;

import com.google.inject.Inject;
import de.dungeon.game.character.enemy.Enemy;
import de.dungeon.game.rule.Dice;

public class EnemyAttack {

    private final Dice dice;
    private Enemy enemy;

    @Inject
    public EnemyAttack(final Dice dice) {
        this.dice = dice;
    }

    public void setEnemy(Enemy enemy) {
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
        if (1 == diceResult) {
            actions++;
        }
        if (20 == diceResult) {
            return 0;
        }

        enemy.getBehavior(diceResult).doBehavior();

        return actions;
    }
}
