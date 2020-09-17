# 課題06

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

`Main.java`

```java
class Main {
    public static void main(String args[]) {
        Monster monster1 = new Monster(
            "ナンデヤ根",
            Type.Hurricane,
            new Skill[] {
                Skill.get(5)
            },
            100
        );
        Monster monster2 = new Monster(
            "ヒト陽炎",
            Type.Fire,
            new Skill[] {
                Skill.get(5)
            },
            150
        );

        monster1.display();
        monster2.display();

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
技：ボコボコキック(闇, 30, (10/10))
HP：(100/100)

名前：ヒト陽炎
タイプ：火
技：ボコボコキック(闇, 30, (10/10))
HP：(150/150)

--- 攻撃！ 1 -> 2 ---

名前：ナンデヤ根
タイプ：風
技：ボコボコキック(闇, 30, (9/10))
HP：(100/100)

名前：ヒト陽炎
タイプ：火
技：ボコボコキック(闇, 30, (10/10))
HP：(120/150)
```

## 考察課題

Q. 会話内で触れられる「staticなオブジェクト」の性質とは

A. 同じオブジェクトを共有すること

Q. 「使ってない技のPPが減った」理由を考えましょう

A. 共有している技なので、一方でPPを減らすともう一方でも減って見える


## ポイント

- `Skill#skills`は`private`で`static`にしておく
    - `skills`を直接参照されるとまた同じオブジェクトを参照する可能性があるので、必ず`private`にする
- `get`は`public`で`static`にしておく
    - 中で`static`な`Skill object`を生成して返す
- 同じ技を持ったパチモンを用意し、攻撃した側の技のみPPが減っていることを確認する
