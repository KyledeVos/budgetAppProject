package budgetApp.Services;

import budgetApp.Data.AddDataToDataBase;

import java.util.Scanner;

public class AddData {

    //add a new user to the database using AddDataToDataBase Class
    //return id of new User
    public int addNewUser(Scanner scanner){

        //AddDataToDataBase object to add new user to database
        AddDataToDataBase addDataToDataBase = new AddDataToDataBase();

        //user information to be passed to addUser Method
        String firstName;
        String lastName;
        String email;

        //userId of new user to return to calling method
        int userId;

        System.out.println("---------------------");
        System.out.println("Addition of new User");

        System.out.println("Please enter first name of new user:");
        firstName = scanner.nextLine();

        System.out.println("\nPlease enter last name of new user");
        lastName = scanner.nextLine();

        System.out.println("\nPlease enter email address of new user");
        email = scanner.nextLine();

        userId = addDataToDataBase.addUser(firstName, lastName, email);

        System.out.println("\nUser: " + firstName + " " + lastName + " with email: " + email + " has been added to database");
        System.out.println("---------------------");

        return userId;
    }

}
