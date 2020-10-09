# 課題12

## 模範解答

`monster/SpecialSkill.java`

```java
package monster;

import util.Pair;

public class SpecialSkill extends Skill {
    private int recoverHP;
    private int raiseMyAP;
    private int raiseMyBP;
    private int raiseOtherAP;
    private int raiseOtherBP;

    private SpecialSkill () {}

    SpecialSkill(
        String name, int recoverHP,
        Pair<Integer, Integer> raiseMyPoint,
        Pair<Integer, Integer> raiseOtherPoint,
        int pp
    ) {
        this.name = name;
        this.recoverHP = recoverHP;
        this.maxPP = this.currentPP = pp;
        this.raiseMyAP = raiseMyPoint.left;
        this.raiseMyBP = raiseMyPoint.right;
        this.raiseOtherAP = raiseOtherPoint.left;
        this.raiseOtherBP = raiseOtherPoint.right;
    }

    @Override
    protected void run(Monster attacker, Monster other) {
        if (this.currentPP > 0) {
            this.currentPP--;

            attacker.recover(this.recoverHP);
            attacker.raiseAP(this.raiseMyAP);
            attacker.raiseBP(this.raiseMyBP);

            other.raiseAP(this.raiseOtherAP);
            other.raiseBP(this.raiseOtherBP);
        } else {
            return;
        }
    }

    @Override
    protected SpecialSkill copy() {
        return new SpecialSkill(
                        this.name, this.recoverHP,
                        new Pair<Integer, Integer>(this.raiseMyAP, this.raiseMyBP),
                        new Pair<Integer, Integer>(this.raiseOtherAP, this.raiseOtherBP),
                        this.maxPP
                    );
    }

    private String rankStr(int rank) {
        String sign = (rank > 0) ? "+" : "";
        return sign + Integer.toString(rank);
    }

    @Override
    public String toString() {
        return String.format("%s(%d, (%s/%s), (%s/%s), (%d/%d))",
                    this.name, this.recoverHP,
                    rankStr(this.raiseMyAP), rankStr(this.raiseMyBP),
                    rankStr(this.raiseOtherAP), rankStr(this.raiseOtherBP),
                    this.currentPP, this.maxPP
                );
    }
}
```

`monster/AttackSkill.java`

```java
public class AttackSkill extends Skill {

    ...

    private int getDamage(Monster attacker, Monster other) {
        if (this.currentPP > 0) {
            this.currentPP--;
            double damage = 1.0 * this.power * this.getTypeMagnification(other.type) * attacker.getAP() / other.getBP();
            return (int)damage;
        } else {
            return 0;
        }
    }

    ...
}
```

`monster/Monster.java`

```java
package monster;

public class Monster {

    private static final int RANK_UPPER_BOUND = 3;
    private static final int RANK_LOWER_BOUND = -3;
    private static final double RANK_MAGNIFICATION = 0.3;

    private String name;
    protected Type type;
    private Skill skills[];
    private int maxHP;
    private int currentHP;
    private int ap;
    private int bp;
    private int rankAP;
    private int rankBP;

    private Monster() {}

    public Monster(String name, Type type, Skill skills[], int hp, int ap, int bp) {
        this.name = name;
        this.type = type;
        this.skills = skills;
        this.maxHP = this.currentHP = hp;
        this.ap = ap;
        this.bp = bp;
        this.rankAP = this.rankBP = 0;
    };

    private String rankStr(int rank) {
        String sign = (rank > 0) ? "+" : "";
        return sign + Integer.toString(rank);
    }

    public void display() {
        String skillStr = this.skills[0].toString();
        for (int i = 1; i < this.skills.length; i++) {
            skillStr += ", " + this.skills[i];
        }
        String rankAPStr = rankStr(this.rankAP);
        String rankBPStr = rankStr(this.rankBP);

        System.out.println(String.format("名前：%s", this.name));
        System.out.println(String.format("タイプ：%s", this.type));
        System.out.println(String.format("技：%s", skillStr));
        System.out.println(String.format("HP：(%d/%d)", this.currentHP, this.maxHP));
        System.out.println(String.format("AP：%d(%s), BP : %d(%s)", this.ap, rankAPStr,this.bp, rankBPStr));
        System.out.println();
    }

    protected int getAP() {
        double resultAP = (1 + RANK_MAGNIFICATION * this.rankAP) * this.ap;
        return (int)resultAP;
    }

    protected int getBP() {
        double resultBP = (1 + RANK_MAGNIFICATION * this.rankBP) * this.bp;
        return (int)resultBP;
    }

    protected void raiseAP(int rank) {
        this.rankAP = Math.min(RANK_UPPER_BOUND, Math.max(RANK_LOWER_BOUND, this.rankAP + rank));
    }

    protected void raiseBP(int rank) {
        this.rankBP = Math.min(RANK_UPPER_BOUND, Math.max(RANK_LOWER_BOUND, this.rankBP + rank));
    }

    public void run(int skillIndex, Monster other) {
        this.skills[skillIndex].run(this, other);
    }

    protected void damaged(int damage) {
        this.currentHP = Math.max(0, this.currentHP - damage);
    }

    protected void recover(int recoverHP) {
        this.currentHP = Math.min(this.maxHP, this.currentHP + recoverHP);
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

## ポイント

- `SpecialSkill class`のフィールドに、自分と相手のランクの変化量を管理するものを追加
    - コンストラクタで初期化
        - 例の実装では、区分を明確にするために`Pair`を使っている
    - 実際に変更する時は、`Monster class`の`raiseAP`、`raiseBP`を利用
- `AttackSkill class`は、ダメージ計算に`getAP`、`getBP`で値を取得するようになった
- `Monster class`は、自分のランクの変化を管理するフィールドが追加された
    - `raiseAP`、`raiseBP`で更新
        - ランクの最大値、最小値を超えないように注意
    - `getAP`、`getBP`で、ランクに基づく計算を行う
- それぞれのクラスの`toString`の更新
