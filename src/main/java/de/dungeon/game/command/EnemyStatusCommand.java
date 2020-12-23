package de.dungeon.game.command;

import com.google.inject.Inject;
import de.dungeon.game.character.enemy.Enemy;
import de.dungeon.game.view.EnemyStatusView;

public class EnemyStatusCommand extends EnemyCommand {

    private final EnemyStatusView view;

    @Inject
    public EnemyStatusCommand(final EnemyStatusView view) {
        this.view = view;
    }

    public EnemyStatusCommand init(final Enemy enemy) {
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
