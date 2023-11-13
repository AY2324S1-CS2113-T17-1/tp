---
layout: page
title: Yi Cheng's Portfolio
---
# Toh Yi Cheng - Project Portfolio Page

# Project: AthletiCLI

## Overview

**AthletiCLI** is an application used to track, analyse, and optimize the users athletic performance.
It is designed for committed athletes who not only keep track of their physical activities but also dietary habits,
sleep metrics, and more. The user interacts with it using a CLI. It is written in Java, and has more than 10k LoC.

## Summary of Contributions

### Code contributed:  

* Code contributed: [RepoSense Link](https://nus-cs2113-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=yicheng-toh&tabRepo=AY2324S1-CS2113-T17-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Feature implemented

#### New feature: Users can add and delete diet goals
For motivated users on AthletiCLI, they can create diet goals to keep track of their nutrient intake. 
The nutrients supported currently are Calories, Fats, Carb and Protein. Consuming more nutrient than the 
target value indicates that they have achieved their diet goal for that specific nutrient.

If they would like to remove their existing goals, they can remvoe it with the delete function

#### New feature: Users can see all diet goals
This provides a convenient way for users to see all the diet goals that they have created and their current progress.

#### New feature: Editing diet goals

Instead of deleting diet goals and creating a new one, edit diet goal functionality hopes to streamline the process.
Instead of typing 2 commands to edit the target value of users' current diet goals, one command could
do the same trick.

### Enhancements implemented: 

#### Enhancement: Diet goal tracks diets within a limited time period. Daily and Weekly.

With the enhancement of diet goals, instead of tracking infinite records of diets in the past, the diet goal is 
now optimised with controlled time span of 1 day (daily diet goal) or 7 days (weekly diet goal). This enhancement
provides more meaningful diet goal function for the users to track their nutrients intake from their diets.

This explains the use of daily/weekly in setting and editing diet goal commands.

**Example of set diet goal command:** `set-diet-goal DAILY calories/500`

#### Enhancement: Diet goal not only encourages nutrients intake but also discourages nutrients intake.

Previously, users can only set diet goals that keep track of their nutrient intake. Once they have exceeded their
target value for the nutrients, the diet goals is marked as achieved.

However, this may not be the case for all nutrients. 
For example, for athletes who want to gain muscles, they would increase their intake of protein. At the same time, 
they would need to reduce their weight by cutting on fats.
In this case, the diet goal only encourage them to consume more fats.
Therefore 'unhealthy' diet goal is created. It marks a diet goal as not achieved if the value consumed is greater
than the target value. 

The creation of such goal can be accomplished by indicating an optional flag `unhealthy`.

**Example of set diet goal command:** `set-diet-goal DAILY unhealthy calories/500`





### Contributions to the UG: 

* Contributed to Activities section of AthlethiCLI for UG draft.
* Contributed to Diet Goals section of AthlethiCLI for UG which includes `set-diet-goal`, 
`edit-diet-goal`, `list-diet-goal`, `delete-diet-goal`.
* Contributed to the FAQ section of UG.

### Contributions to the DG: 
...
Which sections did you contribute to the DG? 
Which UML diagrams did you add/updated?
...
### Contributions to team-based tasks

* Kept track of deadlines for v1.0 and v2.0.
* Assisted in sorting and assigning of post PE dry run issues.
* Suggested the use of interface for find function and abstract class for goals. 
This is only implemented due to  [skylee03 (Ming-Tian)](./skylee03.html)'s outstanding effort in convincing the team.
* Examples of PR reviewed: 
  * [PR for editing activities](https://github.com/AY2324S1-CS2113-T17-1/tp/pull/59#discussion_r1362968136)
* Provided full scale testing for diet functionalities.
* Created issues labels: `type.Optimization`, `UG`, `DG` for issues to facilitate effective classification.
 
### Contributions beyond the project team

* PE dry Run:
  * [Screenshot captured during PE dry run](https://github.com/yicheng-toh/ped/tree/main/files)
* DG review for other teams: 
  * [SysLib CLI](https://github.com/nus-cs2113-AY2324S1/tp/pull/6), [Fit Track](https://github.com/nus-cs2113-AY2324S1/tp/pull/13), [FITNUS](https://github.com/nus-cs2113-AY2324S1/tp/pull/27)
