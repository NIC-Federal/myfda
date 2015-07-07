[Return to Documentation Index](README.md)

User-Centered Design
====================

This section details the approach to user centered design (UCD) used on the prototype project and includes 
select artifacts produced during these UCD activities.  

Approach
--------

The NIC agile team employed User-Centered Design (UCD) techniques and processes throughout the project so 
that the web-based prototype was conceived, designed, and tested from the perspective of how it will be 
understood and used by a human user. The primary purpose of UCD on the prototype project was to design a 
system to support it's defined users’ existing beliefs, attitudes, and behaviors as they relate to the 
tasks that the system is being designed to support, thereby increasing repeated use and wider adoption.  

UCD professionals were integrated into the agile team and brought to the team expertise in accessibility, 
content strategy, information architecture, interaction design, user research, usability testing, user 
interface design, and visual design.

During initial project scoping and defining of epics and user stories, embedded user design professionals 
asked the following questions:

* What product is being developed?
* What information is going to be covered? Will it feature a particular topic or is it for a particular 
audience?
* What amount of research will the team pursue? Is there time built in for incremental adjustments based on 
those findings?
* What methods will be used research the user and test the usability?

At the project start the UCD experts identified the following primary research methods:  user research, 
personas, small group usability testing, large group surveying, and heuristic review.

User Research
-------------

During Sprint 0, a number of research activities were conducted to discover current demographic 
information for our target audience, which is the general public.  User research was conducted to try to 
answer the following about potential users.

* Who might use this?
* What do they know?
* How do they think of the information?
* Where, when, and how might they use it?

A summary of the research results on the user has been documented as its own article:

[User Research](User-Centered%20Design/User%20Research.md)

User Personas and Role Playing
------------------------------

Based on user research and analysis, user personas were developed during Sprint 0.  These personas have 
been placed into their own document located below.

[User Personas](User-Centered%20Design/UserPersonas.pdf)

User personas were continuous reminders of the users that we were targeting with our prototype 
application.  They were used extensively while developing epics and stories for the prototype applications 
to identify opportunities for specializing the solution to the needs of specific types of users.  When 
developing stories, the persona board was used to check that stories considered the different types of 
users and to assess business value by determining what personas would benefit from the story.

When the RFQ response period was extended, the team invented a novel role-playing activity that would help 
us sort out the priorities in the feature backlog.  This exercise was named Persona Value Pitching.  
Instead of dry discussion of potential features, the team members assumed the role of user personas and 
pitched the value of a feature they felt was useful to them.  After hearing all of the pitches, team 
members voted on the features that they thought were most important based on the pitches.  This provided 
scoring that the product owner and product manager considered in the final prioritization of the feature 
backlog.  For more information on the Persona Value Pitching exercise, please consult the [Project 
Management](Project%20Management.md) article.

Small Group Usability Testing
-----------------------------

Due to the short timeframe associated with the project guerrilla usability testing tactics were executed 
on wireframes, incomplete prototypes, and finally against the working user interface.  Tests were 
conducted with small sets of people, numbering between 3 and 5.  Testers were required to not be involved 
in the project or software development, design, or software product management.  Both concept-level and 
detailed-level task analysis was conducted with these small groups during the project.  Concept level 
testing tested the users ability to complete core user objectives.  Detailed-level task analysis focused 
on the users ability to complete the constituent tasks associated with achieving core user objectives.

The objectives of this small group testing were to answer the following questions:

* What is the goal of this task?
* What are the off-normal scenarios?
* What can go wrong?
* What information do I, the user, need to complete this task?
* What will I, the user, know/have accomplished when it's done?
* Why would I, the user, do this?

The first round of small group usability testing was performed on the initial wireframes for the 
prototype.  You can access a summary of the results using the link below.

[Usability Testing Summary 1](User-Centered%20Design/Usability%20Testing%20Summary%201.md)

The second round of small group usability testing was performed on the working release candidate 
software.  You can access of summary of the results using the link below.

[Second Round Usability Testing Results](User-Centered%20Design/MyFDA_UsabilityTestingResults.pdf)

Large Group Surveying
---------------------

When the implementation team encountered decision points involving the user's semantic or aesthetic 
preferences, surveys were created and sent to a large group of NIC employees.  The results of these 
surveys are shown below.

