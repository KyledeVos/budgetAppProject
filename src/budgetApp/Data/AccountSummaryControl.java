package budgetApp.Data;

import java.sql.*;

//class responsible for getting amount information for account Summaries from database
public class AccountSummaryControl {

    Connection connection = null;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    //id of user
    int userId;


    //Constructor to initialize connection, preparedStatement and ResultSet and userId
    public AccountSummaryControl(int userId){

        this.userId = userId;

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
        }
    }

    //used to calculate the average amount in a table
    //@param userId, Name of table in Database
    //@return average amount
    public double averageAmountForTable(int userId, String tableName){

        double average = 0;

        try{

            //calculate and get average amount from database
            preparedStatement = connection.prepareStatement("SELECT AVG(amount) FROM " + tableName + " JOIN user_" + tableName +
                    " on" + " user_" + tableName + "." + tableName + "_id = " + tableName + ".id JOIN users on user_" +
                    tableName + ".user_id = users.id WHERE users.id = " + userId);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                average = resultSet.getDouble(1);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return  average;
    }


    //used to return the sum of an amount column in a table for a specific user
    //@param userId, Name of table in database
    //@return total for amount column
    public double totalAmountForTable(int userId, String tableName) {

        double total = 0;

        try {
            preparedStatement = connection.prepareStatement("SELECT SUM(amount) FROM " + tableName + " JOIN user_" + tableName +
                    " on" + " user_" + tableName + "." + tableName + "_id = " + tableName + ".id JOIN users on user_" +
                    tableName + ".user_id = users.id WHERE users.id = " + userId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                total = resultSet.getDouble(1);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

    return total;

    }

    //method to close connections once service method run is complete
    public void closeConnection(){
        try{
            preparedStatement.close();
            resultSet.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //HELPER METHOD
    //used to convert a month as an int into the name of the month
    //@param number of month
    //@return month name
    private String giveMonthName(int month){

        //String to hold name of month
        String monthName;

        //switch through month numbers to determine name of Month
        switch(month){
            case 1:
                monthName = "January";
                break;

            case 2:
                monthName = "February";
                break;

            case 3:
                monthName = "March";
                break;

            case 4:
                monthName = "April";
                break;

            case 5:
                monthName = "May";
                break;

            case 6:
                monthName = "June";
                break;

            case 7:
                monthName = "July";
                break;

            case 8:
                monthName = "August";
                break;

            case 9:
                monthName = "September";
                break;

            case 10:
                monthName = "October";
                break;

            case 11:
                monthName = "November";
                break;

            case 12:
                monthName = "December";
                break;

            default:
                System.out.println("Invalid Month");
                monthName = "Invalid";
                break;
        }

        return monthName;

    }

}
