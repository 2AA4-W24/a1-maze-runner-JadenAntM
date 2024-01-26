package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

public class Path {
    private List<String> moves;

    public Path(List<String> moves) {
        this.moves = moves;
    }

    public List<String> getMoves() {
        return moves;
    }

}
