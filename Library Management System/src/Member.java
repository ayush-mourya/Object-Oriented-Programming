import java.util.*;
import java.time.*;

class Member extends User {
    private static int idCounter = 1;
    private int memberId;
    private List<BorrowedBook> borrowedBooks;
    private int fineDue;

    public Member(String name, String phone, int age) {
        this.name = name;
        this.phone = phone;
        this.memberId = idCounter++;
        this.borrowedBooks = new ArrayList<>();
        this.fineDue = 0;
    }

    public int getId() {
        return memberId;
    }

    public int getFineDue() {
        return fineDue;
    }

    public List<BorrowedBook> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        if (borrowedBooks.size() >= 2) {
            System.out.println("Cannot borrow more than 2 books.");
            return;
        }
        borrowedBooks.add(new BorrowedBook(book));
        book.decrementAvailableCopies();
        System.out.println("Book Issued Successfully!");
    }

    public void returnBook(int bookId) {
        for (BorrowedBook bb : borrowedBooks) {
            if (bb.getBook().getId() == bookId) {
                long days = Duration.between(bb.getIssueDate(), LocalDateTime.now()).getSeconds();
                int fine = (int) Math.max(0, (days - 10) * 3);
                fineDue += fine;
                bb.getBook().incrementAvailableCopies();
                borrowedBooks.remove(bb);
                System.out.printf(
                        "Book ID: %d successfully returned. %d Rupees has been charged for a delay of %d days.%n",
                        bookId, fine, Math.max(0, (days - 10)));
                return;
            }
        }
        System.out.println("Book ID not found in your borrowed list.");
    }

    public void payFine() {
        if (fineDue > 0) {
            System.out.printf("You had a total fine of Rs. %d. It has been paid successfully!%n", fineDue);
            fineDue = 0;
        } else {
            System.out.println("No dues to pay.");
        }
    }

    @Override
    public String toString() {
        return String.format("%s (ID: %d, Phone: %s, Fine Due: Rs.%d)", name, memberId, phone, fineDue);
    }
}