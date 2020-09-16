public class Skill {
    private String name;
    private Type type;
    private int power;
    private int maxPP;
    private int currentPP;

    private Skill () {}

    private Skill(String name, Type type, int power, int pp) {
        this.name = name;
        this.type = type;
        this.power = power;
        this.maxPP = this.currentPP = pp;
    }

    public int getDamage() {
        if (this.currentPP > 0) {
            this.currentPP--;
            return this.power;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return String.format("%s(%s, %d, (%d/%d))", this.name, this.type, this.power, this.currentPP, this.maxPP);
    }

    public static Skill fireAttack = new Skill("火を吐く", Type.Fire, 40, 5);
    public static Skill heatFist = new Skill("灼熱フィスト", Type.Fire, 30, 20);
    public static Skill waterAttack = new Skill("みずふんしゃ", Type.Water, 1, 10);
    public static Skill hurricaneAttack = new Skill("疾風パンチ", Type.Hurricane, 10, 30);
    public static Skill hurricaneElbow = new Skill("かまいたちエルボー", Type.Hurricane, 20, 20);
    public static Skill kickKickKick = new Skill("ボコボコキック", Type.Dark, 30, 10);
    public static Skill reflection = new Skill("乱反射", Type.Holy, 100, 1);
}
