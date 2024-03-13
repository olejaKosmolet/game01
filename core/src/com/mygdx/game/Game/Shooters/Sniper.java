package com.mygdx.game.Game.Shooters;

public class Sniper extends Shooters {
    public Sniper(String name, int x, int y, int numberOfShells) {
        super(name, x, y, numberOfShells);
    }

    @Override
    public String getInfo() {
        return "Снайпер";
    }
}