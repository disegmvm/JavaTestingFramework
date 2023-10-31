Meta:
@API

Narrative:
As a RP user
I want to manage Dashboard
So that I can use RP more effectively

Scenario: Create a Dashboard through API
Given a POST request to create a Dashboard with name "$dashboardName" and description "$dashboardDescription"
When I send POST request to create a Dashboard
Then the Dashboard is created

Examples:
|dashboardName            |dashboardDescription    |
|TestDashboardName        |TestDescription         |
|AnotherTestDashboardName |AnotherTestDescription2 |

Scenario: Update a Dashboard through API
Given an existing Dashboard
When I send PUT request to update an existing Dashboard with name "$dashboardName" and description "$dashboardDescription"
Then the Dashboard is updated

Examples:
|dashboardName                |dashboardDescription          |
|NewUpdatedDashboardName      |NewDescriptionOfDashboard     |
|OneMoreUpdatedDashboardName  |OneMoreDescriptionOfDashboard |
