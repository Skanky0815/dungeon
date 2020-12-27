package de.dungeon.game.scenery;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface SceneryCallback {
    void call(@NotNull String input) throws Exception;
}
