# Alexander Wolters Project Portfolio Page

# Project: AthletiCLI

## Overview
**AthletiCLI** is an application used to track, analyse, and optimize the users athletic performance.
It is designed for committed athletes who not only keep track of their physical activities but also dietary habits,
sleep metrics, and more. The user interacts with it using a CLI. It is written in Java, and has about 10 kLoC.

## Summary of Contributions
Given below are my contributions to the project.

### New Feature: Added the ability to add and delete activities
* What it does: Allows the user to add activities to the application with a variety of parameters. The user can also
  delete added activities.
* Justification: This feature is the core of the activity management as it allows the user to track their athletic 
  performance and progress. It is also the basis for other features like the activity goal tracking.
* Highlights: It was challenging to find an elegant and efficient implementation which keeps code redundancy to a 
  minimum, as it had to combine three different object types with some similar but also unique parameters. This was 
  achieved by using inheritance, generic parser functions and extensive refactoring which involved in-depth analysis.

### New Feature: Added command to list all activities 
* What it does: Allows the user to list all tracked activities in two different ways: either as a quick overview or 
  with all details.
* Justification: This feature allows the user to compare their performance and analyse their progress over time.
* Highlights: The implementation included a sorting mechanism by date and time, which had to be applied during any 
  data modifying operations.

### New Feature: Added command to effortlessly edit activities
* What it does: Allows the user to edit any parameter of an activity. 
... (optinal parameters)

### New Feature: Implemented a goal tracking mechanism for activities
... (incl finding by date and timespan)

### New Feature: Implemented storing capabilities for activities and activity goals
* What it does: automatically stores all activities and activity goals in a file and loads them on startup of the 
  application.
* Justification: This feature improves the product significantly by allowing the user to close the application and 
  reopen it without losing any data. This is especially important as the application is designed to track the 
  progress over a longer period of time.
* ...

### Code Contributed
[RepoSense Link](https://nus-cs2113-ay2324s1.github.io/tp-dashboard/?search=alwo223&breakdown=true)

### Project Management
...

### Documentation
* User Guide:
    * Added documentation for the features `add-activity`, `add-run`, `add-swim`, `add-cycle`, `delete-activity`,
      `list-activity`, `edit-activity`, `set-activity-goal`
    * ...
* Developer Guide:
    * Explained implementation details of the `add-activity` feature as well as the `set-activity-goal` and tracking 
      functionality
      [#139](https://github.com/AY2324S1-CS2113-T17-1/tp/pull/139) [#113](https://github.com/AY2324S1-CS2113-T17-1/tp/pull/113)
    * ...

### Community
* PRs reviewed (with non-trivial review comments): [#139](https://github.com/nus-cs2113-AY2324S1/tp/pull/8#pullrequestreview-1709775159)
* Reported bugs and suggestions for other teams in the class (examples: [#139](https://github.com/nus-cs2113-AY2324S1/tp/pull/8#pullrequestreview-1709775159), [#113](https://github.com/AY2324S1-CS2113-W12-3/tp/issues/113), [#110](https://github.com/AY2324S1-CS2113-W12-3/tp/issues/110),
  [#96](https://github.com/AY2324S1-CS2113-W12-3/tp/issues/96), [#94](https://github.com/AY2324S1-CS2113-W12-3/tp/issues/94))
