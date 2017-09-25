package org.opentosca.toscana.cli;

import org.apache.commons.cli.*;

public class Main {

    private static OptionsMenu options;
    private static CommandLineParser parser;
    private static CommandLine line;

    public static void main(String[] args) {
        options = new OptionsMenu();
        options.printHelp();

        // create the parser
        parser = new DefaultParser();
        try {
            // parse the command line arguments
            line = parser.parse( options, args );

            if(line.hasOption("test")){
                System.out.println("test");
            }
        }
        catch( ParseException exp ) {
            System.err.println( "Unexpected error: " + exp.getMessage() );
        }
    }

}
