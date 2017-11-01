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
	
	@Command(description="Get all users details")
	public void getAllUsers() {
		Collection<Users> user = azure.getUsers();
		System.out.println(user);
	}
	
	@Command(description="Get User By Name")
	public void getUserByName(
			@Param(name="First Name") String name) 
	{
		Users user = azure.getUserByName(name);
		System.out.println(user);
	}
	
	@Command(description="Delete a user")
	public void deleteUser(
			@Param(name="name") String name) 
	{
		Optional<Users> user = Optional.fromNullable(azure.getUserByName(name));
		if(user.isPresent()) {
			azure.deleteUser(user.get().id);
		}
	}
	
	@Command(description="Create a new user")
	public void addUser(@Param(name="first name") String firstName, 
						@Param(name="last name") String lastName, 
            			@Param(name="age") String age,     
            			@Param(name="gender") String gender, 
            			@Param(name="occupation")  String occupation) 
	{
		
		azure.createUser(firstName, lastName, age, gender, occupation);
	}
	
	@Command(description="Create a new movie")
	public void addMovie(@Param(name="user-id") Long id, 
						@Param(name="title") String title, 
            			@Param(name="year") String year,     
            			@Param(name="url") String url) 
	{
		Optional<Users> user = Optional.fromNullable(azure.getUserById(id));
		if(user.isPresent()) {
			azure.createMovie(id, title, year, url);
		}
	}
	
	@Command(description="Add Rating to a movie")
	  public void addRatings (@Param(name="activity-id") Long  id,   
	                          @Param(name="user-id") Long userId, 
	                          @Param(name="movie-id") Long movieId,
	                          @Param(name="rating") int ratings)
	{
	    Optional<Movies> rating = Optional.fromNullable(azure.getMovie(movieId));
	    if (rating.isPresent())
	    {
	      azure.addRatings(rating.get().id, userId, movieId, ratings);
	    }
	  }

	public static void main(String[] args) throws Exception {
		Azure main = new Azure();
	    Shell shell = ShellFactory.createConsoleShell("pm", "Welcome to Azure Stuidos\nPlease type ?help for instructions", main);
	    //main.azure.initalLoad();
	    shell.commandLoop();

	    main.azure.store();
	}

}
