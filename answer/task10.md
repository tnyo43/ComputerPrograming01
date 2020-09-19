# 課題10

## 模範解答

`monster/Skill.java`

```java
package monster;

import java.util.HashMap;
import java.util.Map;

import util.Pair;

public class Skill {

    ...

	public String getName() {
		return this.name;
	}
}
```

`monster/Monster.java`


```java
package monster;

public class Monster {

    ...

    // テキストを追加しました
    public void attack(int skillIndex, Monster other) {
        Skill skill = this.skills[skillIndex];
        System.out.println(String.format("『%s』を使った！", skill.getName()));
        int damage = skill.getDamage(this, other);
        other.currentHP = Math.max(0, other.currentHP - damage);
    }

    public boolean canFight() {
        return this.currentHP > 0;
    }

	public Object getName() {
		return this.name;
    }
    
    public String skillListString() {
        String str = String.format("1. %s", this.skills[0]);
        for (int i = 1; i < this.skills.length; i++) {
            str += String.format(", %d. %s", (i+1), this.skills[i]);
        }
        return str;
    }
}
```

`Main.java`

```java
import java.util.Scanner;

import monster.*;

class Main {
    public static void main(String args[]) {
        Monster monster1 = new Monster(
            "ナンデヤ根",
            Type.Hurricane,
            new Skill[] {
                Skill.get(3),
                Skill.get(4)
            },
            100, 60, 90
        );
        Monster monster2 = new Monster(
            "ヒト陽炎",
            Type.Fire,
            new Skill[] {
                Skill.get(0),
                Skill.get(1)
            },
            150, 70, 40
        );

        monster1.display();
        monster2.display();

        boolean turnMonster1 = true;
        Scanner scanner = new Scanner(System.in);

        System.out.println("========== 対戦スタート！ ==========");

        while(monster1.canFight() && monster2.canFight()) {
            Monster attacker = turnMonster1 ? monster1 : monster2;
            Monster defender = turnMonster1 ? monster2 : monster1;
            System.out.println(String.format("--- 攻撃！ %s -> %s ---\n", attacker.getName(), defender.getName()));

            System.out.println("技を選択してください");
            System.out.println("技リスト");
            System.out.println(attacker.skillListString());
            
            while (true) {
                String txt = scanner.nextLine();
                try {
                    int index = Integer.parseInt(txt);
                    attacker.attack(index-1, defender);
                    break;
                } catch (Exception e) {
                    System.out.println("もう一度入力してください");
                }
            }

            System.out.println("=========== 現在の状態 =========== ");
            monster1.display();
            monster2.display();
            System.out.println("================================== ");

            turnMonster1 ^= true;
        }

        Monster winner = monster1.canFight() ? monster1 : monster2;
        System.out.println(String.format("%sの勝利！", winner.getName()));

        scanner.close();
    }
}
```

`OUTPUT`

```
名前：ナンデヤ根
タイプ：風
技：疾風パンチ(風, 10, (30/30)), かまいたちエルボー(風, 20, (20/20))
HP：(100/100)
AP：60, BP : 90

名前：ヒト陽炎
タイプ：火
技：火を吐く(火, 40, (5/5)), 灼熱フィスト(火, 30, (20/20))
HP：(150/150)
AP：70, BP : 40

========== 対戦スタート！ ==========
--- 攻撃！ ナンデヤ根 -> ヒト陽炎 ---

技を選択してください
技リスト
1. 疾風パンチ(風, 10, (30/30)), 2. かまいたちエルボー(風, 20, (20/20))
1
『疾風パンチ』を使った！
=========== 現在の状態 =========== 
名前：ナンデヤ根
タイプ：風
技：疾風パンチ(風, 10, (29/30)), かまいたちエルボー(風, 20, (20/20))
HP：(100/100)
AP：60, BP : 90

名前：ヒト陽炎
タイプ：火
技：火を吐く(火, 40, (5/5)), 灼熱フィスト(火, 30, (20/20))
HP：(140/150)
AP：70, BP : 40

================================== 
--- 攻撃！ ヒト陽炎 -> ナンデヤ根 ---

技を選択してください
技リスト
1. 火を吐く(火, 40, (5/5)), 2. 灼熱フィスト(火, 30, (20/20))
2
『灼熱フィスト』を使った！
=========== 現在の状態 =========== 
名前：ナンデヤ根
タイプ：風
技：疾風パンチ(風, 10, (29/30)), かまいたちエルボー(風, 20, (20/20))
HP：(70/100)
AP：60, BP : 90

名前：ヒト陽炎
タイプ：火
技：火を吐く(火, 40, (5/5)), 灼熱フィスト(火, 30, (19/20))
HP：(140/150)
AP：70, BP : 40

================================== 

（略）

--- 攻撃！ ヒト陽炎 -> ナンデヤ根 ---

技を選択してください
技リスト
1. 火を吐く(火, 40, (4/5)), 2. 灼熱フィスト(火, 30, (19/20))
3
もう一度入力してください
1
『火を吐く』を使った！
=========== 現在の状態 =========== 
名前：ナンデヤ根
タイプ：風
技：疾風パンチ(風, 10, (28/30)), かまいたちエルボー(風, 20, (19/20))
HP：(0/100)
AP：60, BP : 90

名前：ヒト陽炎
タイプ：火
技：火を吐く(火, 40, (3/5)), 灼熱フィスト(火, 30, (19/20))
HP：(109/150)
AP：70, BP : 40

================================== 
ヒト陽炎の勝利！
```

## ポイント

- 具体的な出力はなんでもよいが、以下の点は必須項目
    - そのターンが「誰から誰への攻撃」かわかる
    - 「使う技」のリストと「キーと技の対応」がわかる
    - 「現在のパチモンの状態」がそれぞれわかる（特にHP）
    - 勝利したパチモンがわかる
