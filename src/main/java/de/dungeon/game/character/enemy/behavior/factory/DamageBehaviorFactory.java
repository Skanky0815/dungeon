package de.dungeon.game.character.enemy.behavior.factory;

import com.google.inject.Inject;
import com.google.inject.Injector;
import de.dungeon.game.character.enemy.behavior.Behavior;
import de.dungeon.game.character.enemy.behavior.DamageBehavior;
import de.dungeon.game.rule.Damage;
import org.jetbrains.annotations.NotNull;

class DamageBehaviorFactory extends BehaviorTypeFactory {

    @Inject
    public DamageBehaviorFactory(@NotNull final Injector injector) {
        super(injector);
    }

    @Override
    public Behavior create(@NotNull final BehaviorMapper mapper) {
        return injector.getInstance(DamageBehavior.class).setDamage(createDamage(mapper.getDamageMapper()));
    }

    private Damage createDamage(@NotNull final DamageMapper damage) {
        return injector.getInstance(Damage.class).init(
                damage.getDiceCount(),
                damage.getDiceType(),
                damage.getModifier()
        );
    }
}
