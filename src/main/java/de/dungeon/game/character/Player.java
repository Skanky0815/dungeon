package de.dungeon.game.character;

import de.dungeon.game.character.player.Weapon;
import de.dungeon.game.character.property.Dodge;
import de.dungeon.game.character.property.Magic;
import de.dungeon.game.character.property.Melee;
import de.dungeon.game.character.property.Range;

import java.util.ArrayList;
import java.util.List;

public final class Player extends Character {

    private final Melee melee;
    private final Range range;
    private final Magic magic;

    private final List<Weapon> weaponList;

    public Player(
            final String name,
            final Melee melee,
            final Range range,
            final Magic magic,
            final Dodge dodge,
            final int armor,
            final List<Weapon> weaponList
    ) {
        super(name, 40, armor, dodge);
        this.melee = melee;
        this.range = range;
        this.magic = magic;

        this.weaponList = weaponList;
    }

    public Melee getMelee() {
        return melee;
    }

    public boolean tryToDoAMeleeAttack() {
        return melee.test().isSuccess();
    }

    public Range getRange() {
        return range;
    }

    public Magic getMagic() {
        return magic;
    }

    public List<Weapon> getWeaponList() {
        return weaponList;
    }
}
