package userTests;

import static model.Fixtures.movies;
import static model.Fixtures.users;
import static model.Fixtures.rating;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.AzureFlimAPI;
import model.Users;

public class AzureAPUITests {

	private AzureFlimAPI azure;
	private Users eoin = new Users();

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
	public void addUser() {
		assertEquals(users.length, azure.getUsers().size());
		azure.createUser("Emma", "Martin", 32, "M", "Student");
//		assertEquals(users.length , azure.getUsers().size());
//		assertEquals(users[0], azure.getUserById(users[0].id));
	}

	@Test
	public void testUsers() {
		for(Users user : users) {
			azure.createUser(user.firstName, user.lastName, user.age, user.gender, user.occupation);
		}
		 assertEquals (users.length +2, azure.getUsers().size());
		
	}

	public void testUserEmpty() {
		eoin = new Users("Eoin", "Kelly", 38, "M", "Student");
		assertEquals(0, azure.getUsers().size());
		azure.createUser("Eoin", "Kelly", 38, "M", "Student");
		assertEquals(1, azure.getUsers().size());
	}

	@Test
	public void testDeleteUsers() {
	for(Users user: users) {
		azure.createUser(user.firstName, user.lastName, user.age, user.gender, user.occupation);
	}
		assertEquals(users.length +1, azure.getUsers().size() -1);
		eoin = azure.getUserById(2l);
		azure.deleteUser(2l);
		assertEquals(users.length +1, azure.getUsers().size() -1);
	}

	@Test
	public void testAddMovie() {
		azure.addMovies("Gonnies", "2017", "Saw.com");
		azure.addMovies("Saw", "2017", "Saw.com");
		assertEquals(movies.length, azure.getMovies().size());
		//assertEquals(movies[0], azure.getMovieByTitle(movies[0].title));
		
	}
	
	@Test
	public void testUserLogin() {
		//Checking with admin eoin login
		assertTrue(azure.login(users[0].id, users[0].lastName));
		assertEquals(azure.currentUser.get(), users[0]);
		
//		//check logout
//		azure.logout();
//		assertEquals(azure.currentUser, Optional.absent());
//		
//		//check login fail
//		assertFalse(azure.login(users[0].id, "paddy"));
//		assertEquals(azure.currentUser, Optional.absent());
		
	}

}
