package de.dungeon.game.battle;

import de.dungeon.game.character.Character;

public class DamageHandler {

    public void makeDamage(int damage, final Character attacker, final Character defender) {
        if (defender.tryToDoge()) {
            damage = (int) Math.ceil(damage / 2);
        }

        var wounds = Math.max(damage - defender.getArmor(), 0);
        defender.takeDamage(wounds);

        System.out.printf("%s verursacht bei %s %d Schaden.\n", attacker.getName(), defender.getName(), wounds);
    }
}
