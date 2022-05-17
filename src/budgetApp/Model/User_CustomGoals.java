package budgetApp.Model;

public class User_CustomGoals {

    int id;
    int user_id;
    int custom_goals_id;

    public User_CustomGoals() {
        //empty constructor when initializing linkedLists from database
    }

    //Parameterized Constructor when creating new User_Custom_Goals relation
    public User_CustomGoals(int id, int user_id, int custom_goals_id) {
        this.id = id;
        this.user_id = user_id;
        this.custom_goals_id = custom_goals_id;
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

    public int getCustom_goals_id() {
        return custom_goals_id;
    }

    public void setCustom_goals_id(int custom_goals_id) {
        this.custom_goals_id = custom_goals_id;
    }

    @Override
    public String toString() {
        return "User_CustomGoals{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", custom_goals_id=" + custom_goals_id +
                '}';
    }
}
