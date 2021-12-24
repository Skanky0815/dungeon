package de.dungeon.game.command;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.dungeon.game.view.PlayerStatusView;
import org.jetbrains.annotations.NotNull;

@Singleton
public class PlayerStatusCommand extends Command {

    @Inject
    public PlayerStatusCommand(@NotNull final PlayerStatusView view) {
        super(view);
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
