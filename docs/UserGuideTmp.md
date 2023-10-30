---
layout: page
title: User Guide
---

## Quick Start

* Ensure you have the required runtime environment installed on your computer.
* Download the latest AthletiCLI from the official repository.
* Copy the downloaded file to a folder you want to designate as the home for AthletiCLI.
* Open a command terminal, cd into the folder where you copied the file, and run java -jar AthletiCLI.jar

## Features

**Notes about Command Format**

* Words in UPPER_CASE are parameters provided by the user.
* Parameters can be in any order.
* Parameters enclosed in square brackets [] are optional.

## Activity Management

### Adding Activities:

`add-activity`, `add-run`, `add-swim`, `add-cycle`

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
* DATETIME: The date and time of the start of the activity. It must follow the ISO Date Time Format: YYYY-MM-DD HH:MM

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

* `delete-activity 2` deletes the second activity in the activity list.
* `delete-activity 1` deletes the first activity in the activity list.

### Listing Activities:

`list-activity`

You can see all your tracked activities in a list by using this command. For more detailed information, you can use
the detailed flag.

**Syntax:**

* `list-activity [-d]`

**Flags:**

* `-d`: Shows a detailed list of activities.

**Examples:**

* `list-activity` shows a brief overview of all activities.
* `list-activity -d` shows a detailed summary of all activities.

### Editing Activities:

`edit-activity`, `edit-run`, `edit-swim`, `edit-cycle`

You can edit your activities in AthletiCLI by editing the activity at the specified index.

**Syntax:**

* `edit-activity INDEX CAPTION duration/DURATION distance/DISTANCE datetime/DATETIME`
* `edit-run INDEX CAPTION duration/DURATION distance/DISTANCE datetime/DATETIME elevation/ELEVATION`
* `edit-swim INDEX CAPTION duration/DURATION distance/DISTANCE datetime/DATETIME laps/LAPS`
* `edit-cycle INDEX CAPTION duration/DURATION distance/DISTANCE datetime/DATETIME elevation/ELEVATION`

**Parameters:**

* INDEX: The index of the activity to be edited - must be a positive number
* see adding activities for the other parameters

**Examples:**

* `edit-activity 1 Morning Run duration/60 distance/10000 datetime/2021-09-01 06:00`
* `edit-cycle 2 Evening Ride duration/120 distance/20000 datetime/2021-09-01 18:00 elevation/1000`


## Diet Goal Management


### Adding Diet Goals:


`set-diet-goal`
You can create a new diet goal to track your nutrients intake with AtheltiCLI by adding the nutrients you wish to track and the target value for your nutrient goals.


Currently only the following nutrients/metrics are tracked:
1. Calories
2. Protein
3. Carbs
4. Fats


You can set multiple nutrients goals at once with the `set-diet-goal` command.


**Syntax:**


* `set-diet-goal calories/CALORIES protein/PROTEIN carb/CARBS fat/FAT`


**Parameters:**


* CALORIES: Your target value for calories intake, in terms of cal.
* PROTEIN: The target for protein intake, in terms of milligrams.
* CARB: Your target value for carbohydrate intake, in terms of milligrams.
* FAT: Your target value for fats intake, in terms of milligrams.


You can create one or multiple nutrient goals at once with this command.




**Examples:**

Create multiple nutrients goals:
* `set-diet-goal calories/500 protein/20 carb/50 fat/10`


Create a single calories goal:
* `set-diet-goal calories/500`


### Deleting Diet Goals:


`delete-diet-goal`
You can delete your diet goals in AtheltiCLI by deleting the goal at the specified index.
This index will be referenced via `list-diet-goal` command.


**Syntax:**


* `delete-diet-goal INDEX`


**Parameters:**


* INDEX: The index of the diet goal to be deleted. It must be a positive integer.


**Examples:**


* `delete-diet-goal 1`


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


This command takes in at least one argument. You are able to edit multiple diet goals target value at once. No repetition is allowed.


**Syntax:**


* `edit-diet-goal calories/CALORIES protein/PROTEIN carb/CARBS fat/FAT`


**Parameters:**


* CALORIES: Your target value for calories intake, in terms of cal.
* PROTEIN: The target for protein intake, in terms of milligrams.
* CARBS: Your target value for carbohydrate intake, in terms of milligrams.
* FAT: Your target value for fats intake, in terms of milligrams.


You can create one or multiple nutrient goals with this command.


**Examples:**


Edit multiple nutrients goals:
* `edit-diet-goal calories/5000 protein/200 carb/500 fat/100`


Edit a single calories goal:
* `edit-diet-goal calories/5000`

## Sleep Management

### Adding Sleep:

**Command:** `add-sleep`  
You can record your sleep timings in AtheltiCLI by adding your sleep start and end time.

**Syntax:**

* `add-sleep start/START end/END`

**Parameters:**

* START: The start time of the sleep in the following Date Time Format: DD-MM-YYYY HH:MM
* END: The end time of the sleep in the following Date Time Format: DD-MM-YYYY HH:MM

**Examples:**

* `add-sleep start/01-09-2021 22:00 end/02-09-2021 06:00`

### Listing Sleep:

**Command:** `list-sleep`
You can list all your sleep records in AtheltiCLI.

**Syntax:** `list-sleep`

**Examples:** `list-sleep`

### Deleting Sleep:

**Command:** `delete-sleep`  
You can delete your sleep in AtheltiCLI by specifying the sleep's index.

**Syntax:**

* `delete-sleep INDEX`

**Parameters:**

* INDEX: The integer index of the sleep record you wish to delete.

**Examples:**

* `delete-sleep 5`  
  (Note: This will delete the 5th sleep record from your records.)

### Editing Sleep:

**Command:** `edit-sleep`  
You can modify existing sleep records in AtheltiCLI by specifying the sleep's index and then providing the new start and
end times.

**Syntax:**

* `edit-sleep INDEX start/START end/END`

**Parameters:**

* INDEX: The integer index of the sleep record you wish to edit.
* START: The new start time of the sleep in the following Date Time Format: DD-MM-YYYY HH:MM
* END: The new end time of the sleep in the following Date Time Format: DD-MM-YYYY HH:MM

**Examples:**

* `edit-sleep 5 start/05-09-2021 23:00 end/06-09-2021 07:00`  
  (Note: This will edit the 5th sleep record to have the new specified timings.)

---

Remember, when using AtheltiCLI:

* Make sure to provide accurate dates and times.
* Double-check indexes before deleting or editing records to prevent mistakes.
* If you encounter any error messages, read them carefully to understand what went wrong.
