package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

public class FormatPath {
    //converts the list of moves into canonical
    public static String toCanonical(List<String> moves) {
        StringBuilder sb = new StringBuilder();
        for (String move : moves) {
            sb.append(move);
        }
        return sb.toString();
    }
        //formats the list of moves to factorized form
        public static String toFactorized(List<String> moves) {

        StringBuilder sb = new StringBuilder();
        String lastMove = moves.get(0);
        int count = 1;

        for (int i = 1; i < moves.size(); i++) {
            String currMove = moves.get(i);
            if (currMove.equals(lastMove)) {
                count++;
            } else {
                appendMove(sb, lastMove, count);
                lastMove = currMove;
                count = 1;
            }
        }
        appendMove(sb, lastMove, count);

        return sb.toString().trim();
    }

    //appends move to stringbuilder and groups the same letter
    private static void appendMove(StringBuilder sb, String move, int count) {
        sb.append(move);
        if (count > 1) {
            sb.append(count);
        }
        sb.append(" ");  // adds space between letters
    }
}

