# Dylan Chia Tian Project Portfolio Page

# Project: AthletiCLI

## Overview
Contributed to all sleep related features, including the `sleep` commands, `Sleep` class, `SleepParser` class, as well as the `SleepGoal` class. 

Also contributed to the formatting of the `User Guide` including the `Command Summary` and `FAQ`.

## Summary of Contributions
Given below are my contributions to the project.

### New Feature: Added the ability to add, edit, delete and list sleep 

* What it does: Allows the user to track their sleep by adding, editing, deleting and listing sleep.
* Justification: This feature is the core of the sleep management as it allows the user to track their sleep.
* Highlights: It was challenging to find an elegant and efficient implementation which keeps code redundancy to a 
  minimum, as it had to combine three different object types with some similar but also unique parameters. This was 
  achieved by using inheritance, generic parser functions and extensive refactoring which involved in-depth analysis.

### New Feature: Added sleep goal tracking mechanism
* What it does: Allows the user to set a sleep goal and track their progress towards the goal of number of minutes slept, in the past day, week, month or year.
* Justification: This feature allows the user to compare their sleep performance and analyse their progress over time.
* Highlights: The implementation had many different parameters and OOP concepts, which had to be applied during any target modifying operations. Duration of sleep was also a unique implementation that had to be accounted for. I used the Duration class from Java to store the duration of sleep, which can be calculated and tracked easily.


### New Feature: Implemented storing capabilities for sleep and sleep goals
* What it does: automatically stores all sleep and sleep goals in a file and loads them on startup of the application. It also allows for the user to edit the file manually.
* Justification: This feature improves the product significantly by allowing the user to close the application and reopen it without losing any data. This is especially important as the application is designed to track the progress over a longer period of time. It also allows for the user to edit the file manually, which is useful for manual data entry.
* Highlights: The implementation had issues with the parser and the storage of sleep entries.  A custom parser was created to parse the sleep entries, and the storage of sleep entries was done using a custom storage class. The storage class was also used to store sleep goals.


### Code Contributed
[RepoSense Link](https://nus-cs2113-ay2324s1.github.io/tp-dashboard/?search=dadevchia&breakdown=true)

### Project Management
...

### Documentation
* User Guide:
    * Added documentation for the features `add-sleep`, `delete-sleep`, `edit-sleep` `list-sleep`, `edit-sleep`, `set-sleep-goal`
    * ...
* Developer Guide:
    * Explained implementation details of the `add-sleep` feature 
    * ...

### Community

