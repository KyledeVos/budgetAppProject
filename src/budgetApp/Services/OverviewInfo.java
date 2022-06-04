package budgetApp.Services;

//class used in App Class with methods to show user an overview of information
//Show user:
// 1) Upcoming Debt Payment Dates and Amounts
//2) Time and amount till completion of a custom goal
//3) Amount user can possibly add to savings now
//4) 30 vs 60 day expense track of user defined expense


import budgetApp.Controllers.LinkedListsClass;
import budgetApp.Data.AccountSummaryControl;
import budgetApp.Data.PopulateLinkedLists;
import budgetApp.Model.CustomGoals;
import budgetApp.Model.DebtPayments;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OverviewInfo {

    //Info is taken from database and Added to a Linked List
    LinkedListsClass listsClass;
    PopulateLinkedLists populateLinkedLists;

    //id of user
    int userId;


    //constructor Initializes Class
    public OverviewInfo(int userId){
        listsClass = new LinkedListsClass();
        populateLinkedLists = new PopulateLinkedLists(userId);
        this.userId = userId;

    }

    //method to print out upcoming dates and payment amounts for debt payments
    public void printUpcomingDebtPaymentInfo(){

        //first we need a Linked List containing all debt payments for a user
        LinkedList<DebtPayments> debtPayments = new LinkedList<>();

        try {

            //populate debt payments list with data from database
            debtPayments = listsClass.getDebtPayments();
            populateLinkedLists.initializeDebtPayments(debtPayments);

        }catch(SQLException e){
            e.printStackTrace();
        }

        System.out.println("--------------------------------------------");
        System.out.println("Upcoming Debt Payments Date and Amount");

        //if there is no data in the LinkedList there were no debt payments in the dataBase
        if(debtPayments.size() == 0){
            System.out.println("No Debt Payment Data for user");
            System.out.println("--------------------------------------------");
            return;
        }

        //at this point we know there is Debt Payment data for the user

        //need an List of debt payments using the last as a reference and working with ones that go back one month
        LinkedList<DebtPayments> previousPayments = populateListForPeriod(debtPayments);

        //first check if all debt payments for the last month have been paid off
        if(previousPayments.size() == 0) {
            System.out.println("All Debts have been paid off");
            System.out.println("--------------------------------------------");
            return;
        } else {
            int count = 1;

            for(DebtPayments current : previousPayments){
                System.out.println(count + " : name - " + current.getType_category() + ", Amount :R " +
                        current.getAmount() + ", next payment date : " + incrementDate(current.getPayment_date()));
                count++;
            }

            System.out.println("--------------------------------------------");

        }

    }

    //method to print out time and amount till completion of custom goal
    public void printCustomGoalsInfo(){

        //first we need a Linked List containing all custom goals for a user
        LinkedList<CustomGoals> customGoals = new LinkedList<>();

        try {

            //populate debt payments list with data from database
            customGoals = listsClass.getCustomGoals();
            populateLinkedLists.initializeCustomGoals(customGoals);

        }catch(SQLException e){
            e.printStackTrace();
        }

        System.out.println("--------------------------------------------");
        System.out.println("Custom Goals Overview");

        //if there is no data in the LinkedList there are no custom Goals set by the user
        if(customGoals.size() == 0){
            System.out.println("No Custom Goalsfor user");
            System.out.println("--------------------------------------------");
            return;
        }

        //at this point we know there are custom Goals for the user

        //we want to get a list of all unique custom goals with an amount equal to the total the user
        //has saved towards the goal

        LinkedList<CustomGoals> shortList = summarizeCustomGoals(customGoals);

        for(CustomGoals copy : shortList){
            System.out.println("\nCustom Goal: " + copy.getDescription());
            System.out.println("Amount Saved: R" + copy.getAmount());
            System.out.println("Total amount need to complete goal: R" + copy.getTotal_desired());
        }

    }

    //method allowing user to try and test the amount they would like to add to their savings
    //calculates their current account balance and allows user to enter a percentage of this amount they would like to add
    //to savings. Does not add the amount for the user
    //@params Scanner instance, userId
    public void savingsCalculator(Scanner scanner){

        //first we need to get the user's total income, total expenses, total debt payments, total
        //savings and total custom_goals amount to determine their current account balance
        AccountSummaryControl accountSummaryControl = new AccountSummaryControl(userId);

        double totalIncome = accountSummaryControl.totalAmountForTable(userId, "income");
        double totalExpenses = accountSummaryControl.totalAmountForTable(userId, "expenses");
        double totalDebtPayments = accountSummaryControl.totalAmountForTable(userId, "debt_payments");
        double totalSavings = accountSummaryControl.totalAmountForTable(userId, "savings");
        double totalCustomGoals = accountSummaryControl.averageAmountForTable(userId, "custom_goals");

        //calculate user's current account balance
        double currentBalance = totalIncome - totalExpenses - totalDebtPayments - totalSavings - totalCustomGoals;

        //boolean to check if user wants to stop using tool
        boolean quit = false;

        //print current balance data to user
        System.out.println("-------------------------------------------");
        System.out.println("Savings Calculator Tool\n");

        System.out.println("Your Current Balance is R" + currentBalance + "\n");

        //variable to hold user's choice
        int choice;

        while(!quit){

            System.out.println("\nPlease enter percentage you would like to save or \'0'\' to quit");

            boolean hasInt = scanner.hasNextInt();

            if(hasInt){
                choice = scanner.nextInt();

                if(choice== 0){
                    System.out.println("Exit");
                    quit = true;

                } else if(choice > 0 && choice <100){
                    int savingsAmount = (int) ((currentBalance * ((double) (choice)/100)));
                    System.out.println("Percentage: " + choice + "%");
                    System.out.println("Savings Amount would be: R" + savingsAmount);
                    System.out.println("Remaining account balance would be: R" + (currentBalance - savingsAmount));
                } else{
                    System.out.println("Invalid Percentage. Please enter a number between 1 and 100 or \'0\' to quit");
                }

            }else{
                System.out.println("Invalid Input");
            }

            scanner.nextLine();

        }

        System.out.println("----------------------------------------------------");

    }

    //HELPER METHOD
    //used to populate a debt payment linked list with data from last entry in list and go back one month
    //@param Linked List
    //@return linkedList containing data from last month
    private LinkedList<DebtPayments> populateListForPeriod(LinkedList<DebtPayments> initialList){

        //debtPayment list to be returned
        LinkedList<DebtPayments> reducedList = new LinkedList<>();

        //retrieve date String from last entry in original List (start point)
        String lastDate = initialList.getLast().getPayment_date();

        //set previous month to be one less than one received from lastDate
        String previousMonth = lastDate.substring(5, 7);

        //convert month to int value
        int currentMonth = Integer.parseInt(previousMonth);

        //iterate through full linked list to check for debt payments that
        //1) do not have a total owed = 0 -> payments would be completed
        //2) fall in the period of the last month

        Iterator<DebtPayments> iterator = initialList.iterator();

        while(iterator.hasNext()){

            DebtPayments current = iterator.next();

            if(current.getTotal_owed() == 0){
                //if current debt payment has total = 0 then its payments are completed
                iterator.next();
            } else {

                //check that date of debt_payment falls in one month period

                //get Date as String and isolate month, convert to int value
                int currentEntryMonth = Integer.parseInt(current.getPayment_date().substring(5, 7));

                //check that month of entry is one or less than one month behind currentMonth
                if(currentEntryMonth == currentMonth-1){
                    //this is a valid debt payment and can be added to the new list
                    reducedList.add(current);
                }



            }

        }

        return  reducedList;

    }


    //HELPER METHOD
    //used to populate a LinkedList with current, non-duplicated custom goals for a user
    //@param Complete Linked List of all custom goals for user
    //@return Linked List of unique custom goals with amount modified to total amount user has saved toward goal\
    private LinkedList<CustomGoals> summarizeCustomGoals(LinkedList<CustomGoals> fullList){

        //Linked List of summarized custom goals to return
        LinkedList<CustomGoals> shortList = new LinkedList<>();

        //create a list to hold names of all custom_goals
        List<String> names = new LinkedList<>();

        //populate list will all names from full list
        for(CustomGoals hold : fullList){
            names.add(hold.getDescription());
        }

        //use Stream API to make a unique list of goal descriptions
        List<String> uniqueList = names.stream().distinct().collect(Collectors.toList());

        //populate shortList:
        //now we iterate through the fullList comparing the description name to the unique name in
        //the uniqueList of goal names (descriptions) and add the first object that matches in the fullList
        //to the shortList
        int limit = uniqueList.size();
        int count = 0;

        while(count<limit) {

            String description = uniqueList.get(count);

            for (CustomGoals hold : fullList) {
                if(hold.getDescription().equals(description)){

                    //here we want to change the amount of hold to zero for when we
                    //add all the amounts together in the next step
                    //however we keep track of the old amount to restore it in the fullList

                    shortList.add(hold);
                    count++;
                    break;
                }
            }
        }

        //finally, we iterate through the fullList checking for matching names with the unique goals in shortList
        //if there is a match, then we add the amount from the goal in the fullList to the amount in the shortList
        for(CustomGoals shortListTemp: shortList){

            //for first run, get amount in shortList to subtract from amount at end
            //addition of amount from fullList causes a duplicate on first entry
            double firstAmount = shortListTemp.getAmount();
            for(CustomGoals fullListTemp: fullList){

                if(shortListTemp.getDescription().equals(fullListTemp.getDescription())){

                    //get current amount in shortList goal and increase amount by fullList goal
                    double amount = shortListTemp.getAmount();
                    amount += fullListTemp.getAmount();
                    shortListTemp.setAmount(amount);

                }

            }

            //reduce shortListAmount by subtracting initial duplicate amount from fullList
            double oldAmount = shortListTemp.getAmount();
            shortListTemp.setAmount(oldAmount - firstAmount);

        }

        //we return the shortList
        return shortList;

    }



    //HELPER METHOD
    //used to increment a given date string by one month
    //@param date
    //@return String incremented date
    private String incrementDate(String date){

        //first we isolate the year, month and day from the given date
        //these would exist and be in valid format with ValidateDataFromDataBase()
        //in ValidateDataFromDataBase Class

        //Note: end index is exclusive
        String yearString = date.substring(0, 4);
        String monthString = date.substring(5, 7);
        String dayString = date.substring(8, 10);

        //we need to convert the year and month to int values
        int year = Integer.parseInt(yearString);
        int month = Integer.parseInt(monthString);

        //if the month is less than 12, then we simply increment the month and leave the year as it is
        //if the month equals 12, then we increment the year and change the month to one
        if(month<12){
            month++;
        } else {
            year++;
            month = 1;
        }

        //-------------------------
        //Format Incremented Date String

        //if the month is less than 10 then we need to add a zero to the front of the month String
        if(month<10){
            monthString = "0" + month;
        } else{
            monthString = "" +  month;
        }

        return "" + year + "-" + monthString + "-" + dayString;
    }


}
