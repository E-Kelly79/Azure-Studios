package model;

import static com.google.common.base.MoreObjects.toStringHelper;
import com.google.common.base.Objects;


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
	
	@Override
	public String toString() {
		return toStringHelper(this).addValue(firstName)
								   .addValue(lastName)
								   .addValue(age)
								   .addValue(gender)
								   .addValue(occupation)
								   .toString();
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(this.firstName, this.lastName, this.age, this.gender, this.occupation);
	}
	
	
}
