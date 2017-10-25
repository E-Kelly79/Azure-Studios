package userTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.AzureFlimAPI;
import model.Movies;
import model.Users;

import static model.Fixtures.users;
import static model.Fixtures.movies;
import static model.Fixtures.rating;

public class AzureAPUITests {

	private AzureFlimAPI azure;

	@Before
	public void setup() {
		azure = new AzureFlimAPI();
		for (Users user : users) {
			azure.createUser(user.firstName, user.lastName, user.age, user.gender, user.occupation);
		}
	}

	@After
	public void tearDown() {
		azure = null;
	}

	@Test
	public void testUser() {
		assertEquals(users.length, azure.getUsers().size());
		azure.createUser("Eoin", "Kelly", 38, 'M', "Student");
		assertEquals(users.length + 1, azure.getUsers().size());
		assertEquals(users[0], azure.getUserByName(users[0].firstName));
	}

	@Test
	public void testUsers() {
		assertEquals(users.length, azure.getUsers().size());
		for (Users user : users) {
			Users eachUser = azure.getUserByName(user.firstName);
			assertEquals(user, eachUser);
			assertNotSame(user, eachUser);
		}
	}

	public void testUserEmpty() {
		Users eoin = new Users("Eoin", "Kelly", 38, 'M', "Student");
		assertEquals(0, azure.getUsers().size());
		azure.createUser("Eoin", "Kelly", 38, 'M', "Student");
		assertEquals(1, azure.getUsers().size());
	}

	@Test
	public void testDeleteUsers() {
		assertEquals(users.length, azure.getUsers().size());
		Users eoin = azure.getUserByName("Eoin");
		azure.deleteUser(eoin.id);
		assertEquals(users.length - 1, azure.getUsers().size());
	}
	
	@Test
	public void testAddMovie() {
		Users eoin = azure.getUserByName("Eoin");
		Movies movie = azure.createMovie(eoin.id, movies[0].title, movies[0].year,	movies[0].url);
		Movies returnedMovie = azure.getMovie(movie.id);
		assertEquals(movies[0], returnedMovie);
		assertNotSame(movies[0], returnedMovie);
	}

}
