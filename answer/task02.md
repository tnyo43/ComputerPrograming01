# 課題01

## 模範解答

`Type.java`

```java
public enum Type {
    Fire, Water, Hurricane, Holy, Dark;

    @Override
    public String toString() {
        switch (this) {
            case Fire:
                return "火";
            case Water:
                return "水";
            case Hurricane:
                return "風";
            case Holy:
                return "光";
            case Dark:
                return "闇";
        }
        return "";
    }
}
```

`Monster.java`

```java
public class Monster {
    private String name;
    // Type型
    private Type type;
    private String skills[];
    private int hp;

    private Monster() {}

    // Type型に変更
    public Monster(String name, Type type, String skills[], int hp) {
        this.name = name;
        this.type = type;
        this.skills = skills;
        this.hp = hp;
    };

    public void display() {
        System.out.println(String.format("名前：%s", this.name));
        System.out.println(String.format("タイプ：%s", this.type));
        System.out.println(String.format("技：%s", String.join(", ", this.skills)));
        System.out.println(String.format("HP：%d", this.hp));
        System.out.println();
    }
}
```

`Main.java`

```java
class Main {
    public static void main(String args[]) {
        Monster monster1 = new Monster("ナンデヤ根", Type.Hurricane, new String[] {"パンチ", "キック", "エルボー"}, 100);
        Monster monster2 = new Monster("ヒト陽炎", Type.Fire, new String[] {"火を吐く"}, 150);
        Monster monster3 = new Monster("カネカメ", Type.Water, new String[] {"みずてっぽう"}, 130);

        monster1.display();
        monster2.display();
        monster3.display();
    }
}
```

## 考察課題模範解答

Q. switch文とenumの相性がよい理由

A. Switch文は型のとりうる値を網羅する必要がある。StringやIntでswitchさせる場合は`default case`についての処理、たとえばエラー処理や戻り値が`null`になるような処理、を必要とする。。これでは可読性が下がり、不必要な処理を追加することでバグを生みやすい。Enumでは、自分で定義した有限個のケースに対応するだけなので、網羅しやすいのでバグを生みにくく可読性も高くなる。

Q. `@Override`修飾子の目的と効果

A. `@Override`をつけると、スーパークラスのメソッドをオーバーライドすることになる。スーパーメソッドがない、もしくは型の合わない場合はコンパイルエラーになる。よって、クラスのオーバーライドが明示的になって可読性が上がる

## ポイント


- `Type enum`は好きなタイプを定義する
    - 値の名前はなんでもよいが、属性と意味が通るような名前にする
    - `toString`は`public`でないといけない
    - `toString`の戻り値は、会話中に出てくる「火・水・風・光・闇」でないといけない
- `Monster class`のタイプを`Type`型で表現する
