package controller;

import java.io.File;
import java.util.Collection;

import com.google.common.base.Optional;

import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellFactory;
import model.Movies;
import model.Users;
import utils.Serializer;
import utils.XMLSerializer;

public class Azure {
	public AzureFlimAPI azure;
	
	public Azure() throws Exception {
		File movies = new File("./lib/users5.xml");
		Serializer serializer = new XMLSerializer(movies);
		azure = new AzureFlimAPI(serializer);
		if (movies.isFile()) {
			azure.load();
		}
	}
	
//	@Command(description="Get all users details")
//	public void getAllUsers() {
//		Collection<Users> user = azure.getUsers();
//		System.out.println(user);
//	}
//	
//	@Command(description="Get User By Name")
//	public void getUserByName(
//			@Param(name="First Name") String name) 
//	{
//		Users user = azure.getUserByName(name);
//		System.out.println(user);
//	}
//	
//	@Command(description="Delete a user")
//	public void deleteUser(
//			@Param(name="name") String name) 
//	{
//		Optional<Users> user = Optional.fromNullable(azure.getUserByName(name));
//		if(user.isPresent()) {
//			azure.deleteUser(user.get().id);
//		}
//	}
//	
//	@Command(description="Create a new user")
//	public void addUser(@Param(name="first name") String firstName, 
//						@Param(name="last name") String lastName, 
//            			@Param(name="age") String age,     
//            			@Param(name="gender") String gender, 
//            			@Param(name="occupation")  String occupation) 
//	{
//		
//		azure.createUser(firstName, lastName, age, gender, occupation);
//	}
//	
//	@Command(description="Create a new movie")
//	public void addMovie(@Param(name="user-id") Long id, 
//						@Param(name="title") String title, 
//            			@Param(name="year") String year,     
//            			@Param(name="url") String url) 
//	{
//		Optional<Users> user = Optional.fromNullable(azure.getUserById(id));
//		if(user.isPresent()) {
//			azure.createMovie(id, title, year, url);
//		}
//	}
//	
//	@Command(description="Add Rating to a movie")
//	  public void addRatings (@Param(name="activity-id") Long  id,   
//	                          @Param(name="user-id") Long userId, 
//	                          @Param(name="movie-id") Long movieId,
//	                          @Param(name="rating") int ratings)
//	{
//	    Optional<Movies> rating = Optional.fromNullable(azure.getMovie(movieId));
//	    if (rating.isPresent())
//	    {
//	      azure.addRatings(rating.get().id, userId, movieId, ratings);
//	    }
//	  }
	
	public void createUser(String firstName, String lastName, String age, String gender, String occupation) {
	azure.createUser(firstName, lastName, age, gender, occupation);
}

public void getUsers() {
	Collection<Users> users = azure.getUsers();
	System.out.println(users);
}

public void getUserByName(String name) {
	Users user = azure.getUserByName(name);
	System.out.println(user);
}

public void addMovie(Long movieId, String title, String year, String url) {
	Optional<Users> user = Optional.fromNullable(azure.getUserById(movieId));
	if (user.isPresent()) {
		azure.createMovie(movieId, title, year, url);
	}
}

//public void getMovies() {
//	Collection<Movies> movies = azure.getMovies();
//	System.out.println(movies);
//}

public void addRating(Long userId, Long movieId, int rating) {
	Optional<Movies> ratings = Optional.fromNullable(azure.getMovie(movieId));
	if (ratings.isPresent()) {
		azure.addRatings(ratings.get().id,userId, movieId, rating);
	}
}

public static void main(String[] args) throws Exception {
	String firstName, lastName, gender, occupation, title, year, url, age;
	int choice = 0;
	Long movieId = null;
	Azure mainProg = new Azure();
	
	mainProg.azure.initalLoad();
	do {
		System.out.println("Please select an option from the menu");
		menu();
		choice = EasyScanner.nextInt();
		switch (choice) {
		case 1:
			System.out.println("Please enter your first name");
			firstName = EasyScanner.nextString();

			System.out.println("Please enter your last name");
			lastName = EasyScanner.nextString();

			System.out.println("Please enter your age name");
			age = EasyScanner.nextString();

			System.out.println("Please enter you gender");
			gender = EasyScanner.nextString();

			System.out.println("Please enter your occupation");
			occupation = EasyScanner.nextString();
			mainProg.createUser(firstName, lastName, age, gender, occupation);

			break;
		case 2:
			System.out.println("Please enter userID");
			movieId = (long) EasyScanner.nextInt();
			System.out.println("Please enter the title of the movie");
			title = EasyScanner.nextString();

			System.out.println("Please enter the release date of the movie");
			year = EasyScanner.nextString();

			System.out.println("Please enter the website url of the movie");
			url = EasyScanner.nextString();
			mainProg.addMovie(movieId, title, year, url);

			break;
		case 3:
			mainProg.getUsers();
			break;
		case 4:
			System.out.println("Please enter the first name of the user");
			firstName = EasyScanner.nextString();
			mainProg.getUserByName(firstName);
			break;
		}
	} while (choice != 0);
	
	  mainProg.azure.store();
	  
	  
	 //getUsers();
//	 Azure main = new Azure();
//	 Shell shell = ShellFactory.createConsoleShell("lm", "Welcome to movieDome - please type ?list for a menu", main);
//	 shell.commandLoop();
//	 main.azure.store();

}

public static void menu() {
	System.out.println("1. Create a new user");
	System.out.println("2. Delete a user");
	System.out.println("3. List all users");
	System.out.println("4. Create a movie");
	System.out.println("5. List movies");
	System.out.println("6. Add a rating");

}
}

//	public static void main(String[] args) throws Exception {
//		Azure main = new Azure();
//	    Shell shell = ShellFactory.createConsoleShell("pm", "Welcome to Azure Stuidos\nPlease type ?help for instructions", main);
//	    //main.azure.initalLoad();
//	    shell.commandLoop();
//
//	    main.azure.store();
//	}

//}
