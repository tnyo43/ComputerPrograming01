class Main {
    public static void main(String args[]) {
        Monster monster1 = new Monster(
            "ナンデヤ根",
            Type.Hurricane,
            new Skill[] {
                Skill.get(5)
            },
            100
        );
        Monster monster2 = new Monster(
            "ヒト陽炎",
            Type.Fire,
            new Skill[] {
                Skill.get(5)
            },
            150
        );

        monster1.display();
        monster2.display();

        System.out.println("--- 攻撃！ 1 -> 2 ---\n");
        monster1.attack(0, monster2);

        monster1.display();
        monster2.display();
    }
}
