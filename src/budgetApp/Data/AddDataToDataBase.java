package budgetApp.Data;

import java.sql.*;

public class AddDataToDataBase {

    //Create Connection and PreparedStatement Objects
    Connection connection = null;
    PreparedStatement preparedStatement;

    //resultSet needed for id of last insert to update look-up tables
    ResultSet resultSet;


    //Constructor is used to initialize connection to dataBase each time an object is created
    //We note that each method will close this connection once it has completed its execution
    //Set preparedStatement to Null;
    public AddDataToDataBase() {
        preparedStatement = null;
        resultSet = null;

        try{
            //load the jdbc driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //create connection to dataBase
            connection = DriverManager.getConnection("jdbc:mysql://localhost/javabudgetapp?user=...&password=...&useSSL=false");

        } catch(SQLException e) {
            System.out.println("Could not connect to dataBase");
        } catch(ClassNotFoundException e){
            System.out.println("Driver not on ClassPath");
        }

        //The 'finally' closing of connection and preparedStatement is done by each individual method

    }

    //Method to add new user to database
    //@params Strings user: name, last_name and email;
    //@returns user_id after addition of user for addition to linkedList
    public int addUser(String first_name, String last_name, String email){

        int user_id = 0;

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO users(first_name, last_name, email) VALUES(?, ? ,?)");
            preparedStatement.setString(1, first_name);
            preparedStatement.setString(2, last_name);
            preparedStatement.setString(3, email);
            preparedStatement.executeUpdate();

            //get id of new_user
            preparedStatement = connection.prepareStatement("SELECT * FROM users ORDER BY id DESC LIMIT 1");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                user_id = resultSet.getInt("id");
            }


        } catch(SQLException e){
            System.out.println(e.getMessage());
        } finally {
            try{
                preparedStatement.close();
                connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

        return user_id;
    }

    //Method to add new incomes to database and update user_income table
    //@params user_id source_name, amount, payment_date, payment_interval, notes
    //@returns income_id after addition of income for addition to linkedList
    public int addIncome(int user_id, String source_name, double amount, String payment_date, String payment_interval, String notes){

        //id of new income after it is added for return to add income to linkedList
        int income_id = 0;

        try {

            //insert data into database table income
            preparedStatement = connection.prepareStatement("INSERT INTO income(source_name, amount, payment_date, payment_interval, notes)" +
                    " VALUES(?, ? ,?, ?, ?)");
            preparedStatement.setString(1, source_name);
            preparedStatement.setDouble(2, amount);
            preparedStatement.setString(3, payment_date);
            preparedStatement.setString(4, payment_interval);
            preparedStatement.setString(5, notes);
            preparedStatement.executeUpdate();

            income_id = idOfLastElement("income");

            //update lookup table with user_id and income_id
            preparedStatement = connection.prepareStatement("INSERT INTO user_income(user_id, income_id) VALUES(? , ?)");
            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(2, income_id);
            preparedStatement.executeUpdate();

        } catch(SQLException e){
            System.out.println(e.getMessage());
        } finally {
            try{
                preparedStatement.close();
                connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

        return income_id;
    }

    //Method to add new expenses to database and update user_expense table
    //@params user_id, type_category, paid_to, amount, payment_date, description, notes
    //@returns expense_id after addition of expense for addition to linkedList
    public int addExpenses(int user_id, String type_category, String paid_to,  double amount, String payment_date, String description, String notes){

        //id of new expense after it is added for return to add expense to linkedList
        int expense_id = 0;

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO expenses(type_category, paid_to, amount, payment_date, description, notes)" +
                    " VALUES(?, ? ,?, ?, ? , ?)");
            preparedStatement.setString(1, type_category);
            preparedStatement.setString(2, paid_to);
            preparedStatement.setDouble(3, amount);
            preparedStatement.setString(4, payment_date);
            preparedStatement.setString(5, description);
            preparedStatement.setString(6, notes);
            preparedStatement.executeUpdate();


            //get id from last insert (new expense)
            expense_id = idOfLastElement("expenses");

            //update lookup table with user_id and expenses_id
            preparedStatement = connection.prepareStatement("INSERT INTO user_expenses(user_id, expenses_id) VALUES(? , ?)");
            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(2, expense_id);
            preparedStatement.executeUpdate();

        } catch(SQLException e){
            System.out.println(e.getMessage());
        } finally {
            try{
                preparedStatement.close();
                connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

        return expense_id;
    }


    //Method to add new debt_payments to database and update user_debt_payments table
    //@params user_id type_category, paid_to, amount, payment_date, end_date, total_owed, interest, notes
    //@returns debt_payment_id after addition of debt_payment for addition to linkedList
    public int addDebtExpenses(int user_id, String type_category, String paid_to,  double amount, String payment_date, String end_date, double total_owed
                                , double interest, String notes){

        //id of new debt_payments after it is added for return to add debt_payment to linkedList
        int debt_payments_id = 0;

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO debt_payments(type_category, paid_to, amount, " +
                    "payment_date, end_date, total_owed, interest, notes)" +
                    " VALUES(?, ? ,?, ?, ? , ?, ?, ?)");
            preparedStatement.setString(1, type_category);
            preparedStatement.setString(2, paid_to);
            preparedStatement.setDouble(3, amount);
            preparedStatement.setString(4, payment_date);
            preparedStatement.setString(5, end_date);
            preparedStatement.setDouble(6, total_owed);
            preparedStatement.setDouble(7, interest);
            preparedStatement.setString(8, notes);
            preparedStatement.executeUpdate();

            //get id from last insert (new debt_payment)
            preparedStatement = connection.prepareStatement("SELECT * FROM debt_payments ORDER BY id DESC LIMIT 1");
            resultSet = preparedStatement.executeQuery();

            debt_payments_id = idOfLastElement("debt_payments");

            //update lookup table with user_id and debt_payments_id
            preparedStatement = connection.prepareStatement("INSERT INTO user_debt_payments(user_id, debt_payments_id) " +
                    "VALUES(?,?)");
            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(2, debt_payments_id);
            preparedStatement.executeUpdate();

        } catch(SQLException e){
            System.out.println(e.getMessage());
        } finally {
            try{
                preparedStatement.close();
                connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return debt_payments_id;
    }


    //Method to add new savings to database and update user_savings table
    //@params user_id, saved_location, amount, saved_date, notes
    //@returns savings_id after addition of savings for addition to linkedList
    public int addSavings(int user_id, String saved_location, double amount, String saved_date, String notes){

        int savings_id = 0;

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO savings(saved_location, amount, " +
                    "saved_date, notes)" +
                    " VALUES(?, ? ,?, ?)");
            preparedStatement.setString(1, saved_location);
            preparedStatement.setDouble(2, amount);
            preparedStatement.setString(3, saved_date);
            preparedStatement.setString(4, notes);
            preparedStatement.executeUpdate();


            //get id from last insert (new savings)
            savings_id = idOfLastElement("savings");

            //update lookup table with user_id and savings_id
            preparedStatement = connection.prepareStatement("INSERT INTO user_savings(user_id, savings_id) VALUES(? , ?)");
            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(2, savings_id);
            preparedStatement.executeUpdate();

        } catch(SQLException e){
            System.out.println(e.getMessage());
        } finally {
            try{
                preparedStatement.close();
                connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

        return savings_id;
    }


    //Method to add new custom_goal to database and update user_custom_goals table
    //@params user_id, description, saved_location, amount, saved_date, total_desired, final_date, notes
    //@returns custom_goal_id after addition of custom_goal for addition to linkedList
    public int addCustomGoal(int user_id, String description, String saved_location,  double amount, String saved_date, double total_desired,
                              String final_date, String notes){

        int custom_goals_id = 0;

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO custom_goals(description, saved_location, amount, saved_date, " +
                    "total_desired, final_date, notes)" +
                    " VALUES(?, ? ,?, ?, ?, ?, ?)");
            preparedStatement.setString(1, description);
            preparedStatement.setString(2, saved_location);
            preparedStatement.setDouble(3, amount);
            preparedStatement.setString(4, saved_date);
            preparedStatement.setDouble(5, total_desired);
            preparedStatement.setString(6, final_date);
            preparedStatement.setString(7, notes);
            preparedStatement.executeUpdate();

            custom_goals_id = idOfLastElement("custom_goals");

            //update lookup table with user_id and savings_id
            preparedStatement = connection.prepareStatement("INSERT INTO user_custom_goals(user_id, custom_goals_id) VALUES(? , ?)");
            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(2, custom_goals_id);
            preparedStatement.executeUpdate();

        } catch(SQLException e){
            System.out.println(e.getMessage());
        } finally {
            try{
                preparedStatement.close();
                connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

        return custom_goals_id;
    }


    //method to return id of last element in table
    //@param name of table in data_base
    //@return int id of last element in table
    public int idOfLastElement(String tableName){

        int id = 0;

        try{
            //get id from last element in specified table
            preparedStatement = connection.prepareStatement("SELECT * FROM " + tableName + " ORDER BY id DESC LIMIT 1");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                id = resultSet.getInt("id");
            }
        }catch(SQLException exc){
            System.out.println(exc.getMessage());
        }
        return id;
    }
}
