# 課題08

## 問題文

```
あなた：ちょっと、Monsterクラスのtype、ap、bpってpublicになっちゃってますけど、これってよくないですよね
上司：よくわかったね。じゃあpackageを使ってみよう
あなた：「monster」パッケージを作って、Monster、Type、Skillをその中にいれますね。さっきのフィールドのアクセス修飾子を修正すればいいですね
```

## 課題

- 会話の内容にそうように、変更を加えましょう
    - `monster`という名前のディレクトリを作成します。以降、`monster package`と呼びます
    - 「Monster.java」「Skill.java」「Type.java」を`monster package`に移動してください
    - これら3つのファイルの先頭に「package monster;」と1行追加してください
    - 「Main.java」の先頭に「impoer monster.*;」を追加してください
- パッケージを作ることで得られる恩恵を考えましょう
    - `Monster class`の`type`、`ap`、`bp`のアクセス修飾子を考えましょう
    - `public`ではないことの利点を考えてください

## 考察課題

- フィールド変数がむやみやたらに`public`になるとなにがまずいでしょう
