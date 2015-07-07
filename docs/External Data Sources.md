[Return to Documentation Index](README.md)

External Data Sources
=====================

The following external data sources are used by the MyFDA prototype.  Some of these data sources are 
directly accessed from the client layer while others are accessed by the server-side components, with the 
data provided to the MyFDA's user interface through MyFDA's own REST API.

| API Dependency | Endpoint | Description | Documentation |
| :------------- | :------- | :---------- | :------------ |
| openFDA - Adverse Drug Events | /drug/event | API endpoint for adverse drug events. An adverse event is submitted to the FDA to report any undesirable experience associated with the use of a drug, including serious drug side effects, product use errors, product quality problems, and therapeutic failures. This data was used for visualizing adverse drug effects in the drug details portion of the application. | [Documentation](https://open.fda.gov/drug/event/) |
| openFDA - Drug Enforcement Reports | /drug/enforcement | API endpoint for all drug product recalls monitored by the FDA. This data source was used for displaying recalls for a specific medication and for displaying the most recent recalls on the application's start screen. | [Documentation](https://open.fda.gov/drug/enforcement/) |
| openFDA - Drug Labeling | /drug/label | Every prescription drug (including biological drug products) approved by FDA for human use comes with FDA-approved labeling. The drug product labeling API provides data for prescription and over-the-counter (OTC) drug labeling. This endpoint was used when conducting searches for medication. | [Documentation](https://open.fda.gov/drug/label/) |
| NLM - Drug Interaction API | /REST/interaction | The Interaction API is a web service for accessing drug-drug interactions. This API was used to detect interactions between drugs when viewing drug details. | [Documentation](http://rxnav.nlm.nih.gov/InteractionAPIs.html) |
| NLM - RxNorm API | /REST/ | RxNorm provides normalized names for clinical drugs and links its names to many of the drug vocabularies commonly used in pharmacy management and drug interaction software. Used to relate (NORalize) the FDA IDs to the RxNormID in order to access other NLM data sources. | [Documentation](http://rxnav.nlm.nih.gov/RxNormAPIs.html) |
| NLM - DailyMed API | /dailymed/services/ | DailyMed provides trustworthy information about marketed drugs in the United States. DailyMed is the official provider of FDA label information (package inserts). This API is used to provide autocomplete search suggestions for users when they are entering search terms. | [Documentation](https://dailymed.nlm.nih.gov/dailymed/index.cfm) |
| Merriam-Webster Dictionary API | /api/v1/references/medical/ | The Merriam-Webster Dictionary API gives developers access to a comprehensive resource of dictionary and thesaurus content as well as specialized medical, Spanish, ESL, and student-friendly vocabulary. Used by MyFDA to provide descriptions of adverse effect terminology. | [Documentation](http://www.dictionaryapi.com/) |
