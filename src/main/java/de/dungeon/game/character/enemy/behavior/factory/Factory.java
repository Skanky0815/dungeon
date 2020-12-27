package de.dungeon.game.character.enemy.behavior.factory;

import com.google.inject.Inject;
import com.google.inject.Injector;
import de.dungeon.game.character.enemy.UnknownBehaviorTypeException;
import de.dungeon.game.character.enemy.behavior.Behavior;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class Factory {

    private final Injector injector;
    private final TypeMapper typeMapper;

    @Inject
    public Factory(@NotNull final Injector injector, @NotNull final TypeMapper typeMapper) {
        this.injector = injector;
        this.typeMapper = typeMapper;
    }

    public Behavior create(@NotNull final Map behaviorData) throws UnknownBehaviorTypeException {
        final var behavior = createBehaviorByTypeOrDefault(behaviorData);
        behavior.init((String) behaviorData.get("text"), (int) behaviorData.get("min"), (int) behaviorData.get("max"));
        return behavior;
    }

    private Behavior createBehaviorByTypeOrDefault(
            @NotNull final Map behaviorData
    ) throws UnknownBehaviorTypeException {
        if (behaviorData.containsKey("type")) {
            return typeMapper.createBehaviorByType(behaviorData);
        }

        return injector.getInstance(Behavior.class);
    }
}
