package de.dungeon.game.command;

import de.dungeon.game.character.Player;
import de.dungeon.game.character.enemy.Enemy;

public class FightCommand extends EnemyCommand {

    private final Player player;

    public FightCommand(final Player player, final Enemy enemy) {
        super("%s kämpft!".formatted(player.getName()), "%s angreifen.".formatted(enemy.getName()), enemy);
        this.player = player;
    }

    @Override
    protected boolean doing() {
        return false;
    }
}
