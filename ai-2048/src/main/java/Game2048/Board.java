package Game2048;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 * 2048 game board
 */
public class Board {

    private int[][] board;
    private Random random;
    private Directions orientation;
    private ArrayList<String> freeCoords;

    /**
     * Class constructor
     * @param random
     */
    public Board(Random random) {
        orientation = Directions.LEFT;
        board = new int[4][4];
        int count = 0;
        this.random = random;
        freeCoords = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                freeCoords.add(Integer.toString(j) + Integer.toString(i));
            }
        }
        
        for (int i = 0; i < 3; i++) {
            addBlock();        
        }
    }

    

    /**
     * Class construcor for custom size board, not implemented yet
     *
     * @param size
     */
    public Board(int size) {
        throw new UnsupportedOperationException();
    }
    
    public void addBlock() {
        int newCoord = random.nextInt(freeCoords.size());
        String coord = freeCoords.remove(newCoord);
        int x = Integer.parseInt(coord.substring(0,1));
        int y = Integer.parseInt(coord.substring(1,2));
        if (random.nextDouble() < 0.9) {
            board[y][x] = 2;
        } else {
            board[y][x] = 4;
        }

    }
    
    public void move(Directions direction) {
        rotate(direction);
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int k = j;
                while(true){
                    if (k == 0) {
                        break;
                    } else if (board[i][k] != 0 && board[i][k] == board[i][k - 1]){
                        board[i][k - 1] = board[i][k - 1] * 2;
                        board[i][k] = 0;
                        k--;
                    } else if(board[i][k-1] == 0 && board[i][k] != 0) {
                        board[i][k - 1] = board[i][k];
                        board[i][k] = 0;
                        k--;
                    } else {
                        break;
                    }
                }
            }
        }
    }

    private void rotate(Directions direction) {
        while(orientation != direction) {
            int[][] b = new int[4][4];
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    b[j][i] = board[i][j];
                }
            }
            orientation = orientation.next();
        }
    }
    
    public int[][] getBoard() {
        return board;
    }
}
