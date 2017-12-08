package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.google.common.base.Optional;

import asg.cliche.Command;
import asg.cliche.Param;
import model.Movies;
import model.Ratings;
import model.Users;

public class DefaultMenu {
	private String name;
	private Users user;
	private AzureFlimAPI azureAPI;

	public DefaultMenu(AzureFlimAPI azureAPI, Users user) {
		this.azureAPI = azureAPI;
		this.setName(user.firstName);
		this.user = user;
	}
	
	//Return a name
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/*=====================
	 * Users
	 ======================*/
	
	@Command(description = "Get all users sorted by there Name")
	public void getAllUsers() {
		TreeSet<Users> sortedUsers = new TreeSet<Users>();
		sortedUsers.addAll(azureAPI.getUsers());
		Iterator<Users> iter = sortedUsers.iterator();
		while(iter.hasNext()) {
			Users u = iter.next();
			System.out.println(u.firstName + " " + u.lastName);
		}
	}
	
	@Command(description = "Get a Users detail")
	public void getUser(@Param(name = "name") Long id) {
		Users user = azureAPI.getUserById(id);
	    System.out.println(user);
	}
	
	@Command(description = "Create a new User")
	public void createUser(@Param(name = "first name") String firstName, @Param(name = "last name") String lastName,
	@Param(name = "age") int age, @Param(name = "gender") String gender, @Param(name = "occupation") String occupation) {
		azureAPI.createUser(firstName, lastName, age, gender, occupation);
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
