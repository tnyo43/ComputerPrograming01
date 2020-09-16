public class Monster {
    private String name;
    private Type type;
    private Skill skills[];
    private int maxHP;
    private int currentHP;

    private Monster() {}

    public Monster(String name, Type type, Skill skills[], int hp) {
        this.name = name;
        this.type = type;
        this.skills = skills;
        this.maxHP = this.currentHP = hp;
    };

    public void display() {
        String skillStr = this.skills[0].toString();
        for (int i = 1; i < this.skills.length; i++) {
            skillStr += ", " + this.skills[i];
        }

        System.out.println(String.format("名前：%s", this.name));
        System.out.println(String.format("タイプ：%s", this.type));
        System.out.println(String.format("技：%s", skillStr));
        System.out.println(String.format("HP：(%d/%d)", this.currentHP, this.maxHP));
        System.out.println();
    }

    public void attack(int skillIndex, Monster other) {
        int damage = this.skills[skillIndex].getDamage();
        other.currentHP = Math.max(0, other.currentHP - damage);
    }
}
