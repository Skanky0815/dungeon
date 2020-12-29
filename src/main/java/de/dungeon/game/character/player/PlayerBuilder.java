package de.dungeon.game.character.player;

import de.dungeon.game.character.Player;
import de.dungeon.game.character.enemy.behavior.factory.DamageMapper;
import de.dungeon.game.character.property.Dodge;
import de.dungeon.game.character.property.Magic;
import de.dungeon.game.character.property.Melee;
import de.dungeon.game.character.property.Range;
import de.dungeon.game.rule.Damage;
import de.dungeon.game.rule.Dice;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayerBuilder {

    private final String name;
    private final Melee melee;
    private final Range range;
    private final Magic magic;
    private final Dodge dodge;
    private final int armor;
    private final List<Weapon> weaponList;

    // TODO: refactor this crap ore replace with some other nicer class stuff
    private PlayerBuilder(
            @NotNull final String name,
            final int melee,
            final int range,
            final int magic,
            final int dodge
    ) {
        final var dice = new Dice(new Random());

        this.name = name;
        this.melee = (Melee) new Melee(dice).init(melee);
        this.range = (Range) new Range(dice).init(range);
        this.magic = (Magic) new Magic(dice).init(magic);
        this.dodge = (Dodge) new Dodge(dice).init(dodge);
        this.armor = 0;
        this.weaponList = new ArrayList<>() {{
            add(new Weapon("Axt", new Damage(dice).init(DamageMapper.build(1, 6, 0)), Melee.class));
        }};
    }

    public static PlayerBuilder build(
            @NotNull final String name,
            final int melee,
            final int range,
            final int magic,
            final int dodge
    ) {
        return new PlayerBuilder(name, melee, range, magic, dodge);
    }

    public PlayerBuilder withRangeModifier(final int modifier) {
        this.range.setModifier(modifier);
        return this;
    }

    public Player get() {
        return new Player(name, melee, range, magic, dodge, armor, weaponList);
    }
}
