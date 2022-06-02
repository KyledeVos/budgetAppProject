package budgetApp.Data;

import budgetApp.Controllers.LinkedListsClass;
import budgetApp.Model.*;

import java.sql.*;
import java.util.LinkedList;


//Data Class used to connect to Database and initialize linked lists of each object specific to each table
//in the database
public class PopulateLinkedLists {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private int userId;

    public PopulateLinkedLists(int userId) {

        this.userId = userId;

        try {
            //load the jdbc Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //create connection to dataBase
            connection = DriverManager.getConnection("jdbc:mysql://localhost/javabudgetapp?user=...&password=...&useSSL=false");

        }catch(SQLException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            System.out.println("Driver not on class Path");
        }

        this.preparedStatement = null;
        this.resultSet = null;
    }

    //method called in controller class to initialize linked lists of each object
    //@Params Linked Lists of each Model Object Type
    public void initializeFromDatabase(LinkedListsClass listsClass){

        try{

            //Call Each Method below that initializes a unique linked list of each Model Object Type
            initializeUsers(listsClass.getUser());
            initializeIncomes(listsClass.getIncomes());
            initializeExpenses(listsClass.getExpenses());
            initializeDebtPayments(listsClass.getDebtPayments());
            initializeSavings(listsClass.getSavings());
            initializeCustomGoals(listsClass.getCustomGoals());
            initializeAccountSummary(listsClass.getAccountSummaries());
            initializeUserIncome(listsClass.getUsers_incomes());
            initializeUserExpenses(listsClass.getUser_expenses());
            initializeUserDebtPayments(listsClass.getUser_debtPayments());
            initializeUserSavings(listsClass.getUser_savings());
            initializeUserCustomGoals(listsClass.getUser_customGoals());
            initializeUserAccountSummary(listsClass.getUser_accountSummaries());


        }catch(SQLException exc){
            System.out.println("Sql Exception Occurred");
        }  finally {
            try{
                preparedStatement.close();
                resultSet.close();
                connection.close();
            }catch(SQLException exc){
                System.out.println("unable to close");
            }
        }


    }

    //method to display table content for removal of a row
    //excludes users
    //@param int menu option choice
    public void displayTableForRowRemoval(int choice, LinkedListsClass listsClass){

        //use user's choice to populate specific linkedList from database
        try {

            switch(choice) {

                //1 - user wants to remove an income

                case 1:
                    initializeUsers(listsClass.getUser());
                    break;

                case 2:
                    initializeIncomes(listsClass.getIncomes());
                    break;

                case 3:
                    initializeExpenses(listsClass.getExpenses());
                    break;

                case 4:
                    initializeDebtPayments(listsClass.getDebtPayments());
                    break;

                case 5:
                    initializeSavings(listsClass.getSavings());
                    break;

                case 6:
                    initializeCustomGoals(listsClass.getCustomGoals());
                    break;

                default:
                    System.out.println("Invalid");


            }
        }catch(SQLException e){
            e.printStackTrace();
        } finally {
            try{
                resultSet.close();
                preparedStatement.close();
                connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }


    }

    //populate user List with data from User Table in Database
    //@Params User LinkedList
    private void initializeUsers(LinkedList<Users> userList) throws SQLException{

        //first we clear all data that may be in the list
        userList.clear();

        preparedStatement = connection.prepareStatement("Select * FROM users");
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){

            Users user = new Users();
            user.setId(resultSet.getInt("id"));
            user.setFirst_name(resultSet.getString("first_name"));
            user.setLast_name(resultSet.getString("last_name"));
            user.setEmail(resultSet.getString("email"));
            userList.add(user);

        }
    }


