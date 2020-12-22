package de.dungeon.game.command;

import de.dungeon.game.battle.DamageHandler;
import de.dungeon.game.battle.EnemyAttack;
import de.dungeon.game.battle.PlayerAttack;
import de.dungeon.game.character.Player;
import de.dungeon.game.character.enemy.Enemy;

public class FightCommand extends EnemyCommand {

    private final Player player;
    private final PlayerAttack playerAttack;
    private final EnemyAttack enemyAttack;

    public FightCommand(final Player player, final Enemy enemy) {
        super("%s kämpft!".formatted(player.getName()), "%s angreifen.".formatted(enemy.getName()), enemy);
        this.player = player;
        this.playerAttack = new PlayerAttack(player, new DamageHandler());
        this.enemyAttack = new EnemyAttack(enemy);
    }

    @Override
    protected boolean doing() {
        System.out.printf("%s versucht %s anzugreifen\n", player.getName(), enemy.getName());
        playerAttack.attack(enemy);
        if (enemy.isAlive()) {
            enemyAttack.attack(player);
            return false;
        }

        return true;
    }
}
