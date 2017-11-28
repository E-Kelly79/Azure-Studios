package controller;

import java.util.Collection;

import com.google.common.base.Optional;

import asg.cliche.Command;
import asg.cliche.Param;
import model.Movies;
import model.Users;

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


	@Command(description = "Get all users details", abbrev="getU")
	public void getUsers() {
		Collection<Users> users = azureAPI.getUsers();
		System.out.println(users);
	}

	@Command(description = "Create a new User", abbrev="addu")
	public void createUser(@Param(name = "first name") String firstName, @Param(name = "last name") String lastName,
	@Param(name = "age") String age, @Param(name = "gender") String gender, @Param(name = "occupation") String occupation) {
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
			azureAPI.addRatings(movie.get().id, userId, movieId, rating);
		}
	}

}
