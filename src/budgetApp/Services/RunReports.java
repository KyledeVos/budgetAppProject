package budgetApp.Services;

import budgetApp.Controllers.LinkedListsClass;
import budgetApp.Data.PopulateLinkedLists;
import budgetApp.Model.DebtPayments;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

//class responsible for methods that run reports using LinkedLists of data from database
public class RunReports {

    //userId
    int userId;

    //Instance of LinkedList and PopulateLinkedList Class
    LinkedListsClass listsClass;
    PopulateLinkedLists populateLinkedLists;

    //Scanner if needed
    Scanner scanner;

    ///initialize fields with constructor
    public RunReports(Scanner scanner, int userId){

        this.userId = userId;
        this.scanner = scanner;

        listsClass = new LinkedListsClass();
        populateLinkedLists = new PopulateLinkedLists(userId);

    }

    //method to determine how long it will take a user to pay off their debt
    //@param name of debt
    public void debtPayOff(){

        //first we need a Linked List containing all Debt Payments for a user
        LinkedList<DebtPayments> debtPayments = new LinkedList<>();

        try {

            //populate debt payments list with data from database
            debtPayments = listsClass.getDebtPayments();
            populateLinkedLists.initializeDebtPayments(debtPayments);

        }catch(SQLException e){
            e.printStackTrace();
        }

        System.out.println("--------------------------------------------");
        System.out.println("Debt Payment");

        //if there is no data in the LinkedList there are no debt payments
        if(debtPayments.size() == 0){
            System.out.println("No Debt Payments found for user");
            System.out.println("--------------------------------------------");
            return;
        }

        //at this point we know there are DebtPayments

        //we want to get a list of all unique, most recent paid debt Payments for user
        LinkedList<DebtPayments> shortList = summarizeDebtPayments(debtPayments);

        //display names of all debt payment types to user and ask user to choose one

        //print available options to user
        System.out.println("Please select the option number corresponding to a debt payment");
        int count = 1;
        int limit = shortList.size();
        for(DebtPayments copy :shortList){
            System.out.println(count + " :" + copy.getType_category());
            count++;
        }

        //object to validate user input
        ValidateUserInput validateUserInput = new ValidateUserInput(scanner);
        int choice = validateUserInput.getMenuChoice(limit);

        //user's choice corresponds to a debt Payment
        DebtPayments chosenDebt = shortList.get(choice-1);

        //determine total time it would take user to pay off debt at current interest rate
        //uses compound interest and ignores end date given by user
        double paymentAmount = chosenDebt.getAmount();
        double totalDue = chosenDebt.getTotal_owed();
        double interestRate = chosenDebt.getInterest();
        System.out.println(paymentAmount + " " + totalDue + " " + interestRate);
        String currentDebtPaymentTime = debtTimeCalculator(paymentAmount, totalDue, interestRate);

        //calculate how long it would take to pay debt off by paying 20% more
        String extraTwentyPaymentTime = debtTimeCalculator(paymentAmount*1.2, totalDue, interestRate);

        //calculate how long it would take to pay off if user was paying 20% less
        String lessTwentyPaymentTime = debtTimeCalculator(paymentAmount*0.8, totalDue, interestRate);

        //display results to user:
        System.out.println("\nTime taken to pay off " + chosenDebt.getType_category() + ":");
        System.out.println("Current amount of R" + chosenDebt.getAmount() + " : " + currentDebtPaymentTime);
        System.out.println("Paying 20% More: " + extraTwentyPaymentTime);
        System.out.println("Paying 20% Less: " + lessTwentyPaymentTime);

        System.out.println("--------------------------------------------");

    }

    //HELPER METHOD
    //used to calculate the number of years and months it would take a user to pay off a debt
    //uses a present value annuity with compound interest.
    //interest is compounded monthly for realistic calculations
    //@param, monthly amount paid, total due, interest rate
    //@return String of years and months it would take to pay off debt
    private String debtTimeCalculator(double p, double a, double interest){

        double numberOfMonths =0;

        //convert interest rate to a percentage
        double i = interest/100;

        try{

           numberOfMonths = (Math.log(1/(1-(a*(i/12))/(p))))/(Math.log(1+(i/12)));

        }catch(ArithmeticException e){
            System.out.println("Divison by zero, value for debt payment has not been added correctly to database");
            System.out.println("Please correct");
        }

        //convert number of months to years and months
        int years = (int) (numberOfMonths/12);
        int months = (int) ((numberOfMonths-years*12));

        return "" + years + " years and " + months + " months";
    }

    //HELPER METHOD
    //used to populate a LinkedList with last found, non-duplicated debt payments for a user
    //@param Complete Linked List of all debt_payments for user
    //@return Linked List of unique debt payments
    private LinkedList<DebtPayments> summarizeDebtPayments(LinkedList<DebtPayments> fullList){

        //Linked List of summarized Debt Payments to return
        LinkedList<DebtPayments> shortList = new LinkedList<>();

        //create a list to hold names of all debt payments
        List<String> names = new LinkedList<>();

        //populate list will all names from full list
        for(DebtPayments hold : fullList){
            names.add(hold.getType_category());
        }

        //use Stream API to make a unique list of goal descriptions
        List<String> uniqueList = names.stream().distinct().collect(Collectors.toList());

        //populate shortList:
        //now we iterate through the fullList comparing the type/category name to the unique name in
        //the uniqueList of debt payment names (type) and add the last object that matches in the fullList
        //to the shortList
        int limit = uniqueList.size();
        int count = 0;

        while(count<limit) {

            String type = uniqueList.get(count);

            for (int i =fullList.size()-1; i>0; i--){
                if(fullList.get(i).getType_category().equals(type)){
                    shortList.add(fullList.get(i));
                    break;
                }
            }
            count++;
        }

        //we return the shortList
        return shortList;
    }

}
