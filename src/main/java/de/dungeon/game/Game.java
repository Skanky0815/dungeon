package de.dungeon.game;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import de.dungeon.game.character.Player;
import de.dungeon.game.character.player.PlayerBuilder;
import de.dungeon.game.command.PlayerStatusCommand;
import de.dungeon.game.scenery.factory.SceneryFactory;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Game extends AbstractModule {

    private final FrontController controller;
    private final Injector injector;
    private final Text text;
    private Player player;

    public Game() {
        injector = Guice.createInjector(this);
        controller = injector.getInstance(FrontController.class);
        text = injector.getInstance(Text.class);
    }

    @Override
    protected void configure() {
        bind(Player.class).toProvider(() -> player);
        bind(BufferedReader.class).toInstance(new BufferedReader(new InputStreamReader(System.in)));
    }

    public static void main(final String[] args) {
        try {
            (new Game()).run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void run() throws Exception {
        controller.action(text.get("game.welcome"), this::createPlayerAndStartTheGame);
    }

    private void createPlayerAndStartTheGame(@NotNull final String name) throws Exception {
        player = PlayerBuilder.build(name, 14, 8, 0, 5).get();
        System.out.printf(text.get("game.start"), player.getName());
        controller.addCommand("c", injector.getInstance(PlayerStatusCommand.class).init());

        final var sceneries = injector.getInstance(SceneryFactory.class).init();
        var index = (int) (Math.random() * sceneries.size());
        for (var scenery : sceneries.values()) {
            if (--index < 0) {
                scenery.run();
                return;
            }
        }
    }
}
