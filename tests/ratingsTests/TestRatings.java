package ratingsTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Ratings;

public class TestRatings {
	
	private Ratings one;
	private Ratings two;
	
	@Before
	public void setup() {
		one = new Ratings(1L, 2L, 3);
		two = new Ratings(2L, 3L, 4);
	}
	
	@After
	public void tearDown() {
		one = two = null;
	}
	
	@Test
	public void testCreate() {
		assertEquals(1L, one.userId,01);
		assertEquals(2L, one.movieId, 01);
		assertEquals(3, one.rating);
	}

	@Test
	public void testIds() {
		Ratings one = new Ratings(1L, 2L, 3);
		Ratings two = new Ratings(2L, 3L, 4);
		assertNotEquals(one.id, two.id);
	}
	
	@Test
	public void testToString() {
		Ratings one = new Ratings(1L, 2L, 3);
		assertEquals("Ratings{6, 1, 2, 3}", one.toString());
	}

}
