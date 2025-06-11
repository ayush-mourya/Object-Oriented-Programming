// Represents a visitor to the zoo.

import java.util.HashMap;
import java.util.Map;

public class Visitor {
    private String name;
    private int age;
    private String phoneNumber;
    private double balance;
    private String email;
    private String password;
    private Membership membership;
    private int visitCount;
    private Map<String, Double> visitHistory;

    public Visitor(String name, int age, String phoneNumber, double balance, String email, String password) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
        this.email = email;
        this.password = password;
        this.membership = null;
        this.visitCount = 0;
        this.visitHistory = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void addVisit(String attractionName, double amountPaid) {
        visitCount++;
        visitHistory.put(attractionName, amountPaid);
    }

    public void buyMembership(Membership membership) {
        if (this.membership != null) {
            System.out.println("You already have a membership: " + this.membership.getType());
            return;
        }
        if (balance >= membership.getPrice()) {
            balance -= membership.getPrice();
            this.membership = membership;
            System.out.println(
                    membership.getType() + " Membership purchased successfully. Your balance is now ₹" + balance);
        } else {
            System.out.println("Insufficient balance to purchase membership.");
        }
    }

    public double getTicketPrice(double basePrice) {
        if (membership == null) {
            return basePrice;
        }
        return membership.calculateTicketPrice(basePrice);
    }

}
