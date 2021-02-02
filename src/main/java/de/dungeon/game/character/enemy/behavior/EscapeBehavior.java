package de.dungeon.game.character.enemy.behavior;

import com.google.inject.Inject;
import de.dungeon.game.view.View;
import org.jetbrains.annotations.NotNull;

public class EscapeBehavior extends Behavior {

    @Inject
    public EscapeBehavior(@NotNull final View view) {
        super(view);
    }

    @Override
    public void doBehavior() {
        enemy.die();
        super.doBehavior();
    }
}
