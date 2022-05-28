package budgetApp.Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

public class UpdateData {

    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;


    public UpdateData() {
        this.connection = null;
        this.preparedStatement = null;
        resultSet = null;


        try{

            //load the jdbc Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //create Connection to DataBase
            connection = DriverManager.getConnection("jdbc:mysql://localhost/javabudgetapp?user=...&password=...&useSSL=false");

        }catch(SQLException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            System.out.println("Driver not on ClassPath");
        }
    }

    //update an existing user
    //@params id of user, first_name, last_name, email
    //@return true if row changed, false if not changed or id could not be found
    public boolean updateUser(int id, String first_name, String last_name, String email){

        if(!search(id, "users")){
            return false;
        }

        //at this point we know the user does exist

        //variable to confirm if change was made to row
        int changedRows = 0;

        try{
            preparedStatement = connection.prepareStatement("UPDATE users set first_name = ?, last_name = ?, " +
                    "email = ? WHERE id = " + id);
            preparedStatement.setString(1, first_name);
            preparedStatement.setString(2, last_name);
            preparedStatement.setString(3, email);
            changedRows = preparedStatement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        } finally{
            try{
                preparedStatement.close();
                resultSet.close();
                connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

        if(changedRows == 1){
            return true;
        } else{
            return false;
        }

    }

    //update an existing income
    //@params id of income, source_name, amount, payment_date, payment_interval, notes
    //@return true if row changed, false if not changed or id could not be found
    public boolean updateIncome(int id, String source_name, double amount, String payment_date,
                                String payment_interval, String notes){

        //first check that income exists in database
        if(!search(id, "income")){
            return false;
        }

        //at this point we know the income does exist

        //variable to hold number of changed rows
        int affectedRows = 0;

        try {
            preparedStatement = connection.prepareStatement("UPDATE income SET source_name = ?, amount = ?, payment_date = ? " +
                    ", payment_interval = ?, notes = ? WHERE id = " + id);
            preparedStatement.setString(1, source_name);
            preparedStatement.setDouble(2, amount);
            preparedStatement.setString(3, payment_date);
            preparedStatement.setString(4, payment_interval);
            preparedStatement.setString(5, notes);
            affectedRows = preparedStatement.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            try{
                preparedStatement.close();
                resultSet.close();
                connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

        if (affectedRows == 1){
            return true;
        } return false;
    }

    //update an existing expense
    //@params id of expense, type_category, paid_to, amount, payment_date, description, notes
    //@return true if row changed, false if not changed or id could not be found
    public boolean updateExpense(int id, String type_category, String paid_to, double amount, String payment_date,
                                 String description, String notes){

        //first check if expense id exists in database
        if(!search(id, "expenses")){
            return false;
        }

        //at this point we know the expense does exist

        //variable to hold number of changed rows
        int affectedRows = 0;

        try{

            preparedStatement = connection.prepareStatement("UPDATE expenses SET type_category = ?, paid_to = ?, amount = ?, " +
                    "payment_date = ?, description = ?, notes = ? WHERE id = " + id);
            preparedStatement.setString(1,type_category);
            preparedStatement.setString(2, paid_to);
            preparedStatement.setDouble(3, amount);
            preparedStatement.setString(4, payment_date);
            preparedStatement.setString(5, description);
            preparedStatement.setString(6, notes);
            affectedRows = preparedStatement.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                preparedStatement.close();
                resultSet.close();
                connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

        if(affectedRows == 1){
            return true;
        }
        return false;

    }

    //update an existing debt_payment
    //@params id of debt_payment, type_category, paid_to, amount, payment_date, end_date, total_owed, interest, notes
    //@return true if row changed, false if not changed or id could not be found
    public boolean updateDebtPayment(int id, String type_category, String paid_to, double amount, String payment_date,
                                     String end_date, double total_owed, double interest, String notes){

        //check if debt_payment with matching id exists in database
        if(!search(id,"debt_payments")){
            return false;
        }

        //at this point we know the debt_payment does exist

        //variable to hold number of changed rows
        int affectedRows = 0;

        try{

            preparedStatement = connection.prepareStatement("UPDATE debt_payments SET type_category = ?, paid_to = ?, " +
                    "amount = ?, payment_date = ?, end_date = ?, total_owed = ?,interest = ?, notes = ? WHERE id = " + id);
            preparedStatement.setString(1, type_category);
            preparedStatement.setString(2, paid_to);
            preparedStatement.setDouble(3, amount);
            preparedStatement.setString(4, payment_date);
            preparedStatement.setString(5, end_date);
            preparedStatement.setDouble(6, total_owed);
            preparedStatement.setDouble(7, interest);
            preparedStatement.setString(8, notes);
            affectedRows = preparedStatement.executeUpdate();

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

        if(affectedRows == 1){
            return true;
        }
         return false;
    }

    //update an existing savings
    //@params id of saved_location, amount, saved_date, notes
    //@return true if row changed, false if not changed or id could not be found

    public boolean updateSavings(int id, String saved_location, double amount, String saved_date, String notes){

        //check if savings with matching id exists in database
        if(!search(id,"savings")){
            return false;
        }

        //at this point we know the savings does exist

        //variable to hold number of changed rows
        int affectedRows = 0;

        try{

            preparedStatement = connection.prepareStatement("UPDATE savings SET saved_location = ?, amount = ?, " +
                    "saved_date = ?, notes = ? WHERE id = " + id);
            preparedStatement.setString(1, saved_location);
            preparedStatement.setDouble(2, amount);
            preparedStatement.setString(3, saved_date);
            preparedStatement.setString(4, notes);
            affectedRows = preparedStatement.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                resultSet.close();
                preparedStatement.close();
                connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

        if(affectedRows == 1){
            return true;
        }
        return false;
    }

    //update an existing custom_goal
    //@params id of custom_goal, description, saved_location, amount, saved_date, total_desired, final_date, notes
    //@return true if row changed, false if not changed or id could not be found

    public boolean updateCustomGoal(int id, String description, String saved_location, double amount, String saved_date,
                                    double total_desired, String final_date, String notes){

        //check if custom_goal with matching id exists in database
        if(!search(id,"custom_goals")){
            return false;
        }

        //at this point we know the custom_goal does exist

        //variable to hold number of changed rows
        int affectedRows = 0;

        try{

            preparedStatement = connection.prepareStatement("UPDATE custom_goals SET description = ?, saved_location = ? , " +
                    "amount = ?, saved_date = ?, total_desired = ?, final_date = ?, notes = ? WHERE id = " + id);
            preparedStatement.setString(1, description);
            preparedStatement.setString(2, saved_location);
            preparedStatement.setDouble(3, amount);
            preparedStatement.setString(4, saved_date);
            preparedStatement.setDouble(5, total_desired);
            preparedStatement.setString(6, final_date);
            preparedStatement.setString(7, notes);
            affectedRows = preparedStatement.executeUpdate();


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

        if(affectedRows == 1){
            return true;
        }
        return false;

    }

    //HELPER METHOD
    //used to search for data entry in database
    //@param id of element in linkedList, name of table to search in
    //@return true if row with matching id is found, false if not
    private boolean search(int id, String table_name){

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM " + table_name);
            resultSet = preparedStatement.executeQuery();

            //iterate through all rows to see if matching ID is found
            while(resultSet.next()){
                int row_id = resultSet.getInt("id");

                if(row_id == id){
                    return true;
                }
            }

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        //at this point no matching ID was found and false is returned
        return false;

    }
}
