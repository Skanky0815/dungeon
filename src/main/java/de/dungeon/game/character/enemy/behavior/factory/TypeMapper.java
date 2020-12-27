package de.dungeon.game.character.enemy.behavior.factory;

import com.google.inject.Inject;
import de.dungeon.game.character.enemy.UnknownBehaviorTypeException;
import de.dungeon.game.character.enemy.behavior.Behavior;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

class TypeMapper {

    private final DamageBehaviorFactory damageBehaviorFactory;

    @Inject
    public TypeMapper(final DamageBehaviorFactory damageBehaviorFactory) {
        this.damageBehaviorFactory = damageBehaviorFactory;
    }

    public Behavior createBehaviorByType(@NotNull final Map behaviorData) throws UnknownBehaviorTypeException {
        return switch ((String) behaviorData.get("type")) {
            case "damage" -> damageBehaviorFactory.create(behaviorData);
            default -> throw new UnknownBehaviorTypeException((String) behaviorData.get("type"));
        };
    }
}
