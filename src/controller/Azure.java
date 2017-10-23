package controller;

import java.util.ArrayList;
import java.util.List;

import model.Users;

public class Azure {

	public static void main(String[] args) {
		List<Users> users = new ArrayList<>();
		users.add(new Users("Eoin", "Kelly", 38, 'M', "Student"));
		System.out.println(users);

	}

}
