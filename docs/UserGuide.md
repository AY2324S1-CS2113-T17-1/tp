---
layout: page
title: User Guide
---
*Your all-in-one solution to track, analyse, and optimize your athletic performance.*
*Designed for the committed athlete, this command-line interface (CLI) tool not only keeps track of your physical 
activities but also covers dietary habits, sleep metrics, and more.*

* Table of Contents
{:toc}

## 🚀 Quick Start

* ✅ Ensure you have the required runtime environment installed on your computer.
* ✅ Download the latest AthletiCLI from the official repository.
* ✅ Copy the downloaded file to a folder you want to designate as the home for AthletiCLI.
* ✅ Open a command terminal, cd into the folder where you copied the file, and run `java -jar AthletiCLI.jar` .

## Features

**Notes about Command Format**

* Words in UPPER_CASE are parameters provided by the user.
* Parameters need to be specified in the given order unless specified otherwise.
* Parameters enclosed in square brackets [] are optional.

**Notes about lack of Goal Delete for Sleep and Activity**

The absence of a "Goal Delete" feature for Sleep and Activity in the current version of AthletiCLI, while present for Diet, can be concisely justified as follows:

1. **Diversity of Diet Goals:** The Diet section likely encompasses a wider range of goals compared to Sleep and Activity. With such variability, users might frequently need to delete diet goals, making a delete function more essential in this section.

2. **Stability of Sleep and Activity Goals:** Goals related to sleep and activity are generally more consistent and less variable over time. This stability reduces the immediate need for a delete feature, as users are less likely to remove these goals frequently.

3. **Focused Development Resources:** Given limited development resources and time, the team prioritized implementing the delete feature for the Diet section, where it was deemed most necessary due to the larger volume and variability of goals.

4. **Planned for Future Implementation:** The absence of this feature in the current version for Sleep and Activity does not indicate it will never be implemented. It is planned for a future update, aligning with a phased development approach.

## 🏃 Activity Management

