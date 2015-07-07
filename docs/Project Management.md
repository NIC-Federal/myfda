[Return to Documentation Index](README.md)

Project Management
==================

The project was managed using practices and techniques originating in Scrum, Kanban, Lean Software Development, and XP.  Additionally, ad hoc tactics were applied to make critical planning and implementation decisions.

Scrumban
--------

NIC’s project management approach for the Agile Delivery Services RFQ challenge employed Scrumban, a 
proven Agile approach currently leveraged by NIC for prototyping new services.  Scrumban is a blend of 
Scrum and Kanban Agile project management, using aspects of both methodologies.  Scrumban is meant for a 
fast-paced, changing work environment, where plans and priorities change often, while enabling delivery of 
a working product in a short period of time.  By using Scrumban, the team leveraged the prescriptive 
nature of Scrum to be Agile and the flow of Kanban to allow the team to continually work on the highest 
priority items identified by product management.

There were two main goals using this approach in this timeline:

* Do the most important thing - the highest priority items need attention first. Learn quickly!
* Flow - team members need to be open about existing and potential blockers. Fail fast!

For the MyFDA project, Scrumban was used to organize teamwork in small iterations.  Epics and stories were 
derived from the ideation process, the product visioning meeting, and release planning meeting and were 
placed into the backlog.  The business value of these epics and component stories were assigned by the 
product owner and product manager formulating the backlog of our MVP (minimum viable product).  Story 
points were given by the team to estimate the amount of time to complete a story or task.  Both the 
business value and the story point estimates were then considered in prioritizing work, attempting to 
maximize the overall value being added to the working prototype over each iteration. Additional bundles of 
features and tasks were also organized into priority groupings which followed the most important MVP 
features.

Daily standups were conducted in the mornings.  Additionally, due to the fast pace of the prototype 
project, additional standup meetings were conducted to coordinate effort and ensure that all blockers were 
known by the team.  At all standups, the Kanban board was used to communicate to the team the work 
committed to by the team.  This simple but powerful work visualization aide categorizes work into three 
categories:  To Do, In Progress, and Done.

Sprint reviews followed by sprint retros were held on a regular basis to confirm progress and any 
blockage. Retros were highly valuable in quickly learning lessons around teamwork, the collaborative space 
and how to help the team communicate efficiently. The retro notes were made available immediately on our 
Confluence page and left on the whiteboard for the team to focus on how to apply the improvements.

![The product owner and delivery manager using the Kanban board](Project%20Management/kanban.jpg)  
*Image Caption:  The product owner and delivery manager using the Kanban board during a sprint planning meeting.*

Sprint Schedule
---------------
Due to the very short timeframe provided to develop the prototype, the team decided to use a very short 
sprint period.  Most sprints lasted only a single day, with the exception being a sprint that spanned a 
weekend.  This strategy allowed the team to plan for a single day, and to adjust quickly as work was 
completed and knowledge was gained.  Ceremonies were kept short and concise as to not take team members 
away from the actual work they were to perform during each iteration.

| Iteration   | Start   | End     | Primary Objectives |
| :---------- | :------ | :------ | :----------------- |
| Ideation | 6/18/2015 | 6/20/2015 | Problem Statement, Solution Concept |
| Sprint 0 | 6/20/2015 | 6/21/2015 | (Spike Only Sprint) User Understanding, Personas, Epics, Stories, CI Setup |
| Sprint 1 | 6/22/2015 | 6/23/2015 | Wireframes, High-Level Architecture, Data Research |
| Sprint 2 | 6/23/2015 | 6/24/2015 | Simple Drug Search, Stub Out Internal APIs, Large Group User Survey |
| Sprint 3 | 6/24/2015 | 6/25/2015 | Drug Details, Small Group User Testing, Updated Wireframes, API Implementation |
| Sprint 4 | 6/25/2015 | 6/26/2015 | Drug Details, Dashboard, Facebook Authentication |
| Sprint 5 | 6/26/2015 | 6/28/2015 | Visual Styling, Data Persistence, Search Improvements, Issue Fixes |
| Sprint 6 | 6/29/2015 | 6/30/2015 | Personalization Features, Small Group User Testing, Visual Styling, Security Testing |

Working under such a short sprint period is not the normal for team members, so understanding our true 
velocity was important to keeping pace with the rapid sprint schedule.  The image below shows our velocity 
report for the prototype project.

![The sprint-by-sprint velocity report for the project iterations.](Project%20Management/velocity_chart.PNG)  
*Image Caption:  The sprint-by-sprint velocity report for the project iterations.*

Sprint Structure
----------------

Each sprint followed the same structure.  Sprints were quickly planned at the beginning of the day and a 
standup meeting was conducted.  Work was then executed by the team.  At the end of the day a review of 
work and the demo was conducted.  After the demo, a retrospective meeting was conducted to identify things 
that went well during the sprint and strategies for improving things that could have went better.

Borrowed Tactics
----------------

