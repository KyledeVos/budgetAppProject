package budgetApp.Model;

public class CustomGoals {

    int id;
    String description;
    String saved_location;
    double amount;
    String saved_date;
    double total_desired;
    String final_date;
    String notes;

    public CustomGoals() {
        //empty constructor when initializing linkedLists from database
    }

    //Parameterized Constructor when creating new Custom_Goals Entry
    public CustomGoals(int id, String description, String saved_location, double amount, String saved_date,
                       double total_desired, String final_date, String notes) {
        this.id = id;
        this.description = description;
        this.saved_location = saved_location;
        this.amount = amount;
        this.saved_date = saved_date;
        this.total_desired = total_desired;
        this.final_date = final_date;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSaved_location() {
        return saved_location;
    }

    public void setSaved_location(String saved_location) {
        this.saved_location = saved_location;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getSaved_date() {
        return saved_date;
    }

    public void setSaved_date(String saved_date) {
        this.saved_date = saved_date;
    }

    public double getTotal_desired() {
        return total_desired;
    }

    public void setTotal_desired(double total_desired) {
        this.total_desired = total_desired;
    }

    public String getFinal_date() {
        return final_date;
    }

    public void setFinal_date(String final_date) {
        this.final_date = final_date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "CustomGoals{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", saved_location='" + saved_location + '\'' +
                ", amount=" + amount +
                ", saved_date='" + saved_date + '\'' +
                ", total_desired=" + total_desired +
                ", final_date='" + final_date + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
