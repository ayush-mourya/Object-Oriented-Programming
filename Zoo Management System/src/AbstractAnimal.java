public abstract class AbstractAnimal implements Animal {
    private String name;
    private String type;

    public AbstractAnimal(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void readAbout() {
        System.out.println("Reading about " + name + ". A fascinating " + type + ".");
    }
}