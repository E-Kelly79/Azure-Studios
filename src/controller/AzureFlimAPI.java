package controller;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.google.common.base.Optional;

import model.Movies;
import model.Ratings;
import model.Users;
import utils.Serializer;

public class AzureFlimAPI {
	
	private Serializer serializer;
	private Map<Long, Users> usersIndex = new HashMap<>();
	private Map<String, Users> usersName = new HashMap<>();
	private Map<Long, Movies> movieIndex = new HashMap<>();

	public AzureFlimAPI() {

	}

	public AzureFlimAPI(Serializer serializer) {
		this.serializer = serializer;
	}

	public Collection<Users> getUsers() {
		return usersIndex.values();
	}

	public void deleteUsers() {
		usersIndex.clear();
		usersName.clear();
	}

	public Users createUser(String firstName, String lastName, String age, String gender, String occupation) {
		Users user = new Users(firstName, lastName, age, gender, occupation);
		usersIndex.put(user.id, user);
		usersName.put(firstName, user);
		return user;
	}

	public Users getUser(Long id) {
		return usersIndex.get(id);
	}

	public Users getUserByName(String name) {
		return usersName.get(name);
	}

	public void deleteUser(Long id) {
		Users user = usersIndex.remove(id);
		usersName.remove(user.firstName);
	}

	public Movies createMovie(Long id, String title, String year, String url) {
		Movies movie = new Movies(title, year, url);
		Optional<Users> user = Optional.fromNullable(usersIndex.get(id));
		if (user.isPresent()) {
			user.get().movieObject.put(movie.id, movie);
			movieIndex.put(movie.id, movie);
		}
		return movie;
	}

	public Movies getMovie(Long id) {
		return movieIndex.get(id);
	}

	public void addRatings(Long id, Long userID, Long movieID, int rating) {
		Optional<Movies> movie = Optional.fromNullable(movieIndex.get(id));
		if (movie.isPresent()) {
			movie.get().theMovies.add(new Ratings(userID, movieID, rating));
		}
	}
	
	
	public void load() throws Exception {
		serializer.read();
		usersIndex 		= (Map<Long, Users>) serializer.pop();
		movieIndex 		= (Map<Long, Movies>) serializer.pop();
		usersName 	= (Map<String, Users>) serializer.pop();
	}
	
	

	public void store() throws Exception {
		serializer.push(usersIndex);
		serializer.push(usersName);
		serializer.push(movieIndex);
		serializer.write(); 
	}
	
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
	        scanner.close();
	}

}
