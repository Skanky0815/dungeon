package de.dungeon.game.command;

public class UnknownPropertyException extends CommandException {
    public UnknownPropertyException(final String property) {
        super("Property %s does not exists!".formatted(property));
    }
}
