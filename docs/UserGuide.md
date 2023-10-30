---
layout: page
title: User Guide
---

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
* DATETIME: The date and time of the meal. It must follow the ISO Date Time Format: YYYY-MM-DD HH:MM

**Examples:**

* `add-diet calories/500 protein/20 carb/50 fat/10 datetime/2021-09-01 06:00`

### Editing Diets:

`edit-diet`
You can edit your diet in AtheltiCLI by editing the diet at the specified index.

**Syntax:**

* `edit-diet INDEX calories/CALORIES protein/PROTEIN carb/CARB fat/FAT datetime/DATETIME`

**Parameters:**

* INDEX: The index of the diet to be edited - must be a positive integer.
* CALORIES: The total calories of the meal. [OPTIONAL]
* PROTEIN: The total protein of the meal. [OPTIONAL]
* CARB: The total carbohydrates of the meal. [OPTIONAL]
* FAT: The total fat of the meal. [OPTIONAL]
* DATETIME: The date and time of the meal. It must follow the ISO Date Time Format: YYYY-MM-DD HH:MM [OPTIONAL]

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



## Diet Goal Management


### Adding Diet Goals:


`set-diet-goal`
You can create a new daily or weekly diet goal to track your nutrients intake with AtheltiCLI by adding the nutrients you wish to track and the target value for your nutrient goals.


Currently only the following nutrients/metrics are tracked:
1. Calories
2. Protein
3. Carbs
4. Fats


You can set multiple nutrients goals at once with the `set-diet-goal` command.


**Syntax:**


* `set-diet-goal <DAILY/WEEKLY> calories/CALORIES protein/PROTEIN carb/CARBS fat/FAT`


**Parameters:**

* DAILY/WEEKLY: Determines if the goal is set for a day or set for the week. It accepts 2 values.
DAILY goals account for what you eat for the day.
WEEKLY goals account for what you eat for the week.
* CALORIES: Your target value for calories intake, in terms of cal.
* PROTEIN: The target for protein intake, in terms of milligrams.
* CARB: Your target value for carbohydrate intake, in terms of milligrams.
* FAT: Your target value for fats intake, in terms of milligrams.


You can create one or multiple nutrient goals at once with this command.




**Examples:**

Create multiple nutrients goals:
* `set-diet-goal WEEKLY calories/500 protein/20 carb/50 fat/10`


Create a single calories goal:
* `set-diet-goal DAILY calories/500`


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


This command takes in at least 2 arguments. You are able to edit multiple diet goals target value of the same time frame at once. No repetition is allowed.


**Syntax:**


* `edit-diet-goal <DAILIY/WEEKLY> calories/CALORIES protein/PROTEIN carb/CARBS fat/FAT`


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


Edit multiple nutrients goals if all of them exists:
* `edit-diet-goal DAILY calories/5000 protein/200 carb/500 fat/100`


Edit a single calories goal if the goal exists:
* `edit-diet-goal WEEKLY calories/5000`
