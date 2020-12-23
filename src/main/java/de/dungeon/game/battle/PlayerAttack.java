package de.dungeon.game.battle;

import de.dungeon.game.character.Player;
import de.dungeon.game.character.enemy.Enemy;
import de.dungeon.game.character.player.Weapon;
import de.dungeon.game.rule.UnknownDiceException;

public class PlayerAttack {

    private final Player player;
    private final DamageHandler damageHandler;

    public PlayerAttack(final Player player, final DamageHandler damageHandler) {
        this.player = player;
        this.damageHandler = damageHandler;
    }

    public void attack(final Enemy enemy) {
        // TODO: implement weapon selection each weapon or attack typ is a separate command
        final var weapon = player.getWeaponList().get(0);
        if (tryToAttackWithWeapon(weapon)) {
            try {
                damageHandler.makeDamage(weapon.getDamage(), player, enemy);
            } catch (UnknownDiceException ignore) {

            }
            return;
        }

        System.out.println("Es passiert nichts");
    }

    private boolean tryToAttackWithWeapon(final Weapon weapon) {
        if (weapon.getTestProperty().isInstance(player.getMelee())) {
            return player.tryToDoAMeleeAttack();
        }

        return false;
    }
}
