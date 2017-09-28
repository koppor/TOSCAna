package org.opentosca.toscana.cli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiController {

    //private static final Logger LOG = LoggerFactory.getLogger(ApiController.class);
    private boolean started=false;

    /**
     * Calls the REST API and starts the Transformation
     * @return
     */
    public String startTransformation(){
        started=true;
        return "Starting Transformation";
    }

    /**
     * Calls the REST API and stops the currently running Transformation
     * @return
     */
    public String abortTransformation(){
        if(started==true) {
            started = false;
            return "Aborting Transformation";
        } else{
            return "Transformation not running";
        }
    }

    /**
     * Activates Logs for better debugging
     * @return
     */
    public String verbose(){
        return "Logs activated";
    }

    /**
     * Shows all available platforms where the Transformation can occur
     * @return
     */
    public String listPlatforms(){
        return "Show available Platforms";
    }

    /**
     * Provides the Transformation platform to the REST API
     * @param platform
     * @return
     */
    public String selectPlatform(String platform){
        return "Platform selected: " +platform;
    }

}
