package Game2048;

import static org.mockito.Mockito.*;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {

    public Board board;
    public Random rand;

    public BoardTest() {
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
        when(rand.nextInt(anyInt())).thenReturn(0, 1, 2);
        board = new Board(rand);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testBoardIsCreatedWithThreeNumbers() {
        int count = 0;
        int[][] b = board.getBoard();
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                if (b[i][j] != 0) {
                    count++;
                }
            }
        }

        assertEquals(3, count);
    }

    @Test
    public void testMoveAddsUpNumbers() {
        board.move(Directions.LEFT);
        int[][] b = board.getBoard();
        boolean found = false;
        for (int i = 0; i < b[1].length; i++) {
            if (b[0][i] == 4) {
                found = true;
            }
        }
        assertTrue(found);
    }

    @Test
    public void testMoveMovesNumbersOverEmptySpace() {
        reset(rand);
        when(rand.nextInt(anyInt())).thenReturn(3, 6, 9);
        board = new Board(rand);
        assertTrue("First index empty", board.getBoard()[0][0] == 0);
        board.move(Directions.LEFT);
        assertTrue("First index not empty", board.getBoard()[0][0] == 2);
    }

    @Test
    public void testMoveMovesNumbersOverEmptySpaceAndAddsThem() {
        reset(rand);
        when(rand.nextInt(anyInt())).thenReturn(0, 2, 12);
        board = new Board(rand);
        assertTrue("First index has value 2", board.getBoard()[0][0] == 2);
        board.move(Directions.LEFT);
        assertTrue("First index has value 4", board.getBoard()[0][0] == 4);
    }

    @Test
    public void testMoveRotatesTheBoard() {
        reset(rand);
        when(rand.nextInt(anyInt())).thenReturn(3, 7, 11);
        board = new Board(rand);
        assertTrue("Index[3,0] not empty", board.getBoard()[0][3] == 2);
        board.move(Directions.UP);
        assertTrue("Index[0,0] not empty", board.getBoard()[0][0] == 2);
    }

    @Test
    public void testNewBlockIsAddedAfterMove() {
        reset(rand);
        when(rand.nextInt(anyInt())).thenReturn(4, 4, 4, 0);
        board = new Board(rand);
        board.addBlock();
        assertEquals(2, board.getBoard()[0][0]);
    }
    
    @Test
    public void testPlayerCanMoveWhenThereIsFreeSlots() {
        assertTrue(board.playerCanMove());
    }
    
    @Test
    public void testPlayerCanMoveWhenBoardIsFullButCombineIsPossible() {
        int[][] b = {
            {2,4,2,4},
            {4,2,4,2},
            {8,2,8,4},
            {4,8,4,2}};
        board = new Board(rand, b);
        assertTrue(board.playerCanMove());
    }
    
    @Test
    public void testPlayerCantMoveWhenBoardIsFullAndNoMoveIsPossible() {
        int[][] b = {
            {2,4,2,4},
            {4,2,4,2},
            {2,4,2,4},
            {4,2,4,2}};
        board = new Board(rand, b);
        assertFalse(board.playerCanMove());
    }

    private void playToFullBoard() {
        reset(rand);
        when(rand.nextInt(anyInt())).thenReturn(0, 0, 0, 3, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        when(rand.nextDouble()).thenReturn(0d, 1d, 0d, 1d, 1d, 0d, 1d, 0d, 0d, 1d, 0d, 1d, 1d, 0d, 1d, 0d);
        board = new Board(rand);
        board.addBlock();
        assertEquals(2, board.getBoard()[0][0]);
    }

}
