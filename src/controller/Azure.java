package controller;

import java.io.IOException;
import java.util.Collection;

import model.Users;
import utils.FileLogger;

public class Azure {

	public static void main(String[] args) throws IOException {
		AzureFlimAPI azure = new AzureFlimAPI();
		
		azure.createUser("Eoin", "Kelly", 38, 'M', "Student");
		azure.createUser("Emma", "Martin", 31, 'F', "Student");
		
		
		
		FileLogger logger = FileLogger.getLogger();
		logger.log("Creating User List");
		
		Collection<Users> users = azure.getUsers();
		System.out.println(users);
		
		Users eoin = azure.getUser((long) 1);
		System.out.println(eoin);
		
		azure.deleteUser(eoin.id);
		users = azure.getUsers();
		System.out.println(users);
		
		logger.log("Serializing contacts to XML");
//		XStream xstream = new XStream(new DomDriver());
//		ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("./lib/users.xml"));
//	    out.writeObject(users);
//	    out.close();    
		
		
		logger.log("Finished - shutting Down");

	}

}
