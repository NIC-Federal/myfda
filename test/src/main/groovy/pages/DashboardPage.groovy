package pages

import geb.Page

class DashboardPage extends MyFDAPage {
    static url = System.properties['application.context.root']

    static at = {
        waitFor(30) {
            $('#hero > div > div > h1').text().startsWith("Search for Information")
        }
    }

    static content = {
        largeSearchButton {
            $('#large-search-button')
        }
        largeSearchTextBox {
            $('#large-search-text-box')
        }
    }
}
