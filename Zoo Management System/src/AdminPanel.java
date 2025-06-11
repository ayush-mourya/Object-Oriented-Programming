import java.util.*;

public class AdminPanel {
    private List<Attraction> attractions;
    private List<ZooAnimal> animals;
    private Map<String, Event> eventMap;
    private List<Discount> discounts;
    private List<Deal> deals;
    private List<Feedback> feedbackList;
    private List<Membership> memberships;
    private Scanner scanner;

    public AdminPanel() {
        attractions = new ArrayList<>();
        animals = new ArrayList<>();
        eventMap = new HashMap<>();
        discounts = new ArrayList<>();
        deals = new ArrayList<>();
        feedbackList = new ArrayList<>();
        memberships = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("\nWelcome Admin!");
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Manage Attractions");
            System.out.println("2. Manage Events");
            System.out.println("3. Manage Animals"); // NEW
            System.out.println("4. View Visitor Stats");
            System.out.println("5. Create Discount");
            System.out.println("6. Create Deal");
            System.out.println("7. View Feedback");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> manageAttractions(); // Wrap Attraction related methods
                case 2 -> manageEvents();
                case 3 -> manageAnimals(); // NEW
                case 4 -> manageDiscounts();
                case 5 -> manageDeals();
                case 6 -> viewVisitorStats();
                case 7 -> viewFeedback();
                case 8 -> {
                    System.out.println("Exiting Admin Panel...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }

        }
    }

    private void manageAttractions() {
        while (true) {
            System.out.println("\n--- Manage Attractions ---");
            System.out.println("1. Add Attraction");
            System.out.println("2. Remove Attraction");
            System.out.println("3. View All Attractions");
            System.out.println("4. Modify Attraction");
            System.out.println("5. Back");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1 -> addAttraction();
                case 2 -> removeAttraction();
                case 3 -> viewAttractions();
                case 4 -> modifyAttraction();
                case 5 -> {
                    System.out.println("Returning to Admin Panel...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addAttraction() {
        System.out.print("Enter attraction ID: ");
        String id = scanner.nextLine();

        if (getAttractionById(id) != null) {
            System.out.println("Attraction with this ID already exists.");
            return;
        }

        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter ticket price: ");
        double price = scanner.nextDouble();

        attractions.add(new Attraction(id, name, description, price));
        System.out.println("Attraction added successfully!");
    }

    private void removeAttraction() {
        System.out.print("Enter attraction ID to remove: ");
        String id = scanner.nextLine();

        Attraction toRemove = getAttractionById(id);
        if (toRemove != null) {
            attractions.remove(toRemove);
            eventMap.remove(id);
            System.out.println("Attraction removed.");
        } else {
            System.out.println("Attraction not found.");
        }
    }

    private void viewAttractions() {
        if (attractions.isEmpty()) {
            System.out.println("No attractions found.");
            return;
        }
        for (Attraction a : attractions) {
            System.out.printf("ID: %s | Name: %s | Description: %s | Price: ₹%.2f | Visitors: %d\n",
                    a.getId(), a.getName(), a.getDescription(), a.getPrice(), a.getTicketedVisitors());
        }
    }

    private void modifyAttraction() {
        System.out.print("Enter attraction ID to modify: ");
        String id = scanner.nextLine();

        Attraction a = getAttractionById(id);
        if (a == null) {
            System.out.println("Attraction not found.");
            return;
        }

        System.out.println("Leave field empty to keep current value.");

        System.out.print("Enter new name (" + a.getName() + "): ");
        String name = scanner.nextLine();
        if (!name.isEmpty())
            a.setName(name);

        System.out.print("Enter new description (" + a.getDescription() + "): ");
        String desc = scanner.nextLine();
        if (!desc.isEmpty())
            a.setDescription(desc);

        System.out.print("Enter new ticket price (₹" + a.getPrice() + "): ");
        String priceInput = scanner.nextLine();
        if (!priceInput.isEmpty()) {
            try {
                double newPrice = Double.parseDouble(priceInput);
                a.setPrice(newPrice);
            } catch (NumberFormatException e) {
                System.out.println("Invalid price input. Keeping old price.");
            }
        }

        System.out.println("Attraction updated.");
    }

    private void manageEvents() {
        while (true) {
            System.out.println("\n--- Manage Events Menu ---");
            System.out.println("1. View All Events");
            System.out.println("2. Schedule New Event (Open)");
            System.out.println("3. Modify Existing Event");
            System.out.println("4. Close Event");
            System.out.println("5. Back");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> viewAllEvents();
                case 2 -> scheduleNewEvent();
                case 3 -> modifyEvent();
                case 4 -> closeEvent();
                case 5 -> {
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void viewAllEvents() {
        if (eventMap.isEmpty()) {
            System.out.println("No active events found.");
            return;
        }
        System.out.println("\nActive Events:");
        for (Map.Entry<String, Event> entry : eventMap.entrySet()) {
            String id = entry.getKey();
            Event e = entry.getValue();
            Attraction a = getAttractionById(id);
            if (a != null) {
                System.out.printf("ID: %s | Name: %s | Schedule: %s | Open: %b | Price: ₹%.2f | Visitors: %d\n",
                        id, a.getName(), e.getSchedule(), e.isOpen(), a.getPrice(), a.getTicketedVisitors());
            }
        }
    }

    private void scheduleNewEvent() {
        System.out.print("Enter attraction ID to schedule: ");
        String id = scanner.nextLine();

        Attraction a = getAttractionById(id);
        if (a == null) {
            System.out.println("Attraction not found.");
            return;
        }
        if (eventMap.containsKey(id)) {
            System.out.println("Event is already scheduled for this attraction.");
            return;
        }

        System.out.print("Enter schedule (e.g., 10:00 AM - 5:00 PM): ");
        String schedule = scanner.nextLine();
        System.out.print("Enter ticket price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        a.setPrice(price);
        eventMap.put(id, new Event(id, schedule, true));
        System.out.println("Event scheduled and attraction opened.");
    }

    private void modifyEvent() {
        System.out.print("Enter attraction ID to modify event: ");
        String id = scanner.nextLine();

        if (!eventMap.containsKey(id)) {
            System.out.println("No scheduled event found for this attraction.");
            return;
        }

        Event e = eventMap.get(id);
        Attraction a = getAttractionById(id);

        System.out.println("Leave field blank to keep current value.");

        System.out.printf("Current Schedule: %s\nEnter new schedule: ", e.getSchedule());
        String newSchedule = scanner.nextLine();
        if (!newSchedule.isEmpty())
            e.setSchedule(newSchedule);

        System.out.printf("Current Price: ₹%.2f\nEnter new price: ", a.getPrice());
        String priceInput = scanner.nextLine();
        if (!priceInput.isEmpty()) {
            try {
                double newPrice = Double.parseDouble(priceInput);
                a.setPrice(newPrice);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid price input. Keeping old price.");
            }
        }

        eventMap.put(id, e); // update if needed
        System.out.println("Event modified.");
    }

    private void closeEvent() {
        System.out.print("Enter attraction ID to close event: ");
        String id = scanner.nextLine();

        if (eventMap.remove(id) != null) {
            System.out.println("Event closed and removed from schedule.");
        } else {
            System.out.println("No such scheduled event found.");
        }
    }


    
    private void manageAnimals() {
        while (true) {
            System.out.println("\n--- Manage Animals ---");
            System.out.println("1. Add Animal");
            System.out.println("2. View All Animals");
            System.out.println("3. Update Animal");
            System.out.println("4. Remove Animal");
            System.out.println("5. Back");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addAnimal();
                case 2 -> viewAnimals();
                case 3 -> updateAnimal();
                case 4 -> removeAnimal();
                case 5 -> {
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void addAnimal() {
        System.out.print("Enter animal name: ");
        String name = scanner.nextLine();

        System.out.println("Enter type (Mammal / Amphibian / Reptile): ");
        String type = scanner.nextLine();

        // Validate type
        if (!type.equalsIgnoreCase("Mammal") &&
                !type.equalsIgnoreCase("Amphibian") &&
                !type.equalsIgnoreCase("Reptile")) {
            System.out.println("Invalid animal type. Must be Mammal, Amphibian, or Reptile.");
            return;
        }

        System.out.print("Enter sound it makes when fed: ");
        String sound = scanner.nextLine();

        ZooAnimal animal = new ZooAnimal(name, capitalize(type), sound);
        animals.add(animal);
        System.out.println(animal.getName() + " added successfully as a " + animal.getType() + ".");
    }

    private void viewAnimals() {
        if (animals.isEmpty()) {
            System.out.println("No animals in the zoo yet.");
            return;
        }
        System.out.println("\n--- Animals in the Zoo ---");
        for (int i = 0; i < animals.size(); i++) {
            Animal a = animals.get(i);
            System.out.printf("%d. Name: %s | Type: %s | Sound: ", (i + 1), a.getName(), a.getType());
            a.feed();
        }
    }

    private void updateAnimal() {
        if (animals.isEmpty()) {
            System.out.println("No animals to update.");
            return;
        }

        viewAnimals();
        System.out.print("Enter the number of the animal to update: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index < 1 || index > animals.size()) {
            System.out.println("Invalid index.");
            return;
        }

        ZooAnimal animal = (ZooAnimal) animals.get(index - 1);

        System.out.print("Enter new name (" + animal.getName() + "): ");
        String name = scanner.nextLine();
        if (!name.isEmpty())
            animal.setName(name);

        System.out.print("Enter new type (" + animal.getType() + "): ");
        String type = scanner.nextLine();
        if (!type.isEmpty() &&
                (type.equalsIgnoreCase("Mammal") || type.equalsIgnoreCase("Amphibian")
                        || type.equalsIgnoreCase("Reptile"))) {
            animal.setType(capitalize(type));
        }

        System.out.print("Enter new feeding sound: ");
        String sound = scanner.nextLine();
        if (!sound.isEmpty())
            animal.setSound(sound);

        System.out.println("Animal updated successfully.");
    }

    private void removeAnimal() {
        if (animals.isEmpty()) {
            System.out.println("No animals to remove.");
            return;
        }

        viewAnimals();
        System.out.print("Enter the number of the animal to remove: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index < 1 || index > animals.size()) {
            System.out.println("Invalid index.");
            return;
        }

        Animal removed = animals.remove(index - 1);
        System.out.println(removed.getName() + " has been removed from the zoo.");
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty())
            return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    private void manageDiscounts() {
        while (true) {
            System.out.println("\n--- Manage Discounts Menu ---");
            System.out.println("1. Add Discount");
            System.out.println("2. Remove Discount");
            System.out.println("3. View All Discounts");
            System.out.println("4. Back");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addDiscount();
                case 2 -> removeDiscount();
                case 3 -> viewDiscounts();
                case 4 -> {
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void addDiscount() {
        System.out.print("Enter visitor category (e.g., MINOR/SENIOR): ");
        String category = scanner.nextLine();
        System.out.print("Enter discount code: ");
        String code = scanner.nextLine();
        System.out.print("Enter discount percentage: ");
        double percentage = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        discounts.add(new Discount(category, code, percentage));
        System.out.println("Discount added successfully.");
    }

    private void removeDiscount() {
        if (discounts.isEmpty()) {
            System.out.println("No discounts to remove.");
            return;
        }

        System.out.print("Enter discount code to remove: ");
        String code = scanner.nextLine();

        boolean removed = discounts.removeIf(d -> d.getCode().equalsIgnoreCase(code));

        if (removed) {
            System.out.println("Discount removed successfully.");
        } else {
            System.out.println("No discount found with the given code.");
        }
    }

    private void viewDiscounts() {
        if (discounts.isEmpty()) {
            System.out.println("No active discounts.");
            return;
        }

        System.out.println("\nActive Discounts:");
        for (Discount d : discounts) {
            System.out.printf("Code: %s | Category: %s | Discount: %.2f%%\n",
                    d.getCode(), d.getCategory(), d.getPercentage());
        }
    }

    private void manageDeals() {
        while (true) {
            System.out.println("\n--- Manage Deals Menu ---");
            System.out.println("1. Add Deal");
            System.out.println("2. Remove Deal");
            System.out.println("3. View All Deals");
            System.out.println("4. Back");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addDeal();
                case 2 -> removeDeal();
                case 3 -> viewDeals();
                case 4 -> {
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void addDeal() {
        System.out.print("Enter minimum tickets to qualify: ");
        int threshold = scanner.nextInt();
        System.out.print("Enter discount percentage: ");
        double percentage = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        deals.add(new Deal(threshold, percentage));
        System.out.println("Deal added successfully.");
    }

    private void removeDeal() {
        if (deals.isEmpty()) {
            System.out.println("No deals available to remove.");
            return;
        }

        System.out.print("Enter minimum ticket threshold of the deal to remove: ");
        int threshold = scanner.nextInt();
        System.out.print("Enter discount percentage of the deal to remove: ");
        double percentage = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        boolean removed = deals.removeIf(d -> d.getThreshold() == threshold && d.getDiscountPercentage() == percentage);

        if (removed) {
            System.out.println("Deal removed successfully.");
        } else {
            System.out.println("No matching deal found.");
        }
    }

    private void viewDeals() {
        if (deals.isEmpty()) {
            System.out.println("No active deals.");
            return;
        }

        System.out.println("\nActive Deals:");
        for (Deal d : deals) {
            System.out.printf("Min Tickets: %d | Discount: %.2f%%\n", d.getThreshold(), d.getDiscountPercentage());
        }
    }

    private void viewVisitorStats() {
        if (attractions.isEmpty()) {
            System.out.println("No attraction data available.");
            return;
        }

        int totalVisitors = 0;
        double totalRevenue = 0.0;

        // Filter attractions with scheduled events
        List<Attraction> scheduledAttractions = new ArrayList<>();
        for (Attraction a : attractions) {
            if (eventMap.containsKey(a.getId())) {
                scheduledAttractions.add(a);
            }
        }

        if (scheduledAttractions.isEmpty()) {
            System.out.println("No attractions with scheduled events.");
            return;
        }

        System.out.println("\n--- Scheduled Attractions Stats ---");
        for (Attraction a : scheduledAttractions) {
            int visitors = a.getTicketedVisitors();
            double revenue = visitors * a.getPrice();
            totalVisitors += visitors;
            totalRevenue += revenue;

            System.out.printf("ID: %s | Name: %s | Visitors: %d | Revenue: ₹%.2f\n",
                    a.getId(), a.getName(), visitors, revenue);
        }

        System.out.println("\n--- Visitor Statistics Summary ---");
        System.out.printf("Total Visitors: %d\n", totalVisitors);
        System.out.printf("Total Revenue: ₹%.2f\n", totalRevenue);

        // Sort and show top 3 attractions by visitor count
        scheduledAttractions.sort((a1, a2) -> Integer.compare(a2.getTicketedVisitors(), a1.getTicketedVisitors()));

        System.out.println("\n--- Top 3 Popular Attractions ---");
        for (int i = 0; i < Math.min(3, scheduledAttractions.size()); i++) {
            Attraction a = scheduledAttractions.get(i);
            System.out.printf("%d. %s | Visitors: %d | Revenue: ₹%.2f\n",
                    i + 1, a.getName(), a.getTicketedVisitors(), a.getTicketedVisitors() * a.getPrice());
        }
    }

    private void viewFeedback() {
        if (feedbackList.isEmpty()) {
            System.out.println("No feedback yet.");
            return;
        }

        System.out.println("\n--- Visitor Feedback ---");
        for (Feedback f : feedbackList) {
            System.out.println(f); // uses the overridden toString() in Feedback class
            System.out.println("------------------------");
        }
    }

    // Helper method
    private Attraction getAttractionById(String id) {
        for (Attraction a : attractions) {
            if (a.getId().equals(id)) {
                return a;
            }
        }
        return null;
    }

    public List<Membership> getMemberships() {
        return memberships;
    }

    // Additional helper for Visitor system to submit feedback
    public void receiveFeedback(Feedback feedback) {
        feedbackList.add(feedback);
    }

    // Getters
    public List<Attraction> getAttractions() {
        return attractions;
    }

    public List<ZooAnimal> getAnimals() {
        return animals;
    }

    public Map<String, Event> getEventMap() {
        return eventMap;
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public List<Deal> getDeals() {
        return deals;
    }
}
