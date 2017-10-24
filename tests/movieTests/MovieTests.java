package movieTests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.Movies;

	public class MovieTests
	{
	  private Movies[] movies =
	  {
	    new Movies ("Gonnies",  "1989", "Gonnies.com"),
	    new Movies ("Twilight",  "2008",   "Twilight.com"),
	    new Movies ("Shrek",   "2001",   "Shrek.org"),
	    new Movies ("Robocop",  "1992",   "Robocop.net"),
	   
	  };

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


