package de.dungeon.game.command.factory;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import de.dungeon.game.character.Player;
import de.dungeon.game.character.enemy.Enemy;
import de.dungeon.game.character.property.Property;
import de.dungeon.game.command.*;
import de.dungeon.game.view.View;
import org.jetbrains.annotations.NotNull;

@Singleton
public class CommandFactory {

    private final Player player;
    private final Injector injector;

    @Inject
    public CommandFactory(@NotNull final Player player, @NotNull final Injector injector) {
        this.player = player;
        this.injector = injector;
    }

    public Command create(@NotNull final CommandMapper mapper) throws CommandException {
        return switch (mapper.getCommand()) {
            case "go" -> createGoCommand(mapper);
            case "attribute_test" -> createAttributeTestCommand(mapper);
            default -> throw new UnknownCommandException(mapper.getCommand());
        };
    }

    public Command create(@NotNull final CommandMapper mapper, @NotNull final Enemy enemy) throws CommandException {
        final var command = switch (mapper.getCommand()) {
            case "npc_status" -> injector.getInstance(EnemyStatusCommand.class);
            case "fight" -> injector.getInstance(FightCommand.class);
            default -> throw new UnknownCommandException(mapper.getCommand());
        };
        return command.setEnemy(enemy);
    }

    private Command createGoCommand(@NotNull final CommandMapper mapper) {
        return (new Command(injector.getInstance(View.class)) {
            @Override
            protected boolean doing() {
                return true;
            }
        }).init(
                mapper.getText().formatted(player.getName()),
                mapper.getDoText().formatted(player.getName())
        );
    }

    private Command createAttributeTestCommand(@NotNull final CommandMapper mapper) throws CommandException {
        final Property property = switch (mapper.getAttribute()) {
            case "melee" -> player.getMelee();
            case "range" -> player.getRange();
            case "magic" -> player.getMagic();
            case "dodge" -> player.getDodge();
            default -> throw new UnknownPropertyException(mapper.getAttribute());
        };

        return injector.getInstance(AttributeTestCommand.class).init(
                mapper.getText().formatted(player.getName()),
                mapper.getDoText().formatted(player.getName()),
                mapper.getModifier(),
                property
        );
    }
}
