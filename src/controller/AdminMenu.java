package controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import com.google.common.base.Optional;

import asg.cliche.Command;
import asg.cliche.Param;
import model.Movies;
import model.Ratings;
import model.Users;
import utils.ComparatorByID;

public class AdminMenu {
	private String name;
	private AzureFlimAPI azureAPI;

	public AdminMenu(AzureFlimAPI azureAPI, String name) {
		this.azureAPI = azureAPI;
		this.name = name;
	}
	
	//retuns name
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Command(description = "Get all users details", abbrev="getUsers")
	public void getUsers() {
		azureAPI.getUsers();
		
	}

	@Command(description = "Create a new User", abbrev="addu")
	public void createUser(@Param(name = "first name") String firstName, @Param(name = "last name") String lastName,
	@Param(name = "age") int age, @Param(name = "gender") String gender, @Param(name = "occupation") String occupation) {
		azureAPI.createUser(firstName, lastName, age, gender, occupation);
	}

	@Command(description = "Get a Users detail", abbrev="getu")
	public void getUser(@Param(name = "name") String firstName) {
		Users user = azureAPI.getUserByName(firstName);
		System.out.println(user);
	}

	@Command(description = "Delete a User", abbrev="deluser")
	public void deleteUser(@Param(name = "name") String name) {
		Optional<Users> user = Optional.fromNullable(azureAPI.getUserByName(name));
		if (user.isPresent()) {
			azureAPI.deleteUser(user.get().id);
		}
	}

	@Command(description = "Add a movie", abbrev="addm")
	public void addMovie(@Param(name = "id") long id, @Param(name = "title") String title,
	@Param(name = "year") String year, @Param(name = "url") String url) {
		azureAPI.addMovies(title, year, url);
	}
	

	@Command(description = "Add ratings to a movie" , abbrev="addR")
	public void addRating(@Param(name = "movie-id") Long id, @Param(name = "userId") Long userId,
	@Param(name = "movieId") Long movieId, @Param(name = "rating") int rating ) {
		Optional<Movies> movie = Optional.fromNullable(azureAPI.getMovie(id));
		if (movie.isPresent()) {
			azureAPI.addRatings(userId, movieId, rating);
		}
	}
	
	/*=====================
	 * Users
	 ======================*/
	
	@Command(description = "Get all users sorted by there Name", abbrev="getUsers")
	public void getAllUsers() {
		TreeSet<Users> sortedUsers = new TreeSet<Users>();
		sortedUsers.addAll(azureAPI.getUsers());
		Iterator<Users> iter = sortedUsers.iterator();
		while(iter.hasNext()) {
			Users u = iter.next();
			System.out.println(u.firstName + " " + u.lastName);
		}
	}
	
	@Command(description = "search a user by name")
	public void getUserByName(String name) {
		ArrayList<Users> users = new ArrayList<Users>();
		users.addAll(azureAPI.getUsers());
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).firstName.toLowerCase().contains(name.toLowerCase())) {
				System.out.println(users.get(i));
			}
		}
	}
	
	@Command(description = "Get top 10 movies", abbrev="t10")
	public void Top10Movies()
	{
		List<Movies> list = new ArrayList<Movies>(azureAPI.movieIndex.values());
		Collections.sort(list, new ComparatorByID().reversed());
		Iterator<Movies> iter = list.iterator();
		while (iter.hasNext()) {
			Movies s = iter.next();
			System.out.println(s.title + "  " + (s.ratingSystem / s.theMoviesRatings.size()));
	}
	}
	
	@Command(description = "Get a Users detail", abbrev="gubid")
	public void getUser(@Param(name = "name") Long id) {
		Users user = azureAPI.getUserById(id);
	    System.out.println(user);
	}
	
	@Command(description="Delete a User", abbrev="dubid")
	public void deleteUser(@Param(name="User ID")long id){
		azureAPI.deleteUser(id);
	}
	
	
	/*=====================
	 * Movies
	 ======================*/
	
	@Command(description="Get a List of all movies sorted by there title")
	public void getMovies(){
		TreeSet<Movies> sortedMovies = new TreeSet<Movies>();
		sortedMovies.addAll(azureAPI.getMovies());
		Iterator<Movies> iter = sortedMovies.iterator();
		while(iter.hasNext()) {
			Movies u = iter.next();
			System.out.println(u.title);
		}
	}
	
	
	@Command(description="Get a Movie by its ID")
	public Movies getMovie(@Param(name="Movie Id") Long id){
		return azureAPI.getMovie(id);
	}
	
	
	/*=====================
	 * Ratings
	 ======================*/
	@Command(description = "Add a rating")
	public void addRating(@Param(name = "userId") Long userId, @Param(name = "movieId") Long movieId, @Param(name = "rating") int rating) {
		 azureAPI.addRatings(userId, movieId, rating);
	}
	
	@Command(description="Get User Ratings")
	public Map<Long, Ratings> getUserRating(@Param(name="User ID")long id){
		return azureAPI.getUserRating(id);
	}
	
	@Command(description="Get Movies Ratings")
	public Map<Long,Ratings> getMovieRating(@Param(name="Movie Id")long id)
	{
		return azureAPI.getMovieRating(id);
	}
	
	@Command(description="Return a Rating")
	public Ratings getRating(@Param(name="Rating Id")long id){
		return azureAPI.getRating(id);
	}
	
	@Command(description="Get All Ratings")
	public void getRatings(){
		azureAPI.getRatings();
	}
	
	@Command(description="Delete a Rating")
	public void deleteRating(@Param(name="Rating Id")long id){
//		 Users user = azureAPI.currentUser.get();
//		if(user.ge  != id) {
//			System.out.println("You can not delete another person rating");
//		}else {
		azureAPI.deleteRating(id);
		//}
	}
	

}
