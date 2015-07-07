[Return to Documentation Index](README.md)

Security
========

Explains how security was applied to the agile project and details the security techniques and tools used 
for the project and supporting hosting environment. 

Integrated Project Security
---------------------------

A strong security-focused design approach was taken from the inception of the project. A full-time 
security engineer was embedded in the Agile team throughout the course of the project to ensure that 
application security considerations were taken into account during each iteration of the development process. Development of 
the comprehensive security strategy for the project was identified as a story in Sprint 0. The strategy 
identified the secure technologies and tools for securing the software that are outlined in this section.

Secure Technologies
-------------------

In full support of the [HTTPS Everywhere](https://18f.gsa.gov/2014/11/13/why-we-use-https-in-every-gov-website-we-make/) initiative, this project can only be accessed via HTTPS.  The TLS 
connections are terminated currently at Amazon Elastic Load Balancer.  ELB has been configured according 
to the guidance provided by the [18F TLS standards](https://github.com/18F/tls-standards/tree/master/configuration#terminating-on-an-ec2-instance).  AWS ELB does not allow full compliance with this 
guidance, though.  If this prototype were to become a production system, instead of terminating the TLS 
connection at an Amazon Elastic Load Balancer, which results in less overall control of the TLS 
configuration, the ELB would forward the encrypted traffic back to an Nginx SSL proxy service located on 
load-balanced nodes. This would allow for us to properly maintain full control over the TLS configuration 
for this application and completely comply with the 18F TLS standards.  The [Qualys SSLLabs](https://www.ssllabs.com/ssltest/) tool has been used to 
validate that secure TLS configurations are in place.

Java was selected as the backend server development technology.  Java has a robust and consistent security 
model that protects applications from vulnerabilities that other server-side technologies are subject to.  
Using Spring only augments the security features of the base JDK.  For instance, Spring Social was used to 
enable Facebook authentication, resulting in a secure and abstracted method for handling identity.

Secure Coding
-------------

We have focused heavily on the [OWASP Top 10 Vulnerabilities](https://www.owasp.org/index.php/Top_10_2013-Top_10) during the prototype project to ensure that 
the appropriate security practices are followed during application development.  All NIC developers 
undergo ongoing security training to ensure that they understand how to avoid the most common and most 
critical types of vulnerabilities.

* A1 Injection
* A2 Broken Authentication and Session Management
* A3 Cross-Site Scripting (XSS)
* A4 Insecure Direct Object References
* A5 Security Misconfiguration
* A6 Sensitive Data Exposure
* A7 Missing Function Level Access Control
* A8 Cross-Site Request Forgery (CSRF)
* A9 Using Components with Known Vulnerabilities
* A10 Unvalidated Redirects and Forwards

Another aspect of the secure coding practices used for the prototype development project is peer review.  
All code produced for the prototype underwent peer review before being committed into the project.  
Potential security issues are just one of the things that peer reviewers look for when performing this 
important quality assurance activity.  When potential security issues were encountered by developers, the 
security engineer on the team was consulted to jointly develop a mitigation strategy.

Security Scanning
-----------------
To support the continuous delivery cycle, NIC has identified the following scanning tools for the project:

* [BurpSuite](http://portswigger.net/burp/)
* [OWASP ZAP](https://www.owasp.org/index.php/OWASP_Zed_Attack_Proxy_Project)
* [Gauntlt](http://gauntlt.org/)

Since this is prototype project, these tools were ran on-demand when development reached milestone 
releases.  If MyFDA were to become a true production application, these tools would be executed 
automatically when a new version of the software was deployed to a staging environment.  The reports 
generated from these tools would be reviewed by the security team and additional testing conducted as 
necessary to validate the security of the service before promotion of the software to production.  In our 
simplified prototype deployment architecture, security testing was performed when needed using a security 
engineer's workstation as a staging environment.

While automated security tests are helpful in identifying common vulnerabilities early on in the automated 
build process, it is important to note that conducting periodic manual testing by skilled application 
security professionals is key to ensuring that the application remains secure and stable. This manual 
testing was initiated at the end of milestone sprints during prototype development.  If this application 
would be further developed, this manual testing would be conducted whenever significant functionality 
changes are made or when deemed necessary by the security team.

It is NIC's policy to execute scheduled vulnerability scanning on a regular basis to scan applications and 
infrastructure for known vulnerabilities, misconfigurations, and outdated components.  However, as per 
Amazon policies, the Elastic Computing (EC2) instances that are used for this prototype deployment did not 
allow for running automated vulnerability scanning tools against them. However, if this project moved 
beyond being a prototype, formal approval will be obtained from Amazon to conduct the scanning against the 
environment.  Since we could not scan the software hosted in AWS, scanning was conducted on the production 
docker images in a local environment.

Continuous Security Monitoring
------------------------------

Additionally, a continuous monitoring program was developed to enhance our baseline security posture and 
address emerging risk on a continuous basis and throughout the product’s lifecycle.  This continuous 
monitoring program utilizes a risk-based approach and a combination of technical and managerial controls 
to ensure the security and privacy of citizen information, the product, and associated data.  This 
continuous security monitoring plan was executed for the prototype during the design and development effort.

Because the project is hosted in the Amazon Web Services cloud, one area of risk that has been identified 
is changes made to the hosting environment that could significantly impact overall security.  Therefore, 
one aspect of this continuous security monitoring program is continuous monitoring of the AWS 
environment.  The [SecurityMonkey](https://github.com/Netflix/security_monkey) tool has been deployed for continuously monitoring the AWS environment 
for configuration changes.  When this tool detects that changes have been made, it runs a set of business 
rules against each configuration, determines a level of risk associated with a particular configuration, 
and alerts the system administrators and security teams accordingly.

The continuous monitoring of the servers and applications themselves is achieved through a combination of [OSSEC](http://www.ossec.net/),  
[Logstash](https://www.elastic.co/guide/en/logstash/current/index.html), [ElasticSearch](https://www.elastic.co/products/elasticsearch), and [Kibana](https://www.elastic.co/products/kibana).  OSSEC is an Open Source Host-based Intrusion Detection System that performs log analysis, file 
integrity checking, policy monitoring, rootkit detection, real-time alerting and active response.  
Logstash is a tool for receiving, processing and outputting logs. This includes system logs, webserver 
logs, error logs, application logs, and many other supported sources.  ElasticSearch is used as a storage 
mechanism for Logstash and Kibana is used as a frontend reporting tool.  These tools combine to create a 
powerful and flexible continuous security monitoring solution that can evolve with the evolving threat 
landscape.
