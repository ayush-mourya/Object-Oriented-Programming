public class PremiumMembership extends Membership {

    public PremiumMembership() {
        super("Premium", 50); // membership fee ₹50
    }

    @Override
    public double calculateTicketPrice(double basePrice) {
        return 0; // free tickets for premium members
    }
}
