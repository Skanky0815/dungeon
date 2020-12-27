package de.dungeon.game.command;

import org.jetbrains.annotations.NotNull;

public class UnknownPropertyException extends CommandException {
    public UnknownPropertyException(@NotNull final String property) {
        super("Property %s does not exists!".formatted(property));
    }
}
