package budgetApp.Controllers;

import budgetApp.Data.ValidateData;
import budgetApp.Services.AddData;
import budgetApp.Services.ValidateUserInput;

import java.util.Scanner;

//Controller Class for Entire App
public class AppClass {



    public static void main(String[] args) {

        //create scanner object used by program
        Scanner scanner = new Scanner(System.in);

        //user_id for current user
        int userId = 0;

        System.out.println("Welcome to your Custom Budget App");

        //first we check if there are any users in the database

        ValidateData validateData = new ValidateData();
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
                customizeEntriesMenu(scanner);
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
        customizeEntryChoice(choice);

        //must return true to allow while loop to continue in main menu
        return true;
    }

    private static void customizeEntryChoice(int choice){


        switch(choice){

            case 1:
                System.out.println("\n------------------------");
                System.out.println("Add new Entry Menu");
                System.out.println("Additional Menu Options In development");
                break;

            case 2:
                System.out.println("\n------------------------");
                System.out.println("Remove Entry Menu");
                System.out.println("Additional Menu Options In development");
                break;

            case 3:
                System.out.println("\n------------------------");
                System.out.println("Update Current Entry Menu");
                System.out.println("Additional Menu Options In development");
                break;

            default:
                System.out.println("Invalid");
                break;
        }

    }



}
