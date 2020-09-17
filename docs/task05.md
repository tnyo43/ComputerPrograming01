# 課題05

## 問題文

```
上司：さて、そろそろパチモン対戦をできるようにしようか
あなた：よ！待ってました！！！
上司：パチモンは技を受けたらダメージが減るようにしたいな。現状では、その時点でのHPしか記憶できないから、「最大HP」と「現在のHP」を持つ変数をそれぞれ追加しよう。Monsterクラスには「攻撃する」ためのメソッドが必要になるね。
あなた：攻撃する度に、そのメソッドを呼び出せばいいんですね！
上司：その通り。それに攻撃するたびにSkillのppも使うほど減るはずだから、「最大PP」と「現在のPP」がわかるようにしたいね
```

## 課題

- 技のPP、パチモンのHPを「最大値」と「現在の値」のそれぞれを管理できるようにしましょう
    - 合わせてそれぞれのクラスの`display`メソッドと`toString`メソッドを修正しましょう
- Skillクラスに`getDamage`メソッドを追加しましょう。その技が与えるダメージを返すメソッドです
    - 与えられるダメージは、技の『すごさ』と同じです
    - 使用するたびに、技の現在のPPが1減ります
    - PPが0の技は発動しないのでダメージは0です
- Monsterクラスに攻撃したときの処理を行う`attack`メソッドを追加しましょう
    - 引数は「使う技の、技配列内の番号」と「相手モンスター」
    - 与えたダメージの分、相手の現在のHPが減ります
        - ただしHPは0になったら、それ以上減りません
    - まだタイプの相性を気にしなくていいです