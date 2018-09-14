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
    private boolean canBeAdded;
    private boolean playerCanMove;

    /**
     * Class constructor
     *
     * @param random
     */
    public Board(Random random) {
        this(random, new int[4][4]);
    }
    
    public Board(Random random, int[][] board){
        orientation = Directions.LEFT;
        this.board = board;
        int count = 0;
        this.random = random;
        canBeAdded = true;
        playerCanMove = true;
        freeCoords = new ArrayList<>();
        updateEmptySlots();

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
    /**
     * Adds a block on a empty space
     */
    public void addBlock() {
        if (freeCoords.isEmpty()) {
            canBeAdded = false;
            return;
        }
        rotate(Directions.LEFT);
        int newCoord = random.nextInt(freeCoords.size());
        String coord = freeCoords.remove(newCoord);
        int x = Integer.parseInt(coord.substring(0, 1));
        int y = Integer.parseInt(coord.substring(1, 2));
        if (random.nextDouble() < 0.9) {
            board[y][x] = 2;
        } else {
            board[y][x] = 4;
        }

    }
    /**
     * Moves blocks to selected direction
     * @param direction 
     */
    public void move(Directions direction) {
        if (!playerCanMove()) {
            return;
        }
        
        rotate(direction);

        combine();
        
        rotate(Directions.LEFT);
        updateEmptySlots();
    }

    private void combine() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int k = j;
                while (true) {
                    if (k == 0) {
                        break;
                    } else if (board[i][k] != 0 && board[i][k] == board[i][k - 1]) {
                        board[i][k - 1] = board[i][k - 1] * 2;
                        board[i][k] = 0;
                        k--;
                    } else if (board[i][k - 1] == 0 && board[i][k] != 0) {
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
    /**
     * Returns true if theres legal moves
     * @return 
     */
    public boolean playerCanMove() {
        if (!freeCoords.isEmpty()) {
            return true;
        }
        for (int k = 0; k < 4; k++) {
            rotate(orientation.next());
            for (int i = 0; i < board.length; i++) {
                int prev = 0;
                for (int j = 0; j < board[i].length; j++) {
                    if (j == 0) {
                        prev = board[i][j];
                        continue;
                    } else if (prev == board[i][j]) {
                        return true;
                    } else {
                        prev = board[i][j];
                    }

                }
            }
        }
        return false;
    }

    private void rotate(Directions direction) {
        if (direction == orientation) {
            return;
        }
        int[][] b = new int[4][4];
        while (orientation != direction) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    b[j][i] = board[i][j];
                }
            }
            orientation = orientation.next();
        }
        board = b;
    }
    
    private void updateEmptySlots() {
        freeCoords.clear();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    freeCoords.add(Integer.toString(j) + Integer.toString(i));
                }
            }
        }
        canBeAdded = !freeCoords.isEmpty();
    }

    public int[][] getBoard() {
        return board;
    }
    
}
