# 課題09

## 模範解答

`monster/Skill.java`

```java
package monster;

import java.util.HashMap;
import java.util.Map;

import util.Pair;

public class Skill {
    private String name;
    private Type type;
    private int power;
    private int maxPP;
    private int currentPP;

    private static final double MAG_LARGE = 1.3;
    private static final double MAG_MIDDLE = 1.0;
    private static final double MAG_SMALL = 0.7;

    private static Map<Pair<Type, Type>, Double> magnificationMap;

    private Skill () {}

    private Skill(String name, Type type, int power, int pp) {
        this.name = name;
        this.type = type;
        this.power = power;
        this.maxPP = this.currentPP = pp;

        if (magnificationMap == null) {
            initMagnificationMap();
        }
    }

    private void initMagnificationMap() {
        magnificationMap = new HashMap<Pair<Type, Type>, Double>();
        // 火の攻撃
        magnificationMap.put(new Pair<Type, Type>(Type.Fire, Type.Water), MAG_SMALL);
        magnificationMap.put(new Pair<Type, Type>(Type.Fire, Type.Hurricane), MAG_LARGE);
        // 水の攻撃
        magnificationMap.put(new Pair<Type, Type>(Type.Water, Type.Hurricane), MAG_SMALL);
        magnificationMap.put(new Pair<Type, Type>(Type.Water, Type.Fire), MAG_LARGE);
        // 風の攻撃
        magnificationMap.put(new Pair<Type, Type>(Type.Hurricane, Type.Fire), MAG_SMALL);
        magnificationMap.put(new Pair<Type, Type>(Type.Hurricane, Type.Water), MAG_LARGE);
        // 光の攻撃
        magnificationMap.put(new Pair<Type, Type>(Type.Holy, Type.Dark), MAG_LARGE);
        // 闇の攻撃
        magnificationMap.put(new Pair<Type, Type>(Type.Dark, Type.Holy), MAG_LARGE);
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

    protected int getDamage(Monster attacker, Monster other) {
        if (this.currentPP > 0) {
            this.currentPP--;
            double damage = 1.0 * this.power * this.getTypeMagnification(other.type) * attacker.ap / other.bp;
            return (int)damage;
        } else {
            return 0;
        }
    }

    public static Skill get(int index) {
        Skill s = skills[index];
        return new Skill(s.name, s.type, s.power, s.maxPP);
    }

    @Override
    public String toString() {
        return String.format("%s(%s, %d, (%d/%d))", this.name, this.type, this.power, this.currentPP, this.maxPP);
    }

    private static Skill skills[] = {
        new Skill("火を吐く", Type.Fire, 40, 5),
        new Skill("灼熱フィスト", Type.Fire, 30, 20),
        new Skill("みずふんしゃ", Type.Water, 1, 10),
        new Skill("疾風パンチ", Type.Hurricane, 10, 30),
        new Skill("かまいたちエルボー", Type.Hurricane, 20, 20),
        new Skill("ボコボコキック", Type.Dark, 30, 10),
        new Skill("乱反射", Type.Holy, 100, 1)
    };
}
```

## 考察課題模範解答

Q. なぜ`magnificationMap`は`static`なのか

A. タイプの相性はゲーム全体で固定なので、初期化した以降変更は加えられず、全てのケースで同じものを参照することになるから

Q. なぜ`Map`で実装しないのか

A. `Map`は`interface`だから。対して、`HashMap`は`Map`の具体的な実装を行っている

Q. コードがどのように読みやすくなったら考えてみましょう

A. 「相性についての情報が一行で得られる」「ネストが浅くなった」。ただし、「実行時の速度低下」「意図しない上書きが発生しうる」などのデメリットもある

- 
- 

## ポイント

- `magnificationMap`の初期化は1回になるようにコンストラクタで制御
    - 制御しないと、コンストラクタが呼ばれる旅に`magnificationMap`を作り直すことになる
