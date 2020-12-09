package com.tr.goblin.view;

import com.tr.goblin.model.Goblin;
import com.tr.goblin.model.Hero;

import java.util.Scanner;

public class View {
    Scanner sc = new Scanner(System.in);

    public String getUserInput(String question) {
        System.out.print(question);
        return sc.nextLine();
    }

    public void displayHeroStats(Hero hero) {
        System.out.println("Hero Stats ");
        System.out.println("===================");
        System.out.println("Hero Name: " + hero.getName());
        System.out.println("HP: " + hero.getCurrentHP() + "/" + hero.getMaxHP());
        System.out.println("ATK Power: " + hero.getAtk());
        System.out.println("DEF Power: " + hero.getDef());
    }

    public void displayGoblinStats(Goblin goblin) {
        System.out.println("You have found a goblin !!");
        System.out.println("Goblin Status ");
        System.out.println("===================");
        System.out.println("HP: " + goblin.getCurrentHP() + "/" + goblin.getMaxHP());
        System.out.println("ATK Power: " + goblin.getAtk());
        System.out.println("DEF Power: " + goblin.getDef());
    }

    public void displayCurrentStep(int step) {
        System.out.println("Step: " + step);
        System.out.println("===================");
    }

    public void println(String text) {
        System.out.println(text);
    }

    public void displayResult(int step, int level) {
        System.out.println("\n===================");
        System.out.println("You have died...");
        System.out.println("You have gone through " + step + " steps");
        System.out.println("and have reached Level: " + level);
    }
}
