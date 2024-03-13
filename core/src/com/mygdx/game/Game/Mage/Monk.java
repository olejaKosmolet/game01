package com.mygdx.game.Game.Mage;

public class Monk extends Mage {

    public Monk(String name, int x, int y, int mana) {
        super(name, x, y, mana);
    }
    @Override
    public String getInfo() {
        return "Монах";
    }
}
