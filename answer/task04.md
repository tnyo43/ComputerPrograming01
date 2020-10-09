# 課題04

## 模範解答

`Skill.java`

```java
public class Skill {
    private String name;
    private Type type;
    private int power;
    private int pp;

    private Skill () {}

    private Skill(String name, Type type, int power, int pp) {
        this.name = name;
        this.type = type;
        this.power = power;
        this.pp = pp;
    }

    @Override
    public String toString() {
        return String.format("%s(%s/%d/%d)", this.name, this.type, this.power, this.pp);
    }

    public static Skill fireAttack = new Skill("火を吐く", Type.FIRE, 40, 5);
    public static Skill heatFist = new Skill("灼熱フィスト", Type.FIRE, 30, 20);
    public static Skill waterAttack = new Skill("みずふんしゃ", Type.WATER, 1, 10);
    public static Skill hurricaneAttack = new Skill("疾風パンチ", Type.HURRICANE, 10, 30);
    public static Skill hurricaneElbow = new Skill("かまいたちエルボー", Type.HURRICANE, 20, 20);
    public static Skill kickKickKick = new Skill("ボコボコキック", Type.DARK, 30, 10);
    public static Skill reflection = new Skill("乱反射", Type.HOLY, 100, 1);
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
                Skill.hurricaneAttack,
                Skill.kickKickKick,
                Skill.hurricaneElbow
            },
            100
        );
        Monster monster2 = new Monster(
            "ヒト陽炎",
            Type.FIRE,
            new Skill[] {
                Skill.fireAttack,
                Skill.heatFist
            },
            150
        );
        Monster monster3 = new Monster(
            "カネカメ",
            Type.WATER,
            new Skill[] {
                Skill.waterAttack,
                Skill.reflection
            },
            130
        );

        monster1.display();
        monster2.display();
        monster3.display();
    }
}
```

## 考察課題模範解答

Q. 意図しない外部のクラスが、`Skill`オブジェクトを生成してしまうことで生じる問題はなんでしょう

A. 問題文であるように、`Skill`クラスの外でオブジェクトが生成されると、想定していない技を作ってしまったとしても探すのが困難になる。コンストラクタを`public`にしているということは、「このクラスのオブジェクトは好きに生成してもいい」という意味になる。`private`なコンストラクタにして「権限のあるクラスが生成したオブジェクトしか使えない」という制約を与えることができる。ただし、今後の課題で`Skill class`をパッケージに移してコンストラクタを`protected`にする。これは、「同一パッケージ内でしかオブジェクトを生成できない」という制約になっている

Q. Skill`オブジェクトの生成を`Skill class`のみ可能にするには、具体的にどうすればいいですか

A. コンストラクタを全て`private`にする

## ポイント

コンストラクタを`private`にして、クラス内で定義した`public`で`static`な変数を外のクラスで使う。クラス内で生成したオブジェクトしか存在しないので、オブジェクトの管理が容易になる。一方、静的なオブジェクトの変更は、同一オブジェクトを参照する外部にも影響を与えるので注意が必要（課題06参照）

