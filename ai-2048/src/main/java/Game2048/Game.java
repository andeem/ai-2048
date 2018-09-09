package Game2048;

/**
 * Interface for turn based game
 * @param <T>
 */
public interface Game<T> {
 
    void playRandom();
    boolean ownTurn();
    boolean getRunning();
    int getWins();
    int getTotalGames();
    T[] getBoards();
}
