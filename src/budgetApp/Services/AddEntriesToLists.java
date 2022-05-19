package budgetApp.Services;

import budgetApp.Data.AddDataToDataBase;
import budgetApp.Model.*;

import java.util.LinkedList;

public class AddEntriesToLists {

    //Add New User to User LinkedList
    //Also calls methods to add new User to Database
    //@params first_name, last_name, email
    public void addNewUser(String first_Name, String last_name, String email, LinkedList<Users> users){

        //Call and Get User_id from addition of entry to users table
        AddDataToDataBase addDataToDataBase = new AddDataToDataBase();
        int user_id = addDataToDataBase.addUser(first_Name, last_name, email);

        //add new user to User LinkedList
        users.add(new Users(user_id, first_Name, last_name, email));
    }

    //Add New Income to Income LinkedList and User_Income LookUp Linked List
    //Also calls methods to add new Income to Database and update user_income LookUp Table
    //@params user_id, source_name, amount, payment_date, payment_interval, notes
    public void addNewIncome(int user_id, String source_name, double amount, String payment_date, String payment_interval,
                             String notes, LinkedList<Income> incomes, LinkedList<Users_Income> users_incomes){

        //Call and get income from addition of entry to income table
        //doing so will add new income to income table in database and update user_income lookUp table
        AddDataToDataBase addDataToDataBase = new AddDataToDataBase();
        int income_id = addDataToDataBase.addIncome(user_id, source_name, amount, payment_date, payment_interval, notes);

        //add new income to Income LinkedList
        incomes.add(new Income(income_id, source_name, amount, payment_date,
                payment_interval, notes));

        //add new income_id to user_income LinkedList
        AddDataToDataBase addDataToDataBase2 = new AddDataToDataBase();
        users_incomes.add(new Users_Income(addDataToDataBase2.idOfLastElement("user_income"), user_id, income_id));
    }

    //Add New Expense to Expense LinkedList and User_Expense LookUp Linked List
    //Also calls methods to add new Expense to Database and update user_expense LookUp Table
    //@params user id, type_Category, paid_to, amount, payment_date, description, notes, expense and user_enpenses
    //LinkedLists
    public void addNewExpense(int user_id, String type_category, String paid_to, double amount, String payment_date,
                              String description, String notes, LinkedList<Expenses> expenses,
                              LinkedList<User_Expenses> user_expenses){

        //Call and get expense_id from addition of entry to expenses table
        //doing so will add new expense to expenses table in database and update user_expenses lookUp table
        AddDataToDataBase addDataToDataBase = new AddDataToDataBase();
        int expense_id = addDataToDataBase.addExpenses(user_id, type_category, paid_to,
                amount,payment_date , description, notes);

        //add new Expense to Expense LinkedList
        expenses.add(new Expenses(expense_id, type_category, paid_to, amount,
                payment_date, description, notes));

        //add new expense_id to user_expenses LinkedList
        AddDataToDataBase addDataToDataBase2 = new AddDataToDataBase();
        user_expenses.add(new User_Expenses(addDataToDataBase2.idOfLastElement("user_expenses"), user_id, expense_id));

    }

    //Add New Debt_Payment to Debt_Payment LinkedList and User_Debt_Payment LookUp Linked List
    //Also calls methods to add new Debt_Payment to Database and update user_Debt_Payment LookUp Table
    //@params user id, type_Category, paid_to, amount, payment_date, description, notes, expense and user_expenses
    //LinkedLists
    public void addNewDebtPayment(int user_id, String type_category, double amount, String payment_date, String end_date,
                                  double total_owed, double interest, String notes, LinkedList<DebtPayments> debtPayments,
                                  LinkedList<User_DebtPayments> user_debtPayments){

        //Call and get debt_payments_id from addition of entry to debt_payments table
        //doing so will add new debt_payment to debt_payments table in database and update user_debt_payments lookUp table
        AddDataToDataBase addDataToDataBase = new AddDataToDataBase();
        int debt_payment_id = addDataToDataBase.addDebtExpenses(user_id, type_category, payment_date, amount,
                payment_date, end_date, total_owed, interest, notes);

        //add new Debt_Payments to Debt_Payments LinkedList
        debtPayments.add(new DebtPayments(debt_payment_id, type_category, payment_date, amount,
                payment_date, end_date, total_owed, interest, notes));

        //add new debt_payment_id to user_debt_payment LinkedList
        AddDataToDataBase addDataToDataBase2 = new AddDataToDataBase();
        user_debtPayments.add(new User_DebtPayments(addDataToDataBase2.idOfLastElement("debt_payments"),
                user_id,debt_payment_id ));

    }

    //Add New Savings to Savings LinkedList and User_Savings LookUp Linked List
    //Also calls methods to add new Savings to Database and update user_Savings LookUp Table
    //@params user_id, saved_location, amount, saved_date, notes, savings and user_savings linkedLists
    public void AddSavings(int user_id, String saved_location, double amount, String saved_date, String notes,
                           LinkedList<Savings> savings, LinkedList<User_Savings> user_savings){

        //Call and get savings_id from addition of entry to savings table
        //doing so will add new savings to savings table in database and update user_savings lookUp table
        AddDataToDataBase addDataToDataBase = new AddDataToDataBase();
        int savings_id = addDataToDataBase.addSavings(user_id, saved_location, amount,
                saved_date, notes);

        //add new Debt_Payments to Debt_Payments LinkedList
        savings.add(new Savings(savings_id, saved_location, amount, saved_date, notes));

        //add new savings_id to user_savings LinkedList
        AddDataToDataBase addDataToDataBase2 = new AddDataToDataBase();
        user_savings.add(new User_Savings(addDataToDataBase2.idOfLastElement("savings"),
                user_id, savings_id));

    }

    //Add New Custom_Goal to Custom_Goal LinkedList and User_Custom_Goal LookUp Linked List
    //Also calls methods to add new Custom_Goal to Database and update user_Custom_Goal LookUp Table
    //@params
    public void addNewCustomGoal(int user_id, String description, String saved_location, double amount, String saved_date,
                                 double total_desired, String final_date, String notes, LinkedList<CustomGoals> customGoals,
                                 LinkedList<User_CustomGoals> user_customGoals){

        //Call and get custom_goals_id from addition of entry to custom_goals table
        //doing so will add new custom_goal to custom_goals table in database and update user_custom_goals lookUp table
        AddDataToDataBase addDataToDataBase = new AddDataToDataBase();
        int custom_goals_id = addDataToDataBase.addCustomGoal(user_id, description, saved_location, amount, saved_date,
                total_desired, final_date, notes);

        //add new Custom_Goals to Custom_Goals LinkedList
        customGoals.add(new CustomGoals(custom_goals_id, description, saved_location, amount, saved_date, total_desired,
                final_date, notes));

        //add new custom_goal_id to user_custom_goals LinkedList
        AddDataToDataBase addDataToDataBase2 = new AddDataToDataBase();
        user_customGoals.add(new User_CustomGoals(addDataToDataBase2.idOfLastElement("custom_goals"),
                user_id, custom_goals_id));

    }

}
