package de.dungeon.game.character.enemy.behavior;

import com.google.inject.Inject;
import de.dungeon.game.battle.DamageHandler;
import de.dungeon.game.character.Player;
import de.dungeon.game.rule.Damage;
import de.dungeon.game.rule.UnknownDiceException;

public class DamageBehavior extends Behavior {

    private final DamageHandler damageHandler;
    private final Player player;

    private Damage damage;

    @Inject
    public DamageBehavior(
            final DamageHandler damageHandler,
            final Player player
    ) {
        this.damageHandler = damageHandler;
        this.player = player;
    }

    public DamageBehavior setDamage(final Damage damage) {
        this.damage = damage;
        return this;
    }

    @Override
    public void doBehavior() {
        try {
            damageHandler.makeDamage(damage.calculateDamage(), enemy, player);
        } catch (UnknownDiceException ignore) { }
    }
}
