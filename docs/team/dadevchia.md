---
layout: page
title: Dylan Chia's Project Portfolio Page (PPP)
---

# Project: AthletiCLI

## Overview
Contributed to all sleep related features, including the `sleep` commands, `Sleep` class, `SleepParser` class, as well as the `SleepGoal` class. 

Also contributed to the formatting of the `User Guide` including the `Command Summary` and `FAQ`.

View my **code contributions** on [RepoSense](https://nus-cs2113-ay2324s1.github.io/tp-dashboard/?
search=dadevchia&breakdown=true).

## Summary of Contributions
Given below are my contributions to the project.

### New Feature: Added the ability to add, edit, delete and list sleep 

* What it does: Allows the user to track their sleep by adding, editing, deleting and listing sleep.
* Justification: This feature is the core of the sleep management as it allows the user to track their sleep.
* Highlights: It was challenging to find an elegant and efficient implementation which keeps code redundancy to a minimum. I used inheritance to create a parent class for sleep, and child classes for different types of sleep. This allowed for a modular implementation, which was easy to understand and extend. I also used a custom parser to parse the sleep entries, which allowed for a more efficient implementation.

### New Feature: Added the ability to find sleep by date

* What it does: Allows the user to find sleep by date.
* Justification: This feature allows the user to find sleep entries by date, which is useful for analysing sleep performance.
* Highlights: The implementation had to be done in a way that was consistent with the other find commands. The difficulty was that sleep records do not have a fixed date, but rather a start and end datetime, which had to be accounted for. I used a custom constructor to analyze the datetime and create a sleep entry with the correct date.

### New Feature: Added the ability to list all sleep records

* What it does: Allows the user to list all sleep records, including the sleep  start and end datetime, and duration of sleep.
* Justification: This feature allows the user to view their sleep records chronologically and have automated calculations of the duration of sleep.
* Highlights: The sleep duration had to be calculated using the start and end datetime, which was a unique implementation from other classes. I used the Duration class from Java to store the duration of sleep, which can be calculated and tracked easily.

### New Feature: Added sleep goal tracking mechanism
* What it does: Allows the user to set a sleep goal and track their progress towards the goal of number of minutes slept, in the past day, week, month or year.
* Justification: This feature allows the user to compare their sleep performance and analyse their progress over time.
* Highlights: The implementation had many different parameters and OOP concepts, which had to be applied during any target modifying operations. Duration of sleep was also a unique implementation that had to be accounted for. I used the Duration class from Java to store the duration of sleep, which can be calculated and tracked easily.


### New Feature: Implemented storing capabilities for sleep and sleep goals
* What it does: automatically stores all sleep and sleep goals in a file and loads them on startup of the application. It also allows for the user to edit the file manually.
* Justification: This feature improves the product significantly by allowing the user to close the application and reopen it without losing any data. This is especially important as the application is designed to track the progress over a longer period of time. It also allows for the user to edit the file manually, which is useful for manual data entry.
* Highlights: The implementation had issues with the parser and the storage of sleep entries.  A custom parser was created to parse the sleep entries, and the storage of sleep entries was done using a custom storage class. The storage class was also used to store sleep goals.


#### Review
* [Reviewed PRs](https://github.com/AY2324S1-CS2113-T17-1/tp/issues?q=reviewed-by:dadevchia+) (Examples: [#31](https://github.com/AY2324S1-CS2113-T17-1/tp/pull/31) [#118](https://github.com/AY2324S1-CS2113-T17-1/tp/pull/118) )

* [Started Discussions in Github Forum](https://github.com/AY2324S1-CS2113-T17-1/tp/discussions/49)

* [Issues reported / discussed](https://github.com/AY2324S1-CS2113-T17-1/tp/issues?q=author:dadevchia+type:issue)

### Code Contributed
[RepoSense Link](https://nus-cs2113-ay2324s1.github.io/tp-dashboard/?search=dadevchia&breakdown=true)

### Project Management
* Maintained issue tracker, including generating suitable labels.
* Responsible for ensuring proper testing of the implemented features.
* Planned and led group meetings, ensuring that a clear direction was set for the project.
* Managed release v1.0 and v2.0.
* Strictly following deadlines, git conventions and forking workflow.

### Documentation
* User Guide:
    * Added documentation for the features `add-sleep`, `delete-sleep`, `edit-sleep` `list-sleep`, `edit-sleep`, `find-sleep`, `set-sleep-goal`, `list-sleep-goal`, `edit-sleep-goal`
    * Added the `Command Summary` and `FAQ` sections

* Developer Guide:
    * Explained implementation details of the sleep commands, including `add-sleep`, `delete-sleep`, `edit-sleep` `list-sleep`, `edit-sleep`, `find-sleep`,
    * Explained how the parser works for sleep commands with an assosciated UML sequence diagram
    * Explained how the sleep object links to assosciated classes with an UML class diagram
    * Explained challenges and how they were overcome, particularly with regards to the duration of sleep
    * Added Value Proposition and User Stories 
    * Provided test instructions for sleep commands
