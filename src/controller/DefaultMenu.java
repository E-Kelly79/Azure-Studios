package controller;

import com.google.common.base.Optional;

import asg.cliche.Command;
import asg.cliche.Param;
import model.Movies;
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Command(description = "Get a Users detail")
	public void getUser(@Param(name = "name") String firstName) {
		Users user = azureAPI.getUserByName(firstName);
	    System.out.println(user);
	}
	
	@Command(description = "Add a movie")
	public void addActivity(@Param(name = "title") String title, @Param(name = "Year") String year, @Param(name = "url") String url) {
		azureAPI.createMovie(user.id, title, year, url);
	}
	@Command(description = "Add ratings to a movie")
	public void addLocation(@Param(name = "activity-id") Long id, @Param(name = "userId") Long userId, @Param(name = "movieId") Long movieId, @Param(name = "rating") int rating) {
		Optional<Movies> movie = Optional.fromNullable(azureAPI.getMovie(id));
	    if (movie.isPresent()) {
	      azureAPI.addRatings(movie.get().id, userId, movieId, rating);
	    }
	}
}
