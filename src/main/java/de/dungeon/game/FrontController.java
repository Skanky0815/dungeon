package de.dungeon.game;

import de.dungeon.game.command.Command;
import de.dungeon.game.command.ExitCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class FrontController {

    private final Map<String, Command> commands;
    private final BufferedReader reader;

    public FrontController() {
        commands = new HashMap<>() {{
            put("x", new ExitCommand());
        }};

        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void addCommand(final String key, final Command command) {
        commands.put(key, command);
    }

    public boolean action(StringBuilder text) {
        return this.action(text, null);
    }

    public boolean action(StringBuilder text, ActionCallback callback) {
        text.append(setupCommands());

        System.out.println(text);
        final String input;
        try {
            input = reader.readLine();
            if (commands.containsKey(input)) {
                commands.get(input).doAction();
                return false;
            }

            if (null != callback) {
                callback.call(input);
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return false;
    }

    private StringBuilder setupCommands() {
        final var text = new StringBuilder("---------------------------------------\n");
        for (Map.Entry<String, Command> entry : commands.entrySet()) {
            text.append("[%s] %s\n".formatted(entry.getKey(), entry.getValue().getText()));
        }
        text.append("\n");

        return text;
    }
}
