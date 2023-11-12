---
layout: page
title: Ming-Tian’s Portfolio
---

## Overview

**AthletiCLI** is your all-in-one solution to track, analyse, and optimize your athletic performance. Designed for the committed athlete, this command-line interface (CLI) tool not only keeps tabs on your physical activities but also covers dietary habits, sleep metrics, and more.

## Summary of Contributions

Given below are my contributions to the project.

* :computer: **Code contributed**: [RepoSense link](https://nus-cs2113-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=skylee03&tabRepo=AY2324S1-CS2113-T17-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
* :house: **Architecture**: Designed the overall software architecture of AthletiCLI.
* :bulb: **Features**:
  * **Entry**: Designed and implemented the `AthletiCLI` class as the entry component of the program.
  * **UI**: Designed and implemented the `Ui` class which provides the basic interactive functions.
  * **Commands**: Designed and implemented the `Command` abstract class as the prototype of any other commands. Designed and implemented the `ByeCommand`, the `FindCommand`, the `HelpCommand`, and the `SaveCommand`.
  * **Data**: Designed and implemented the `Data` class which serves as the core component of data processing.
  * **Retrieval of Entries**: Designed and implemented the `Findable` interface which is an abstraction to lists whose entries can be retrieved with a keyword.
  * **Goals**: Designed and implemented the `Goal` abstract class in which the basic attributes (e.g., time span) and methods of a goal.
  * **File Storage**: Designed and implemented the `Storage` class and the `StorableList` abstract class. The former provides underlying file reading and writing functions, and the latter is an abstraction to lists which can be converted into a stream to be used for file reading and writing.
  * **Exception Handling**: Designed and implemented the `AthletiCLI` class and some exception wrappers.
* :cop: **Project management**:
  * Develops the project iteratively and incrementally.
  * Manages our project using GitHub mechanisms: milestones, releases, issues, PRs, etc.
  * Strictly adheres to the [schedule](https://nus-cs2113-ay2324s1.github.io/website/schedule/timeline.html).
  * Strictly follows our [Git conventions](https://se-education.org/guides/conventions/git.html).
  * Strictly follows the [forking workflow](https://nus-cs2113-ay2324s1.github.io/website/se-book-adapted/chapters/gitAndGithub.html#forking-workflow).
* :books: **Documentation**:
  * Integrated a Jekyll theme ([Alembic](https://github.com/daviddarnes/alembic)) to the project website.
  * Integrated a Jekyll plugin ([Jemoji](https://github.com/jekyll/jemoji)) to the project website.
  * :green_book: User Guide:
    * Added instructions on [miscellaneous commands](../UserGuide.html#miscellaneous).
  * :blue_book: Developer Guide:
    * Contributed to the sections of [design](../DeveloperGuide.html#design) and [implementation](../DeveloperGuide.html#implementation).
    * Added sequence diagrams for [`help add-diet`](../images/HelpAddDiet.svg) and [`save`](../images/Save.svg).
    * Checked and unified the format of the DG. 
* :family: **Community**:
  * :eyes: Reviewed PRs: [tP comments dashboard](https://nus-cs2113-ay2324s1.github.io/dashboards/contents/tp-comments.html)
  * :lips: Contributed to forum discussions: [Forum activities dashboard](https://nus-cs2113-ay2324s1.github.io/dashboards/contents/forum-activities.html)
  * :open_hands: Reported bugs and suggestions for other teams in the class:
    * [Issues I created](https://github.com/AY2324S1-CS2113-T18-1/tp/issues?q=%5BPE-D%5D%5BTester+E%5D)
    * [Issues/PRs I commented on](https://github.com/AY2324S1-CS2113-T18-1/tp/issues?q=involves%3Askylee03)