package de.dungeon.game.command;

import de.dungeon.game.character.enemy.Enemy;
import de.dungeon.game.view.EnemyStatusView;

public class EnemyStatusCommand extends EnemyCommand {

    private final EnemyStatusView view;

    public EnemyStatusCommand(final Enemy enemy, final EnemyStatusView view) {
        super("Den Status von %s abfragen.".formatted(enemy.getName()), null, enemy);
        view.setEnemy(enemy);
        this.view = view;
    }

    @Override
    protected boolean doing() {
        view.render();
        return false;
    }
}
