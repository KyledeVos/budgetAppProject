package budgetApp.Services;

import budgetApp.Controllers.LinkedListsClass;
import budgetApp.Data.PopulateLinkedLists;
import budgetApp.Data.UpdateDataFromDataBase;

import java.util.Scanner;

public class UpdateData {

    //object that checks user input to validate for removal from database
    ValidateUserInput validateUserInput;

    //object to update Information in database
    UpdateDataFromDataBase updateDataFromDataBase;

    //object to populate list
    PopulateLinkedLists populateLinkedLists;

    //object to display data in linked lists to user
    LinkedListsClass listsClass = new LinkedListsClass();


    //id of user
    int userId;

    //constructor to initialize user id to one used in app class
    //initialize validUserInput instance to check and validate user inputs
    public UpdateData(int userId, Scanner scanner){
        this.userId = userId;
        validateUserInput = new ValidateUserInput(scanner);
        updateDataFromDataBase = new UpdateDataFromDataBase();
        populateLinkedLists = new PopulateLinkedLists(userId);
        listsClass = new LinkedListsClass();
    }

    //method to allow user to update their own information
    //userId cannot be updated or changed
    public void updateUser(Scanner scanner) {

        //information to pass to UpdateDataFromDataBase;
        String firstName;
        String lastName;
        String email;

        //display menu message
        System.out.println("-------------------------------");
        System.out.println("Update of Own User Info\n");

        //display current user info
        System.out.println("\nCurrent User Info");
        populateLinkedLists.displayTableForRowRemoval(1, listsClass);
        listsClass.printCurrentUserInfo(userId);
        System.out.println();

        //get inputs from user to update user entry in database
        System.out.println("Update Information for User\n");

        System.out.println("Please enter First name");
        firstName = scanner.nextLine();

        System.out.println("\nPlease enter Last Name");
        lastName = scanner.nextLine();

        System.out.println("\nPlease enter email address");
        email = scanner.nextLine();

        //update user info in database
        updateDataFromDataBase.updateUser(userId, firstName, lastName, email);

        System.out.println("\nUpdate Completed");

    }

    //method to allow user to update an income entry
    public void updateIncome(Scanner scanner){

        //information to pass to UpdateDataFromDataBase;
        String sourceName;
        double amount = 0;
        String paymentDate;
        String paymentInterval;
        String notes = "";

        //variable to hold user input;
        int choice = 0;

        //boolean to terminate input loop
        boolean quit = false;

        //display menu message
        System.out.println("-------------------------------");
        System.out.println("Update of User Income Entry");

        //display user's current income info
        System.out.println("\nCurrent List of All Incomes");
        populateLinkedLists.displayTableForRowRemoval(2, listsClass);
        listsClass.printIncome();
        System.out.println();

        while(!quit){
            System.out.println("Please enter id of desired row or \'0\' to return to menu");

            boolean hasInt = scanner.hasNextInt();

            if(hasInt){
                choice = scanner.nextInt();

                //allows user to cancel removal choice and go back to main menu
                if(choice == 0){
                    quit = true;
                } else {

                    // will check that user has entered an id matching one if the database matching the UserId
                    quit = updateDataFromDataBase.search(userId, choice, "income");

                    if(!quit){
                        System.out.println("Invalid Input. Please enter a valid Id matching one in the list");
                    }

                }

            }else {
                System.out.println("Invalid Input. Please enter valid numeric value of id for desired row");
            }

            //clear scanner
            scanner.nextLine();
        }

        //if user has chosen to go back to main menu
        if(choice == 0){
            return;
        }

        //get inputs from user to update income entry in database
        System.out.println("Update Information for User\n");

        System.out.println("Please enter Source of Income\n");
        sourceName = scanner.nextLine();

        amount = validateUserInput.getAmountValue("Please enter income amount. Use a \',\' for decimals");

        paymentDate = validateUserInput.createAndValidateDate();

        System.out.println("\nPlease enter Payment Interval eg) monthly, weekly, etc.");
        paymentInterval = scanner.nextLine();

        //Additional Notes
        //--------------------------
        System.out.println("\nPlease enter any additional notes to accompany this income.");
        System.out.println("If you do not wish to add any, please enter \'0\'");
        String hold = scanner.nextLine();
        if(!hold.equals("0")){
            notes = hold;
        }

        //update Income info in Database
        updateDataFromDataBase.updateIncome(userId, choice, sourceName, amount, paymentDate, paymentInterval, notes);

        System.out.println("Income has been updated");
    }


