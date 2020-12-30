package de.dungeon.game.character.enemy.behavior;

import com.google.inject.Inject;
import de.dungeon.game.battle.DamageHandler;
import de.dungeon.game.character.Player;
import de.dungeon.game.rule.Damage;
import de.dungeon.game.rule.UnknownDiceException;
import de.dungeon.game.view.View;
import org.jetbrains.annotations.NotNull;

public class DamageBehavior extends Behavior {

    private final DamageHandler damageHandler;
    private final Player player;

    private Damage damage;

    @Inject
    public DamageBehavior(
            @NotNull final DamageHandler damageHandler,
            @NotNull final Player player,
            @NotNull final View view
            ) {
        super(view);
        this.damageHandler = damageHandler;
        this.player = player;
    }

    public DamageBehavior setDamage(@NotNull final Damage damage) {
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
