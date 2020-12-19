package de.dungeon.game.character;

import de.dungeon.game.character.property.Dodge;
import de.dungeon.game.character.property.Magic;
import de.dungeon.game.character.property.Melee;
import de.dungeon.game.character.property.Range;

public final class Player extends Character {

    private final Melee melee;
    private final Range range;
    private final Magic magic;

    public Player(String name, Melee melee, Range range, Magic magic, Dodge dodge, int armor) {
        super(name, 40, armor, dodge);
        this.melee = melee;
        this.range = range;
        this.magic = magic;

        // TODO: add skills
    }

    public Melee getMelee() {
        return melee;
    }

    public Range getRange() {
        return range;
    }

    public Magic getMagic() {
        return magic;
    }
}
