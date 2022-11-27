package junitsinglylinkedlist;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestSLL {

	public SLL sll;
	@Before
	public void setUp() throws Exception {
		sll = new SLL();
	}
    @Test
    public void testHeadForNull() {
        assertNull(sll.head);
    }
    
    @Test
    public void testHeadForNode() {
        sll.add(8);
        assertNotNull(sll.head);
    }
    
    @Test
    public void testHeadNextVal() {
        sll.add(5);
        sll.add(10);
        assertTrue(sll.head.next.value == 10);
    }
    
    @Test
    public void testHeadAfterRemove() {
        sll.add(10);
        sll.removeFront();
        assertNull(sll.head);
    }

}
