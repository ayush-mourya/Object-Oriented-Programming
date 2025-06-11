// Represents a special deal applied automatically based on number of tickets bought.
public class Deal {
    private int threshold;
    private double discountPercentage;

    public Deal(int threshold, double discountPercentage) {
        this.threshold = threshold;
        this.discountPercentage = discountPercentage;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
