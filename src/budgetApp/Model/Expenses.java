package budgetApp.Model;

public class Expenses {

    int id;
    String type_category;
    String paid_to;
    double amount;
    String payment_date;
    String description;
    String notes;

    public Expenses() {
        //empty constructor when initializing linkedLists from database
    }

    //Parameterized Constructor when creating new Expense Entry
    public Expenses(int id, String type_category, String paid_to, double amount, String payment_date, String description, String notes) {
        this.id = id;
        this.type_category = type_category;
        this.paid_to = paid_to;
        this.amount = amount;
        this.payment_date = payment_date;
        this.description = description;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType_category() {
        return type_category;
    }

    public void setType_category(String type_category) {
        this.type_category = type_category;
    }

    public String getPaid_to() {
        return paid_to;
    }

    public void setPaid_to(String paid_to) {
        this.paid_to = paid_to;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Expenses{" +
                "id=" + id +
                ", type_category='" + type_category + '\'' +
                ", paid_to='" + paid_to + '\'' +
                ", amount=" + amount +
                ", payment_date='" + payment_date + '\'' +
                ", description='" + description + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
