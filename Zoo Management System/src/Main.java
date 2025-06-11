import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AdminPanel adminPanel = new AdminPanel();
        VisitorPanel visitorPanel = new VisitorPanel(adminPanel);
        initializeDefaultAnimals(adminPanel);

        while (true) {
            System.out.println("\n====== Welcome to ZooBuddies ======");
            System.out.println("1. Admin Panel");
            System.out.println("2. Visitor Panel");
            System.out.println("3. Exit Application");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> adminPanel.start();
                case 2 -> visitorPanel.start();
                case 3 -> {
                    System.out.println("Thanks for visiting ZooBuddies! 🐾");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void initializeDefaultAnimals(AdminPanel adminPanel) {
        adminPanel.getAnimals().add(new ZooAnimal("Lion", "Mammal", "Roars!"));
        adminPanel.getAnimals().add(new ZooAnimal("Elephant", "Mammal", "Trumpets!"));
        adminPanel.getAnimals().add(new ZooAnimal("Frog", "Amphibian", "Croaks!"));
        adminPanel.getAnimals().add(new ZooAnimal("Salamander", "Amphibian", "Hisses softly!"));
        adminPanel.getAnimals().add(new ZooAnimal("Snake", "Reptile", "Hisses!"));
        adminPanel.getAnimals().add(new ZooAnimal("Crocodile", "Reptile", "Growls!"));
    }

}
