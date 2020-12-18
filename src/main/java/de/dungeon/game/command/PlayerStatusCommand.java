package de.dungeon.game.command;

import de.dungeon.game.character.Player;
import de.dungeon.game.view.PlayerStatusView;

import javax.inject.Inject;

public class PlayerStatusCommand extends Command {
    final private PlayerStatusView view;

    @Inject
    public PlayerStatusCommand(final PlayerStatusView view) {
        super("Charakterbogen");
        this.view = view;
    }

    public void setPlayer(final Player player) {
        this.view.setPlayer(player);
    }

    @Override
    protected boolean doing() {
        view.render();

        return false;
    }
}