###Demographic Survey

In preparation for the project, NIC assembled groups of users from our many offices around the country.  
Nearly 200 NIC employees volunteered to serve as a sample user audience for the sake of surveys and other 
user center design activities.  A preliminary survey was sent out to learn about the demographics of these 
testers.  A link to the results of this survey is below.

[Demographic Survey Results](User-Centered%20Design/Demographic%20Survey%201.pdf)

###Branding and Interest Survey
The questions of the branding and interest survey centered around the name for the service and other critical terminology.  
Additionally, questions were asked to better understand the interest level of users in the service.

[Branding and Interest Survey Results](User-Centered%20Design/User%20Survey%201.pdf)
 
Heuristic Review
----------------

Throughout the project, the UCD focused team members had informal conversations where design artifacts 
were shown to other UCD professionals outside of the project.  These UX professionals helped to review 
critical decisions and usability options based on experience, known best practices, and design 
principles.  These reviews provided wider perspective to the UCD team as they considered usability testing 
feedback and formed UX improvement strategies.

The findings of these reviews are presented in their own section:

[Heuristic Review Summary](User-Centered%20Design/Heuristic%20Review%20Summary.md).

Accessibility Testing
---------------------

To ensure accessibility by all users, NIC took into consideration the standards of U.S. Section 508 and 
W3C such as the level of contrast in our design, using label tags for form elements, and ensuring sure our 
code is clean. To help achieve this, we used automated tools that allow us to run tests over the entire 
myFDA prototype website.  These tools provided detailed reports of all the potential issues and bugs for 
508 and WCAG compliance.

The following accessibility testing tools were used for the prototype development project.

