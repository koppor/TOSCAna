package org.opentosca.toscana.cli;

import org.apache.commons.cli.*;

/**
 * Class for the displayed Options in the CLI
 */
public class OptionsMenu extends Options {

    Options options;
    Option startt = new Option("startt", "start transformation",false, "starts the transformation");
    Option stopt = new Option("stopt", "stop transformation", false, "stops a transformation");
    Option startd = new Option("startd", "start deployment", false, "starts the deployment");
    Option stopd = new Option("stopd", "stop deployment", false, "stops the deployment");
    Option status = new Option("status", false, "prints some information about a current transformation or component");
    Option verbose = new Option("v", "verbose", false, "show logs while transformation");
    Option list = new Option("l", "list", false, "show all available supported platforms");
    Option transform = new Option("t", "transform", true, "transform the given topology to the desired platform");
    Option help = new Option("h", "help", false, "prints the main page");
    Option debug = new Option("debug", false, "prints debug information");
    Option change = new Option("change", true, "change a given value");
    Option def = new Option("default", false, "prints the set default values");
    Option cls = new Option("cls", false, "cleans the screen");
    Option quit = new Option("e", "quit", false, "quits the CLI");

    /**
     * Constructor adds the options for the CLI
     */
    public OptionsMenu(){
        options = new Options();
        options.addOption(startt);
        options.addOption(stopt);
        options.addOption(startd);
        options.addOption(stopd);
        options.addOption(status);
        options.addOption(verbose);
        options.addOption(list);
        options.addOption(transform);
        options.addOption(help);
        options.addOption(debug);
        options.addOption(change);
        options.addOption(def);
        options.addOption(cls);
        options.addOption(quit);
    }

    /*
    *  generates the help statement
    */
    void printHelp(){
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("toscana", options);
    }

}
