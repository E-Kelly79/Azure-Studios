package model;

public class Fixtures {
	public static Users[] users = { 
			new Users("Eoin", "Kelly", 38, 'M', "Student"),
			new Users("Emma", "Martin", 32, 'F', "Student") 
	};

	public static Movies[] movies = { 
			new Movies("Gonnies", "1989", "Gonnies.com"),
			new Movies("Twilight", "2008", "Twilight.com"), 
			new Movies("Shrek", "2001", "Shrek.org"),
			new Movies("Robocop", "1992", "Robocop.net")

	};
	
	public static Ratings[] rating = { 
			new Ratings(1L, 2L, 3),
			new Ratings(2L, 3L, 4), 
			new Ratings(3L, 4L, 5),
			new Ratings(4L, 5L, 6)

	};
}
