package model;
/*
 * Author: Eoin Kelly 
 * Student No: 20074820
 * Date: 03/11/17
 */
import java.util.HashMap;
import java.util.Map;
import com.google.common.base.Objects;
import static com.google.common.base.MoreObjects.toStringHelper;

public class Users {
	static Long counter = 01L;
	public Long id;
	public String firstName;
	public String lastName;
	public String age;
	public String gender;
	public String occupation;

	public Map<Long, Movies> movieObject = new HashMap<>();

	public Users() {
	}

	public Users(String firstName, String lastName, String age, String gender, String occupation) {
		this.id = counter++;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.occupation = occupation;
	}

	@Override
	public String toString() {
		return toStringHelper(this).addValue(id).addValue(firstName).addValue(lastName).addValue(age).addValue(gender)
				.addValue(occupation +"\n").toString() ;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.firstName, this.lastName, this.age, this.gender, this.occupation);
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof Users) {
			final Users other = (Users) obj;
			return Objects.equal(firstName, other.firstName) 
					&& Objects.equal(lastName, other.lastName)
					&& Objects.equal(age, other.age) 
					&& Objects.equal(gender, other.gender)
					&& Objects.equal(occupation, other.occupation);
		} else {
			return false;
		}
	}

}
