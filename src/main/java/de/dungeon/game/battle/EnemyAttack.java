package de.dungeon.game.battle;

import de.dungeon.game.character.Player;
import de.dungeon.game.character.enemy.Enemy;
import de.dungeon.game.rule.Dice;

public class EnemyAttack {

    private Enemy enemy;

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public void attack(final Player player) {
        for (int action = 1; action <= enemy.getActions(); action++) {
            var behavior = enemy.getBehavior(Dice.rollD20());
            System.out.println(behavior.getText());
            // TODO: hier gehts weiter mit dem herausfinden, was die action macht :D
        }
    }

}
