package com.example.myapplication;

public class Enemy extends Unit {

    private String modif;
    public Enemy(int hp, int maxHp, int atk, int def, String name, String modif) {
        super(hp, maxHp, atk, def, name);
        this.modif = modif;
    }

    public String getModif() {
        return modif;
    }

    public void setModif(String modif) {
        this.modif = modif;
    }
}
