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

    private static Map<Pair<Type, Type>, Double> magnificationMap;

    private AttackSkill () {}

    AttackSkill(String name, Type type, int power, int pp) {
        this.name = name;
        this.type = type;
        this.power = power;
        this.maxPP = this.currentPP = pp;

        if (magnificationMap == null) {
            initMagnificationMap();
        }
    }

    // magnificationMapの初期化のための補助関数
    private void addMagnification(Type type1, Type type2, double mag) {
        magnificationMap.put(new Pair<Type, Type>(type1, type2), mag);
    }

    private void initMagnificationMap() {
        magnificationMap = new HashMap<Pair<Type, Type>, Double>();
        // 火の攻撃
        addMagnification(Type.Fire, Type.Water, MAG_SMALL);
        addMagnification(Type.Fire, Type.Hurricane, MAG_LARGE);
        // 水の攻撃
        addMagnification(Type.Water, Type.Hurricane, MAG_SMALL);
        addMagnification(Type.Water, Type.Fire, MAG_LARGE);
        // 風の攻撃
        addMagnification(Type.Hurricane, Type.Fire, MAG_SMALL);
        addMagnification(Type.Hurricane, Type.Water, MAG_LARGE);
        // 光の攻撃
        addMagnification(Type.Holy, Type.Dark, MAG_LARGE);
        // 闇の攻撃
        addMagnification(Type.Dark, Type.Holy, MAG_LARGE);
    }

    private double getTypeMagnification(Type type) {
        Pair<Type, Type> key = new Pair<Type, Type>(this.type, type);
        if (magnificationMap.containsKey(key)) {
            return magnificationMap.get(key);
        } else {
            return MAG_MIDDLE;
        }

        // より簡潔な実装
        // return magnificationMap.getOrDefault(key, MAG_MIDDLE);
    }

    @Override
    public void run(Monster attacker, Monster other) {
        int damage = this.getDamage(attacker, other);
        other.damaged(damage);
    }

    private int getDamage(Monster attacker, Monster other) {
        if (this.currentPP > 0) {
            this.currentPP--;
            double damage = 1.0 * this.power * this.getTypeMagnification(other.type) * attacker.getAP() / other.getBP();
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