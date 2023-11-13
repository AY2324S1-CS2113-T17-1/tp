---
layout: page
title: Alexander Wolters’ Portfolio
---

## Project: AthletiCLI

**AthletiCLI** is a Java-based application designed for dedicated athletes to track, analyse, and optimize their athletic 
performance. This tool not only monitors physical activities but also dietary habits,
sleep metrics, and more. The user interacts through a user-friendly CLI. The project comprises about 11 kLoC.

View my **code contributions** on [RepoSense](https://nus-cs2113-ay2324s1.github.io/tp-dashboard/?
search=alwo223&breakdown=true). The key enhancements are summarized below.

#### New Features
  * **Added the ability to add, list and delete activities**
    * Purpose: This feature is the core of activity management as it enables users to track their athletic 
      performance and progress.
    * Highlights: Implementing an elegant, efficient and unified solution for the different activity types with 
      unique parameters and commands was a complex challenge. This was achieved by leveraging inheritance, generic 
      parser functions and extensive refactoring leading to a modular and efficient design. Therefore, this approach involved in-depth analysis and planning.
  * **Added command to list all activities**
    * Purpose: This feature allows users to view their activities chronologically and in different levels of detail, 
      aiding in performance analysis and progress tracking.
    * Highlights: The implementation included a sorting mechanism by date and time and ensured data consistency 
      during any data modifying operations. 
  * **Added command to effortlessly edit activities**
    * Purpose: This feature is essential for users to correct or update their activities without 
      replacing the whole entry, thus enhancing the user experience.
    * Highlights: Similar to the add command, this feature required a modular implementation approach to handle the 
      different activity and to effectively reuse existing parser functions.
  * **Implemented parsing and unparsing functionality for activity (goal) storing**
    * Purpose: This features allows to store all activities and activity goals in a file and parse them on startup 
      of the application. This feature improves the product significantly by enabling to close and reopen the 
      application without any data loss.
    * Highlights: The approach was developed in collaboration with @nihalzp. The storing functionality of other 
      tracking components like sleep were based on this implementation. It involved some analysis of the existing code 
      to efficiently reuse existing parser functions.
  * **Implemented goal tracking mechanism and find feature for activities**
    * Purpose: empowers users to set and monitor goals for different periods, sports and metrics. It is essential 
      for the user to plan their training and to push themselves to improve. 
      This also comes with the ability to find activities by date.
    * Highlights: The implementation was adopted for other goal tracking mechanism like sleep and diet.

#### Review / Mentoring
* [Reviewed PRs](https://github.com/AY2324S1-CS2113-T17-1/tp/issues?q=reviewed-by%3Aalwo223+) (examples: 
[#288](https://github.com/AY2324S1-CS2113-T17-1/tp/pull/288), 
[#280](https://github.com/AY2324S1-CS2113-T17-1/tp/pull/280),
[#95](https://github.com/AY2324S1-CS2113-T17-1/tp/pull/95),
[#21](https://github.com/AY2324S1-CS2113-T17-1/tp/pull/21))
* [Issues reported / discussed](https://github.com/AY2324S1-CS2113-T17-1/tp/issues?q=author%3Aalwo223+type%3Aissue)
* [Discussions in forum](https://github.com/AY2324S1-CS2113-T17-1/tp/discussions/110)

#### Project Management
* Set up the GitHub repository and team organization for the project.
* Maintained issue tracker, including generating suitable labels.
* Responsible for ensuring proper testing of the implemented features.
* Managed final release v2.1.
* Strictly following deadlines, git conventions and forking workflow.

#### Documentation
* User Guide:
  * Added documentation for the features `add-activity`, `add-run`, `add-swim`, `add-cycle`, `delete-activity`,
    `list-activity`, `edit-activity`, `edit-run`, `edit-cycle`, `edit-swim`, `set-activity-goal`: [Activity 
    Management](../UserGuide.html#-activity-management)
  * Improved overall visual appearance of the document: [#253](https://github.com/AY2324S1-CS2113-T17-1/tp/pull/253)
* Developer Guide:
  * Explained all implementation details in DG related to Activity Management, including `add-activity` and 
    `set-activity-goal` features, find by timespan, goal tracking mechanism, detailed parsing process, modular 
    implementation approach and justification: [Activity Management](../DeveloperGuide.html#activity-management-in-athleticli)
  * Created UML diagrams: [#1](../images/ActivityInheritance.svg), 
  [#2](../images/ActivityGoalEvaluation.svg), 
  [#3](../images/ActivityObjectDiagram.svg), [#4](../images/ActivityParsing.svg),
  [#5](../images/AddActivity.svg), [#6](../images/AddActivityGoal.svg)
  * Provided test instructions for activity related features

#### Community
* PRs reviewed (with non-trivial review comments): [#139](https://github.com/nus-cs2113-AY2324S1/tp/pull/8#pullrequestreview-1709775159), [tp comments dashboard](https://nus-cs2113-ay2324s1.github.io/dashboards/contents/tp-comments.html)
* Reported bugs and suggestions for other teams in the class: [#139](https://github.com/nus-cs2113-AY2324S1/tp/pull/8#pullrequestreview-1709775159), [Issues created](https://github.com/AY2324S1-CS2113-W12-3/tp/issues?q=%22%5BPE-D%5D%5BTester+A%5D%22)
