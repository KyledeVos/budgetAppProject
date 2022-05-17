package budgetApp.Model;

public class AccountSummary {

    int id;
    double account_balance;
    String month;
    String year;
    double total_income;
    double total_expenses;
    double total_savings;

    public AccountSummary() {
        //empty constructor when initializing linkedLists from database
    }

    //Parameterized Constructor when creating new accountSummary Entry
    public AccountSummary(int id, double account_balance, String month, String year, double total_income,
                          double total_expenses, double total_savings) {
        this.id = id;
        this.account_balance = account_balance;
        this.month = month;
        this.year = year;
        this.total_income = total_income;
        this.total_expenses = total_expenses;
        this.total_savings = total_savings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAccount_balance() {
        return account_balance;
    }

    public void setAccount_balance(double account_balance) {
        this.account_balance = account_balance;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public double getTotal_income() {
        return total_income;
    }

    public void setTotal_income(double total_income) {
        this.total_income = total_income;
    }

    public double getTotal_expenses() {
        return total_expenses;
    }

    public void setTotal_expenses(double total_expenses) {
        this.total_expenses = total_expenses;
    }

    public double getTotal_savings() {
        return total_savings;
    }

    public void setTotal_savings(double total_savings) {
        this.total_savings = total_savings;
    }

    @Override
    public String toString() {
        return "AccountSummary{" +
                "id=" + id +
                ", account_balance=" + account_balance +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", total_income=" + total_income +
                ", total_expenses=" + total_expenses +
                ", total_savings=" + total_savings +
                '}';
    }
}
