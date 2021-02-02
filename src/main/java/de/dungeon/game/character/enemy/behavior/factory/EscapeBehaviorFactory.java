package de.dungeon.game.character.enemy.behavior.factory;

import com.google.inject.Injector;
import de.dungeon.game.character.enemy.behavior.Behavior;
import de.dungeon.game.character.enemy.behavior.EscapeBehavior;
import org.jetbrains.annotations.NotNull;

public class EscapeBehaviorFactory extends BehaviorTypeFactory {

    public EscapeBehaviorFactory(@NotNull final Injector injector) {
        super(injector);
    }

    public Behavior create(@NotNull final BehaviorMapper behaviorMapper) {
        return injector.getInstance(EscapeBehavior.class);
    }
}
