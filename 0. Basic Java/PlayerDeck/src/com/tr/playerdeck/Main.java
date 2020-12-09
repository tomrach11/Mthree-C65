package com.tr.playerdeck;

import com.tr.playerdeck.model.Card;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int deckSize = 10;
        Card[] deck = createDeckOfCards(deckSize);

        boolean playAgain = true;

        while (playAgain) {
            shuffleCards(deck);
            int i = 0;
            while (i < deckSize) {
                int drawNum;
                try {
                    System.out.println("How many card you want to draw?");
                    drawNum = Integer.parseInt(sc.nextLine());
                    if (drawNum < 0) {
                        throw new NegativeArraySizeException();
                    }
                } catch (Exception e) {
                    drawNum = getCorrectDrawNum();
                }
                if (drawNum > deckSize - i) {
                    drawNum = deckSize - i;
                    System.out.println("There are only " + drawNum + " card(s) left.");
                }
                if (drawNum > 1) {
                    System.out.println("Here are the cards:");
                    for (int j = 0; j < drawNum; j++) {
                        System.out.println(deck[i].getTitle());
                        System.out.println(deck[i].getDescription());
                        i++;
                    }
                }
            }

            System.out.println("Do you want to play again? (press [Y] to play again)");
            String input = sc.nextLine();
            if (!input.toLowerCase().equals("y")) {
                playAgain = false;
            }
        }
    }

    private static Card[] createDeckOfCards(int num) {
        Card[] deck = new Card[num];
        for (int i = 1; i < num + 1; i++) {
            Card card = new Card("Card #" + i, "This is card " + i);
            deck[i-1] = card;
        }
        return deck;
    }

    private static Card[] shuffleCards(Card[] deck) {
        Random random = new Random();
        for (int i = 0; i < deck.length; i++) {
            int randomIndex = random.nextInt(deck.length);
//            System.out.println(randomIndex);
            Card temp = deck[i];
            deck[i] = deck[randomIndex];
            deck[randomIndex] = temp;
        }
        return deck;
    }

    private static int getCorrectDrawNum() {
        Scanner sc = new Scanner(System.in);
        int drawNum;
        try {
            System.out.println("Please enter POSITIVE NUMBER of how many card(s) you want to draw:");
            drawNum = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            return getCorrectDrawNum();
        }
        return drawNum;
    }
}
