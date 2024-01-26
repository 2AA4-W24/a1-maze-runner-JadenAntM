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
        return FormatPath.toCanonical(path.getMoves());
    }


    private Path findPath() {
            List<String> moves = new ArrayList<>();
            Direction movementDirc = Direction.Right; // faces right to start
            int[] playerPos = maze.findEntryPoint();
            int[] exitPoint = maze.findExitPoint();

            while (!(playerPos[0] == exitPoint[0] && playerPos[1] == exitPoint[1])) {
                int[] futurePos;
                boolean canMove;

            switch (movementDirc) {
                case Up:
                    // checks right
                    futurePos = new int[]{playerPos[0] + 1, playerPos[1]};
                    canMove = mazeBound(futurePos) && maze.getMazeArr()[futurePos[1]][futurePos[0]] == ' ';
                    if (canMove) {
                        movementDirc = Direction.Left;
                        moves.add("R");
                        break;
                    }
                    // checks foward
                    futurePos = new int[]{playerPos[0], playerPos[1] - 1};
                    canMove = mazeBound(futurePos) && maze.getMazeArr()[futurePos[1]][futurePos[0]] == ' ';
                    if (canMove) {
                        break;
                    }
                    // checks left
                    futurePos = new int[]{playerPos[0] - 1, playerPos[1]};
                    canMove = mazeBound(futurePos) && maze.getMazeArr()[futurePos[1]][futurePos[0]] == ' ';
                    if (canMove) {
                        movementDirc = Direction.Right;
                        moves.add("L");
                        break;
                    }
                    // dead end turn around
                    movementDirc = Direction.Down;
                    moves.add("R");
                    moves.add("R");
                    break;


                case Right:
                    // checks right
                    futurePos = new int[]{playerPos[0], playerPos[1] + 1};
                    canMove = mazeBound(futurePos) && maze.getMazeArr()[futurePos[1]][futurePos[0]] == ' ';
                    if (canMove) {
                        movementDirc = Direction.Down;
                        moves.add("R");
                        break;
                    }
                    // checks forward
                    futurePos = new int[]{playerPos[0] +1, playerPos[1]};
                    canMove = mazeBound(futurePos) && maze.getMazeArr()[futurePos[1]][futurePos[0]] == ' ';
                    if (canMove) {
                        break;
                    }
                    // checks left
                    futurePos = new int[]{playerPos[0], playerPos[1] -1};
                    canMove = mazeBound(futurePos) && maze.getMazeArr()[futurePos[1]][futurePos[0]] == ' ';
                    if (canMove) {
                        movementDirc = Direction.Up;
                        moves.add("L");
                        break;
                    }
                    // Dead end turn around
                    movementDirc = Direction.Down;
                    moves.add("R");
                    moves.add("R");
                    break;
                case Left:
                    // checks right
                    futurePos = new int[]{playerPos[0], playerPos[1]-1};
                    canMove = mazeBound(futurePos) && maze.getMazeArr()[futurePos[1]][futurePos[0]] == ' ';
                    if (canMove) {
                        movementDirc = Direction.Left;
                        moves.add("R");
                        break;
                    }
                    // checks forward
                    futurePos = new int[]{playerPos[0] -1, playerPos[1]};
                    canMove = mazeBound(futurePos) && maze.getMazeArr()[futurePos[1]][futurePos[0]] == ' ';
                    if (canMove) {
                        break;
                    }
                    // checks left
                    futurePos = new int[]{playerPos[0], playerPos[1] +1};
                    canMove = mazeBound(futurePos) && maze.getMazeArr()[futurePos[1]][futurePos[0]] == ' ';
                    if (canMove) {
                        movementDirc = Direction.Right;
                        moves.add("L");
                        break;
                    }
                    // Dead end turn around
                    movementDirc = Direction.Right;
                    moves.add("R");
                    moves.add("R");
                    break;

                case Down:
                    //checks right
                    futurePos = new int[]{playerPos[0] -1, playerPos[1]};
                    canMove = mazeBound(futurePos) && maze.getMazeArr()[futurePos[1]][futurePos[0]] == ' ';
                    if (canMove) {
                        movementDirc = Direction.Left;
                        moves.add("R");
                        break;
                    }
                    // checks forward
                    futurePos = new int[]{playerPos[0], playerPos[1] + 1};
                    canMove = mazeBound(futurePos) && maze.getMazeArr()[futurePos[1]][futurePos[0]] == ' ';
                    if (canMove) {
                        break;
                    }
                    // checks left
                    futurePos = new int[]{playerPos[0] +1, playerPos[1]};
                    canMove = mazeBound(futurePos) && maze.getMazeArr()[futurePos[1]][futurePos[0]] == ' ';
                    if (canMove) {
                        movementDirc = Direction.Right;
                        moves.add("L");
                        break;
                    }
                    //dead end turn around
                    movementDirc = Direction.Up;
                    moves.add("R");
                    moves.add("R");
                    break;


                    default:
                        throw new IllegalStateException("Unknown direction");
                }
                if (canMove) {
                    playerPos = futurePos;
                    moves.add("F");
                }
            }
            return new Path(moves);
        }

        //checks if player is in bounds before moving
    private boolean mazeBound(int[] playerPos) {
        int xCord = playerPos[0];
        int yCord = playerPos[1];
        int mazeWidth = maze.getMazeArr()[0].length;
        int mazeHeight = maze.getMazeArr().length;

        return xCord >= 0
        && xCord < mazeWidth
        && yCord >= 0
        && yCord < mazeHeight;
    }

        enum Direction {
        Up, Right, Down, Left
    }
}