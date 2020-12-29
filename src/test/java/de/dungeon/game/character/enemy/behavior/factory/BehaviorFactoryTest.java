package de.dungeon.game.character.enemy.behavior.factory;

import com.google.inject.Injector;
import de.dungeon.game.character.enemy.behavior.Behavior;
import de.dungeon.game.character.enemy.behavior.DamageBehavior;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BehaviorFactoryTest {

    @Test
    void createShouldThrownAUnknownBehaviorTypeException() {
        assertThrows(UnknownBehaviorTypeException.class, () -> {
            final var mapper = new BehaviorMapper();
            mapper.setType("woops");
            final var typeMapper = mock(TypeMapper.class);
            when(typeMapper.createBehaviorByType(mapper)).thenThrow(UnknownBehaviorTypeException.class);

            new BehaviorFactory(mock(Injector.class), typeMapper).create(mapper);
        });
    }

    @Test
    void createShouldCreateABehavior() throws Exception {
        final var behavior = mock(DamageBehavior.class);
        final var injector = mock(Injector.class);
        when(injector.getInstance(Behavior.class)).thenReturn(behavior);

        final var mapper = new BehaviorMapper();
        mapper.setMin(1);
        mapper.setMax(5);
        mapper.setText("some text");

        assertEquals(behavior, new BehaviorFactory(injector, mock(TypeMapper.class)).create(mapper));
        verify(behavior).init(eq(mapper));
    }

    @Test
    void createShouldCreateADamageBehavior() throws Exception {
        final var behavior = mock(DamageBehavior.class);
        final var injector = mock(Injector.class);

        final var mapper = new BehaviorMapper();
        mapper.setType("damage");
        mapper.setMin(1);
        mapper.setMax(5);
        mapper.setText("some text");

        final var typeMapper = mock(TypeMapper.class);
        when(typeMapper.createBehaviorByType(mapper)).thenReturn(behavior);

        assertEquals(behavior, new BehaviorFactory(injector, typeMapper).create(mapper));
        verify(typeMapper).createBehaviorByType(eq(mapper));
    }
}