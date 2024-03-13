package com.mygdx.game.Game.Mage;

public class Witch extends Mage {
    public Witch(String name, int x, int y, int mana) {
        super(name, x, y, mana);
    }

    @Override
    public String getInfo() {
        return "Ведьмак";
    }
}

