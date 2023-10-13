# AthletiCLI User Guide

**AthletiCLI** is your all-in-one solution to track, analyse, and optimize your athletic performance. Designed for the 
committed athlete, this command-line interface (CLI) tool not only keeps tabs on your physical activities but also covers dietary habits, sleep metrics, and more.

## Quick Start
+ Ensure you have the required runtime environment installed on your computer. 
+ Download the latest AthletiCLI from the official repository. 
+ Copy the downloaded file to a folder you want to designate as the home for AthletiCLI. 
+ Open a command terminal, cd into the folder where you copied the file, and run java -jar AthletiCLI.jar

## Features
**Notes about Command Format**
+ Words in UPPER_CASE are parameters provided by the user.
+ Parameters can be in any order.
+ Parameters enclosed in square brackets [] are optional.

## Activity Management
# Adding Activities:
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


Useful links:
[User Guide](UserGuide.md)
[Developer Guide](DeveloperGuide.md)
[About Us](AboutUs.md)
