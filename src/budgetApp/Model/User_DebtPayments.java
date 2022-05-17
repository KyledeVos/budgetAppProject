package budgetApp.Model;

public class User_DebtPayments {

    int id;
    int user_id;
    int user_debtPayments;

    public User_DebtPayments() {
        //empty constructor when initializing linkedLists from database
    }

    //Parameterized Constructor when creating new User_DebtPayment relation
    public User_DebtPayments(int id, int user_id, int user_debtPayments) {
        this.id = id;
        this.user_id = user_id;
        this.user_debtPayments = user_debtPayments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_debtPayments() {
        return user_debtPayments;
    }

    public void setUser_debtPayments(int user_debtPayments) {
        this.user_debtPayments = user_debtPayments;
    }

    @Override
    public String toString() {
        return "User_DebtPayments{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", user_expense_id=" + user_debtPayments +
                '}';
    }
}
