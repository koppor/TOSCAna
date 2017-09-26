# Definition CLI Architecture
This document describes the architecture, the supported commands and used libraries for the CLI component of the TOSCAna Software.

## Class Diagram
![Class Diagram](img/cli_class_diagram.png)
***Note:*** This diagram was created using LucidChart. The source file can be found [here](https://www.lucidchart.com/invitations/accept/6c4ca4c7-d79a-4fee-82ba-6a79e2971f39).

## Cli

The CliMain is the main class to initialize the CLI and show the possible commands of the OptionsMenu the User can execute. After the user has input a command, the CliMain calls the ApiCaller to process the command.

## OptionsMenu
The OptionsMenu contains all commands the User can call with the CLI.

The methods shown in the above class diagram have the following functionality:
* `printHelp(): void` - prints all possible Commands the User can call
* `printUsage():void` - the Usage of the CLI is explained

## ApiCaller
The ApiCaller performs the tasks the User wants to do and calls the functions of the REST API.

The methods shown in the above class diagram have the following functionality:
* `startTransformation(platform: String): Application` - starts a new Transformation to the desired Platform
* `stopTransformation():Boolean` - stops currently running Transformation
* `startDeployment():Boolean` - starts a new Deployment to the Destination
* `stopDeployment():Boolean` - stops currently running Deployment
* `serveArchive(archive: File):Boolean` - The Archive gets uploaded to the API Server
* `status():String` - print the Status of the currently running Transformation/ Deployment or other Operations
* `quit():void` - exits the CLI
* `printDebug():String` - enable debug mode for better Error handling
* `printLogs():String` - prints last created Logs
* `listPlatforms():String` - lists all available Platforms where an archive can be deployed

## Basics
The main tasks of the CLI component are:
- parse and call the commands
- call the method to transform and/ or deploy a given topology to a custom platform (start / stop / transform platform)
- show current status of transformation
- print detailed logs if wanted
- get status from different components
- show supported platforms
- create threads to provide asynchronous operations

## Supported Commands
| command | description | option |
|-----------------------|-----------------------|-------------------------------|
| start transformation | starts the transformation | -startt or --start-transformation |
| stop transformation | stops a transformation | -stopt or --stop-transformation |
| status | prints some information about a current transformation or component | -status |
| verbose | show logs while transformation | -v or --verbose |
| list | show all available supported platforms | -l or --list |
| transform | transform the given topology to the desired platform | -t <platform> or --transform <platform> |
| help | prints the man page | -h or --help |

## Architecture Library
To control the TOSCAna software we need a command-line-interface (CLI) which will be integrated in the program-code. Therefore we could use different libraries.

## Considered Alternatives

* library created by ourself
* open source library from apache - ["Commons CLI"](https://commons.apache.org/proper/commons-cli/index.html)
* [args4j](https://github.com/kohsuke/args4j)
* [jopt simple](http://pholser.github.io/jopt-simple/examples.html)
* [JewelCli](http://jewelcli.lexicalscope.com/)


## Conclusion

* *Chosen Alternative: Commons CLI from Apache*

### Commons CLI

* `+` open source
* `+` costumer prefers Commons CLI
* `+` well provided documentation
* `-` could be more powerful than needed


## License

Copyright (c) 2017 University of Stuttgart.

All rights reserved. Made available under the terms of the [Eclipse Public License v1.0] and the [Apache License v2.0] which both accompany this distribution.

 [Apache License v2.0]: http://www.apache.org/licenses/LICENSE-2.0.html
 [Eclipse Public License v1.0]: http://www.eclipse.org/legal/epl-v10.html