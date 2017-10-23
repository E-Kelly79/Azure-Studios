package model;

import static com.google.common.base.MoreObjects.toStringHelper;

public class Users {
	public String firstName;
	public String lastName;
	public int age;
	public char gender;
	public String occupation;
	
	public Users() {
		
	}
	
	public Users(String firstName, String lastName, int age, char gender, String occupation) {
		this.firstName 	= firstName;
		this.lastName 	= lastName;
		this.age		= age;
		this.gender		= gender;
		this.occupation	= occupation;
	}
	
	public String toString() {
		return toStringHelper(this).addValue(firstName)
								   .addValue(lastName)
								   .addValue(age)
								   .addValue(gender)
								   .addValue(occupation)
								   .toString();
	}
	
	
}