    //method to allow user to update an expense entry
    public void updateExpense(Scanner scanner){

        //information to pass to UpdateDataFromDataBase;
        String typeCategory;
        String paidTo;
        double amount = 0;
        String paymentDate;
        String description = "";
        String notes = "";

        //variable to hold user input;
        int choice = 0;

        //boolean to terminate input loop
        boolean quit = false;

        //display menu message
        System.out.println("-------------------------------");
        System.out.println("Update of User Expense Entry");

        //display user's current expenses info
        System.out.println("\nCurrent List of All Expenses");
        populateLinkedLists.displayTableForRowRemoval(3, listsClass);
        listsClass.printExpenses();
        System.out.println();

        while(!quit){
            System.out.println("Please enter id of desired row or \'0\' to return to menu");

            boolean hasInt = scanner.hasNextInt();

            if(hasInt){
                choice = scanner.nextInt();

                //allows user to cancel removal choice and go back to main menu
                if(choice == 0){
                    quit = true;
                } else {

                    // will check that user has entered an id matching one if the database matching the UserId
                    quit = updateDataFromDataBase.search(userId, choice, "expenses");

                    if(!quit){
                        System.out.println("Invalid Input. Please enter a valid Id matching one in the list");
                    }

                }

            }else {
                System.out.println("Invalid Input. Please enter valid numeric value of id for desired row");
            }

            //clear scanner
            scanner.nextLine();
        }

        //if user has chosen to go back to main menu
        if(choice == 0){
            return;
        }

        //get inputs from user to update expense entry in database
        System.out.println("Update Information for User\n");

        System.out.println("Please enter the Type or Category of Expense\n");
        typeCategory = scanner.nextLine();

        System.out.println("Please enter the person or business this has been paid to\n");
        paidTo = scanner.nextLine();

        amount = validateUserInput.getAmountValue("Please enter expense amount. Use a \',\' for decimals");

        System.out.println("\nDate of Payment");
        paymentDate = validateUserInput.createAndValidateDate();

        System.out.println("\nWould you like to enter a description of this expense.");
        System.out.println("If you do not wish to add any, please enter \'0\'");
        String hold = scanner.nextLine();
        if(!hold.equals("0")){
            description = hold;
        }


        //Additional Notes
        //--------------------------
        System.out.println("\nPlease enter any additional notes to accompany this expense.");
        System.out.println("If you do not wish to add any, please enter \'0\'");
        String hold2 = scanner.nextLine();
        if(!hold2.equals("0")){
            notes = hold2;
        }

        //update Expense info in Database
        updateDataFromDataBase.updateExpense(choice, typeCategory, paidTo, amount,
                paymentDate, description, notes);
        System.out.println("Expense has been updated");
    }


