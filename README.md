# budgetAppProject
Coding Nomads - Final Java Project

This is a back-end Budget App Project allowing a user to add, change, remove and view aspects of their personal budget including overviews and report printing. 

## Background

I wanted to create a project allowing a user to add to and track their personal finances. In addition I wanted the user to see account summary overviews and run reports 
on their finances. Use of Java LinkedLists and a SQL Database are incorporated. The idea is to populate the LinkedLists with data from the database when overviews and 
reports are selected. The program will analyse the data in the LinkedLists 

## Features
* Track and allow Multiple Budget Aspects
 * - Multiple Users
 * - Income
 * - Various Expenses - Debt Payments and General Expenses
 * - Savings - General Savings and Custom Goals


* Show Overview Account and Budget Information
*  - Show user current Account Summary information
*  - Allow user to see upcoming debt payment information
*  - Display progress of custom savings goals
*  - savings tool allowing user to see how much they could ass to their savings with effect on account balance

* Run Reports
* - allow user to select a debt payment and see time it would take to pay it off
* - Monthly break-down of total savings vs spending 
* - debt urgency report showing which debt payment has the highest interest rate and which has the highest amount owing

## Installation

Run budgetAppDatabase.sql to create tables required for program located in src folder

Program requires an IDE running java with main file located in AppClass in Controllers package

Before running main, all classes in Database must be given a user name and password for connection to local host in Connection String

Classes to add user name and password:
* AccountSummaryControl
* AddDataToDataBase
* PopulateLinkedLists
* RemoveDataFromDataBase
* UpdateDataFromDataBase
* ValidateDataFromDataBase

## Usage

Once running, the program will first check if there are any users in the database. If none are found it will default to ask for addition information for a new user.
If there is already a user(s) in the database, the program will request the name of a user to use (and validate by comparing name to names of user's in the database.

Once user has been added / selected, the main menu option will display allowing the user to select main and submenu options to 

1) View Account Blances
 * Shows User's Account Summary 
 
2) View Overview Info

  i) Show Upcoming Debt Payments
  
  ii) Show Progress towards completion of a Custom Savings Goal
  
  iii) Savings tool
  * Allows user to specify a percentage of their account balance that they may wish to add to savings
    
3) Customize Entries in Budget
  
  i) Add New Entry
  * Allows user to add an entry to User, Income, Expenses, Debt Payments, Savings and Custom Goals Tables
      
  ii) Remove Entry
  * Allows User to remove entry from tables listed above
  * NOTE: A user can only remove their own data from the database. Doing this would delete the user, all the data and terminate the program
      
  iii) Update Current Entry
  * Allows user to modify data in any of the tables listed above
  * NOTE: User can only change their user data and not that of any other user
 
 4) Run Reports
 
 i) DebtPaymentTime
  * Allows user to choose one of their debt payments (given in a list for user to see) and then see how long it would take to pay the debt off at the current amount 
    they are paying, if the user paid 20% more and if the user paid 20% less
  * NOTE: Calculation is done with the Present Value Annuity formula using interest compounded monthly
    
 ii) Savings vs Spending
  * Prints a month by month comparison of total spending (expenses and debt payments) and total savings (savings and custom goals)
    
 iii) Debt Urgency Report
 * Shows user which debt payment has the highest interest rate an which has the highest amount still owed

## RoadMap

Future development to this program would first focus on:

* Change to Debt Payment and Custom Goals Tables allowing user to select a current Debt Payment and Custom Goal and contribute an amount towards it
  instead of re-entering all the information again as the program currently requires
* Possible addition of passwords for each user to further authenticate a correct user to the program
* Potential use of hash maps instead of Linked Lists used to store data to make retrieval faster for larger data sets
* Addition of Threads to display "Loading ... " output to user for retrieval and calculations using very large data sets

From there development would look at cleaning up the code (such as proper use of Composition in classes) and then the addition of more options for the user
such as more types of reports a user could run
    
    


