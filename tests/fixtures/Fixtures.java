package fixtures;

import model.Movies;
import model.Ratings;
import model.Users;

public class Fixtures {
	public static Users[] users = { 
			new Users("Eoin", "Kelly", 33, "M", "Student", "admin"),
			new Users("Emma", "Martin", 31, "F", "Student", "default"), 
			new Users("Paddy", "Kelly", 10, "M", "Dog"),
			new Users("Sadie", "Kelly", 11, "F", "Dog")
	};

	public static Movies[] movies = { new Movies("Ghostbusters", "1989", "ghostbusters.com"),
			new Movies("Tincup", "1994", "tincup.org"), new Movies("Splash", "1987", "splash.com") };

	public static Ratings[] rating = { new Ratings(2L, 3L, 3), new Ratings(1L, 1L, 5) };

}
