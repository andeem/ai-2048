package Game2048;

import java.util.Random;

/**
 * 2048 game board
 */
public class Board {

    private int[][] board;
    
    /**
     * Class constructor
     */
    public Board() {
        board = new int[4][4];
        int count = 0;
        Random random = new Random();
        int x = random.nextInt(4) + 1;
        int y = random.nextInt(4) + 1;
        
        while (count < 4) {
            if (board[y][x] == 0) {
                if (Math.random() < 0.9) {
                    board[y][x] = 2;
                } else {
                    board[y][x] = 4;
                }
                count++;
            }
        }
    }
    /**
     * Construcor for custom size board, not implemented yet
     * @param size 
     */
    public Board(int size){
        throw new UnsupportedOperationException();
    }
}

