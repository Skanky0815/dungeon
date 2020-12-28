package de.dungeon.game.scenery.factory;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface SceneryCallback {
    void call(@NotNull String input) throws Exception;
}
