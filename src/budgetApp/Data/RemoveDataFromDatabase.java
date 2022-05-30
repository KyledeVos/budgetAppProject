package budgetApp.Data;

import java.sql.*;

public class RemoveDataFromDatabase {

    //Create Connection, PreparedStatement and ResultSet Objects
    Connection connection = null;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    //constructor used to initialize connection
    //closing of connection, preparedStatement and resultSet done by update and remove methods
    //search method is a helper method to these and does not close the connection, preparedStatement or resultSet
    public RemoveDataFromDatabase(){

        try{

            //load the jdbc driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //create connection to dataBase
            connection = DriverManager.getConnection("jdbc:mysql://localhost/javabudgetapp?user=apptest&password=fake&useSSL=false");

            preparedStatement = null;
            resultSet = null;

        }catch(SQLException e){
            System.out.println("may have not been able to connect to database");
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            System.out.println("Driver not on ClassPath");
        }

    }

    //remove a user from the database
    //@params id of user, initialize Database object to alter data in LinkedLists
    //@return true if user can be removed, false if user not found in database
    public boolean removeUser(int userId, int choice){

        int affectedRows = 1;

        //first check if desired user_id is in database users
        if(!search(choice, "users")){
            return false;
        }

        //if the chosen option does not equal the user's own ID, then the removal cannot be done
        //as users can only delete their own data and no one else's
        if(choice != userId){
            System.out.println("Action Refused. You cannot delete another uses");
            return false;
        }

        //at this point we know the user does exist and that they are deleting themselves and no one else

        try {

            //fist we want to use all look-up tables to delete all data associated with a user
            clearUserData(choice);


            //once all data associated with user has been deleted, we can delete the user
            preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id =  " + choice);
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

        //confirm only one row was removed
        if(affectedRows == 1){
            return true;
        }
        return false;
    }

    //remove row from desired table
    //@param id of row
    //@param table_name in database
    //@return false if id does not exist in table, return true if one or more rows was affected
    //to show removal, false if none were affected
    public boolean removeEntry(int userId, int id, String table_name){
        if(!search(userId, id, table_name)){
            System.out.println("Matching ID not found in database");
            return false;
        }

        int affectedRows = 0;

        try {
            //at this point we know the id does exist in the desired table and can simply remove it
            preparedStatement = connection.prepareStatement("DELETE FROM " + table_name + " WHERE id = " + id);
            affectedRows = preparedStatement.executeUpdate();
        }catch (SQLException e){
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

        //if more than one row was effected (removed), return true
        if(affectedRows > 0){
            return true;
        }else {
            //no rows were affected meaning removal not done
            return false;
        }

    }

    //HELPER METHOD
    //used to search for user entry in database
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

    //HELPER METHOD
    //used to search for data entry in database
    //@param id of user, id of element in linkedList, name of table to search in
    //@return true if row with matching id is found, false if not
    private boolean search(int userId, int id, String table_name){

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM " + table_name + " JOIN user_" + table_name +
                    " ON user_" + table_name + "." + table_name + "_id = " + table_name + ".id JOIN users " +
            "ON user_" + table_name + ".user_id = users.id WHERE user_id = " + userId);
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


    //HELPER METHOD to removeUser()
    //Extra Method that clears user linked data from all tables (income, expenses, etc)
    //if user is deleted, then all data affiliated with that user in all tables must be deleted too.
    //this includes all data in lookUp Tables
    //@params user_id
    private void clearUserData(int user_id){

        try {
            //delete incomes associated with user
            preparedStatement = connection.prepareStatement("DELETE FROM income WHERE id IN(SELECT income_id FROM user_income" +
                    " WHERE user_income.user_id = " + user_id + ")");
            preparedStatement.executeUpdate();

            //delete expenses associated with user
            preparedStatement = connection.prepareStatement("DELETE FROM expenses WHERE id IN(SELECT expenses_id FROM " +
                    "user_expenses WHERE user_expenses.user_id = " + user_id + ")");
            preparedStatement.executeUpdate();

            //delete debt_payments associated with user
            preparedStatement = connection.prepareStatement("DELETE FROM debt_payments WHERE id IN(SELECT debt_payments_id FROM " +
                    "user_debt_payments WHERE user_debt_payments.user_id = " + user_id + ")");
            preparedStatement.executeUpdate();

            //delete savings associated with user
            preparedStatement = connection.prepareStatement("DELETE FROM savings WHERE id IN(SELECT savings_id FROM " +
                    "user_savings WHERE user_savings.user_id = " + user_id + ")");
            preparedStatement.executeUpdate();

            //delete custom_goals associated with user
            preparedStatement = connection.prepareStatement("DELETE FROM custom_goals WHERE id IN(SELECT custom_goals_id " +
                    "FROM user_custom_goals WHERE user_custom_goals.user_id = " + user_id + ")");
            preparedStatement.executeUpdate();

            //delete accountSummaries associated with user
            preparedStatement = connection.prepareStatement("DELETE FROM accountsummary WHERE id IN(SELECT accountSummary_id " +
                    "FROM user_accountsummary WHERE user_accountsummary.user_id = " + user_id + ")");
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }



}
