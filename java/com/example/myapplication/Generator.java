package com.example.myapplication;

import android.graphics.Color;

import java.util.ArrayList;

public class Generator {
    private int stage;
    private int level;

    public Generator(int stage, int level) {
        this.stage = stage;
        this.level = level;
    }

    public Unit enemyGenerator() {
        if (stage == 1 && level < 5) {
            ArrayList<Enemy> basEnemy = new ArrayList<>();
            basEnemy.add(new Enemy(50, 50, 15, 3, "New enemy 1", "1"));
            basEnemy.add(new Enemy(150, 150, 8, 1, "New enemy 2", "2"));
            basEnemy.add(new Enemy(15, 15, 35, 7, "New enemy 3", "3"));
            basEnemy.add(new Enemy(40, 40, 20, 10, "New enemy 4", "4"));
            basEnemy.add(new Enemy(30, 30, 25, 3, "New enemy 5", "5"));
            return basEnemy.get((int) (Math.random() * basEnemy.size()));
        } else if (stage == 1 && level >= 5) {
            ArrayList<Enemy> bossEnemy = new ArrayList<>();
            bossEnemy.add(new Enemy(250, 250, 15, 10, "New boss 1", "1.1"));
            bossEnemy.add(new Enemy(40, 40, 40, 7, "New boss 1", "1.2"));
            return bossEnemy.get((int) (Math.random() * bossEnemy.size()));
        }
        return null;
    }

    public Item itemForFightGenerator(ArrayList<Item> heroItems) {
        ArrayList<Item> commonItems = new ArrayList<>();
        commonItems.add(new Item(2, 0, 0, "C.1"));
        commonItems.add(new Item(0, 1, 0, "C.2"));
        commonItems.add(new Item(0, 0, 5, "C.3"));
        commonItems.add(new Item(1, 0, 3, "C.4"));
        commonItems.add(new Item(-1, 1, 2, "C.5"));
        commonItems.add(new Item(1, 1, -2, "C.6"));

        ArrayList<Item> rareItems = new ArrayList<>();
        rareItems.add(new Item(5, 0, 0, "R.1"));
        rareItems.add(new Item(0, 2, 4, "R.2"));
        rareItems.add(new Item(2, 1, 3, "R.3"));
        rareItems.add(new Item(5, 2, -7, "R.4"));

        ArrayList<Item> epicItems = new ArrayList<>();
        epicItems.add(new Item(-3, 5, 20, "E.1"));
        epicItems.add(new Item(12, -2, -10, "E.2"));

        ArrayList<Item> legendaryItems = new ArrayList<>();
        legendaryItems.add(new Item(7, 3, 10, "L.1"));

        Item goItem = null;

        while (goItem == null) {
            int randomItem = ((int) (Math.random() * 100));
            if (randomItem <= 49) {
                goItem = commonItems.get(((int) (Math.random() * commonItems.size())));
            } else if (randomItem <= 74) {
                goItem = rareItems.get(((int) (Math.random() * rareItems.size())));
            } else if (randomItem <= 89) {
                goItem = epicItems.get(((int) (Math.random() * epicItems.size())));
            } else {
                goItem = legendaryItems.get(((int) (Math.random() * legendaryItems.size())));
            }
            for (int i = 0; i < heroItems.size(); i++) {
                if (goItem.getModif().equals(heroItems.get(i).getModif())) {
                    goItem = null;
                    break;
                }
            }
        }

        return goItem;
    }

