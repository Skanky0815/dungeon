package de.dungeon.game;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import de.dungeon.game.character.Player;
import de.dungeon.game.character.PlayerBuilder;
import de.dungeon.game.command.PlayerStatusCommand;
import de.dungeon.game.scenery.Scenery;
import de.dungeon.game.scenery.SceneryFactory;

public class Game extends AbstractModule {

    private final FrontController controller;
    private final Injector injector;
    private Player player;

    public Game() {
        injector = Guice.createInjector(this);
        controller = injector.getInstance(FrontController.class);
    }

    @Override
    protected void configure() {
        bind(Player.class).toProvider(() -> player);
    }

    public static void main(final String[] args) {
        final var game = new Game();

        try {
            game.run();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void run() throws Exception {
        controller.action("Willkommen im Dungeon!\nWie willst du heißen?\n", this::createPlayerAndStartTheGame);
    }

    private void createPlayerAndStartTheGame(final String name) throws Exception {
        player = PlayerBuilder.build(name, 14, 8, 0, 5).get();
        System.out.printf("Du bist der %s. Viel Spaß beim spielen.\n\n", player.getName());
        controller.addCommand("c", injector.getInstance(PlayerStatusCommand.class));
        final var sceneryFactory = injector.getInstance(SceneryFactory.class);
        final var sceneries = sceneryFactory.init();
        var index = (int) (Math.random() * sceneries.size());
        for (Scenery scenery : sceneries.values()) {
            if (--index < 0) {
                scenery.run();
                return;
            }
        };
    }
}
