package de.dungeon.game.command;

import com.google.inject.Inject;
import de.dungeon.game.character.enemy.Enemy;
import de.dungeon.game.view.EnemyStatusView;
import org.jetbrains.annotations.NotNull;

public class EnemyStatusCommand extends EnemyCommand {

    @Inject
    public EnemyStatusCommand(@NotNull final EnemyStatusView view) {
        super(view);
    }

    @Override
    public EnemyStatusCommand setEnemy(@NotNull final Enemy enemy) {
        super.init("Den Status von %s abfragen.".formatted(enemy.getName()), null);
        ((EnemyStatusView) view).setEnemy(enemy);
        return (EnemyStatusCommand) super.setEnemy(enemy);
    }

    @Override
    protected boolean doing() {
        view.render();
        return false;
    }
}
