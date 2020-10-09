package monster;

import util.Pair;

public abstract class Skill {
    protected String name;
    protected int maxPP;
    protected int currentPP;

    public static Skill get(int index) {
        Skill s = skills[index];
        return s.copy();
    }

    protected abstract void run(Monster attacker, Monster other);

    protected abstract Skill copy();

    public abstract String toString();

    private static Skill skills[] = {
        new AttackSkill("火を吐く", Type.FIRE, 40, 5),
        new AttackSkill("灼熱フィスト", Type.FIRE, 30, 20),
        new AttackSkill("みずふんしゃ", Type.WATER, 1, 10),
        new AttackSkill("疾風パンチ", Type.HURRICANE, 10, 30),
        new AttackSkill("かまいたちエルボー", Type.HURRICANE, 20, 20),
        new AttackSkill("ボコボコキック", Type.DARK, 30, 10),
        new AttackSkill("乱反射", Type.HOLY, 100, 1),

        new SpecialSkill("ひとやすみ", 20, new Pair<Integer, Integer>(0, 0), new Pair<Integer, Integer>(0, 0), 10),
        new SpecialSkill("エイエイオー", 0, new Pair<Integer, Integer>(1, 0), new Pair<Integer, Integer>(0, 0), 15),
        new SpecialSkill("防御体制", 0, new Pair<Integer, Integer>(0, 2), new Pair<Integer, Integer>(0, 0), 12),
        new SpecialSkill("こちょこちょ", 0, new Pair<Integer, Integer>(0, 0), new Pair<Integer, Integer>(-2, 0), 7),
        new SpecialSkill("溶かす", 0, new Pair<Integer, Integer>(0, 0), new Pair<Integer, Integer>(0, -1), 9),
    };
}
