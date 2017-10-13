package org.opentosca.toscana.cli;

import org.apache.commons.cli.*;

import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Class for the displayed Options in the CLI
 */
public class OptionsMenu extends Options {

    private Options options;
    private Option stop = new Option("a", "abort", false, "stops a running transformation");
    private Option verbose = new Option("v", "verbose", false, "show logs while a transformation is running");
    private Option list = new Option("l", "list", false, "show all available supported platforms");
    private Option transform = new Option("t", "transform", true, "transform the given CSAR Archive to the desired platform");
    private Option help = new Option("h", "help", false, "prints the man page");
    private Option usage = new Option("u", "usage", false, "explains the usage of the commands");
    private Option csar = new Option("c", "csar", true, "location of the CSAR Archive that should be transformed");
    private Option interactive = new Option("i", "interactive", false, "CLI gets Interactive");

    /**
     * Constructor adds the options for the CLI
     */
    public OptionsMenu(){
        options = new Options();
        transform.setArgName("platform");
        //usage.setArgName("command");
        csar.setArgName("location");
        options.addOption(stop);
        options.addOption(verbose);
        options.addOption(list);
        options.addOption(transform);
        options.addOption(help);
        options.addOption(usage);
        options.addOption(csar);
        options.addOption(interactive);
    }

    /*
    * generates the help statement
    */
    public void printHelp(){
        final String syntax = "java -jar org.opentosca.toscana.cli.jar";
        final String helpHeader = "\n These are common TOSCAna commands used in various situations: \n\n";
        final String helpFooter = "\n See 'TOSCAna -u' to read about the usage of subcommands \n\n";
        final HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp(syntax, helpHeader, options, helpFooter, true);
    }

    /*
    * generates the usage statement
     */
    public void printUsage(Options op, OutputStream out){
        final String syntax = "java -jar org.opentosca.toscana.cli.jar";
        final HelpFormatter formatter = new HelpFormatter();
        final PrintWriter print = new PrintWriter(out);
        formatter.printUsage(print, 80, syntax, op);
        print.flush();
    }

    /*
    * get all Options for the CLI
    */
    public Options getAllOptions(){
        return options;
    }

}
