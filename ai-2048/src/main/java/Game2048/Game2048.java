package Game2048;

import Utils.ArrayList;
import java.util.Random;

public class Game2048 implements Game {

    private Random random;
    private final Board board;
    private ArrayList<Game> moves;
    private ArrayList<Game> legalMoves;
    private int wins;
    private int losses;
    private Directions directionToCounter;
    private String prevHighestPos;
    private int highest;

    public Game2048(Random random, Board board, int highest) {
        this.random = random;
        this.board = board;
        this.moves = new ArrayList();
        //this.legalMoves = legalMovesFromBoard();
        this.wins = 0;
        this.losses = 0;
        this.directionToCounter = null;
        this.prevHighestPos = "00";
        this.highest = highest;
    }

    @Override
    public void playRandom() {
        playRandom(1);
    }

    public void playRandom(int selection) {
        //Board simulation = new Board(random, board.getBoard(), board.getTurn());
        if (board.getTurn() == 1) {
            board.addBlock();
        } else {
            if (board.playerCanMove()) {
                switch (selection) {
                    case 1:
                        randomPlay1();
                        break;
                    case 2:
                        randomPlay2();
                        break;
                    case 3:
                        randomPlay3();
                        break;
                    case 4:
                        randomPlay4();
                        break;
                }
            }
        }

    }

    private void randomPlay4() {

        if (board.move(Directions.LEFT)) {
            return;
        }
        if (board.move(Directions.DOWN)) {
            return;
        }
        if (board.move(Directions.RIGHT)) {
            return;
        }
        if (board.move(Directions.UP)) {
            return;
        }

    }

    private void randomPlay3() {
            Directions dir = getDirection(random.nextInt(4));
            while (!board.move(dir)) {
                dir = getDirection(random.nextInt(4));
            }
    }

    private void randomPlay2() {

            if (board.move(Directions.LEFT)) {
                return;
            }
            if (board.move(Directions.DOWN)) {
                return;
            }

            Directions dir = getDirection(random.nextInt(4));
            while (!board.move(dir)) {
                dir = getDirection(random.nextInt(4));
            }
    }

    private void randomPlay1() {
            if (directionToCounter != null && !prevHighestPos.equals(board.getHighestPos())) {
                if (board.move(directionToCounter)) {
                    return;
                }
            }
            directionToCounter = null;
            prevHighestPos = board.getHighestPos();
            if (board.move(Directions.LEFT)) {
                return;
            }
            if (board.move(Directions.DOWN)) {
                return;
            }
            if (board.move(Directions.UP)) {
                directionToCounter = Directions.DOWN;
                return;
            }
            if (board.move(Directions.RIGHT)) {
                directionToCounter = Directions.LEFT;
                return;
            }

            Directions dir = getDirection(random.nextInt(4));
            directionToCounter = dir.next().next();
            while (!board.move(dir)) {
                dir = getDirection(random.nextInt(4));
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
    public boolean isRunning() {
        
        return board.playerCanMove() ;//&& board.getHighest() <= highest;
    }

    @Override
    public int getWins() {
        return wins;
    }

    @Override
    public int getTotalGames() {
        return wins + losses;
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
        return arrayFromList(this.moves);
    }

    @Override
    public Game[] getLegalMoves() {
        if (this.legalMoves == null) {
            this.legalMoves = legalMovesFromBoard();
        }
        return arrayFromList(this.legalMoves);
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
        return board.getHighest() > highest;
        

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
        ArrayList<Board> boards = board.getLegalMoves();
        for (Board b : boards) {
            moves.add(new Game2048(random, b, this.highest));
        }
        return moves;
    }

    private Game[] arrayFromList(ArrayList<Game> list) {
        Game[] arr = new Game[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        
        return arr;
    }
    
    @Override
    public Board getBoard() {
        return board;
    }

}
