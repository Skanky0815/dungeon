package de.dungeon.game.command;

public class ExitCommand extends Command {

    public ExitCommand init() {
        super.init("Beenden", null);
        return this;
    }

    @Override
    protected boolean doing() {
        System.exit(0);
        return true;
    }
}
