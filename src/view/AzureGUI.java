package view;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import controller.AzureFlimAPI;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import model.Users;
import utils.Serializer;
import utils.XMLSerializer;

public class AzureGUI extends Application {
	public AzureFlimAPI azure;
	public Stage window;
	public BorderPane root;
	public Button addUser, cancel;

	public AzureGUI() throws Exception {
		File movies = new File("./lib/users6.xml");
		Serializer serializer = new XMLSerializer(movies);
		azure = new AzureFlimAPI(serializer);
		if (movies.isFile()) {
			azure.load();
		}
	}

	public String getUsers() {
		try {
			azure.initalLoad();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Collection<Users> users = azure.getUsers();
		String user = users.toString();
		return user;
	}

	public static void main(String[] args) {

		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		AzureGUI prog = new AzureGUI();
		root = new BorderPane();
		Scene scene = new Scene(root, 600, 400);
		window = primaryStage;
		window.setTitle("Azure Studios");

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(5));
		grid.setHgap(5);
		grid.setVgap(5);

		for (int i = 0; i < 5; i++) {
			ColumnConstraints column = new ColumnConstraints(100);
			grid.getColumnConstraints().add(column);
		}

		// column5.setHgrow(Priority.ALWAYS);
		// grid.getColumnConstraints().addAll(column1, column2,column3,column4,column5);

		Label firstName = new Label("First Name");
		TextField fName = new TextField();
		Label lastName = new Label("Last Name");
		TextField lName = new TextField();
		Label age = new Label("Age");
		TextField ageBox = new TextField();
		Label gender = new Label("Gender");
		TextField genderBox = new TextField();
		Label occupation = new Label("Occupation");
		TextField job = new TextField();

		GridPane.setHalignment(firstName, HPos.LEFT);
		grid.add(firstName, 0, 0);

		// Last name label
		GridPane.setHalignment(lastName, HPos.LEFT);
		grid.add(lastName, 0, 1);

		// Age label
		GridPane.setHalignment(age, HPos.LEFT);
		grid.add(age, 0, 2);

		// Gender label
		GridPane.setHalignment(gender, HPos.LEFT);
		grid.add(gender, 0, 3);

		// Occupation label
		GridPane.setHalignment(occupation, HPos.LEFT);
		grid.add(occupation, 0, 4);

		// First name field
		GridPane.setHalignment(fName, HPos.LEFT);
		grid.add(fName, 1, 0);

		// Last name field
		GridPane.setHalignment(lName, HPos.LEFT);
		grid.add(lName, 1, 1);

		// Age Field
		GridPane.setHalignment(ageBox, HPos.LEFT);
		grid.add(ageBox, 1, 2);

		// Gender Field
		GridPane.setHalignment(genderBox, HPos.LEFT);
		grid.add(genderBox, 1, 3);

		// Occupation Field
		GridPane.setHalignment(job, HPos.LEFT);
		grid.add(job, 1, 4);

		// Buttons
		addUser = new Button("Add User");
		cancel = new Button("Cancel");
		// add user button
		GridPane.setHalignment(addUser, HPos.RIGHT);
		grid.add(addUser, 0, 5);

		GridPane.setHalignment(cancel, HPos.RIGHT);
		grid.add(cancel, 1, 5);

		// File Menu
		Menu fileMenu = new Menu("File");

		// User menu
		Menu userMenu = new Menu("User");

		// Movies Menu
		Menu movieMenu = new Menu("Movie");

		// fileMenu items
		fileMenu.getItems().add(new MenuItem("Load Files"));
		fileMenu.getItems().add(new MenuItem("Read Files"));
		fileMenu.getItems().add(new MenuItem("Save Files"));

		HBox text = new HBox();
		TextArea t = new TextArea();
		t.setMaxSize(300, 300);
		t.setEditable(false);
		t.setWrapText(true);

		// userMenu items

		MenuItem listUser = new MenuItem("List All Users");
		listUser.setOnAction(e -> t.setText(prog.getUsers()));
		userMenu.getItems().add(listUser);
		userMenu.getItems().add(new MenuItem("Get User by Name"));
		userMenu.getItems().add(new MenuItem("Delete A User"));

		// movieMenu items
		movieMenu.getItems().add(new MenuItem("List Movies"));
		movieMenu.getItems().add(new MenuItem("Delete Movie"));
		movieMenu.getItems().add(new MenuItem("Get Movie by id"));

		// Main Menu Bar
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(fileMenu, userMenu, movieMenu);

		text.getChildren().add(t);

		root.setTop(menuBar);
		root.setLeft(text);
		root.setCenter(grid);

		window.setScene(scene);
		window.show();

	}

}
