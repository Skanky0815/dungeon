package de.dungeon.game.character;

import de.dungeon.game.character.property.Dodge;

public class Character {

    final private String name;
    final private int maxHealth;
    private int health;
    final private int armor;
    final private Dodge dodge;

    public Character(final String name, final int health, final int armor, final Dodge dodge) {
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
}
