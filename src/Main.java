class Main {
    public static void main(String args[]) {
        Monster monster1 = new Monster(
            "ナンデヤ根",
            Type.Hurricane,
            new Skill[] {
                new Skill("疾風パンチ", Type.Hurricane, 10, 30),
                new Skill("ボコボコキック", Type.Dark, 30, 10),
                new Skill("かまいたちエルボー", Type.Hurricane, 20, 20)
            },
            100
        );
        Monster monster2 = new Monster(
            "ヒト陽炎",
            Type.Fire,
            new Skill[] {
                new Skill("火を吐く", Type.Fire, 40, 5),
                new Skill("灼熱フィスト", Type.Fire, 30, 20),
            },
            150
        );
        Monster monster3 = new Monster(
            "カネカメ",
            Type.Water,
            new Skill[] {
                new Skill("みずふんしゃ", Type.Water, 1, 10),
                new Skill("乱反射", Type.Holy, 100, 1)
            },
            130
        );

        monster1.display();
        monster2.display();
        monster3.display();
    }
}
