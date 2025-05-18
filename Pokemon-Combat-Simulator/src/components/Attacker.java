package components;

public class Attacker {
    private static int atk;
    private static int special;

    private static int level;

    private static String attackerType1;
    private static String attackerType2;

    public Attacker(int atk, int sAttacker, int level, String attackerType1, String attackerType2) {
        this.atk = atk;
        this.special = sAttacker;
        this.level = level;
        this.attackerType1 = attackerType1;
        this.attackerType2 = attackerType2;
    }

    public static int getAtk() {
        return atk;
    }

    public String getAttackerType1() {
        return attackerType1;
    }

    public String getAttackerType2() {
        return attackerType2;
    }

    public static int getLevel() {
        return level;
    }
    public void setAtk(int atk) {
        this.atk = atk;
    }

    public static void setAttackerType1(String attackerType1) {
        Attacker.attackerType1 = attackerType1;
    }

    public static void setAttackerType2(String attackerType2) {
        Attacker.attackerType2 = attackerType2;
    }

    public static void setLevel(int level) {
        Attacker.level = level;
    }

    public static int getSpecial() {
        return special;
    }

    public void setSpecial(int special) {
        this.special = special;
    }

    @Override
    public String toString() {
        return "Attack: " + atk +
                "\nSpecial attack: " + special +
                "\nLevel: " + level +
                "\nType 1: " + attackerType1 +
                "\nType 2: " + attackerType2;
    }
}
