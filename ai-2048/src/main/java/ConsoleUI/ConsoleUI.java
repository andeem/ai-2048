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
        Board b = new Board(new Random());
        Game2048 game = new Game2048(new Random(), b);
        AI ai = new AI(game);
        ai.playOut();
    }

    private static void move(Board b, Directions d) {
        if (b.move(d)) {
            b.addBlock();
        }
    }
}
