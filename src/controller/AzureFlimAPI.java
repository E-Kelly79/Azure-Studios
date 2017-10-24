package controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Optional;

import model.Movies;
import model.Ratings;
import model.Users;

public class AzureFlimAPI {
	private Map<Long, Users> usersIndex = new HashMap<>();
	private Map<String, Users> usersName = new HashMap<>();
	private Map<Long, Movies> movieIndex = new HashMap<>();
	
	
	public Collection<Users> getUsers(){
		return usersIndex.values();
	}
	
	public void deleteUsers() {
		usersIndex.clear();
		usersName.clear();
	}
	
	public Users createUser(String firstName, String lastName, int age, char gender, String occupation) {
		Users user = new Users(firstName, lastName, age, gender, occupation);
		usersIndex.put(user.id,user);
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
	
	public void createMovie(Long id, String title, String year, String url){
	    Movies movie = new Movies (title, year, url);
	    Optional<Users> user = Optional.fromNullable(usersIndex.get(id));
	    if (user.isPresent())
	    {
	      user.get().movieObject.put(movie.id, movie);
	      movieIndex.put(movie.id, movie);
	    }
	}
	
	public Movies getMovie(Long id) {
		return movieIndex.get(id);
	}
	
	public void addRatings(Long id, Long userID, Long movieID, int rating) {
		Optional<Movies> movie = Optional.fromNullable(movieIndex.get(id));
		if(movie.isPresent()) {
			movie.get().theMovies.add(new Ratings(userID, movieID, rating));
		}
	}
	
}