    //method to allow user to update a Debt Payment entry
    public void updateDebtPayment(Scanner scanner){

        //information to pass to UpdateDataFromDataBase;
        String typeCategory;
        String paidTo;
        double amount = 0;
        String paymentDate;
        String endDate;
        double totalOwed = 0;
        double interest = 0;
        String notes = "";

        //variable to hold user input;
        int choice = 0;

        //boolean to terminate input loop
        boolean quit = false;

        //display menu message
        System.out.println("-------------------------------");
        System.out.println("Update of User Debt Payment Entry");

        //display user's current Debt Payment info
        System.out.println("\nCurrent List of All Debt Payments");
        populateLinkedLists.displayTableForRowRemoval(4, listsClass);
        listsClass.printDebtPayments();
        System.out.println();

        while(!quit){
            System.out.println("Please enter id of desired row or \'0\' to return to menu");

            boolean hasInt = scanner.hasNextInt();

            if(hasInt){
                choice = scanner.nextInt();

                //allows user to cancel removal choice and go back to main menu
                if(choice == 0){
                    quit = true;
                } else {

                    // will check that user has entered an id matching one if the database matching the UserId
                    quit = updateDataFromDataBase.search(userId, choice, "debt_payments");

                    if(!quit){
                        System.out.println("Invalid Input. Please enter a valid Id matching one in the list");
                    }

                }

            }else {
                System.out.println("Invalid Input. Please enter valid numeric value of id for desired row");
            }

            //clear scanner
            scanner.nextLine();
        }

        //if user has chosen to go back to main menu
        if(choice == 0){
            return;
        }

        //get inputs from user to update debt payment entry in database
        System.out.println("Update Information for User\n");

        System.out.println("Please enter the Type or Category of Debt Payment\n");
        typeCategory = scanner.nextLine();

        System.out.println("Please enter the person or business this has been paid to\n");
        paidTo = scanner.nextLine();


        amount = validateUserInput.getAmountValue("Please enter Debt Payment amount. Use a \',\' for decimals");

        System.out.println("\nDate of Payment");
        paymentDate = validateUserInput.createAndValidateDate();

        System.out.println("\nExpected End Date of Payment");
        endDate = validateUserInput.createAndValidateDate();

        totalOwed = validateUserInput.getAmountValue("Please enter the remaining total owed for this Debt Payment. " +
                "Use a \',\' for decimals ");

        interest = validateUserInput.getAmountValue("Please enter the interest charged for this Debt Payment. " +
                "Use a \',\' for decimals ");

        //Additional Notes
        //--------------------------
        System.out.println("\nPlease enter any additional notes to accompany this Debt Payment.");
        System.out.println("If you do not wish to add any, please enter \'0\'");
        String hold = scanner.nextLine();
        if(!hold.equals("0")){
            notes = hold;
        }

        //update Debt Payment info in Database
        updateDataFromDataBase.updateDebtPayment(choice, typeCategory, paidTo, amount,
                paymentDate, endDate,totalOwed, interest, notes);
        System.out.println("Debt Payment has been updated");
    }


    //method to allow user to update a Savings entry
    public void updateSavings(Scanner scanner){

        //information to pass to UpdateDataFromDataBase;
        String savedLocation;
        double amount = 0;
        String savedDate;
        String notes = "";

        //variable to hold user input;
        int choice = 0;

        //boolean to terminate input loop
        boolean quit = false;

        //display menu message
        System.out.println("-------------------------------");
        System.out.println("Update of User Savings Entry");

        //display user's current savings info
        System.out.println("\nCurrent List of All Savings");
        populateLinkedLists.displayTableForRowRemoval(5, listsClass);
        listsClass.printSavings();
        System.out.println();

        while(!quit){
            System.out.println("Please enter id of desired row or \'0\' to return to menu");

            boolean hasInt = scanner.hasNextInt();

            if(hasInt){
                choice = scanner.nextInt();

                //allows user to cancel removal choice and go back to main menu
                if(choice == 0){
                    quit = true;
                } else {

                    // will check that user has entered an id matching one if the database matching the UserId
                    quit = updateDataFromDataBase.search(userId, choice, "savings");

                    if(!quit){
                        System.out.println("Invalid Input. Please enter a valid Id matching one in the list");
                    }

                }

            }else {
                System.out.println("Invalid Input. Please enter valid numeric value of id for desired row");
            }

            //clear scanner
            scanner.nextLine();
        }

        //if user has chosen to go back to main menu
        if(choice == 0){
            return;
        }

        //get inputs from user to update savings entry in database
        System.out.println("Update Information for User\n");

        System.out.println("Please enter the location of Savings eg) Bank, Piggy-Bank, etc\n");
       savedLocation = scanner.nextLine();

        amount = validateUserInput.getAmountValue("Please enter Savings amount. Use a \',\' for decimals");

        System.out.println("Please enter the date of this saving");
        savedDate = validateUserInput.createAndValidateDate();

        //Additional Notes
        //--------------------------
        System.out.println("\nPlease enter any additional notes to accompany this saving.");
        System.out.println("If you do not wish to add any, please enter \'0\'");
        String hold = scanner.nextLine();
        if(!hold.equals("0")){
            notes = hold;
        }

        //update Savings info in Database
        updateDataFromDataBase.updateSavings(choice, savedLocation, amount,
                savedDate,notes );
        System.out.println("Savings has been updated");
    }


