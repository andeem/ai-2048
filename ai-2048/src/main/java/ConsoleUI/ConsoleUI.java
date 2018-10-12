/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConsoleUI;

import AI.AI;
import Game2048.Board;
import Game2048.Directions;
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
        System.out.println("Enter command: 1) Run MCTS simulation tests with different simulations, 9) Quit");
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
                    while(ai.oneTurn()){}
                    System.out.println(ai.getGame().getBoard());
                    break;
                default:
                    System.out.println("Wrong command");
            }
        }

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
