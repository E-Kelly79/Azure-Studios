package controller;

import java.io.File;
import java.util.Collection;

import model.Users;
import utils.Serializer;
import utils.XMLSerializer;

public class Azure {
	public AzureFlimAPI azure;
	
	public Azure() throws Exception {
		File movies = new File("./lib/users5.xml");
		Serializer serializer = new XMLSerializer(movies);
		azure = new AzureFlimAPI(serializer);
		if (movies.isFile()) {
			azure.load();
		}
	}

	public static void main(String[] args) throws Exception {
		Azure main = new Azure();

		main.azure.initalLoad();
//		main.azure.createUser("Eoin", "Kelly", "38", "M", "Student");
//		main.azure.createUser("Emma", "Martin", "31", "F", "Student");
//		main.azure.createUser("Keith", "Maher", "31", "M", "Programmer");

		Collection<Users> users = main.azure.getUsers();
		System.out.println(users);

		Users eoin = main.azure.getUserByName("Emma");
		//main.azure.createMovie(eoin.id, "Grilmin", "1993", "Grimlock.com");

		main.azure.store();

	}

}
