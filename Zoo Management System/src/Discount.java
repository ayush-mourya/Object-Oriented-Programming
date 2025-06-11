// Represents a discount code for a category of visitors.
public class Discount {
    private String category; // e.g., "MINOR", "SENIOR"
    private String code;
    private double percentage;

    public Discount(String category, String code, double percentage) {
        this.category = category;
        this.code = code;
        this.percentage = percentage;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
