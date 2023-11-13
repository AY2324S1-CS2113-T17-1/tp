---
layout: page
title: Developer Guide
---

- Table of Contents
{:toc}
---
## Acknowledgements

[//]: # ({list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well})

1. [AB-3 Developer Guide](https://se-education.org/addressbook-level3/DeveloperGuide.html)
2. [PlantUML for sequence diagrams](https://plantuml.com/)

---
## Setting Up and Getting Started

First, fork this repo, and clone the fork into your computer.

If you plan to use Intellij IDEA (highly recommended):

1. Configure the JDK: Follow the guide [se-edu/guides IDEA: Configuring the JDK](https://se-education.org/guides/tutorials/intellijJdk.html)  
to ensure Intellij is configured to use JDK 11.
2. Import the project as a Gradle project: Follow the guide
[se-edu/guides IDEA: Importing a Gradle project](https://se-education.org/guides/tutorials/intellijImportGradleProject.html)
to import the project into IDEA.
:exclamation: Note: Importing a Gradle project is slightly different from importing a normal Java project.
3. Verify the setup:
   * Run athlethicli.AthletiCLI and try a few commands.
   * Run the tests using `./gradlew check` ensure they all pass.




---
## Design

This section provides a high-level explanation of the design and implementation of AthletiCLI, 
supported by UML diagrams and short code snippets to illustrate the flow of data and interactions between the 
components.

---
### Architecture

Given below is a quick overview of main components and how they interact with each other.
<p  align="center" width="100%">
  <img width="80%" src="images/architectureDiagram.svg" alt="'set-diet-goal' Sequence Diagram"/>
</p>

**Main components of the architecture**

**`AthletiCLI`** is in charge of the app launch and shut down.

The bulk of the AthletiCLI’s work is done by the following components, with each of them corresponds to a package:

* [`UI`](https://github.com/AY2324S1-CS2113-T17-1/tp/tree/master/src/main/java/athleticli/ui): The UI and other UI-related sub-components of AthletiCLI.
* [`Parser`](https://github.com/AY2324S1-CS2113-T17-1/tp/tree/master/src/main/java/athleticli/parser): Parses the commands input by the users.
* [`Storage`](https://github.com/AY2324S1-CS2113-T17-1/tp/tree/master/src/main/java/athleticli/storage): Reads data from, and writes data to, the hard disk.
* [`Data`](https://github.com/AY2324S1-CS2113-T17-1/tp/tree/master/src/main/java/athleticli/data): Holds the data of AthletiCLI in memory.
* [`Commands`](https://github.com/AY2324S1-CS2113-T17-1/tp/tree/master/src/main/java/athleticli/commands): The command executors.

[`Exceptions`](https://github.com/AY2324S1-CS2113-T17-1/tp/tree/master/src/main/java/athleticli/exceptions) represents exceptions used by multiple other components.

### Overview

The class diagram shows the relationship between `AthletiCLI`, `Ui`, `Parser`, and `Data`.

<p  align="center" width="100%">
  <img width="80%" src="images/MainClassDiagram.svg" alt="'set-diet-goal' Sequence Diagram"/>
</p>

### Data Component

The class diagram shows how the `Data` component is constructed with multiple classes.

<p  align="center" width="100%">
  <img width="80%" src="images/DataClassDiagram.svg" alt="'set-diet-goal' Sequence Diagram"/>
</p>

**How the architecture components interact with each other**

The _Sequence Diagram_ below shows how the components interact with each other for the scenario where the user issues the command `help add-diet`.

![](images/HelpAddDiet.svg)

This diagram involves the interaction between `AthletiCLI`, `UI`, `Parser`, `Commands` components and the user.

The `Storage` component only interacts with the `Data` component. The _Sequence Diagram_ below shows how they interact with each other for the scenario where a `save` command is executed.

![](images/Save.svg)

For simplicity, only 1 `StorableList` is drawn instead of the actual 6.

---

## Implementation

### Diet Management in AthletiCLI

#### [Implemented] Setting Up, Editing, Deleting, Listing, and Finding Diets

Regardless of the operation you are performing on diets (setting up, editing, deleting, listing, or finding), the process follows a general five-step pattern in AthletiCLI:

1. **Input Processing**: The user's input is passed through AthletiCLI to the Parser Class. Examples of user inputs include:
    - `add-diet calories/500 protein/20 carb/50 fat/10 datetime/2021-09-01 06:00` for adding a diet.
    - `edit-diet 1 calories/500 protein/20 carb/50 fat/10 datetime/2021-09-01 06:00` for editing a diet.
    - `delete-diet 1` for deleting a diet.
    - `list-diet` for listing all diets.
    - `find-diet 2021-09-01` for finding diets of a particular date.

2. **Command Identification**: The Parser Class identifies the type of diet operation and passes the necessary parameters.

3. **Command Creation**: An instance of the corresponding command class is created (e.g., AddDietCommand, EditDietCommand, etc.) and returned to AthletiCLI.

4. **Command Execution**: AthletiCLI executes the command, interacting with the data instance of DietList to perform the required operation.

5. **Result Display**: A message is returned post-execution and passed through AthletiCLI to the UI for display to the user.

By following these general steps, AthletiCLI ensures a streamlined process for managing diet-related tasks.

#### [Implemented] Setting Up of Diet Goals

This following sequence diagram show how the 'set-diet-goal' command works:

<p  align="center" width="100%">
  <img width="80%" src="images/setDietGoalUmlSequenceDiagram.svg" alt="'set-diet-goal' Sequence Diagram"/>
</p>

**Step 1:** The input from the user ("set-diet-goal fats/1") runs through AthletiCLI to the Parser Class.

**Step 2:** The Parser Class will identify the request as setting up a diet goal and pass in the parameters
"fats/1".

**Step 3:** A temporary dietGoalList is created to store newly created diet goals.

**Step 4:** The inputs are verified against our lists of approved diet goals.

**Step 5:** For each of the diet goals that are valid, a dietGoal object will be created and stored in the 
temporary dietGoalList.

**Step 6:** The Parser then creates for an instance of SetDietGoalCommand and returns the instance to 
AthletiCLI.

**Step 7:** AthletiCLI will execute the SetDietGoalCommand. This adds the dietGoals that are present in the 
temporary list into the data instance of DietGoalList which will be kept for records.

**Step 8:** After executing the SetDietGoalCommand, SetDietGoalCommand returns a message that is passed to 
AthletiCLI to be passed to UI(not shown) for display.

#### [Proposed] Future Implementation of DietGoalList Class

The current implementation of DietGoalList is an ArrayList.
It helps to store diet goals, however it is not efficient in searching for a particular dietGoal.
At any instance of time, there could only be the existence of one dietGoal.
Verifying if there is an existence of a diet goal using an ArrayList takes O(n) time, where n is the number of dietGoals.
The proposed change will be to change the underlying data structure to a hashmap for amortised O(1) time complexity
for checking the presence of a dietGoal.

### Activity Management in AthletiCLI

#### [Implemented] Adding activities
The `add-activity` feature is a core functionality which allows users to record new activities in the application.
The feature is designed in a modular and extendable way, ensuring seamless integration of future enhancements and 
especially new activity types.

The architecture of the `add-activity` feature is composed of the following main components.
1. `AthletiCLI`: Facilitates the mechanism. It captures the user input and initiates the parsing and execution.
2. `Parser` (`Activity Parser`): Interprets the user input, generating both the appropriate command object and 
   the activity instance.
3. `AddActivityCommand`: Encapsulates the execution of the `add-activity` command, adding the activity to the data.
4. `ActivityChanges`: Contains the arguments of the activity to be added. It is used to transfer the data from the 
   parser to the activity in a modular way.
5. `Activity`: Represents the activity to be added. It is a superclass for specific activity types like Run, Swim and 
   Cycle.
6. `Data`: Manages the current state of the activity list.
7. `ActivityList`: Maintains the list of all activities added to the application.


Class Relationships:

Below is a class diagram illustrating the relationships between the data components `Activity`,`Data` and 
`ActivityList`:

<p align="center" >
  <img width="50%" src="images/ActivityInheritance.svg" alt="Activity Data Components"/>
</p>

> The diagram shows the inheritance relationship between the `Activity` class and the specific activity types Run, 
> Swim and Cycle, each with unique attributes and methods. This design becomes especially crucial in future 
> development cycles with added parameters and activity types. The 'ActivityList' aggregates these instances.

Usage Scenario and Process Flow:
The process of adding an activity involves several steps, each handled by different components.
Given below is an example usage scenario and how the add mechanism behaves.

**Step 1 - Input Capture:** The user issues an `add-activity ...` (or `add-run`, etc., respectively) which is 
captured and forwarded to the Parser by the running AthletiCLI instance.

**Step 2 - Activity Parsing:** The ActivityParser interprets the raw input to obtain the arguments of the activity. 
Given that all parameters are provided correctly and no exception is thrown, a new activity object is created.

This diagram illustrates the activity parsing process in more detail:
The `ActivityChanges` object plays a key role in the parsing process. It is used for storing the 
different attributes of the activity that are to be added. Later, the `ActivityParser` 
will use the `ActivityChanges` to create the `Activity` object. 
> This way of transferring data between the parser and the activity is more flexible which is suitable for future 
extensions of the activity types and allows for a more modular design. This design and most of the methods can be reused 
for the `edit-activity` mechanism, which works in the same way with slight modifications due to optional parameters.

<p align="center" >
  <img width="100%" src="images/ActivityParsing.svg" alt="Activity Parsing Process"/>
</p>

**Step 3 - Command Parsing:** Afterwards the parser constructs an `AddActivityCommand` embedding the newly created 
activity within it. The `AddActivityCommand` implements the `AddActivityCommand#execute()` operation and is passed to 
the AthletiCLI instance.

**Step 4 - Activity Addition:** The AthletiCLI instance executes the `AddActivityCommand` object. The command 
accesses the data and retrieves the currently stored list of activities stored inside it. The new `Activity` object is 
then added to the `ActivityList`.

**Step 5 - User Interaction:** Upon successful addition of the activity, a confirmation message is displayed to the 
user.

The following sequence diagram visually represents the flow and interactions of components during the `add-activity` 
operation:
<p  align="center" >
  <img width="100%" src="images/AddActivity.svg" alt="Sequence Diagram: `add-activity` operation"/>
</p>

#### [Implemented] Tracking activity goals

The `set-activity-goal` feature allows users to set and track periodic goals for their activities.
The goal fulfillment is automatically monitored and can be reviewed by the user at any time.

These are the key components and their roles in the architecture of the goal tracking:
* `SetActivityGoalCommand`: Encapsulates the execution of the `set-activity-goal` command. It adds 
  the activity goal to the data.
* `ActivityGoal`: Represents the activity goal that is to be added and contains functionality to 
  track the fulfillment of the goal. 
* `ActivityList`: Contains key functionality to retrieve and filter the activity list according to the specified 
  criteria of the goal.

Given below is an example usage scenario and how the goal setting and tracking mechanism behaves at 
each step.

1. **Step 1 - Input Capture:** The user issues a `set-activity-goal ...` which is captured and passed to the 
   Parser by the running AthletiCLI instance.
2. **Step 2 - Goal Parsing:** The `ActivityParser` parses the raw input to obtain the sports, target and timespan of the 
   goal. 
   Given that all these parameters are provided correctly and no exception is thrown, a new activity goal object is 
   created.
3. **Step 3 - Command Parsing:** In addition the parser will create a `SetActivityGoalCommand` object with the newly 
   added activity goal attached to it. The command implements the `SetActivityGoalCommand#execute()` operation and is 
   passed to the AthletiCLI instance.
4. **Step 4 - Goal Addition:** The AthletiCLI instance executes the `SetActivityGoalCommand` object. The command will 
   access the data and retrieve the currently stored list of activity goals stored inside it. The new `ActivityGoal` 
   object is added to the list.

The following sequence diagram shows how the `set-activity-goal` operation works:
<p  align="center" >
  <img width="100%" src="images/AddActivityGoal.svg" alt="Sequence Diagram of set-activity-goal"/>
</p>

Assume that the user has set a goal to run 10km per week and has already tracked two running activities of 5km each 
within the last 7 days as well as three older sport activities. The object diagram below shows the state of the 
scenario with the eligible activities for the goal highlighted in green.

<p align="center" >
  <img width="100%" src="images/ActivityObjectDiagram.svg" alt="Object Diagram of the scenario"/>
</p>

The following describes how the goal evaluation works after being invoked by the user, e.g., with a `list-activity-goal` command:

5. **Step 5 - Goal Assessment:** The evaluation of the goal is operated by the `ActivityGoal` object. It retrieves the 
activity list with the five tracked activities from the data and calls the total distance calculation function. It 
   filters the activity list according to the specified timespan and sports of the goal. The current value obtained by this, 
   10km in the example, is returned to the `ActivityGoal` object. This output is compared to the target value of the 
   goal. This mechanism is visualized in the following sequence diagram:

<p  align="center" >
    <img width="100%" src="images/ActivityGoalEvaluation.svg" alt="Sequence Diagram of activity goal evaluation"/>
</p>

### Sleep Management in AthletiCLI

#### [Implemented] Finding, Adding, Editing, Deleting, Listing Sleep

1. **Input Processing**: The user's input is passed through AthletiCLI to the Parser Class. Examples of user inputs include:
    - "add-sleep hours/8 datetime/2021-09-01 06:00" for adding sleep.
    - "edit-sleep 1 hours/8 datetime/2021-09-01 06:00" for editing sleep.
    - "delete-sleep 1" for deleting sleep.
    - "list-sleep" for listing all sleep.

2. **Command Identification**: The Parser Class identifies the type of sleep operation and passes the necessary parameters.

3. **Command Creation**: An instance of the corresponding command class is created (e.g., AddSleepCommand, EditSleepCommand, etc.) and returned to AthletiCLI.

4. **Command Execution**: AthletiCLI executes the command, interacting with the data instance of SleepList to perform the required operation.

5. **Result Display**: A message is returned post-execution and passed through AthletiCLI to the UI for display to the user.


---

The following class diagram shows how sleep and sleep-related classes are constructed in AthletiCLI:

<p  align="center" width="100%">
  <img width="80%" src="images/SleepAndSleepListClassDiagram.svg" alt="Class Diagram of Sleep and SleepList"/>

</p>

---

## Product scope
### Target user profile

AthletiCLI is designed for athletic individuals who are committed to optimizing their performance. 

These users are highly disciplined and engaged not only in regular, intense physical training but also in nutrition, mental conditioning, and recovery. 

They are looking for a holistic tool that integrates all facets of an athletic lifestyle. AthletiCLI serves as a daily or weekly companion, designed to monitor, track, and analyze various elements crucial for high-level athletic performance. 

### Value proposition

AthletiCLI provides a streamlined, integrated solution for athletic individuals focused on achieving peak performance. 

While the app includes robust capabilities for tracking physical training metrics, it also offers features for monitoring dietary habits and sleep metrics. 

By providing a comprehensive view of various performance-related factors over time, AthletiCLI enables athletes to identify trends, refine their training and lifestyle habits, and optimize outcomes. The app is more than a tracking tool—it's a performance optimization platform that takes into account the full spectrum of an athlete's life. 

---

## User Stories


| Version | As a ...                        | I want to ...                                                     | So that I can ...                                                                      |
|---------|---------------------------------|-------------------------------------------------------------------|----------------------------------------------------------------------------------------|
| v1.0    | fitness enthusiastic user       | add different activities including running, swimming and cycling) | keep track of my fitness activities and athletic performance.                          |
| v1.0    | analytical user                 | view my activity details at any point in time                     | track my progress and make informed decisions about my fitness routine.                |
| v1.0    | clumsy user                     | delete any tracked activity                                       | I can correct any mistakes or remove accidentally added activities.                    |
| v1.0    | detail-oriented user            | modify any of my tracked activities                               | ensure accuracy in my fitness records.                                                 |
| v1.0    | health-conscious user           | add my dietary information                                        | keep track of my daily calorie and nutrient intake                                     |
| v1.0    | organized user                  | delete a dietary entry                                            | remove outdated or incorrect data from my diet records                                 |
| v1.0    | fitness enthusiast              | view all my diet records                                          | have a clear overview of my dietary habits and make informed decisions on my diet      |
| v1.0    | new user                        | see usage instructions                                            | refer to them when I forget how to use the application                                 |
| v1.0    | motivated weight-conscious user | set diet goals                                                    | have the motivation to work towards keeping weight in check.                           |
| v1.0    | forgetful user                  | see all my diet goals                                             | remind myself of all the diet goals I have set.                                        |
| v1.0    | regretful user                  | remove my diet goals                                              | I can rescind the strict goals I set previously when I find the goals too far fetched. |
| v1.0    | motivated user                  | update my diet goals                                              | I can work towards better version of myself by setting stricter goals.                 |
| v1.0    | sleep deprived user             | add my sleep information                                          | keep track of my sleep habits and identify areas for improvement                       |
| v1.0    | sleep deprived user             | delete a sleep entry                                              | remove outdated or incorrect data from my sleep records                                |
| v1.0    | sleep deprived user             | view all my sleep records                                         | have a clear overview of my sleep habits and make informed decisions on my sleep       |
| v1.0    | sleep deprived user             | edit my sleep entries                                             | correct any mistakes or update my sleep information as needed                          |
| v2.0    | user                            | find a to-do item by name                                         | locate a to-do without having to go through the entire list                            |
| v2.0    | meticulous user                 | edit my dietary entries                                           | correct any mistakes or update my diet information as needed                           |
| v2.0    | active user                     | set activity goals                                                | work towards a specific fitness target for different sports activities.                |
| v2.0    | adaptable athlete               | edit my activity goals                                            | modify my fitness targets to align with my current fitness level and schedule.         |
| v2.0    | organized athlete               | list all my activity goals                                        | have a clear overview of my set targets and track my progress easily.                  |
| v2.0    | meticulous user                 | find my diets by date                                             | easily retrieve my dietary records for a specific day and monitor my eating habits.    |
| v2.0    | motivated user                  | keep track of my diet goals for a period of time                  | I can monitor my diet progress on a weekly basis and increase or reduce if needed.     |                                         |

---

## Non-Functional Requirements

1. AthletiCLI should work on Windows, MacOS and Linux that has java 11 installed.
2. AthletiCLI should be able to store data locally.
3. AthletiCLI should be able to work offline.
4. AthletiCLI should be easy to use.

---

## Glossary

[//]: # (* *glossary item* - Definition)
* **UI** - A short form for User Interface. A UI class refers to the class that is responsible for handling user input 
and provide feedback to the users.


---

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}

**Note**: This section serves to provide a quick start for manual testing on AthletiCLI. This list is not exhaustive.
Developers are expected to conduct more extensive tests.

### Initial Launch

* ✅ Download the latest AthletiCLI from the official repository.
* ✅ Copy the downloaded file to a folder you want to designate as the home for AthletiCLI.
* ✅ Open a command terminal, cd into the folder where you copied the file, and run `java -jar AthletiCLI.jar`.

### Activity Management

#### Activity Records

#### Activity Goals

### Diet Management

#### Diet Records

#### Diet Goals

1. Setting diet goals
   * Prerequisite: There are no similar goals present
   * Test case 1:
     * There are no diet goals constructed.
     * `set-diet-goal DAILY calories/500` creates a daily healthy calories goal with a target value of 500
   * Test case 2:
     * There are no diet goals constructed.
     * `set-diet-goal WEEKLY calories/500 fats/600` Creates 2 weekly healthy nutrient goals: calories and fats.
   * Test case 3:
     * There is a daily healthy calories goal present.
     * `set-diet-goal DAILY calories/500` will result in an error since the goal is already present.
   * Test case 4:
     * There is a daily healthy calories goal present.
     * `set-diet-goal DAILY unhealthy calories/500` will result in an error as a nutrient goal cannot be healthy 
     and unhealthy at the same time.
   * Test case 5:
     * There is a daily healthy calories goal present with a target value of 1000
     * `set-diet-goal WEEKLY healthy calories/500` will result in an error since the value of the daily diet goal 
     is greater than the value of weekly diet goal.
2. Listing diet goals
   * Test case 1:
     * 'list-diet-goal' lists all the diet goals that are created and present in the diet goal records.
3. Deleting diet goals
   * Test case 1:
     * There is one diet goal present in the diet goal records.
     * `delete-diet-goal 1` removes the goal from the diet goal records.
   * Test case 2:
     * 'delete-diet-goal' without any index to delete the goal or non-positive integers provided 
     or the value is greater than the number of diet goals present in the diet goal records, error will be thrown.
4. Editing diet goals
   * This is similar to setting diet goal, but the goal is required to be in the diet goals record first.
   * Users are only allowed to edit the target value of the goal. There is no edit supported to edit diet goal 
   types or diet goal time span.
   * Test case 1:
     * No goals present in the records.
     * `edit-diet-goal WEEKLY calories/5000` will return an error since there are no associated goals to 
     make an edit to the goal's target value.
   * Test case 2: 
     * Weekly healthy calories goal is present with a target value of 20.
     * `edit-diet-goal WEEKLY calories/5000` will update the target value of weekly healthy calories goal to 5000.
   * Similar to setting diet goals, the weekly goal values should always be greater than the daily goal values.
### Sleep Management

#### Sleep Records

#### Sleep Goals

### Exiting Program

### Data Storage
