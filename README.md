# Azure Flim
Name: Eoin Kelly

## Overview.
This assignment was give to us so that we could build a movie recommender in java with some data stractures


## Functionality
 . . . . . List of the functional features implemented . . . .

 + Get top ten movies
 + Recommend a movie
 + Sort
 + Search
 + List all movies
 + List all users
 + List all ratings

## Installation requirements
. . . .  List of software, libraries and tools used (hint: everything on your build path libraries ) . . . . . . .
* [Xstream](http://x-stream.github.io/) - Used for reading and writing files
* [Gauva](https://github.com/google/guava) - Helpers for string methods
* [cliche](https://sourceforge.net/projects/cliche/) - Used to make commands in the command line

## Getting started
If you would like to use this program please downlaod the files as a zip then import them into eclipse. Once in eclipse you can run the program from the Azure.java class and log in. Example on how to log in is below

```
The Cliche Movie Shell
Enter ?list to list available commands.
Movies> ?list
abbrev	name	params
li	log-in	(user name, password)
Azure> li 5 Parker
Admin
Azure/Jenna>
```

## Examples

. . . . Examples of program's user interface (e.g. CLI)  (see example below) with appropriate captions ....
- Get top ten movies
```
Azure/Jenna> t10
Toy Story (1995), 01-Jan-1995, http://us.imdb.com/M/title-exact?Toy%20Story%20(1995), 3}
Babe (1995), 01-Jan-1995, http://us.imdb.com/M/title-exact?Babe%20(1995), 3}
Richard III (1995), 22-Jan-1996, http://us.imdb.com/M/title-exact?Richard%20III%20(1995), 2}
Four Rooms (1995), 01-Jan-1995, http://us.imdb.com/M/title-exact?Four%20Rooms%20(1995), 1}
Shanghai Triad (Yao a yao yao dao waipo qiao) (1995), 01-Jan-1995, http://us.imdb.com/Title?Yao+a+yao+yao+dao+waipo+qiao+(1995), 1}
Twelve Monkeys (1995), 01-Jan-1995, http://us.imdb.com/M/title-exact?Twelve%20Monkeys%20(1995), 1}
GoldenEye (1995), 01-Jan-1995, http://us.imdb.com/M/title-exact?GoldenEye%20(1995), 0}
Get Shorty (1995), 01-Jan-1995, http://us.imdb.com/M/title-exact?Get%20Shorty%20(1995), -1}
Dead Man Walking (1995), 01-Jan-1995, http://us.imdb.com/M/title-exact?Dead%20Man%20Walking%20(1995), -2}
Copycat (1995), 01-Jan-1995, http://us.imdb.com/M/title-exact?Copycat%20(1995), -3}
```

- Search for a user by name:
```
azure/Jenna> gubn G
George
Gregory
azure/Jenna>
```

## Versioning
This is the final version of my assignment

## Authors

* **Eoin Kelly**  - [Eoin Kelly](https://github.com/Timmo2000)



