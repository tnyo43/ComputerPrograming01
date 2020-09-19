# 課題11

## 模範解答

`monster/AttackSkill.java`

```java
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
```

`monster/SpecialSkill.java`

```java
package monster;

public class SpecialSkill extends Skill {
    private int recoverHP;

    private SpecialSkill () {}

    SpecialSkill(String name, int recoverHP, int pp) {
        this.name = name;
        this.recoverHP = recoverHP;
        this.maxPP = this.currentPP = pp;
    }

    @Override
    public void run(Monster attacker, Monster _other) {
        if (this.currentPP > 0) {
            this.currentPP--;
            attacker.recover(this.recoverHP);
        } else {
            return;
        }
    }

    @Override
    public SpecialSkill copy() {
        return new SpecialSkill(this.name, this.recoverHP, this.maxPP);
    }

    @Override
    public String toString() {
        return String.format("%s(%d, (%d/%d))", this.name, this.recoverHP, this.currentPP, this.maxPP);
    }
}
```

`monster/Monster.java`

```java
package monster;

public class Monster {
    private String name;
    protected Type type;
    private Skill skills[];
    private int maxHP;
    private int currentHP;
    protected int ap;
    protected int bp;

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

    public void run(int skillIndex, Monster other) {
        this.skills[skillIndex].run(this, other);
    }

	protected void damaged(int damage) {
        this.currentHP = Math.max(0, this.currentHP - damage);
	}

	public void recover(int recoverHP) {
        this.currentHP = Math.min(this.maxHP, this.currentHP + recoverHP);
	}
}
```


## 考察課題模範解答

Q. なぜ継承を行ったのでしょうか。

A. `AttackSkill class`と`SpecialSkill class`を両方とも`Skill class`のオブジェクトとして扱うことができる

## ポイント

- 継承したメソッドをそれぞれのクラスに合うように実装している

