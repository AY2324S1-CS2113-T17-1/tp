---
layout: page
title: Alexander Wolters’ Project Portfolio Page
---

## Project: AthletiCLI

**AthletiCLI** is an application used to track, analyse, and optimize the users athletic performance.
It is designed for committed athletes who not only keep track of their physical activities but also dietary habits,
sleep metrics, and more. The user interacts with it using a CLI. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code Contributed**:
[RepoSense Link](https://nus-cs2113-ay2324s1.github.io/tp-dashboard/?search=alwo223&breakdown=true)
* **New Features**:
  * Added the ability to add, delete and list activities
#### New Feature: Added the ability to add and delete activities
* What it does: Allows the user to add activities to the application with a variety of parameters. The user can also
  delete added activities.
* Justification: This feature is the core of the application as it allows the user to track their athletic performance
  and progress. It is also the basis for other features like the activity goal tracking.
* Highlights: It was challenging to find an elegant and efficient implementation which keeps code redundancy to a
  minimum, as it had to combine three different object types with some similar but also unique parameters. This was
  achieved by using inheritance, generic parser functions and extensive refactoring. The correct way of refactoring
  involved in-depth analysis.

### New Feature: Added command to list all activities
* What it does: Allows the user to list all tracked activities in two different ways: either as a quick overview or
  with all details.
* Justification: This feature allows the user to compare their performance and analyse their progress over time.
* Highlights: The implementation included a sorting mechanism by date and time, which had to be applied during any
  data modifying operations.

### New Feature: Added command to effortlessly edit activities
* What it does: Allows the user to edit any parameter of an activity.
* ...

### New Feature: Implemented a goal tracking mechanism for activities
...

### New Feature: find activities by date and timespan
... adopted by other team members

### New Feature: Implemented storing capabilities for activities and activity goals
* What it does: automatically stores all activities and activity goals in a file and loads them on startup of the
  application.
* Justification: This feature improves the product significantly by allowing the user to close the application and
  reopen it without losing any data. This is especially important as the application is designed to track the
  progress over a longer period of time.
* Highlights: This enhancement affects the storing functionality of other components like the sleep tracking as the
  implementation of these were based on the activity storing. This required the code to be very generic and involved
  some analysis of the existing code to reuse certain parser functions.
* Credits: The general idea was developed in collaboration with @nihalzp.

### Review / Mentoring
* [Reviewed PRs](https://github.com/AY2324S1-CS2113-T17-1/tp/issues?q=reviewed-by%3Aalwo223+) (examples: 
[#288](https://github.com/AY2324S1-CS2113-T17-1/tp/pull/288), 
[#280](https://github.com/AY2324S1-CS2113-T17-1/tp/pull/280),
[#95](https://github.com/AY2324S1-CS2113-T17-1/tp/pull/95),
[#21](https://github.com/AY2324S1-CS2113-T17-1/tp/pull/21))
* [Issues reported / discussed](https://github.com/AY2324S1-CS2113-T17-1/tp/issues?q=author%3Aalwo223+type%3Aissue)
* [Discussions in forum](https://github.com/AY2324S1-CS2113-T17-1/tp/discussions/110)

### Project Management
* Set up the GitHub repository and team organization for the project.
* Maintained issue tracker, including generating suitable labels.
* Responsible for ensuring proper testing of the implemented features.
* Strictly following deadlines, git conventions and forking workflow.

### Documentation
* User Guide:
  * Added documentation for the features `add-activity`, `add-run`, `add-swim`, `add-cycle`, `delete-activity`,
    `list-activity`, `edit-activity`, `edit-run`, `edit-cycle`, `edit-swim`, `set-activity-goal`: [Activity 
    Management]((../UserGuide.html#activity-management))
  * Improved overall visual appearance of the document: [#253](https://github.com/AY2324S1-CS2113-T17-1/tp/pull/253)
* Developer Guide:
  * Explained all implementation details in DG related to Activity Management, including `add-activity` and 
    `set-activity-goal` features, find by timespan, goal tracking mechanism, detailed parsing process, modular 
    implementation approach and justification: [Activity Management](../DeveloperGuide.html#activity-management)
  * Created UML diagrams: [Activity Inheritance](../images/ActivityInheritance.svg), 
  [Activity Goal Evaluation](../images/ActivityGoalEvaluation.svg), 
  [Activity Object Diagram](../images/ActivityObjectDiagram.svg), [Activity Parsing](../images/ActivityParsing.svg),
  [add-activity](../images/AddActivity.svg), [set-activity-goal](../images/AddActivityGoal.svg)

### Community
* PRs reviewed (with non-trivial review comments): [#139](https://github.com/nus-cs2113-AY2324S1/tp/pull/8#pullrequestreview-1709775159), [tp comments dashboard](https://nus-cs2113-ay2324s1.github.io/dashboards/contents/tp-comments.html)
* Reported bugs and suggestions for other teams in the class: [#139](https://github.com/nus-cs2113-AY2324S1/tp/pull/8#pullrequestreview-1709775159), [Issues created](https://github.com/AY2324S1-CS2113-W12-3/tp/issues?q=%22%5BPE-D%5D%5BTester+A%5D%22)

