package com.example.myapplication;

import java.io.Serializable;
import java.util.ArrayList;

public class Character extends Unit implements Serializable {

    private int exp;
    private int money;
    private int lvl;
    private int impPoint;
    private ArrayList<Item> heroItems;
    private ArrayList<Skill> skills;

    public Character(int hp, int maxHp, int atk, int def, String name, int exp,
                     int money, int lvl, int impPoint, ArrayList<Item> heroItems, ArrayList<Skill> skills) {
        super(hp, maxHp, atk, def, name);
        this.exp = exp;
        this.money = money;
        this.lvl = lvl;
        this.impPoint = impPoint;
        this.heroItems = heroItems;
        this.skills = skills;
    }

    public int getExp() {
        return exp;
    }

    public int getMoney() {
        return money;
    }

    public int getLvl() {
        return lvl;
    }

    public int getImpPoint() {
        return impPoint;
    }

    public ArrayList<Item> getHeroItems() {
        return heroItems;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public void setImpPoint(int impPoint) {
        this.impPoint = impPoint;
    }

    public void setHeroItems(ArrayList<Item> heroItems) {
        this.heroItems = heroItems;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public void setAll(Character hero) {
        super.setAll(hero);
        this.money = hero.getMoney();
        this.exp = hero.getExp();
        this.lvl = hero.getLvl();
        this.impPoint = hero.getImpPoint();
        this.skills = hero.getSkills();
    }


}
