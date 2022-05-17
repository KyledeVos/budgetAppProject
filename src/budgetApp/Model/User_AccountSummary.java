package budgetApp.Model;

public class User_AccountSummary {

    int id;
    int user_id;
    int accountSummary_id;

    public User_AccountSummary() {
        //empty constructor when initializing linkedLists from database
    }

    //Parameterized Constructor when creating new User_AccountSummary Entry
    public User_AccountSummary(int id, int user_id, int accountSummary_id) {
        this.id = id;
        this.user_id = user_id;
        this.accountSummary_id = accountSummary_id;
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

    public int getAccountSummary_id() {
        return accountSummary_id;
    }

    public void setAccountSummary_id(int accountSummary_id) {
        this.accountSummary_id = accountSummary_id;
    }

    @Override
    public String toString() {
        return "User_AccountSummary{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", accountSummary_id=" + accountSummary_id +
                '}';
    }
}
