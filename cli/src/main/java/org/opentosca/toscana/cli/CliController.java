package org.opentosca.toscana.cli;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;

public class CliController {

    //private static final Logger LOG = LoggerFactory.getLogger(CliController.class);

    private static Options options;
    private static CommandLineParser parser;
    private static CommandLine line;
    private static OptionsMenu optionsMenu;
    private static ApiController api;
    private static File file;

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

        try {

            line = parser.parse(options, input);

        } catch(ParseException exp) {
                System.err.println("Error occured: " +exp.getMessage());
                //LOG.error("Error occured: ",exp);
                return false;
            }

        if(line.hasOption("file")){
            final String filePath=line.getOptionValue("file");
            if(new File(filePath).exists() && isCSAR(filePath)) {
                setFile(filePath);
            } else {
                System.err.println("CSAR Archive not found!");
            }
        }

        if(line.hasOption("abort")){
            System.out.println(api.abortTransformation());
        }

        if(line.hasOption("list")){
            System.out.println(api.listPlatforms());
        }

        if(line.hasOption("transform")){
                if (getFile()!=null) {
                    System.out.println(api.selectPlatform(line.getOptionValue("transform")));
                    System.out.println(api.startTransformation());
                }
        }

        if(line.hasOption("help")){
            optionsMenu.printHelp();
        }

        if(line.hasOption("usage")){
           optionsMenu.printUsage(options, System.out);
        }

        if(line.hasOption("verbose")){
            System.out.println(api.verbose());
        }

        return true;
    }

    /*
    * checks if the provided File is a CSAR Archive
     */
    private boolean isCSAR(String name){
        final String last = name.substring(name.lastIndexOf(".") + 1, name.length());
        return "csar".equalsIgnoreCase(last);
    }

    public void setFile(String path){
        file = new File(path);
    }

    public File getFile(){
        return file;
    }

}
