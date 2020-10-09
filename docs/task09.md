# 課題09

## 問題文

```
上司：新しいタイプを追加することになったよ
あなた：ちょっと待ってくださいよ！もうすでに「タイプ相性を扱うgetTypeMagnification」が長くなってるのに、これ以上増えたら大変なことになりますよ
上司：それなら、「getTypeMagnificationのリファクタリング」をしてみよう
あなた：リファクタリングって具体的にどうするんですか？
上司：リファクタリングの方法はいろいろあるけど、ここでは「Map」を使ってみよう！ここでの目的は「コード量を減らす」ことと「コードが読みやすくなる」ことだから意識して見てね
あなた：わかりました。ところで、新しいタイプって何が増えるんですが？
上司：「火のえ、火のと、水のえ、水のと、風のえ、風のと、光のえ、光のと、闇のえ、闇のと」が追加されるよ
あなた：今度は「○滅の刃」かよ
```

## 課題

- 2つの値のペアを扱うクラス`Pair`を実装しましょう
    - 新しいパッケージ、`util`パッケージを作成して、`Pair.java`を追加しましょう
    - `Pair.java`は以下を使用してください
        - `Pair<T, U>`の`T`や`U`は「仮型パラメータ」と呼ばれ、使用するときに好きなデータ型を渡すことができます
            - 例：`Pair<Integer, Boolean>`は int と boolean のペアを扱うクラス
            - 例：`Pair<Monster, Skill>`は Monster と Skill のペアを扱うクラス
        - 一つ目の値を取り出したいときは`.left`、二つ目の値を取り出したいときは`.right`を使います。
            - 例：`Pair<Integer, Boolean>`の`.left`はint型、`.right`はboolean型
            - 例：`Pair<Monster, Skill>`の`.left`はMonster型、`.right`はSkill型
- `Pair<Type, Type>`をkeyとし、`Double`をvalueとする`Map<Pair<Type, Type>, Double>`型の`magnificationMap`を作成してください
    - Mapは連想配列で、「keyに対応するvalue」を記憶しておく構造体です
    - 例えば物体の名前と色を記憶する`Map<String, Color> colorMap`は、「"tomoto" -> RED, "leaf" -> GREEN, "banana" -> YELLOW」という対応になっているとうれしいです
    - `magnificationMap`は「タイプのペアに対する、ダメージの倍率」を記憶しています
    - 具体的な実装は、以下を参考にしてください
        - `Map<Pair<Type, Type>, Double> magnificationMap`を`private`で`static`なフィールドとして定義します
        - コンストラクタで「もし`magnificationMap`が`null`」なら初期化します
            - `Map`の実装には`HashMap`を用います
            - タイプXとタイプYの相性が◎なら「(X, Y) -> `MAG_LARGE`」 、△なら「(X, Y) -> `MAG_SMALL`」を割り当てます
            - 相性が○なら何も割り当てません
        - `Skill#getTypeMagnification`では「2つのタイプの`magnificationMap`での割り当て」をチェックします
            - 割り当てられているなら、その倍率を返します
            - 割り当てがない場合は、「相性が○」なので`MAG_MIDDLE`を返します

## 考察課題

- 「`Map<Pair<Type, Type>, Double> magnificationMap`を`private`で`static`なフィールドとして定義します」とありますが、なぜ`static`なフィールドとして定義する必要があるのでしょうか。
- 「`Map`の実装には`HashMap`を用います」とありますが、`Map`の実装を`Map`で行わない理由はなんですか
    - ヒント：`Map<X, Y> map = new Map<X, Y>();`としたときにどのようなエラーが出るか確かめてみましょう
- コードがどのように読みやすくなったら考えてみましょう
    - （オプション）他にも読みやすい実装方法を自分で考えてみましょう

## 実装例

`util/Pair.java`

```java
package util;

public class Pair<T, U> {
    public T left;
    public U right;

    private Pair() {}

    public Pair(T left, U right) {
        this.left = left;
        this.right = right;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        Pair<T, U> p = (Pair<T, U>)obj;
        return this.left.equals(p.left) && this.right.equals(p.right);
    }

    @Override
    public int hashCode() {
        return 255 * this.left.hashCode() + this.right.hashCode();
    }
    
    @Override
    public String toString() {
        return String.format(
            "(%s, %s)",
            this.left.toString(),
            this.right.toString()
        );
    }
}
```

`monster/Skill.java`（一部のみ）

```java
package monster;

import java.util.HashMap;
import java.util.Map;

import util.Pair;

public class Skill {
    ...

    private static Map<Pair<Type, Type>, Double> magnificationMap = initMagnificationMap();

    private Skill(String name, Type type, int power, int pp) {
        ...
    }

    // magnificationMapの初期化のための補助関数
    private static void addMagnification(Map<Pair<Type, Type>, Double> m, Type type1, Type type2, double mag) {
        m.put(new Pair<>(type1, type2), mag);
    }

    private static Map<Pair<Type, Type>, Double> initMagnificationMap() {
        Map<Pair<Type, Type>, Double> m = new HashMap<>();
        // 火の攻撃

        addMagnification(m, Type.FIRE, Type.WATER, MAG_SMALL);
        addMagnification(m, Type.FIRE, Type.HURRICANE, MAG_LARGE);
        // 水の攻撃
        addMagnification(m, Type.WATER, Type.HURRICANE, MAG_SMALL);
        addMagnification(m, Type.WATER, Type.FIRE, MAG_LARGE);
        // 風の攻撃
        addMagnification(m, Type.HURRICANE, Type.FIRE, MAG_SMALL);
        addMagnification(m, Type.HURRICANE, Type.WATER, MAG_LARGE);
        // 光の攻撃
        addMagnification(m, Type.HOLY, Type.DARK, MAG_LARGE);
        // 闇の攻撃
        addMagnification(m, Type.DARK, Type.HOLY, MAG_LARGE);

        return m;
    }

    private double getTypeMagnification(Type type) {
        Pair<Type, Type> key = new Pair<Type, Type>(this.type, type);
        if (magnificationMap.containsKey(key)) {
            return magnificationMap.get(key);
        } else {
            return MAG_MIDDLE;
        }
    }

    ...
```

## おまけ

タイプの相性の計算は、気持ち的には switch 文を使って次のようにしたいと思うかもしれません。

```java
switch (new Pair<Type, Type>(this.type, type)) {
    case (new Pair<FIRE, WATER>):
        return ...
}
```

残念ながらJava の switch 文 は int や enum　の比較はできても、 object の比較はできません。これは Java という言語の仕様上の都合です。

たとえば、筆者の好きな OCaml という言語がありますが、パターンマッチングを用いて switch 文と同じように次のように書けたりします。使う言語の特性を知ると、より良い書き方（俗にいう○○らしい書き方）になり可読性や保守性があがります。

```OCaml
match (type1, type2) with
  | (FIRE, WATER) -> MAG_SMALL
  | (FIRE, HURRICANE) -> MAG_LARGE
  ...
  | _ -> MAG_MIDDLE
```
