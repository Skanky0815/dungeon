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

    public EnemyStatusCommand setEnemy(@NotNull final Enemy enemy) {
        super.init("Den Status von %s abfragen.".formatted(enemy.getName()), null);
        view.setEnemy(enemy);
        return (EnemyStatusCommand) super.setEnemy(enemy);
    }

    @Override
    protected boolean doing() {
        view.render();
        return false;
    }
}
