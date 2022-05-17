package budgetApp.Model;

public class DebtPayments {

    int id;
    String type_category;
    String paid_to;
    double amount;
    String payment_date;
    String end_date;
    double total_owed;
    double interest;
    String notes;

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

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public double getTotal_owed() {
        return total_owed;
    }

    public void setTotal_owed(double total_owed) {
        this.total_owed = total_owed;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "DebtPayments{" +
                "id=" + id +
                ", type_category='" + type_category + '\'' +
                ", paid_to='" + paid_to + '\'' +
                ", amount=" + amount +
                ", payment_date='" + payment_date + '\'' +
                ", end_date='" + end_date + '\'' +
                ", total_owed=" + total_owed +
                ", interest=" + interest +
                ", notes='" + notes + '\'' +
                '}';
    }
}
