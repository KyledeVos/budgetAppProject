package budgetApp.Model;

public class User_Savings {

    int id;
    int user_id;
    int savings_id;

    public User_Savings() {
        //empty constructor when initializing linkedLists from database
    }

    //Parameterized Constructor when creating new User_Savings relation
    public User_Savings(int id, int user_id, int savings_id) {
        this.id = id;
        this.user_id = user_id;
        this.savings_id = savings_id;
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

    public int getSavings_id() {
        return savings_id;
    }

    public void setSavings_id(int savings_id) {
        this.savings_id = savings_id;
    }

    @Override
    public String toString() {
        return "User_Savings{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", savings_id=" + savings_id +
                '}';
    }
}
