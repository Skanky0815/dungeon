package de.dungeon.game.command;

import de.dungeon.game.character.enemy.Enemy;

public abstract class EnemyCommand extends Command {

    protected Enemy enemy;

    public void setEnemy(final Enemy enemy) {
        this.enemy = enemy;
    }
}
