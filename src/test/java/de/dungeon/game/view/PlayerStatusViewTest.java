package de.dungeon.game.view;

import de.dungeon.game.Text;
import de.dungeon.game.character.player.PlayerBuilder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PlayerStatusViewTest extends ViewTestCase {

    @Test
    void renderShouldPrintThePlayerStatsIntoTheCommandLine() {
        final var player = PlayerBuilder.build("Ruhindil", 12, 6, 0, 5)
                .withRangeModifier(2)
                .get();

        new PlayerStatusView(player, mock(Text.class)).render();

        final var output = """
            Name: Ruhindil
            Leben: 40/40
            ---------------------------------------
            Nahkampf: 12\tFernkampf: 6 (+2)\tMagie: 0
            Ausweichen: 5\tRüstung: 0
            ---------------------------------------
            """;

        assertEquals(output, outContent.toString());
    }
}

