Feature: Test page navigations and correct browser titles

  Scenario: SVT Play should show correct title
    Given User visits SVT Play
    Then The browser title should be "SVT Play"


  Scenario: SVT Play logo should be visible
    Given User visits SVT Play
    Then Logo should be visible

  Scenario:Start link should exist
    Given User visits SVT Play
    Then Start link should exist and have the text "START"

  Scenario:Program link should exist
    Given User visits SVT Play
    Then Program link should exist and have the text "PROGRAM"

  Scenario:Kanaler link should exist
    Given User visits SVT Play
    Then Kanaler link should exist and have the text "KANALER"

  Scenario:Tillgänglighet link should exist
    Given User visits SVT Play
    Then Tillganglighet link should exist

  Scenario:Clicking Tillgänglighet link should lead to correct page
    Given User visits SVT Play
    When User clicks tillganglighet link
    Then Tillgänglighet page should display with header text "Så arbetar SVT med tillgänglighet"

  Scenario:Verify 17 program categories is shown
    Given User visits SVT Play
    When User clicks on Program link
    Then "17" categories are shown

  Scenario:Verify you can search on the site
    Given User visits SVT Play
    When User search for the text "ag"
    Then search results header for text "ag" should display.

  Scenario:Cookie information link should display
    Given User visits SVT Play
    Then Cookie information link should display

  Scenario:Cookie information link should lead to correct page
    Given User visits SVT Play
    When User clicks cookie information link
    Then Cookie information page should display

  Scenario:TV tablå for SVT 1 should display
    Given User visits SVT Play
    When User clicks kanaler link
    Then TVTablå for "SVT 1" should display.

  Scenario:TV tablå for SVT 2 should display
    Given User visits SVT Play
    When User clicks kanaler link
    Then TVTablå for "SVT 2" should display.

  Scenario:TV tablå for SVT Barn should display
    Given User visits SVT Play
    When User clicks kanaler link
    Then TVTablå for "SVT Barn" should display.

  Scenario:TV tablå for Kunskapskanalen should display
    Given User visits SVT Play
    When User clicks kanaler link
    Then TVTablå for "Kunskapskanalen" should display.

  Scenario:Nyhetsbrev link displays
    Given User visits SVT Play
    Then Nyhetsbrev link should display

  Scenario:Nyhetsbrev link leads to correct page
    Given User visits SVT Play
    When User clicks Nyhetsbrev link
    Then Nyhetsbrev page should display

  Scenario:Search for Agenda shows first in the search result
    Given User visits SVT Play
    When User search for the text "Agenda"
    Then search results header for text "Agenda" should display.
    Then program number "1" in the program list should be "Agenda"

  Scenario:Search for Pistvakt and verify number of episodes in season 2
    Given User visits SVT Play
    When User search for the text "Pistvakt"
    Then search results header for text "Pistvakt" should display.
    When user clicks Pistvakt
    #Collapsing season1 below
    And clicks Pistvakt season one
    #Expanding season 2
    And clicks Pistvakt season two
    #Now only season 2 episodes are displaying
    Then "6" number of episodes are displaying
    Then episode "5" is called "5. Personalfestan"




