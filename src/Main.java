import java.util.Scanner;

import monster.*;

class Main {
    public static void main(String args[]) {
        Monster monster1 = new Monster(
            "ナンデヤ根",
            Type.Hurricane,
            new Skill[] {
                Skill.get(3),
                Skill.get(4),
                Skill.get(7)
            },
            100, 60, 90
        );
        Monster monster2 = new Monster(
            "ヒト陽炎",
            Type.Fire,
            new Skill[] {
                Skill.get(0),
                Skill.get(1),
                Skill.get(7)
            },
            150, 70, 40
        );

        monster1.display();
        monster2.display();

        System.out.println("========== 対戦スタート！ ==========");

        boolean turnMonster1 = true;
        Scanner scanner = new Scanner(System.in);

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
                    attacker.run(index-1, defender);
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
