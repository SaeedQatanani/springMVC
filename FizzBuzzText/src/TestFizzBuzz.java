import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestFizzBuzz {
	public FizzBuzz fb;
	@Before
	public void setUp() throws Exception {
		fb = new FizzBuzz();
	}

	@Test
	public void test() {
		assertNotNull(fb);
	}
	
	@Test
    public void testForFizz() {
        String result = fb.fizzBuzz(3);
        assertTrue(result.equals("fizz"));
    }
    
    @Test
    public void testForBuzz() {
        String result = fb.fizzBuzz(5);
        assertTrue(result.equals("buzz"));
    }
    
    @Test
    public void testForFizzBuzz() {
        String expected = "fizzbuzz";
        String actual = fb.fizzBuzz(30);
        assertEquals(expected, actual);
    }

}
