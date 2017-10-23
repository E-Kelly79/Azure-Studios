package controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import model.Users;

public class AzureFlimAPI {
	private Map<Long, Users> usersIndex = new HashMap<>();
	private Map<String, Users> usersName = new HashMap<>();
	
	
	public Collection<Users> getUsers(){
		return usersIndex.values();
	}
	
	public void deleteUsers() {
		usersIndex.clear();
		usersName.clear();
	}
	
	public Users createUser(String firstName, String lastName, int age, char gender, String occupation) {
		Users user = new Users(firstName, lastName, age, gender, occupation);
		usersIndex.put(user.id,user);
		usersName.put(firstName, user);
		return user;
	}
	
	public Users getUser(Long id) {
		return usersIndex.get(id);
	}
	
	public void deleteUser(Long id) {
		Users user = usersIndex.remove(id);
		usersName.remove(user.firstName);
	}
}
