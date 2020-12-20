package de.dungeon.game.command;

import de.dungeon.game.character.enemy.Enemy;

public class EnemyStatusCommand extends EnemyCommand {

    public EnemyStatusCommand(Enemy enemy) {
        super("Den Status von %s abfragen.".formatted(enemy.getName()), null, enemy);
    }

    @Override
    protected boolean doing() {
        // TODO: add enemy view there
        return false;
    }
}
