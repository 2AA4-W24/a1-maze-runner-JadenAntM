package ca.mcmaster.se2aa4.mazerunner;

public class Maze {
    // representation of maze

    public Maze(String inputFile) {
        // read maze
    }

    public Point findEntryPoint() {
        // find entry point
        return null;
    }

    public Point findExitPoint() {
        // find exit point
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
