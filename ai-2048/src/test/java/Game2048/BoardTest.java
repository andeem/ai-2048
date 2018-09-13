package Game2048;

import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {

    public Board board;

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
        board = new Board(new StubRandom(0, 1, 2));
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
        board = new Board(new StubRandom(3, 6, 9));
        assertTrue("First index empty", board.getBoard()[0][0] == 0);
        board.move(Directions.LEFT);
        assertTrue("First index not empty", board.getBoard()[0][0] == 2);
    }

    @Test
    public void testMoveMovesNumbersOverEmptySpaceAndAddsThem() {
        board = new Board(new StubRandom(0, 2, 12));
        assertTrue("First index has value 2", board.getBoard()[0][0] == 2);
        board.move(Directions.LEFT);
        assertTrue("First index has value 4", board.getBoard()[0][0] == 4);
    }

    @Test
    public void testMoveRotatesTheBoard() {
        board = new Board(new StubRandom(3, 7, 11));
        assertTrue("Index[3,0] not empty", board.getBoard()[0][3] == 2);
        board.move(Directions.UP);
        assertTrue("Index[0,0] not empty", board.getBoard()[0][0] == 2);
    }

    @Test
    public void testNewBlockIsAddedAfterMove() {
        board = new Board(new StubRandom(4, 4, 4, 0));
        board.addBlock();
        assertEquals(2, board.getBoard()[0][0]);
    }

}
