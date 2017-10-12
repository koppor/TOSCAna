# Cloud Foundry Plugin

## Basics of Cloud Foundry
Cloud Foundry is a open-source Platform as a Service Model (PaaS) which allows everyone to deploy easily an existing application on an infrastructure e.g. AWS, vSphere or OpenStack.
There are two types of possible use-cases:
1. deploy an application on a infrastructure with existing underlying CloudFoundry-Platform --> [CF-Cloud-Provider](https://www.cloudfoundry.org/certified-platforms/)
2. deploy on an IaaS like AWS, vSphere, or OpenStack. Therefore you have to install CloudFoundry manually.

Cloud Foundry installs everything what the application needs to compile and run like OS, buildpack with all languages, libraries, and services that the app uses.

For further information you can have a look at the official [Cloud Foundry Documentation](https://docs.cloudfoundry.org/concepts/overview.html).
## Transformation
For the further document it is assumed that the CloudFoundry-Platform is already installed on the target infrastructure. If not the process of deployment throws an error. There is a well documented manual how to install Cloud Foundry on the [CF-Documentation](https://docs.cloudfoundry.org/deploying/index.html).
### Workflow
This workflow describes the doing of the plugin this means the system did some steps before.

plugin...
- gets data model of core
- parse the nodes
  - e.g. check for needed services like database
  - check name of application
  - ...
- transforms the information to a YAML-file which can be read from CloudFoundry (manifest.yml)
- asks for user data (CloudFoundry user, pw)
- creates a deployment script. The script uses CloudFoundry CLI-Commands (the basic information are inside the manifest.yml).
- return the created artifacts respectively the path to it

### Requirements/Issues
For this transformation-workflow there are some requirements and possible issues:
- using CloudFoundry-Platform assumes that is already installed on the target infrastructure
- using the CF-CLI assumes that the CF-CLI is installed on the local machine
- there are two possible ways to deploy an app with the CloudFoundry-CLI
  - using a manifest.yml. In this file are all information of the deployment (recommended)
  - using CLI-Commands
- saving the username and the pw could be a security-issue

## Deployment
If the user wants to deploy the application automatically, the system could just execute the created deployment-script.


## Additional information
You can find a well documented manual to Cloud Foundry on the [official doc-website](https://docs.cloudfoundry.org/).
