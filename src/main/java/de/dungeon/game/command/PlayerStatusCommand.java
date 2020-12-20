package de.dungeon.game.command;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.dungeon.game.view.PlayerStatusView;

@Singleton
public class PlayerStatusCommand extends Command {

    private final PlayerStatusView view;

    @Inject
    public PlayerStatusCommand(final PlayerStatusView view) {
        super("Charakterbogen");
        this.view = view;
    }

    @Override
    protected boolean doing() {
        view.render();

        return false;
    }
}
