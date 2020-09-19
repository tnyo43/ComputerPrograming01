package monster;

public class SpecialSkill extends Skill {
    private int recoverHP;

    private SpecialSkill () {}

    SpecialSkill(String name, int recoverHP, int pp) {
        this.name = name;
        this.recoverHP = recoverHP;
        this.maxPP = this.currentPP = pp;
    }

    @Override
    public void run(Monster attacker, Monster _other) {
        if (this.currentPP > 0) {
            this.currentPP--;
            attacker.recover(this.recoverHP);
        } else {
            return;
        }
    }

    @Override
    public SpecialSkill copy() {
        return new SpecialSkill(this.name, this.recoverHP, this.maxPP);
    }

    @Override
    public String toString() {
        return String.format("%s(%d, (%d/%d))", this.name, this.recoverHP, this.currentPP, this.maxPP);
    }
}
