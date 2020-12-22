package de.dungeon.game.battle;

import de.dungeon.game.character.Character;
import de.dungeon.game.character.property.Dodge;
import de.dungeon.game.rule.TestResult;
import de.dungeon.game.view.ViewTestCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DamageHandlerTest extends ViewTestCase {

    @Test
    void makeDamageShouldMake8DamageAtTheDefender() {
        var dodgeMock = mock(Dodge.class);
        when(dodgeMock.test()).thenReturn(new TestResult(0, 0) {
            @Override
            public boolean isSuccess() {
                return false;
            }
        });

        var attackerStub = new Character("char A", 20, 2, new Dodge(5));
        var defenderStub = new Character("char B", 20, 2, dodgeMock);


        var handler = new DamageHandler();
        handler.makeDamage(10, attackerStub, defenderStub);

        assertEquals(12, defenderStub.getHealth());
    }

    @Test
    void makeDamageShouldMake3DamageAtTheDefender() {
        var dodgeMock = mock(Dodge.class);
        when(dodgeMock.isTheTestSuccessfully()).thenReturn(true);

        var attackerStub = new Character("char A", 20, 2, new Dodge(5));
        var defenderStub = new Character("char B", 20, 2, dodgeMock);


        var handler = new DamageHandler();
        handler.makeDamage(10, attackerStub, defenderStub);

        assertEquals(17, defenderStub.getHealth());
    }
}