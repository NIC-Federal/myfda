package com.nicusa.testing.tests

import com.nicusa.testing.pages.IndexPage
import geb.Browser
import org.junit.Test

class HelloWorldTest {

    @Test
    public void contentPShouldContainHelloWorld()
    {
        def browser = new Browser()
        browser.drive {
            to IndexPage
            assert contentP.text().contains('Hello World')
        }

    }
}
