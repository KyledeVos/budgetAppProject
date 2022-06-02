package budgetApp.Services;

//class used in App Class with methods to show user an overview of information
//Show user:
// 1) Upcoming Debt Payment Dates and Amounts
//2) Time and amount till completion of a custom goal
//3) Amount user can possibly add to savings now
//4) 30 vs 60 day expense track of user defined expense


import budgetApp.Controllers.LinkedListsClass;
import budgetApp.Data.PopulateLinkedLists;
import budgetApp.Model.DebtPayments;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;

public class OverviewInfo {

    //Info is taken from database and Added to a Linked List
    LinkedListsClass listsClass;
    PopulateLinkedLists populateLinkedLists;


    //constructor Initializes Class
    public OverviewInfo(int userId){
        listsClass = new LinkedListsClass();
        populateLinkedLists = new PopulateLinkedLists(userId);

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
            }

            System.out.println("--------------------------------------------");

        }

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
