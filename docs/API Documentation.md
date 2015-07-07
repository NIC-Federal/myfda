[Return to Documentation Index](README.md)

API Documentation
=================

To support the functionality of the prototype frontend, a set of RESTful API endpoints were created.  
These are used by the frontend of the prototype but have the potential to be used by other web 
applications or native mobile applications.
 
General API Information
-----------------------

The MYFDA application provides a Level 3 RESTful web service on the Richardson Maturity Model for REST web 
services.  For instance, users of the API can find the URI of the authenticated user by issues a GET to 
/user and recieve a 201 with the URI of the authenticated user in the Location header of the response.  
Performing a GET on this URI results in retrieving a user profile resource which can have a link to the 
users My Meds portofolio.  If the user does not have a My Meds portofolio yet one can be created by 
issuing a POST to /protofolio.  If the portfolio is successfully created a 201 is returned with the URI of 
the portofolio in the Location header of the response.  This URI can be added to the links in the user 
profile resource and a PUT issues to update the user.  This use of HTTP verbs and links is central to 
creating a Level 3 web service.

API Endpoints
-------------

| Endpoint URL | Arguments | Query Parameters | Notes |
| :----------- | :-------- | :--------------- | :---- |
| /drug |      | name (the drug name to search)<br/>limit (applies the limit to the result set, default=10)<br/>skip ( applies the skip to the result set, default=0) | Queries 1) OpenFDA api 2)NLM Rxcui api<br/>Retrieves all the drug unii for the name being searched,<br/>Retrieves all the brand names for the unii.<br/>Rates the drug results in for best match.<br/>Sort : Ascending: As the brand name best match rating |
| /autocomplete |    | name (the name of the drug to search) | Queries: NLM dailymed autocomplete API<br/>Returns autocomplete drug names for all the possible matches for a given drug name<br/>Filter: Applied on identical first name of the drug.<br/>limit: limits to 10 results, if identical first name is found. |
| /drug/recalls |    | limit (applies the limit to the result set, default =5)<br/>fromDt (drug recalls to search for from date, MMDDYYYY)<br/>toDt (drug recalls to search with in to date, MMDDYYYY) | Queries OpenFDA api<br/>Returns the most recent drug recalls.<br/>Sort: Descending order: report_date<br/>Default period : last 2 months from current date |
| /drug/enforcements |   | unii (the unii to be searched for the enforcements)<br/>limit (applies the limit to the result set) | Queries OpenFDA api<br/>Returns all the enforcements for a given unii.<br/>Sort: Descending order: report_date.<br/>Returns NOT_FOUND, if enforcements are not found for a given unii.<br/>Returns most recent drug recalls, if unii is not provided |
| /event |   | unii (the unii to retrieve all the adverse effects)<br/>limit (applies the limit to the result set, default=10)<br/>skip ( applies the skip to the result set, default=0) | Queries: 1) OpenFDA api 2) Merriam Webster dictionary (for effect descriptions)<br/>Returns all the adverse effects for a given unii.<br/>Sort: Descending order: as per the total cases filed for an effect. |
| /drug/ | {id} |   | AUTH api: GET drug by Id<br/>returns the drug name and unii for a saved drug in the users portfolio
| /drug/ | {id} |   | AUTH api: DELETE drug by id<br/>deletes the saved drug from an users portfolio
| /api/drug |   |   | AUTH api: POST json<br/>adds an drug to the users portfolio |
| /portfolio | {id} |   | AUTH api: GET user portfolio<br/>Returns an user portfolio by user id |
| /portfolio | {id} |   | AUTH api: DELETE user portfolio<br/>Deletes an user portfolio by user id |
| /portfolio | {id} |   | AUTH api: PUT user portfolio<br/>updates an user portfolio by user id and provided json |
| /portfolio |   |   | AUTH api: POST user portfolio<br/>Creates an new user portfolio from the user provided json body. |
| /user |   |   | AUTH api: GET user profile<br/>Returns an user profile based on the logged in user |
| /user | {id} |   | AUTH api: GET user profile by Id<br/>Returns an user profile by user id |
| /user |   |   | AUTH api: POST user profile<br/>creates an user profile from the user provided json |
| /signup |   |   | AUTH api: GET<br/>creates an user profile and default portfolio based on the oAuth login token |
