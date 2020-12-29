package de.dungeon.game.scenery.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import de.dungeon.game.character.Player;
import de.dungeon.game.character.enemy.Enemy;
import de.dungeon.game.character.enemy.factory.EnemyFactory;
import de.dungeon.game.command.Command;
import de.dungeon.game.command.factory.CommandFactory;
import de.dungeon.game.command.factory.CommandMapper;
import de.dungeon.game.scenery.Scenery;
import org.jetbrains.annotations.*;

import java.io.File;
import java.util.*;

@Singleton
public class SceneryFactory {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Injector injector;
    private final Player player;
    private final EnemyFactory enemyFactory;
    private final CommandFactory commandFactory;
    private final Map<String, Command> commands = new HashMap<>();
    private final Map<String, Scenery> sceneries = new HashMap<>();
    private final Map<String, SceneryMapper> sceneriesData = new HashMap<>();

    @Inject
    public SceneryFactory(
            @NotNull final Injector injector,
            @NotNull final Player player,
            @NotNull final EnemyFactory enemyFactory,
            @NotNull final CommandFactory commandFactory
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

    private File[] loadFiles() throws Exception {
        final var dir = new File(getClass().getClassLoader().getResource("sceneries").getFile());
        final var files = dir.listFiles();
        if (null == files) {
            throw new NoSceneriesException();
        }

        return files;
    }

    private void create(@NotNull final File file) throws Exception {
        final var mapper = objectMapper.readValue(file, SceneryMapper.class);
        sceneriesData.put(mapper.getKey(), mapper);
        sceneries.put(mapper.getKey(), createScenery(injector.getInstance(Scenery.class), mapper));
    }

    private Scenery createScenery(@NotNull final Scenery scenery, @NotNull final SceneryMapper mapper) throws Exception {
        final var text = mapper.getText().formatted(player.getName());
        final var key = mapper.getKey();
        if (mapper.hasEnemy()) {
            final var enemy = enemyFactory.create(mapper.getEnemy());
            return scenery.init(key, text, setupCommands(mapper.getCommands(), enemy), enemy);
        }
        return scenery.init(key, text, setupCommands(mapper.getCommands()));
    }

    private ArrayList<Command> setupCommands(
            @NotNull final List<CommandMapper> commandMapperList,
            @NotNull final Enemy enemy
    ) throws Exception {
        final var commands = new ArrayList<Command>();
        for (var mapper : commandMapperList) {
            final var command = commandFactory.create(mapper, enemy);
            this.commands.put(mapper.getKey(), command);
            commands.add(command);
        }
        return commands;
    }

    private ArrayList<Command> setupCommands(@NotNull final List<CommandMapper> commandMapperList) throws Exception {
        final var commands = new ArrayList<Command>();
        for (var mapper : commandMapperList) {
            final var command = commandFactory.create(mapper);
            this.commands.put(mapper.getKey(), command);
            commands.add(command);
        }
        return commands;
    }

    private void loopOverSceneriesAndAddFailureAndSuccessActions() {
        for (Map.Entry<String, SceneryMapper> entry : sceneriesData.entrySet()) {
            final var commands = entry.getValue().getCommands();
            for (var mapper : commands) {
                handleLinkedScenery(mapper.getSuccess(), mapper.getKey(), this::handSuccessAction);
                handleLinkedScenery(mapper.getFailure(), mapper.getKey(), this::handFailureAction);
            }
        }
    }

    private void handleLinkedScenery(
            @Nullable final SceneryLinkMapper mapper,
            @NotNull final String key,
            @NotNull final LinkedSceneryCallback callback
    ) {
        if (null == mapper) {
            return;
        }

        final var linkedScenery = sceneries.get(mapper.getAction());
        final var text = mapper.getText().formatted(player.getName());
        callback.call(commands.get(key), linkedScenery, text);
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
