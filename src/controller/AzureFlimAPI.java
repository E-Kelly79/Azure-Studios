package controller;
/*
 * Author Eoin Kelly 

 * Date 03/11/17
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.google.common.base.Optional;
import model.Movies;
import model.Ratings;
import model.Users;
import utils.Serializer;

public class AzureFlimAPI implements azureInterface {

	private Serializer serializer;
	public Map<Long, Users> usersIndex = new HashMap<>();
	private Map<String, Users> usersName = new HashMap<>();
	public Map<Long, Movies> movieIndex = new HashMap<>();
	private Map<Long, Ratings> ratingsIndex = new HashMap<>();
	private Map<String, Movies> movieTitle = new HashMap<>();
	public Optional<Users> currentUser;

	/*
	 * ========================== Constructors =========================
	 */
	public AzureFlimAPI() {

	}

	public AzureFlimAPI(Serializer serializer) {
		this.serializer = serializer;
	}

	/*
	 * ============================ Utils ===========================
	 */
	// Login a user
	public boolean login(Long userId, String lastName) {
		Optional<Users> user = Optional.fromNullable(usersIndex.get(userId));
		if (user.isPresent() && user.get().lastName.equals(lastName)) {
			currentUser = user;
			return true;
		} else {
			return false;
		}

	}

	// Logout a user
	public void logout() {
		Optional<Users> user = currentUser;
		if (user.isPresent()) {
			currentUser = Optional.absent();
		}
	}

	// This method will read in data from the users.dat file and items file and
	// will create new users and movies
	public void initalLoad() throws IOException {
		String delims = "[|]";
		Scanner scanner = new Scanner(new File("./files/users5.dat"));
		while (scanner.hasNextLine()) {
			String userDetails = scanner.nextLine();
			// parse user details string
			String[] userTokens = userDetails.split(delims);

			if (userTokens.length == 7) {
				createUser(userTokens[1], userTokens[2], Integer.valueOf(userTokens[3]), userTokens[4], userTokens[5]);
			} else {
				scanner.close();
				throw new IOException("Invalid member length: " + userTokens.length);
			}
		}

		scanner = new Scanner(new File("./files/items5.dat"));
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

		scanner = new Scanner(new File("./files/ratings5.dat"));
		while (scanner.hasNextLine()) {
			String userDetails2 = scanner.nextLine();
			// parse user details string
			String[] userTokens2 = userDetails2.split(delims);
			if (userTokens2.length == 4) {
				addRatings(Long.valueOf(userTokens2[0]), Long.valueOf(userTokens2[1]), Integer.valueOf(userTokens2[2]));
			} else {
				scanner.close();
				throw new IOException("Invalid member length: " + userTokens2.length);
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
		ratingsIndex = (Map<Long, Ratings>) serializer.pop();
		// usersName = (Map<String, Users>) serializer.pop();
	}

	@Override
	public void store() throws Exception {
		serializer.push(ratingsIndex);
		serializer.push(movieIndex);
		serializer.push(usersIndex);
		// serializer.push(usersName);
		serializer.write();
	}

	/*
	 * ===================== User Methods ===================
	 */

	// Return all users in file
	public Collection<Users> getUsers() {
		return usersIndex.values();
	}

	// delete all users
	public void deleteUsers() {
		usersIndex.clear();
		usersName.clear();
	}

	// create a new users using @Param firstname, lastname, age, gender, occupation
	// *hello*
	@Override
	public void createUser(String firstName, String lastName, int age, String gender, String occupation) {
		Users user = new Users(firstName, lastName, age, gender, occupation);
		usersIndex.put(user.id, user);
		// return user;
	}

	// Returns user information when searching for there first name
	@Override
	public Users getUserByName(String name) {
		return usersName.get(name);
	}

	// Returns user by id
	@Override
	public Users getUserById(Long id) {
		return usersIndex.get(id);
	}

	// delete user by his id
	@Override
	public void deleteUser(Long id) {
		usersIndex.remove(id);
	}

	/*
	 * ========================== Movie Methods =======================
	 */

	// create a movie without an id
	public void addMovies(String title, String year, String url) {
		Movies movie = new Movies(title, year, url);
		movieIndex.put(movie.id, movie);
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

	public void deleteMovie(Long id) {
		movieIndex.remove(id);
	}

	// Get a movie by its title details
	@Override
	public Movies getMovieByTitle(String title) {
		return movieTitle.get(title);
	}

	/*
	 * ======================== Rating Methods ======================
	 */

	@Override
	public void addRatings(Long userID, Long movieID, int rating) {
		Ratings ratings;
		Optional<Users> user = Optional.fromNullable(usersIndex.get(userID));
		Optional<Movies> movie = Optional.fromNullable(movieIndex.get(movieID));
		if (movie.isPresent() && user.isPresent()) {
			ratings = new Ratings(userID, movieID, rating); // add new rating
			user.get().userRatings.put(ratings.id, ratings); // attach user to a rating
			movie.get().theMoviesRatings.put(ratings.id, ratings); // attach a movie to a rating
			ratingsIndex.put(ratings.id, ratings); // add rating to a collection

			movie.get().ratingSystem = movie.get().ratingSystem + rating;

		}
	}

	public Collection<Ratings> getRatings() {
		return ratingsIndex.values();
	}

	public Ratings getRating(long id) {
		return ratingsIndex.get(id);
	}

	public Map<Long, Ratings> getUserRating(long id) {
		Optional<Users> user = Optional.fromNullable(usersIndex.get(id));
		return user.get().userRatings;
	}

	public Map<Long, Ratings> getMovieRating(long id) {
		Optional<Movies> movie = Optional.fromNullable(movieIndex.get(id));
		return movie.get().theMoviesRatings;

	}

	public void deleteRating(long id) {
		ratingsIndex.remove(id);
	}

	public void calculateAvg() {
		ArrayList<Movies> ratings = new ArrayList<Movies>();
		ratings.addAll(getMovies());
		for (int i = 0; i < ratings.size(); i++) {
			if (ratings.get(i).ratingSystem > 0.0) {
				System.out.println(ratings.get(i).title + " " + ratings.get(i).ratingSystem);
			}
		}

	}

	public void recommender(long id) {
		Map<Long, Movies> movies = new HashMap<>();
		movies.putAll(movieIndex);
		Optional<Users> user = Optional.fromNullable(usersIndex.get(id));
		Set<Long> list;
		list = user.get().userRatings.keySet();
		for (Long ratings : list) {
			Ratings r1 = ratingsIndex.get(ratings);
			ratings = r1.movieId;
			movies.remove(ratings);
		}
		List<Movies> userMovies = new ArrayList<Movies>(movies.values());
		Collections.sort(userMovies, new Movies().reversed());
		for (Movies movie : userMovies) {
			System.out.println(movie.title);
		}
	}
}
