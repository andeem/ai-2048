package AI;

import Game2048.Board;
import Game2048.Game;
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
    public void playOut() {
        select();
        add();
        backpropagate(simulate());
    }
    
    private void select() {
        current = game;
        stack = new Stack();
        while(!current.canMove()) {
            stack.push(current);
            current = current.getMoves()[rand.nextInt(current.getMoves().length)];
        }        
    }

    private void add() {
        Game toAdd = current.getLegalMoves()[rand.nextInt(current.getLegalMoves().length)];
        current.add(toAdd);
    }
    
    private boolean simulate() {
        Game simulation = current.getLatest();
        while(simulation.isRunning()) {
            simulation.playRandom();
        }
        System.out.println(simulation.getBoard());
        return simulation.hasWon();
    }

    private void backpropagate(boolean win) {
        if (win) {
            while(!stack.empty()){
                stack.pop().addWin();
            }
        } else {
            while(!stack.empty()){
                stack.pop().addLoss();
            }
        }
    }
}
