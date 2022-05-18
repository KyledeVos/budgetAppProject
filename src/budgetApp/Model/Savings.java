package budgetApp.Model;

public class Savings {

    int id;
    String saved_location;
    double amount;
    String saved_date;
    String notes;

    public Savings() {
        //empty constructor when initializing linkedLists from database
    }

    //Parameterized Constructor when creating new Savings Entry
    public Savings(int id, String saved_location, double amount, String saved_date, String notes) {
        this.id = id;
        this.saved_location = saved_location;
        this.amount = amount;
        this.saved_date = saved_date;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Savings{" +
                "id=" + id +
                ", saved_location='" + saved_location + '\'' +
                ", amount=" + amount +
                ", saved_date='" + saved_date + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
