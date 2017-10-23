package controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import model.Users;

public class AzureFlimAPI {
	private Map<String, Users> users = new HashMap<>();
	
	public Collection<Users> getUsers(){
		return users.values();
	}
	
	public void deleteUsers() {
		users.clear();
	}
	
	public Users createUser(String firstName, String lastName, int age, char gender, String occupation) {
		Users user = new Users(firstName, lastName, age, gender, occupation);
		users.put(firstName,user);
		return user;
	}
	
	public Users getUser(String name) {
		return users.get(name);
	}
	
	public void deleteUser(String name) {
		users.remove(name);
	}
}
