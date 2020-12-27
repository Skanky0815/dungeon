package de.dungeon.game.character.enemy;

import com.google.inject.Inject;
import com.google.inject.Injector;
import de.dungeon.game.rule.Damage;

import java.util.Map;

public class BehaviorFactory {

    private final Injector injector;

    @Inject
    public BehaviorFactory(final Injector injector) {
        this.injector = injector;
    }

    @SuppressWarnings (value="unchecked")
    public Behavior create(final Map behaviorData) throws UnknownBehaviorTypeException {
        Behavior behavior;
        if (behaviorData.containsKey("type")) {
            behavior = switch ((String) behaviorData.get("type")) {
                case "damage" -> createDamageBehavior((Map<String, Integer>) behaviorData.get("damage"));
                default -> throw new UnknownBehaviorTypeException((String) behaviorData.get("type"));
            };
        } else {
            behavior = injector.getInstance(Behavior.class);
        }
        behavior.init((String) behaviorData.get("text"), (int) behaviorData.get("min"), (int) behaviorData.get("max"));
        return behavior;
    }

    private DamageBehavior createDamageBehavior(final Map<String, Integer> damageData) {
        return injector.getInstance(DamageBehavior.class).setDamage(injector.getInstance(Damage.class).init(
                damageData.get("diceCount"),
                damageData.get("diceType"),
                damageData.get("modifier")
        ));
    }
}
