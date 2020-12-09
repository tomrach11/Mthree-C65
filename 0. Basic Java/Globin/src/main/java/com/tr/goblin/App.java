package com.tr.goblin;

import com.tr.goblin.model.Goblin;
import com.tr.goblin.model.Hero;
import com.tr.goblin.view.View;

import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        View view = new View();
        Scanner sc = new Scanner(System.in);
        int gold = 0;
        int potions = 5;
        int step = 1;
        int level = 1;

        //create new hero
        Hero hero = new Hero(view.getUserInput("Please enter you Hero name: "));
        view.displayHeroStats(hero);

        while(hero.getCurrentHP() > 0) {
            view.getUserInput("press any key to take another step\n");
            view.displayCurrentStep(step);
            Random random = new Random();
            //start at 10% chance to find goblin then increase every 10 percent for each level
            if (random.nextInt(11) <= level) {
                //found goblin

                //create goblin
                Goblin goblin = new Goblin();
                view.displayGoblinStats(goblin);

                //if hero or goblin still arrive keep going
                while(goblin.getCurrentHP() > 0 && hero.getCurrentHP() > 0) {
                    view.getUserInput("\nPress any key to attack the goblin.");
                    int goblinDef = random.nextInt(goblin.getDef() + 1); //random generate goblin defence

                    //hero attack goblin
                    if (goblinDef >= hero.getAtk()) {
                        //fail to attack
                        view.println(">>> Goblin defence your attack successfully!");
                    } else {
                        //success to attack
                        int atkPower = hero.getAtk() - goblinDef; //how much to atk goblin
                        goblin.setCurrentHP(goblin.getCurrentHP() - atkPower); // reduce goblin health
                        view.println(">>> You successfully attack the goblin by " + atkPower);
                        view.println("Goblin Health: " + goblin.getCurrentHP() + "/" + goblin.getMaxHP());
                    }

                    //if goblin not dead
                    if (goblin.getCurrentHP() > 0) {
                        int heroDef = random.nextInt(hero.getDef() + 1); //random generate hero defence
                        view.println("\nGoblin attack back !!!");
                        view.getUserInput("Press any key to try block the attack!");
                        //goblin attack
                        if (heroDef >= goblin.getAtk()) {
                            //goblin fail to attack
                            view.println(">>> You successfully block goblin attack!");
                        } else {
                            //goblin attack success
                            int atkPower = goblin.getAtk() - heroDef; //how much to damage hero
                            hero.setCurrentHP(hero.getCurrentHP() - atkPower); // reduce hero current health
                            view.println(">>> You fail to block goblin attack and damaged by " + atkPower);
                            view.println("Hero Health: " + hero.getCurrentHP() + "/" + hero.getMaxHP());
                        }
                    } else {
                     //if goblin dead give gold to player
                        view.println("Nice job! You slayed the goblin.");
                        gold += 2;
                    }
                }
            } else {
                //drink potion
                view.println("You are safe!! No goblin found.");
                boolean keepGoing = true;
                while (keepGoing) {
                    String input = view.getUserInput("Press [Y] to drink potion || [Enter] to continue.");
                    //want to use potion?
                    if (input.equals("Y") || input.equals("y")) {
                        //check for available potion
                        if (potions < 1) {
                            //no more potion
                            view.println("You don't have any potion left. Lets keep going.");
                            keepGoing = false;
                        } else {
                            //do have potion
                            potions--;
                            //check full health
                            if (hero.getCurrentHP() + 2 > hero.getMaxHP()) {
                                //exceed full health use maxHP
                                hero.setCurrentHP(hero.getMaxHP());
                            } else {
                                //less than maxHP add 2 to currentHP
                                hero.setCurrentHP(hero.getCurrentHP() + 2);
                            }
                        }
                    } else {
                        view.println("Moving forward...");
                        keepGoing = false;
                    }
                }
            }

            step++;

            if (step % 10 == 0) {
                //buy potion
                view.println("\nGreat Job! You survive level " + level);
                view.println("Welcome to potion shop, please take a look around.");
                boolean keepGoing = true;
                while (keepGoing) {
                    String input = view.getUserInput("Would you like to buy a potion for 4 golds? (press [Y] to buy)");
                    if (input.equals("Y") || input.equals("y")) {
                        if(gold < 4) {
                            view.println("Hey!! you don't have enough money. Get out of my shop!!");
                            keepGoing = false;
                        } else {
                            view.println("Alright! here is your potion and I will take your gold.");
                            potions++;
                            gold -= 4;
                            view.println("Your Bag: " + potions + " potion(s) / " + gold + "Gold(s)");
                        }
                    } else {
                        view.println("No problem! You know where to find me when you need potion.");
                        keepGoing = false;
                    }
                }
                level++;
                step++;
            }
        }
        view.displayResult(step, level);
    }
}
