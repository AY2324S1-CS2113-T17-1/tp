---
layout: page
title: User Guide
---

* Table of Contents
{:toc}

**AthletiCLI** is your all-in-one solution to track, analyse, and optimize your athletic performance. Designed for the
committed athlete, this command-line interface (CLI) tool not only keeps tabs on your physical activities but also
covers dietary habits, sleep metrics, and more.

## Quick Start

* Ensure you have the required runtime environment installed on your computer.
* Download the latest AthletiCLI from the official repository.
* Copy the downloaded file to a folder you want to designate as the home for AthletiCLI.
* Open a command terminal, cd into the folder where you copied the file, and run `java -jar AthletiCLI.jar` .

## Features

**Notes about Command Format**

* Words in UPPER_CASE are parameters provided by the user.
* Parameters need to be specified in the given order unless specified otherwise.
* Parameters enclosed in square brackets [] are optional.

## Activity Management

### Adding Activities

`add-activity`

`add-run`

`add-swim`

`add-cycle`

You can record your activities in AtheltiCLI by adding different activities including running, cycling, and swimming.
A brief summary of the activity will be shown after adding the activity. Use the detailed list command to access the 
full activity insights.

**Syntax:**

* `add-activity CAPTION duration/DURATION distance/DISTANCE datetime/DATETIME`
* `add-run CAPTION duration/DURATION distance/DISTANCE datetime/DATETIME elevation/ELEVATION`
* `add-swim CAPTION duration/DURATION distance/DISTANCE datetime/DATETIME style/STYLE`
* `add-cycle CAPTION duration/DURATION distance/DISTANCE datetime/DATETIME elevation/ELEVATION`

**Parameters:**

* CAPTION: A short description of the activity.
* DURATION: The duration of the activity in ISO Time Format: HH:mm:ss.
* DISTANCE: The distance of the activity in meters. It must be a positive number.
* DATETIME: The date and time of the start of the activity. It must follow the ISO Date Time Format: yyyy-MM-dd HH:mm.
* ELEVATION: The elevation gain of a run or cycle in meters. It must be a number.
* STYLE: The style of the swim. It must be one of the following: freestyle, backstroke, breaststroke, butterfly.

**Examples:**

* `add-activity Morning Run duration/01:00:00 distance/10000 datetime/2021-09-01 06:00`
* `add-cycle Evening Ride duration/02:00:00 distance/20000 datetime/2021-09-01 18:00 elevation/1000`
* `add-swim Evening Swim duration/01:00:00 distance/1000 datetime/2023-10-16 20:00 style/freestyle`

### Deleting Activities

`delete-activity`

Accidentally added an activity? You can quickly delete any type of activity including run, swims and cycles by using 
the following command.

**Syntax:**

* `delete-activity INDEX`

**Parameters:**

* INDEX: The index of the activity as shown in the displayed activity list. Note, that the list is sorted by 
  date and that the index must be a positive number which is not larger than the number of activities recorded.

**Examples:**

* `delete-activity 2` Deletes the second activity in the activity list.
* `delete-activity 1` Deletes the most recent activity in the activity list.

### Listing Activities

`list-activity`

By using this command, you can see all your tracked activities in a list sorted by date. For more 
detailed information about your activities including evaluations like pace (running), speed (cycling) or lap time 
(swimming), you can use the `-d` flag.

**Syntax:**

* `list-activity [-d]`

**Parameters:**

* `-d`: Shows a detailed list of the activities.

**Metrics:**
* Pace: the average time taken to run 1km. Common performance metric for runners.
* Speed: the average speed of the cycle in km/h. Common performance metric for cyclists.
* Lap Time: the time taken to swim 1 lap (50m). Common performance metric for swimmers.

**Examples:**

* `list-activity` Shows a brief overview of all activities.
* `list-activity -d` Shows a detailed summary of all activities.

### Editing Activities

`edit-activity`

`edit-run`

`edit-swim`

`edit-cycle`

You can edit your activities in AthletiCLI by editing the activity at the specified index.
Specify the parameters you want to edit with the corresponding flags. At least one parameter must be specified.

**Syntax:**

