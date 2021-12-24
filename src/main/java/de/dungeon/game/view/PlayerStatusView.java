package de.dungeon.game.view;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.dungeon.game.Text;
import de.dungeon.game.character.Player;
import org.jetbrains.annotations.NotNull;

@Singleton
public class PlayerStatusView extends View {

    private final Player player;

    @Inject
    public PlayerStatusView(@NotNull final Player player, @NotNull final Text text) {
        super(text);
        this.player = player;
    }

    @Override
    public void render() {
        final var text = """
            Name: %s
            Leben: %d/40
            ---------------------------------------
            Nahkampf: %s\tFernkampf: %s\tMagie: %s
            Ausweichen: %s\tRüstung: %d
            ---------------------------------------
            """.formatted(
                    player.getName(),
                    player.getHealth(),
                    player.getMelee(),
                    player.getRange(),
                    player.getMagic(),
                    player.getDodge(),
                    player.getArmor()
            );

        render(text);
    }
}
