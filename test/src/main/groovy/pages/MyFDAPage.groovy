package pages

import geb.Page

class MyFDAPage extends Page {
    static at = {
        $("#myfda-logo") != null
    }
    static content = {
        facebookLoginButton {
            $("#fb_signin")
        }
    }
}
