package Game2048;

import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class Game2048Test {
    
    private Game2048 game;
    private Random rand;
    private Board board;
    
    public Game2048Test() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        rand = mock(Random.class);
        board = mock(Board.class);
        game = new Game2048(rand, board);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testRandomMove() {
        when(board.playerCanMove()).thenReturn(true);
        when(rand.nextInt(4)).thenReturn(0);
        game.playRandom();
        verify(board).move(any());
    }
    
}
