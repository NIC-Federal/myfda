Project Name:  MyFDA
Prototype URL: https://myfda.egov.com
Install Instructions:  [Install Guide](https://github.com/NIC-Federal/myfda/blob/master/docs/Install%20Guide.md)
License: [MyFDA Licensing Information](https://github.com/NIC-Federal/myfda/blob/master/docs/Licensing.md)

Description
-----------

NIC’s long-standing tradition of making government accessible to all citizens allowed us to quickly 
address the FDA’s need to present this new dataset in an innovative, easily consumed format to the 
public.  As a leading agile practitioner to government, NIC was able to hand-pick an experienced 
[multi-disciplinary agile team](docs/Project%20Team.md) (Play 7) to build the NIC solution -- MyFDA, a personalized portal to FDA 
drug information (Play 2). 

User-centered design techniques drove the MyFDA prototype feature-set, which included:

* Personalization features, including login through Facebook (with the ability to support other 
authentication mechanisms in the future) and the ability to create and manage a MyMeds medication list.
* The ability to search for medications using scientific and brand names with search term completion.
* Rich visualization of drug information including drug recalls, adverse effects, and drug interactions.
* Combining external datasets with the openFDA data to create a friendlier and more complete experience.
* An easy to use UI that works on mobile devices and with assistive technologies used by users with 
disabilities (Play 3).

Being a prototype service, our intent was to provide key features that would enable future informational 
and personalization features to be added to the service. 

As the team was being assembled, a group of innovative thinkers from across the country representing a 
multitude of technical and non-technical skills and experience began work on conceptualizing an approach 
to the solution that would guide the agile team once they hit the ground.  From a consumer’s perspective 
the largest challenge with the OpenFDA data is its sheer volume.  As such, the concept of a personalized 
portal naturally emerged as the solution as it would limit the scope of the data to a more manageable 
level.  The ideation team surveyed a group of 200 employees outside the project to both vet the idea and 
generate a name (Play 12).  From that [survey](https://github.com/NIC-Federal/myfda/blob/master/docs/User-Centered%20Design/User%20Survey%201.pdf) MyFDA was born.

NIC also used this time to appoint an experienced product manager who was given full authority over the 
project, and assumed responsibility for the functionality and quality of the prototype (Play 6).  The 
product manager working with the product owner immediately set out to prepare [user personas](https://github.com/NIC-Federal/myfda/blob/master/docs/User-Centered%20Design/UserPersonas.pdf) to act as 
surrogates to guide the team when engaging actual users would not be possible.  The team polled friends 
and family that they felt best represented these personas to determine their needs and generate persona 
stories.  

NIC appointed an experienced delivery manager to the team who worked with the product manager to determine 
the most appropriate methodology for the project (Play 7).  Given the project requirements, the team chose 
Scrumban, a proven [agile approach](docs/Project%20Management.md) currently leveraged by NIC for rapid service development (Play 4).  By 
using Scrumban, the team leveraged the prescriptive nature of Scrum to remain agile and the flow of Kanban 
to quickly address user feedback and shifting priorities.

Armed with the overall vision and persona stories, the team quickly set out to generate [epics and user 
stories] to populate the backlog.  The team followed up with a product planning session, using story 
points and business value to shape an MVP with the greatest ROI.  The design team quickly built a set of 
[wireframes](https://github.com/NIC-Federal/myfda/blob/master/docs/User-Centered%20Design/Final_Wireframes.pdf) 
demonstrating the priority features and conducted [small group usability testing using those wireframes](https://github.com/NIC-Federal/myfda/blob/master/docs/User-Centered%20Design/Usability%20Testing%20Summary%201.md), 
which shaped the UX of the MVP in subsequent iterations (Play 3).

The first four one-day sprints comprised three value based releases: homepage and search, search results, 
and drug details.  The following three-day sprint added in the login and MyMeds personalization features, 
during which the product manager and UX lead met with an [internal expert group](https://github.com/NIC-Federal/myfda/blob/master/docs/User-Centered%20Design/Heuristic%20Review%20Summary.md) 
which generated additional usability enhancements that were incorporated into later sprints (Play 4).

When the team learned of the first RFQ extension, a decision was made to bring additional features into 
the prototype.  To determine which features should be added, the team held a “[persona value pitch](https://github.com/NIC-Federal/myfda/blob/master/docs/Project%20Management.md#persona-value-pitching)” 
session that had team members pitch feature ideas through the eyes of one of the personas.  The exercise 
proved to be incredibly valuable, both in the planning of a new MVP release and in keeping the team 
focused on the user’s needs (Play 1).  The subsequent value sprints also incorporated external data 
sources to enhance the user’s experience (Play 13). 

With the MVP release candidate in place the team conducted [usability testing](https://github.com/NIC-Federal/myfda/blob/master/docs/User-Centered%20Design/MyFDA_UsabilityTestingResults.pdf) with a small group of 
external users (Play 1).  The team prioritized as many of the discovered issues as possible in the 
remaining time and backlogged the rest with suggested fixes.  The moderator also used the user testing 
group to poll proposed features for the product.  NIC has also added this poll to the [MyFDA Facebook page](https://www.facebook.com/AboutMyFDA) to further 
vet those features.

The entire project consisted of [six sprints](https://github.com/NIC-Federal/myfda/raw/master/docs/Project%20Management/velocity_chart.PNG), spanning eight days (Play 4).  Sprint planning and standups 
were conducted in the mornings.  Additionally, due to the fast pace of the prototype project, afternoon 
standup meetings were conducted to coordinate effort and ensure that all blockers were known by the team.  
Each sprint ended with a demo of the working software and a retrospective meeting.  This iterative 
approach allowed the team to account for new knowledge frequently and to adjust course.

The team selected a modern open source technology stack (Play 13), documented in the [Technology 
Registry](https://github.com/NIC-Federal/myfda/blob/master/docs/Technology%20Registry.md), 
which enabled the team to develop a scalable framework to ensure that future capabilities could 
easily be added to the prototype (Play 8).  Some of the notable technology aspects of the prototype include:

•	The frontend UI was implemented as a single page Ember.js application
•	The backend was implemented using Java and Spring
•	MyFDA offers its own Level 5 REST API to the frontend components
•	A style guide was created and evolved throughout the project
•	Changes to the master branch in GitHub trigger a build and deploy using Jenkins
•	The application is packaged as a runnable Docker image for ease of deployment and portability
•	Deployed prototype to Amazon Web Services and used Amazon EC2 and Amazon ELB (Play 9)
•	Established a DevOps process that supports both [continuous integration](https://github.com/NIC-Federal/myfda/blob/master/docs/DevOps.md#continuous-integration) and [continuous deployment](https://github.com/NIC-Federal/myfda/blob/master/docs/DevOps.md#continuous-delivery) (Play 10)

NIC integrated a security engineer onto the agile team, and implemented a comprehensive security strategy 
which included secure technologies, coding, deployment, DevOps tools, and security scanning.  
Additionally, a [continuous security monitoring](https://github.com/NIC-Federal/myfda/blob/master/docs/Security.md) program was developed to enhance our baseline security 
posture and address emerging risk on a continuous basis and throughout the product’s lifecycle (Play 11).  
The continuous monitoring system was provisioned using OSSEC, Logstash, ElasticSearch, and Kibana.

At the conclusion of the allotted performance period, NIC delivered a tested and scalable open source 
MyFDA prototype that provides rich functionality and an achievable roadmap of future features that will 
extend user-driven functionality (Play 13).

Detailed project documentation and artifacts can be found in the GitHub repository at:

https://github.com/NIC-Federal/myfda/blob/master/docs/README.md

Criteria
--------

| Criteria | NIC Approach | Evidence Links |
| :------- | :----------- | :------------- |
| a. | Assigned product manager as one, accountable leader. | [1](https://github.com/NIC-Federal/myfda/blob/master/docs/Project%20Team.md), [2](https://raw.githubusercontent.com/NIC-Federal/myfda/master/docs/Project%20Team/leadership_evidence.jpg) |
| b. | Multidisciplinary team with 12 of 13 labor categories | [1](https://github.com/NIC-Federal/myfda/blob/master/docs/Project%20Team.md), [2](https://raw.githubusercontent.com/NIC-Federal/myfda/master/docs/Project%20Team/evidence_multidiscipline.jpg) |
| c. | Kept focused on the user throughout the entire project | [1](https://github.com/NIC-Federal/myfda/blob/master/docs/User-Centered%20Design.md), [2](https://github.com/NIC-Federal/myfda/blob/master/docs/User-Centered%20Design/User%20Research.md), [3](https://github.com/NIC-Federal/myfda/blob/master/docs/User-Centered%20Design/Usability%20Testing%20Summary%201.md), [4](https://github.com/NIC-Federal/myfda/blob/master/docs/User-Centered%20Design/MyFDA_UsabilityTestingResults.pdf), [5]((https://github.com/NIC-Federal/myfda/blob/master/docs/User-Centered%20Design/User%20Survey%201.pdf) |
| d. | Six human-centered design techniques:  User research, user personas, user surveying, usability interviews, heuristic reviews, accessibility testing. | [1](https://github.com/NIC-Federal/myfda/blob/master/docs/User-Centered%20Design.md), [2](https://github.com/NIC-Federal/myfda/blob/master/docs/User-Centered%20Design/User%20Research.md), [3](https://github.com/NIC-Federal/myfda/blob/master/docs/User-Centered%20Design/UserPersonas.pdf), [4](https://github.com/NIC-Federal/myfda/blob/master/docs/User-Centered%20Design/User%20Survey%201.pdf), [5](https://github.com/NIC-Federal/myfda/blob/master/docs/User-Centered%20Design/Usability%20Testing%20Summary%201.md), [6](ttps://github.com/NIC-Federal/myfda/blob/master/docs/User-Centered%20Design/MyFDA_UsabilityTestingResults.pdf), [7](https://github.com/NIC-Federal/myfda/blob/master/docs/User-Centered%20Design/Heuristic%20Review%20Summary.md), [8](https://github.com/NIC-Federal/myfda/blob/master/docs/User-Centered%20Design/Section508_MyFDA_CynthiaSays_Results.pdf) |
| e. | Created style guide for project and used Bootstrap as responsive design pattern. | [1](https://github.com/NIC-Federal/myfda/blob/master/docs/User-Centered%20Design.md#responsive-design), [2](https://github.com/NIC-Federal/myfda/blob/master/docs/Style%20Guidelines.md), [3](https://myfda.egov.com/#/style-guide) |
| f. | Ran usability testing multiple times during the project. | [1](https://github.com/NIC-Federal/myfda/blob/master/docs/User-Centered%20Design/Usability%20Testing%20Summary%201.md), [2](https://github.com/NIC-Federal/myfda/blob/master/docs/User-Centered%20Design/MyFDA_UsabilityTestingResults.pdf) |
| g. | Used Scrumban as the project management methodology. | [1](https://github.com/NIC-Federal/myfda/blob/master/docs/Project%20Management.md), [2](https://raw.githubusercontent.com/NIC-Federal/myfda/master/docs/Project%20Management/velocity_chart.PNG) |
| h. | Used Twitter Bootstrap for the responsive design framework and tested on mobile devices. | [1](https://myfda.egov.com/), [2](https://github.com/NIC-Federal/myfda/blob/master/docs/User-Centered%20Design.md#responsive-design), [3](https://raw.githubusercontent.com/NIC-Federal/myfda/master/docs/User-Centered%20Design/placeit-dashboard.png), [4](https://raw.githubusercontent.com/NIC-Federal/myfda/master/docs/User-Centered%20Design/placeit_feature.png) |
| i. | Used 25 open source technologies in the production of the prototype. | [1](https://github.com/NIC-Federal/myfda/blob/master/docs/Technology%20Registry.md), [2](https://github.com/NIC-Federal/myfda/blob/master/pom.xml), [3](https://github.com/NIC-Federal/myfda/blob/master/bower.json) |
| j. | Deployed prototype to Amazon Web Services EC2. | [1](https://github.com/NIC-Federal/myfda/blob/master/docs/DevOps/Cloud_Architecture_Diagram.png), [2](https://github.com/NIC-Federal/myfda/blob/master/docs/DevOps/aws_ec2_dashboard.PNG) |
| k. | Developed automated unit and integration testing. | [1](https://github.com/NIC-Federal/myfda/tree/master/src/test/java/com/nicusa), [2](https://github.com/NIC-Federal/myfda/blob/master/docs/Quality%20Assurance.md) |
| l. | Established continuous integration and continuous deployment processes. | [1](https://github.com/NIC-Federal/myfda/blob/master/docs/DevOps.md), [2](https://raw.githubusercontent.com/NIC-Federal/myfda/master/docs/DevOps/CI_Architecture.png), [3](https://raw.githubusercontent.com/NIC-Federal/myfda/master/docs/DevOps/CD_Architecture.png), [4](https://github.com/NIC-Federal/myfda/blob/master/docs/DevOps/myfda-26-consoleText.txt), [5](https://github.com/NIC-Federal/myfda/blob/master/docs/DevOps/myfda-docker-28-consoleText.txt), [6](https://github.com/NIC-Federal/myfda/blob/master/docs/DevOps/myfda-deploy-15-consoleText.txt) |
| m. | All configuration was controlled by the source control system. | [1](https://github.com/NIC-Federal/myfda/blob/master/docs/DevOps.md#configuration-management), [2](https://github.com/NIC-Federal/myfda/tree/master/jenkins/myfda), [3](https://github.com/NIC-Federal/myfda/blob/master/docker-build.sh), [4](https://github.com/NIC-Federal/myfda/tree/master/jenkins/myfda-deploy) |
| n. | Implemented continuous availability and security monitoring. | [1](https://github.com/NIC-Federal/myfda/blob/master/docs/Security.md#continuous-security-monitoring), [2](https://github.com/NIC-Federal/myfda/blob/master/docs/DevOps/Cloud_Architecture_Diagram.png), [3](https://github.com/NIC-Federal/myfda/blob/master/docs/DevOps.md#continuous-monitoring) |
| o. | Selected Docker as virtualization container and registered with Docker Hub. | [1](https://registry.hub.docker.com/u/nicfederal/myfda/), [2](https://github.com/NIC-Federal/myfda/blob/master/docker-build.sh), [3](https://github.com/NIC-Federal/myfda/blob/master/Dockerfile), [4](https://github.com/NIC-Federal/myfda/blob/master/docs/Install%20Guide.md) |
| p. | Included "install.md" and an Install Guide for developers | [1](https://github.com/NIC-Federal/myfda/blob/master/docs/Install%20Guide.md), [2](https://github.com/NIC-Federal/myfda/blob/master/INSTALL.md) |
| q. | Used technologies which are openly licensed and free of charge. | [1](https://github.com/NIC-Federal/myfda/blob/master/LICENSE), [2](https://github.com/NIC-Federal/myfda/blob/master/docs/Licensing.md), [3](https://github.com/NIC-Federal/myfda/blob/master/docs/Technology%20Registry.md) |

