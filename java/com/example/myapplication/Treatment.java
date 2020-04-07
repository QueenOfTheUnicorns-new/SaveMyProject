package com.example.myapplication;

import java.util.ArrayList;

public class Treatment {
    static public Character battleWin(Character hero, int stage, int level) {

        if (level != 5) {
            hero.setExp(hero.getExp() + 5 * stage + level);
        } else {
            hero.setExp(hero.getExp() + 10 * stage + level);
        }

        if (level != 5) {
            hero.setMoney(hero.getMoney() + 5);
        } else {
            hero.setMoney(hero.getMoney() + 10);
        }

        while (true) {
            int needExp = 10;
            for (int i = 0; i < hero.getLvl(); i++) {
                needExp += needExp * 0.3;
            }
            if (hero.getExp() >= needExp) {
                hero.setExp(hero.getExp() - needExp);
                hero.setLvl(hero.getLvl() + 1);
                hero.setMaxHp((int) (hero.getMaxHp() + hero.getMaxHp() * 0.2));
                if (hero.getHp() + hero.getMaxHp() * 0.25 < hero.getMaxHp()) {
                    hero.setHp((int) (hero.getHp() + hero.getMaxHp() * 0.25));
                } else {
                    hero.setHp(hero.getMaxHp());
                }
                hero.setAtk((int) (hero.getAtk() + hero.getAtk() * 0.15));
                hero.setDef((int) (hero.getDef() + hero.getDef() * 0.1));
                hero.setImpPoint(hero.getImpPoint() + 1);
            } else {
                break;
            }
        }

        MainActivity.setLevel(MainActivity.getLevel() + 1);
        return hero;
    }

    static class Fight {
        Character hero;
        ArrayList<Item> heroItems;
        Enemy enemy;

        public Fight(Character hero, ArrayList<Item> heroItems, Enemy enemy) {
            this.hero = hero;
            this.heroItems = heroItems;
            this.enemy = enemy;
        }

        public Character setFightIndicator () {
            for (int i = 0; i < heroItems.size(); i++) {
                hero.setAtk(hero.getAtk() + heroItems.get(i).getAtk());
                hero.setDef(hero.getDef() + heroItems.get(i).getDef());
                hero.setMaxHp(hero.getMaxHp() + heroItems.get(i).getMaxHp());
            }
            return hero;
        }

        public int basAttack () {
            int dmg = hero.getAtk() - enemy.getDef();
            if (enemy.getHp() - dmg > 0) {
                enemy.setHp(enemy.getHp() - dmg);
            } else {
                enemy.setHp(0);
            }
            return enemy.getHp();
        }
    }

}
