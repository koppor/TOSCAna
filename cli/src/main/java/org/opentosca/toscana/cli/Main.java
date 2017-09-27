package org.opentosca.toscana.cli;

import org.apache.commons.cli.*;

public class Main {

    private static Options options;
    private static CommandLineParser parser;
    private static CommandLine line;
    private static OptionsMenu optionsMenu;

    public static void main(String[] args) {
        optionsMenu = new OptionsMenu();
        options = optionsMenu.getAllOptions();

        // create the parser
        parser = new DefaultParser();
        try{
            // parse the command line arguments
            line = parser.parse(options, args);

            if(line.hasOption("help")){
                optionsMenu.printHelp();
            }
        }
        catch( ParseException exp ) {
            System.err.println("Error occured: " +exp.getMessage());
        }
    }

}
