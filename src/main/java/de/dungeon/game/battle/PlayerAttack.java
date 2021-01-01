package de.dungeon.game.battle;

import com.google.inject.Inject;
import de.dungeon.game.character.Player;
import de.dungeon.game.character.enemy.Enemy;
import de.dungeon.game.rule.UnknownDiceException;
import org.jetbrains.annotations.NotNull;

public class PlayerAttack {

    private final Player player;
    private final DamageHandler damageHandler;

    @Inject
    public PlayerAttack(@NotNull final Player player, @NotNull final DamageHandler damageHandler) {
        this.player = player;
        this.damageHandler = damageHandler;
    }

    public void attack(@NotNull final Enemy enemy, final int weaponKey) {
        final var weapon = player.getWeapon(weaponKey);
        if (player.tryToAttackWithWeapon(weapon)) {
            try {
                damageHandler.makeDamage(weapon.getDamage(), player, enemy);

                // TODO: check if defender is alive
                    // TODO: if not remove them and the scenery

                // TODO go to success
            } catch (UnknownDiceException ignore) { }
        }
    }
}
