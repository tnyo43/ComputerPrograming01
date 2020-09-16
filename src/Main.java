class Main {
    public static void main(String args[]) {
        Monster monster1 = new Monster("ナンデヤ根", Type.Hurricane, new String[] {"パンチ", "キック", "エルボー"}, 100);
        Monster monster2 = new Monster("ヒト陽炎", Type.Fire, new String[] {"火を吐く"}, 150);
        Monster monster3 = new Monster("カネカメ", Type.Water, new String[] {"みずてっぽう"}, 130);

        monster1.display();
        monster2.display();
        monster3.display();
    }
}
