package budgetApp.Controllers;

import budgetApp.Data.InitializeData;
import budgetApp.Model.*;

import java.util.LinkedList;

//Controller Class for Entire App
public class AppClass {

    public static void main(String[] args) {

        //immediately copy data from dataBase to DataStructures in Java Program
        InitializeData initializeData = new InitializeData();

        //Create LinkedLists of each Type
        LinkedList<Users> user = new LinkedList<>();
        LinkedList<Income> incomes = new LinkedList<>();
        LinkedList<Expenses> expenses = new LinkedList<>();
        LinkedList<DebtPayments> debtPayments = new LinkedList<>();
        LinkedList<Savings> savings = new LinkedList<>();
        LinkedList<CustomGoals> customGoals = new LinkedList<>();
        LinkedList<AccountSummary> accountSummaries = new LinkedList<>();
        LinkedList<Users_Income> users_incomes = new LinkedList<>();
        LinkedList<User_Expenses> user_expenses = new LinkedList<>();
        LinkedList<User_DebtPayments> user_debtPayments = new LinkedList<>();
        LinkedList<User_Savings> user_savings = new LinkedList<>();
        LinkedList<User_CustomGoals> user_customGoals = new LinkedList<>();
        LinkedList<User_AccountSummary> user_accountSummaries = new LinkedList<>();

        initializeData.initializeFromDatabase(user, incomes, expenses, debtPayments, savings, customGoals, accountSummaries,
                users_incomes, user_expenses, user_debtPayments,user_savings, user_customGoals, user_accountSummaries);


        for(Users test: user){
            System.out.println(test.toString());
        }

        System.out.println("\n-------------------\n");

        for(Income copy: incomes){
            System.out.println(copy.toString());
        }

        System.out.println("\n-------------------\n");

        for(Expenses copy: expenses){
            System.out.println(copy.toString());
        }

        System.out.println("\n-------------------\n");

        for(DebtPayments copy: debtPayments){
            System.out.println(copy.toString());
        }

        System.out.println("\n-------------------\n");

        for(Savings copy: savings){
            System.out.println(copy.toString());
        }

        System.out.println("\n-------------------\n");

        for(CustomGoals copy: customGoals){
            System.out.println(copy.toString());
        }

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
}
