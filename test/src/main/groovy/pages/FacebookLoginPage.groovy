package pages

import geb.Page

/**
 * Created by mchurch on 7/1/15.
 */
class FacebookLoginPage extends Page {
    static at = {
        waitFor(30) {
            $('#email')
        }

    }
}
