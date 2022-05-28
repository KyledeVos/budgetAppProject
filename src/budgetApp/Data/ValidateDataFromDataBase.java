package budgetApp.Data;

import java.sql.*;
import java.util.Scanner;

public class ValidateDataFromDataBase {

    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public ValidateDataFromDataBase(){
        //Create Connection, PreparedStatement and ResultSet Objects
        preparedStatement = null;
        resultSet = null;

        //constructor used to initialize connection
        try{

            //load the jdbc driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //create connection to dataBase
            connection = DriverManager.getConnection("jdbc:mysql://localhost/javabudgetapp?user=...&password=...&useSSL=false");

            preparedStatement = null;
            resultSet = null;

        }catch(SQLException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            System.out.println("Driver not on ClassPath");
            e.printStackTrace();
        }

    }

    //method to check if there are  any user's in the database
    //@ return true if user table is empty, false if it is not empty
    public boolean emptyUserTable(){

        try{

            //check if table is empty, return false if it is not
            preparedStatement = connection.prepareStatement("SELECT * FROM users LIMIT 1");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){

                //if there is data in the result set then this while loop will run once making
                //the method return false

                //false means the table is not empty, and we do not close the connection as
                //we want to call the validateUser method in the AppController Class using the same ValidateData
                //object requiring an open connection
                return false;
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        //true means the table is empty,
        //We can close the connection as we do not have any users
        //to validate (select) in the AppController Class, meaning we do not need an open connection to
        //use the validateUser() below

        try {
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    //method to check if a user's name(input from console) matches a user first_name in database
    //a list of users will be displayed if incorrect input is received
    //@param Scanner Object
    //@return id of user in database
    public int validateUser(Scanner scanner){

        //variable to loop if invalid input is received
        boolean loop = true;

        //user_id to return to calling method
        int user_id = 0;

        while(loop) {

            System.out.println("Please enter name of user");
            String input = scanner.nextLine();

            try {
                preparedStatement = connection.prepareStatement("SELECT * FROM users");
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {

                    String name = resultSet.getString("first_name");

                    if(input.equals(name)){
                        loop = false;
                        user_id = resultSet.getInt("id");

                        try{
                            preparedStatement.close();
                            resultSet.close();
                            connection.close();
                        }catch(SQLException e){
                            e.printStackTrace();
                        }

                        return user_id;
                    }
                }

                //at this point it means no user was found to match given name
                System.out.println("No user with matching name has been found\n");
                System.out.println("List of users in database");

                preparedStatement = connection.prepareStatement("SELECT * FROM users");
                resultSet = preparedStatement.executeQuery();

                while(resultSet.next()){
                    System.out.println("User Name: " + resultSet.getString("first_name"));
                }



            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user_id;
    }
}