    //populate income List with data from Income Table in Database
    //@Params Income LinkedList
    private void initializeIncomes(LinkedList<Income> incomeList) throws SQLException{

        //first we clear all data that may be in the list
        incomeList.clear();

        preparedStatement = connection.prepareStatement("Select * FROM income JOIN user_income ON " +
                "user_income.income_id = income.id JOIN users ON user_income.user_id = users.id WHERE user_id =   " + userId);
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){

            Income income = new Income();
            income.setId(resultSet.getInt("id"));
            income.setSource_name(resultSet.getString("source_name"));
            income.setAmount(resultSet.getDouble("amount"));
            income.setPayment_date(resultSet.getString("payment_date"));
            income.setPayment_interval(resultSet.getString("payment_interval"));
            income.setNotes(resultSet.getString("notes"));
            incomeList.add(income);

        }
    }

    //populate expenses List with data from Expenses Table in Database
    //@Params Expense LinkedList
    private void initializeExpenses(LinkedList<Expenses> expenseList) throws SQLException{

        //first we clear all data that may be in the list
        expenseList.clear();

        preparedStatement = connection.prepareStatement("Select * FROM expenses JOIN user_expenses ON " +
                "user_expenses.expenses_id = expenses.id JOIN users ON user_expenses.user_id = users.id WHERE user_id =   " +
                 userId);
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){

            Expenses expense = new Expenses();
            expense.setId(resultSet.getInt("id"));
            expense.setType_category(resultSet.getString("type_category"));
            expense.setPaid_to(resultSet.getString("paid_to"));
            expense.setAmount(resultSet.getDouble("amount"));
            expense.setPayment_date(resultSet.getString("payment_date"));
            expense.setDescription(resultSet.getString("description"));
            expense.setNotes(resultSet.getString("notes"));
            expenseList.add(expense);

        }
    }

    //populate DebtPayments List with data from Debt_Payments Table in Database
    //@Params DebtPayments LinkedList
    public void initializeDebtPayments(LinkedList<DebtPayments> debtPaymentsList) throws SQLException{

        //first we clear all data that may be in the list
        debtPaymentsList.clear();

        preparedStatement = connection.prepareStatement("Select * FROM debt_payments JOIN user_debt_payments ON " +
                "user_debt_payments.debt_payments_id = debt_payments.id JOIN users ON user_debt_payments.user_id = users.id " +
                "WHERE user_id =   " + userId);
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){

            DebtPayments debtPayments = new DebtPayments();
            debtPayments.setId(resultSet.getInt("id"));
            debtPayments.setType_category(resultSet.getString("type_category"));
            debtPayments.setPaid_to(resultSet.getString("paid_to"));
            debtPayments.setAmount(resultSet.getDouble("amount"));
            debtPayments.setPayment_date(resultSet.getString("payment_date"));
            debtPayments.setEnd_date(resultSet.getString("end_date"));
            debtPayments.setTotal_owed(resultSet.getDouble("total_owed"));
            debtPayments.setInterest(resultSet.getDouble("interest"));
            debtPayments.setNotes(resultSet.getString("notes"));
            debtPaymentsList.add(debtPayments);
        }
    }

    //populate savings List with data from Savings Table in Database
    //@Params Savings LinkedList
    private void initializeSavings(LinkedList<Savings> savingsList) throws SQLException{

        //first we clear all data that may be in the list
        savingsList.clear();

        preparedStatement = connection.prepareStatement("Select * FROM savings JOIN user_savings ON " +
                "user_savings.savings_id = savings.id JOIN users ON user_savings.user_id = users.id WHERE user_id =   " +
                userId);
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){

            Savings savings = new Savings();
            savings.setId(resultSet.getInt("id"));
            savings.setSaved_location(resultSet.getString("saved_location"));
            savings.setAmount(resultSet.getDouble("amount"));
            savings.setSaved_date(resultSet.getString("saved_date"));
            savings.setNotes(resultSet.getString("notes"));
            savingsList.add(savings);

        }
    }

    //populate CustomGoals List with data from CustomGoals Table in Database
    //@Params CustomGoals LinkedList
    private void initializeCustomGoals(LinkedList<CustomGoals> customGoalsList) throws SQLException{

        //first we clear all data that may be in the list
        customGoalsList.clear();

        preparedStatement = connection.prepareStatement("Select * FROM custom_goals JOIN user_custom_goals ON " +
                "user_custom_goals.custom_goals_id = custom_goals.id JOIN users ON user_custom_goals.user_id = users.id " +
                "WHERE user_id =   " +
                userId);
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){

            CustomGoals customGoals = new CustomGoals();
            customGoals.setId(resultSet.getInt("id"));
            customGoals.setDescription(resultSet.getString("description"));
            customGoals.setSaved_location(resultSet.getString("saved_location"));
            customGoals.setAmount(resultSet.getDouble("amount"));
            customGoals.setSaved_date(resultSet.getString("saved_date"));
            customGoals.setTotal_desired(resultSet.getDouble("total_desired"));
            customGoals.setFinal_date(resultSet.getString("final_date"));
            customGoals.setNotes(resultSet.getString("notes"));
            customGoalsList.add(customGoals);
        }
    }

    //populate accountSummary List with data from accountSummary Table in Database
    //@Params accountSummary LinkedList
    private void initializeAccountSummary(LinkedList<AccountSummary> accountSummariesList) throws SQLException{

        //first we clear all data that may be in the list
        accountSummariesList.clear();

        preparedStatement = connection.prepareStatement("Select * FROM accountsummary");
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){

            AccountSummary accountSummary = new AccountSummary();
            accountSummary.setId(resultSet.getInt("id"));
            accountSummary.setAccount_balance(resultSet.getDouble("account_balance"));
            accountSummary.setMonth(resultSet.getInt("month"));
            accountSummary.setYear(resultSet.getInt("year"));
            accountSummary.setTotal_income(resultSet.getDouble("total_income"));
            accountSummary.setTotal_expenses(resultSet.getDouble("total_expenses"));
            accountSummary.setTotal_savings(resultSet.getDouble("total_savings"));
            accountSummariesList.add(accountSummary);
        }
    }

    //populate user_income List with data from user_income LookUp Table in Database
    //@Params user_income LinkedList
    private void initializeUserIncome(LinkedList<Users_Income> users_incomesList) throws SQLException{

        //first we clear all data that may be in the list
        users_incomesList.clear();

        preparedStatement = connection.prepareStatement("Select * FROM user_income");
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){

            Users_Income users_income = new Users_Income();
            users_income.setId(resultSet.getInt("id"));
            users_income.setUser_id(resultSet.getInt("user_id"));
            users_income.setIncome_id(resultSet.getInt("income_id"));

            users_incomesList.add(users_income);
        }
    }

    //populate user_expenses List with data from user_expenses LookUp Table in Database
    //@Params user_expenses LinkedList
    private void initializeUserExpenses(LinkedList<User_Expenses> users_expensesList) throws SQLException{

        //first we clear all data that may be in the list
        users_expensesList.clear();

        preparedStatement = connection.prepareStatement("Select * FROM user_expenses");
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){

            User_Expenses user_expenses = new User_Expenses();
            user_expenses.setId(resultSet.getInt("id"));
            user_expenses.setUser_id(resultSet.getInt("user_id"));
            user_expenses.setExpenses_id(resultSet.getInt("expenses_id"));

            users_expensesList.add(user_expenses);
        }
    }

    //populate user_debtPayments List with data from user_debtPayments LookUp Table in Database
    //@Params user_debtPayments LinkedList
    private void initializeUserDebtPayments(LinkedList<User_DebtPayments> users_debtPaymentsList) throws SQLException{

        //first we clear all data that may be in the list
        users_debtPaymentsList.clear();

        preparedStatement = connection.prepareStatement("Select * FROM user_debt_payments");
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){

            User_DebtPayments user_debtPayments = new User_DebtPayments();
            user_debtPayments.setId(resultSet.getInt("id"));
            user_debtPayments.setUser_id(resultSet.getInt("user_id"));
            user_debtPayments.setUser_debtPayments(resultSet.getInt("debt_payments_id"));

            users_debtPaymentsList.add(user_debtPayments);
        }
    }

    //populate user_savings List with data from user_savings LookUp Table in Database
    //@Params user_savings LinkedList
    private void initializeUserSavings(LinkedList<User_Savings> users_savingsList) throws SQLException{

        //first we clear all data that may be in the list
        users_savingsList.clear();

        preparedStatement = connection.prepareStatement("Select * FROM user_savings");
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){

            User_Savings user_savings = new User_Savings();
            user_savings.setId(resultSet.getInt("id"));
            user_savings.setUser_id(resultSet.getInt("user_id"));
            user_savings.setSavings_id(resultSet.getInt("savings_id"));

            users_savingsList.add(user_savings);
        }
    }

    //populate user_custom_goals List with data from user_custom_goals LookUp Table in Database
    //@Params user_custom_goals LinkedList
    private void initializeUserCustomGoals(LinkedList<User_CustomGoals> users_custom_goalsList) throws SQLException{

        //first we clear all data that may be in the list
        users_custom_goalsList.clear();

        preparedStatement = connection.prepareStatement("Select * FROM user_custom_goals");
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){

            User_CustomGoals user_customGoals = new User_CustomGoals();
            user_customGoals.setId(resultSet.getInt("id"));
            user_customGoals.setUser_id(resultSet.getInt("user_id"));
            user_customGoals.setCustom_goals_id(resultSet.getInt("custom_goals_id"));

            users_custom_goalsList.add(user_customGoals);
        }
    }

    //populate user_accountSummary List with data from user_accountSummary LookUp Table in Database
    //@Params user_accountSummary LinkedList
    private void initializeUserAccountSummary(LinkedList<User_AccountSummary> users_accountSummaryList) throws SQLException{

        //first we clear all data that may be in the list
        users_accountSummaryList.clear();

        preparedStatement = connection.prepareStatement("Select * FROM user_accountsummary");
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){

            User_AccountSummary user_accountSummary = new User_AccountSummary();
            user_accountSummary.setId(resultSet.getInt("id"));
            user_accountSummary.setUser_id(resultSet.getInt("user_id"));
            user_accountSummary.setAccountSummary_id(resultSet.getInt("accountSummary_id"));

            users_accountSummaryList.add(user_accountSummary);
        }
    }
}
