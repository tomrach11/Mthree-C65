package com.tr.goblin.model;

import java.util.Random;

public class Hero {
    private String name;
    private int maxHP;
    private int currentHP;
    private int atk;
    private int def;
//    private int gold;
//    private int[] potions = new int[5];

    public Hero(String name) {
        Random random = new Random();
        this.name = name;
        this.maxHP = random.nextInt(10 + 1) + 20;
        this.currentHP = this.maxHP;
        this.atk = random.nextInt(3 + 1) + 1;
        this.def = random.nextInt(5 + 1) + 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }
}
