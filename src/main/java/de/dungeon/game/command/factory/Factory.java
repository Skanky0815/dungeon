package de.dungeon.game.command.factory;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import de.dungeon.game.character.Player;
import de.dungeon.game.character.enemy.Enemy;
import de.dungeon.game.character.property.Property;
import de.dungeon.game.command.*;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

@Singleton
public class Factory {

    private final Player player;
    private final Injector injector;

    @Inject
    public Factory(@NotNull final Player player, @NotNull final Injector injector) {
        this.player = player;
        this.injector = injector;
    }

    public Command create(@NotNull final Map<String, Object> commandData) throws CommandException {
        return switch ((String) commandData.get("command")) {
            case "go" -> createGoCommand(commandData);
            case "attribute_test" -> createAttributeTestCommand(commandData);
            default -> throw new UnknownCommandException((String) commandData.get("command"));
        };
    }

    public Command create(
            @NotNull final Map<String, Object> commandData,
            @NotNull final Enemy enemy
    ) throws CommandException {
        final var command = switch ((String) commandData.get("command")) {
            case "npc_status" -> injector.getInstance(EnemyStatusCommand.class);
            case "fight" -> injector.getInstance(FightCommand.class);
            default -> throw new UnknownCommandException((String) commandData.get("command"));
        };
        return command.setEnemy(enemy);
    }

    private Command createGoCommand(@NotNull final Map<String, Object> commandData) {
        return (new Command() {
            @Override
            protected boolean doing() {
                return true;
            }
        }).init(
                ((String) commandData.get("text")).formatted(player.getName()),
                ((String) commandData.get("do_text")).formatted(player.getName())
        );
    }

    private Command createAttributeTestCommand(@NotNull final Map<String, Object> commandData) throws CommandException {
        final Property property = switch ((String) commandData.get("attribute")) {
            case "melee" -> player.getMelee();
            case "range" -> player.getRange();
            case "magic" -> player.getMagic();
            case "dodge" -> player.getDodge();
            default -> throw new UnknownPropertyException((String) commandData.get("attribute"));
        };

        return injector.getInstance(AttributeTestCommand.class).init(
                ((String) commandData.get("text")).formatted(player.getName()),
                ((String) commandData.get("do_text")).formatted(player.getName()),
                (int) commandData.get("modifier"),
                property
        );
    }
}
