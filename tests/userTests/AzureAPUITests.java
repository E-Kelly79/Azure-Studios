package userTests;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.AzureFlimAPI;
import model.Users;

public class AzureAPUITests {

	private AzureFlimAPI azure;
	
	@Before
	public void setup() {
		azure = new AzureFlimAPI();
	}
	
	@After
	public void tearDown() {
		azure = null;
	}
	
	@Test
	public void testUser() {
		Users eoin = new Users("Eoin", "Kelly", 38, 'M', "Student");
		assertEquals(0, azure.getUsers().size());
		azure.createUser("Eoin", "Kelly", 38, 'M', "Student");
		assertEquals(1, azure.getUsers().size());
		assertEquals(eoin, azure.getUserByName("Eoin"));
	}
	
	public void testUserEmpty() {
		Users eoin = new Users("Eoin", "Kelly", 38, 'M', "Student");
		assertEquals(0, azure.getUsers().size());
		azure.createUser("Eoin", "Kelly", 38, 'M', "Student");
		assertEquals(1, azure.getUsers().size());
	}

}
