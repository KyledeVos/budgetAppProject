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

    public boolean removeUser(int userId, Scanner scanner){

        //variable to loop until desired id in table is found and removal Confirmed
        boolean quit = false;

        //variable to return if user has been deleted
        boolean endApp = false;

        //variable to hold user input;
        int choice;

        //RemoveDataFromDatabase object to add remove income from database
        RemoveDataFromDatabase removeDataFromDatabase = new RemoveDataFromDatabase();

        //Display Warning Message
        System.out.println("-------------------------------");
        System.out.println("Removal of User\n");
        System.out.println("WARNING!");
        System.out.println("Removal of User will delete ALL data associated with that user");
        System.out.println("THIS ACTION CANNOT BE UNDONE\n");

        //Display list of Users for removal
        System.out.println("Please choose \'ID\' of user you wish to remove from list in database:");
        System.out.println("Please note you can only delete your own data and no one else's");
        LinkedListsClass listsClass = new LinkedListsClass();
        PopulateLinkedLists populateLinkedLists = new PopulateLinkedLists(userId);
        populateLinkedLists.displayTableForRowRemoval(1, listsClass);
        listsClass.printUsers();


        while(!quit){
            System.out.println("Please enter id of desired row or \'0\' to return to menu");

            //variable to loop until desired id in table is found and removal Confirmed
            boolean hasInt = scanner.hasNextInt();

            if(hasInt){
                choice = scanner.nextInt();

                //allows user to cancel removal choice and go back to main menu
                if(choice == 0){
                    quit = true;
                } else {

                    //at this point we want to remove the user
                    quit = removeDataFromDatabase.removeUser(userId, choice);
                    endApp = quit;

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

        return endApp;

    }

    //option to remove income from income table in database
    public void removeIncome(int userId, Scanner scanner){

        //id of user to remove data row from
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


    //option to remove expense from expense table in database
    public void removeExpense(int userId, Scanner scanner){

        //id of user to remove data row from
        this.userId = userId;

        //variable to loop until desired id in table is found and removal Confirmed
        boolean quit = false;

        //variable to hold user input;
        int choice;

        //RemoveDataFromDatabase object to add remove expense from database
        RemoveDataFromDatabase removeDataFromDatabase = new RemoveDataFromDatabase();


        System.out.println("-------------------------------");
        System.out.println("Removal of expense\n");

        System.out.println("Please choose \'ID\' of expense you wish to remove from list in database:");
        LinkedListsClass listsClass = new LinkedListsClass();
        PopulateLinkedLists populateLinkedLists = new PopulateLinkedLists(userId);
        populateLinkedLists.displayTableForRowRemoval(3, listsClass);
        listsClass.printExpenses();

        while(!quit){
            System.out.println("Please enter id of desired row or \'0\' to return to menu");

            boolean hasInt = scanner.hasNextInt();

            if(hasInt){
                choice = scanner.nextInt();

                //allows user to cancel removal choice and go back to main menu
                if(choice == 0){
                    quit = true;
                } else {

                    quit = removeDataFromDatabase.removeEntry(userId, choice, "expenses");

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

    //option to remove debt payment from debt payment table in database
    public void removeDebtPayment(int userId, Scanner scanner){

        //id of user to remove data row from
        this.userId = userId;

        //variable to loop until desired id in table is found and removal Confirmed
        boolean quit = false;

        //variable to hold user input;
        int choice;

        //RemoveDataFromDatabase object to add remove debt payment from database
        RemoveDataFromDatabase removeDataFromDatabase = new RemoveDataFromDatabase();


        System.out.println("-------------------------------");
        System.out.println("Removal of Debt Payment\n");

        System.out.println("Please choose \'ID\' of debt payment you wish to remove from list in database:");
        LinkedListsClass listsClass = new LinkedListsClass();
        PopulateLinkedLists populateLinkedLists = new PopulateLinkedLists(userId);
        populateLinkedLists.displayTableForRowRemoval(4, listsClass);
        listsClass.printDebtPayments();

        while(!quit){
            System.out.println("Please enter id of desired row or \'0\' to return to menu");

            boolean hasInt = scanner.hasNextInt();

            if(hasInt){
                choice = scanner.nextInt();

                //allows user to cancel removal choice and go back to main menu
                if(choice == 0){
                    quit = true;
                } else {

                    quit = removeDataFromDatabase.removeEntry(userId, choice, "debt_payments");

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


    //option to remove a savings from savings table in database
    public void removeSavings(int userId, Scanner scanner){

        //id of user to remove data row from
        this.userId = userId;

        //variable to loop until desired id in table is found and removal Confirmed
        boolean quit = false;

        //variable to hold user input;
        int choice;

        //RemoveDataFromDatabase object to add remove savings from database
        RemoveDataFromDatabase removeDataFromDatabase = new RemoveDataFromDatabase();


        System.out.println("-------------------------------");
        System.out.println("Removal of Savings\n");

        System.out.println("Please choose \'ID\' of Savings you wish to remove from list in database:");
        LinkedListsClass listsClass = new LinkedListsClass();
        PopulateLinkedLists populateLinkedLists = new PopulateLinkedLists(userId);
        populateLinkedLists.displayTableForRowRemoval(5, listsClass);
        listsClass.printCustomGoals();

        while(!quit){
            System.out.println("Please enter id of desired row or \'0\' to return to menu");

            boolean hasInt = scanner.hasNextInt();

            if(hasInt){
                choice = scanner.nextInt();

                //allows user to cancel removal choice and go back to main menu
                if(choice == 0){
                    quit = true;
                } else {

                    quit = removeDataFromDatabase.removeEntry(userId, choice, "custom_goals");

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

    //option to remove custom_goal from custom goal table in database
    public void removeCustomGoal(int userId, Scanner scanner){

        //id of user to remove data row from
        this.userId = userId;

        //variable to loop until desired id in table is found and removal Confirmed
        boolean quit = false;

        //variable to hold user input;
        int choice;

        //RemoveDataFromDatabase object to add remove custom goal from database
        RemoveDataFromDatabase removeDataFromDatabase = new RemoveDataFromDatabase();


        System.out.println("-------------------------------");
        System.out.println("Removal of Custom Goal\n");

        System.out.println("Please choose \'ID\' of Custom Goal you wish to remove from list in database:");
        LinkedListsClass listsClass = new LinkedListsClass();
        PopulateLinkedLists populateLinkedLists = new PopulateLinkedLists(userId);
        populateLinkedLists.displayTableForRowRemoval(6, listsClass);
        listsClass.printCustomGoals();

        while(!quit){
            System.out.println("Please enter id of desired row or \'0\' to return to menu");

            boolean hasInt = scanner.hasNextInt();

            if(hasInt){
                choice = scanner.nextInt();

                //allows user to cancel removal choice and go back to main menu
                if(choice == 0){
                    quit = true;
                } else {

                    quit = removeDataFromDatabase.removeEntry(userId, choice, "custom_goals");

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
