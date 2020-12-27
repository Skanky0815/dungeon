package de.dungeon.game.scenery;

import de.dungeon.game.FrontController;
import de.dungeon.game.character.enemy.Enemy;
import de.dungeon.game.command.Command;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Scenery {

    private final String key;
    private final FrontController controller;
    private final String text;
    private List<Command> commands;
    private Enemy enemy;

    public Scenery(@NotNull final String key, @NotNull final FrontController controller, @NotNull final String text) {
        this.key = key;
        this.controller = controller;
        this.text = text;
        commands = new ArrayList<>();
    }

    public Scenery(
            @NotNull final String key,
            @NotNull final FrontController controller,
            @NotNull final String text,
            @NotNull final List<Command> commands,
            @NotNull final Enemy enemy
    ) {
        this(key, controller, text);
        this.commands = commands;
        this.enemy = enemy;
    }

    public Scenery(
            @NotNull final String key,
            @NotNull final FrontController controller,
            @NotNull final String text,
            @NotNull final List<Command> commands
    ) {
        this(key, controller, text);
        this.commands = commands;
    }

    public void run() throws Exception {
        final var text = new StringBuffer((null == enemy ? this.text : this.text.formatted(enemy.getName()))).append("\n");
        addCommands(text);

        if (!controller.action(text.toString(), this::callback)) {
            run();
        }
    }

    private void callback(@NotNull final String input) throws Exception {
        final var index = Integer.parseInt(input);
        if (index < 0 || index >= commands.size()) {
            System.out.printf("No option found for number %d\n", index);
            run();
        } else {
            if (commands.get(index).doAction()) {
                System.out.println("Nächste Action");
            } else {
                run();
            }
        }
    }

    private void addCommands(@NotNull final StringBuffer text) {
        var key = 0;
        for (Command command : commands) {
            text.append("[%d] %s\n".formatted(key++, command.getText()));
        }
    }
}
