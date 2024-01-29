package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            int[] rightPos = futurePlayerPos(playerPos, rightDir);

            // for turning right then forward
            if (mazeBound(rightPos) && maze.getMazeArr()[rightPos[1]][rightPos[0]] == ' ') {
                movementDir = rightDir;
                playerPos = rightPos;
                moves.add("R");
                moves.add("F");
            // if cant go right it goes forward
            } else {
                int[] forwardPos = futurePlayerPos(playerPos, movementDir);
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
    private int[] futurePlayerPos(int[] currentPosition, Direction direction) {
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

    public boolean validatePath(String path) {
        // convert the string path into a list of moves
        List<String> moves = expandFactorizedPath(path);

        int[] playerPos = maze.findEntryPoint();
        Direction movementDir = Direction.Right; // entry from the east

        //loops through each move in the path
        for (String move : moves) {
            switch (move) {
                case "F":
                    playerPos = futurePlayerPos(playerPos, movementDir);
                    if (!mazeBound(playerPos) || maze.getMazeArr()[playerPos[1]][playerPos[0]] == '#') {
                        return false; //returns false if path is invalid
                    }
                    break;
                case "R":
                    movementDir = turnRight(movementDir);
                    break;
                case "L":
                    movementDir = turnRight(turnRight(turnRight(movementDir)));
                    break;
            }
        }

        int[] exitPoint = maze.findExitPoint(); //gets exit point
        return playerPos[0] == exitPoint[0] && playerPos[1] == exitPoint[1]; //checks to see if player is at exit
        }
        
        
    // Method to allow users to input factorized form instead
    private List<String> expandFactorizedPath(String path) {
        List<String> moves = new ArrayList<>();
        Pattern pattern = Pattern.compile("(\\d*)([FRL])");
        Matcher matcher = pattern.matcher(path);

        while (matcher.find()) {
            String countStr = matcher.group(1);
            String move = matcher.group(2);
            int count = countStr.isEmpty() ? 1 : Integer.parseInt(countStr);

            for (int i = 0; i < count; i++) {
                moves.add(move);
                }
        }
        return moves;
    }

    // convert string path into a list of moves
    private List<String> pathToList(String path) {
        List<String> moves = new ArrayList<>();
        for (char c : path.toCharArray()) {
            moves.add(String.valueOf(c));
        }
        return moves;
    }
}
