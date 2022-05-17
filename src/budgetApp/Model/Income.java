package budgetApp.Model;

public class Income {

    int id;
    String source_name;
    double amount;
    String payment_date;
    String payment_interval;
    String notes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSource_name() {
        return source_name;
    }

    public void setSource_name(String source_name) {
        this.source_name = source_name;
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

    public String getPayment_interval() {
        return payment_interval;
    }

    public void setPayment_interval(String payment_interval) {
        this.payment_interval = payment_interval;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Income{" +
                "id=" + id +
                ", source_name='" + source_name + '\'' +
                ", amount=" + amount +
                ", payment_date='" + payment_date + '\'' +
                ", payment_interval='" + payment_interval + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
