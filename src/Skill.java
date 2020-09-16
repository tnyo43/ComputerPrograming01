public class Skill {
    private String name;
    private Type type;
    private int power;
    private int pp;

    private Skill () {}

    public Skill(String name, Type type, int power, int pp) {
        this.name = name;
        this.type = type;
        this.power = power;
        this.pp = pp;
    }

    @Override
    public String toString() {
        return String.format("%s(%s/%d/%d)", this.name, this.type, this.power, this.pp);
    }
}
