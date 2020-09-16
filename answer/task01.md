# 課題01

## 模範解答

`Monster class`

```java
public class Monster {
    private String name;
    private String type;
    private String skills[];
    private int hp;

    private Monster() {}

    public Monster(String name, String type, String skills[], int hp) {
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

`Main class`

```java
class Main {
    public static void main(String args[]) {
        Monster monster1 = new Monster("ナンデヤ根", "自然", new String[] {"パンチ", "キック", "エルボー"}, 100);
        Monster monster2 = new Monster("ヒト陽炎", "炎", new String[] {"火を吐く"}, 150);
        Monster monster3 = new Monster("カネカメ", "Water", new String[] {"みずふんしゃ"}, 130);

        monster1.display();
        monster2.display();
        monster3.display();
    }
}
```

`OUTPUT

```
前：ナンデヤ根
タイプ：自然
技：パンチ, キック, エルボー
HP：100

名前：ヒト陽炎
タイプ：炎
技：火を吐く
HP：150

名前：カネカメ
タイプ：Water
技：みずふんしゃ
HP：130
```

## ポイント

- Monster class は基本的にはこれと同じになる
    - フィールド名は意味が通ればなんでもよい
- 「パチモンはいくつかの技を覚えるんだよ」といっているので配列になる（ListでもOK）
    - 技は複数なので、`skills`, `skillList`など
- 「HP」は問題文では大文字だが、フィールド変数としては`hp`など
- Main class では monster を3つ定義
    - パチモンの名前、タイプ、技、HPはなんでもいい
- ディスプレイの結果は情報が読み取れる状況になっていることを確認する。
