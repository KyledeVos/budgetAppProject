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
    public void addUser(String first_name, String last_name, String email){
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO users(first_name, last_name, email) VALUES(?, ? ,?)");
            preparedStatement.setString(1, first_name);
            preparedStatement.setString(2, last_name);
            preparedStatement.setString(3, email);
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
    }

    //Method to add new incomes to database and update user_income table
    //@params user_id source_name, amount, payment_date, payment_interval, notes
    public void addIncome(int user_id, String source_name, double amount, String payment_date, String payment_interval, String notes){
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

            int income_id = 0;

            //get id from last insert (new income)
            preparedStatement = connection.prepareStatement("SELECT * FROM income ORDER BY id DESC LIMIT 1");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                income_id = resultSet.getInt("id");
            }

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
    }

    //Method to add new expenses to database
    //@params type_category, paid_to, amount, payment_date, description, notes
    public void addExpenses(String type_category, String paid_to,  double amount, String payment_date, String description, String notes){
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
    }


    //Method to add new debt_payments to database
    //@params type_category, paid_to, amount, payment_date, end_date, total_owed, interest, notes
    public void addDebtExpenses(String type_category, String paid_to,  double amount, String payment_date, String end_date, double total_owed
                                , double interest, String notes){
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
    }


    //Method to add new savings to database
    //@params saved_location, amount, saved_date, notes
    public void addSavings(String saved_location, double amount, String saved_date, String notes){
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO savings(saved_location, amount, " +
                    "saved_date, notes)" +
                    " VALUES(?, ? ,?, ?)");
            preparedStatement.setString(1, saved_location);
            preparedStatement.setDouble(2, amount);
            preparedStatement.setString(3, saved_date);
            preparedStatement.setString(4, notes);
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
    }


    //Method to add new custom_goal to database
    //@params description, saved_location, amount, saved_date, total_desired, final_date, notes
    public void addCustomGoal(String description, String saved_location,  double amount, String saved_date, double total_desired,
                              String final_date, String notes){
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
    }
}
