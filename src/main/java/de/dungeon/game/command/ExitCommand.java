package de.dungeon.game.command;

import com.google.inject.Inject;
import de.dungeon.game.Text;

public class ExitCommand extends Command {

    private final Text text;

    @Inject
    public ExitCommand(final Text text) {
        this.text = text;
    }

    public ExitCommand init() {
        super.init(text.get("game.exit"), null);
        return this;
    }

    @Override
    protected boolean doing() {
        System.exit(0);
        return true;
    }
}
