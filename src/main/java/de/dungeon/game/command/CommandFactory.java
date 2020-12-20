package de.dungeon.game.command;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.dungeon.game.character.Player;
import de.dungeon.game.character.enemy.Enemy;
import de.dungeon.game.character.property.Property;
import de.dungeon.game.view.EnemyStatusView;

import java.util.Map;

@Singleton
public class CommandFactory {

    private final Player player;

    @Inject
    public CommandFactory(final Player player) {
        this.player = player;
    }

    public Command create(final Map<String, Object> commandData, final Enemy enemy) {
        return switch ((String) commandData.get("command")) {
            case "npc_status" -> createEnemyStatusCommand(enemy);
            case "go" -> createGoCommand(commandData);
            case "attribute_test" -> createAttributeTestCommand(commandData);
            case "fight" -> createFightCommand(enemy);
            default -> null;
        };
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
        return new EnemyStatusCommand(enemy, new EnemyStatusView());
    }

    private Command createAttributeTestCommand(final Map<String, Object> commandData) {
        Property property = switch ((String) commandData.get("attribute")) {
            case "melee" -> player.getMelee();
            case "range" -> player.getRange();
            case "magic" -> player.getMagic();
            case "dodge" -> player.getDodge();
            default -> null;
        };

        return new AttributeTestCommand(
                ((String) commandData.get("text")).formatted(player.getName()),
                ((String) commandData.get("do_text")).formatted(player.getName()),
                (int) commandData.get("modifier"),
                property
        );
    }

    private Command createFightCommand(final Enemy enemy) {
        return new FightCommand(player, enemy);
    }
}
