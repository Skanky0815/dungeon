package de.dungeon.game.character.enemy.behavior.factory;

import com.google.inject.Injector;
import de.dungeon.game.character.enemy.behavior.Behavior;
import org.jetbrains.annotations.NotNull;

abstract class BehaviorTypeFactory {

    protected final Injector injector;

    public BehaviorTypeFactory(@NotNull final Injector injector) {
        this.injector = injector;
    }

    abstract Behavior create(@NotNull final BehaviorMapper mapper);
}
