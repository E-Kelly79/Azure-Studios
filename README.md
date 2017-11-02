# Azure Flim

This assignment was to make a movie recommender like the one netflix uses this is the first part of the assignment were we had to read in from a file then save that file as an xml file. With that done we then had to save new data to that xml file like new users and movies and also be able to look for a user by name or id and the same with movies


## Getting Started
To use this project please go and clone it form the clone and zip option provided by github then unzip the folder then import the project to you eclipse workspace


## Examples
```
to add a new user: au john doe 33 M job
to add a new movie am title year url
```


## Running Tests
The test that i ran for this assignment are  i test out how the create user, tostring method , add movies,  delete files find users 

### What these tests test and why
The first test i ran was the create users this needs to be tested to enable the user to be able to create new users to the application
if this test could not pass the the app would not have been viable to work 

```
Users eoin = new Users("Eoin", "Kelly", "38", "M", "Student");

	@Test
	public void testCreate() {
		assertEquals("Eoin", eoin.firstName);
		assertEquals("Kelly", eoin.lastName);
		assertEquals("38", eoin.age);
		assertEquals("M", eoin.gender);
		assertEquals("Student", eoin.occupation);
	}
```

## Built With

* [Xstream](http://x-stream.github.io/) - Used for reading and writing files
* [Gauva](https://github.com/google/guava) - Helpers for string methods
* [cliche](https://sourceforge.net/projects/cliche/) - Used to make commands in the command line

## Versioning
This is the first version of my assignment

## Authors

* **Eoin Kelly**  - [Eoin Kelly](https://github.com/Timmo2000)



