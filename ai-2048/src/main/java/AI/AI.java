package AI;

import Game2048.Board;
import Game2048.Game;
import Game2048.Game2048;
import Utils.Stack;
import java.util.Random;
//import java.util.Stack;

public class AI {

    private Game<Board> game;
    private Random rand;
    private Game<Board> current;
    private Stack<Game> stack;
    private long simulations;
    public int c512;
    public int c1024;
    public int c2048;

    public AI(Game game) {
        this.game = game;
        this.rand = new Random();
        this.simulations = 0;
        this.c512 = 0;
        this.c1024 = 0;
        this.c2048 = 0;

    }

    public boolean oneTurn() {
        long time = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
//            System.out.println(i);
            playOut();
        }
        boolean result = doMove();
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
        playOut(2);
    }

    public void playOut(int selection) {
//        long start = System.currentTimeMillis();
        select(true);
//        System.out.println(System.currentTimeMillis() - start);
        add();
//        System.out.println(System.currentTimeMillis() - start);
        boolean win = simulate(selection);
//        System.out.println(System.currentTimeMillis() - start);
        backpropagate(win);
//        System.out.println(System.currentTimeMillis() - start);
    }

    private void select(boolean UCT) {
        current = game;
        stack = new Stack();
        if (current.canMove()) {
            stack.push(current);
        }
        if (UCT) {
            while (!current.canMove()) {

                if (current.getMoves().length == 0) {
                    break;
                }
                stack.push(current);
                double highestUct = 0;
                Game next = null;
                for (Game move : current.getMoves()) {
                    double moveUct = (double) move.getWins() / move.getTotalGames();
                    moveUct += 1.2 * Math.sqrt(Math.log(simulations) / move.getTotalGames());
                    if (moveUct > highestUct) {
                        highestUct = moveUct;
                        next = move;
                    }
                }
                current = next;
            }
        } else {
            while (!current.canMove()) {

                if (current.getMoves().length == 0) {
                    break;
                }
                stack.push(current);
                Game next = current.getMoves()[rand.nextInt(current.getMoves().length)];
                current = next;
            }
        }
    }

    private void add() {
        if (current.getLegalMoves().length < 1) {
            return;
        }
        Game toAdd = current.getLegalMoves()[rand.nextInt(current.getLegalMoves().length)];
        current.add(toAdd);
        stack.push(toAdd);
    }

    private boolean simulate(int selection) {
        if (current.getLegalMoves().length == 0) {
            return false;
        }
        Game2048 simulation = new Game2048(rand, new Board(rand, current.getLatest().getBoard().boardCopy(), current.getLatest().getBoard().getTurn(), false), current.getBoard().getHighest());
        while (simulation.isRunning()) {
            simulation.playRandom(selection);
        }
        this.simulations++;
        if (simulation.getBoard().getHighest() >= 2048) {
            c2048++;
        } else if (simulation.getBoard().getHighest() >= 1024) {
            c1024++;
        } else if (simulation.getBoard().getHighest() >= 512) {
            c512++;
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
