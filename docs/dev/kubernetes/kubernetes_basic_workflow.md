# Kubernetes Basic Transformation Workflow

This document outlines the workflow for the deployment of a simple application on the Kubernetes platform in order to help design the basic Kubernetes plugin for TOSCAna.

#### Basic Information about Kubernetes
- Packs applications in isolated containers instead of VMs (one application per container)
- Deploys containers based on operating-system-level virtualization rather than hardware virtualization
- Kubernetes mainly uses Docker for containers

## Automated Workflow
Describes the planned automated deployment of the simple application based on our [transformation workflow](https://github.com/StuPro-TOSCAna/TOSCAna/blob/transformation-flow/docs/dev/architecture/workflow.md).

### Transformation
- Data model of the simple app is provided by core
- Application is already containerized through Docker
  - **Note**: For the full version of the plugin an additional step is needed in order to containerize applications. This will probably be fairly complex, since the plugin must decide which parts of the application will be put in which container.
- Plugin creates Kubernetes Deployment configuration (YAML) based on data model
- **Output**: target artifacts (Deployment configuration, container)

### Deployment
- Plugin creates Kubernetes cluster
- Plugin creates Deployment from YAML configuration
- **Output**: endpoint of Kubernetes cluster

---

**Additional Information**:
- [Kubernetes Documentation](https://kubernetes.io/docs/home/)
- Seminar Paper on Kubernetes by @cmueller (not available plublicly)
