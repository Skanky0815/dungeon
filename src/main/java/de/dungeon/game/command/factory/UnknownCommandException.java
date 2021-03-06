package de.dungeon.game.command.factory;

import org.jetbrains.annotations.NotNull;

public class UnknownCommandException extends CommandException {
    public UnknownCommandException(@NotNull final String message) {
        super("Command %s does not exists!".formatted(message));
    }
}
