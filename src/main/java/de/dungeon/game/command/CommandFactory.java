package de.dungeon.game.command;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.dungeon.game.character.Player;
import de.dungeon.game.character.enemy.Enemy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

@Singleton
public class CommandFactory {

    private final Player player;

    @Inject
    public CommandFactory(final Player player) {
        this.player = player;
    }

    public Command create(final Map<String, Object> commandData, final Enemy enemy) throws Exception {
        return new HashMap<String, Callable<Command>>() {{
            put("npc_status", () -> createEnemyStatusCommand(enemy));
            put("go", () -> createGoCommand(commandData));
            put("attribute_test", () -> null);
            put("fight", () -> null);
        }}.get(commandData.get("command")).call();
    }

    private Command createGoCommand(final Map<String, Object> commandData) {
        return new Command(
                ((String) commandData.get("text")).formatted(player.getName()),
                ((String) commandData.get("do_text")).formatted(player.getName())
        ) {

            @Override
            protected boolean doing() { return true; }
        };
    }

    private Command createEnemyStatusCommand(final Enemy enemy) {
        return new Command("w") {
            @Override
            protected boolean doing() {
                return false;
            }
        };
    }
}
