package Game2048;

import java.util.Random;

public class Game2048 implements Game {

    private Random random;
    private Board board;

    public Game2048(Random random, Board board) {
        this.random = random;
        this.board = board;
    }

    @Override
    public void playRandom() {
        if (board.playerCanMove()) {
            switch (random.nextInt(4)) {
                case 0:
                    board.move(Directions.LEFT);
                    break;
                case 1:
                    board.move(Directions.UP);
                    break;
                case 2:
                    board.move(Directions.DOWN);
                    break;
                case 3:
                    board.move(Directions.RIGHT);
                    break;
                
                   
            }
        }

    }

    @Override
    public boolean ownTurn() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean getRunning() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public Object[] getBoards() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
