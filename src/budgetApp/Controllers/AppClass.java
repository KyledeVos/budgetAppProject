package budgetApp.Controllers;

import budgetApp.Data.ValidateData;
import budgetApp.Services.AddData;

import java.util.Scanner;

//Controller Class for Entire App
public class AppClass {

    public static void main(String[] args) {

        //create scanner object used by program
        Scanner scanner = new Scanner(System.in);

        //boolean to check if user wants to exit app
        boolean quit = false;

        //user_id for current user
        int userId = 0;

        System.out.println("Welcome to your Custom Budget App");

        //first we check if there are any users in the database

        //testing
        ValidateData validateData = new ValidateData();

        boolean noUsers = validateData.emptyUserTable();

        //if user table is not empty, then we want app User to select user from the database
        if(!noUsers){
            userId = validateData.validateUser(scanner);
            printMainMenu();
        } else {

            //if user_table is empty then we first need the app user to add a new user to the database
            System.out.println("There are no users in App.");
            AddData addData = new AddData();

            //assign userId to id of user just added
            userId = addData.addNewUser(scanner);
        }



        //create infinite loop to cycle through menu options until user selects 'exit'
        while(!quit){


            boolean hasInt = scanner.hasNextInt();

            //if user enters an int value, menuChoiceMethod() Evaluated
            if(hasInt){
                int menuChoice = scanner.nextInt();
                quit = mainMenuChoice(menuChoice);
            } else {
                //occurs if user did not enter an integer
                System.out.println("Invalid input. Please enter valid number between 1 and 5\n");
                //printMainMenu();
            }

            //clear scanner input
            scanner.nextLine();

        }

        //finally, close scanner at the end
        scanner.close();


    }

    private static void printMainMenu(){
        System.out.println("----------------------------------------");
        System.out.println("\nPlease select a menu option below by entering the number next to your choice\n");
        System.out.println("1) View Account Balances");
        System.out.println("2) View Overview Stats");
        System.out.println("3) Customize Entries in Budget - Add, Remove or Update");
        System.out.println("4) Run Reports");
        System.out.println("5) Exit App");
    }

    private static boolean mainMenuChoice(int option){

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
                System.out.println("Customize entries in development");
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

}