| Tool Name | Tool Description | Website |
| :-------- | :--------------- | :------ |
| Colour Contrast Analyser | A desktop program to help determine the legibility of text and the contrast of visual elements. | [Website](http://www.paciellogroup.com/resources/contrastanalyser/) |
| WAVE | WAVE is web accessibility evaluation tool developed by WebAIM.org. It provides visual feedback about the accessibility of your web content by injecting icons and indicators into your page. | [Website](http://wave.webaim.org/) |
| CynthiaSays | CynthiaSays helps users identify errors in their Web content related to Section 508 standards and/or the WCAG guidelines for Web accessibility. | [Website](http://www.cynthiasays.com/) |
| W3C Markup Validation Service | A tool that helps check the validity of Web documents. | [Website](http://validator.w3.org/) |

Accessibility testing results generated from CynthiaSays can be viewed below.

[CynthiaSays Testing Results](User-Centered%20Design/Section508_MyFDA_CynthiaSays_Results.pdf)

Responsive Design
-----------------

Twitter Bootstrap was used as the responsive web design framework.  This is the leading framework for 
producing websites and web applications that work on across different types of client devices, from 
desktop computers to smartphones.  The user interface used Bootstrap to establish an elastic grid for 
content and controls within the application.  This grid automatically adjusts based on the size of the 
view being rendered.  Additionally, breakpoints are established where the behavior of the user interface 
will change to accommodate limitations of smaller screens.  For instance, an embedded search box at the 
top of the application will become a full width search bar when the screen resolution is smaller than a 
certain configured point.

Throughout the project, the application was tested by team members using a variety of different devices.  
This included usage on workstations, laptops, tablets, and smartphones.  From our user research, we fully 
expect that a large percentage of users will access this service from mobile devices.  Therefore we made 
sure that the team was not just using the working prototype on their workstations when testing their work.

![Responsive design of dashboard](User-Centered%20Design/placeit-dashboard.png)  
*Image Caption:  The dashboard shown across three different classes of device.*

![Responsive design of drug detail feature](User-Centered%20Design/placeit_feature.png)
*Image Caption:  The drug detail screen shown on three different classes of device.*

Design Tools
------------
The following project design tools were used for the prototype development project.

| Tool Name | Tool Description | Website |
| :-------- | :--------------- | :------ |
| Moqups | Moqups is a nifty HTML5 App used to create wireframes, mockups or UI concepts. This tool was used for wireframe creation, review, and evolution. | [Website](https://moqups.com/) |
| Adobe Photoshop | Adobe Photoshop is the leading raster graphics editor for digital artists. It was used to create and refine graphical and branding assets. | [Website](http://www.adobe.com/products/photoshop.html) |
| Adobe Illustrator | Adobe Illustrator is the leading vector graphics editor for digital artists. It was used to create and refine graphical and branding assets. | [Website](http://www.adobe.com/products/illustrator.html) |
| CODA 2 | CODA is a commercial and proprietary web development application for Mac OS X. | [Website](https://panic.com/coda/) |
| ATOM | ATOM is a text-editor made by GitHub. | [Website](https://atom.io) |

Design Evolution Artifacts
--------------------------

The following artifacts are being included in the documentation to demonstrate the evolution of MyFDA as 
design and development iterations and user-centered activities were completed.

###Prototype Conceptualization

Before design could begin in earnest, the vision for the prototype was captured from the Product Owner and 
Product Manager.  First, a mind map was constructed for potential features.  Using the persona stories 
developed prior to the product planning session, the team collaboratively defined the epics and stories. In turn, these epics and stories were put into JIRA and served as the basis for work.

![Mind map](User-Centered%20Design/mindmap.jpg)  
*Image Caption:  This mind map was drawn during the initial product visioning and planning session and shows the high-level feature concepts and their relations.*

![Epics and stories](User-Centered%20Design/epics_stories.jpg)  
*Image Caption:  As stories were identified they were written onto sticky notes and were organized on the wall into epics.  Once completed, this information was entered into JIRA but the physical version was left on the wall to ensure visibility and to allow team members to refactor stories as needed.*

###Wireframe Evolution

The team started design with wireframes so that stories and features could be quickly visualized, discussed, and tested against real users.

The following wireframes were created by the team during the first day of the project.  These were then tested through task based analysis with users.

[Initial Wireframes](User-Centered%20Design/Initial_Wireframes.pdf)

After usability testing was conducted on the wireframes, the wireframes were modified to address this new user understanding.

[Final Wireframes](User-Centered%20Design/Final_Wireframes.pdf)

###Dashboard Design Evolution

The screen captures below shows how the UI for the user dashboard evolved during the project.

![Screenshot](User-Centered%20Design/dashboard-anon.png)  
*Image Caption:  Screenshot of the initial functioning UI for the anonymous user dashboard.*

![Screenshot](User-Centered%20Design/dashboard-1.png)  
*Image Caption: Capture of the second design/development iteration which brought improved content and clearer layout.*

![Screenshot](User-Centered%20Design/dashboard-2.png)  
*Image Caption:  After the third design/develop iteration, this screen capture shows the improved calls to action and the content added based on heuristic review that had been conducted.*

![Screenshot](User-Centered%20Design/dashboard-final.png)  
*Image Caption:  This screen capture shows the final design for the dashboard.  This final design/develop iterations introduced improvements based on heuristic review and usability testing of the release candidate software.*

###Drug Details Design Evolution

MyFDA provides the ability to view recall, adverse event, and drug interaction information for medications.  These features of the prototype began as simple presentations and evolved into interactive visualizations.  Below is shown the evolution of the "Adverse Effects" portion of the drug details feature.

![Screenshot](User-Centered%20Design/drug-adverse-effects-list-1.png)  
*Image Caption:  The adverse effects detail feature as initially implemented for the prototype.*

![Screenshot](User-Centered%20Design/drug-adverse-effects-list-2.png)  
*Image Caption:  In the second design/development iteration for the adverse effects feature, definitions are added along with accordion navigation.*

![Screenshot](User-Centered%20Design/featuredesign.JPG)  
*Image Caption:  Features were typically designed collaboratively by the product owner and the lead designer using white board sketches.  This sketch shows the detailed design of the adverse effects detail feature.*

![Screenshot](User-Centered%20Design/drug-adverse-effects-details.png)  
*Image Caption:  The third design/develop iteration realized the design changes shown by the whiteboard sketch for this feature.  This version served as the release candidate.*

![Screenshot](User-Centered%20Design/adverse-effects-final.png)  
*Image Caption:  A final version of the drug-details screen showing improvements made after heuristic review and usability testing the feature with real users.*
