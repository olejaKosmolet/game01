package com.mygdx.game.Game.Warriors;

import com.mygdx.game.Game.AbstractUnit.AbstractUnit;
import com.mygdx.game.Game.Place.Position;

import java.util.ArrayList;

abstract public class Warriors extends AbstractUnit {
    public Warriors(String name, int x, int y) {
        super(new Position(x, y), name, 15, "spear", 75, 2, 10, false);
    }

    @Override
    public void getHit(int damage) {
        super.getHit(damage);
    }

    @Override
    public void step(ArrayList<AbstractUnit> enemy, ArrayList<AbstractUnit> friend) {
        if (getHp()<=0) return;

        AbstractUnit target = super.searchForEnemy(enemy);
        if (target == null){
            return;
        }

        if (position.distanceToTarget(target.position) < 2){
            target.getHit(this.damage);
            return;
        }
        Position diff = this.position.getDifference(target.position);
       /* Position newposition = new Position(position.x, position.y);*/

        if (Math.abs(diff.getX()) > Math.abs(diff.getY())){
            position.setX(position.getX() + (diff.getX() < 0 ? 1 : -1));
        } else position.setY(position.getY() + (diff.getY() < 0 ? 1 : -1));

       /* for (AbstractUnit unit : friend) {
            if (unit.position.equals(newposition) && unit.getHp() >= 1) return;

        }
        this.position = newposition;
*/
    }
}
