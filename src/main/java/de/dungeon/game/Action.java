package de.dungeon.game;

import de.dungeon.game.character.enemy.Enemy;
import de.dungeon.game.command.Command;

import java.util.List;

public class Action {

    private final String key;
    private final FrontController controller;
    private final String text;
    private List<Command> commands;
    private Enemy enemy;

    public Action(final String key, final FrontController controller, final String text) {
        this.key = key;
        this.controller = controller;
        this.text = text;
    }

    public Action(
            final String key,
            final FrontController controller,
            final String text,
            final List<Command> commands,
            final Enemy enemy
    ) {
        this(key, controller, text);
        this.commands = commands;
        this.enemy = enemy;
    }

    public void run() {
        var question = new StringBuilder((null == enemy ? text : text.formatted(enemy.getName())));
        int key = 0;
        for (Command command : commands) {
            question.append("[%d] %s\n".formatted(key++, command.getText()));
        }

        if (!controller.action(question)) {
            run();
        }
    }
}
