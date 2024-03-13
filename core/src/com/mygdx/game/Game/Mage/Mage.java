package com.mygdx.game.Game.Mage;

import com.mygdx.game.Game.AbstractUnit.AbstractUnit;
import com.mygdx.game.Game.Place.Position;

import java.util.ArrayList;

abstract public class Mage extends AbstractUnit {
    protected int mana;
    protected int maxMana;
    protected boolean flag;

    public Mage(String name, int x, int y, int mana) {
        super(new Position(x,y), name, 2, "magic wand", 50, 1, 0, false);
        this.mana = mana;
        this.maxMana = this.mana = mana;
    }

    @Override
    public void getHit(int damage) {
        super.getHit(damage);
    }

    @Override
    public String toString() {
        return super.toString() + " | ☯ " + mana;
    }

    @Override
    public void step(ArrayList<AbstractUnit> enemy, ArrayList<AbstractUnit> friend) {
        if (getHp() <= 0) return;

        ArrayList<AbstractUnit> sortList = new ArrayList<>(friend);
        ArrayList<AbstractUnit> deadList = new ArrayList<>();
        sortList.sort((o1, o2) -> o1.getHp() - o2.getHp());

        int countAtRest = 0;

        for (AbstractUnit unit : sortList) {
            if (unit.getHp() == 0) {
                deadList.add(unit);
                countAtRest++;
            }
        }
        if (deadList.size() >= 3){
            flag = true;
            System.out.println("Погибло 3 воина! Готовлю заклинание 'воскресенья'!");
        }
        if (flag && mana == 10){
            deadList.sort((o1, o2) -> o2.getDamage() - o1.getDamage());
            deadList.get(0).getResurrection();
            mana = 0;
            System.out.println(name + " воскресил " + deadList.get(0).getName() + "!");
            flag = false;
            return;
        }

        if (flag || mana < 2) {
            mana++;
            return;
        }

        sortList.get(0).getHeal();
        mana -= 2;
    }
}
