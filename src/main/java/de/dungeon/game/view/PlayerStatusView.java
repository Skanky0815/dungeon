package de.dungeon.game.view;

import de.dungeon.game.character.Player;

public class PlayerStatusView implements View {

    private Player player;

    public void setPlayer(final Player player) {
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

        System.out.println(status);
    }
}
