package com.tr.goblin.model;

import java.util.Random;

public class Goblin {
    private int maxHP;
    private int currentHP;
    private int atk;
    private int def;

    public Goblin() {
        Random random = new Random();
        this.maxHP = random.nextInt(5 + 1) + 5;
        this.currentHP = maxHP;
        this.atk = random.nextInt(2 + 1) + 1;
        this.def = random.nextInt(2 + 1) + 1;
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
