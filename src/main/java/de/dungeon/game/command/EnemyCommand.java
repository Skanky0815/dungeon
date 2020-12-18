package de.dungeon.game.command;

import de.dungeon.game.character.enemy.Enemy;

public abstract class EnemyCommand extends Command {

    protected final Enemy enemy;

    public EnemyCommand(final String text, final String doText, final Enemy enemy) {
        super(text, doText);
        this.enemy = enemy;
    }
}
