package Character;

import Character.Property.Dodge;
import Character.Property.Magic;
import Character.Property.Melee;
import Character.Property.Range;

final public class Player extends Character {

    final private Melee melee;
    final private Range range;
    final private Magic magic;

    public Player(String name, Melee melee, Range range, Magic magic, Dodge dodge, int armor) {
        super(name, 40, armor, dodge);
        this.melee = melee;
        this.range = range;
        this.magic = magic;
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
