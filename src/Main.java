class Main {
    public static void main(String args[]) {
        Monster monster1 = new Monster("ナンデヤ根", "自然", new String[] {"パンチ", "キック", "エルボー"}, 100);
        Monster monster2 = new Monster("ヒト陽炎", "炎", new String[] {"火を吐く"}, 150);
        Monster monster3 = new Monster("カネカメ", "Water", new String[] {"みずてっぽう"}, 130);

        monster1.display();
        monster2.display();
        monster3.display();
    }
}
