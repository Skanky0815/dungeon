package de.dungeon.game.character.enemy.factory;

import de.dungeon.game.character.enemy.EnemyProperty;
import de.dungeon.game.character.enemy.behavior.factory.BehaviorMapper;

import java.util.List;

public class EnemyMapper implements EnemyProperty {

    private String name;
    private int health;
    private int dodge;
    private int armor;
    private int actions;
    private List<BehaviorMapper> behaviors;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDodge() {
        return dodge;
    }

    public void setDodge(int dodge) {
        this.dodge = dodge;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getActions() {
        return actions;
    }

    public void setActions(int actions) {
        this.actions = actions;
    }

    public List<BehaviorMapper> getBehaviors() {
        return behaviors;
    }

    public void setBehaviors(List<BehaviorMapper> behaviors) {
        this.behaviors = behaviors;
    }

    public static EnemyMapper build(final String name, final int health, final int armor, final int actions) {
        final var mapper = new EnemyMapper();
        mapper.setName(name);
        mapper.setHealth(health);
        mapper.setArmor(armor);
        mapper.setActions(actions);
        return mapper;
    }
}
