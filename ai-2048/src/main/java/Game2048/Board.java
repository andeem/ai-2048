package Game2048;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
    private int turn;
    private int highest;

    /**
     * Class constructor
     *
     * @param random
     */
    public Board(Random random) {
        this(random, new int[4][4]);
    }

    public Board(Random random, int[][] board) {
        this(random, board, -1);
    }

    public Board(Random random, int[][] board, int turn) {
        orientation = Directions.LEFT;
        this.board = board;
        int count = 0;
        this.random = random;
        canBeAdded = true;
        playerCanMove = true;
        freeCoords = new ArrayList<>();
        updateEmptySlots();
        this.turn = turn;
        this.highest = 0;

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
        int newCoord = random.nextInt(freeCoords.size());
        String coord = freeCoords.remove(newCoord);
        int x = Integer.parseInt(coord.substring(0, 1));
        int y = Integer.parseInt(coord.substring(1, 2));
        if (random.nextDouble() < 0.9) {
            board[y][x] = 2;
        } else {
            board[y][x] = 4;
        }

        this.turn = -1;

    }

    /**
     * Moves blocks to selected direction
     *
     * @param direction
     */
    public boolean move(Directions direction) {
        if (!playerCanMove()) {
            return false;
        }

        rotate(direction);

        boolean didMove = combine();

        rotate(Directions.LEFT);
        updateEmptySlots();
        this.turn = 1;
        return didMove;
    }

    private boolean combine() {
        boolean moved = false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int k = j;
                int limit = 0;
                while (true) {
                    if (k == limit) {
                        break;
                    } else if (board[i][k] != 0 && board[i][k] == board[i][k - 1]) {
                        board[i][k - 1] = board[i][k - 1] * 2;
                        if (board[i][k - 1] > highest) {
                            highest = board[i][k - 1];
                        }
                        board[i][k] = 0;
                        k--;
                        moved = true;
                        limit = k;
                    } else if (board[i][k - 1] == 0 && board[i][k] != 0) {
                        board[i][k - 1] = board[i][k];
                        board[i][k] = 0;
                        k--;
                        moved = true;
                    } else {
                        break;
                    }
                }
            }
        }
        return moved;
    }

    /**
     * Returns true if theres legal moves
     *
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
        int[][] b1 = board;
        while (orientation != direction) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    b[j][i] = b1[i][3 - j];
                }
            }
            b1 = b;

            board = b1;
            b = new int[4][4];
            orientation = orientation.next();
        }
        board = b1;
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

    ArrayList<Board> getLegalMoves() {
        ArrayList<Board> list = new ArrayList();
        if (this.turn == -1) {
            for (int i = 0; i < 4; i++) {
                int[][] b = this.board.clone();
                if (combine()) {
                    list.add(new Board(random, board, 1));
                    this.board = b;
                }
                rotate(orientation.next());
            }
        } else {
            for (String coord : freeCoords) {
                int x = Integer.parseInt(coord.substring(0, 1));
                int y = Integer.parseInt(coord.substring(1, 2));
                int[][] b = this.board.clone();
                board[y][x] = 2;
                list.add(new Board(random, board, -1));
                board = b;
                b = this.board.clone();
                board[y][x] = 4;
                list.add(new Board(random, board, -1));
                board = b;
            }
        }
        return list;
    }

    public int[][] getBoard() {
        return board;
    }

    public int getTurn() {
        return turn;
    }

    public int getEmptyCount() {
        return freeCoords.size();
    }

    public int getHighest() {
        return this.highest;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        int prime = 3;
        for (int i = 0; i < board.length; i++) {
            hash = 3 * hash + Arrays.hashCode(board[i]);
        }
        hash += turn;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Board other = (Board) obj;
        if (this.turn != other.turn) {
            return false;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (this.board[i][j] != other.board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String ts = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                ts += String.format("%1$6d", board[i][j]);
            }
            ts += "\n";
        }
        return ts;
    }

}
