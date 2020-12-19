package de.dungeon.game.command;

public class ExitCommand extends Command {

    public ExitCommand() {
        super("Beenden");
    }

    @Override
    protected boolean doing() {
        System.exit(0);
        return true;
    }
}
