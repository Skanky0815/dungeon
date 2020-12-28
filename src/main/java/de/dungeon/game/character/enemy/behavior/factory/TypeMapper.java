package de.dungeon.game.character.enemy.behavior.factory;

import com.google.inject.Inject;
import de.dungeon.game.character.enemy.behavior.Behavior;

import org.jetbrains.annotations.NotNull;

class TypeMapper {

    private final DamageBehaviorFactory damageBehaviorFactory;

    @Inject
    public TypeMapper(@NotNull final DamageBehaviorFactory damageBehaviorFactory) {
        this.damageBehaviorFactory = damageBehaviorFactory;
    }

    public Behavior createBehaviorByType(@NotNull final BehaviorMapper mapper) throws UnknownBehaviorTypeException {
        return switch (mapper.getType()) {
            case "damage" -> damageBehaviorFactory.create(mapper);
            default -> throw new UnknownBehaviorTypeException(mapper.getType());
        };
    }
}
