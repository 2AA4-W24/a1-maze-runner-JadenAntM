package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            Configuration config = configure(args);

            // create a maz instance
            Maze maze = new Maze(config.getInputFile());

            // pass the maze to maze runner
            MazeRunner mazeRunner = new MazeRunner(maze);
            
            if (config.getPath() != null) {
                boolean isValid = mazeRunner.validatePath(config.getPath());
                logger.info("is the path valid: " + isValid);
            } else {
                String result = mazeRunner.run();
                logger.info(result);
            }
        } catch (Exception e) {
            logger.error("Error has occurred", e);
            System.exit(1);
        }
    }

    private static Configuration configure(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption("i", "input", true, "Input file path");
        options.addOption("p", "path", true, "Path to validate");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        String inputFile = cmd.getOptionValue("i");
        String path = cmd.getOptionValue("p");

        return new Configuration(inputFile, path);
    }

    private static class Configuration {
        private final String inputFile;
        private final String path;

        public Configuration(String inputFile, String path) {
            this.inputFile = inputFile;
            this.path = path;
        }

        public String getInputFile() {
            return inputFile;
        }

        public String getPath() {
            return path;
        }
    }
}
