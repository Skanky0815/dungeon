package de.dungeon.game.scenery.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import de.dungeon.game.character.Player;
import de.dungeon.game.character.enemy.Enemy;
import de.dungeon.game.character.enemy.EnemyFactory;
import de.dungeon.game.command.Command;
import de.dungeon.game.scenery.Scenery;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.*;

@Singleton
public class Factory {

    private final ObjectMapper mapper = new ObjectMapper();
    private final Injector injector;
    private final Player player;
    private final EnemyFactory enemyFactory;
    private final de.dungeon.game.command.factory.Factory commandFactory;
    private final Map<String, Command> commands = new HashMap<>();
    private final Map<String, Scenery> sceneries = new HashMap<>();
    private final Map<String, Map> sceneriesData = new HashMap<>();

    @Inject
    public Factory(
            @NotNull final Injector injector,
            @NotNull final Player player,
            @NotNull final EnemyFactory enemyFactory,
            @NotNull final de.dungeon.game.command.factory.Factory commandFactory
    ) {
        this.injector = injector;
        this.player = player;
        this.enemyFactory = enemyFactory;
        this.commandFactory = commandFactory;
    }

    public Map<String, Scenery> init() throws Exception {
        final var files = loadFiles();
        for (File file : files) {
            create(file);
        }
        loopOverSceneriesAndAddFailureAndSuccessActions();

        return sceneries;
    }

    private File[] loadFiles() throws NoSceneriesException {
        final var dir = new File(getClass().getClassLoader().getResource("sceneries").getFile());
        final var files = dir.listFiles();
        if (null == files) {
            throw new NoSceneriesException();
        }

        return files;
    }

    private void create(@NotNull final File file) throws Exception {
        final var data = mapper.readValue(file, HashMap.class);
        final var scenery = createScenery(injector.getInstance(Scenery.class), data);
        sceneries.put((String) data.get("key"), scenery);
    }

    @SuppressWarnings(value = "uncheced")
    private Scenery createScenery(@NotNull final Scenery scenery, @NotNull final Map data) throws Exception {
        final var text = ((String) data.get("text")).formatted(player.getName());
        final var key = (String) data.get("key");
        if (data.containsKey("enemy")) {
            final var enemy = enemyFactory.create((String) data.get("enemy"));
            return scenery.init(key, text, setupCommands((List) data.get("commands"), enemy), enemy);
        }
        return scenery.init(key, text, setupCommands((List) data.get("commands")));
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
    private void loopOverSceneriesAndAddFailureAndSuccessActions() {
        for (Map.Entry<String, ?> entry : sceneriesData.entrySet()) {
            final List<Map> commands = ((Map<String, List>) entry.getValue()).get("commands");
            for (var commandsData : commands) {
                handleFailureAndSuccess((Map<String, Map>) commandsData, "success", this::handSuccessAction);
                handleFailureAndSuccess((Map<String, Map>) commandsData, "failure", this::handFailureAction);
            }
        }
    }

    @SuppressWarnings(value = "unchecked")
    private void handleFailureAndSuccess(
            @NotNull final Map commandData,
            @NotNull final String actionType,
            @NotNull final LinkedSceneryCallback callback
    ) {
        if (!commandData.containsKey(actionType)) return;

        final var actionData = (Map<String, String>) commandData.get(actionType);
        final var command = commands.get(commandData.get("key"));
        final var linkedScenery = sceneries.get(actionData.get("action"));
        final var text = actionData.get("text").formatted(player.getName());
        callback.call(command, linkedScenery, text);
    }

    private void handSuccessAction(
            @NotNull final Command command,
            @NotNull final Scenery scenery,
            @NotNull final String text
    ) {
        command.setSuccessAction(scenery, text);
    }

    private void handFailureAction(
            @NotNull final Command command,
            @NotNull final Scenery scenery,
            @NotNull final String text
    ) {
        command.setFailureAction(scenery, text);
    }
}
