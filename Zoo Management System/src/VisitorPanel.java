import java.util.*;

public class VisitorPanel {
    private Map<String, Visitor> visitors;
    private Visitor currentVisitor;
    private AdminPanel adminPanel;
    private Scanner scanner;

    public VisitorPanel(AdminPanel adminPanel) {
        this.adminPanel = adminPanel;
        this.visitors = new HashMap<>();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\n--- Visitor Portal ---");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit to Main Menu");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> register();
                case 2 -> login();
                case 3 -> {
                    System.out.println("Returning to main menu...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void register() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter starting balance: ");
        double balance = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Create password: ");
        String password = scanner.nextLine();

        if (visitors.containsKey(email)) {
            System.out.println("Email already registered!");
            return;
        }

        Visitor v = new Visitor(name, age, phone, balance, email, password);
        visitors.put(email, v);
        System.out.println("Registration successful!");
    }

    private void login() {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Visitor v = visitors.get(email);
        if (v == null || !v.getPassword().equals(password)) {
            System.out.println("Invalid credentials.");
            return;
        }

        currentVisitor = v;
        System.out.println("Welcome, " + v.getName() + "!");
        visitorMenu();
    }

    private void visitorMenu() {
        while (true) {
            System.out.println("\n--- Visitor Menu ---");
            System.out.println("1. Explore Zoo (Attractions + Animals)");
            System.out.println("2. Buy Membership");
            System.out.println("3. Buy Ticket");
            System.out.println("4. Visit Animal");
            System.out.println("5. Submit Feedback");
            System.out.println("6. Logout");

            System.out.print("Enter choice: ");
            int ch = scanner.nextInt();
            scanner.nextLine();

            switch (ch) {
                case 1 -> exploreZoo();
                case 2 -> buyMembership();
                case 3 -> buyTicket();
                case 4 -> visitAnimal();
                case 5 -> submitFeedback();
                case 6 -> {
                    System.out.println("Logged out.");
                    currentVisitor = null;
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void exploreZoo() {
        System.out.println("\n--- Attractions ---");
        List<Attraction> attractions = adminPanel.getAttractions();
        for (Attraction a : attractions) {
            System.out.printf("ID: %s | Name: %s | Price: ₹%.2f\n", a.getId(), a.getName(), a.getPrice());
        }

        System.out.println("\n--- Animals in the Zoo ---");
        List<ZooAnimal> animals = adminPanel.getAnimals();
        for (Animal a : animals) {
            System.out.printf("Name: %s | Type: %s\n", a.getName(), a.getType());
        }
    }

    private void buyMembership() {
        System.out.println("Available Memberships:");
        for (Membership m : adminPanel.getMemberships()) {
            System.out.printf("Type: %s | Price: ₹%.2f\n", m.getType(), m.getPrice());
        }

        System.out.print("Enter membership type to buy: ");
        String type = scanner.nextLine();
        Membership selected = adminPanel.getMemberships().stream()
                .filter(m -> m.getType().equalsIgnoreCase(type)).findFirst().orElse(null);

        if (selected == null) {
            System.out.println("Invalid membership type.");
            return;
        }

        currentVisitor.buyMembership(selected);
    }

    private void buyTicket() {
        System.out.print("Enter Attraction ID to buy ticket: ");
        String id = scanner.nextLine();
        Attraction a = adminPanel.getAttractions().stream()
                .filter(at -> at.getId().equals(id)).findFirst().orElse(null);

        if (a == null) {
            System.out.println("Invalid ID.");
            return;
        }

        double finalPrice = currentVisitor.getTicketPrice(a.getPrice());

        for (Discount d : adminPanel.getDiscounts()) {
            if ((d.getCategory().equals("MINOR") && currentVisitor.getAge() < 18) ||
                    (d.getCategory().equals("SENIOR") && currentVisitor.getAge() >= 60)) {
                finalPrice *= (1 - d.getPercentage() / 100.0);
                System.out.printf("Discount applied: %.1f%%\n", d.getPercentage());
                break;
            }
        }

        for (Deal deal : adminPanel.getDeals()) {
            if (currentVisitor.getVisitCount() + 1 >= deal.getThreshold()) {
                finalPrice *= (1 - deal.getDiscountPercentage() / 100.0);
                System.out.printf("Deal applied: %.1f%%\n", deal.getDiscountPercentage());
                break;
            }
        }

        a.incrementTicketedVisitors();
        currentVisitor.addVisit(a.getName(), finalPrice);
        System.out.printf("Ticket purchased for ₹%.2f\n", finalPrice);
    }

    private void visitAnimal() {
        List<ZooAnimal> animals = adminPanel.getAnimals();
        for (int i = 0; i < animals.size(); i++) {
            System.out.printf("%d. %s (%s)\n", i + 1, animals.get(i).getName(), animals.get(i).getType());
        }
        System.out.print("Choose an animal to visit: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > animals.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        Animal selected = animals.get(choice - 1);
        System.out.println("1. Feed the animal\n2. Read about the animal");
        System.out.print("Enter choice: ");
        int act = scanner.nextInt();
        scanner.nextLine();

        switch (act) {
            case 1 -> selected.feed();
            case 2 -> System.out.println("Information: " + selected.getName() + " is a " + selected.getType() + ".");
            default -> System.out.println("Invalid action.");
        }
    }

    private void submitFeedback() {
        System.out.print("Enter your rating: ");
        int rating = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter your feedback: ");
        String msg = scanner.nextLine();
        Feedback fb = new Feedback(currentVisitor.getName(), rating, msg);
        adminPanel.receiveFeedback(fb);
        System.out.println("Thank you for your feedback!");
    }
}
