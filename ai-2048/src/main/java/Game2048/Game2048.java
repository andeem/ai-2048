package Game2048;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game2048 implements Game {

    private Random random;
    private final Board board;
    private ArrayList<Game> moves;
    private ArrayList<Game> legalMoves;
    private int wins;
    private int losses;

    public Game2048(Random random, Board board) {
        this.random = random;
        this.board = board;
        this.moves = new ArrayList();
        //this.legalMoves = legalMovesFromBoard();
        this.wins = 0;
        this.losses = 0;
    }

    @Override
    public void playRandom() {
        //Board simulation = new Board(random, board.getBoard(), board.getTurn());
        if (board.getTurn() == 1) {
            board.addBlock();
        } else {
            if (board.playerCanMove()) {
                if (board.move(board.getHighestDir())) {
                    return;
                }
                if (board.move(board.getHighestDir().next())) {
                    return;
                }
                Directions dir = getDirection(random.nextInt(4));
                while(!board.move(dir)) {
                    dir = getDirection(random.nextInt(4));
                }
            }
        }

    }

    private Directions getDirection(int i) {
        switch (i) {
            case 0:
                return Directions.LEFT;
            case 1:
                return Directions.UP;
            case 2:
                return Directions.RIGHT;
            case 3:
                return Directions.DOWN;
        }
        return null;
    }
    
    @Override
    public boolean ownTurn() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isRunning() {
        return board.playerCanMove();
    }

    @Override
    public int getWins() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getTotalGames() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean canMove() {
        if (legalMoves == null) {
            return true;
        } 
        return !legalMoves.isEmpty();
 
    }

    @Override
    public Game[] getMoves() {
        return this.moves.toArray(new Game[moves.size()]);
    }

    @Override
    public Game[] getLegalMoves() {
        if (this.legalMoves == null) {
            this.legalMoves = legalMovesFromBoard();
        }
        return this.legalMoves.toArray(new Game[legalMoves.size()]);
    }

    @Override
    public void add(Game toAdd) {
        moves.add(toAdd);
        legalMoves.remove(toAdd);
    }

    @Override
    public Game getLatest() {
        return moves.get(moves.size() - 1);
    }

    @Override
    public boolean hasWon() {
        return board.getTurn() == -1;
    }

    @Override
    public void addWin() {
        this.wins++;
    }

    @Override
    public void addLoss() {
        this.losses++;
    }

    private ArrayList<Game> legalMovesFromBoard() {
        ArrayList<Game> moves = new ArrayList();
        List<Board> boards = board.getLegalMoves();
        for (Board b : boards) {
            moves.add(new Game2048(random,b));
        }
        return moves;
    }

    @Override
    public Board getBoard() {
        return board;
    }

}
