# 課題07

## 模範解答

`Skill.java`

```java
public class Skill {
    private String name;
    private Type type;
    private int power;
    private int maxPP;
    private int currentPP;

    private static final double MAG_LARGE = 1.3;
    private static final double MAG_MIDDLE = 1.0;
    private static final double MAG_SMALL = 0.7;

    private Skill () {}

    private Skill(String name, Type type, int power, int pp) {
        this.name = name;
        this.type = type;
        this.power = power;
        this.maxPP = this.currentPP = pp;
    }

    private double getTypeMagnification(Type type) {
        switch (this.type) {
            case Fire:
                switch (type) {
                    case Hurricane:
                        return MAG_LARGE;
                    case Water:
                        return MAG_SMALL;
                    default:
                        return MAG_MIDDLE;
                }
            case Water:
                switch (type) {
                    case Fire:
                        return MAG_LARGE;
                    case Hurricane:
                        return MAG_SMALL;
                    default:
                        return MAG_MIDDLE;
                }
            case Hurricane:
                switch (type) {
                    case Water:
                        return MAG_LARGE;
                    case Fire:
                        return MAG_SMALL;
                    default:
                        return MAG_MIDDLE;
                }
            case Holy:
                switch (type) {
                    case Dark:
                        return MAG_LARGE;
                    default:
                        return MAG_MIDDLE;
                }
            case Dark:
                switch (type) {
                    case Holy:
                        return MAG_LARGE;
                    default:
                        return MAG_MIDDLE;
                }
        }
        return MAG_MIDDLE;
    }

    public int getDamage(Monster attacker, Monster other) {
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

`OUTPUT`

```
名前：ナンデヤ根
タイプ：風
技：疾風パンチ(風, 10, (30/30)), かまいたちエルボー(風, 20, (20/20))
HP：(100/100)
AP：60, BP : 90

名前：ヒト陽炎
タイプ：火
技：火を吐く(火, 40, (5/5))
HP：(150/150)
AP：70, BP : 40

名前：カネカメ
タイプ：水
技：みずふんしゃ(水, 1, (10/10))
HP：(150/150)
AP：70, BP : 40

--- 攻撃！ 1 -> 2 ---

--- 攻撃！ 1 -> 3 ---

名前：ナンデヤ根
タイプ：風
技：疾風パンチ(風, 10, (28/30)), かまいたちエルボー(風, 20, (20/20))
HP：(100/100)
AP：60, BP : 90

名前：ヒト陽炎
タイプ：火
技：火を吐く(火, 40, (5/5))
HP：(140/150)
AP：70, BP : 40

名前：カネカメ
タイプ：水
技：みずふんしゃ(水, 1, (10/10))
HP：(131/150)
AP：70, BP : 40
```
