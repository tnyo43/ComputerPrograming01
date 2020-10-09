package monster;

public abstract class Skill {
    protected String name;
    protected int maxPP;
    protected int currentPP;

    public static Skill get(int index) {
        Skill s = skills[index];
        return s.copy();
    }

    public String getName() {
        return this.name;
    }

    public abstract void run(Monster attacker, Monster other);

    public abstract Skill copy();

    public abstract String toString();

    private static Skill skills[] = {
        new AttackSkill("火を吐く", Type.FIRE, 40, 5),
        new AttackSkill("灼熱フィスト", Type.FIRE, 30, 20),
        new AttackSkill("みずふんしゃ", Type.WATER, 1, 10),
        new AttackSkill("疾風パンチ", Type.HURRICANE, 10, 30),
        new AttackSkill("かまいたちエルボー", Type.HURRICANE, 20, 20),
        new AttackSkill("ボコボコキック", Type.DARK, 30, 10),
        new AttackSkill("乱反射", Type.HOLY, 100, 1),

        // あたらしく追加した回復の技（引数は先頭から順番に、技の名前、HPの回復量、PP）
        new SpecialSkill("ひとやすみ", 20, 10)
    };
}