    public ArrayList<Item> itemForShopGenerator(ArrayList<Item> heroItems) {

        ArrayList<Item> commonItems = new ArrayList<>();
        commonItems.add(new Item(2, 0, 0, "C.1"));
        commonItems.add(new Item(0, 1, 0, "C.2"));
        commonItems.add(new Item(0, 0, 5, "C.3"));
        commonItems.add(new Item(1, 0, 3, "C.4"));
        commonItems.add(new Item(-1, 1, 2, "C.5"));
        commonItems.add(new Item(1, 1, -2, "C.6"));

        ArrayList<Item> rareItems = new ArrayList<>();
        rareItems.add(new Item(5, 0, 0, "R.1"));
        rareItems.add(new Item(0, 2, 4, "R.2"));
        rareItems.add(new Item(2, 1, 3, "R.3"));
        rareItems.add(new Item(5, 2, -7, "R.4"));

        ArrayList<Item> epicItems = new ArrayList<>();
        epicItems.add(new Item(-3, 5, 20, "E.1"));
        epicItems.add(new Item(12, -2, -10, "E.2"));

        ArrayList<Item> legendaryItems = new ArrayList<>();
        legendaryItems.add(new Item(7, 3, 10, "L.1"));

        ArrayList<Item> goInShop = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            int randomItem = ((int) (Math.random() * 100));
            if (randomItem <= 64) {
                goInShop.add(commonItems.get(((int) (Math.random() * commonItems.size()))));
            } else if (randomItem <= 84) {
                goInShop.add(rareItems.get(((int) (Math.random() * rareItems.size()))));
            } else if (randomItem <= 94) {
                goInShop.add(epicItems.get(((int) (Math.random() * epicItems.size()))));
            } else {
                goInShop.add(legendaryItems.get(((int) (Math.random() * legendaryItems.size()))));
            }
            Item isFree = goInShop.get(i);
            for (int i1 = 0; i1 < goInShop.size() - 1; i1++) {
                if (isFree.getModif().equals(goInShop.get(i1).getModif())) {
                    goInShop.remove(i);
                    i--;
                    break;
                }
                if (i1 + 1 == goInShop.size() - 1) {
                    for (int i2 = 0; i2 < heroItems.size(); i2++) {
                        if (isFree.getModif().equals(heroItems.get(i2).getModif())) {
                            goInShop.remove(i);
                            i--;
                            break;
                        }
                    }
                }
            }
        }
        return goInShop;
    }

    public String infoAboutItemInShop(Item item) {
        String inf = "";
        if (item.getAtk() != 0) {
            if (item.getAtk() > 0) {
                inf += "Атака: +" + item.getAtk();
                inf += "\n";
            } else {
                inf += "Атака: " + item.getAtk();
                inf += "\n";
            }
        }
        if (item.getDef() != 0) {
            if (item.getDef() > 0) {
                inf += " Защита: +" + item.getDef();
                inf += "\n";
            } else {
                inf += " Защита: " + item.getDef();
                inf += "\n";
            }
        }
        if (item.getMaxHp() != 0) {
            if (item.getMaxHp() > 0) {
                inf += " Макс HP: +" + item.getMaxHp();
                inf += "\n";
            } else {
                inf += " Макс HP: " + item.getMaxHp();
                inf += "\n";
            }
        }
        switch (item.getModif().split("\\.")[0]) {
            case "C":
                inf += ".Обычный";
                break;
            case "R":
                inf += ".Редкий";
                break;
            case "E":
                inf += ".Эпичный";
                break;
            default:
                inf += ".Легендарный";
                break;
        }
        return inf;
    }

    public int priceOfItemInStore(Item item) {
        int i;
        switch (item.getModif().split("\\.")[0]) {
            case "C":
                i = 15 * stage;
                break;
            case "R":
                i = 40 * stage;
                break;
            case "E":
                i = 70 * stage;
                break;
            default:
                i = 120 * stage;
                break;
        }
        return i;
    }

    public int colorOfItemRarity(Item item) {
        int green = Color.parseColor("#47A04A");
        int blue = Color.parseColor("#3F51B5");
        int purple = Color.parseColor("#9C27B0");
        int orange = Color.parseColor("#E25324");
        switch (item.getModif().split("\\.")[0]) {
            case "C":
                return green;
            case "R":
                return blue;
            case "E":
                return purple;
            default:
                return orange;
        }
    }

    public String skillLevel(int skillLevel, int maxSkillLevel) {
        return "Уровень навыка" + "\n" + skillLevel + "/" + maxSkillLevel;

    }
}

