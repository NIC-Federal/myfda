[Return to Documentation Index](README.md)

About MyFDA
===========

This section of the wiki provides background on the project, the features of the MyFDA prototype,
and future features envisioned for the prototype concept.

Background
----------

The GSA released an RFQ for Agile Delivery Services asking bidders to provide Agile design and 
development services to the 18F group within the GSA.  As part of this RFQ, GSA has asked bidders 
to use agile delivery to produce a functioning prototype application, which utilizes at a minimum,
the openFDA API as a source of data.  As part of its proposal, NIC Federal, LLC (formerly named NIC 
Technologies, LLC) herein NIC, a leader in providing digital services to government developed the 
MyFDA prototype to demonstrate the potential uses of this data.  This prototype was developed over 
an eight day period with an agile team staffed with experienced technology and design resources.

To develop the concept, a group of innovative thinkers across NIC's business units were provided 
background information on the openFDA datasets and the general objectives of the prototype.  This
ideation team was asked to develop simple problem/solution proposals and to discuss these ideas 
as a group.  As the discussion progressed, the idea of providing a personalized portal to FDA 
drug information emerged because it presented the most value to the largest user audience and the 
team concluded that finding and understanding the open datasets published by the FDA was a 
compelling problem to solve for the general population.

Problem Statement
-----------------

The FDA publishes a wealth of valuable data on drugs, medical devices, and foods through their openFDA 
API.  For typical users this data is overwhelming to navigate and track.  The information has the 
potential to inform users about potential adverse effects and important safety recall information.  To 
provide true value to users, this vast data set should be related to their needs and presented in an 
easily consumable, meaningful format.

While the OpenFDA data is valuable to the user, inconsistencies in the data presented challenges in 
identifying a particular drug to retrieve the most relevant information.  Correlating this data to 
valuable information from other providers can be difficult and time consuming for users.  Thus, providing 
a single trusted and easily-consumed method to access such information would be of huge value to the user.

Solution Concept
----------------

To fully address this problem, the solution has four major themes built with a foundation for growth.  
First, to normalize the OpenFDA data to make it easily discoverable by users with limited medical 
knowledge.  Second, to analyze the data and provide the most relevant information in an easily consumed 
format to the user, enhanced by additional trusted datasources.  Third, to allow the user to identify 
themselves to the system and build a profile including their commonly taken medications through a simple 
one-click mechanism that would appear when viewing a drug.  Finally, to use the profile to put the data 
into simple-to-understand context for the user and provide immediate access to commonly taken medications.

MyFDA Prototype Features
------------------------

As part of the ideation process, every feature's value to the user was carefully evaluated before being 
added to a sprint.  The team invested time upfront to develop user personas and persona stories that could be 
quickly referenced when immediate user outreach was not possible.  For more information about our 
user-centered design practices please refer to the [User-Centered Design](User-Centered%20Design.md) article.
The features below are grouped by the themes that drove the team's sprint planning.

* **Find a Drug**
  * **Search** - The most basic user story of every user persona was the ability to find any drug tracked by the FDA.  MyFDA allows users to quickly find a drug by name.
  * **Autocomplete** - None of the personas have a medical background and medications are often difficult to spell or remember.  MyFDA uses the NLM DailyMed API to provide an autocomplete from the search box.
  * **Search Results** - To provide the best possible user experience NLM's RxNorm API was used to normalize the raw OpenFDA results to provide consistency and reduce duplication.
* **View a Drug**
  * **Drug Recalls** - Nearly all user personas indicated that recall information was the most important and relevant data provided by the FDA.  MyFDA shows recalls related to a drug the user is interested in and normalizes the distribution data to be presented on a map.  This allows users to quickly identify if it affected people in their area.
  * **Adverse Effects** - The second most important piece of information the users valued was adverse effects mostly to be informed before taking a new drug or to analyze potential symptoms after taking a drug.  MyFDA presents adverse effects reports through data analysis and visualization.  Only the most relevant data is presented to the user, in an easily consumable, graphical format including age and gender breakdowns as well as general severity based on patient outcomes.
* **Personalization**
  * **Login** - To deliver the personalized and trusted information valued by our users MyFDA needed to establish the user's identity.  MyFDA currently allows Facebook login and can be expanded to support other authentication mechanisms.
  * **Save a Drug** -  Many of our users were managing multiple medication lists, so MyFDA incorporates a simple way for users to save a drug to their MyMeds list as soon as they know it is relevant to them.  This meant adding prominent save buttons to both the search results and the drug details pages.
  * **MyMeds** - Our users were looking for quick access to the multiple medications they commonly take.  The MyMeds list allows the user to populate their MyMeds list and becomes their hub for further research and monitoring.
* **Added Value**
  * **Adverse Effect Definitions** - Based on user testing we found that many of the listed adverse effects were not easily understood by the test group.  MyFDA now uses the Merriam Webster medical dictionary to provide users with a definition of those medical terms so the terms can be understood by everyone.
  * **Drug Interactions** - Many of our user personas, especially those with higher risk factors (the mothers, senior, and the caregiver) wanted to research possible drug interactions.  MyFDA uses the NLM Interactions API to enhance the FDA data and presents the drug interactions along with the rest of the drug details offering a seamless experience to the user.
  * **Instructional Content** - As part of user testing we found that users clearly understood the step to search for a drug but they didn't understand what they could do with that data.  Content was added to the MyFDA landing page that informs users about the benefits of the system.

Feature Roadmap
---------------

As part of the ongoing persona analysis, participatory design, and usability testing, the MyFDA team 
generated a comprehensive backlog of features that could provide real user value moving forward with the 
project.  With the site launch, the team will conduct users surveys on the MyFDA app's Facebook page to 
determine the value of these stories and their inclusion in future sprints.  Below is the list of features 
that will be reviewed by the user community:

* **Side Effect Research** - Allow users to search against their MyMeds list for a symptom they are currently 
experiencing to determine if it could be caused by one of the drugs they are taking.  The results of the 
search would be a list of drugs the user takes ordered by the count of adverse effect reports submitted 
for that drug and effect.  Expanding the result would present the effect details similar to the drug 
details page.
* **MyMeds Quick Pick** - Allow users to quickly add common over-the-counter drugs to their MyMeds without leaving the MyMeds page.  MyFDA would track the most commonly added drugs and populate a list of the top twenty that could be added with a single click.
* **Drug Allergies** - Allow users to add drugs to an allergy list.  MyFDA would use this list to later warn the user if they try to add a drug from the same category.  
* **Multilingual Support** - Support languages other than English.  Medical terminology would not be affected 
but instructions, headings, and other page content would be presented in the desired language.
* **Multiple MyMeds List** - Allow users to add MyMeds lists for others they care for.  MyFDA would offer the 
same features but could be put in the context of different people.
* **Medication Notes** - Allow users to add notes to drugs in their MyMeds lists.  This could be used to track 
instructions from their doctors or just to remember past experiences in taking a particular drug.  This 
could be especially useful for users who manage the care of others to track the different reactions 
between those they care for.
* **Recall Notifications** - Allow users to opt-in for email notification when a drug listed in their MyMeds is 
recalled.  This would provide proactive information on recalls that users could use to inform themselves 
and act accordingly.
* **Food Recalls** - Allow users to opt-in for email notification when the FDA issues a food recall that affects 
the state they live in.
* **Medical Devices** - Expand the features of MyFDA to include medical device information.  
* **Remind Me** - Allow users to enter the time and/or frequency which they take a drug in their MyMeds list.  
MyFDA would then provide reminders to the user to take their medications and track that they did.
