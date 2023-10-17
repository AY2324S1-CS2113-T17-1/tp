# AthletiCLI User Guide

**AthletiCLI** is your all-in-one solution to track, analyse, and optimize your athletic performance. Designed for the
committed athlete, this command-line interface (CLI) tool not only keeps tabs on your physical activities but also
covers dietary habits, sleep metrics, and more.

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

`activity`, `run`, `swim`, `cycle`

You can record your activities in AtheltiCLI by adding different activities including running, cycling, and swimming.

**Syntax:**

* `activity CAPTION duration/DURATION distance/DISTANCE datetime/DATETIME`
* `run CAPTION duration/DURATION distance/DISTANCE datetime/DATETIME elevation/ELEVATION`
* `swim CAPTION duration/DURATION distance/DISTANCE datetime/DATETIME laps/LAPS`
* `cycle CAPTION duration/DURATION distance/DISTANCE datetime/DATETIME elevation/ELEVATION`

**Parameters:**

* CAPTION: A short description of the activity.
* DURATION: The duration of the activity in minutes.
* DISTANCE: The distance of the activity in meters.
* DATETIME: The date and time of the start of the activity. It must follow the ISO Date Time Format: YYYY-MM-DD HH:MM

**Examples:**

* `activity Morning Run duration/60 distance/10000 datetime/2021-09-01 06:00`
* `cycle Evening Ride duration/120 distance/20000 datetime/2021-09-01 18:00 elevation/1000`

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
* `list-activity` shows a brief overview  of all activities.
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

## Diet Management

### Adding Diets:

`diet`
You can record your diet in AtheltiCLI by adding your calorie, protein, carbohydrate,and fat intake of your meals.

**Syntax:**

* `diet calories/CALORIES protein/PROTEIN carbs/CARBS fat/FAT`

**Parameters:**

* CALORIES: The total calories of the meal.
* PROTEIN: The total protein of the meal.
* CARBS: The total carbohydrates of the meal.
* FAT: The total fat of the meal.

**Examples:**

* `diet calories/500 protein/20 carbs/50 fat/10`

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

Useful links:
[User Guide](UserGuide.md)
[Developer Guide](DeveloperGuide.md)
[About Us](AboutUs.md)
