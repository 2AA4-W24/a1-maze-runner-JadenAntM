package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        // Sets options for either i or input
        Options options = new Options();
        options.addOption(Option.builder("i").longOpt("input").hasArg().desc("Input file path").build());

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        try {
            //parses the cli args
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            // handles cli parsing errors
            logger.error("Error: " + e.getMessage());
            logger.error("Use the format --> java -jar mazerunner.jar -i <inputFile>");
            System.exit(1);
            return;
        }

        String inputFile = cmd.getOptionValue("i");

        // shows error if no file is detected
        if (inputFile == null) {
            logger.error("missing -i or --input");
            logger.error("use the format --> java -jar mazerunner.jar -i 'inputFile'");
            System.exit(1);
        }

        logger.info("** Starting Maze Runner");
        try {
            logger.info("**** Reading the maze from file " + inputFile);
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line;
            while ((line = reader.readLine()) != null) {
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        logger.info("WALL ");
                    } else if (line.charAt(idx) == ' ') {
                        logger.info("PASS ");
                    }
                }
                logger.info(System.lineSeparator());
            }
        } catch(Exception e) {
            logger.info("/!\\ An error has occured /!\\");
        }
        logger.info("**** Computing path");
        logger.info("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}
