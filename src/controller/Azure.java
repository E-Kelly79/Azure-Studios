package controller;

import java.util.ArrayList;
import java.util.List;

import model.Users;
import utils.FileLogger;

public class Azure {

	public static void main(String[] args) {
		FileLogger logger = FileLogger.getLogger();
		logger.log("Creating User List");
		
		List<Users> users = new ArrayList<>();
		users.add(new Users("Eoin", "Kelly", 38, 'M', "Student"));
		System.out.println(users);
		
		logger.log("Finished - shutting Down");

	}

}
