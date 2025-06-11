import java.time.LocalDate;

/**
 * Represents feedback left by a visitor about their zoo experience.
 */
public class Feedback {
    private String visitorName;
    private int rating; // 1 to 5 stars
    private String comments;
    private LocalDate date;

    public Feedback(String visitorName, int rating, String comments) {
        this.visitorName = visitorName;
        this.rating = rating;
        this.comments = comments;
        this.date = LocalDate.now();
    }

    public String getVisitorName() {
        return visitorName;
    }

    public int getRating() {
        return rating;
    }

    public String getComments() {
        return comments;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Feedback from " + visitorName + " (" + date + "):\n" +
               "Rating: " + rating + "/5\n" +
               "Comments: " + comments + "\n";
    }
}
