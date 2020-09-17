# 課題05

## 模範解答

`Skill.java`

```java
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
```

`Monster.java`

```java
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

    public void attack(int skillIndex, Monster monster) {
        int damage = this.skills[skillIndex].getDamage();
        monster.currentHP = Math.max(0, monster.currentHP - damage);
    }
}
```

`Main.java`

```java
class Main {
    public static void main(String args[]) {
        Monster monster1 = new Monster(
            "ナンデヤ根",
            Type.Hurricane,
            new Skill[] {
                Skill.hurricaneAttack,
                Skill.kickKickKick,
                Skill.hurricaneElbow
            },
            100
        );
        Monster monster2 = new Monster(
            "ヒト陽炎",
            Type.Fire,
            new Skill[] {
                Skill.fireAttack,
                Skill.heatFist
            },
            150
        );
        Monster monster3 = new Monster(
            "カネカメ",
            Type.Water,
            new Skill[] {
                Skill.waterAttack,
                Skill.reflection
            },
            130
        );

        monster1.display();
        monster2.display();
        // monster3.display();

        System.out.println("--- 攻撃！ 1 -> 2 ---\n");
        monster1.attack(0, monster2);

        monster1.display();
        monster2.display();
    }
}
```

`OUTPUT`

```
名前：ナンデヤ根
タイプ：風
技：疾風パンチ(風, 10, (30/30)), ボコボコキック(闇, 30, (10/10)), かまいたちエルボー(風, 20, (20/20))
HP：(100/100)

名前：ヒト陽炎
タイプ：火
技：火を吐く(火, 40, (5/5)), 灼熱フィスト(火, 30, (20/20))
HP：(150/150)

--- 攻撃！ 1 -> 2 ---

名前：ナンデヤ根
タイプ：風
技：疾風パンチ(風, 10, (29/30)), ボコボコキック(闇, 30, (10/10)), かまいたちエルボー(風, 20, (20/20))
HP：(100/100)

名前：ヒト陽炎
タイプ：火
技：火を吐く(火, 40, (5/5)), 灼熱フィスト(火, 30, (20/20))
HP：(140/150)
```

## ポイント

- 最大値を管理するために、`Monster class`の`hp`、`Skill class`の`pp`をそれぞれ`current〇〇`、`max〇〇`で管理
- `getDamage`は技のすごさを返す
    - `currentPP`が1以上かどうかで分岐、デクリメントを忘れない
    - ゲームの対戦システムを扱うクラスから呼び出されることが想定されるので`public`
- `attack`は`Skill#getDamage`でダメージを計算したあとに`other`の`currentHP`を減らす
    - ゲームの対戦システムを扱うクラスから呼び出されることが想定されるので`public`
- 攻撃した側のPPが減り、攻撃された側のHPが減っていることを確認
