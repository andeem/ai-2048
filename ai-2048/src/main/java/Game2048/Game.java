package Game2048;

/**
 * Interface for turn based game
 * @param <T>
 */
public interface Game<T> {
 
    void playRandom();
    boolean isRunning();
    int getWins();
    int getTotalGames();
    boolean canMove();
    Game[] getMoves();
    Game[] getLegalMoves();
    void add(Game toAdd);
    Game getLatest();
    boolean hasWon();
    void addWin();
    void addLoss();
    Board getBoard();
}
