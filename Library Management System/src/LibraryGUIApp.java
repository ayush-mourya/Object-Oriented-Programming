import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class LibraryGUIApp {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private static Map<String, Member> membersByPhone = new HashMap<>();
    private static java.util.List<Book> books = new ArrayList<>();

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> new LibraryGUIApp().createAndShowGUI());
    }

    private void createAndShowGUI() {
        frame = new JFrame("🏖️ Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.setBackground(new Color(245, 245, 245));
        mainPanel.add(createHomePage(), "home");
        mainPanel.add(createLibrarianPanel(), "librarian");
        mainPanel.add(createMemberLoginPanel(), "memberLogin");

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JPanel createHomePage() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 245, 245));

        JLabel title = new JLabel("Welcome to the Library", JLabel.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 26));
        panel.add(title, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(40, 150, 40, 150));
        buttonsPanel.setBackground(new Color(245, 245, 245));

        JButton librarianBtn = new JButton("👨‍🏫 Enter as Librarian");
        JButton memberBtn = new JButton("👤 Enter as Member");
        JButton exitBtn = new JButton("❌ Exit");

        librarianBtn.setFont(new Font("Arial", Font.PLAIN, 18));
        memberBtn.setFont(new Font("Arial", Font.PLAIN, 18));
        exitBtn.setFont(new Font("Arial", Font.PLAIN, 18));

        buttonsPanel.add(librarianBtn);
        buttonsPanel.add(memberBtn);
        buttonsPanel.add(exitBtn);

        panel.add(buttonsPanel, BorderLayout.CENTER);

        librarianBtn.addActionListener(e -> cardLayout.show(mainPanel, "librarian"));
        memberBtn.addActionListener(e -> cardLayout.show(mainPanel, "memberLogin"));
        exitBtn.addActionListener(e -> System.exit(0));

        return panel;
    }

    private JPanel createLibrarianPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 245, 245));

        JLabel header = new JLabel("📚 Librarian Dashboard", JLabel.CENTER);
        header.setFont(new Font("SansSerif", Font.BOLD, 22));
        panel.add(header, BorderLayout.NORTH);

        JPanel options = new JPanel(new GridLayout(0, 1, 8, 8));
        options.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        options.setBackground(new Color(245, 245, 245));

        JButton regBtn = new JButton("➕ Register Member");
        JButton remBtn = new JButton("➖ Remove Member");
        JButton addBookBtn = new JButton("➕ Add Book");
        JButton remBookBtn = new JButton("➖ Remove Book");
        JButton viewMembersBtn = new JButton("👥 View All Members");
        JButton viewBooksBtn = new JButton("📚 View All Books");
        JButton backBtn = new JButton("⬅️ Back");

        options.add(regBtn);
        options.add(remBtn);
        options.add(addBookBtn);
        options.add(remBookBtn);
        options.add(viewMembersBtn);
        options.add(viewBooksBtn);
        options.add(backBtn);

        panel.add(options, BorderLayout.CENTER);

        regBtn.addActionListener(e -> {
            JTextField nameField = new JTextField();
            JTextField ageField = new JTextField();
            JTextField phoneField = new JTextField();
            Object[] msg = {"Name:", nameField, "Age:", ageField, "Phone:", phoneField};
            int res = JOptionPane.showConfirmDialog(frame, msg, "Register Member", JOptionPane.OK_CANCEL_OPTION);
            if (res == JOptionPane.OK_OPTION) {
                try {
                    String name = nameField.getText();
                    int age = Integer.parseInt(ageField.getText());
                    String phone = phoneField.getText();
                    if (membersByPhone.containsKey(phone)) {
                        JOptionPane.showMessageDialog(frame, "Member already exists.");
                    } else {
                        Member m = new Member(name, phone, age);
                        membersByPhone.put(phone, m);
                        JOptionPane.showMessageDialog(frame, "Registered! ID: " + m.getId());
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid age input.");
                }
            }
        });

        remBtn.addActionListener(e -> {
            String phone = JOptionPane.showInputDialog(frame, "Enter phone number:");
            if (phone != null && membersByPhone.remove(phone) != null)
                JOptionPane.showMessageDialog(frame, "Member removed.");
            else
                JOptionPane.showMessageDialog(frame, "Member not found.");
        });

        addBookBtn.addActionListener(e -> {
            JTextField title = new JTextField();
            JTextField author = new JTextField();
            JTextField copies = new JTextField();
            Object[] msg = {"Title:", title, "Author:", author, "Copies:", copies};
            int res = JOptionPane.showConfirmDialog(frame, msg, "Add Book", JOptionPane.OK_CANCEL_OPTION);
            if (res == JOptionPane.OK_OPTION) {
                try {
                    books.add(new Book(title.getText(), author.getText(), Integer.parseInt(copies.getText())));
                    JOptionPane.showMessageDialog(frame, "Book Added!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid number of copies.");
                }
            }
        });

        remBookBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter Book ID to remove:"));
                if (books.removeIf(b -> b.getId() == id))
                    JOptionPane.showMessageDialog(frame, "Book removed.");
                else
                    JOptionPane.showMessageDialog(frame, "Book not found.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input.");
            }
        });

        viewBooksBtn.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            for (Book b : books) sb.append(b).append("\n");
            JOptionPane.showMessageDialog(frame, sb.toString(), "Books", JOptionPane.INFORMATION_MESSAGE);
        });

        viewMembersBtn.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            for (Member m : membersByPhone.values()) {
                sb.append(m).append("\n");
                for (BorrowedBook bb : m.getBorrowedBooks()) sb.append(" -> ").append(bb.getBook()).append("\n");
            }
            JOptionPane.showMessageDialog(frame, sb.toString(), "Members", JOptionPane.INFORMATION_MESSAGE);
        });

        backBtn.addActionListener(e -> cardLayout.show(mainPanel, "home"));

        return panel;
    }

    private JPanel createMemberLoginPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 245, 245));

        JLabel title = new JLabel("👤 Member Login", JLabel.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 16));
        panel.add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(3, 2, 8, 8));
        form.setBorder(BorderFactory.createEmptyBorder(20, 150, 20, 150));
        form.setBackground(new Color(245, 245, 245));

        JTextField name = new JTextField();
        JTextField phone = new JTextField();
        JButton login = new JButton("🔓 Login");
        JButton back = new JButton("⬅️ Back");

        form.add(new JLabel("Name: "));
        form.add(name);
        form.add(new JLabel("Phone: "));
        form.add(phone);
        form.add(login);
        form.add(back);

        panel.add(form, BorderLayout.CENTER);

        login.addActionListener(e -> {
            String n = name.getText();
            String p = phone.getText();
            Member m = membersByPhone.get(p);
            if (m != null && m.getName().equals(n)) {
                showMemberPanel(m);
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials");
            }
        });

        back.addActionListener(e -> cardLayout.show(mainPanel, "home"));
        return panel;
    }

    private void showMemberPanel(Member member) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 120, 15, 120));
        panel.setBackground(new Color(245, 245, 245));

        JFrame memberFrame = new JFrame("👤 Member Portal - " + member.getName());
        memberFrame.setSize(600, 400);

        JButton listBooks = new JButton("📚 List Available Books");
        JButton listMyBooks = new JButton("📚 List My Books");
        JButton issueBook = new JButton("➕ Issue Book");
        JButton returnBook = new JButton("↩️ Return Book");
        JButton payFine = new JButton("💳 Pay Fine");
        JButton logout = new JButton("❌ Logout");

        Component[] buttons = {listBooks, listMyBooks, issueBook, returnBook, payFine, logout};
        for (Component c : buttons) {
            if (c instanceof JButton) {
                ((JButton) c).setAlignmentX(Component.CENTER_ALIGNMENT);
                panel.add(c);
                panel.add(Box.createRigidArea(new Dimension(0, 5)));
            }
        }

        listBooks.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            for (Book b : books) sb.append(b).append("\n");
            JOptionPane.showMessageDialog(memberFrame, sb.toString());
        });

        listMyBooks.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            for (BorrowedBook bb : member.getBorrowedBooks()) sb.append(bb.getBook()).append("\n");
            JOptionPane.showMessageDialog(memberFrame, sb.toString());
        });

        issueBook.addActionListener(e -> {
            if (member.getFineDue() > 0) {
                JOptionPane.showMessageDialog(memberFrame, "Please pay your fine first.");
                return;
            }
            String idStr = JOptionPane.showInputDialog(memberFrame, "Enter Book ID:");
            try {
                int id = Integer.parseInt(idStr);
                for (Book b : books) {
                    if (b.getId() == id && b.getAvailableCopies() > 0) {
                        member.borrowBook(b);
                        JOptionPane.showMessageDialog(memberFrame, "Book issued!");
                        return;
                    }
                }
                JOptionPane.showMessageDialog(memberFrame, "Book not available.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(memberFrame, "Invalid input.");
            }
        });

        returnBook.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(memberFrame, "Enter Book ID to return:");
            try {
                int id = Integer.parseInt(idStr);
                member.returnBook(id);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(memberFrame, "Invalid input.");
            }
        });

        payFine.addActionListener(e -> member.payFine());

        logout.addActionListener(e -> memberFrame.dispose());

        memberFrame.add(panel);
        memberFrame.setVisible(true);
    }
}