package de.dungeon.game.character;

import de.dungeon.game.character.player.Weapon;
import de.dungeon.game.character.property.Dodge;
import de.dungeon.game.character.property.Magic;
import de.dungeon.game.character.property.Melee;
import de.dungeon.game.character.property.Range;

import java.util.List;

public class Player extends Character {

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

    public boolean tryToAttackWithWeapon(final Weapon weapon) {
        if (weapon.getTestProperty().isInstance(melee)) {
            return true;
        }
        if (weapon.getTestProperty().isInstance(range)) {
            return true;
        }
        return weapon.getTestProperty().isInstance(magic);
    }

    public Range getRange() {
        return range;
    }

    public Magic getMagic() {
        return magic;
    }

    public Weapon getWeapon(final int index) {
        return weaponList.get(index);
    }
}
