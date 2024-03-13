package com.mygdx.game.Game.Place;

import java.util.ArrayList;

public class Position {
    protected int x, y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public ArrayList<Integer> getPosition (){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(x);
        list.add(y);
        return list;
    }

    public int getX() {return x;}

    public int getY() {return y;}

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public String toString(){
        return x + "," + y;
    }

    public double distanceToTarget(Position target){
        return Math.sqrt(Math.pow(x - target.x, 2) + Math.pow(y - target.y, 2));
    }

    public Position getDifference(Position target){
        return new Position(x - target.x, y - target.y);
    }

    public boolean equals(Position target){
        return x == target.x && y == target.y;
    }

}
