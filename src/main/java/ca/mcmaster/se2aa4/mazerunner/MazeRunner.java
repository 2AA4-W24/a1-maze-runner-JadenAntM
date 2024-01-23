package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

public class MazeRunner {
    private Maze maze;

    public MazeRunner(String inputFile) {
        this.maze = new Maze(inputFile);
    }

    public String run() {
        // main logic
        Path path = findPath();
        return path.toFactorizedForm(); 
    }

    private Path findPath() {
        // Path finding logic
        return new Path();
    }

    private static class Path {
        // path representation
        private List<String> steps;

        public String toCanonicalForm() {
            // convert to canonical
            return "";
        }

        public String toFactorizedForm() {
            // Convert to factorized
            return "";
        }
    }
}
