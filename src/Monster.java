public class Monster {
    private String name;
    private Type type;
    private String skills[];
    private int hp;

    private Monster() {}

    public Monster(String name, Type type, String skills[], int hp) {
        this.name = name;
        this.type = type;
        this.skills = skills;
        this.hp = hp;
    };

    public void display() {
        System.out.println(String.format("名前：%s", this.name));
        System.out.println(String.format("タイプ：%s", this.type));
        System.out.println(String.format("技：%s", String.join(", ", this.skills)));
        System.out.println(String.format("HP：%d", this.hp));
        System.out.println();
    }
}
