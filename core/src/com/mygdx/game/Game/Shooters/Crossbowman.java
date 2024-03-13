package com.mygdx.game.Game.Shooters;

public class Crossbowman extends Shooters {
    public Crossbowman(String name, int x, int y, int numberOfShells) {
        super(name, x, y, numberOfShells);
    }

    @Override
    public String getInfo() {
        return "Арбалетчик";
    }
}