[Return to Documentation Index](README.md)

Quality Assurance
=================

Describes the quality and testing processes associated with the prototype project.

In-Line Testing
---------------

The following types of testing are done inline during the CI/CD build process.  These tests ensure that 
code changes have not impacted expected functionality of components and subsystems and that acceptance 
criteria have been met.  These tests must be successfully passed before the CD system will deploy the new 
version of the application to production.

###Unit Testing

Unit tests test the smallest unit of functionality, typically a method/function.  Unit tests are developed 
for each functional aspect of objects and classes.  Below is an example of a unit test from the prototype.

```
@Test
public void getNameShouldReturnTheValuePassedInSetName() throws Exception {
        assertThat(drug.getId(), is(nullValue()));
        drug.setName("unikitty");
        drug.setUnii("6M3C89ZY6R");
        assertThat(drug.getName(), is("unikitty"));
        assertThat(drug.getUnii(), is("6M3C89ZY6R"));
}
```
*Code excerpt taken from:  [DrugTest.java](https://github.com/NIC-Federal/myfda/blob/master/src/test/java/com/nicusa/domain/DrugTest.java)*

###Integration Testing

Integration tests combine units of code and test that the resulting combination of functionality, for 
instance a subsystem within an application.  Below is an example of an integration test in the server-side 
code.

```
@Test
public void testAutoComplete() throws Exception
{
    MvcResult result = mockMvc.perform(get("/autocomplete?name=Adv"))
            .andExpect(status().isOk()).andReturn();
    JsonNode node =  objectMapper.readTree(result.getResponse().getContentAsString());
    assertNotNull(node);
    assertTrue(node.isArray());
    assertTrue(node.size() > 8 );
    assertTrue(node.get(0).asText().trim().equalsIgnoreCase("ADVAIR"));
    assertTrue(node.get(7).asText().trim().equalsIgnoreCase("ADVICOR"));
    assertTrue(node.get(8).asText().trim().equalsIgnoreCase("ADVIL"));
}
```
*Code excerpt taken from:  [DrugControllerIT.java](https://github.com/NIC-Federal/myfda/blob/master/src/test/java/com/nicusa/controller/DrugControllerIT.java)*

On-Demand Testing
-----------------

Since this was a prototype application, and not a production application, certain testing was done on 
demand as needed throughout the project.  If this application were to be deployed for production use, then 
these tests would also be integrated into the CI/CD process.  In a true production deployment of MyFDA, 
the CI/CD system would spin up a staging host and execute these tests automatically against the deployment 
candidate before promoting the code to production.

###Functional Testing

Functional tests test a particular feature for an expected result for a given input.  Essentially, 
functional tests act as the user to ensure that the end user functionality is functioning correctly.  To 
perform functional testing for the prototype, a developer would build the latest version of the software 
and would run the functional tests locally within their development environment.

[Sahi Pro](http://sahipro.com/) was used as the testing tool for our on-demand functional testing process. It allowed the team to 
increase their efficiency and productivity by simulating real user interaction and verification of 
functionality within multiple modern browsers at once.

###Accessibility Testing

Another type of on-demand testing that was performed was accessibility testing.  To ensure accessibility 
by all users, NIC took into consideration the standards of U.S. Section 508 and W3C such as the level of 
contrast in our design, using label tags for form elements, and ensuring sure our code is clean. To help 
achieve this, we used automated tools that allow us to run tests over the entire myFDA prototype website.
For details on accessibility testing, please consult the UCD section:  [User-Centered Design/Accessibility Testing](User-Centered%20Design.md#accessibility-testing).

###Security Testing

Security is a primary but often overlooked aspect of quality when it comes to government websites.  At 
project start, a security engineer defined the strategy for securing and validating the security of the 
MyFDA prototype.  This engineer then executed security testing at the end of milestone sprints.  For more 
information on security testing and the overall security strategy, please consult the [Security](Security.md) article.

###Peer Review

Embarking on such a broadly functional prototype meant we would not have time to waste chasing bugs.  All 
code underwent review and validation before being committed into the master branch of the repository and 
deployed to production.  The technical lead for the project was the chief reviewer of code and the 
guardian of the master branch.  Developers worked on their own branch of the software and would send a 
pull request when their code had met the established acceptance criteria.  The technical lead would then 
review their code as he merged the changes into the master branch.  If the code contribution was coming 
from the technical lead himself, then another developer would review and merge the pull from his working 
branch.  This little extra time taken to carefully review code for defects and quality issues led to 
better quality code and a better quality end product.
