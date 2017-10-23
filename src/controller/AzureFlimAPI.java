package controller;

import java.util.ArrayList;
import java.util.List;

import model.Users;

public class AzureFlimAPI {
	private List<Users> users = new ArrayList<>();
	
	public List<Users> getUsers(){
		return users;
	}
	
	public void deleteUsers() {
		users.clear();
	}
	
	public Users createUser(String firstName, String lastName, int age, char gender, String occupation) {
		Users user = new Users(firstName, lastName, age, gender, occupation);
		users.add(user);
		return user;
	}
	
	public Users getUser(String name) {
		for(Users user: users) {
			if(name.equalsIgnoreCase(user.firstName) || name.equalsIgnoreCase(user.lastName)) {
				return user;
			}
		}
		return null;
	}
	
	public void deleteUser(String name) {
		Users foundUser = null;
		for(Users user: users) {
			if(name.equalsIgnoreCase(user.firstName) || name.equalsIgnoreCase(user.lastName)) {
				foundUser = user;
			}
			if(foundUser != null) {
				users.remove(foundUser);
			}
		}
	}
}
