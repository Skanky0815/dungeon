package de.dungeon.game.battle;

import de.dungeon.game.character.Player;
import de.dungeon.game.character.enemy.Enemy;

public class PlayerAttack {

    private final Player player;
    private final DamageHandler damageHandler;

    public PlayerAttack(final Player player, final DamageHandler damageHandler) {
        this.player = player;
        this.damageHandler = damageHandler;
    }

    public void attack(final Enemy enemy) {
        if (player.tryToDoAMeleeAttack()) {
            // TODO: get damage from selected weapon!
            damageHandler.makeDamage(5, player, enemy);
            return;
        }

        System.out.println("Es passiert nichts");
    }
}
