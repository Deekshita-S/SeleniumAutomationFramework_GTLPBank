# SeleniumAutomationFramework_GTLPBank
                                                        Java Selenium Automation framework

This framework is a personal project that I’ve created to learn to build an automation framework from scratch. I have automated Login Page scenarios and ‘add new customer’ feature of GTPL Bank, a demo website to practice automation.

URL - https://demo.guru99.com/V1/index.php

Test cases automated – gtplBank_TC

About the framework

Language – Java Selenium

Type of Framework -  Data-driven Framework by using Page Object Model

POM – created separate class files for each webpage

Package – created 4 different packages, one each for pageObjects, test cases, test data (excel sheet for data driven Test cases) and utilities.
Utility package – It contains:-
a)	ReadConfig file – to read and fetch values from config.properties file
b)	XLUtils – to read the Excel file for data driven test cases and obtain all the data from it.

Configuration folder – contains config.properties file. This stores all the information that remain unchanged/ don’t change frequently, in key-value format.

Base class – contains all the common methods and attributes used by all the pages. In this project it contains xpaths of some common elements, method to instantiate and close the browser etc.

Screenshots – stores all the screenshot captured during test execution, especially used to capture screenshots of failed testcases.

TestNG – testing annotations used to control flow of the methods and for assetrions.

Maven - a build tool that is used to download dependencies and plugins and also for execution of the script using POM.xml








