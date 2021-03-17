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

    public void makeDamage(final int damage, @NotNull final Character attacker, @NotNull final Character defender) {
        final var wounds = calculateWounds(damage, attacker, defender);
        defender.takeDamage(wounds);

        view.render("game.battle.do_damage", attacker.getName(), defender.getName(), wounds);
    }

    public int calculateWounds(int damage, @NotNull final Character attacker, @NotNull final Character defender) {
        if (defender.tryToDoge()) {
            damage = calculateWoundsAfterDoge(damage, defender);
        }

        return Math.max(damage - defender.getArmor(), 0);
    }

    private int calculateWoundsAfterDoge(int damage, @NotNull final Character defender) {
        view.render("game.battle.doge_successful", defender.getName());
        return (int) Math.ceil(damage / 2);
    }
}
