package com.example.myapplication;

import java.io.Serializable;

public class Item implements Serializable {

    private int atk;
    private int def;
    private int maxHp;
    private String modif;

    public Item(int atk, int def, int maxHp, String modif) {
        this.atk = atk;
        this.def = def;
        this.maxHp = maxHp;
        this.modif = modif;
    }

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public String getModif() {
        return modif;
    }
}
