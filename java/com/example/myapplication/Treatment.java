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

        public Character setFightIndicator() {

            for (int i = 0; i < hero.getSkills().size(); i++) {
                Skill heroPasSkill = hero.getSkills().get(i);
                if (heroPasSkill.getSkill().split("\\.")[0].equals("1")) {
                    if (heroPasSkill.isFlag()) {
                        if (heroPasSkill.getSkill().split("\\.")[1].equals("phs_up")) {
                            if (heroPasSkill.getLevel() == 1) {
                                hero.setAtk((int) (hero.getAtk() * 1.1));
                            } else if (heroPasSkill.getLevel() == 2) {
                                hero.setAtk((int) (hero.getAtk() * 1.15));
                                hero.setMaxHp((int) (hero.getMaxHp() * 1.2));
                            } else {
                                hero.setAtk((int) (hero.getAtk() * 1.2));
                                hero.setMaxHp((int) (hero.getMaxHp() * 1.3));
                                hero.setDef((int) (hero.getDef() * 1.1));
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < heroItems.size(); i++) {
                hero.setAtk(hero.getAtk() + heroItems.get(i).getAtk());
                hero.setDef(hero.getDef() + heroItems.get(i).getDef());
                hero.setMaxHp(hero.getMaxHp() + heroItems.get(i).getMaxHp());
            }
            return hero;
        }

        public boolean skillIsReady(String mod) {
            boolean flag = false;
            for (int i = 0; i < hero.getSkills().size(); i++) {
                String eMod = hero.getSkills().get(i).getSkill();
                if (eMod.split("\\.")[1].equals(mod)) {
                    if (hero.getSkills().get(i).getNeedStep() == 0) {
                        flag = true;
                    }
                }
            }
            return flag;
        }

        public ArrayList<Skill> setSkillStep(String target) {
            ArrayList<Skill> skills = hero.getSkills();
            for (int i = 0; i < skills.size(); i++) {
                String info = skills.get(i).getSkill();
                if (!info.split("\\.")[0].equals("1")) {
                    if (info.split("\\.")[1].equals(target)) {
                        skills.get(i).setNeedStep(skills.get(i).getStep());
                    }
                }
            }
            return skills;
        }

        public ArrayList<Skill> skillsUpdateStep(String target) {
            ArrayList<Skill> skills = hero.getSkills();
            for (int i = 0; i < skills.size(); i++) {
                String info = skills.get(i).getSkill();
                if (!info.split("\\.")[0].equals("1")) {
                    if (!info.split("\\.")[1].equals(target)) {
                        if (skills.get(i).getNeedStep() > 0) {
                            skills.get(i).setNeedStep(skills.get(i).getNeedStep() - 1);
                        }
                    }
                }
            }
            return skills;
        }

        public ArrayList<Skill> setSkillsAfterFight() {
            ArrayList<Skill> skills = hero.getSkills();
            for (int i = 0; i < skills.size(); i++) {
                String info = skills.get(i).getSkill();
                if (!info.split("\\.")[0].equals("1")) {
                    skills.get(i).setNeedStep(0);
                }
            }
            return skills;
        }

        public int basAttack() {
            int dmg = hero.getAtk() - enemy.getDef();
            enemy.setHp(Math.max(enemy.getHp() - dmg, 0));
            return enemy.getHp();
        }

        public int secondAttack(ArrayList<Skill> skills) {
            int dmg;
            int skillLevel = 1;
            for (int i = 0; i < skills.size(); i++) {
                Skill skill = hero.getSkills().get(i);
                if (skill.getSkill().equals("2.skill_atk")) {
                    if (skill.getLevel() == 2) {
                        skillLevel = 2;
                    }
                    if (skill.getLevel() == 3) {
                        skillLevel = 3;
                    }
                }
            }
            if (skillLevel == 1) {
                dmg = (int) (hero.getAtk() - enemy.getDef() * 0.8);
                enemy.setHp(Math.max(enemy.getHp() - dmg, 0));
                return enemy.getHp();
            } else if (skillLevel == 2) {
                dmg = (int) (hero.getAtk() * 1.1 - enemy.getDef() * 0.7);
                enemy.setHp(Math.max(enemy.getHp() - dmg, 0));
                return enemy.getHp();
            } else {
                dmg = (int) (hero.getAtk() * 1.2  - enemy.getDef() * 0.6);
                enemy.setHp(Math.max(enemy.getHp() - dmg, 0));
                return enemy.getHp();
            }
        }

        public int shadowAttack(ArrayList<Skill> skills, Enemy enemy) {
            int dmg;
            int skillLevel = 1;
            for (int i = 0; i < skills.size(); i++) {
                Skill skill = hero.getSkills().get(i);
                if (skill.getSkill().equals("2.shadow_atk")) {
                    if (skill.getLevel() == 2) {
                        skillLevel = 2;
                    }
                    if (skill.getLevel() == 3) {
                        skillLevel = 3;
                    }
                    if (skill.getLevel() == 4) {
                        skillLevel = 4;
                    }
                }
            }
            if (skillLevel == 1) {
                dmg = (int) (enemy.getHp() * 0.1 - enemy.getDef());
                enemy.setHp(Math.max(enemy.getHp() - dmg, 0));
                return enemy.getHp();
            } else if (skillLevel == 2) {
                dmg = (int) (enemy.getHp() * 0.15 - enemy.getDef() * 0.5);
                enemy.setHp(Math.max(enemy.getHp() - dmg, 0));
                return enemy.getHp();
            } else if (skillLevel == 3) {
                dmg = (int) (enemy.getHp() * 0.2 + hero.getAtk() * 0.25 - enemy.getDef() * 0.5);
                enemy.setHp(Math.max(enemy.getHp() - dmg, 0));
                return enemy.getHp();
            } else {
                dmg = (int) (enemy.getHp() * 0.25 + hero.getAtk() * 0.25);
                enemy.setHp(Math.max(enemy.getHp() - dmg, 0));
                return enemy.getHp();
            }
        }
    }

}
