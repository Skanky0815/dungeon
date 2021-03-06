package de.dungeon.game.character;

import de.dungeon.game.character.property.Dodge;
import org.jetbrains.annotations.NotNull;

public class Character {

    private final String name;
    private final int maxHealth;
    private int health;
    private final int armor;
    private final Dodge dodge;

    public Character(@NotNull final String name, final int health, final int armor, @NotNull final Dodge dodge) {
        this.name = name;
        this.maxHealth = this.health = health;
        this.armor = armor;
        this.dodge = dodge;
    }

    public String getName() {
        return name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void takeDamage(final int damageValue) {
        health -= damageValue;
    }

    public int getHealth() {
        return health;
    }

    public int getArmor() {
        return armor;
    }

    public Dodge getDodge() {
        return dodge;
    }

    public boolean tryToDoge() {
        return dodge.isTestSuccessfully();
    }

    public boolean isAlive() {
        return health > 0;
    }
}
