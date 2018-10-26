/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UtilsTest;

import Utils.ArrayList;
import java.util.Iterator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author emil
 */
public class ArrayListTest {

    private ArrayList<String> list;

    public ArrayListTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        list = new ArrayList();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testNewListIsEmpty() {
        assertTrue(list.isEmpty());
    }

    @Test
    public void testNewListSizeIsZero() {
        assertEquals(0, list.size());
    }

    @Test
    public void testAfterAdditionSizeIsOne() {
        list.add("joo");
        assertEquals(1, list.size());
    }

    @Test
    public void testAfterAdditionListIsNotEmpty() {
        list.add("joo");
        assertFalse(list.isEmpty());
    }

    @Test
    public void testRemoveRemovesObjectAtIndex() {
        list.add("joo");
        list.remove(0);
        assertTrue(list.isEmpty());
    }

    @Test
    public void testRemoveRemovesObjectAtIndexMultipleObjectsInList() {
        list.add("joo");
        list.add("joo1");
        list.add("joo2");
        list.remove(1);

        assertEquals(2, list.size());
    }

    @Test
    public void testRemoveFromIndexReturnsRightObject() {
        list.add("joo");
        list.add("joo1");
        list.add("joo2");
        assertEquals("joo1", list.remove(1));
    }
    
    @Test
    public void testRemoveFromIndecRemovesFromLastIndex() {
        list.add("joo");
        list.add("joo1");
        list.add("joo2");
        assertEquals("joo2", list.remove(2));
    }
    
    @Test
    public void testRemoveFromIndecRemovesFromFirstIndex() {
        list.add("joo");
        list.add("joo1");
        list.add("joo2");
        assertEquals("joo", list.remove(0));
    }
    @Test
    public void testListStateIsValidAfterRemove() {
        list.add("joo");
        list.add("joo1");
        list.add("joo2");
        list.remove(1);
        assertEquals("joo", list.get(0));
        assertEquals("joo2", list.get(1));
    }
    
    @Test
    public void testSizeIsRight() {
        list.add("joo");
        list.add("joo1");
        list.add("joo2");
        assertEquals(3, list.size());
    }
    
    @Test
    public void testRemoveByObjectRemoves() {
        list.add("joo");
        list.remove("joo");
        assertTrue(list.isEmpty());
    }
    
    @Test
    public void testRemoveByObjectRemoveRightObject() {
        list.add("joo");
        list.add("joo1");
        list.add("joo2");
        assertEquals("joo1", list.remove("joo1"));
    }
    
    @Test
    public void testgetReturnRightObject() {
        list.add("joo");
        list.add("joo1");
        list.add("joo2");
        assertEquals("joo1", list.get(1));
    }
    
    @Test
    public void testGetReturnsRightObjectOnlyOneObjectOnList() {
        list.add("joo");
        assertEquals("joo", list.get(0));
    }
    
    @Test
    public void testIterator() {
        list.add("joo");
        list.add("joo1");
        list.add("joo2");
        
        Iterator<String> it = list.iterator();
        assertTrue(it.hasNext());
        assertEquals("joo", it.next());
        assertEquals("joo1", it.next());
        assertEquals("joo2", it.next());
    }
}
