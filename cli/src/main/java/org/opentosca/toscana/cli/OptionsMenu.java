package org.opentosca.toscana.cli;

import org.apache.commons.cli.*;

/**
 * Class for the displayed Options in the CLI
 */
public class OptionsMenu extends Options {

    private Options options;
    private Option start = new Option("s", "start-transformation",false, "starts the transformation");
    private Option stop = new Option("a", "abort-transformation", false, "stops a transformation");
    private Option verbose = new Option("v", "verbose", false, "show logs while transformation");
    private Option list = new Option("l", "list", false, "show all available supported platforms");
    private Option transform = new Option("t", "transform", true, "transform the given topology to the desired platform");
    private Option help = new Option("h", "help", false, "prints the main page");
    private Option usage = new Option("u", "usage", true, "explains the specified command works");

    /**
     * Constructor adds the options for the CLI
     */
    public OptionsMenu(){
        options = new Options();
        options.addOption(start);
        options.addOption(stop);
        options.addOption(verbose);
        options.addOption(list);
        options.addOption(transform);
        options.addOption(help);
        options.addOption(usage);
    }

    /*
    * generates the help statement
    */
    public void printHelp(){
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("toscana", options);
    }

    /*
    * get all Options for the CLI
    */
    public Options getAllOptions(){
        return options;
    }

}
