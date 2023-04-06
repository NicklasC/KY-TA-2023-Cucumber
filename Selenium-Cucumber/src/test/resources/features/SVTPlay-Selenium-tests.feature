Feature: Test page navigations and correct browser titles

  Scenario: SVT Play should show correct title
    Given user visits SVT Play
    Then the browser title should be "SVT Play"

  Scenario: SVT Play logo should be visible
    Given user visits SVT Play
    Then logo should be visible

  Scenario:Start link should exist
    Given user visits SVT Play
    Then start link should exist and have the text "START"

  Scenario:Program link should exist
    Given user visits SVT Play
    Then program link should exist and have the text "PROGRAM"

  Scenario:Kanaler link should exist
    Given user visits SVT Play
    Then kanaler link should exist and have the text "KANALER"

  Scenario:Tillgänglighet link should exist
    Given user visits SVT Play
    Then tillganglighet link should exist

  Scenario:Clicking Tillgänglighet link should lead to correct page
    Given user visits SVT Play
    When user clicks tillganglighet link
    Then tillgänglighet page should display with header text "Så arbetar SVT med tillgänglighet"

  Scenario:Verify 17 program categories is shown
    Given user visits SVT Play
    When user clicks on Program link
    Then "17" categories are shown

  Scenario:Verify you can search on the site
    Given user visits SVT Play
    When user search for the text "ag"
    Then search results header for text "ag" should display

  Scenario:Cookie information link should display
    Given user visits SVT Play
    Then cookie information link should display

  Scenario:Cookie information link should lead to correct page
    Given user visits SVT Play
    When user clicks cookie information link
    Then cookie information page should display

  Scenario:TV tablå for SVT 1 should display
    Given user visits SVT Play
    When user clicks kanaler link
    Then TVTablå for "SVT 1" should display

  Scenario:TV tablå for SVT 2 should display
    Given user visits SVT Play
    When user clicks kanaler link
    Then TVTablå for "SVT 2" should display

  Scenario:TV tablå for SVT Barn should display
    Given user visits SVT Play
    When user clicks kanaler link
    Then TVTablå for "SVT Barn" should display

  Scenario:TV tablå for Kunskapskanalen should display
    Given user visits SVT Play
    When user clicks kanaler link
    Then TVTablå for "Kunskapskanalen" should display

  Scenario:Nyhetsbrev link displays
    Given user visits SVT Play
    Then nyhetsbrev link should display

  Scenario:Nyhetsbrev link leads to correct page
    Given user visits SVT Play
    When user clicks Nyhetsbrev link
    Then nyhetsbrev page should display

  Scenario:Search for Agenda shows first in the search result
    Given user visits SVT Play
    When user search for the text "Agenda"
    Then search results header for text "Agenda" should display
    Then program number "1" in the program list should be "Agenda"

  Scenario:Search for Pistvakt and verify number of episodes in season 2
    Given user visits SVT Play
    When user search for the text "Pistvakt"
    Then search results header for text "Pistvakt" should display
    When user clicks Pistvakt
    #Collapsing season1 below
    And clicks Pistvakt season one
    #Expanding season 2
    And clicks Pistvakt season two
    #Now only season 2 episodes are displaying
    Then "6" number of episodes are displaying
    Then episode "5" is called "5. Personalfestan"

  Scenario:Dator link displays and navigates to correct page
    Given user visits SVT Play
    Then dator link should display
    When user clicks on dator link
    Then computer requirements page displays

  Scenario:Search for Karen Pirie: Kalla fall shows first in the search result
    Given user visits SVT Play
    When user search for the text "Karen Pirie: Kalla fall"
    Then search results header for text "Karen Pirie: Kalla fall" should display
    When user clicks Karen Pirie: Kalla fall
    Then "3" number of episodes are displaying
    Then episode "1" is called "Avsnitt 1"






