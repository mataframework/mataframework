# MATA Framework - Mobile Application Test Automation Framework

All test code is located in the src/test/kotlin folder:

* app - classes for connecting to the application, common to all test cases:
    * Configuration - contains launch parameters: platform (Android, iOS).
    * AppLauncher - the main class, responsible for launching the application with various parameters.
    * App - the launched application class. Contains the driver.
    * AndroidDriver, IosDriver - driver settings for platforms (Android, iOS).
* pages - Page Object model classes
* uitests - E2E UI tests classes
* utils - Utility classes