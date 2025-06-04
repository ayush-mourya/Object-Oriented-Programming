import java.util.*;

public class LibrarySystem {
    private static Map<String, Member> membersByPhone = new HashMap<>();
    private static List<Book> books = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Library Portal Initialized….");
        while (true) {
            System.out.println("---------------------------------");
            System.out.println("1. Enter as a librarian\t\n2. Enter as a member\n3. Exit");
            System.out.println("---------------------------------");
            String choice = sc.nextLine();
            switch (choice) {
                case "1" -> librarianInterface();
                case "2" -> memberInterface();
                case "3" -> {
                    System.out.println("Thanks for visiting!");
                    return;
                }
                default -> System.out.println("Invalid Option.");
            }
        }
    }

    private static void librarianInterface() {
        while (true) {
            System.out.println("---------------------------------");
            System.out.println(
                    "1. Register a member\n2. Remove a member\n3. Add a book\n4. Remove a book\n5. View all members along with their books and fines to be paid\n6. View all books\n7. Back");
            System.out.println("---------------------------------");
            String choice = sc.nextLine();
            switch (choice) {
                case "1" -> registerMember();
                case "2" -> removeMember();
                case "3" -> addBook();
                case "4" -> removeBook();
                case "5" -> listMembers();
                case "6" -> listBooks();
                case "7" -> {
                    return;
                }
                default -> System.out.println("Invalid Option.");
            }
        }
    }

    private static void memberInterface() {
        System.out.print("Name : ");
        String name = sc.nextLine();
        System.out.print("Phone No: ");
        String phone = sc.nextLine();
        Member member = membersByPhone.get(phone);
        if (member == null || !member.getName().equals(name)) {
            System.out.printf("Member with Name: %s and Phone No: %s doesn't exist.\n", name, phone);
            return;
        }
        System.out.printf("Welcome %s. Member ID: %d%n", name, member.getId());
        while (true) {
            System.out.println("---------------------------------");
            System.out.println(
                    "1. List Available Books\n2. List My Books\n3. Issue book\n4. Return book\n5. Pay Fine\n6. Back");
            System.out.println("---------------------------------");
            String choice = sc.nextLine();
            switch (choice) {
                case "1" -> listBooks();
                case "2" -> member.getBorrowedBooks().forEach(bb -> System.out.println(bb.getBook()));
                case "3" -> {
                    if (member.getFineDue() > 0) {
                        System.out.println("Please pay your due fine before issuing new books.");
                        break;
                    }
                    listBooks();
                    System.out.print("Book ID: ");
                    int bid = Integer.parseInt(sc.nextLine());

                    Book bookToBorrow = null;
                    for (Book b : books) {
                        if (b.getId() == bid && b.getAvailableCopies() > 0) {
                            bookToBorrow = b;
                            break;
                        }
                    }

                    if (bookToBorrow != null) {
                        member.borrowBook(bookToBorrow);
                    } else {
                        System.out.println("Book not available.");
                    }
                }

                case "4" -> {
                    member.getBorrowedBooks().forEach(bb -> System.out.println(bb.getBook()));
                    System.out.print("Enter Book ID: ");
                    int bid = Integer.parseInt(sc.nextLine());
                    member.returnBook(bid);
                }
                case "5" -> member.payFine();
                case "6" -> {
                    return;
                }
                default -> System.out.println("Invalid Option.");
            }
        }
    }

    private static void registerMember() {
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Age: ");
        int age = Integer.parseInt(sc.nextLine());
        System.out.print("Phone no: ");
        String phone = sc.nextLine();
        if (membersByPhone.containsKey(phone)) {
            System.out.println("Member already exists.");
            return;
        }
        Member newMember = new Member(name, phone, age);
        membersByPhone.put(phone, newMember);
        System.out.printf("Member Successfully Registered with ID: %d!%n", newMember.getId());
    }

    private static void removeMember() {
        System.out.print("Phone no: ");
        String phone = sc.nextLine();
        if (membersByPhone.remove(phone) != null)
            System.out.println("Member Removed Successfully!");
        else
            System.out.println("Member Not Found.");
    }

    private static void addBook() {
        System.out.print("Book title: ");
        String title = sc.nextLine();
        System.out.print("Author: ");
        String author = sc.nextLine();
        System.out.print("Copies: ");
        int copies = Integer.parseInt(sc.nextLine());
        books.add(new Book(title, author, copies));
        System.out.println("Book Added Successfully!");
    }

    private static void removeBook() {
        System.out.print("Book ID: ");
        int bid = Integer.parseInt(sc.nextLine());
        if (books.removeIf(b -> b.getId() == bid))
            System.out.println("Book Removed Successfully!");
        else
            System.out.println("Book Not Found.");
    }

    private static void listBooks() {
        for (Book b : books) {
            System.out.println(b);
        }
    }

    private static void listMembers() {
        for (Member m : membersByPhone.values()) {
            System.out.println(m);
            m.getBorrowedBooks().forEach(bb -> System.out.println("  -> " + bb.getBook()));
        }
    }
}