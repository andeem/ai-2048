package AI;

import Game2048.Board;
import Game2048.Game;
import Game2048.Game2048;
import java.util.Random;
import java.util.Stack;

public class AI {

    private Game<Board> game;
    private Random rand;
    private Game<Board> current;
    private Stack<Game> stack;

    public AI(Game game) {
        this.game = game;
        this.rand = new Random();
    }

    
    public boolean oneTurn() {
        for (int i = 0; i < 1000; i++) {
//            System.out.println(i);
            playOut();
        }
        boolean result = doMove();
        System.out.println(game.getBoard());
        return result;
    }
    
    public boolean doMove() {
        Game toSelect = null;
        if (game.getBoard().getTurn() == -1) {
            double ratio = 0;
            if (game.getMoves().length == 0 && game.getLegalMoves().length == 0) {
                return false;
            }
            toSelect = game.getMoves()[0];
            for (Game move : game.getMoves()) {
                if (ratio < (double) move.getWins() / move.getTotalGames()) {
                    ratio = (double) move.getWins() / move.getTotalGames();
                    toSelect = move;
                }
            }
        } else {
            Board enemyMove = new Board(rand, game.getBoard().boardCopy(), game.getBoard().getTurn(), false);
            enemyMove.addBlock();
            for (Game move : game.getMoves()) {
                if (move.getBoard().equals(enemyMove)) {
                    toSelect = move;
                    break;
                }
            }
        }
        game = toSelect;
        return true;
    }

    public void playOut() {
        playOut(1);
    }
    
    public void playOut(int selection) {
//        long start = System.currentTimeMillis();
        select();
//        System.out.println(System.currentTimeMillis() - start);
        add();
//        System.out.println(System.currentTimeMillis() - start);
        boolean win = simulate(1);
//        System.out.println(System.currentTimeMillis() - start);
        backpropagate(win);
//        System.out.println(System.currentTimeMillis() - start);
    }

    private void select() {
        current = game;
        stack = new Stack();
        if (current.canMove()) {
            stack.push(current);
        }
        while (!current.canMove()) {
            
            if (current.getMoves().length == 0) {
                break;
            }
            stack.push(current);
            Game next = current.getMoves()[rand.nextInt(current.getMoves().length)];
            current = next;
        }
    }

    private void add() {
        if (current.getLegalMoves().length < 1) {
            return; 
        }
        Game toAdd = current.getLegalMoves()[rand.nextInt(current.getLegalMoves().length)];
        current.add(toAdd);
    }

    
    private boolean simulate(int selection) {
        if (current.getLegalMoves().length == 0) {
            return false;
        }
        Game2048 simulation = new Game2048(rand, new Board(rand, current.getLatest().getBoard().boardCopy(), current.getLatest().getBoard().getTurn(), false), current.getBoard().getHighest());
        while (simulation.isRunning()) {
            simulation.playRandom(selection);
        }
        return simulation.hasWon();
    }

    private void backpropagate(boolean win) {
        if (win) {
            while (!stack.empty()) {
                stack.pop().addWin();
            }
        } else {
            while (!stack.empty()) {
                stack.pop().addLoss();
            }
        }
    }

    public Game<Board> getGame() {
        return game;
    }

}
