package budgetApp.Controllers;

import budgetApp.Data.ValidateDataFromDataBase;
import budgetApp.Services.AddData;
import budgetApp.Services.RemoveData;
import budgetApp.Services.UpdateData;
import budgetApp.Services.ValidateUserInput;

import java.util.Scanner;

//Controller Class for Entire App
public class AppClass {

    //user_id for current user
    static int userId = 0;

    public static void main(String[] args) {

        //create scanner object used by program
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to your Custom Budget App");

        //first we check if there are any users in the database

        ValidateDataFromDataBase validateData = new ValidateDataFromDataBase();
        boolean noUsers = validateData.emptyUserTable();

        //if user table is not empty, then we want app User to select user from the database
        if(!noUsers){
            userId = validateData.validateUser(scanner);
        } else {

            //if user_table is empty then we first need the app user to add a new user to the database
            System.out.println("There are no users in App.");
            AddData addData = new AddData(scanner);

            //assign userId to id of user just added
            userId = addData.addNewUser(scanner);
        }

        //from here we begin to cycle through the menu options
        mainMenu(userId, scanner);


//        //TEST
//        System.out.println("\nTEST\n");
//        AddData addData = new AddData(scanner);
//        addData.addNewUser(scanner);
//        addData.addNewIncome(userId, scanner);
//        addData.addNewExpense(userId, scanner);


        //finally, close scanner at the end
        scanner.close();
    }

    private static void mainMenu(int userID , Scanner scanner){

        printMainMenu();

        //boolean to check if user wants to exit app
        boolean quit = false;

        //object allowing User input to be received and validated for menu options
        ValidateUserInput validateUserInput = new ValidateUserInput(scanner);

        //create infinite loop to cycle through menu options until user selects 'exit'

        while(!quit){

            int choice = validateUserInput.getMenuChoice(5);
            quit = mainMenuChoice(choice, scanner);
            if(!quit){
                printMainMenu();
            }

        }

    }

    //---------------------------------------------------------------------
    //Main Menu

    private static void printMainMenu(){
        System.out.println("----------------------------------------");
        System.out.println("MAIN MENU");
        System.out.println("\nPlease select a menu option below by entering the number next to your choice\n");
        System.out.println("1) View Account Balances");
        System.out.println("2) View Overview Stats");
        System.out.println("3) Customize Entries in Budget - Add, Remove or Update");
        System.out.println("4) Run Reports");
        System.out.println("5) Exit App");
        System.out.println("----------------------------------------");
    }

    private static boolean mainMenuChoice(int option, Scanner scanner){

        //used to return if user has chosen to exit app
        boolean quit = false;

        switch(option){
            case 1:
                System.out.println("Account Balances in Development");
                break;

            case 2:
                System.out.println("Overview Stats in Development");
                break;

            case 3:
                quit = customizeEntriesMenu(scanner);
                break;

            case 4:
                System.out.println("Run Reports in development");
                break;

            case 5:
                System.out.println("Thank you for using the Java Budget App!");
                quit = true;
                break;

            default:
                System.out.println("Invalid Option Entered");
                break;
        }

        return quit;

    }

    /////////////////////////////////////////////////////////////
    //CustomizeEntries Menu

    //print menu options
    private static void printCustomizeEntriesMenu(){

        System.out.println("\nPlease select a menu option below by entering the number next to your choice\n");
        System.out.println("1) Add new Entry ");
        System.out.println("2) Remove an Entry");
        System.out.println("3) Update/Change a current Entry");
    }

    private static boolean customizeEntriesMenu(Scanner scanner){

        System.out.println("\n---------------------------");
        System.out.println("Customize Entries Menu");
        printCustomizeEntriesMenu();

        ValidateUserInput validateUserInput = new ValidateUserInput(scanner);

        //variable to hold user choice
        int choice = validateUserInput.getMenuChoice(3);
        return customizeEntryChoice(choice, scanner);


    }

    private static boolean customizeEntryChoice(int choice, Scanner scanner){

        switch(choice){

            case 1:
                System.out.println("\n------------------------");
                System.out.println("Add new Entry Menu");
                addNewEntry(scanner);
                break;

            case 2:
                System.out.println("\n------------------------");
                System.out.println("Remove Entry Menu");
                return removeEntry(scanner);

            case 3:
                System.out.println("\n------------------------");
                System.out.println("Update Current Entry Menu");
                updateEntry(scanner);
                break;

            default:
                System.out.println("Invalid");
                break;
        }

        return false;
    }

    ///////////////////////////////////////////////
    //Customize Entry Menu - Add New Entry

