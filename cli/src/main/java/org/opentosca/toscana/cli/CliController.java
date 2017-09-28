package org.opentosca.toscana.cli;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CliController {

    //private static final Logger LOG = LoggerFactory.getLogger(CliController.class);

    private static Options options;
    private static CommandLineParser parser;
    private static CommandLine line;
    private static OptionsMenu optionsMenu;
    private static ApiController api;

    public static void main(String[] args) {
        CliController cli = new CliController();
        cli.createCli(args);
    }

    /**
     * Initializes the CLI with the usable Options and Commandline Parser
     * @param input of the CLI
     * @return true if command was successfully used
     */
    public boolean createCli(String[] input){

        api = new ApiController();
        optionsMenu = new OptionsMenu();
        options = optionsMenu.getAllOptions();
        parser = new DefaultParser();

        try{

            line = parser.parse(options, input);

            if(line.hasOption("start-transformation")){
                System.out.println(api.startTransformation());
            }

            if(line.hasOption("abort-transformation")){
                System.out.println(api.abortTransformation());
            }

            if(line.hasOption("list")){
                System.out.println(api.listPlatforms());
            }

            if(line.hasOption("transform")){
                System.out.println(api.selectPlatform(line.getOptionValue("transform")));
            }

            if(line.hasOption("help")){
                optionsMenu.printHelp();
            }

            if(line.hasOption("usage")){
                System.out.println("Usage of: " +line.getOptionValue("usage"));
            }

            if(line.hasOption("verbose")){
                System.out.println(api.verbose());
            }
        }
        catch(ParseException exp) {
            System.err.println("Error occured: " +exp.getMessage());
            return false;
            //LOG.error("Error occured: ",exp);
        }
        return true;
    }

}
