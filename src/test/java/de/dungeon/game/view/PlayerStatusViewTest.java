package de.dungeon.game.view;

import de.dungeon.game.character.Player;
import de.dungeon.game.character.property.Dodge;
import de.dungeon.game.character.property.Magic;
import de.dungeon.game.character.property.Melee;
import de.dungeon.game.character.property.Range;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerStatusViewTest extends ViewTestCase {

    @Test
    void renderShouldPrintThePlayerStatsIntoTheCommandLine() {
        final var player = new Player(
                "Ruhindil",
                new Melee(12, 0),
                new Range(6, 2),
                new Magic(0, 0),
                new Dodge(5, 0),
                0
        );

        final var view = new PlayerStatusView();

        view.setPlayer(player);
        view.render();

        final var output = """
            Name: Ruhindil
            Leben: 40/40
            ---------------------------------------
            Nahkampf: 12\tFernkampf: 6 (+2)\tMagie: 0
            Ausweichen: 5\tRüstung: 0
            ---------------------------------------
            """.replaceAll("\\n|\\r\\n", System.getProperty("line.separator")).trim();

        assertEquals(output, outContent.toString().trim());
    }
}

