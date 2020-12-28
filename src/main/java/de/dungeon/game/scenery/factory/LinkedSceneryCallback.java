package de.dungeon.game.scenery.factory;

import de.dungeon.game.command.Command;
import de.dungeon.game.scenery.Scenery;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface LinkedSceneryCallback {
    void call(@NotNull final Command command, @NotNull final Scenery scenery, @NotNull final String text);
}
