package budgetApp.Data;

import budgetApp.Model.*;

import java.sql.*;
import java.util.LinkedList;

public class InitializeData {


    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;


    public void initializeFromDatabase(LinkedList<Users> userList, LinkedList<Income> incomes, LinkedList<Expenses> expenses
                                       , LinkedList<DebtPayments> debtPayments, LinkedList<Savings> savings,
                                       LinkedList<CustomGoals> customGoals, LinkedList<AccountSummary> accountSummaries,
                                       LinkedList<Users_Income> users_incomes, LinkedList<User_Expenses> user_expenses,
                                       LinkedList<User_DebtPayments> user_debtPayments, LinkedList<User_Savings> user_savings,
                                       LinkedList<User_CustomGoals> user_customGoals, LinkedList<User_AccountSummary> user_accountSummaries){

        try{

            //load the jdbc Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost/javabudgetapp?user=...&password=...&useSSL=false");

            initializeUsers(userList);
            initializeIncomes(incomes);
            initializeExpenses(expenses);
            initializeDebtPayments(debtPayments);
            initializeSavings(savings);
            initializeCustomGoals(customGoals);
            initializeAccountSummary(accountSummaries);
            initializeUserIncome(users_incomes);
            initializeUserExpenses(user_expenses);
            initializeUserDebtPayments(user_debtPayments);
            initializeUserSavings(user_savings);
            initializeUserCustomGoals(user_customGoals);
            initializeUserAccountSummary(user_accountSummaries);


        }catch(SQLException exc){
            System.out.println("Sql Exception Occured");
        } catch(ClassNotFoundException exc){
            System.out.println("Driver class not on ClassPath");
        } finally {
            try{
                preparedStatement.close();
                resultSet.close();
                connection.close();
            }catch(SQLException exc){
                System.out.println("unable to close");
            }
        }


    }

    //populate user List with data from User Table in Database
    //@Params User LinkedList
    private void initializeUsers(LinkedList<Users> userList) throws SQLException{

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

        preparedStatement = connection.prepareStatement("Select * FROM income");
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

        preparedStatement = connection.prepareStatement("Select * FROM expenses");
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
    private void initializeDebtPayments(LinkedList<DebtPayments> debtPaymentsList) throws SQLException{

        preparedStatement = connection.prepareStatement("Select * FROM debt_payments");
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

        preparedStatement = connection.prepareStatement("Select * FROM savings");
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

        preparedStatement = connection.prepareStatement("Select * FROM custom_goals");
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

        preparedStatement = connection.prepareStatement("Select * FROM accountsummary");
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){

            AccountSummary accountSummary = new AccountSummary();
            accountSummary.setId(resultSet.getInt("id"));
            accountSummary.setAccount_balance(resultSet.getDouble("account_balance"));
            accountSummary.setMonth(resultSet.getString("month"));
            accountSummary.setYear(resultSet.getString("year"));
            accountSummary.setTotal_income(resultSet.getDouble("total_income"));
            accountSummary.setTotal_expenses(resultSet.getDouble("total_expenses"));
            accountSummary.setTotal_savings(resultSet.getDouble("total_savings"));
            accountSummariesList.add(accountSummary);
        }
    }

    //populate user_income List with data from user_income LookUp Table in Database
    //@Params user_income LinkedList
    private void initializeUserIncome(LinkedList<Users_Income> users_incomesList) throws SQLException{

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
