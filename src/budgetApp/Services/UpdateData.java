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


}
