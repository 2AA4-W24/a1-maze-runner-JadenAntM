package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;

public class Main {

    public static void main(String[] args) {
        try {
            Configuration config = configure(args);

            // create a maz instance
            Maze maze = new Maze(config.getInputFile());

            // pass the maze to maze runner
            MazeRunner mazeRunner = new MazeRunner(maze);
            String result = mazeRunner.run();

            System.out.println(result);
        } catch (Exception e) {
            System.err.println("An error has occurred");
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static Configuration configure(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption("i", "input", true, "Input file path");
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        String inputFile = cmd.getOptionValue("i");
        return new Configuration(inputFile);
    }

    private static class Configuration {
        private final String inputFile;

        public Configuration(String inputFile) {
            this.inputFile = inputFile;
        }

        public String getInputFile() {
            return inputFile;
        }
    }
}
