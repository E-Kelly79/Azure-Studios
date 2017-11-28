package controller;

/*
 * Author: Eoin Kelly 
 * Student No: 20074820
 * Date: 03/11/17
 */
import java.io.File;
import java.io.IOException;
import java.util.Collection;

import com.google.common.base.Optional;

import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellDependent;
import asg.cliche.ShellFactory;
import model.Movies;
import model.Users;
import utils.Serializer;
import utils.XMLSerializer;

public class Azure implements ShellDependent {
	private static final String ADMIN = "admin";
	private Shell shell;
	public AzureFlimAPI azure;

	// Make a constructor of the azure clas to load the file for read and writing
	public Azure() throws Exception {
		File movies = new File("./lib/users9.xml");
		Serializer serializer = new XMLSerializer(movies);
		azure = new AzureFlimAPI(serializer);
		if (movies.isFile()) {
			azure.load();
		}
	}

	public void cliSetShell(Shell shell) {
		this.shell = shell;
	}

	@Command(description = "Log in")
	public void logIn(@Param(name = "firstname") String firstName, @Param(name = "lastName") String lastName) throws IOException {
		if (azure.login(firstName, lastName) && azure.currentUser.isPresent()) {
			Users user = azure.currentUser.get();
			System.out.println("You are logged in as " + user.firstName + user.lastName);
			if (user.role != null && user.role.equals(ADMIN)) {
				AdminMenu adminMenu = new AdminMenu(azure, user.firstName);
				ShellFactory.createSubshell(user.firstName, shell, "admin", adminMenu).commandLoop();
			} else {
				DefaultMenu defaultMenu = new DefaultMenu(azure, user);
				ShellFactory.createSubshell(user.firstName, shell, "default", defaultMenu).commandLoop();
			}
		} else {
			System.out.println("Unknown username/password.");
		}
	}

	//Just A command
	@Command(description = "Get all users details")
	public void getAllUsers() {
		Collection<Users> user = azure.getUsers();

		System.out.println(user);
	}

	@Command(description = "Get all movies details")
	public void getAllMovies() {
		Collection<Movies> movies = azure.getMovies();
		System.out.println(movies);
	}

	@Command(description = "Get User By Name")
	public void getUserByName(@Param(name = "First Name") String name) {
		Users user = azure.getUserByName(name);
		System.out.println(user);
	}

	@Command(description = "Get Movie by titler ")
	public void getMovieByTitle(@Param(name = "title") String title) {
		Movies movieTitle = azure.getMovieByTitle(title);
		System.out.println(movieTitle);
	}

	@Command(description = "Delete a user")
	public void deleteUser(@Param(name = "name") String name) {
		Optional<Users> user = Optional.fromNullable(azure.getUserByName(name));
		if (user.isPresent()) {
			azure.deleteUser(user.get().id);
		}
	}

	@Command(description = "Create a new user")
	public void addUser(@Param(name = "first name") String firstName, @Param(name = "last name") String lastName,
			@Param(name = "age") String age, @Param(name = "gender") String gender,
			@Param(name = "occupation") String occupation) {

		azure.createUser(firstName, lastName, age, gender, occupation);
	}

	@Command(description = "Create a new movie")
	public void addMovie(@Param(name = "user-id") Long id, @Param(name = "title") String title,
			@Param(name = "year") String year, @Param(name = "url") String url) {
		Optional<Users> user = Optional.fromNullable(azure.getUserById(id));
		if (user.isPresent()) {
			azure.createMovie(id, title, year, url);
		}
	}

	@Command(description = "Add Rating to a movie")
	public void addRatings(@Param(name = "activity-id") Long id, @Param(name = "user-id") Long userId,
			@Param(name = "movie-id") Long movieId, @Param(name = "rating") int ratings) {
		Optional<Movies> rating = Optional.fromNullable(azure.getMovie(movieId));
		if (rating.isPresent()) {
			azure.addRatings(rating.get().id, userId, movieId, ratings);
		}
	}

	public static void main(String[] args) throws Exception {
		Azure main = new Azure();
		main.azure.initalLoad();
		Shell shell = ShellFactory.createConsoleShell("lm", "Welcome to movieDome - please type ?list for a menu",
				main);
		shell.commandLoop();
		main.azure.store();

	}

}
