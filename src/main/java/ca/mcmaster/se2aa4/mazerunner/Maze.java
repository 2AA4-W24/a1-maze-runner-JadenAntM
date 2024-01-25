package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Maze {
    private static final Logger logger = LogManager.getLogger(Maze.class);
    private char[][] mazeArray; // 2D array

    public Maze(String inputFile) {
        readMazeFromFile(inputFile);
    }

    private void readMazeFromFile(String inputFile) {
        // List to store the maze
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            // read the lines of maze and store in list
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            // starts maze array based on size
            mazeArray = new char[lines.size()][];
            for (int i = 0; i < lines.size(); i++) {
                // stores elements as a row
                mazeArray[i] = lines.get(i).toCharArray();
            }
        } catch (IOException e) {
            logger.error("error reading file", e);
        }
    }

    public Point findEntryPoint() {
        //checks teh left side of the maze
        for (int i = 0; i < mazeArray.length; i++) {
            if (mazeArray[i][0] == ' ') {
                return new Point(0, i);
            }
        }
        return null;
    }

    public Point findExitPoint() {
        //checks right side of the maze
        for (int i = 0; i < mazeArray.length; i++) {
            int lastColumnIndex = mazeArray[i].length - 1;
            if (mazeArray[i][lastColumnIndex] == ' ') {
                return new Point(lastColumnIndex, i);
            }
        }
        return null;
    }

    public static class Point {
        // point representation
        public final int x;
        public final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
}
