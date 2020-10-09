# 課題11

## 問題文

```
上司：攻撃ではない技を追加することになったんだ。『HPを回復する技』とか『APを上昇させる技』とか『相手のBPを下降させる技』とかね
あなた：それは対戦が面白くなっていいですね！きっと「ポケモ○」みたいに人気になりますよ！
上司：あー聞こえない聞こえない。とりあえずHPを回復する技を実装してみよう
あなた：どうやって実装するんだろう。『すごさ』をマイナスにするとか？
上司：それでも実現できるけど、ここでは『技』を『攻撃技』と『特殊技』に分離することで実現してみよう。抽象クラスの『Skill』は次のようになるよ
```

`monster/Skill.java`

```java
package monster;

public abstract class Skill {
    protected String name;
    protected int maxPP;
    protected int currentPP;

    public static Skill get(int index) {
        Skill s = skills[index];
        return s.copy();
    }

    public String getName() {
        return this.name;
    }

    public abstract void run(Monster attacker, Monster other);

    public abstract Skill copy();

    public abstract String toString();

    private static Skill skills[] = {
        new AttackSkill("火を吐く", Type.FIRE, 40, 5),
        new AttackSkill("灼熱フィスト", Type.FIRE, 30, 20),
        new AttackSkill("みずふんしゃ", Type.WATER, 1, 10),
        new AttackSkill("疾風パンチ", Type.HURRICANE, 10, 30),
        new AttackSkill("かまいたちエルボー", Type.HURRICANE, 20, 20),
        new AttackSkill("ボコボコキック", Type.DARK, 30, 10),
        new AttackSkill("乱反射", Type.HOLY, 100, 1),

        // あたらしく追加した回復の技（引数は先頭から順番に、技の名前、HPの回復量、PP）
        new SpecialSkill("ひとやすみ", 20, 10)
    };
}
```


## 課題

- `Skill class`を抽象クラスにして、それを継承した`AttackSkill class`と`SpecialSkill class`を作成しましょう
    - 既存の`Skill class`を`AttackSkill class`と呼ぶことになります
    - `SpecialSkill class`は『名前』『PP』『HPの回復量の整数値』を持ちます
    - `Skill class`、`AttackSkill class`、`SpecialSkill class`の持つべきフィールド変数をそれぞれ考えましょう
        - `Skill class`のフィールド変数は、サブクラスでも使えるように`protected`にしてください
    - `Skill class`には、次の抽象メソッドを準備して、継承したクラスで実装してください。
        - `public abstract void run(Monster attack, Monster other);`
            - `AttackSkill class`（既存の`Skill class`）の`attack`メソッドと同じ役割で、技を放つときに呼び出します。`AttackSkill class`での実装は`attack`メソッドと同じでよいです
        - `public abstract Skill copy();`
            - `Skill`のコンストラクタを呼び出すメソッドです。実装側では、コンストラクタを呼び出すことでコピーを作成して返すようにしてください（課題06参照）
        - `public abstract String toString();`
            - `AttackSkill class`（既存の`Skill class`）の`toString`メソッドと同じ役割です。クラスごとに保持するフィールドが異なるので、実装時に注意してください
- `Skill#skills`に『PP』が10、『HP』を20回復する技『ひとやすみ』を追加し、使用して動作を確かめてください
    - 蓄積してるダメージが20以上のときは20回復することを確認してください
    - 蓄積してるダメージが20未満のときはHPが満タンになることを確認してください

## 考察課題

- `Skill class`を継承させることで2種類の技を定義しました。なぜ継承を行ったのでしょうか。`Skill class`の配列`skills`に`AttackSkill class`と`SpecialSkill class`の両方のオブジェクトが含まれていることを参考にして考えましょう
