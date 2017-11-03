package movieTests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.Movies;

public class MovieTests {

	Movies test = new Movies("Gonnies", "1989", "gonnies.com");

	// See if a movie can be created
	@Test
	public void testCreate() {
		assertEquals("Gonnies", test.title);
		assertEquals("1989", test.year);
		assertEquals("gonnies.com", test.url);
	}

	// test toString method
	@Test
	public void testToString() {
		assertEquals("Movies{" + test.id + ", Gonnies, 1989, gonnies.com, []}", test.toString());
	}
}
