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
