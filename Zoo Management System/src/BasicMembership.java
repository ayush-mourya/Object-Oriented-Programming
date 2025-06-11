public class BasicMembership extends Membership {

    public BasicMembership() {
        super("Basic", 20);  // membership fee ₹20
    }

    @Override
    public double calculateTicketPrice(double basePrice) {
        return basePrice; // no free tickets for basic, pay full ticket price
    }
}
