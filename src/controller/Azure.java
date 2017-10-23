package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import model.Users;
import utils.FileLogger;

public class Azure {

	public static void main(String[] args) throws IOException {
		FileLogger logger = FileLogger.getLogger();
		logger.log("Creating User List");
		
		List<Users> users = new ArrayList<>();
		users.add(new Users("Eoin", "Kelly", 38, 'M', "Student"));
		System.out.println(users);
		
		logger.log("Serializing contacts to XML");
		XStream xstream = new XStream(new DomDriver());
		ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("./lib/users.xml"));
	    out.writeObject(users);
	    out.close();    
		
		
		logger.log("Finished - shutting Down");

	}

}
