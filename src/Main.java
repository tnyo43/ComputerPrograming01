class Main {
    public static void main(String args[]) {
        Monster monster1 = new Monster(
            "ナンデヤ根",
            Type.Hurricane,
            new Skill[] {
                Skill.hurricaneAttack,
                Skill.kickKickKick,
                Skill.hurricaneElbow
            },
            100
        );
        Monster monster2 = new Monster(
            "ヒト陽炎",
            Type.Fire,
            new Skill[] {
                Skill.fireAttack,
                Skill.heatFist
            },
            150
        );
        Monster monster3 = new Monster(
            "カネカメ",
            Type.Water,
            new Skill[] {
                Skill.waterAttack,
                Skill.reflection
            },
            130
        );

        monster1.display();
        monster2.display();
        // monster3.display();

        System.out.println("--- 攻撃！ 1 -> 2 ---\n");
        monster1.attack(0, monster2);

        monster1.display();
        monster2.display();
    }
}
