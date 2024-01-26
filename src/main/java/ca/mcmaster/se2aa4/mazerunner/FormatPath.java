package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

public class FormatPath {
    public static String toCanonical(List<String> moves) {
        StringBuilder sb = new StringBuilder();
        for (String move : moves) {
            sb.append(move);
        }
        return sb.toString();
    }
}

