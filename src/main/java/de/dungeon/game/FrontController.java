package de.dungeon.game;

import com.google.inject.Inject;
import de.dungeon.game.command.Command;
import de.dungeon.game.command.ExitCommand;
import de.dungeon.game.scenery.SceneryCallback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class FrontController {

    private final Map<String, Command> commands;
    private final BufferedReader reader;

    @Inject
    public FrontController(final ExitCommand exitCommand) {
        commands = new HashMap<>() {{
            put("x", exitCommand);
        }};

        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void addCommand(final String key, final Command command) {
        commands.put(key, command);
    }

    public boolean action(final String text, final SceneryCallback callback) {
        System.out.println(text + setupCommands());
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
