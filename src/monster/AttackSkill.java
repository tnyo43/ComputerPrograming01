package monster;

import java.util.HashMap;
import java.util.Map;

import util.Pair;

public class AttackSkill extends Skill {
    private Type type;
    private int power;

    private static final double MAG_LARGE = 1.3;
    private static final double MAG_MIDDLE = 1.0;
    private static final double MAG_SMALL = 0.7;

    private static Map<Pair<Type, Type>, Double> magnificationMap = initMagnificationMap();

    private AttackSkill () {}

    AttackSkill(String name, Type type, int power, int pp) {
        this.name = name;
        this.type = type;
        this.power = power;
        this.maxPP = this.currentPP = pp;
    }

    // magnificationMapの初期化のための補助関数
    private static void addMagnification(Map<Pair<Type, Type>, Double> m, Type type1, Type type2, double mag) {
        m.put(new Pair<>(type1, type2), mag);
    }

    private static Map<Pair<Type, Type>, Double> initMagnificationMap() {
        Map<Pair<Type, Type>, Double> m = new HashMap<>();
        // 火の攻撃

        addMagnification(m, Type.FIRE, Type.WATER, MAG_SMALL);
        addMagnification(m, Type.FIRE, Type.HURRICANE, MAG_LARGE);
        // 水の攻撃
        addMagnification(m, Type.WATER, Type.HURRICANE, MAG_SMALL);
        addMagnification(m, Type.WATER, Type.FIRE, MAG_LARGE);
        // 風の攻撃
        addMagnification(m, Type.HURRICANE, Type.FIRE, MAG_SMALL);
        addMagnification(m, Type.HURRICANE, Type.WATER, MAG_LARGE);
        // 光の攻撃
        addMagnification(m, Type.HOLY, Type.DARK, MAG_LARGE);
        // 闇の攻撃
        addMagnification(m, Type.DARK, Type.HOLY, MAG_LARGE);

        return m;
    }

    private double getTypeMagnification(Type type) {
        Pair<Type, Type> key = new Pair<>(this.type, type);
        if (magnificationMap.containsKey(key)) {
            return magnificationMap.get(key);
        } else {
            return MAG_MIDDLE;
        }
    }

    @Override
    public void run(Monster attacker, Monster other) {
        int damage = this.getDamage(attacker, other);
        other.damaged(damage);
    }

    private int getDamage(Monster attacker, Monster other) {
        if (this.currentPP > 0) {
            this.currentPP--;
            double damage = 1.0 * this.power * this.getTypeMagnification(other.type) * attacker.ap / other.bp;
            return (int)damage;
        } else {
            return 0;
        }
    }

    @Override
    public AttackSkill copy() {
        return new AttackSkill(this.name, this.type, this.power, this.maxPP);
    }

    @Override
    public String toString() {
        return String.format("%s(%s, %d, (%d/%d))", this.name, this.type, this.power, this.currentPP, this.maxPP);
    }
}