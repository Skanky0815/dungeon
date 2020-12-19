package de.dungeon.game;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.dungeon.game.character.PlayerBuilder;
import de.dungeon.game.command.PlayerStatusCommand;

public class Game {

    private final FrontController controller;
    private final Injector injector;

    public Game() {
        controller = new FrontController();
        injector = Guice.createInjector();
    }

    public static void main(String[] args) {
        final var game = new Game();
        game.run();
    }

    private void run() {
        var text = new StringBuilder("Willkommen im Dungeon!\nWie willst du heißen?\n");
        controller.action(text, this::createPlayerAndStartTheGame);
    }

    private void createPlayerAndStartTheGame(final String name) {
        final var playerBuilder = new PlayerBuilder();
        final var player = playerBuilder.build(name, 14, 8, 0, 5).get();
        System.out.printf("Du bist der %s. Viel Spaß beim spielen.\n\n", player.getName());

        final var playerStatusCommand = injector.getInstance(PlayerStatusCommand.class);
        playerStatusCommand.setPlayer(player);

        controller.addCommand("c", playerStatusCommand);
        /*
        command_factory: CommandFactory = CommandFactory(self.__player, self.__test)
        action_factory: ActionFactory = ActionFactory(self.__player, self.__controller, command_factory)
        action_factory.start()
         */
    }
}