- [Adding Activities](#-adding-activities)
- [Deleting Activities](#-deleting-activities)
- [Listing Activities](#-listing-activities)
- [Editing Activities](#-editing-activities)
- [Setting Activity Goals](#-setting-activity-goals)
- [Editing Activity Goals](#-editing-activity-goals)
- [Listing Activity Goals](#-listing-activity-goals)

### ➕ Adding Activities:

`add-activity` `add-run` `add-swim` `add-cycle`

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
* DISTANCE: The distance of the activity in meters. It must be a positive number smaller than 1000000.
* DATETIME: The date and time of the start of the activity. It must follow the ISO Date Time Format yyyy-MM-dd HH:mm,
  must be valid, and cannot be in the future.
* ELEVATION: The elevation gain of a run or cycle in meters. It must be a positive number smaller than 10000.
* STYLE: The style of the swim. It must be one of the following: freestyle, backstroke, breaststroke, butterfly.

**Examples:**

* `add-activity Morning Run duration/01:00:00 distance/10000 datetime/2021-09-01 06:00`
* `add-cycle Evening Ride duration/02:00:00 distance/20000 datetime/2021-09-01 18:00 elevation/1000`
* `add-swim Evening Swim duration/01:00:00 distance/1000 datetime/2023-10-16 20:00 style/freestyle`

---

### ➖ Deleting Activities:

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

---

### 📅 Listing Activities:

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
<p>
  <img width="100%" src="images/listActivityShowcase.png" alt="List returned by `list-activity`"/>
</p>

* `list-activity -d` Shows a detailed summary of all activities.
<p>
  <img width="60%" src="images/listActivityDetailedShowcase.png" alt="Detailed list returned by `list-activity -d`"/>
</p>


### ✍️ Editing Activities:

`edit-activity` `edit-run` `edit-swim` `edit-cycle`

You can edit your activities in AthletiCLI by editing the activity at the specified index.
Specify the parameters you want to edit with the corresponding flags. At least one parameter must be specified.

**Syntax:**

* `edit-activity INDEX [caption/CAPTION] [duration/DURATION] [distance/DISTANCE] [datetime/DATETIME]`
* `edit-run INDEX [caption/CAPTION] [duration/DURATION] [distance/DISTANCE] [datetime/DATETIME] [elevation/ELEVATION]`
* `edit-swim INDEX [caption/CAPTION] [duration/DURATION] [distance/DISTANCE] [datetime/DATETIME] [style/STYLE]`
* `edit-cycle INDEX [caption/CAPTION] [duration/DURATION] [distance/DISTANCE] [datetime/DATETIME] [elevation/ELEVATION]`

**Parameters:**

* INDEX: The index of the activity to be edited as shown in the displayed activity list - must be a positive number 
  which is not larger than the number of activities recorded. Note, that the indices are allocated based on the date of the activity.
* See [adding activities](#-adding-activities) for the other parameters.

**Examples:**

* `edit-activity 1 caption/Morning Run distance/10000`
* `edit-cycle 2 datetime/2021-09-01 18:00 elevation/1000`

---

### 🔍 Finding Activities:

`find-activity`

You can find all your activities on a specific date in AtheltiCLI.

**Syntax:**

* `find-activity DATE`

**Parameters:**

* DATE: The date of the activity. It must follow the ISO Date Format: yyyy-MM-dd, must be valid and cannot be in 
  the future.

**Example:**

* `find-activity 2021-09-01`

---

### 🎯 Setting Activity Goals:

`set-activity-goal`

You can set goals for specific sports by defining target distance or duration over various periods. The goals can track
your daily, weekly, monthly, or yearly progress.

**Syntax**

* `set-activity-goal sport/SPORT type/TYPE period/PERIOD target/TARGET`

**Parameters**

* SPORT: The sport for which to set a goal. Options: running, cycling, swimming, general.
* TYPE: The metric for the goal. Options: distance, duration.
* PERIOD: The period for the goal. Options: daily, weekly, monthly, yearly. Only activities that are recorded within
  the period will be counted towards the goal.
* TARGET: The target value. It must be a positive number. For distance, in meters. For duration, in minutes.

**Examples**

* `set-activity-goal sport/running type/distance period/weekly target/10000` Sets a goal of running 10km per week.
* `set-activity-goal sport/swimming type/duration period/monthly target/120` Sets a goal of swimming for 2 hours 
  per month.

---

### ✍️ Editing Activity Goals:

`edit-activity-goal`

You can edit your set goals by specifying the sport, target, and period.

**Syntax**

* `edit-activity-goal sport/SPORT target/TARGET period/PERIOD target/TARGET`

**Parameters**

* TARGET: The new target value. For distance (in meters), for duration (in minutes).
* See [setting activity goals](#-setting-activity-goals) for the other parameters.

**Examples**

* `edit-activity-goal sport/running type/distance period/weekly target/20000` Adjusts the goal of running distance 
  to 20km per week.
* `edit-activity-goal sport/swimming type/duration period/monthly target/60` Adjusts the goal of swimming duration 
  to 1 hour per month.

---

### 📅 Listing Activity Goals:

`list-activity-goal`

You can list all your set goals and view your progress towards them.

**Syntax**

* `list-activity-goal`

**Examples**

* `list-activity-goal` Lists all activity goals.
<p>
  <img width="100%" src="images/listActivityGoalShowcase.png" alt="List returned by `list-activity-goal`"/>
</p>

---

### ➖ Deleting Activity Goals:

`delete-activity-goal`

You can delete your set goals by specifying the sport, target, and period.

**Syntax**

* `delete-activity-goal sport/SPORT target/TARGET period/PERIOD`

**Parameters**

* See [setting activity goals](#-setting-activity-goals) for the parameters.

**Examples**

* `delete-activity-goal sport/running type/distance period/weekly` Deletes the weekly running distance goal.
* `delete-activity-goal sport/swimming type/duration period/monthly` Deletes the monthly swimming duration goal.

---

## 🍏 Diet Management

- [Adding Diets](#-adding-diets)
- [Editing Diets](#-editing-diets)
- [Deleting Diets](#-deleting-diets)
- [Listing Diets](#-listing-diets)
- [Finding Diets](#-finding-diets)
- [Adding Diet Goals](#-adding-diet-goals)
- [Deleting Diet Goals](#-deleting-diet-goals)
- [Listing Diet Goals](#-listing-diet-goals)
- [Editing Diet Goals](#-editing-diet-goals)

### ➕ Adding Diets:

`add-diet`

You can record your diet by specifying calorie, protein, carbohydrate, and fat intake.

**Syntax:**

* `add-diet calories/CALORIES protein/PROTEIN carb/CARB fat/FAT datetime/DATETIME`

**Parameters:**

* CALORIES: Total calories (in cal) of the meal.
* PROTEIN: Total protein (in milligrams) of the meal.
* CARB: Total carbohydrates (in milligrams) of the meal.
* FAT: Total fat (in milligrams) of the meal.
* DATETIME: Date and time of the meal in ISO Date Time Format (yyyy-MM-dd HH:mm). It must be valid and cannot be in the 
  future.

**Examples:**

* `add-diet calories/500 protein/20 carb/50 fat/10 datetime/2021-09-01 06:00` Adds a diet entry with 500 calories, 
  20mg of protein, 50mg of carbohydrates, and 10mg of fat on 1st September 2021 at 6am.
* `add-diet calories/2000 datetime/2023-09-01 16:00 fat/10 carb/100 protein/200` Adds a diet entry with 2000 calories, 
  200mg of protein, 100mg of carbohydrates, and 10mg of fat on 1st September 2023 at 4pm.

---

### ✍️ Editing Diets:

`edit-diet`

You can modify existing diet entries by specifying the index of the diet you wish to edit.

**Syntax:**

* `edit-diet INDEX [calories/CALORIES] [protein/PROTEIN] [carb/CARB] [fat/FAT] [datetime/DATETIME]`

**Parameters:**

* INDEX: Index of the diet entry (positive integer).
* See [adding diets](#-adding-diets) for the other parameters.

**Examples:**

* `edit-diet 1 calories/500 protein/20 carb/50 fat/10 datetime/2021-09-01 06:00` Edits the first diet entry.
* `edit-diet 1 protein/215` Edits the first diet entry to have 215mg of protein.

*Note: Find the index of your diet entry in the listing section.*

---

### ➖ Deleting Diets:

`delete-diet`

You can remove a diet entry from your records.

**Syntax:**

* `delete-diet INDEX`

**Parameters:**

* INDEX: Index of the diet to be deleted (positive integer).

**Examples:**

* `delete-diet 1` Deletes the first diet entry.

---

### 📅 Listing Diets:

`list-diet`

You can view a list of all your recorded diets.

**Syntax:**

* `list-diet`

**Examples:**

* `list-diet` Lists all diets.
<p>
  <img width="100%" src="images/listDietShowcase.png" alt="List returned by `list-diet`"/>
</p>

---

### 🔍 Finding Diets:

`find-diet DATE`

You can locate diets recorded on a specific date.

**Syntax:**

* `find-diet DATE`

**Parameters:**

* DATE: Date of the diet in ISO Date Format (yyyy-MM-dd). It must be valid and cannot be in the future.

**Examples:**

* `find-diet 2021-09-01` Finds diets recorded on 1st September 2021.

---

### 🎯 Adding Diet Goals:

`set-diet-goal`

You can create a new daily or weekly diet goal to track your nutrients intake with AtheltiCLI by adding the nutrients you wish to track and the target value for your nutrient goals.

You can set multiple nutrients goals at once with the `set-diet-goal` command.

**Note: At least one of the nutrients (CALORIES,PROTEIN,CARB,FATS) must be present!**

**Syntax:**

* `set-diet-goal <DAILY/WEEKLY> [unhealthy] [calories/CALORIES] [protein/PROTEIN] [carb/CARBS] [fats/FATS]`

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
* FATS: Your target value for fats intake, in terms of milligrams. The target value must be a positive integer.

**Note: At least one of the nutrients (CALORIES,PROTEIN,CARB,FATS) must be present!**

You can create one or multiple nutrient goals at once with this command.

**Examples:**

* `set-diet-goal WEEKLY calories/500 fats/600` Creates multiple 2 nutrient goals: calories and fats.

* `set-diet-goal DAILY calories/500` Creates a single calories goal.

---

### ➖ Deleting Diet Goals:

`delete-diet-goal`

You can delete your diet goals in AtheltiCLI by deleting the goal at the specified index.
This index will be referenced via `list-diet-goal` command.

**Syntax:**

* `delete-diet-goal INDEX`

**Parameters:**

* INDEX: The index of the diet goal to be deleted. It must be a positive integer and 
it is bounded by the number of diet goals available.

**Examples:**

* `delete-diet-goal 1` Deletes a diet goal that is located on the first index of the list.

---

### 📅 Listing Diet Goals:

`list-diet-goal`

You can list all your diet goals in AtheltiCLI.

**Syntax:**

* `list-diet-goal`

**Examples:**

* `list-diet-goal`

---

### ✍️ Editing Diet Goals:

`edit-diet-goal`

You can edit the target value of your diet goals in AtheltiCLI, redefining the target value for the specified nutrient.

This command takes in at least 2 arguments. You are able to edit multiple diet goals target value of the same time frame at once. 
No repetition is allowed. The diet goal needs to be present before any edits is allowed.

**Syntax:**

* `edit-diet-goal <DAILIY/WEEKLY> [unhealthy] [calories/CALORIES] [protein/PROTEIN] [carb/CARBS] [fats/FATS]`

**Parameters:**

* DAILY/WEEKLY: This determines if the goal you want to edit is a daily goal or a weekly goal. It accepts 2 values.
  DAILY goals account for what you eat for the day.
  WEEKLY goals account for what you eat for the week.
* unhealthy: This determines if you are trying to get more of this nutrient or less of it. 
This flag is used to change goals that are set as unhealthy previously.
* CALORIES: Your target value for calories intake, in terms of cal. The target value must be a positive integer.
* PROTEIN: The target for protein intake, in terms of milligrams. The target value must be a positive integer.
* CARBS: Your target value for carbohydrate intake, in terms of milligrams. The target value must be a positive integer.
* FATS: Your target value for fats intake, in terms of milligrams. The target value must be a positive integer.

**Note: At least one of the nutrients (CALORIES,PROTEIN,CARB,FATS) must be present!**

You can edit one or multiple nutrient goals with this command.

**Examples:**

* `edit-diet-goal DAILY calories/5000 protein/200 carb/500 fats/100` 
Edits multiple nutrients goals if all of them exists.
* `edit-diet-goal WEEKLY calories/5000` 
Edits a single calories goal if the goal exists.

---

## 🛌 Sleep Management

- [Adding Sleep](#-adding-sleep)
- [Listing Sleep](#-listing-sleep)
- [Deleting Sleep](#-deleting-sleep)
- [Editing Sleep](#-editing-sleep)
- [Finding Sleep](#-finding-sleep)
- [Adding Sleep Goals](#-adding-sleep-goals)
- [Listing Sleep Goals](#-listing-sleep-goals)
- [Editing Sleep Goals](#-editing-sleep-goals)

### ➕ Adding Sleep:

`add-sleep`  

You can record your sleep timings in AtheltiCLI by adding your sleep start and end time. It also automagically calculates for you the duration of your sleep, as well as the sleep date.

**Syntax:**

* `add-sleep start/START end/END`

**Parameters:**

* START: The start time of the sleep. It must follow the ISO Date Time Format: yyyy-MM-dd HH:mm, must be valid 
  and cannot be in the future.

* END: The end time of the sleep. It must follow the ISO Date Time Format: yyyy-MM-dd HH:mm, must be valid and 
  cannot be in the future.

**Examples:**

Take note that all sleep entries have an associated date. 

All sleep entries with a start time before 06:00 will be taken to represent the previous days sleep. 

* `add-sleep start/2023-01-20 02:00 end/2023-01-20 08:00`  will be taken to represent the sleep record on `2022-01-19`, which is the day before, since the start time is before 06:00 on `2022-01-20`.

* `add-sleep start/2022-01-20 22:00 end/2022-01-21 06:00` will be taken to represent the sleep record on `2022-01-20`, since the start time is after 06:00 on `2022-01-20`.

---

### 📅 Listing Sleep:

`list-sleep`

You can see all your tracked sleep records in a list by using this command.

**Syntax:** `list-sleep`

**Example:** `list-sleep`

---

### ➖ Deleting Sleep:

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

---

### ✍️ Editing Sleep:

`edit-sleep`  

You can modify existing sleep records in AtheltiCLI by specifying the sleep's index and then providing the new start and end times.

**Syntax:**

* `edit-sleep INDEX start/START end/END`

**Parameters:**

* INDEX: The index of the sleep record you wish to edit. It must be a positive number and is not larger than the number of sleep records recorded.
* START: The new start time of the sleep. It must follow the ISO Date Time Format: yyyy-MM-dd HH:mm, must be 
  valid and cannot be in the future.
* END: The new end time of the sleep. It must follow the ISO Date Time Format: yyyy-MM-dd HH:mm, must be valid and 
  cannot be in the future.

**Examples:**

Assuming that there are 5 sleep records in the list:

* `edit-sleep 5 start/2023-01-20 02:00 end/2023-01-20 08:00`  will edit the 5th sleep record in the sleep records list to have a start time of `2023-01-20 02:00` and an end time of `2023-01-20 08:00`. 

* `edit-sleep 1 start/2022-01-20 22:00 end/2022-01-21 06:00` will edit the 1st sleep record in the sleep records list to have a start time of `2022-01-20 22:00` and an end time of `2022-01-21 06:00`.

---

### 🔍 Finding Sleep:

`find-sleep DATE`

You can find your sleep record on a specific date in AtheltiCLI.

**Syntax:**

* `find-sleep DATE`

**Parameters:**

* DATE: The date of the sleep. It must follow the ISO Date Format: yyyy-MM-dd, must be valid and cannot be in the 
  future.

**Examples:**

* `find-sleep 2021-09-01`

---

### 🎯 Setting Sleep Goals:

`set-sleep-goal`

You can set goals for your sleep AthletiCLI by setting the target duration specified in minutes. Tracking can be done for the past day, week, month or year.

**NOTE: Only one sleep goal can be set for each time period.**

**Syntax:**
* `set-sleep-goal type/TYPE period/PERIOD target/TARGET`

**Parameters:**
* TYPE: The type of sleep goal. It must be the following: `duration`.
* PERIOD: The period for which you want to set a goal. It must be one of the following: `daily, weekly, monthly, yearly`. Only sleeps that are recorded within the period from the current time will be counted towards the goal.
* TARGET: The target value. It must be a positive number. For duration, it is in minutes.

**Examples:**
* `set-sleep-goal type/duration period/daily target/420` Sets a goal of sleeping 7 hours per day.
* `set-sleep-goal type/duration period/weekly target/2940` Sets a goal of sleeping 49 hours per week. 

---

### 📅 Editing Sleep Goals:

`edit-sleep-goal`

You can edit your already set sleep goals by mentioning the type, period, and target of the goal you want to edit.

**Syntax:**
* `edit-sleep-goal type/TYPE period/PERIOD target/TARGET`

**Parameters:**
* TYPE: The type of sleep goal. It must be the following: `duration`.
* PERIOD: The period for which you want to set a goal. It must be one of the following: `daily, weekly, monthly, yearly`. Only sleeps that are recorded within the period from the current timewill be counted towards the goal.
* TARGET: The target value. It must be a positive number. For duration, it is in minutes.

**Examples:**
* `edit-sleep-goal type/duration period/daily target/360` Edits the daily goal to sleeping 6 hours per day.
* `edit-sleep-goal type/duration period/weekly target/2520` Edits the weekly goal to sleeping 42 hours per week.

---

### 📅 Listing Sleep Goals:

`list-sleep-goal`

You can list all your sleep goals in AthletiCLI and see your progress towards them.

---

## Miscellaneous

### 🔍 Finding Records:

You can find all your records, including activities, sleeps, and diets, on a specific date in AtheltiCLI.

**Syntax:**

* `find DATE`

**Parameters:**

* `DATE`: The date of the records. It must follow the ISO Date Format `yyyy-MM-dd`, must be valid and cannot be in 
  the future.

**Example:**

* `find 2023-11-01`

---

### 📦 Saving Files:

You can save files while using AthletiCLI if you want to, rather than waiting until the AthletiCLI exits to automatically save them.

**Syntax:**

* `save`

---

### 👋 Exiting AthletiCLI:

You can use the `bye` command at any time to safely store the file and exit AthletiCLI.

**Syntax:**

* `bye`

---

### ℹ️ Viewing Help Messages:

If you forget a command, you can always use the `help` command to see their syntax.

**Syntax:**

* `help [COMMAND]`

**Parameters:**

* `COMMAND`: The command you want to view. If it is omitted, a list containing the syntax of all commands will be shown.

**Examples:**

* `help` lists the syntax of all commands.
* `help add-diet` shows the syntax of the `add-diet` command.

---

## FAQ
  **Q: *Am I allowed to update the storage files?***

  **A**: 
  While it is generally advisable not to edit the contents of the storage file, you do have the option to make updates. 
  Please exercise caution when doing so. Incorrect edits to the storage file can result in data loss. If AthleticCLI 
  encounters incorrect format of the file contents, it will prompt you to exit using the 
  [bye](#exiting-athleticli) command. 
  Continuing with the program in such cases will lead to the deletion of all data in the file.
  

---
## Summary of Commands

### Activity Management

| **Command**               | **Syntax**                                                                                          | **Parameters**                                   | **Examples**                                                                                      |
|---------------------------|-----------------------------------------------------------------------------------------------------|--------------------------------------------------|---------------------------------------------------------------------------------------------------|
| `add-activity`            | `add-activity CAPTION duration/DURATION distance/DISTANCE datetime/DATETIME`                        | CAPTION, DURATION, DISTANCE, DATETIME            | `add-activity Morning Run duration/01:00:00 distance/10000 datetime/2021-09-01 06:00`            |
| `add-run`                 | `add-run CAPTION duration/DURATION distance/DISTANCE datetime/DATETIME elevation/ELEVATION`         | CAPTION, DURATION, DISTANCE, DATETIME, ELEVATION | `add-run NY Marathon duration/03:33:17 distance/42125 datetime/2023-11-05 07:00`                  |
| `add-swim`                | `add-swim CAPTION duration/DURATION distance/DISTANCE datetime/DATETIME style/STYLE`                | CAPTION, DURATION, DISTANCE, DATETIME, STYLE     | `add-swim Evening Swim duration/01:00:00 distance/1000 datetime/2023-10-16 20:00 style/freestyle`              |
| `add-cycle`               | `add-cycle CAPTION duration/DURATION distance/DISTANCE datetime/DATETIME elevation/ELEVATION`       | CAPTION, DURATION, DISTANCE, DATETIME, ELEVATION | `add-cycle Evening Ride duration/02:00:00 distance/20000 datetime/2021-09-01 18:00 elevation/1000` |
| `delete-activity`         | `delete-activity INDEX`                                                                             | INDEX                                            | `delete-activity 2`                                                                               |
| `list-activity`           | `list-activity [-d]`                                                                                | -d                                               | `list-activity`, `list-activity -d`                                                               |
| `edit-activity`           | `edit-activity INDEX [caption/CAPTION] [duration/DURATION] [distance/DISTANCE] [datetime/DATETIME]` | INDEX, CAPTION, DURATION, DISTANCE, DATETIME     | `edit-activity 1 caption/Morning Run distance/10000`                |
| `edit-run`                | Similar to `edit-activity` but with elevation.                                                      | Same as `edit-activity` with ELEVATION           | -                                                                                                 |
| `edit-swim`               | Similar to `edit-activity` but with style.                                             <br/>             | Same as `edit-activity` with STYLE               | -                                                                                                 |
| `edit-cycle`              | Similar to `edit-activity` but with elevation.                                                      | Same as `edit-activity` with ELEVATION           | `edit-cycle 2 datetime/2021-09-01 18:00 elevation/1000`  |
| `find-activity`           | `find-activity DATE`                                                                                | DATE                                             | `find-activity 2021-09-01`                                                                        |
| `set-activity-goal`       | `set-activity-goal sport/SPORT type/TYPE period/PERIOD target/TARGET`                               | SPORT, TYPE, PERIOD, TARGET                      | `set-activity-goal sport/running type/distance period/weekly target/10000`                        |
| `edit-activity-goal`      | `edit-activity-goal sport/SPORT type/TYPE period/PERIOD target/TARGET`                              | SPORT, TYPE, PERIOD, TARGET                      | `edit-activity-goal sport/running type/distance period/weekly target/20000`                       |
| `list-activity-goal`      | `list-activity-goal`                                                                                | None                                             | `list-activity-goal`                                                                              |
| `delete-activity-goal`    | `delete-activity-goal sport/SPORT type/TYPE period/PERIOD`                                    | SPORT, TYPE, PERIOD                              | `delete-activity-goal sport/running type/distance period/weekly` |

### Diet Management

| **Command**               | **Syntax**                                                                                        | **Parameters**                                         | **Examples**                                             |
|---------------------------|---------------------------------------------------------------------------------------------------|--------------------------------------------------------|----------------------------------------------------------|
| `add-diet`                | `add-diet calories/CALORIES protein/PROTEIN carb/CARB fat/FAT datetime/DATETIME`                  | CALORIES, PROTEIN, CARB, FAT, DATETIME                 | `add-diet calories/500 protein/20 carb/50 fat/10 datetime/2021-09-01 06:00` |
| `edit-diet`               | `edit-diet INDEX [calories/CALORIES] [protein/PROTEIN] [carb/CARB] [fat/FAT] [datetime/DATETIME]` | INDEX, [CALORIES], [PROTEIN], [CARB], [FAT], [DATETIME] | `edit-diet 1 calories/500 protein/20 carb/50 fat/10 datetime/2021-09-01 06:00` |
| `delete-diet`             | `delete-diet INDEX`                                                                               | INDEX                                                  | `delete-diet 1`                                           |
| `list-diet`               | `list-diet`                                                                                       | None                                                   | `list-diet`                                               |
| `find-diet`               | `find-diet DATE`                                                                             | DATE                                                   | `find-diet 2021-09-01`                               |
| `set-diet-goal`           | `set-diet-goal <DAILY/WEEKLY> [calories/CALORIES] [protein/PROTEIN] [carb/CARBS] [fats/FATS]`     | DAILY/WEEKLY, [CALORIES], [PROTEIN], [CARBS], [FAT]    | `set-diet-goal WEEKLY calories/500 fats/600` |
| `edit-diet-goal`          | `edit-diet-goal <DAILIY/WEEKLY> [calories/CALORIES] [protein/PROTEIN] [carb/CARBS] [fats/FATS]`   | DAILY/WEEKLY, [CALORIES], [PROTEIN], [CARBS], [FAT]    | `edit-diet-goal WEEKLY calories/500 fats/600` |
| `delete-diet-goal`        | `delete-diet-goal INDEX`                                                                          | INDEX                                                  | `delete-diet-goal 1`                                      |
| `list-diet-goal`          | `list-diet-goal`                                                                                  | None                                                   | `list-diet-goal`                                          |


### Sleep Management


| **Command**               | **Syntax**                                                                          | **Parameters**                                         | **Examples**                                             |
|---------------------------|-------------------------------------------------------------------------------------|--------------------------------------------------------|----------------------------------------------------------|
| `add-sleep`               | `add-sleep start/START end/END`                                                      | START, END                                             | `add-sleep start/2023-01-20 02:00 end/2023-01-20 08:00` |
| `list-sleep`              | `list-sleep`                                                                        | None                                                   | `list-sleep`                                             |
| `delete-sleep`            | `delete-sleep INDEX`                                                                | INDEX                                                  | `delete-sleep 1`                                         |
| `edit-sleep`              | `edit-sleep INDEX start/START end/END`                                               | INDEX, START, END                                      | `edit-sleep 1 2023-01-20 02:00 2023-01-20 08:00`         |
| `find-sleep`              | `find-sleep date/DATE`                                                              | DATE                                                   | `find-sleep date/2021-09-01`                             |
| `set-sleep-goal`          | `set-sleep-goal type/TYPE period/PERIOD target/TARGET`                               | TYPE, PERIOD, TARGET                                   | `set-sleep-goal type/duration period/daily target/420`    |
| `edit-sleep-goal`         | `edit-sleep-goal type/TYPE period/PERIOD target/TARGET`                              | TYPE, PERIOD, TARGET                                   | `edit-sleep-goal type/duration period/daily target/360`   |
| `list-sleep-goal`         | `list-sleep-goal`                                                                   | None                                                   | `list-sleep-goal`                                        |


### Miscellaneous

| **Command**               | **Syntax**                                                                          | **Parameters**                                         | **Examples**                                             |
|---------------------------|-------------------------------------------------------------------------------------|--------------------------------------------------------|----------------------------------------------------------|
| `find`                    | `find DATE`                                                                         | DATE                                                   | `find 2023-11-01`                                        |
| `save`                    | `save`                                                                              | None                                                   | `save`                                                   |
| `bye`                     | `bye`                                                                               | None                                                   | `bye`                                                    |
| `help`                    | `help [COMMAND]`                                                                    | [COMMAND]                                              | `help`, `help add-diet`                                  |
