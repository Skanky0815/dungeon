package de.dungeon.game.character.enemy.behavior.factory;

import com.google.inject.Inject;
import com.google.inject.Injector;
import de.dungeon.game.character.enemy.behavior.Behavior;
import de.dungeon.game.character.enemy.behavior.DamageBehavior;
import de.dungeon.game.rule.Damage;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

class DamageBehaviorFactory extends BehaviorTypeFactory {

    @Inject
    public DamageBehaviorFactory(@NotNull final Injector injector) {
        super(injector);
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public Behavior create(@NotNull final Map behaviorData) {
        final var damageData = (Map<String, Integer>) behaviorData.get("damage");
        return injector.getInstance(DamageBehavior.class).setDamage(createDamage(damageData));
    }

    private Damage createDamage(@NotNull final Map<String, Integer> damageData) {
        return injector.getInstance(Damage.class).init(
                damageData.get("diceCount"),
                damageData.get("diceType"),
                damageData.get("modifier")
        );
    }
}
