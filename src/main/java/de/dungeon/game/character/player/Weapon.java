package de.dungeon.game.character.player;

import de.dungeon.game.character.property.Property;
import de.dungeon.game.rule.Damage;
import de.dungeon.game.rule.UnknownDiceException;

public class Weapon {

    private final String name;
    private final Class<? extends Property> testProperty;
    private final Damage damage;

    public Weapon(final String name, final Damage damage, final Class<? extends Property> testProperty) {
        this.name = name;
        this.damage = damage;
        this.testProperty = testProperty;
    }

    public String getName() {
        return name;
    }

    public Class<? extends Property> getTestProperty() {
        return testProperty;
    }

    public int getDamage() throws UnknownDiceException {
        return damage.calculateDamage();
    }
}
