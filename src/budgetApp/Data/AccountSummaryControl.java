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
        }
    }

    //add new income to accountSummary
    //@params userId, income Amount, date of payment
    //we note these values would have been validated by AddData Service Method before being passed in here
    public void addIncome(int userId, int amount, String date){

        //variables to hold the month and year of the payment
        int month;
        int year;

        //variables to hold current data in accountSummary for specific month and year
        int accountBalance = 0;
        int totalIncome = 0;

        //these are used only if there is no existing row in the database to match the specified month and year
        int totalExpenses = 0;
        int totalSavings = 0;

        //Convert year of payment to int value
        String yearString = "" + date.charAt(0) + date.charAt(1) + date.charAt(2) + date.charAt(3);
        year = Integer.parseInt(yearString);

        //convert month of payment to int value
        String monthString = "" + date.charAt(5) + date.charAt(6);
        month = Integer.parseInt(monthString);

        //convert received month as int into the name of the Month
        String monthName = giveMonthName(month);

        //DELETE ME
        //System.out.println("month " + monthName + " year: " + year);


        try{

            preparedStatement = connection.prepareStatement("SELECT * FROM accountsummary WHERE accountsummary.month =  " +
                            "\""+ monthName + "\" AND year = " + year);
            resultSet = preparedStatement.executeQuery();

            //need a variable to check if there was row in the database mathcing the specified month and year
            boolean emptyRow = true;


                //at this point we know there is data for this month and year already in the accountSummary
                while (resultSet.next()){

                    //set the emptyRow to false as there is a match in the database
                    emptyRow = false;

                    accountBalance = resultSet.getInt("account_balance");
                    totalIncome = resultSet.getInt("total_income");

                    System.out.println("Acc Bal: " + accountBalance + " tot income: "+ totalIncome);
                }

                //we increment the amount of the accountBalance of total Income if there was a matching row in the database
                if(!emptyRow) {

                    //we add the amount received in income to the total income of the month and the account balance
                    // for that month
                    totalIncome += amount;
                    accountBalance += amount;

                    //then we set these new values in the database
                    preparedStatement = connection.prepareStatement("UPDATE accountsummary SET account_balance = " +
                            accountBalance + " , total_income = " + totalIncome + " WHERE month = \"" + monthName +
                            "\" AND year = " + yearString);
                    preparedStatement.executeUpdate();
                } else {

                    //if we reach this point it means there was no row matching the month and year in the database
                    //and we need to create one

                    preparedStatement = connection.prepareStatement("INSERT INTO accountsummary(account_balance, month, " +
                            "year, total_income, total_expenses, total_savings) VALUES (? , ? , ? , ? , ? , ?)" );
                    preparedStatement.setInt(1, amount);
                    preparedStatement.setString(2, monthName);
                    preparedStatement.setInt(3, year);
                    preparedStatement.setInt(4, amount);
                    preparedStatement.setInt(5, totalExpenses);
                    preparedStatement.setInt(6, totalSavings);
                    preparedStatement.executeUpdate();

                }


        }catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            try{
                resultSet.close();
                preparedStatement.close();
                connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
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
