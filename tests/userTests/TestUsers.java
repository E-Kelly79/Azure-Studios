package userTests;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import model.Users;
import model.Fixtures;

public class TestUsers {
	private  Users[] users = {
			new Users("Eoin", "Kelly", 38, 'M', "Student"),
			new Users("Emma", "Martin", 32, 'F', "Student")
	};

	Users eoin = new Users("Eoin", "Kelly", 38, 'M', "Student");
	
	
	@Test
	public void testCreate() {
		
		assertEquals("Eoin", eoin.firstName);
		assertEquals("Kelly", eoin.lastName);
		assertEquals(38, eoin.age);
		assertEquals('M', eoin.gender);
		assertEquals("Student", eoin.occupation);
	}
	
	@Test
	  public void testIds()
	  {
	    Set<Long> ids = new HashSet<>();
	    for (Users user : users)
	    {
	      ids.add(user.id);
	    }
	    assertEquals (users.length, ids.size());
	  }
	
	@Test
	public void testToString() {
		assertEquals("Users{" + eoin.id + ", Eoin, Kelly, 38, M, Student}", eoin.toString());
	}
	
	

}
