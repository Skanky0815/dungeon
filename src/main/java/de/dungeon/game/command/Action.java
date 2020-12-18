package de.dungeon.game.command;

import de.dungeon.game.character.enemy.Enemy;

import java.util.Map;

public class Action {

    private final String key;
    private final FrontController controller;
    private final String text;
    private Map<String, Command> commands;
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
            final Map<String, Command> commands
    ) {
        this(key, controller, text);
        this.commands = commands;
    }

    public boolean run() {
        return true;
    }
}
