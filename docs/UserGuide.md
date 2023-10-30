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