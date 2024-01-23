package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

public class MazeRunner {
    private Maze maze;

    public MazeRunner(Maze maze) {
        this.maze = maze;
    }

    public String run() {
        // main logic
        Path path = findPath();
        return path.toFactorizedForm(); 
    }

    private Path findPath() {
        // path finding
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
            // convert to fact
            return "";
        }
    }
}
