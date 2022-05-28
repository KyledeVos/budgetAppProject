package budgetApp.Services;

import budgetApp.Controllers.LinkedListsClass;
import budgetApp.Data.PopulateLinkedLists;
import budgetApp.Data.RemoveDataFromDatabase;

import java.util.Scanner;

public class RemoveData {

    //object that checks user input to validate for removal from database
    ValidateUserInput validateUserInput;

    //id of user
    int userId;

    public RemoveData(Scanner scanner){
        validateUserInput = new ValidateUserInput(scanner);
    }

    //option to remove new User From Database

    public void removeUser(Scanner scanner){


    }

    //option to remove income from income table in database
    public void removeIncome(int userId, Scanner scanner){

        this.userId = userId;

        //variable to loop until desired id in table is found and removal Confirmed
        boolean quit = false;

        //variable to hold user input;
        int choice;

        //RemoveDataFromDatabase object to add remove income from database
        RemoveDataFromDatabase removeDataFromDatabase = new RemoveDataFromDatabase();


        System.out.println("-------------------------------");
        System.out.println("Removal of income\n");

        System.out.println("Please choose \'ID\' of income you wish to remove from list in database:");
        LinkedListsClass listsClass = new LinkedListsClass();
        PopulateLinkedLists populateLinkedLists = new PopulateLinkedLists(userId);
        populateLinkedLists.displayTableForRowRemoval(2, listsClass);
        listsClass.printIncome();

        while(!quit){
            System.out.println("Please enter id of desired row or \'0\' to return to menu");

            boolean hasInt = scanner.hasNextInt();

            if(hasInt){
                choice = scanner.nextInt();

                //allows user to cancel removal choice and go back to main menu
                if(choice == 0){
                    quit = true;
                } else {

                    quit = removeDataFromDatabase.removeEntry(userId, choice, "income");

                    if(quit){
                        System.out.println("Removal Completed");
                    }
                }


            }else {
                System.out.println("Invalid Input. Please enter numeric value of id for desired row");
            }

            //clear scanner
            scanner.nextLine();
        }


    }


}
