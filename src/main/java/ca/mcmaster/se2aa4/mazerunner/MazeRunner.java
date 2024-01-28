package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;
import java.util.ArrayList;

public class MazeRunner {
    private Maze maze;

    public MazeRunner(Maze maze) {
        this.maze = maze;
    }

    public String run() {
        // main logic
        Path path = findPath();
        //return FormatPath.toCanonical(path.getMoves()); //formats to canonical
        return FormatPath.toFactorized(path.getMoves());
    }

    // method that finds the path solution
    private Path findPath() {
        List<String> moves = new ArrayList<>(); // list to store moves
        Direction movementDir = Direction.Right; // starts by facing right
        int[] playerPos = maze.findEntryPoint();
        int[] exitPoint = maze.findExitPoint();

        while (!(playerPos[0] == exitPoint[0] && playerPos[1] == exitPoint[1])) {

            Direction rightDir = turnRight(movementDir);  //trys to go right first
            int[] rightPos = getFuturePosition(playerPos, rightDir);

            // for turning right then forward
            if (mazeBound(rightPos) && maze.getMazeArr()[rightPos[1]][rightPos[0]] == ' ') {
                movementDir = rightDir;
                playerPos = rightPos;
                moves.add("R");
                moves.add("F");
            // if cant go right it goes forward
            } else {
                int[] forwardPos = getFuturePosition(playerPos, movementDir);
                if (mazeBound(forwardPos) && maze.getMazeArr()[forwardPos[1]][forwardPos[0]] == ' ') {
                    playerPos = forwardPos;
                    moves.add("F");
                    // If cant go right or forward it goes right three times which is a left
                } else {
                    movementDir = turnRight(turnRight(turnRight(movementDir)));
                    moves.add("R");
                    moves.add("R");
                    moves.add("R");
                }
            }
        }

        return new Path(moves);
    }

        // calculate direction after turning right from current direc
    private Direction turnRight(Direction movementDir) {
        switch (movementDir) {
            case Up:
                return Direction.Right;
            case Right:
                return Direction.Down;
            case Down:
                return Direction.Left;
            case Left:
                return Direction.Up;
            default:
                throw new IllegalStateException("Unknown direction");
        }
    }

    // calculate the future direction based on current position
    private int[] getFuturePosition(int[] currentPosition, Direction direction) {
        switch (direction) {
            case Up:
                return new int[]{currentPosition[0], currentPosition[1] - 1};
            case Right:
                return new int[]{currentPosition[0] + 1, currentPosition[1]};
            case Left:
                return new int[]{currentPosition[0] - 1, currentPosition[1]};
            case Down:
                return new int[]{currentPosition[0], currentPosition[1] + 1};
            default:
                throw new IllegalStateException("Unknown direction");
        }
    }

    // checks to see if the player is within bounds
    private boolean mazeBound(int[] playerPos) {
        int xCord = playerPos[0];
        int yCord = playerPos[1];
        int mazeWidth = maze.getMazeArr()[0].length;
        int mazeHeight = maze.getMazeArr().length;

        return xCord >= 0 && xCord < mazeWidth && yCord >= 0 && yCord < mazeHeight;
    }

    // represents direction
    enum Direction {
        Up, Right, Down, Left
    }
}
