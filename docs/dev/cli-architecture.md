# Definition CLI Architecture

## Basics
- parse and call the commands
- call the method to transform and/ or deploy a given topology to a custom platform (start / stop / transform platform)
- show current status of transformation
- print detailed logs if wanted
- get status from different components
- show supported platforms
- create threads to provide asynchronous operations

## supported commands
| command | description | option |
|-----------------------|-----------------------|-------------------------------|
| start transformation | starts the transformation | -startt or -start transformation |
| stop transformation | stops a transformation | -stopt or -stop transformation |
| start deployment | starts the deployment | -startd or -start deployment |
| stop deployment | stops the deployment | -stopd or -stop deployment |
| status | prints some information about a current transformation or component | -status |
| verbose | show logs while transformation | -v or -verbose |
| list | show all available supported platforms | -l or -list |
| transform | transform the given topology to the desired platform | -t platform or -transform platform |
| help | prints the main page | -h or -help |
| debug | prints debug information | -debug |
| change | change a given value | -change value |
| default | prints the set default values | -default |
| cls | cleans the screen | -cls |
| quit | quits the CLI | -e or -quit |
