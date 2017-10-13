package org.opentosca.toscana.cli;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class CliController {

    //private static final Logger logger = LoggerFactory.getLogger(CliController.class.getName());

    private static Options options;
    private static CommandLineParser parser;
    private static CommandLine lin;
    private static OptionsMenu optionsMenu;
    private static ApiController api;
    private static File file;
    private boolean interactive;

    public static void main(String[] args) throws Exception{
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

            lin = parser.parse(options, input);
            if(callOption(lin).equals("")){
                optionsMenu.printHelp();
            }

            if(getInteractive()) {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("\nInteractive TOSCAna CLI launched!");
                while (true) {
                    final String read = br.readLine();
                    if (read.contains("TOSCAna")) {
                        final String last = read.substring(read.lastIndexOf("TOSCAna") + 8, read.length());
                        final String[] stArr = last.split(" ");
                        final CommandLine cmdL = parser.parse(options, stArr);
                        callOption(cmdL);
                    } else if(read.contains("exit")){
                        break;
                    } else {
                       System.out.println("Try 'TOSCAna -h' for help with commands");
                    }
                }
            }

        } catch(ParseException e) {
                System.err.println("Error occured: " +e.getMessage());
                optionsMenu.printUsage(options, System.out);
                //logger.error("Error occured: ",e);
                return false;
            } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    private String callOption(CommandLine line){
        String call="";

        if(line.hasOption("interactive")){
            setInteractive();
            call+="interactive ";
        }

        if(line.hasOption("csar")){
            final String filePath=line.getOptionValue("csar");
            if(new File(filePath).exists() && isCSAR(filePath)) {
                setFile(filePath);
                call+="csar ";
            } else {
                System.err.println("CSAR Archive not found!");
            }
        }

        if(line.hasOption("abort")){
            System.out.println(api.abortTransformation());
            call+="abort ";
        }

        if(line.hasOption("list")){
            System.out.println(api.listPlatforms());
            call+="list ";
        }

        if(line.hasOption("transform")){
            if (getFile()!=null) {
                System.out.println(api.selectPlatform(line.getOptionValue("transform")));
                System.out.println(api.startTransformation());
                call+="transform ";
            }
        }

        if(line.hasOption("help")){
            optionsMenu.printHelp();
            call+="help ";
        }

        if(line.hasOption("usage")){
            optionsMenu.printUsage(options, System.out);
            call+="usage ";
        }

        if(line.hasOption("verbose")){
            System.out.println(api.verbose());
            call+="verbose ";
        }

        return call;
    }

    /*
    * checks if the provided File is a CSAR Archive
     */
    private boolean isCSAR(String name){
        final String last = name.substring(name.lastIndexOf(".") + 1, name.length());
        return "csar".equalsIgnoreCase(last);
    }

    public void setInteractive(){
        interactive=true;
    }

    public boolean getInteractive(){
        return interactive;
    }

    public void setFile(String path){
        file = new File(path);
    }

    public File getFile(){
        return file;
    }

}
