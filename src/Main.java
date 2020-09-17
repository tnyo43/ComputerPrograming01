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
                Skill.get(0)
            },
            150, 70, 40
        );
        Monster monster3 = new Monster(
            "カネカメ",
            Type.Water,
            new Skill[] {
                Skill.get(2)
            },
            150, 70, 40
        );

        monster1.display();
        monster2.display();
        monster3.display();

        System.out.println("--- 攻撃！ 1 -> 2 ---\n");
        monster1.attack(0, monster2);
        System.out.println("--- 攻撃！ 1 -> 3 ---\n");
        monster1.attack(0, monster3);

        monster1.display();
        monster2.display();
        monster3.display();
    }
}
