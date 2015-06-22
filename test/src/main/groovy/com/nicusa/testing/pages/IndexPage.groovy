package com.nicusa.testing.pages

import geb.Page

/**
 * Created by mchurch on 6/21/15.
 */
class IndexPage extends Page {

    static url = 'http://user:password@localhost:8080'

    static at = {
        $('.container > h1:nth-child(1)').text().contains('Greeting')
    }

    static content = {
        contentP {
            $('p.ng-binding:nth-child(2)')
        }
    }
}