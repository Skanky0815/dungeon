package de.dungeon.game.command;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import de.dungeon.game.character.Player;
import de.dungeon.game.character.enemy.Enemy;
import de.dungeon.game.character.property.Property;

import java.util.Map;

@Singleton
public class CommandFactory {

    private final Player player;
    private final Injector injector;

    @Inject
    public CommandFactory(final Player player, final Injector injector) {
        this.player = player;
        this.injector = injector;
    }

    public Command create(final Map<String, Object> commandData, final Enemy enemy) throws CommandException {
        return switch ((String) commandData.get("command")) {
            case "npc_status" -> injector.getInstance(EnemyStatusCommand.class).init(enemy);
            case "go" -> createGoCommand(commandData);
            case "attribute_test" -> createAttributeTestCommand(commandData);
            case "fight" -> injector.getInstance(FightCommand.class).init(enemy);
            default -> throw new UnknownCommandException((String) commandData.get("command"));
        };
    }

    private Command createGoCommand(final Map<String, Object> commandData) {
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

    private Command createAttributeTestCommand(final Map<String, Object> commandData) throws CommandException {
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