    //method to print options of which type of data to add to database
    public static void printAddEntryMenu(){
        System.out.println("\nPlease select a menu option below by entering the number next to your choice\n");
        System.out.println("1) Add new User ");
        System.out.println("2) Add new Income ");
        System.out.println("3) Add new Expense ");
        System.out.println("4) Add new Debt Payment ");
        System.out.println("5) Add new Savings ");
        System.out.println("6) Add new Custom Goal - Savings ");
    }

    //controlling method to print menu to user and add data
    public static void addNewEntry(Scanner scanner){
        System.out.println("\n------------------------");
        printAddEntryMenu();

        ValidateUserInput validateUserInput = new ValidateUserInput(scanner);

        //variable to hold user choice
        int choice = validateUserInput.getMenuChoice(6);
        addNewEntryChoice(choice, scanner);

    }

    //method to accept users choice
    public static void addNewEntryChoice(int choice, Scanner scanner){

        AddData addData = new AddData(scanner);

        switch(choice){
            case 1:
                userId = addData.addNewUser(scanner, userId);
                break;

            case 2:
                addData.addNewIncome(userId, scanner);
                break;

            case 3:
                addData.addNewExpense(userId, scanner);
                break;

            case 4:
                addData.addNewDebtExpense(userId, scanner);
                break;

            case 5:
                addData.addNewSavings(userId, scanner);
                break;

            case 6:
                addData.addNewCustomGoal(userId, scanner);
                break;
        }

    }

    ///////////////////////////////////////////////
    //Customize Entry Menu - Remove Entry

    public static void printRemoveEntryMenu(){
        System.out.println("\nPlease select a menu option below by entering the number next to your choice\n");
        System.out.println("1) Remove a User ");
        System.out.println("2) Remove Income ");
        System.out.println("3) Remove Expense ");
        System.out.println("4) Remove Debt Payment ");
        System.out.println("5) Remove Savings ");
        System.out.println("6) Remove Custom Goal - Savings ");
    }

    public static boolean removeEntry(Scanner scanner){

        System.out.println("\n------------------------");
        printRemoveEntryMenu();

        ValidateUserInput validateUserInput = new ValidateUserInput(scanner);

        //variable to hold user choice
        int choice = validateUserInput.getMenuChoice(6);
        return removeEntryChoice(choice, scanner);

    }

    public static boolean removeEntryChoice(int choice, Scanner scanner){

        RemoveData removeData = new RemoveData(scanner);

        switch(choice){

            case 1:
                return removeData.removeUser(userId, scanner);

            case 2:
                removeData.removeIncome(userId, scanner);
                break;

            case 3:
                removeData.removeExpense(userId, scanner);
                break;

            case 4:
                removeData.removeDebtPayment(userId, scanner);
                break;

            case 5:
                removeData.removeSavings(userId, scanner);
                break;

            case 6:
                removeData.removeCustomGoal(userId, scanner);
                break;

            default:
                System.out.println("Invalid");
                break;

        }

        return false;

    }

    ///////////////////////////////////////////////
    //Customize Entry Menu - Update Entry

    //print menu options to user for Update Options
    public static void printUpdateMenu(){

        System.out.println("\nPlease select a menu option below to Update/Change an Entry" +
                " by entering the number next to your choice\n");
        System.out.println("1) Current User Information");
        System.out.println("2) Income Entry");
        System.out.println("3) Expense Entry");
        System.out.println("4) Debt Expense Entry");
        System.out.println("5) Savings Entry");
        System.out.println("6) Custom Goal Entry");

    }

    //method to print menu to user and accept chosen menu option
    public static void updateEntry(Scanner scanner){

        System.out.println("\n------------------------");
        printUpdateMenu();


        //object to check user has entered valid, int input withing menu option range
        ValidateUserInput validateUserInput = new ValidateUserInput(scanner);

        //variable to hold user choice
        int choice = validateUserInput.getMenuChoice(6);
        updateEntryChoice(choice, scanner);

    }

    public static void updateEntryChoice(int choice, Scanner scanner){

        //object to call methods to update corresponding data in database
        UpdateData updateData = new UpdateData(userId, scanner);

        switch(choice){
            case 1:
                updateData.updateUser(scanner);
                break;

            case 2:
                updateData.updateIncome(scanner);
                break;

            case 3:
                updateData.updateExpense(scanner);
                break;

            case 4:
                updateData.updateDebtPayment(scanner);
                break;

            case 5:
                updateData.updateSavings(scanner);
                break;

            case 6:
                updateData.updateCustomGoal(scanner);
                break;

            default:
                System.out.println("Invalid");
                break;
        }

    }

}
