// Represents a scheduled event in the zoo for an attraction.
public class Event {
    private String attractionId;
    private String schedule; // e.g., "10:00 AM - 4:00 PM"
    private boolean isOpen;

    public Event(String attractionId, String schedule, boolean isOpen) {
        this.attractionId = attractionId;
        this.schedule = schedule;
        this.isOpen = isOpen;
    }

    public String getAttractionId() {
        return attractionId;
    }

    public void setAttractionId(String attractionId) {
        this.attractionId = attractionId;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
