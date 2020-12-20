package de.dungeon.game.command;

public class UnknownCommandException extends CommandException {
    public UnknownCommandException(final String message) {
        super("Command %s does not exists!".formatted(message));
    }
}
