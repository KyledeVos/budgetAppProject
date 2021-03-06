package budgetApp.Controllers;

import budgetApp.Model.*;

import java.util.LinkedList;

//class to hold all linked Lists that contain data from database
//Each Table relates to a specific object wih each object type stored in its own linkedList
public class LinkedListsClass {

    //Create LinkedLists of each Type
    LinkedList<Users> user;
    LinkedList<Income> incomes;
    LinkedList<Expenses> expenses;
    LinkedList<DebtPayments> debtPayments;
    LinkedList<Savings> savings;
    LinkedList<CustomGoals> customGoals;
    LinkedList<AccountSummary> accountSummaries;
    LinkedList<Users_Income> users_incomes;
    LinkedList<User_Expenses> user_expenses;
    LinkedList<User_DebtPayments> user_debtPayments;
    LinkedList<User_Savings> user_savings;
    LinkedList<User_CustomGoals> user_customGoals;
    LinkedList<User_AccountSummary> user_accountSummaries;

    public LinkedListsClass() {

        //initialize LinkedLists
        user = new LinkedList<>();
        incomes = new LinkedList<>();
        expenses = new LinkedList<>();
        debtPayments = new LinkedList<>();
        savings = new LinkedList<>();
        customGoals = new LinkedList<>();
        accountSummaries = new LinkedList<>();
        users_incomes = new LinkedList<>();
        user_expenses = new LinkedList<>();
        user_debtPayments = new LinkedList<>();
        user_savings = new LinkedList<>();
        user_customGoals = new LinkedList<>();
        user_accountSummaries = new LinkedList<>();

    }

    //used to print data in Income LinkedList
    public void printUsers(){
        for(Users copy: user){
            System.out.println(copy.toString() + "\n");
        }
    }

    //print info for current user only
    public void printCurrentUserInfo(int userid){
        for(Users copy: user){
            if(copy.getId() == userid){
                System.out.println(copy.toString());
            }
        }
    }

    //used to print data in Income LinkedList
    public void printIncome(){
        for(Income copy: incomes){
            System.out.println(copy.toString() + "\n");
        }
    }

    //used to print data in Expenses Class
    public void printExpenses(){
        for(Expenses copy: expenses){
            System.out.println(copy.toString()+ "\n");
        }
    }

    //used to print data in Debt Payments LinkedList
    public void printDebtPayments(){
        for(DebtPayments copy : debtPayments){
            System.out.println(copy.toString()+ "\n");
        }
    }

    //used to print data in Savings LinkedList
    public void printSavings(){
        for(Savings copy : savings){
            System.out.println(copy.toString()+ "\n");
        }
    }

    //used to print data in CustomGoals LinkedList
    public void printCustomGoals(){
        for(CustomGoals copy : customGoals){
            System.out.println(copy.toString()+ "\n");
        }
    }


    //Testing Method to check population of all LinkedLists from DataBase
    public void printOutDatabase(){

        for(Users test: user){
            System.out.println(test.toString());
        }

        System.out.println("\n-------------------\n");

        printIncome();

        System.out.println("\n-------------------\n");

        printExpenses();

        System.out.println("\n-------------------\n");

        printDebtPayments();

        System.out.println("\n-------------------\n");

        printSavings();

        System.out.println("\n-------------------\n");

        printCustomGoals();

        System.out.println("\n-------------------\n");

        for(AccountSummary copy: accountSummaries){
            System.out.println(copy.toString());
        }

        System.out.println("\n-------------------\n");

        for(Users_Income copy: users_incomes){
            System.out.println(copy.toString());
        }

        System.out.println("\n-------------------\n");

        for(User_Expenses copy: user_expenses){
            System.out.println(copy.toString());
        }

        System.out.println("\n-------------------\n");

        for(User_DebtPayments copy: user_debtPayments){
            System.out.println(copy.toString());
        }

        System.out.println("\n-------------------\n");

        for(User_Savings copy: user_savings){
            System.out.println(copy.toString());
        }

        System.out.println("\n-------------------\n");

        for(User_CustomGoals copy: user_customGoals){
            System.out.println(copy.toString());
        }

        System.out.println("\n-------------------\n");

        for(User_AccountSummary copy: user_accountSummaries){
            System.out.println(copy.toString());
        }

    }

    //Getters for all LinkedLists (may remove later)
    public LinkedList<Users> getUser() {
        return user;
    }

    public LinkedList<Income> getIncomes() {
        return incomes;
    }

    public LinkedList<Expenses> getExpenses() {
        return expenses;
    }

    public LinkedList<DebtPayments> getDebtPayments() {
        return debtPayments;
    }

    public LinkedList<Savings> getSavings() {
        return savings;
    }

    public LinkedList<CustomGoals> getCustomGoals() {
        return customGoals;
    }

    public LinkedList<AccountSummary> getAccountSummaries() {
        return accountSummaries;
    }

    public LinkedList<Users_Income> getUsers_incomes() {
        return users_incomes;
    }

    public LinkedList<User_Expenses> getUser_expenses() {
        return user_expenses;
    }

    public LinkedList<User_DebtPayments> getUser_debtPayments() {
        return user_debtPayments;
    }

    public LinkedList<User_Savings> getUser_savings() {
        return user_savings;
    }

    public LinkedList<User_CustomGoals> getUser_customGoals() {
        return user_customGoals;
    }

    public LinkedList<User_AccountSummary> getUser_accountSummaries() {
        return user_accountSummaries;
    }
}
