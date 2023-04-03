Feature: Test SVT Play browser titles

Scenario: SVT Play should show correct title
Given SVT play is available
When User visits SVT Play
Then The title should be "SVT Play"

