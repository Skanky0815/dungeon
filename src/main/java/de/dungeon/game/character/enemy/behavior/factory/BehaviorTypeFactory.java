package de.dungeon.game.character.enemy.behavior.factory;

import com.google.inject.Injector;
import de.dungeon.game.character.enemy.behavior.Behavior;

import java.util.Map;

abstract class BehaviorTypeFactory {

    protected final Injector injector;

    public BehaviorTypeFactory(final Injector injector) {
        this.injector = injector;
    }

    abstract Behavior create(final Map behaviorData);
}
