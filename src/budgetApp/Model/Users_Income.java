package budgetApp.Model;

public class Users_Income {
    int id;
    int user_id;
    int income_id;

    public Users_Income() {
        //empty constructor when initializing linkedLists from database
    }

    //Parameterized Constructor when creating new User_Income Entry
    public Users_Income(int id, int user_id, int income_id) {
        this.id = id;
        this.user_id = user_id;
        this.income_id = income_id;
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

    public int getIncome_id() {
        return income_id;
    }

    public void setIncome_id(int income_id) {
        this.income_id = income_id;
    }

    @Override
    public String toString() {
        return "Users_Income{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", income_id=" + income_id +
                '}';
    }
}
