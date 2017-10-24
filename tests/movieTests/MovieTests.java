package movieTests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.Movies;

	public class MovieTests
	{

	  Movies test = new Movies ("walk",  "fridge", ".com");

	  @Test
	  public void testCreate()
	  {
	    assertEquals ("walk",          test.title);
	    assertEquals ("fridge",        test.year);
	    assertEquals ( ".com",   test.url);    
	  }

	  @Test
	  public void testToString()
	  {
	    assertEquals ("Movies{" + test.id + ", walk, fridge, .com, []}", test.toString());
	  }
	}


