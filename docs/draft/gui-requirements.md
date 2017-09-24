# GUI requirements

## [information in requirements doc](https://github.com/StuPro-TOSCAna/TOSCAna/blob/requirements/docs/requirements/requirements.md)

### User Interface (UI)
| What | How | Importance |Comment|
|------|-----|------------|-------|
|Ease of Use | Very Simple | Must have | The UI must be simple and easy to use. Within a handful of clicks the transformation should be achieved |
|Look and Feel  | The UI must look awesome. No further requirements. | Must have | |
|Type|Web Application|Must have| |
|Supported browsers|Firefox, Chrome| Must have| |
|Communication|REST-calls|Must have| |
|Technology|Angular 4|Should have| |

### Transformation Center
| What | How | Importance |Comment|
|------|-----|------------|-------|
| Deploy app |After transformation, offer mechanism for deployment| Must have (implicit)|Although not explicitly required by client: Some details of the TOSCA model might not be representable in the target platforms modelling language, hence orchestration will be neccessary. note: we have to develop this anyways for testing purposes|
| Endpoint of deployed app | After deployment, the user must find information about how to reach the deployed app | Must have | only if information is found in boundary definition of the TOSCA model |
| Request additional data | If not contained in the TOSCA model itself, the web app must request missing data from the user | Must have | e.g. credentials. in cli mode not required (throw error instead) |
| Error handling | If transformation is not possible, the user gets informed about the problem and the state of the deployment, if any | Must have | |
| | The application recognizes the problem and offers auto correction | Could have | in form of topology optimiziation |
| | If deployment was started, but not finished correctly, compensate changes | Could have | Considered to be quite hard |
|Status information | during deployment, show current status of deployment | Could have | Client would appreciate that very much |
| App Monitoring | Provide health monitoring of deployed app | Could have | very nice to have but very unrealistic|
| Adapt deployment at runtime | If CSAR changes, the changes must be reflected in the deployment | Won't have | too complex |
| Manage multiple CSARs at once | | Won't have | This would force us to build a whole cloud deployment center, which is considered to be beyond the scope of our project |

## filtered information
out of the requirements doc there are some important information which influence the GUI
### Technologies
- Web Frontend
- Angular 4
- Using the REST-API

### Actions
- upload a CSAR-File
- list possible destination-languages
- choose a language
- start the transformation
- request missing data
- list transformed archives
- Question: download archives to deploy them manually?
