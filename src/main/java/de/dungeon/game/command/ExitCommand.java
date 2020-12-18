package de.dungeon.game.command;

public class ExitCommand extends Command {

    public ExitCommand(String text, String doText) {
        super(text, doText);
    }

    @Override
    protected boolean doing() {
        System.exit(0);
        return true;
    }
}
