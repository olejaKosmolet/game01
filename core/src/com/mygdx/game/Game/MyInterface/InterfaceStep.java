package com.mygdx.game.Game.MyInterface;

import com.mygdx.game.Game.AbstractUnit.AbstractUnit;

import java.util.ArrayList;

public interface InterfaceStep {
    void step(ArrayList<AbstractUnit> teamEnemy, ArrayList<AbstractUnit> teamFriend);
}
