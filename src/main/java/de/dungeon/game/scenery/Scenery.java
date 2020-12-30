package de.dungeon.game.scenery;

import com.google.inject.Inject;
import de.dungeon.game.FrontController;
import de.dungeon.game.character.enemy.Enemy;
import de.dungeon.game.command.Command;
import de.dungeon.game.view.View;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Scenery {

    private final FrontController controller;
    private final View view;

    private String key;
    private String text;
    private List<Command> commands;
    private Enemy enemy;

    @Inject
    public Scenery(@NotNull final FrontController controller, @NotNull final View view) {
        this.controller = controller;
        this.view = view;
    }

    public Scenery init(
            @NotNull final String key,
            @NotNull final String text,
            @NotNull final List<Command> commands
    ) {
        this.key = key;
        this.text = text;
        this.commands = commands;
        return this;
    }
    public Scenery init(
            @NotNull final String key,
            @NotNull final String text,
            @NotNull final List<Command> commands,
            @NotNull final Enemy enemy
    ) {
        this.enemy = enemy;
        return this.init(key, text, commands);
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
            view.render("game.scenery.option_not_found", index);
            run();
        } else {
            if (commands.get(index).doAction()) {
                view.render("game.scenery.next_scenery", null);
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