* `edit-activity INDEX [caption/CAPTION] [duration/DURATION] [distance/DISTANCE] [datetime/DATETIME]`
* `edit-run INDEX [caption/CAPTION] [duration/DURATION] [distance/DISTANCE] [datetime/DATETIME] [elevation/ELEVATION]`
* `edit-swim INDEX [caption/CAPTION] [duration/DURATION] [distance/DISTANCE] [datetime/DATETIME] [style/STYLE]`
* `edit-cycle INDEX [caption/CAPTION] [duration/DURATION] [distance/DISTANCE] [datetime/DATETIME] [elevation/ELEVATION]`

**Parameters:**

* INDEX: The index of the activity to be edited - must be a positive number which is not larger than the number of 
  activities recorded. Note, that the indices are allocated based on the date of the activity.
* See [adding activities](#adding-activities) for the other parameters.

**Examples:**

* `edit-activity 1 caption/Morning Run distance/10000`
* `edit-cycle 2 datetime/2021-09-01 18:00 elevation/1000`

### Setting Activity Goals

`set-activity-goal`

You can set goals for your activities in AthletiCLI by setting the target distance or duration for a specific sport.
The goals can track your daily, weekly, monthly, or yearly progress.

**Syntax**

* `set-activity-goal sport/SPORT type/TYPE period/PERIOD target/TARGET`

**Parameters**

* SPORT: The sport for which you want to set a goal. It must be one of the following: run, swim, cycle, general.
* TYPE: The metric for which you want to set a goal. It must be one of the following: distance, duration.
* PERIOD: The period for which you want to set a goal. It must be one of the following: daily, weekly, monthly, 
  yearly. Only activities that are recorded within the period will be counted towards the goal.
* TARGET: The target value. It must be a positive number. For distance, it is in meters. For duration, it is in
  minutes.

**Examples**

* `set-activity-goal sport/running type/distance period/weekly target/10000` Sets a goal of running 10km per week.
* `set-activity-goal sport/swimming type/duration period/monthly target/120` Sets a goal of swimming for 2 hours per
  month.

### Editing Activity Goals

`edit-activity-goal`

You can edit your already set goals by mentioning the sport, target, and period of the goal you want to edit.

**Syntax**

* `edit-activity-goal sport/SPORT target/TARGET period/PERIOD value/VALUE`

**Parameters**

* SPORT: The sport for which you want to set a goal. It must be one of the following: running, swimming, cycling, general.
* TARGET: The target for which you want to set a goal. It must be one of the following: distance, duration.
* PERIOD: The period for which you want to set a goal. It must be one of the following: daily, weekly, monthly, yearly.
* VALUE: The value of the target. It must be a positive number. For distance, it is in meters. For duration, it is in minutes.

**Examples**

* `edit-activity-goal sport/running type/distance period/weekly target/20000` Edits the goal of running 20km per week.
* `edit-activity-goal sport/swimming type/duration period/monthly target/60` Edits the goal of swimming for 1 hour per month.

### Listing Activity Goals

`list-activity-goal`

You can list all your goals in AthletiCLI and see your progress towards them.

**Syntax**

* `list-activity-goal`

**Examples**

* `list-activity-goal` Lists all your goals.

## Diet Management

### Adding Diets

`add-diet`

You can record your diet in AtheltiCLI by adding your calorie, protein, carbohydrate,and fat intake of your meals.

**Syntax:**

* `add-diet calories/CALORIES protein/PROTEIN carb/CARB fat/FAT datetime/DATETIME`

**Parameters:**

* CALORIES: The total calories of the meal.
* PROTEIN: The total protein of the meal.
* CARB: The total carbohydrates of the meal.
* FAT: The total fat of the meal.
* DATETIME: The date and time of the meal. It must follow the ISO Date Time Format: yyyy-MM-dd HH:mm.

**Examples:**

* `add-diet calories/500 protein/20 carb/50 fat/10 datetime/2021-09-01 06:00`

### Editing Diets

`edit-diet`

You can edit your diet in AtheltiCLI by editing the diet at the specified index.

**Syntax:**

* `edit-diet INDEX [calories/CALORIES] [protein/PROTEIN] [carb/CARB] [fat/FAT] [datetime/DATETIME]`

**Parameters:**

* INDEX: The index of the diet to be edited - must be a positive integer.
* CALORIES: The total calories of the meal.
* PROTEIN: The total protein of the meal.
* CARB: The total carbohydrates of the meal.
* FAT: The total fat of the meal.
* DATETIME: The date and time of the meal. It must follow the ISO Date Time Format: yyyy-MM-dd HH:mm.

**Examples:**

* `edit-diet 1 calories/500 protein/20 carb/50 fat/10 datetime/2021-09-01 06:00`
* `edit-diet 1 datetime/2021-09-01 06:00 protein/20 carb/50 calories/500 fat/10`
* `edit-diet 1 calories/500 protein/20 carb/50 fat/10`
* `edit-diet 1 calories/500`
* `edit-diet 1 protein/20`

### Deleting Diets

`delete-diet`

You can delete your diet in AtheltiCLI by deleting the diet at the specified index.

**Syntax:**

* `delete-diet INDEX`

**Parameters:**

* INDEX: The index of the diet to be deleted - must be a positive integer.

**Examples:**

* `delete-diet 1`

### Listing Diets

`list-diet`

You can list all your diets in AtheltiCLI.

**Syntax:**

* `list-diet`

**Examples:**

* `list-diet`

### Finding Diets

`find-diet date/DATE`

You can find all your diets on a specific date in AtheltiCLI.

**Syntax:**

* `find-diet date/DATE`

**Parameters:**

* DATE: The date of the diet. It must follow the ISO Date Format: yyyy-MM-dd.

**Examples:**

* `find-diet date/2021-09-01`

## Diet Goal Management

### Adding Diet Goals

`set-diet-goal`

You can create a new daily or weekly diet goal to track your nutrients intake with AtheltiCLI by adding the nutrients you wish to track and the target value for your nutrient goals.

You can set multiple nutrients goals at once with the `set-diet-goal` command.

**Note: At least one of the nutrients (CALORIES,PROTEIN,CARB,FAT) must be present!**

**Syntax:**

* `set-diet-goal <DAILY/WEEKLY> [unhealthy] [calories/CALORIES] [protein/PROTEIN] [carb/CARBS] [fat/FAT]`

**Parameters:**

* DAILY/WEEKLY: Determines if the goal is set for a day or set for the week. It accepts 2 values.
  DAILY goals account for what you eat for the day.
  WEEKLY goals account for what you eat for the week.
* * unhealthy: This determines if you are trying to get more of this nutrient or less of it.
    If this flag is placed, it means that you are trying to reduce the intake. Hence, exceeding the target value means
    that you have not achieved your goal. If this flag is absent, it means that you are trying to increase the intake.
* CALORIES: Your target value for calories intake, in terms of calories. The target value must be a positive integer.
* PROTEIN: Your target for protein intake, in terms of milligrams. The target value must be a positive integer.
* CARB: Your target value for carbohydrate intake, in terms of milligrams. The target value must be a positive integer.
* FAT: Your target value for fats intake, in terms of milligrams. The target value must be a positive integer.

**Note: At least one of the nutrients (CALORIES,PROTEIN,CARB,FAT) must be present!**

You can create one or multiple nutrient goals at once with this command.

**Examples:**

* `set-diet-goal WEEKLY calories/500 fats/600` Creates multiple 2 nutrient goals: calories and fats.

* `set-diet-goal DAILY calories/500` Creates a single calories goal.

### Deleting Diet Goals

`delete-diet-goal`

You can delete your diet goals in AtheltiCLI by deleting the goal at the specified index.
This index will be referenced via `list-diet-goal` command.

**Syntax:**

* `delete-diet-goal INDEX`

**Parameters:**

* INDEX: The index of the diet goal to be deleted. It must be a positive integer.

**Examples:**

* `delete-diet-goal 1` Deletes a diet goal that is located on the first index of the list.

### Listing Diet Goals

`list-diet-goal`

You can list all your diet goals in AtheltiCLI.

**Syntax:**

* `list-diet-goal`

**Examples:**

* `list-diet-goal`

### Editing Diet Goals

`edit-diet-goal`

You can edit the target value of your diet goals in AtheltiCLI, redefining the target value for the specified nutrient.

This command takes in at least 2 arguments. You are able to edit multiple diet goals target value of the same time frame at once. 
No repetition is allowed. The diet goal needs to be present before any edits is allowed.

**Syntax:**

* `edit-diet-goal <DAILIY/WEEKLY> [unhealthy] [calories/CALORIES] [protein/PROTEIN] [carb/CARBS] [fat/FAT]`

**Parameters:**

* DAILY/WEEKLY: This determines if the goal you want to edit is a daily goal or a weekly goal. It accepts 2 values.
  DAILY goals account for what you eat for the day.
  WEEKLY goals account for what you eat for the week.
* unhealthy: This determines if you are trying to get more of this nutrient or less of it. 
If this flag is placed, it means that you are trying to reduce the intake. Hence, exceeding the target value means 
that you have not achieved your goal. If this flag is absent, it means that you are trying to increase the intake.
* CALORIES: Your target value for calories intake, in terms of cal. The target value must be a positive integer.
* PROTEIN: The target for protein intake, in terms of milligrams. The target value must be a positive integer.
* CARBS: Your target value for carbohydrate intake, in terms of milligrams. The target value must be a positive integer.
* FAT: Your target value for fats intake, in terms of milligrams. The target value must be a positive integer.

**Note: At least one of the nutrients (CALORIES,PROTEIN,CARB,FAT) must be present!**

You can create one or multiple nutrient goals with this command.

**Examples:**

* `edit-diet-goal DAILY calories/5000 protein/200 carb/500 fat/100` 
Edits multiple nutrients goals if all of them exists.
* `edit-diet-goal WEEKLY calories/5000` 
Edits a single calories goal if the goal exists.


## Sleep Management

### Adding Sleep

`add-sleep`  

You can record your sleep timings in AtheltiCLI by adding your sleep start and end time. It also automagically calculates for you the duration of your sleep, as well as the sleep date.

**Syntax:**

* `add-sleep start/START end/END`

**Parameters:**

* START: The start time of the sleep. It must follow the ISO Date Time Format: yyyy-MM-dd HH:mm.

* END: The end time of the sleep. It must follow the ISO Date Time Format: yyyy-MM-dd HH:mm.

**Examples:**

Take note that all sleep entries have an assosciated date. 

All sleep entries with a start time before 06:00 will be taken to represent the previous days sleep. 

* `add-sleep start/2023-01-20 02:00 end/2023-01-20 08:00`  will be taken to represent the sleep record on `2022-01-19`, which is the day before, since the start time is before 06:00 on `2022-01-20`.

* `add-sleep start/2022-01-20 22:00 end/2022-01-21 06:00` will be taken to represent the sleep record on `2022-01-20`, since the start time is after 06:00 on `2022-01-20`.

### Listing Sleep

`list-sleep`

You can see all your tracked sleep records in a list by using this command.

**Syntax:** `list-sleep`

**Example:** `list-sleep`

### Deleting Sleep

`delete-sleep`  

Accidentally added a sleep record? You can quickly delete sleep records by using the following command.
The index must be a positive number and is not larger than the number of sleep records recorded.

**Syntax:**

* `delete-sleep INDEX`

**Parameters:**

* INDEX: The index of the sleep record you wish to delete. It must be a positive number and is not larger than the number of sleep records recorded.
Refer to the list-sleep command for the index of the sleep record you wish to delete.

**Examples:**

Assuming that there are 5 sleep records in the list:

* `delete-sleep 5`  will delete the 5th sleep record in the sleep records list.
* `delete-sleep 1`  will delete the 1st sleep record in the sleep records list.

### Editing Sleep

`edit-sleep`  

You can modify existing sleep records in AtheltiCLI by specifying the sleep's index and then providing the new start and end times.

**Syntax:**

* `edit-sleep INDEX start/START end/END`

**Parameters:**

* INDEX: The index of the sleep record you wish to edit. It must be a positive number and is not larger than the number of sleep records recorded.
* START: The new start time of the sleep. It must follow the ISO Date Time Format: yyyy-MM-dd HH:mm.
* END: The new end time of the sleep. It must follow the ISO Date Time Format: yyyy-MM-dd HH:mm.

**Examples:**

Assuming that there are 5 sleep records in the list:

* `edit-sleep 5 2023-01-20 02:00 2023-01-20 08:00`  will edit the 5th sleep record in the sleep records list to have a start time of `2023-01-20 02:00` and an end time of `2023-01-20 08:00`. 

* `edit-sleep 1 2022-01-20 22:00 2022-01-21 06:00` will edit the 1st sleep record in the sleep records list to have a start time of `2022-01-20 22:00` and an end time of `2022-01-21 06:00`.

### Finding Sleep

`find-sleep date/DATE`

You can find your sleep record on a specific date in AtheltiCLI.

**Syntax:**

* `find-sleep date/DATE`

**Parameters:**

* DATE: The date of the sleep. It must follow the ISO Date Format: yyyy-MM-dd.

**Examples:**

* `find-sleep date/2021-09-01`

---

## Miscellaneous

### Finding Records

You can find all your records, including activities, sleeps, and diets, on a specific date in AtheltiCLI.

**Syntax:**

* `find DATE`

**Parameters:**

* `DATE`: The date of the records. It must follow the ISO Date Format: `yyyy-MM-dd`.

**Example:**

* `find 2023-11-01`

### Saving Files

You can save files while using AthletiCLI if you want to, rather than waiting until the AthletiCLI exits to automatically save them.

**Syntax:**

* `save`

### Exiting AthletiCLI

You can use the `bye` command at any time to safely store the file and exit AthletiCLI.

**Syntax:**

* `bye`

### Viewing Help Messages

If you forget a command, you can always use the `help` command to see their syntax.

**Syntax:**

* `help [COMMAND]`

**Parameters:**

* `COMMAND`: The command you want to view. If it is omitted, a list containing the syntax of all commands will be shown.

**Examples:**

* `help` lists the syntax of all commands.
* `help add-diet` shows the syntax of the `add-diet` command.


# Summary of Commands

## Activity Management

| **Command**               | **Syntax**                                                                                    | **Parameters**                                         | **Examples**                                             |
|---------------------------|-----------------------------------------------------------------------------------------------|--------------------------------------------------------|----------------------------------------------------------|
| `add-activity`            | `add-activity CAPTION duration/DURATION distance/DISTANCE datetime/DATETIME`                  | CAPTION, DURATION, DISTANCE, DATETIME                  | `add-activity Morning Run duration/60 distance/10000 datetime/2021-09-01 06:00` |
| `add-run`                 | `add-run CAPTION duration/DURATION distance/DISTANCE datetime/DATETIME elevation/ELEVATION`   | CAPTION, DURATION, DISTANCE, DATETIME, ELEVATION      | -                                                        |
| `add-swim`                | `add-swim CAPTION duration/DURATION distance/DISTANCE datetime/DATETIME laps/LAPS`            | CAPTION, DURATION, DISTANCE, DATETIME, LAPS            | -                                                        |
| `add-cycle`               | `add-cycle CAPTION duration/DURATION distance/DISTANCE datetime/DATETIME elevation/ELEVATION` | CAPTION, DURATION, DISTANCE, DATETIME, ELEVATION      | `add-cycle Evening Ride duration/120 distance/20000 datetime/2021-09-01 18:00 elevation/1000` |
| `delete-activity`         | `delete-activity INDEX`                                                                       | INDEX                                                  | `delete-activity 2`                                       |
| `list-activity`           | `list-activity [-d]`                                                                          | -d                                                     | `list-activity`, `list-activity -d`                        |
| `edit-activity`           | `edit-activity INDEX CAPTION duration/DURATION distance/DISTANCE datetime/DATETIME`           | INDEX, CAPTION, DURATION, DISTANCE, DATETIME           | `edit-activity 1 Morning Run duration/60 distance/10000 datetime/2021-09-01 06:00` |
| `edit-run`                | Similar to `edit-activity` but with elevation.                                                | Same as `edit-activity` with ELEVATION                 | -                                                        |
| `edit-swim`               | Similar to `edit-activity` but with laps.                                                     | Same as `edit-activity` with LAPS                      | -                                                        |
| `edit-cycle`              | Similar to `edit-activity` but with elevation.                                                | Same as `edit-activity` with ELEVATION                 | `edit-cycle 2 Evening Ride duration/120 distance/20000 datetime/2021-09-01 18:00 elevation/1000` |
| `set-activity-goal`       | `set-activity-goal sport/SPORT type/TYPE period/PERIOD target/TARGET`                         | SPORT, TARGET, PERIOD, VALUE                           | `set-activity-goal sport/running type/distance period/weekly target/10000` |
| `edit-activity-goal`      | `edit-activity-goal sport/SPORT type/TYPE period/PERIOD target/TARGET`                        | SPORT, TARGET, PERIOD, VALUE                           | `edit-activity-goal sport/running type/distance period/weekly target/20000` |
| `list-activity-goal`      | `list-activity-goal`                                                                          | None                                                   | `list-activity-goal`                                       |

## Diet Management

| **Command**               | **Syntax**                                                                          | **Parameters**                                         | **Examples**                                             |
|---------------------------|-------------------------------------------------------------------------------------|--------------------------------------------------------|----------------------------------------------------------|
| `add-diet`                | `add-diet calories/CALORIES protein/PROTEIN carb/CARB fat/FAT datetime/DATETIME`    | CALORIES, PROTEIN, CARB, FAT, DATETIME                 | `add-diet calories/500 protein/20 carb/50 fat/10 datetime/2021-09-01 06:00` |
| `edit-diet`               | `edit-diet INDEX [calories/CALORIES] [protein/PROTEIN] [carb/CARB] [fat/FAT] [datetime/DATETIME]` | INDEX, [CALORIES], [PROTEIN], [CARB], [FAT], [DATETIME] | `edit-diet 1 calories/500 protein/20 carb/50 fat/10 datetime/2021-09-01 06:00` |
| `delete-diet`             | `delete-diet INDEX`                                                                 | INDEX                                                  | `delete-diet 1`                                           |
| `list-diet`               | `list-diet`                                                                         | None                                                   | `list-diet`                                               |
| `find-diet`               | `find-diet date/DATE`                                                               | DATE                                                   | `find-diet date/2021-09-01`                               |
| `set-diet-goal`           | `set-diet-goal <DAILY/WEEKLY> [calories/CALORIES] [protein/PROTEIN] [carb/CARBS] [fat/FAT]` | DAILY/WEEKLY, [CALORIES], [PROTEIN], [CARBS], [FAT]    | `set-diet-goal WEEKLY calories/500 fats/600` |
| `edit-diet-goal`          | `edit-diet-goal <DAILIY/WEEKLY> [calories/CALORIES] [protein/PROTEIN] [carb/CARBS] [fat/FAT]` | DAILY/WEEKLY, [CALORIES], [PROTEIN], [CARBS], [FAT]    | `edit-diet-goal WEEKLY calories/500 fats/600` |
| `delete-diet-goal`        | `delete-diet-goal INDEX`                                                            | INDEX                                                  | `delete-diet-goal 1`                                      |
| `list-diet-goal`          | `list-diet-goal`                                                                    | None                                                   | `list-diet-goal`                                          |


## Sleep Management

| **Command**               | **Syntax**                                                                          | **Parameters**                                         | **Examples**                                             |
|---------------------------|-------------------------------------------------------------------------------------|--------------------------------------------------------|----------------------------------------------------------|
| `add-sleep`               | `add-sleep start/START end/END`                                                      | START, END                                             | `add-sleep start/2023-01-20 02:00 end/2023-01-20 08:00` |
| `list-sleep`              | `list-sleep`                                                                        | None                                                   | `list-sleep`                                             |
| `delete-sleep`            | `delete-sleep INDEX`                                                                | INDEX                                                  | `delete-sleep 1`                                         |
| `edit-sleep`              | `edit-sleep INDEX start/START end/END`                                               | INDEX, START, END                                      | `edit-sleep 1 2023-01-20 02:00 2023-01-20 08:00`         |
| `find-sleep`              | `find-sleep date/DATE`                                                              | DATE                                                   | `find-sleep date/2021-09-01`                             |

## Miscellaneous

| **Command**               | **Syntax**                                                                          | **Parameters**                                         | **Examples**                                             |
|---------------------------|-------------------------------------------------------------------------------------|--------------------------------------------------------|----------------------------------------------------------|
| `find`                    | `find DATE`                                                                         | DATE                                                   | `find 2023-11-01`                                        |
| `save`                    | `save`                                                                              | None                                                   | `save`                                                   |
| `bye`                     | `bye`                                                                               | None                                                   | `bye`                                                    |
| `help`                    | `help [COMMAND]`                                                                    | [COMMAND]                                              | `help`, `help add-diet`                                  |
