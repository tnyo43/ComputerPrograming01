public class Monster {
    private String name;
    public Type type;
    private Skill skills[];
    private int maxHP;
    private int currentHP;
    public int ap;
    public int bp;

    private Monster() {}

    public Monster(String name, Type type, Skill skills[], int hp, int ap, int bp) {
        this.name = name;
        this.type = type;
        this.skills = skills;
        this.maxHP = this.currentHP = hp;
        this.ap = ap;
        this.bp = bp;
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
        System.out.println(String.format("AP：%d, BP : %d", this.ap, this.bp));
        System.out.println();
    }

    public void attack(int skillIndex, Monster other) {
        int damage = this.skills[skillIndex].getDamage(this, other);
        other.currentHP = Math.max(0, other.currentHP - damage);
    }
}
