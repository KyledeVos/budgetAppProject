package budgetApp.Model;

public class User_Expenses {

    int id;
    int user_id;
    int expenses_id;

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

    public int getExpenses_id() {
        return expenses_id;
    }

    public void setExpenses_id(int expenses_id) {
        this.expenses_id = expenses_id;
    }

    @Override
    public String toString() {
        return "User_Expenses{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", expenses_id=" + expenses_id +
                '}';
    }
}
