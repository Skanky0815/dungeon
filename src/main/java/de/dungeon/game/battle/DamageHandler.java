package de.dungeon.game.battle;

import com.google.inject.Inject;
import de.dungeon.game.character.Character;
import de.dungeon.game.view.View;
import org.jetbrains.annotations.NotNull;

public class DamageHandler {

    private final View view;

    @Inject
    public DamageHandler(@NotNull final View view) {
        this.view = view;
    }

    public void makeDamage(int damage, @NotNull final Character attacker, @NotNull final Character defender) {
        if (defender.tryToDoge()) {
            damage = (int) Math.ceil(damage / 2);
        }

        var wounds = Math.max(damage - defender.getArmor(), 0);
        defender.takeDamage(wounds);

        view.render("game.battle.do_damage", attacker.getName(), defender.getName(), wounds);
    }
}
