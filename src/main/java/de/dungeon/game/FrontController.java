package de.dungeon.game;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.dungeon.game.command.Command;
import de.dungeon.game.command.ExitCommand;
import de.dungeon.game.scenery.factory.SceneryCallback;
import de.dungeon.game.view.View;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class FrontController {

    private final Map<String, Command> commands;
    private final View view;
    private final BufferedReader reader;

    @Inject
    public FrontController(
            @NotNull final ExitCommand exitCommand,
            @NotNull final BufferedReader reader,
            @NotNull final View view
    ) {
        this.reader = reader;
        this.view = view;

        commands = new HashMap<>() {{
            put("x", exitCommand.init());
        }};
    }

    public void addCommand(@NotNull final String key, @NotNull final Command command) {
        commands.put(key, command);
    }

    public boolean action(@NotNull final String text, @Nullable final SceneryCallback callback) throws Exception {
        view.render(text + setupCommands() + "\n");
        try {
            final var input = reader.readLine();
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
        for (final Map.Entry<String, Command> entry : commands.entrySet()) {
            text.append("[%s] %s%n".formatted(entry.getKey(), entry.getValue().getText()));
        }
        text.append("\n");

        return text;
    }
}
