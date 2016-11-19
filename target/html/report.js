$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("LongRunningProcessTriggerAutomation.feature");
formatter.feature({
  "line": 1,
  "name": "Long running Cordys Wokflow Scenario",
  "description": "",
  "id": "long-running-cordys-wokflow-scenario",
  "keyword": "Feature"
});
formatter.scenario({
  "comments": [
    {
      "line": 3,
      "value": "# Assumptions:"
    },
    {
      "line": 4,
      "value": "# This scanrio runs locally. And it is a template scenario"
    },
    {
      "line": 5,
      "value": "# In this we call a short lived Cordys workflow called CreateUpdateAsset"
    },
    {
      "line": 6,
      "value": "# We make a call to CreateUpdateAsset BPM with a template request"
    },
    {
      "line": 7,
      "value": "# And we verify the response."
    }
  ],
  "line": 9,
  "name": "Verify Long running Cordys Workflow",
  "description": "",
  "id": "long-running-cordys-wokflow-scenario;verify-long-running-cordys-workflow",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 10,
  "name": "I have data for Longrunning workflow request",
  "keyword": "Given "
});
formatter.step({
  "line": 11,
  "name": "I trigger the process request",
  "keyword": "When "
});
formatter.step({
  "line": 12,
  "name": "I should get response with instance id",
  "keyword": "Then "
});
formatter.match({
  "location": "LongRunningProcessSteps.getBPMRequestData()"
});
formatter.result({
  "duration": 144971746,
  "status": "passed"
});
formatter.match({
  "location": "LongRunningProcessSteps.callCreateUpdateBPMRequest()"
});
formatter.result({
  "duration": 362837675,
  "status": "passed"
});
formatter.match({
  "location": "LongRunningProcessSteps.verifyCreateUpdateAction()"
});
formatter.result({
  "duration": 1278924,
  "status": "passed"
});
});