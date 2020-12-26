package de.dungeon.game.battle;

import com.google.inject.Inject;
import de.dungeon.game.character.Player;
import de.dungeon.game.character.enemy.Enemy;
import de.dungeon.game.rule.UnknownDiceException;

public class PlayerAttack {

    private final Player player;
    private final DamageHandler damageHandler;

    @Inject
    public PlayerAttack(final Player player, final DamageHandler damageHandler) {
        this.player = player;
        this.damageHandler = damageHandler;
    }

    public void attack(final Enemy enemy, final int weaponKey) {
        final var weapon = player.getWeapon(weaponKey);
        if (player.tryToAttackWithWeapon(weapon)) {
            try {
                damageHandler.makeDamage(weapon.getDamage(), player, enemy);
            } catch (UnknownDiceException ignore) {

            }
        }
    }
}
