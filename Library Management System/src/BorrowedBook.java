import java.time.*;

class BorrowedBook {
    private Book book;
    private LocalDateTime issueDate;

    public BorrowedBook(Book book) {
        this.book = book;
        this.issueDate = LocalDateTime.now();
    }

    public Book getBook() {
        return book;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }
}