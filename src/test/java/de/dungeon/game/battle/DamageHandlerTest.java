package de.dungeon.game.battle;

import de.dungeon.game.character.Character;
import de.dungeon.game.character.property.Dodge;
import de.dungeon.game.rule.TestResult;
import de.dungeon.game.view.View;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DamageHandlerTest {

    @Test
    void makeDamageShouldMake8DamageAtTheDefender() {
        final var dodgeMock = mock(Dodge.class);
        final var view = mock(View.class);
        when(dodgeMock.test()).thenReturn(new TestResult(0, 0) {
            @Override
            public boolean isSuccess() {
                return false;
            }
        });

        var attackerStub = new Character("char A", 20, 2, mock(Dodge.class));
        var defenderStub = new Character("char B", 20, 2, dodgeMock);

        new DamageHandler(view).makeDamage(10, attackerStub, defenderStub);

        assertEquals(12, defenderStub.getHealth());
    }

    @Test
    void makeDamageShouldMake3DamageAtTheDefender() {
        final var dodgeMock = mock(Dodge.class);
        final var view = mock(View.class);

        when(dodgeMock.isTestSuccessfully()).thenReturn(true);

        var attackerStub = new Character("char A", 20, 2, mock(Dodge.class));
        var defenderStub = new Character("char B", 20, 2, dodgeMock);

        new DamageHandler(view).makeDamage(10, attackerStub, defenderStub);

        assertEquals(17, defenderStub.getHealth());
    }
}