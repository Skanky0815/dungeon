package de.dungeon.game.scenery;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.dungeon.game.FrontController;
import de.dungeon.game.character.Player;
import de.dungeon.game.character.enemy.Enemy;
import de.dungeon.game.character.enemy.EnemyFactory;
import de.dungeon.game.command.Command;
import de.dungeon.game.command.CommandFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.*;

@Singleton
public class SceneryFactory {

    private final ObjectMapper mapper = new ObjectMapper();
    private final Player player;
    private final FrontController controller;
    private final EnemyFactory enemyFactory;
    private final CommandFactory commandFactory;
    private final Map<String, Command> commands = new HashMap<>();
    private final Map<String, Scenery> sceneries = new HashMap<>();
    private final Map<String, Map> sceneriesData = new HashMap<>();

    @Inject
    public SceneryFactory(
            @NotNull final Player player,
            @NotNull final FrontController controller,
            @NotNull final EnemyFactory enemyFactory,
            @NotNull final CommandFactory commandFactory
    ) {
        this.player = player;
        this.controller = controller;
        this.enemyFactory = enemyFactory;
        this.commandFactory = commandFactory;
    }

    public Map<String, Scenery> init() throws Exception {
        final var files = loadFiles();
        for (File file : files) {
            create(file);
        }
        handFailureAndSuccess();

        return sceneries;
    }

    private File[] loadFiles() throws NoSceneriesException {
        final var dir = new File(getClass().getClassLoader().getResource("sceneries").getFile());
        final var files = dir.listFiles();
        if (null == files) throw new NoSceneriesException();
        return files;
    }

    @SuppressWarnings (value="unchecked")
    private void create(@NotNull final File file) throws Exception {
        final var data = mapper.readValue(file, HashMap.class);
        sceneriesData.put((String) data.get("key"), data);

        final var text = (String) data.get("text");

        Scenery scenery;
        if (data.containsKey("enemy")) {
            final var enemy = enemyFactory.create((String) data.get("enemy"));
            scenery = new Scenery(
                    (String) data.get("key"),
                    controller,
                    text.formatted(player.getName()),
                    setupCommands((List<Map<String, Object>>) data.get("commands"), enemy),
                    enemy
            );
        } else {
            scenery = new Scenery(
                    (String) data.get("key"),
                    controller,
                    text.formatted(player.getName()),
                    setupCommands((List<Map<String, Object>>) data.get("commands"))
            );
        }

        sceneries.put((String) data.get("key"), scenery);
    }

    private ArrayList<Command> setupCommands(
            @NotNull final List<Map<String, Object>> commandsData,
            @NotNull final Enemy enemy
    ) throws Exception {
        final var commands = new ArrayList<Command>();
        for (Map<String, Object> commandData : commandsData) {
            final var command = commandFactory.create(commandData, enemy);
            this.commands.put((String) commandData.get("key"), command);
            commands.add(command);
        }
        return commands;
    }

    private ArrayList<Command> setupCommands(@NotNull final List<Map<String, Object>> commandsData) throws Exception {
        final var commands = new ArrayList<Command>();
        for (Map<String, Object> commandData : commandsData) {
            final var command = commandFactory.create(commandData);
            this.commands.put((String) commandData.get("key"), command);
            commands.add(command);
        }
        return commands;
    }

    @SuppressWarnings(value = "unchecked")
    private void handFailureAndSuccess() {
        for (Map.Entry<String, ?> entry : sceneriesData.entrySet()) {
            final List<Map> commands = ((Map<String, List>) entry.getValue()).get("commands");
            for (var commandsData : commands) {
                handleSuccess((Map<String, Map<String, String>>) commandsData);
                handleFailure((Map<String, Map<String, String>>) commandsData);
            }
        }
    }

    private void handleSuccess(@NotNull final Map<String, Map<String, String>> commandData) {
        if (!commandData.containsKey("success")) return;

        final var success = (Map<String, String>) commandData.get("success");
        commands.get(commandData.get("key")).setSuccessAction(
                sceneries.get(success.get("action")),
                success.get("text").formatted(player.getName())
        );
    }

    private void handleFailure(@NotNull final Map<String, Map<String, String>> commandData) {
        if (!commandData.containsKey("failure")) return;

        final var failure = (Map<String, String>) commandData.get("failure");
        commands.get(commandData.get("key")).setFailureAction(
                sceneries.get(failure.get("action")),
                failure.get("text").formatted(player.getName())
        );
    }
}
