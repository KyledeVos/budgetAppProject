package budgetApp.Data;

import java.sql.*;

public class DatabaseConnector {

    public static void main(String[] args){

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{

            //load the jdbc Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost/javabudgetapp?user=...&password=...&useSSL=false");

            preparedStatement = connection.prepareStatement("DELETE from users WHERE id = 3");
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("Select * FROM users");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String first_Name = resultSet.getString("first_name");
                String last_Name = resultSet.getString("last_name");
                String email = resultSet.getString("email");

                System.out.println("id: " + id + " - " + first_Name + " " + last_Name + " email:" + email);
            }
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



}
