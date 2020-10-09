# 課題07

## 問題文

```
上司：攻撃したときにパチモン『すごさ』だけじゃなくて『AP(攻撃力)』と『BP(防御力)』、それから『タイプ』も考慮するようにしよう
あなた：ますます「ポケ○ン」っぽくなりますね
上司：なにそれ知らないなあ。ちなみにこれらはそれぞれ整数型で、APは「Attack Point」、BPは「Block Point」だよ。ダメージの計算は次の式を参考にしてね
あなた：APとBPはどのように扱いますか？
上司：
```

タイプの計算には以下の式を用います

`d = P × M × A / B`

ただし、変数の意味は下の表を参考にしてください

| 変数名 | 意味 |
| :-: | -- |
| d | 与えるダメージ |
| P | 攻撃する技のすごさ |
| M | 技と攻撃されるパチモンのタイプの相性 |
| A | 攻撃するパチモンの攻撃力 |
| B | 攻撃されるパチモンの防御力 |

技と攻撃されるパチモンのタイプの相性（課題03）と`M`の値は以下の表を参考にしてください

| 相性（技のタイプ→攻撃される側のタイプ） | Mの値 |
| :--: | ---: |
| ◎ | 1.3 |
| ○ | 1.0 |
| △ | 0.7 |

## 課題

- `Monster class`に`AP`、`BP`のフィールドをそれぞれ追加しましょう
    - コンストラクタも修正してください
    - `display`メソッドも修正してください
- 上の表を参考に、`Skill#getDamage`を変更してダメージ計算式を上記の式に合うようにしましょう。
    - 引数に「攻撃するMonster」と「攻撃されるMonster」を指定できるようにしてください
    - 計算中は全て実数型で計算し、最後に整数型にキャストしてください。
    - 『技と攻撃されるパチモンのタイプの相性』の計算には、以下の`Skill#getTypeMagnifucation`メソッドを使ってください。
- 修正に伴って、`Monster class`のいくつかのフィールドのアクセス演算子を修正してください
    - 必要なものだけ変更するようにしてください
- 実行用クラスで以下のモンスターを準備、攻撃して、ダメージを見てみましょう
    - 用意するパチモン
        1. 『すごさ 10、タイプ 風』の技`A`を持った攻撃用パチモン1
        2. 『タイプ 火、BP 40、HP 150』の攻撃される用パチモン2
        3. 『タイプ 水、BP 40、HP 150』の攻撃される用パチモン3
    - 実行内容
        - 『パチモン1』が『パチモン2』に技`A`で攻撃
        - 『パチモン1』が『パチモン3』に技`A`で攻撃
    - 実行結果
        - 『パチモン2』が10のダメージを受けている
        - 『パチモン3』が19のダメージを受けている

`Skill#getTypeMagnifucation`

```java
private static final double MAG_LARGE = 1.3;
private static final double MAG_MIDDLE = 1.0;
private static final double MAG_SMALL = 0.7;

private double getTypeMagnification(Type type) {
    switch (this.type) {
        case FIRE:
            switch (type) {
                case HURRICANE:
                    return MAG_LARGE;
                case WATER:
                    return MAG_SMALL;
                default:
                    return MAG_MIDDLE;
            }
        case WATER:
            switch (type) {
                case FIRE:
                    return MAG_LARGE;
                case HURRICANE:
                    return MAG_SMALL;
                default:
                    return MAG_MIDDLE;
            }
        case HURRICANE:
            switch (type) {
                case WATER:
                    return MAG_LARGE;
                case FIRE:
                    return MAG_SMALL;
                default:
                    return MAG_MIDDLE;
            }
        case HOLY:
            switch (type) {
                case DARK:
                    return MAG_LARGE;
                default:
                    return MAG_MIDDLE;
            }
        case DARK:
            switch (type) {
                case HOLY:
                    return MAG_LARGE;
                default:
                    return MAG_MIDDLE;
            }
    }
    return MAG_MIDDLE;
}
```

## 考察課題

- アクセス演算子を変更することで生じる問題点を考えしょう
    - `public`を`private`にすることで生じる問題はなんでしょう（課題06参照）
    - `private`を`public`にすることで生じる問題はなんでしょう
