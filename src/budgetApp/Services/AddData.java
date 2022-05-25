package budgetApp.Services;

import budgetApp.Data.AddDataToDataBase;

import java.util.Scanner;

public class AddData {

    //add a new user to the database using AddDataToDataBase Class
    //return id of new User
    public int addNewUser(Scanner scanner){

        //AddDataToDataBase object to add new user to database
        AddDataToDataBase addDataToDataBase = new AddDataToDataBase();

        //user information to be passed to addUser Method
        String firstName;
        String lastName;
        String email;

        //userId of new user to return to calling method
        int userId;

        System.out.println("---------------------");
        System.out.println("Addition of new User");

        System.out.println("Please enter first name of new user:");
        firstName = scanner.nextLine();

        System.out.println("\nPlease enter last name of new user");
        lastName = scanner.nextLine();

        System.out.println("\nPlease enter email address of new user");
        email = scanner.nextLine();

        userId = addDataToDataBase.addUser(firstName, lastName, email);

        System.out.println("\nUser: " + firstName + " " + lastName + " with email: " + email + " has been added to database");
        System.out.println("---------------------");

        return userId;
    }

    //add a new income to the database using AddDataToDataBase Class
    //@param user_id , Scanner object
    public void addNewIncome(int userId, Scanner scanner){

        //AddDataToDataBase object to add new user to database
        AddDataToDataBase addDataToDataBase = new AddDataToDataBase();

        //income information to be passed to
        String sourceName;
        double amount = 0;
        String paymentDate;
        String paymentInterval;
        String notes = "";

        //no restrictions on source name
        System.out.println("---------------------");
        System.out.println("Addition of new Income\n");
        System.out.println("Please enter the source name for income eg) Salary, gift, extra work, etc.");
        sourceName = scanner.nextLine();
        System.out.println();

        //AMOUNT
        //--------------------------
        //for addition of amount we must make sure the user has entered a numerical value
        boolean loop = true;
        System.out.println();
        while(loop){

            System.out.println("Please enter amount. For decimals please use a comma \',\':");
            boolean hasDouble = scanner.hasNextDouble();


            if(hasDouble){
                amount = scanner.nextDouble();
                loop = false;
            } else {
                System.out.println("Invalid Input Received");
            }
            scanner.nextLine();
        }

        //Date
        //--------------------------
        //addition of paymentDate must be validated to ensure date is in correct format
        System.out.println("\nDate of Payment");
        paymentDate = createAndValidateDate(scanner);

        //Payment Interval
        //--------------------------
        System.out.println("\nPlease enter payment interval. eg) monthly, weekly, once-off, etc.");
        paymentInterval = scanner.nextLine();

        //Additional Notes
        //--------------------------
        System.out.println("\nPlease enter any additional notes to accompany this income.");
        System.out.println("If you do not wish to add any, please enter \'0\'");
        String hold = scanner.nextLine();
        if(!hold.equals("0")){
            notes = hold;
        }

        //Call AddIncome method in to add new income to database
        addDataToDataBase.addIncome(userId, sourceName, amount, paymentDate,
                paymentInterval, notes);

    }

    //HELPER METHOD
    //check if an entered date is valid
    //@param Scanner object
    //@return valid date in String format
    private String createAndValidateDate(Scanner scanner){

        //variables to store date
        int year = 100;
        int month = 1;
        int day =1 ;

        //create loop variable
        boolean loop = true;

        //Get Year
        while(loop){

            System.out.println("Please enter Year of Payment");
            boolean hasInt = scanner.hasNextInt();

            if(hasInt){
                year = scanner.nextInt();

                if(year > 1800) {
                    loop = false;
                }else{
                    System.out.println("Invalid Input Received. Year cannot be smaller than 1800 for App");
                }
            } else {
                System.out.println("Invalid Input Received");
            }
            scanner.nextLine();
        }

        //Get Month
        //reset loop
        loop = true;

        while(loop){

            System.out.println("Please enter Month of Payment as 1 - 12");
            boolean hasInt = scanner.hasNextInt();

            if(hasInt){
                month = scanner.nextInt();

                if(month >= 1 && month <=12) {
                    loop = false;
                }else{
                    System.out.println("Invalid Input Received. Month must be a value between 1 and 12");
                }
            } else {
                System.out.println("Invalid Input Received");
            }
            scanner.nextLine();
        }

        //Get and Validate Day
        //checks if day corresponds to specific month
        //in the case of february, checks that day of 29 only occurs on a leap year

        //check if it is a leap year
        boolean leapYear;

        if(((year% 4 == 0) && (year%100 != 0)) || (year% 400 == 0)){
            //year given is a leapYear
            leapYear = true;
        } else{
            //year given is not a leapYear
            leapYear = false;
        }

        //reset loop
        loop = true;

        //get day from user and validate it
        while(loop){

            System.out.println("Please enter valid day of month in format 1 - 31");
            boolean hasInt = scanner.hasNextInt();

            //if the data entered is an integer
            if(hasInt){
                day = scanner.nextInt();

                //1) check that day is within possible max and min
                if(day> 0 && day<=31){

                    //2) check day against possible days of month
                    if(month == 4 || month == 6 || month == 9 || month == 11){
                        if(day<=30){
                            //day is valid
                            loop = false;
                        } else {
                            System.out.println("Day not possible. Please enter valid day");
                        }
                    } else if(month == 2){
                        //if month is february

                        if(leapYear){
                            //if leap year then max days in feb is 29
                            if(day <=29) {
                                loop = false;
                            }else {
                                System.out.println("Day not possible. Please enter valid day");
                            }
                        } else{
                                //here we know it is not a leap year
                                if(day<=28){
                                    loop = false;
                                }else {
                                    System.out.println("Day not possible. Please enter valid day");
                                }
                            }
                    } else{
                        //at this point we know the month can only be one with 31 days
                        //which was validated in the beginning
                        loop = false;
                    }

                } else{
                    System.out.println("Day not possible. Please enter valid day");
                }
            } else{
                System.out.println("Invalid Input. Please enter valid day of month in format 1 - 31");
            }
            scanner.nextLine();

        }

        //if month is smaller than 10 we need a zero in front of it for correct date format
        String monthString;
        if(month<10){
            monthString = "0" + month;
        }else {
            monthString = "" + month;
        }

        //if day is smaller than 10 we need a zero in front of it for correct date format
        String dayString;
        if(day<10){
            dayString = "0" + day;
        } else{
            dayString ="" +  day;
        }

        return year + "-" + monthString + "-" + dayString;

    }


}













