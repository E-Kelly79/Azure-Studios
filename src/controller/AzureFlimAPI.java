package controller;
import java.io.File;
import java.io.IOException;
/*
 * Author Eoin Kelly 
 * Date 03/11/17
 */
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.google.common.base.Optional;

import model.Movies;
import model.Ratings;
import model.Users;
import utils.Serializer;

public class AzureFlimAPI implements azureInterface {

	private Serializer serializer;
	private Map<Long, Users> usersIndex = new HashMap<>();
	private Map<String, Users> usersName = new HashMap<>();
	private Map<Long, Movies> movieIndex = new HashMap<>();
	private Map<String, Movies> movieTitle = new HashMap<>();
	public  Optional<Users> currentUser;

	public AzureFlimAPI() {

	}

	public AzureFlimAPI(Serializer serializer) {
		this.serializer = serializer;
	}
	
	public boolean login(String firstName, String lastName) {
		Optional<Users> user = Optional.fromNullable(usersName.get(firstName));
		if(user.isPresent() && user.get().lastName.equals(lastName)) {
			currentUser = user;
			return true;
		}else {
			return false;
		}
		
	}
	
	public void logout() {
		Optional<Users> user = currentUser;
		if(user.isPresent()) {
			currentUser = Optional.absent();
		}
	}

	

	// This method will read in data from the users.dat file and items file and
	// will create new users and movies
	public void initalLoad() throws IOException {
		String delims = "[|]";
		Scanner scanner = new Scanner(new File("./lib/users5.dat"));
		while (scanner.hasNextLine()) {
			String userDetails = scanner.nextLine();
			// parse user details string
			String[] userTokens = userDetails.split(delims);

			if (userTokens.length == 7) {
				createUser(userTokens[1], userTokens[2], userTokens[3], userTokens[4], userTokens[5]);
			} else {
				scanner.close();
				throw new IOException("Invalid member length: " + userTokens.length);
			}
		}

		scanner = new Scanner(new File("./lib/items5.dat"));
		while (scanner.hasNextLine()) {
			String movieDetails = scanner.nextLine();
			// parse user details string
			String[] movieTokens = movieDetails.split(delims);

			if (movieTokens.length == 23) {
				addMovies(movieTokens[1], movieTokens[2], movieTokens[3]);
			} else {
				scanner.close();
				throw new IOException("Invalid member length: " + movieTokens.length);
			}
		}
		scanner.close();

	}

	@SuppressWarnings("unchecked")
	@Override
	public void load() throws Exception {
		serializer.read();
		usersIndex = (Map<Long, Users>) serializer.pop();
		movieIndex = (Map<Long, Movies>) serializer.pop();
		usersName = (Map<String, Users>) serializer.pop();
	}

	@Override
	public void store() throws Exception {
		serializer.push(usersIndex);
		serializer.push(usersName);
		serializer.push(movieIndex);
		serializer.write();
	}

	// Return all users in file
	public Collection<Users> getUsers() {
		List<Entry<Long, Users>> user = new LinkedList<Entry<Long, Users>>(usersIndex.entrySet());
		return usersIndex.values();
	}

	// delete all users
	public void deleteUsers() {
		Users user = new Users();
		if(user.role== "admin") {
			usersIndex.clear();
			usersName.clear();
		}else {
			
		}
	}

	
	// create a new users using @Param firstname, lastname, age, gender, occupation  *hello*
	@Override
	public Users createUser(String firstName, String lastName, String age, String gender, String occupation) {
		Users user = new Users(firstName, lastName, age, gender, occupation);
		if(user.role== "admin") {
			usersIndex.put(user.id, user);
			usersName.put(firstName, user);
			return user;
		}else {
			return null;
		}
	}

	// Returns user information when searching for there first name
	@Override
	public Users getUserByName(String name) {
		return usersName.get(name);
	}

	// Retturns user by id
	@Override
	public Users getUserById(Long id) {
		return usersIndex.get(id);
	}

	// delete user by his id
	@Override
	public void deleteUser(Long id) {
		Users user = usersIndex.remove(id);
		usersName.remove(user.firstName);
	}

	// create movies using @Prama id title year, url
	// Movies
	@Override
	public Movies createMovie(Long id, String title, String year, String url) {
		Movies movie = null;
		Optional<Users> user = Optional.fromNullable(usersIndex.get(id));
		if (user.isPresent()) {
			movie = new Movies(title, year, url);
			user.get().movieObject.put(movie.id, movie);
			movieIndex.put(movie.id, movie);
		}
		return movie;
	}

	// create a movie without an id
	public void addMovies(String title, String year, String url) {
		Movies movie = new Movies(title, year, url);
		movieIndex.put(movie.id, movie);
		movieTitle.put(title, movie);
	}

	// gets a list of all movies in file
	@Override
	public Collection<Movies> getMovies() {
		return movieIndex.values();
	}

	// gets a movie by its id
	public Movies getMovie(Long id) {
		return movieIndex.get(id);
	}

	// Get a movie by its title details
	@Override
	public Movies getMovieByTitle(String title) {
		return movieTitle.get(title);
	}

	// add a rating to a movie
	@Override
	public void addRatings(Long id, Long userID, Long movieID, int rating) {
		Optional<Movies> movie = Optional.fromNullable(movieIndex.get(id));
		if (movie.isPresent()) {
			movie.get().theMovies.add(new Ratings(userID, movieID, rating));
		}
	}

}