    //method to allow user to update a Custom Goal entry
    public void updateCustomGoal(Scanner scanner){

        //information to pass to UpdateDataFromDataBase;
        String description = "default";
        String savedLocation;
        double amount = 0;
        String savedDate;
        double totalDesired = 0;
        String finalDate;
        String notes = "";

        //variable to hold user input;
        int choice = 0;

        //boolean to terminate input loop
        boolean quit = false;

        //display menu message
        System.out.println("-------------------------------");
        System.out.println("Update of User Custom Goal Entry");

        //display user's current Custom Goals info
        System.out.println("\nCurrent List of All Custom Goals");
        populateLinkedLists.displayTableForRowRemoval(6, listsClass);
        listsClass.printCustomGoals();
        System.out.println();

        while(!quit){
            System.out.println("Please enter id of desired row or \'0\' to return to menu");

            boolean hasInt = scanner.hasNextInt();

            if(hasInt){
                choice = scanner.nextInt();

                //allows user to cancel removal choice and go back to main menu
                if(choice == 0){
                    quit = true;
                } else {

                    // will check that user has entered an id matching one if the database matching the UserId
                    quit = updateDataFromDataBase.search(userId, choice, "custom_goals");

                    if(!quit){
                        System.out.println("Invalid Input. Please enter a valid Id matching one in the list");
                    }

                }

            }else {
                System.out.println("Invalid Input. Please enter valid numeric value of id for desired row");
            }

            //clear scanner
            scanner.nextLine();
        }

        //if user has chosen to go back to main menu
        if(choice == 0){
            return;
        }

        //get inputs from user to update custom goal entry in database
        System.out.println("Update Information for User\n");

        System.out.println("Please enter a Description of the Custom Goal\n");
        description = scanner.nextLine();

        System.out.println("Please enter the location of Savings eg) Bank, Piggy-Bank, etc\n");
        savedLocation = scanner.nextLine();

        amount = validateUserInput.getAmountValue("Please enter Custom Goal amount. Use a \',\' for decimals");

        System.out.println("\nDate of Savings");
        savedDate = validateUserInput.createAndValidateDate();

        totalDesired = validateUserInput.getAmountValue("Please enter the desired total for this custom goal");

        System.out.println("\nExpected Final Date");
        finalDate = validateUserInput.createAndValidateDate();

        //Additional Notes
        //--------------------------
        System.out.println("\nPlease enter any additional notes to accompany this Custom Goal.");
        System.out.println("If you do not wish to add any, please enter \'0\'");
        String hold = scanner.nextLine();
        if(!hold.equals("0")){
            notes = hold;
        }

        //update Custom Goal info in Database
        updateDataFromDataBase.updateCustomGoal(choice, description, savedLocation, amount,
                savedDate, totalDesired, finalDate, notes);
        System.out.println("Custom Goal has been updated");
    }

}
