package budgetApp.Data;

import java.sql.*;

//class responsible for adding/changing/removing data in Account Summary Table and User_AccountSummaryLookUp Table
public class AccountSummaryControl {

    Connection connection = null;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    //Constructor to initialize connection, preparedStatement and ResultSet

    public AccountSummaryControl(){
        try{

            //load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //create connection to dataBase
            connection = DriverManager.getConnection("jdbc:mysql://localhost/javabudgetapp?user=...&password=...&useSSL=false");

            preparedStatement = null;
            resultSet = null;

        }catch(SQLException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            System.out.println("Driver not on ClassPath");
        }finally {
            try{
                resultSet.close();
                preparedStatement.close();
                connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    //add new income to accountSummary
    //@params userId, income Amount, date of payment
    //we note these values would have been validated by AddData Service Method before being passed in here
    public void addIncome(int userId, int amount, String date){

        //variables to hold the month and year of the payment
        int month;
        int year;

        //Convert month of payment to int value
        String monthString = "" + date.charAt(5) + date.charAt(6);
        month = Integer.parseInt(monthString);

        //Convert year of payment to int value
        String yearString = "" + date.charAt(0) + date.charAt(1) + date.charAt(2) + date.charAt(3);
        year = Integer.parseInt(yearString);

        System.out.println("month " + month + " year: " + year);


//        try{
//
//
//        }catch(SQLException e){
//            e.printStackTrace();
//        }finally {
//            try{
//                resultSet.close();
//                preparedStatement.close();
//                connection.close();
//            }catch(SQLException e){
//                e.printStackTrace();
//            }
//        }

    }



}
