---
layout: page
title: User Guide
---

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
* Parameters can be in any order.
* Parameters enclosed in square brackets [] are optional.

## Activity Management

### Adding Activities:

`add-activity`

`add-run`

`add-swim`

`add-cycle`

You can record your activities in AtheltiCLI by adding different activities including running, cycling, and swimming.

**Syntax:**

* `add-activity CAPTION duration/DURATION distance/DISTANCE datetime/DATETIME`
* `add-run CAPTION duration/DURATION distance/DISTANCE datetime/DATETIME elevation/ELEVATION`
* `add-swim CAPTION duration/DURATION distance/DISTANCE datetime/DATETIME laps/LAPS`
* `add-cycle CAPTION duration/DURATION distance/DISTANCE datetime/DATETIME elevation/ELEVATION`

**Parameters:**

* CAPTION: A short description of the activity.
* DURATION: The duration of the activity in minutes.
* DISTANCE: The distance of the activity in meters. It must be a positive number.
* DATETIME: The date and time of the start of the activity. It must follow the ISO Date Time Format: yyyy-MM-dd HH:mm.

**Examples:**

* `add-activity Morning Run duration/60 distance/10000 datetime/2021-09-01 06:00`
* `add-cycle Evening Ride duration/120 distance/20000 datetime/2021-09-01 18:00 elevation/1000`

### Deleting Activities:

`delete-activity`

Accidentally added an activity? You can quickly delete activities by using the following command.
The index must be a positive number and is not larger than the number of activities recorded.

**Syntax:**

* `delete-activity INDEX`

**Parameters:**

* INDEX: The index of the activity as shown in the displayed activity list.

**Examples:**

* `delete-activity 2` Deletes the second activity in the activity list.
* `delete-activity 1` Deletes the first activity in the activity list.

### Listing Activities:

`list-activity`

You can see all your tracked activities in a list by using this command. For more detailed information, you can use
the detailed flag.

**Syntax:**

* `list-activity [-d]`

**Parameters:**

* `-d`: Shows a detailed list of activities.

**Examples:**

* `list-activity` Shows a brief overview of all activities.
* `list-activity -d` Shows a detailed summary of all activities.

### Editing Activities:

`edit-activity`

`edit-run`

`edit-swim`

`edit-cycle`

You can edit your activities in AthletiCLI by editing the activity at the specified index.

**Syntax:**

* `edit-activity INDEX CAPTION duration/DURATION distance/DISTANCE datetime/DATETIME`
* `edit-run INDEX CAPTION duration/DURATION distance/DISTANCE datetime/DATETIME elevation/ELEVATION`
* `edit-swim INDEX CAPTION duration/DURATION distance/DISTANCE datetime/DATETIME laps/LAPS`
* `edit-cycle INDEX CAPTION duration/DURATION distance/DISTANCE datetime/DATETIME elevation/ELEVATION`

**Parameters:**

