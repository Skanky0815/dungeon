package de.dungeon.game.command;

import com.google.inject.Inject;
import de.dungeon.game.Text;
import de.dungeon.game.view.View;
import org.jetbrains.annotations.NotNull;

public class ExitCommand extends Command {

    private final Text text;

    @Inject
    public ExitCommand(@NotNull final View view, @NotNull final Text text) {
        super(view);
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
