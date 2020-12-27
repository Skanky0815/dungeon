package de.dungeon.game.command;

import com.google.inject.Inject;
import de.dungeon.game.character.enemy.Enemy;
import de.dungeon.game.view.EnemyStatusView;
import org.jetbrains.annotations.NotNull;

public class EnemyStatusCommand extends EnemyCommand {

    private final EnemyStatusView view;

    @Inject
    public EnemyStatusCommand(@NotNull final EnemyStatusView view) {
        this.view = view;
    }

    public EnemyStatusCommand init(@NotNull final Enemy enemy) {
        super.init("Den Status von %s abfragen.".formatted(enemy.getName()), null);
        setEnemy(enemy);
        view.setEnemy(enemy);
        return this;
    }

    @Override
    protected boolean doing() {
        view.render();
        return false;
    }
}
