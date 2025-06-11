public class ZooAnimal extends AbstractAnimal {
    private String sound;

    public ZooAnimal(String name, String type, String sound) {
        super(name, type);
        this.sound = sound;
    }

    @Override
    public void feed() {
        System.out.println(getName() + " " + sound);
    }

    public void setSound(String sound) {
        this.sound = sound;
    }
}
