// This is a little stack hack i made for no real good reason
// the idea behind this game is that you try to make it so your opponant 
// grabs the pickle chip instead of you.
// the idea here is that you are given the choice to pick either 2 or 3
// chips at a time, and so does your opponant.
// you cannot just eat 1 without shuffling the top 3 (1 in 3 chance of eating anyway)
// the goal of the game is to try and make your opponant eat the pickle 
// chip, which is very disgusting.
// throughout the game, the computer will tell you approximatly how 
// far away the chip is, ex, "the chip is 20, 10, 5 chips away"
// it does not tell you the exact location, and you have to try and
// figure out exactly how many chips you should take to set up your
// opponant to eat the chip.
// the chip spawns within the last 25 - 35 chips. and you only know 
// when it's within the next 20, 10, and 5 chips.
// it's pretty much a gambling game i think
// i haven't written this game yet so i don't know if it will be fun.

// TODO : Make this game fun. 

package game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // generate chip can
        Can chipCan = new Can();

        // input
        Scanner input = new Scanner(System.in);

        // explain rules / title screen
        // TODO

        String loser = "none";
        String player = "player 1";
        int turn = 1;

        // see if you could just replace it with while(true)
        while (loser.equals("none")) {

            // player x turn (options: eat 3, eat 2, shuffle and eat 1)
            System.out.println("Turn " + turn);
            System.out.println("Chip " + chipCan.checkDistance());
            System.out.println(player);
            System.out.println("Possible options:" + 
                            "\n 2 - eat top two chips" + 
                            "\n 3 - eat top three chips" + 
                            "\n s - shuffle top 3 and eat 1");

            System.out.print("> ");
            char option = input.nextLine().toCharArray()[0]; // maybe re think this

            boolean isPickleChipEaten;

            // TODO : handle invalid input
            if (option == '2') 
                isPickleChipEaten = chipCan.eatChips(2);
            else if (option == '3')
                isPickleChipEaten = chipCan.eatChips(3);
            else 
                isPickleChipEaten = chipCan.shuffle();

            // check if it was eaten

            if (isPickleChipEaten) {
                loser = player;
                break;
            }

            // prepare for next loop
            ++turn;

            if (player.equals("player 1")) {
                player = "player 2";
            } else {
                player = "player 1";
            }
        }

        System.out.println("You lose!");

        if (loser.equals("player 1")) {
            System.out.println("Player 2 wins!");
        } else {
            System.out.println("Player 1 wins!");
        }

        // exit program
        input.close();

    }
}