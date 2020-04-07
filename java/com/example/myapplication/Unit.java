package com.example.myapplication;

import java.io.Serializable;

public class Unit implements Serializable {
    private int hp;
    private int maxHp;
    private int atk;
    private int def;
    private String name;

    public Unit(int hp,int maxHp, int atk, int def, String name) {
        this.hp = hp;
        this.maxHp = maxHp;
        this.atk = atk;
        this.def = def;
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    public String getName() {
        return name;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setMaxHp(int mHp) {
        this.maxHp = mHp;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public void setAll (Character hero) {
        this.hp = hero.getHp();
        this.maxHp = hero.getMaxHp();
        this.atk = hero.getAtk();
        this.def = hero.getDef();
    }

}
