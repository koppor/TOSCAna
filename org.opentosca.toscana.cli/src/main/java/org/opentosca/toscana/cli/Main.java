package org.opentosca.toscana.cli;

import org.apache.commons.cli.*;

public class Main {

    public static void main(String[] args) {
        OptionsMenu options = new OptionsMenu();
        options.printHelp();

        // create the parser
        CommandLineParser parser = new DefaultParser();
        try {
            // parse the command line arguments
            CommandLine line = parser.parse( options, args );

            if(line.hasOption("test")){
                System.out.println("test");
            }
        }
        catch( ParseException exp ) {
            System.err.println( "Unexpected error: " + exp.getMessage() );
        }
    }

}
