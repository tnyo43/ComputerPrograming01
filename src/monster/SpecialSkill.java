package monster;

import util.Pair;

public class SpecialSkill extends Skill {
    private int recoverHP;
    private int raiseMyAP;
    private int raiseMyBP;
    private int raiseOtherAP;
    private int raiseOtherBP;

    private SpecialSkill () {}

    SpecialSkill(
        String name, int recoverHP,
        Pair<Integer, Integer> raiseMyPoint,
        Pair<Integer, Integer> raiseOtherPoint,
        int pp
    ) {
        this.name = name;
        this.recoverHP = recoverHP;
        this.maxPP = this.currentPP = pp;
        this.raiseMyAP = raiseMyPoint.left;
        this.raiseMyBP = raiseMyPoint.right;
        this.raiseOtherAP = raiseOtherPoint.left;
        this.raiseOtherBP = raiseOtherPoint.right;
    }

    @Override
    protected void run(Monster attacker, Monster other) {
        if (this.currentPP > 0) {
            this.currentPP--;

            attacker.recover(this.recoverHP);
            attacker.raiseAP(this.raiseMyAP);
            attacker.raiseBP(this.raiseMyBP);

            other.raiseAP(this.raiseOtherAP);
            other.raiseBP(this.raiseOtherBP);
        } else {
            return;
        }
    }

    @Override
    protected SpecialSkill copy() {
        return new SpecialSkill(
                        this.name, this.recoverHP,
                        new Pair<Integer, Integer>(this.raiseMyAP, this.raiseMyBP),
                        new Pair<Integer, Integer>(this.raiseOtherAP, this.raiseOtherBP),
                        this.maxPP
                    );
    }

    private String rankStr(int rank) {
        String sign = (rank > 0) ? "+" : "";
        return sign + Integer.toString(rank);
    }

    @Override
    public String toString() {
        return String.format("%s(%d, (%s/%s), (%s/%s), (%d/%d))",
                    this.name, this.recoverHP,
                    rankStr(this.raiseMyAP), rankStr(this.raiseMyBP),
                    rankStr(this.raiseOtherAP), rankStr(this.raiseOtherBP),
                    this.currentPP, this.maxPP
                );
    }
}