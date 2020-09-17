# 課題08

## 模範解答

`monster/Monster.java`

```java
package monster;

public class Monster {
    private String name;
    protected Type type;     // <-
    private Skill skills[];
    private int maxHP;
    private int currentHP;
    protected int ap;     // <-
    protected int bp;     // <-

    ...
}
```

`monster/Skill.java`

```java
package monster;

public class Skill {

    ...

    protected int getDamage(Monster attacker, Monster other) {
        ...
    }
}
```

`monster/Type.java`

```java
package monster;

public enum Type {
    ...
}
```

`Main.java`

```java
import monster.*;

class Main {
    ...
}
```

## 考察課題模範解答

Q. フィールド変数がむやみやたらに`public`になるとなにがまずいでしょう

A. 外部のクラスから意図しない変更を加えられる可能性があるため。クラス内で完結することであれば`private`、関連するクラスのみ変更を加えられるようにするには`protected`を使うなど、必要に応じてアクセス権を変更する必要がある

## ポイント

- パッケージを作成し、それに必要な変更（package ...; import ...;）を加える
- `Monster class`の`type`などは、`Skill class`のダメージの計算にしか使用しないので、`protected`をつける
- `Skill#getDamage`も`Monster class`からしか使われないので、`protected`をつける
