---
layout: page
title: Developer Guide
---

- Table of Contents
{:toc}

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design

This section provides a high-level explanation of the design and implementation of AthletiCLI, 
supported by UML diagrams and short code snippets to illustrate the flow of data and interactions between the 
components.


#### [Implemented] Setting Up of Diet Goals

### Architecture

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`AthletiCLI`** is in charge of the app launch and shut down.

The bulk of the AthletiCLI’s work is done by the following components, with each of them corresponds to a package:

* [`UI`](https://github.com/AY2324S1-CS2113-T17-1/tp/tree/master/src/main/java/athleticli/ui): The UI of AthletiCLI.
* [`Storage`](https://github.com/AY2324S1-CS2113-T17-1/tp/tree/master/src/main/java/athleticli/storage): Reads data from, and writes data to, the hard disk.
* [`Data`](https://github.com/AY2324S1-CS2113-T17-1/tp/tree/master/src/main/java/athleticli/data): Holds the data of AthletiCLI in memory.
* [`Commands`](https://github.com/AY2324S1-CS2113-T17-1/tp/tree/master/src/main/java/athleticli/commands): The command executors.

[`Exceptions`](https://github.com/AY2324S1-CS2113-T17-1/tp/tree/master/src/main/java/athleticli/exceptions) represents exceptions used by multiple other components.

### UI Component

### Storage Component

### Data Component

### Commands Component

## Implementation

### Diet Management in AthletiCLI

#### [Implemented] Setting Up, Editing, Deleting, Listing, and Finding Diets

Regardless of the operation you are performing on diets (setting up, editing, deleting, listing, or finding), the process follows a general five-step pattern in AthletiCLI:

1. **Input Processing**: The user's input is passed through AthletiCLI to the Parser Class. Examples of user inputs include:
    - "add-diet calories/500 protein/20 carb/50 fat/10 datetime/2021-09-01 06:00" for adding a diet.
    - "edit-diet 1 calories/500 protein/20 carb/50 fat/10 datetime/2021-09-01 06:00" for editing a diet.
    - "delete-diet 1" for deleting a diet.
    - "list-diet" for listing all diets.
    - "find-diet 2021-09-01" for finding diets of a particular date.

2. **Command Identification**: The Parser Class identifies the type of diet operation and passes the necessary parameters.

3. **Command Creation**: An instance of the corresponding command class is created (e.g., AddDietCommand, EditDietCommand, etc.) and returned to AthletiCLI.

4. **Command Execution**: AthletiCLI executes the command, interacting with the data instance of DietList to perform the required operation.

5. **Result Display**: A message is returned post-execution and passed through AthletiCLI to the UI for display to the user.

By following these general steps, AthletiCLI ensures a streamlined process for managing diet-related tasks.

### [Implemented] Setting Up of Diet Goals

This following sequence diagram show how the 'set-diet-goal' command works:

<p  align="center" width="100%">
  <img width="80%" src="images/setDietGoalUmlSequenceDiagram.svg" alt="'set-diet-goal' Sequence Diagram"/>
</p>

Step 1. The input from the user ("set-diet-goal fats/1") runs through AthletiCLI to the Parser Class.

Step 2. The Parser Class will identify the request as setting up a diet goal and pass in the parameters
"fats/1".

Step 3. A temporary dietGoalList is created to store newly created diet goals.

Step 4. The inputs are verified against our lists of approved diet goals.

Step 5. For each of the diet goals that are valid, a dietGoal object will be created and stored in the 
temporary dietGoalList.

Step 6. The Parser then creates for an instance of SetDietGoalCommand and returns the instance to 
AthletiCLI.

Step 7. AthletiCLI will execute the SetDietGoalCommand. This adds the dietGoals that are present in the 
temporary list into the data instance of DietGoalList which will be kept for records.

Step 8. After executing the SetDietGoalCommand, SetDietGoalCommand returns a message that is passed to 
AthletiCLI to be passed to UI(not shown) for display.

#### [Implemented] Adding activities
The `add-activity` feature allows users to add a new activity into the application.
These are the main components behind the architecture of the `add-activity` feature:
1. `AthletiCLI`: faciliates the mechanism. It captures the input and calls the parser and execution.
2. `Parser`: parses the user input and generates the appropriate command object and activity 
   instance.
3. `AddActivityCommand`: encapsulates the execution of the `add-activity` command. It adds 
   the activity to the data.
4. `Activity`: represents the activity that is to be added.
5. `Data`: holds current state of the activity list.
6. `ActivityList`: maintains the list of all added activities.

Given below is an example usage scenario and how the add mechanism behaves at each step.

**Step 1 - Input Capture:** The user issues an `add-activity ...` which is captured and passed to the Parser by the 
running AthletiCLI instance.

**Step 2 - Activity Parsing:** The Parser parses the raw input to obtain the arguments of the activity. Given that all 
parameters are provided correctly and no exception is thrown, a new activity object is created.

**Step 3 - Command Parsing:** In addition the parser will create an `AddActivityCommand` object with the newly added 
activity attached to it. The command implements the `AddActivityCommand#execute()` operation and is passed to 
the AthletiCLI instance.

**Step 4 - Activity Addition:** The AthletiCLI instance executes the `AddActivityCommand` object. The command will 
access the data and retrieve the currently stored list of activities stored inside it. The new `Activity` object is 
added to the list.

**Step 5 - User Interaction:** Once the activity is successfully added, a confirmation message is displayed to the user.

The following sequence diagram shows how the `add-activity` operation works:
<p  align="center" width="100%">
  <img width="80%" src="DeveloperGuide/AddActivity.png" alt="Sequence Diagram of add-activity`"/>
</p>

### [Proposed] Implementation of DietGoalList

The current implementation of DietGoalList is an ArrayList.
It helps to store dietGoals, however it is not efficient in searching for a particular dietGoal.
At any instance of time, there could only be the existence of one dietGoal.
Verifying if there is an existence of a dietGoal using an ArrayList takes O(n) time, where n is the number of dietGoals.
The proposed change will be to change the underlying data structure to a hashmap for amortised O(1) time complexity
for checking the presence of a dietGoal. 

## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

| Version | As a ...                          | I want to ...                     | So that I can ...                                                                      |
|---------|-----------------------------------|-----------------------------------|----------------------------------------------------------------------------------------|
| v1.0    | health-conscious user             | add my dietary information        | keep track of my daily calorie and nutrient intake                                     |
| v1.0    | organized user                    | delete a dietary entry            | remove outdated or incorrect data from my diet records                                 |
| v1.0    | fitness enthusiast                | view all my diet records          | have a clear overview of my dietary habits and make informed decisions on my diet      |
| v1.0    | new user                          | see usage instructions            | refer to them when I forget how to use the application                                 |
| v1.0    | motivated weight-conscious user   | set diet goals                    | have the motivation to work towards keeping weight in check.                           |
| v1.0    | forgetful user                    | see all my diet goals             | remind myself of all the diet goals I have set.                                        |
| v1.0    | regretful user                    | remove my diet goals              | I can rescind the strict goals I set previously when I find the goals too far fetched. |
| v1.0    | motivated user                    | update my diet goals              | I can work towards better version of myself by setting stricter goals.                 |
| v2.0    | user                              | find a to-do item by name         | locate a to-do without having to go through the entire list                            |
| v2.0    | meticulous user                   | edit my dietary entries           | correct any mistakes or update my diet information as needed                           |
| v2.0    | user seeking specific information | find my diets for a specific date | easily retrieve my dietary information for any particular day                          |

## Non-Functional Requirements

{Give non-functional requirements}
1. AthletiCLI should work on Windows, MacOS and Linux that has java 11 installed.

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
