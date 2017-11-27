package userTests;

import static model.Fixtures.movies;
import static model.Fixtures.users;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Optional;

import controller.AzureFlimAPI;
import model.Movies;
import model.Users;

public class AzureAPUITests {

	private AzureFlimAPI azure;

	@Before
	public void setup() {
		azure = new AzureFlimAPI();
		for (Users user : users) {
			azure.createUser(user.firstName, user.lastName, user.age, user.gender, user.occupation);
		}
		
		for (Movies movie : movies) {
			azure.createMovie(movie.id, movie.title, movie.year, movie.url);
		}
	}

	@After
	public void tearDown() {
		azure = null;
	}

	@Test
	public void addUser() {
		assertEquals(users.length, azure.getUsers().size());
		azure.createUser("Eoin", "Kelly", "38", "M", "Student");
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
		Users eoin = new Users("Eoin", "Kelly", "38", "M", "Student");
		assertEquals(0, azure.getUsers().size());
		azure.createUser("Eoin", "Kelly", "38", "M", "Student");
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
		assertEquals(movies.length, azure.getMovies().size());
		azure.createMovie(1L, "Saw", "2017", "Saw.com");
		assertEquals(movies.length + 1, azure.getMovies().size());
		assertEquals(movies[0], azure.getMovieByTitle(movies[0].title));
		
	}
	
	@Test
	public void testUserLogin() {
		//Checking with admin eoin login
		assertTrue(azure.login(users[0].firstName, users[0].lastName));
		assertEquals(azure.currentUser.get(), users[0]);
		
		//check logout
		azure.logout();
		assertEquals(azure.currentUser, Optional.absent());
		
		//check login fail
		assertFalse(azure.login(users[0].firstName, "paddy"));
		assertEquals(azure.currentUser, Optional.absent());
		
	}

}
