package de.dungeon.game.command.factory;

import org.jetbrains.annotations.NotNull;

public class CommandException extends Exception {
    public CommandException(@NotNull final String message) {
        super(message);
    }
}
