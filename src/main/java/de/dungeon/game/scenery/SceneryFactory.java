package de.dungeon.game.scenery;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.dungeon.game.FrontController;
import de.dungeon.game.character.Player;
import de.dungeon.game.character.enemy.EnemyFactory;
import de.dungeon.game.command.Command;
import de.dungeon.game.command.CommandFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class SceneryFactory {

    private final ObjectMapper mapper = new ObjectMapper();
    private final Player player;
    private final FrontController controller;
    private final EnemyFactory enemyFactory;
    private final CommandFactory commandFactory;
    private final Map<String, Command> commands;
    private final Map<String, Scenery> sceneries;

    @Inject
    public SceneryFactory(
            final Player player,
            final FrontController controller,
            final EnemyFactory enemyFactory,
            final CommandFactory commandFactory
    ) {
        this.player = player;
        this.controller = controller;
        this.enemyFactory = enemyFactory;
        this.commandFactory = commandFactory;
        commands = new HashMap<>();
        sceneries = new HashMap<>();
    }

    public Map<String, Scenery> init() throws Exception {
        final var dir = new File(getClass().getClassLoader().getResource("sceneries").getFile());
        File[] files = dir.listFiles();
        if (null != files) {
            for (File file : files) {
                create(file);
            }
        }
        return sceneries;
    }

    @SuppressWarnings (value="unchecked")
    private void create(final File file) throws Exception {
        final var data = mapper.readValue(file, HashMap.class);
        final var enemy = (data.containsKey("enemy") ? enemyFactory.create((String) data.get("enemy")) : null);

        final var text = (String) data.get("text");
        final var commands = new ArrayList<Command>();
        for (Map<String, Object> commandData : (List<Map>) data.get("commands")) {
            final var command = commandFactory.create(commandData, enemy);
            this.commands.put((String) commandData.get("key"), command);
            commands.add(command);
        }

        final var scenery = new Scenery(
                (String) data.get("key"),
                controller,
                text.formatted(player.getName()),
                commands,
                enemy
        );

        sceneries.put((String) data.get("key"), scenery);
    }
}
