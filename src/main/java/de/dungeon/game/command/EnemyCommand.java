package de.dungeon.game.command;

import com.google.inject.Inject;
import de.dungeon.game.character.enemy.Enemy;
import de.dungeon.game.view.View;
import org.jetbrains.annotations.NotNull;

public abstract class EnemyCommand extends Command {

    protected Enemy enemy;

    @Inject
    public EnemyCommand(@NotNull final View view) {
        super(view);
    }

    public EnemyCommand setEnemy(@NotNull final Enemy enemy) {
        this.enemy = enemy;
        return this;
    }
}
