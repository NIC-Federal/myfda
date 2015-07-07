[Return to Documentation Index](README.md)

Configuration Guide
===================

Details on configuring the MyFDA prototype in a production environment.

Configuration Overview
----------------------

The MyFDA application has been designed so that it can run without further configuration in a development 
environment.  This usually comes at the expense of features critical to the end user that can safely be 
ignored by developers such as scalability, reliability, and data persistence.   Eliminating these 
requirements from the development workflow simplifies the process of delivering quality changes quickly.   
If you want to participate in the development of the MyFDA application see the [Install Guide](Install%20Guide.md).

The information in this section is specific to optional configurations that are likely to be used in a 
production configuration.  In order to do this you can supply a application.properties file at runtime 
that overrides the defaults supplied by the project using the command line parameter 
"--spring.config.location".  For an example of this see the projects [Dockerfile](https://github.com/NIC-Federal/myfda/blob/master/Dockerfile).
Since this file is not required developers can run the production Docker container in there development environment and the 
application gracefully degrades in this environment.  The key to this design is the use of the -v and -l 
flag on the docker run command to map a volume on the host OS to a volume in the container and link to 
related containers respectively.  You can see how this is accomplished in the projects [docker-deploy.sh](https://github.com/NIC-Federal/myfda/blob/master/docker-deploy.sh) 
script.  Using the configuration volume specified using the -v flag you should supply environment specific 
configuration that enables all the features that are important to the application. MyFDA uses Spring Boots 
environment configuration framework please [see the documentation](http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html) for details of how this works.  In order 
to activate environment specific features simply create an application.properties file in the 
configuration volume.

Configuring Applications.properties
-----------------------------------

For a normal development install, you should not be required to change any of the applications.properties 
for the prototype to run.  For production usage, though, you will want to ensure that the following 
configuration is in place.

###OpenFDA Configuration

By default MyFDA does not send an API key when making requests of the OpenFDA APIs.  This means that the 
application is limited to significantly lower usage limits.  In a production environment you should [apply for an API key](https://open.fda.gov/api/reference/#your-api-key).
Once you have the key you can use it by placing a property named "api.fda.keys" with the value of your key.

###Facebook Configuration

By default MyFDA uses a Facebook test application that is configured to only allow login from the 
application if it is running on localhost:8080.  In order to run your application in production you will 
need to setup an application on https://developers.facebook.com that uses the hostname you want to use in 
your environment.  Below are the properties you will need to supply from the Facebook developers console:

| Property Name | Default Value | Description |
| :------------ | :------------ | :---------- |
| spring.social.facebook.app-id | 134844506846713 | This app id is assigned by facebook using the developer console. The default value uses a copy of the production application in test mode. This key will only allow you to use the application via the URL http://localhost:8080. To deploy the application in production you will need to create and configure your own application using the Facebook developer console. |
| spring.social.facebook.app-secret | 086b0a36bf2f54b852186d17999dcaf6 | This is the application secret key. The default value uses a copy of the production application in test mode. This key will only allow you to use the application via the URL http://localhost:8080. To deploy the application in production you will need to create and configure your own application using the Facebook developer console. |
| facebook.appNamespace | myfdatest | This is the application secret key. The default value uses a copy of the production application in test mode. This key will only allow you to use the application via the URL http://localhost:8080. To deploy the application in production you will need to create and configure your own application using the Facebook developer console. |

###MySQL Configuration

By default MyFDA uses a HSQLDB in-memory database.  This means that changes made to a users profile and 
Facebook login information are not persisted across restarts of the application.  In production this would 
be unacceptable.  The MyFDA also supports MySQL for production scenarios.   Installing and configuring 
MySQL is beyond the scope of this documentation.  The [MySQL documentation](http://dev.mysql.com/doc/refman/5.6/en/index.html) provides the information you 
will need to install and configure MySQL for use with MyFDA.  Once you have installed MySQL and created a 
user for the MyFDA application you will need to activate the MySQL profile in the application.properties 
file and provide properties detailing the username, password and JDBC URL to the MySQL database you would 
like to use.   Below are the properties you will need to set in the application.properties files to 
accomplish this:

| Property Name | Default Value | Description |
| :------------ | :------------ | :---------- |
| spring.profiles.active	hsqld	Be sure to set this to mysql to activate MySQL.
| mysql.username | None | The username of the MySQL user configured to run MyFDA.
| mysql.password | None | The password of the MySQL user configured to run MyFDA.
| mysql.jdbc.url | None | The JDBC URL of the MySQL user configured run MyFDA.

The schema will be created automatically as it is required so long as the MySQL user assigned has the 
proper permissions.
