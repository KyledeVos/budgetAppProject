package budgetApp.Services;

import budgetApp.Data.AccountSummaryControl;

public class AccountSummary {

    //fields to hold totals for accountSummaryCalculations
    double accountBalance;
    double savingsBalance;
    double totalDebt;
    double totalSavings;
    double averageSavings;
    double totalGoals;

    //user id
    int userId;

    //AccountSummaryControl in Data Class instance
    AccountSummaryControl accountSummaryControl;

    public AccountSummary(int userId){
        this.userId = userId;

        //initialize Fields
        accountBalance = 0;
        savingsBalance = 0;
        totalDebt = 0;
        totalSavings = 0;
        averageSavings = 0;
        totalGoals = 0;
        accountSummaryControl = new AccountSummaryControl(userId);

    }

    //method to print General Account Summary
    //@param user_id
    public void printGenSummary(){

        //variables to hold total income and expenses
        double totalIncome = 0;
        double totalExpenses = 0;

        //get total income received for a user
        totalIncome = accountSummaryControl.totalAmountForTable(userId, "income");

        //get total expenses for a user
        totalExpenses = accountSummaryControl.totalAmountForTable(userId, "expenses");

        //get total debt payments
        totalDebt = accountSummaryControl.totalAmountForTable(userId, "debt_payments");

        //get total savings
        totalSavings = accountSummaryControl.totalAmountForTable(userId, "savings");

        //get average savings amount
        averageSavings = accountSummaryControl.averageAmountForTable(userId, "savings");

        //get total custom Goals
        totalGoals = accountSummaryControl.totalAmountForTable(userId, "custom_goals");


        //calculate account balance
        double balance = totalIncome - (totalExpenses + totalDebt + totalSavings + totalGoals);

        //calculate income to debt ratio

        double debtRatio =  totalDebt/totalIncome;
        double expensesPercentage = (totalExpenses + totalDebt)/totalIncome;

        //Print Summary to User:

        System.out.println("------------------------------------------------------------------------");
        System.out.println("Account Summary:");
        System.out.println("Current Account Balance: R" + balance);
        System.out.println("Total Amount saved: R" + totalSavings);
        System.out.println("Your average savings amount is: R" + averageSavings);
        System.out.println("Total amount towards Custom Goals: R" + totalGoals);
        System.out.println("Income to Debt Ratio is  1 : " + debtRatio);
        System.out.println("Debt and expenses  = " + (expensesPercentage*100) + "% of income total");

        System.out.println("------------------------------------------------------------------------");

        //close connection to database
        accountSummaryControl.closeConnection();


    }
}
