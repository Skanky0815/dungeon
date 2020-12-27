package de.dungeon.game.view;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.dungeon.game.character.Player;
import org.jetbrains.annotations.NotNull;

@Singleton
public class PlayerStatusView implements View {

    private final Player player;

    @Inject
    public PlayerStatusView(@NotNull final Player player) {
        this.player = player;
    }

    public void render() {
        final var status = """
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

        System.out.print(status);
    }
}
