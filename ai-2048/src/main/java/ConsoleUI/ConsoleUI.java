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
                    simulationTest();
                    break;
                case 2:
                    Board b = new Board(new Random());
                    Game2048 game = new Game2048(new Random(), b, 0);
                    AI ai = new AI(game);
                    while(ai.oneTurn()){}
                    break;
                default:
                    System.out.println("Wrong command");
            }
        }

    }

    private static void simulationTest() {
        for (int i = 1; i <= 4; i++) {
            simulation(i);
        }
    }

    private static void simulation(int method) {
        Board b = new Board(new Random());
        Game2048 game = new Game2048(new Random(), b, 0);
        AI ai = new AI(game);
        for (int i = 0; i < 10001; i++) {
            ai.playOut(method);
        }
        System.out.println(ai.getGame().getWins()); // TODO to csv file
    }

}
