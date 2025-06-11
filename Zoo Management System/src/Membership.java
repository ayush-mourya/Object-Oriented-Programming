public abstract class Membership {
    private String type;
    private double price;

    public Membership(String type, double price) {
        this.type = type;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    // Benefit calculation: ticket price after membership discount/benefits
    public abstract double calculateTicketPrice(double basePrice);
}
