[Return to Documentation Index](README.md)

Software Development
====================

Documents the architecture for the MyFDA prototype and presents the technologies used at the client and 
server layers of the application.

Approach
--------

To realize the prototype concept, NIC developed both server-side software and client-side software.  These 
two layers of implementation were joined together by a RESTful API.  For each layer NIC choose software 
technologies, frameworks, and tools which met the following criteria:

* Cloud deployable
* Cloud scalable
* Open Source and Free
* Secure
* Accessible

During Sprint 0, the implementation team conducted discussions regarding what technologies might be used 
on the project.  A client-side lead was identified and lead discussion and documentation of the 
client-side stack.  Likewise, a server-side lead was identified and performed the same task for server 
technologies.  This allowed the team to already have a base stack identified going into Sprint 1.  This 
also allowed the technical architect to draft a high-level architecture for the software.

Prototype Architecture
----------------------

The diagram below shows the summary high-level architecture of the system.

![High-Level Architecture Diagram](Software%20Development/High_Level_Software_Architecture.png)  
*Image Caption:  The high-level architecture for MyFDA.*
 
The web application itself has been implemented as a single-page application, using Ember.js as the 
client-side application framework.  Ember.js works alongside jQuery and Bootstrap.  The Ember application 
relies on a mix of REST calls to external APIs and MyFDA's own API.  Access to the openFDA APIs is done 
through the MyFDA API, allowing for data aggregation with other sources and other data transformations at 
the application server layer.  This is also true about most access to the NLM datasets that were used to 
supplement the FDA data.

The foundation of the server software developed was Spring Boot and Spring MVC.  For user's wishing to 
identify themselves to the system using Facebook, profile information, including the medications saved to 
"MyMeds" is stored in MySQL.  EclipseLink is used to provide a loose integration with MySQL so that other 
databases and NOSQL data stores could be used in production scenarios.  Similarly, the use of EclipseLink 
allows a light-weight, in-memory alternative to MySQL to be used for developer installs of the software.

Client-Side Stack
-----------------

The following frameworks and components were used as the client-side development stack.

* jQuery
* Bootstrap
* Ember.js
* Font Awesome
* U.S. Map
* Chartist
* Easy Pie Chart

Please see the [Technology Registry](Technology%20Registry.md) for more specifics on these technologies.

Server-Side Stack
-----------------

The following frameworks and components were used as the server-side development stack.

* OpenJDK
* Spring Framework
* Spring Boot
* EclipseLink
* HyperSQL
* Spring Hatoas
* Google Guava
* JUnit
* Mockito
* Jasmine
* QUnit
* Logback
* Slf4j

Please see the [Technology Registry](Technology%20Registry.md) for more specifics on these technologies.

Development Tools
-----------------

| Tool Name | Tool Description | Website |
| :-------- | :--------------- | :------ |
| OpenJDK | Core development server-side SDK that offers the rich user interface, performance, versatility, portability, and security that today's applications require. | [OpenJDK Website](http://openjdk.java.net/) |
| Maven | Apache Maven is a software project management and comprehension tool. Based on the concept of a project object model (POM), Maven can manage a project's build, reporting and documentation from a central piece of information. | [Maven Website](https://maven.apache.org/) |
| Ember CLI | Ember CLI is the Ember.js command line utility which provides a fast asset pipeline powered by Broccoli, strong conventional project structure and is extensible via its addon system. | [Ember CLI Website](http://www.ember-cli.com/) |
| IntelliJ IDEA | IntelliJ IDEA is a Java integrated development environment (IDE) for developing computer software. | [IntelliJ IDEA](https://www.jetbrains.com/idea/) |
| Bower | Bower works by fetching and installing packages from all over, taking care of hunting, finding, downloading, and saving the stuff you’re looking for. | [Bower Website](http://bower.io/) |
