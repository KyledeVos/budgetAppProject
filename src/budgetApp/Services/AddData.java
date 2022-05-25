package budgetApp.Services;

import budgetApp.Data.AddDataToDataBase;

import java.util.Scanner;

public class AddData {

    //object that checks user input to validate for addition to database
    ValidateUserInput validateUserInput;

    public AddData(Scanner scanner) {
        validateUserInput = new ValidateUserInput(scanner);
    }

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

        //AddDataToDataBase object to add new income to database
        AddDataToDataBase addDataToDataBase = new AddDataToDataBase();

        //income information to be passed to database
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
        amount = validateUserInput.getAmountValue("Please enter amount. For decimals please use a comma \',\':");

        //Date
        //--------------------------
        //addition of paymentDate must be validated to ensure date is in correct format
        System.out.println("\nDate of Payment");
        paymentDate = validateUserInput.createAndValidateDate();

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


    //add a new expense to the database using AddDataToDataBase Class
    //@param user_id, Scanner object
    public void addNewExpense(int userId, Scanner scanner){

        //AddDataToDataBase object to add new expense to database
        AddDataToDataBase addDataToDataBase = new AddDataToDataBase();

        //expense information to be passed to database
        String typeCategory;
        String paidTo;
        double amount = 0;
        String paymentDate;
        String description = "";
        String notes = "";

        //no restrictions on type_category
        System.out.println("---------------------");
        System.out.println("Addition of new Expense\n");
        System.out.println("Please enter the Type or Category for expense eg) petrol, dinner, groceries, etc.");
        typeCategory = scanner.nextLine();
        System.out.println();

        //no restrictions on paid to
        System.out.println("\nPlease enter the name of the person or business this is paid to");
        paidTo = scanner.nextLine();
        System.out.println();

        //AMOUNT
        //--------------------------
        amount = validateUserInput.getAmountValue("Please enter amount. For decimals please use a comma ',':");

        //paymentDate
        //--------------------------
        //addition of paymentDate must be validated to ensure date is in correct format
        System.out.println("\nDate of Payment");
        paymentDate = validateUserInput.createAndValidateDate();

        //Description - optional
        //--------------------------
        System.out.println("\nPlease enter a description of the expense if you wish to.");
        System.out.println("If you do not wish to add anything, please enter \'0\'");
        String hold = scanner.nextLine();
        if(!hold.equals("0")){
            description = hold;
        }

        //Additional Notes
        //--------------------------
        System.out.println("\nPlease enter any additional notes to accompany this expense.");
        System.out.println("If you do not wish to add any, please enter \'0\'");
        hold = scanner.nextLine();
        if(!hold.equals("0")){
            notes = hold;
        }

        //Call AddExpense method in to add new expense to database
        addDataToDataBase.addExpenses(userId, typeCategory, paidTo, amount,
                paymentDate, description, notes);

    }

    //add a new debt_payment to the database using AddDataToDataBase Class
    //@param user_id , Scanner object
    public void addDebtExpense(int userId, Scanner scanner){

        //AddDataToDataBase object to add new debtPayment to database
        AddDataToDataBase addDataToDataBase = new AddDataToDataBase();

        //debtPayment information to be passed to database
        String typeCategory;
        String paidTo;
        double amount = 0;
        String paymentDate;
        String endDate;
        double totalOwed = 0;
        double interest = 0;
        String notes = "";


        System.out.println("---------------------");
        System.out.println("Addition of new Debt Payment\n");

        //no restrictions on type_category
        System.out.println("Please enter the Type or Category for Debt Payment eg) mortgage, vehicle payment, account, etc.");
        typeCategory = scanner.nextLine();
        System.out.println();

        //no restrictions on paid to
        System.out.println("\nPlease enter the name of the person or business this is paid to");
        paidTo = scanner.nextLine();
        System.out.println();


        //AMOUNT
        //--------------------------
        //for addition of amount we must make sure the user has entered a numerical value
        amount = validateUserInput.getAmountValue("Please enter amount. For decimals please use a comma \',\':");

        //paymentDate
        //--------------------------
        //addition of paymentDate must be validated to ensure date is in correct format
        System.out.println("\nDate of Payment");
        paymentDate = validateUserInput.createAndValidateDate();

        //endDate
        //--------------------------
        //addition of endDate must be validated to ensure date is in correct format
        System.out.println("\nExpected end date for Debt Payments");
        endDate = validateUserInput.createAndValidateDate();

        //totalOwed
        //--------------------------
        totalOwed = validateUserInput.getAmountValue("Please enter total amount still owing for this Debt Payment. " +
                "For decimals please use a comma \',\':");

        //interest
        //--------------------------
       interest = validateUserInput.getAmountValue("Please enter interest amount charged for this payment." +
               " For decimals please use a comma \',\':");

        //Additional Notes
        //--------------------------
        System.out.println("\nPlease enter any additional notes to accompany this debt payment.");
        System.out.println("If you do not wish to add any, please enter \'0\'");
        String hold = scanner.nextLine();
        if(!hold.equals("0")){
            notes = hold;
        }


        //Call AddDebtPayment method in to add new debt payment to database
        addDataToDataBase.addDebtExpenses(userId, typeCategory, paidTo, amount,
                paymentDate, endDate, totalOwed, interest, notes);

    }

}













