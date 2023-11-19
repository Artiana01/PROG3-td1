package prog3.example.prog3;

public class BookCustomer {
    private String customerId;
    private Book book;
    private String action; // Lend or Render
    private String periodStartDate;
    private String periodEndDate;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getPeriodStartDate() {
        return periodStartDate;
    }

    public void setPeriodStartDate(String periodStartDate) {
        this.periodStartDate = periodStartDate;
    }

    public String getPeriodEndDate() {
        return periodEndDate;
    }

    public void setPeriodEndDate(String periodEndDate) {
        this.periodEndDate = periodEndDate;
    }

    public BookCustomer(String customerId, Book book, String action, String periodStartDate, String periodEndDate) {
        this.customerId = customerId;
        this.book = book;
        this.action = action;
        this.periodStartDate = periodStartDate;
        this.periodEndDate = periodEndDate;
    }
}
