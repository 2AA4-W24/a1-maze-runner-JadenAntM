package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Maze {
    private char[][] mazeArray; // 2D array

    public Maze(String inputFile) {
        readMazeFromFile(inputFile);
    }

    public char[][] getMazeArr() {
        return mazeArray;
    }

    private void readMazeFromFile(String inputFile) {
        //List to store the maze
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            //reads the lines of maze and store in array
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            //starts maze array based on size
            mazeArray = new char[lines.size()][];
            for (int i = 0; i < lines.size(); i++) {
                mazeArray[i] = lines.get(i).toCharArray();
            }
            //catches arrer if error in reading file
        } catch (IOException e) {
            System.err.println("error reading file: " + e.getMessage());
        }
    }

    public int[] findEntryPoint() {
        //checks for entry point
        for (int i = 0; i < mazeArray.length; i++) {
            if (mazeArray[i][0] == ' ') {
                return new int[]{0, i};
            }
        }
        return null;
    }

    public int[] findExitPoint() {
        //checks for exit point
        for (int i = 0; i < mazeArray.length; i++) {
            int lastColumnIndex = mazeArray[i].length - 1;
            if (lastColumnIndex >= 0 && mazeArray[i][lastColumnIndex] == ' ') {
                System.out.println("The exit point has been found at: [" + lastColumnIndex + ", " + i + "]");
                return new int[]{lastColumnIndex, i};
            }
        }
        System.err.println("exit point has not been found");
        return null;
    }
}