* INDEX: The index of the activity to be edited - must be a positive number.
* See [adding activities](#adding-activities) for the other parameters.

**Examples:**

* `edit-activity 1 Morning Run duration/60 distance/10000 datetime/2021-09-01 06:00`
* `edit-cycle 2 Evening Ride duration/120 distance/20000 datetime/2021-09-01 18:00 elevation/1000`

### Setting Activity Goals:

`set-activity-goal`

You can set goals for your activities in AthletiCLI by setting the target distance or duration for a specific sport.

**Syntax**

* `set-activity-goal sport/SPORT target/TARGET period/PERIOD value/VALUE`

**Parameters**

* SPORT: The sport for which you want to set a goal. It must be one of the following: run, swim, cycle, general.
* TARGET: The target for which you want to set a goal. It must be one of the following: distance, duration.
* VALUE: The value of the target. It must be a positive number. For distance, it is in meters. For duration, it is in minutes.

**Examples**

* `set-activity-goal sport/running type/distance period/weekly target/10000` Sets a goal of running 10km per week.
* `set-activity-goal sport/swimming type/duration period/monthly target/120` Sets a goal of swimming for 2 hours per
  month.

### Editing Activity Goals:

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

### Listing Activity Goals:

`list-activity-goal`

You can list all your goals in AthletiCLI and see your progress towards them.

**Syntax**

* `list-activity-goal`

**Examples**

* `list-activity-goal` Lists all your goals.

## Diet Management

### Adding Diets:

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

### Editing Diets:

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

### Deleting Diets:

`delete-diet`

You can delete your diet in AtheltiCLI by deleting the diet at the specified index.

**Syntax:**

* `delete-diet INDEX`

**Parameters:**

* INDEX: The index of the diet to be deleted - must be a positive integer.

**Examples:**

* `delete-diet 1`

### Listing Diets:

`list-diet`

You can list all your diets in AtheltiCLI.

**Syntax:**

* `list-diet`

**Examples:**

* `list-diet`

### Finding Diets:

`find-diet date/DATE`

You can find all your diets on a specific date in AtheltiCLI.

**Syntax:**

* `find-diet date/DATE`

**Parameters:**

* DATE: The date of the diet. It must follow the ISO Date Format: yyyy-MM-dd.

**Examples:**

* `find-diet date/2021-09-01`

## Diet Goal Management

### Adding Diet Goals:

`set-diet-goal [calories/CALORIES] [protein/PROTEIN] [carb/CARB] [fat/FAT]`

You can create a new daily or weekly diet goal to track your nutrients intake with AtheltiCLI by adding the nutrients you wish to track and the target value for your nutrient goals.

You can set multiple nutrients goals at once with the `set-diet-goal` command.

**Parameters:**

* CALORIES: Your calories target value in calories.
* PROTEIN: Your protein target value in milligrams.
* CARB: Your carbohydrates target value in milligrams.
* FAT: Your fats target value in milligrams.

`Note: At least one of the parameters must be present!`

**Syntax:**

* `set-diet-goal <DAILY/WEEKLY> [calories/CALORIES] [protein/PROTEIN] [carb/CARBS] [fat/FAT]`

**Parameters:**

* DAILY/WEEKLY: Determines if the goal is set for a day or set for the week. It accepts 2 values.
  DAILY goals account for what you eat for the day.
  WEEKLY goals account for what you eat for the week.
* CALORIES: Your target value for calories intake, in terms of calories.
* PROTEIN: Your target for protein intake, in terms of milligrams.
* CARB: Your target value for carbohydrate intake, in terms of milligrams.
* FAT: Your target value for fats intake, in terms of milligrams.

You can create one or multiple nutrient goals at once with this command.

**Examples:**

* `set-diet-goal WEEKLY calories/500 fats/600` Creates multiple 2 nutrient goals: calories and fats.

* `set-diet-goal DAILY calories/500` Creates a single calories goal.

### Deleting Diet Goals:

`delete-diet-goal`

You can delete your diet goals in AtheltiCLI by deleting the goal at the specified index.
This index will be referenced via `list-diet-goal` command.

**Syntax:**

* `delete-diet-goal INDEX`

**Parameters:**

* INDEX: The index of the diet goal to be deleted. It must be a positive integer.

**Examples:**

* `delete-diet-goal 1` Deletes a diet goal that is located on the first index of the list.

### Listing Diet Goals:

`list-diet-goals`

You can list all your diet goals in AtheltiCLI.

**Syntax:**

* `list-diet-goal`

**Examples:**

* `list-diet-goal`

### Editing Diet Goals:

`edit-diet-goal`

You can edit the target value of your diet goals in AtheltiCLI, redefining the target value for the specified nutrient.

This command takes in at least 2 arguments. You are able to edit multiple diet goals target value of the same time frame at once. No repetition is allowed.

**Syntax:**

* `edit-diet-goal <DAILIY/WEEKLY> [calories/CALORIES] [protein/PROTEIN] [carb/CARBS] [fat/FAT]`

**Parameters:**

* DAILY/WEEKLY: This determines if the goal you want to edit is a daily goal or a weekly goal. It accepts 2 values.
  DAILY goals account for what you eat for the day.
  WEEKLY goals account for what you eat for the week.
* CALORIES: Your target value for calories intake, in terms of cal.
* PROTEIN: The target for protein intake, in terms of milligrams.
* CARBS: Your target value for carbohydrate intake, in terms of milligrams.
* FAT: Your target value for fats intake, in terms of milligrams.

You can create one or multiple nutrient goals with this command.

**Examples:**

* `edit-diet-goal DAILY calories/5000 protein/200 carb/500 fat/100` Edits multiple nutrients goals if all of them exists.
* `edit-diet-goal WEEKLY calories/5000` Edits a single calories goal if the goal exists.

## Miscellaneous

### Finding Records:

You can find all your records, including activities, sleeps, and diets, on a specific date in AtheltiCLI.

**Syntax:**

* `find DATE`

**Parameters:**

* `DATE`: The date of the records. It must follow the ISO Date Format: `yyyy-MM-dd`.

**Example:**

* `find 2023-11-01`

### Saving Files:

You can save files while using AthletiCLI if you want to, rather than waiting until the AthletiCLI exits to automatically save them.

**Syntax:**

* `save`

### Exiting AthletiCLI:

You can use the `bye` command at any time to safely store the file and exit AthletiCLI.

**Syntax:**

* `bye`

### Viewing Help Messages:

If you forget a command, you can always use the `help` command to see their syntax.

**Syntax:**

* `help [COMMAND]`

**Parameters:**

* `COMMAND`: The command you want to view. If it is omitted, a list containing the syntax of all commands will be shown.

**Examples:**

* `help` lists the syntax of all commands.
* `help add-diet` shows the syntax of the `add-diet` command.
