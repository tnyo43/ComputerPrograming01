# 課題03

## 模範解答

`Skill.java`

```java
public class Skill {
    private String name;
    private Type type;
    private int power;
    private int pp;

    private Skill () {}

    public Skill(String name, Type type, int power, int pp) {
        this.name = name;
        this.type = type;
        this.power = power;
        this.pp = pp;
    }

    @Override
    public String toString() {
        return String.format("%s(%s/%d/%d)", this.name, this.type, this.power, this.pp);
    }
}
```

`Monster.java`

```java
public class Monster {
    private String name;
    private Type type;
    private Skill skills[];
    private int hp;

    private Monster() {}

    public Monster(String name, Type type, Skill skills[], int hp) {
        this.name = name;
        this.type = type;
        this.skills = skills;
        this.hp = hp;
    };

    public void display() {
        String skillStr = this.skills[0].toString();
        for (int i = 1; i < this.skills.length; i++) {
            skillStr += ", " + this.skills[i];
        }

        System.out.println(String.format("名前：%s", this.name));
        System.out.println(String.format("タイプ：%s", this.type));
        System.out.println(String.format("技：%s", skillStr));
        System.out.println(String.format("HP：%d", this.hp));
        System.out.println();
    }
}
```

`Main.java`

```java
class Main {
    public static void main(String args[]) {
        Monster monster1 = new Monster(
            "ナンデヤ根",
            Type.HURRICANE,
            new Skill[] {
                new Skill("疾風パンチ", Type.HURRICANE, 10, 30),
                new Skill("ボコボコキック", Type.DARK, 30, 10),
                new Skill("かまいたちエルボー", Type.HURRICANE, 20, 20)
            },
            100
        );
        Monster monster2 = new Monster(
            "ヒト陽炎",
            Type.FIRE,
            new Skill[] {
                new Skill("火を吐く", Type.FIRE, 40, 5),
                new Skill("灼熱フィスト", Type.FIRE, 30, 20),
            },
            150
        );
        Monster monster3 = new Monster(
            "カネカメ",
            Type.WATER,
            new Skill[] {
                new Skill("みずふんしゃ", Type.WATER, 1, 10),
                new Skill("乱反射", Type.HOLY, 100, 1)
            },
            130
        );

        monster1.display();
        monster2.display();
        monster3.display();
    }
}
```

## ポイント

- フィールド名は意味が通ればなんでもいい
- 実行ようクラスでは、各モンスターに`Skill`を3つ以上渡す