Borrowing from Extreme Programming (XP), spikes were used throughout to mitigate risk in future 
iterations.  Spike tasks were created to figure out answers to tough technical or design problems.  
Sometimes these spikes consisted of research activities and at other times they involved technical 
experimentation and exploration.  Regardless, the objective for each spike was to add to understanding and 
to test ideas before committing to them through actual implementation.

Also borrowed from XP was the concept of pair programming.  When working on critical aspects of the 
prototype, developers and designers worked in pairs to ensure real-time peer review and joint decision 
making by the pair.  When the project identified blockers that were holding up new work or would soon be 
holding up new work, the pair programming concept was expanded upon.  For these issues, the team was asked 
to swarm the blocker to drive it to resolution.  All available resources not working other critical work 
would participate in the swarm.

Invented Tactics
----------------

Though Scrumban provided a roadmap for dealing with most planning and execution challenges, NIC always 
looks for opportunities to experiment with new techniques to drive improved outcomes.  One such invention 
that was invented during the project was the Persona Value Pitching exercise.  When the RFQ deadline was 
extended, the decision was made to extend work on the prototype to include some additional functionality 
to prove out the solution concept further.  The feature backlog was extensive from the original product 
visioning sessions so the team needed a way to determine which features remaining in the backlog offered 
the most utility to users.  An exercise named Persona Value Pitching was devised and executed, and proved 
very useful in setting priorities for the extended project.  This exercise is described below.

###Persona Value Pitching

Persona Value Pitching was a role playing exercise conducted to prioritize the feature backlog.  The 
exercise consisted of the following steps.

1. Team members revisited all of the user personas and looked closely at the feature backlog.
2. Team members selected a persona and was asked to identify the feature or features that were the most beneficial to that type of user.
3. Team members, role playing as the persona, pitched to the entire team why that feature was valuable.
4. After hearing all of the pitches, the team were each given three votes to use on the features that had been pitched.  Someone could use all three votes on a single feature or could distribute their votes across the features.
5. The votes were tallied and each feature assigned that score.
6. The product owner and product manager used this guidance to prioritize the feature backlog according to business value.

Project Management Tools
------------------------

The following project management tools were used for the prototype development project.

| Tool Name | Tool Description | Website |
| :-------- | :--------------- | :------ |
| JIRA | Part of the Atlassian suite, JIRA is a flexible and scalable issue tracker for software teams. This was used for planning, tracking, and analyzing work planned for each design and development iteration. | [JIRA Website](https://www.atlassian.com/software/jira) |
| Confluence | Part of the Atlassian suite, Confluence is a collaboration platform built around the idea of a wiki. Confluence was used for knowledge management throughout the project and for creating project and technical documentation for the prototype including sprint retro notes. | [Confluence Website](https://www.atlassian.com/software/confluence) |
| HipChat | Part of the Atlassian suite, HipChat provides a room based discussion mechanism that ties into the other components of the Atlassian suite. This was used as the primary communication mechanism throughout the prototype project. | [HipChat Website](https://www.atlassian.com/software/hipchat) |

Project Management Evidence
---------------------------

The following artifacts/images were created/taken during the project and illustrate the agile process and tools that were used for managing the project.

![The team executing a morning stand up](Project%20Management/morningstandup.jpg)  
*Image Caption:  The team executing a morning stand up with the delivery manager, product owner, and product manager front and center.*

![The working code is demonstrated in an early demo session](Project%20Management/earlydemo.jpg)  
*Image Caption:  The working code is demonstrated in an early demo session.  The UI is still being designed so at this point raw JSON is being shown instead of formatted search results.*

![Retrospective notes](Project%20Management/retro1.jpg)  
*Image Caption:  At the end of each sprint, a retrospective meeting was held to discuss and document what went well and what could be improved.*

![A sprint planning meeting being conducted by the product owner and the delivery manager](Project%20Management/sprintplanning.jpg)  
*Image Caption:  A sprint planning meeting being conducted by the product owner and the delivery manager.  Planning meetings were kept short and focused and were followed up by a standup.*

![White boards were used extensively to communicate agendas, blockers, questions, and other ad hoc documentation](Project%20Management/weekendagenda.jpg)  
*Image Caption:  White boards were used extensively to communicate agendas, blockers, questions, and other ad hoc documentation.  Using the whiteboards not only allowed for quick creation but also made the information more visible to the team.*

![JIRA was used to plan and track work](Project%20Management/jirareport.PNG)  
*Image Caption:  JIRA was used to plan and track work.  The report above shows how planned work progressed to completion throughout the 3 day weekend sprint.*
 
![An excerpt from the project page in Confluence](Project%20Management/confluence.PNG)  
*Image Caption:  An excerpt from the project page in Confluence.  Confluence was used to capture knowledge gained during the project and as a collaborative means to produce project and technical documentation.*
 
![An excerpt from HipChat](Project%20Management/hipchat1.PNG)  
*Image Caption:  An excerpt from HipChat, the central nervous system of the project, showing communications between the product manager and the delivery manager while team members awaited a demo.  This tool allowed for efficient team communication and maximized visibility of this communication to the team.*
 