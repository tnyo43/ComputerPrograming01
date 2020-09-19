package monster;

public class Monster {
    private String name;
    protected Type type;
    private Skill skills[];
    private int maxHP;
    private int currentHP;
    protected int ap;
    protected int bp;

    private Monster() {}

    public Monster(String name, Type type, Skill skills[], int hp, int ap, int bp) {
        this.name = name;
        this.type = type;
        this.skills = skills;
        this.maxHP = this.currentHP = hp;
        this.ap = ap;
        this.bp = bp;
    };

    public void display() {
        String skillStr = this.skills[0].toString();
        for (int i = 1; i < this.skills.length; i++) {
            skillStr += ", " + this.skills[i];
        }

        System.out.println(String.format("名前：%s", this.name));
        System.out.println(String.format("タイプ：%s", this.type));
        System.out.println(String.format("技：%s", skillStr));
        System.out.println(String.format("HP：(%d/%d)", this.currentHP, this.maxHP));
        System.out.println(String.format("AP：%d, BP : %d", this.ap, this.bp));
        System.out.println();
    }

    public void run(int skillIndex, Monster other) {
        this.skills[skillIndex].run(this, other);
    }

	protected void damaged(int damage) {
        this.currentHP = Math.max(0, this.currentHP - damage);
	}

	public void recover(int recoverHP) {
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
