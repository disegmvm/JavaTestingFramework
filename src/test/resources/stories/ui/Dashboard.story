Meta:
@UI

Narrative:
As a RP user
I want to manage Dashboard
So that I can use RP more effectively

Lifecycle:
Before:
Scope: SCENARIO
Given user is logged into Report Portal
And user navigates to Dashboards page

Scenario: Update a Dashboard through UI
Given user selects the first available Dashboard
When user clicks at "Edit" button
And new name <dashboardName> is entered
And new description <dashboardDescription> is entered
And user clicks at "Update" button
Then validate the Dashboard name is <dashboardName>

Examples:
|dashboardName        |dashboardDescription  |
|NewUpdatedNameX      |NewDescriptionOfX     |
|OneMoreUpdatedNameX  |OneMoreDescriptionOfX |
