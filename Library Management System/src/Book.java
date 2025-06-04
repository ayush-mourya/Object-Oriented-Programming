class Book {
    private static int idCounter = 1;
    private int id;
    private String title;
    private String author;
    private int totalCopies;
    private int availableCopies;

    public Book(String title, String author, int totalCopies) {
        this.id = idCounter++;
        this.title = title;
        this.author = author;
        this.totalCopies = this.availableCopies = totalCopies;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public int getTotalCopies() {
        return totalCopies;
    }
    
    public void decrementAvailableCopies() {
        availableCopies--;
    }

    public void incrementAvailableCopies() {
        availableCopies++;
    }

    @Override
    public String toString() {
        return String.format("Book ID - %d, Name - %s, Author - %s, Available - %d", id, title, author,
                availableCopies);
    }
}
