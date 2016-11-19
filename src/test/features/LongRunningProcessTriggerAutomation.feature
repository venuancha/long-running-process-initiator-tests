Feature: Long running Cordys Wokflow Scenario

# Assumptions:
# This scanrio runs locally. And it is a template scenario
# In this we call a short lived Cordys workflow called CreateUpdateAsset
# We make a call to CreateUpdateAsset BPM with a template request
# And we get the response and we store it in a csv file for futher use
 
Scenario: Verify Long running Cordys Workflow
Given I have data for Longrunning workflow request
When I trigger the process request
Then I should get response with instance id
