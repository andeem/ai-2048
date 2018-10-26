/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConsoleUI;

import AI.AI;
import Game2048.Board;
import Game2048.Game2048;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author emil
 */
public class ConsoleUI {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printMenu();
        while (true) {
            int command = Integer.parseInt(scanner.nextLine());
            if (command == 9) {
                break;
            }
            switch (command) {
                case 1:
                    System.out.println("How many runs?");
                    int cnt = Integer.parseInt(scanner.nextLine());
                    simulationTest(cnt);
                    break;
                case 2:
                    Board b = new Board(new Random());
                    Game2048 game = new Game2048(new Random(), b, 0);
                    AI ai = new AI(game);
                    while (ai.oneTurn()) {
                        final String ANSI_CLS = "\u001b[2J";
                        final String ANSI_HOME = "\u001b[H";
                        System.out.print(ANSI_CLS + ANSI_HOME);
                        System.out.flush();
                        for (int i = 0; i < 10; i++) {
                            System.out.println("");
                        }
                        System.out.print(ai.getGame().getBoard());
                    }
                    System.out.println("Game ended!");
                    System.out.println("Press enter...");
                    scanner.nextLine();
                    printMenu();
                    break;
                default:
                    System.out.println("Wrong command");
            }
        }

    }

    private static void printMenu() {
        System.out.println("Enter command: 1) Run MCTS simulation tests with different simulations, 2) Let the AI play! 9) Quit");
    }

    private static void simulationTest(int cnt) {
        for (int i = 1; i <= 4; i++) {
            simulation(i, cnt);
        }
    }

    private static void simulation(int method, int cnt) {
        Board b = new Board(new Random());
        Game2048 game = new Game2048(new Random(), b, 0);
        AI ai = new AI(game);
        for (int i = 0; i < cnt; i++) {
            ai.playOut(method);
        }
        System.out.println("2048: " + ai.c2048);
        System.out.println("1024: " + ai.c1024);
        System.out.println("512: " + ai.c512);
        System.out.println(ai.getGame().getWins()); // TODO to csv file
    }

}
