package de.dungeon.game.character.enemy.behavior.factory;

import com.google.inject.Inject;
import com.google.inject.Injector;
import de.dungeon.game.character.enemy.behavior.Behavior;
import org.jetbrains.annotations.NotNull;

public class BehaviorFactory {

    private final Injector injector;
    private final TypeMapper typeMapper;

    @Inject
    public BehaviorFactory(@NotNull final Injector injector, @NotNull final TypeMapper typeMapper) {
        this.injector = injector;
        this.typeMapper = typeMapper;
    }

    public Behavior create(@NotNull final BehaviorMapper mapper) throws UnknownBehaviorTypeException {
        final var behavior = createBehaviorByTypeOrDefault(mapper);
        behavior.init(mapper.getText(), mapper.getMin(), mapper.getMax());
        return behavior;
    }

    private Behavior createBehaviorByTypeOrDefault(
            @NotNull final BehaviorMapper mapper
    ) throws UnknownBehaviorTypeException {
        if (mapper.hasType()) {
            return typeMapper.createBehaviorByType(mapper);
        }

        return injector.getInstance(Behavior.class);
    }
}
