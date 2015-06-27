package com.nicusa.testing.tests

import groovyx.net.http.HTTPBuilder
import groovyx.net.http.RESTClient
import org.apache.http.client.params.ClientPNames
import org.apache.http.params.BasicHttpParams
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Value
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

class UserControllerTest {

    //def String apiUserEndpoint = System.properties['api.user.endpoint']

    @Test
    public void findAuthenticatedUser() {
        def userApi = new HTTPBuilder( 'http://localhost:8080' )
        userApi.client.params.setBooleanParameter(ClientPNames.HANDLE_REDIRECTS, false)
        def userResourceResponse = userApi.get(path: '/user') { response ->
            assert response.status == 302
            assert response.headers.Location == 'http://localhost:8080/user/0'
        }
    }

}
