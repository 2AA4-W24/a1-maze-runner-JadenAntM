# Assignment A1 - Maze Runner

## Information

  * **Student**: Jaden Moore 
  * **Program**: B. Eng. In Software Engineering
  * **Course code**: SFWRENG 2AA4
  * **Course Title**: Software Design I - Introduction to Software Development 

## Project Overview
Maze Runner is a Java-based application developed for the course SFWRENG 2AA4. The program is designed to explore mazes, finding paths from entry to exit points. The mazes are represented in text files, with specific symbols indicating walls and passageways.

### Business Logic Specification

This program explores a maze, finding a path from an entry point to an exit one.

- Maze files use # for walls and spaces for passages.
- Mazes are enclosed by walls except for entry and exit points, located on the East and West borders.
- The maze exploration algorithm generates a sequence of instructions:
  - F for moving forward
  - R for turning right
  - L for turning left
- Paths can be represented in canonical form (e.g., FFLFF) or factorized form (e.g., 2F L 2F).
- 
## How to run this software?

To build the program, simply package it with Maven:

```
directory % mvn -q clean package 
```
- Standard execution
```
java -jar target/mazerunner.jar -i examples/[MAZE_FILE]
```
Verify a path:
```
java -jar target/mazerunner.jar -i examples/[MAZE_FILE] -p [PATH_SEQUENCE]
```

### Provided version (starter code)

The starter code assumes the maze file name is the first argument. 

```
mosser@azrael A1-Template % java -jar target/mazerunner.jar ./examples/small.maz.txt
** Starting Maze Runner
**** Reading the maze from file ./examples/small.maz.txt
WALL WALL WALL WALL WALL WALL WALL WALL WALL WALL WALL 
WALL PASS PASS PASS PASS PASS PASS PASS PASS PASS WALL 
WALL WALL WALL PASS WALL WALL WALL PASS WALL WALL WALL 
WALL PASS PASS PASS PASS PASS WALL PASS PASS PASS WALL 
WALL PASS WALL PASS WALL WALL WALL WALL WALL PASS WALL 
WALL PASS WALL PASS PASS PASS PASS PASS WALL PASS PASS 
WALL WALL WALL PASS WALL PASS WALL WALL WALL WALL WALL 
WALL PASS PASS PASS WALL PASS PASS PASS PASS PASS WALL 
PASS PASS WALL PASS WALL PASS WALL WALL WALL PASS WALL 
WALL PASS WALL PASS WALL PASS WALL PASS PASS PASS WALL 
WALL WALL WALL WALL WALL WALL WALL WALL WALL WALL WALL 
**** Computing path
PATH NOT COMPUTED
** End of MazeRunner
```

When called on a non-existing file. it prints an error message

```
mosser@azrael A1-Template % java -jar target/mazerunner.jar ./examples/small.maz.txtd
** Starting Maze Runner
**** Reading the maze from file ./examples/small.maz.txtd
/!\ An error has occured /!\
**** Computing path
PATH NOT COMPUTED
** End of MazeRunner
```

### Delivered version

#### Command line arguments

The delivered program at the end of this assignment should use the following flags:

- `-i MAZE_FILE`: specifies the filename to be used;
- `-p PATH_SEQUENCE`: activates the path verification mode to validate that PATH_SEQUENCE is correct for the maze

If you are also delivering the bonus, your program will react to a third flag:

- `-method {tremaux, righthand}`: specifies which path computation method to use. (default is right hand)

#### Examples


