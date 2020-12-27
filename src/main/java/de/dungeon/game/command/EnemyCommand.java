package de.dungeon.game.command;

import de.dungeon.game.character.enemy.Enemy;
import org.jetbrains.annotations.NotNull;

public abstract class EnemyCommand extends Command {

    protected Enemy enemy;

    public void setEnemy(@NotNull final Enemy enemy) {
        this.enemy = enemy;
    }
}
