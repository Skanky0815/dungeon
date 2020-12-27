package de.dungeon.game.command;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.dungeon.game.view.PlayerStatusView;
import org.jetbrains.annotations.NotNull;

@Singleton
public class PlayerStatusCommand extends Command {

    private final PlayerStatusView view;

    @Inject
    public PlayerStatusCommand(@NotNull final PlayerStatusView view) {
        this.view = view;
    }

    public PlayerStatusCommand init() {
        super.init("Charakterbogen", null);
        return this;
    }

    @Override
    protected boolean doing() {
        view.render();

        return false;
    }
}
