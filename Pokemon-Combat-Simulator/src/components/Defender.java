package components;

public class Defender {

    private static int def;
    private static int special;

    private static String defType1;
    private static String defType2;

    public Defender(int def, int sDefender, String defType1, String defType2) {
        this.def = def;
        this.special = sDefender;
        this.defType1 = defType1;
        this.defType2 = defType2;
    }

    public static int getDef() {
        return def;
    }

    public static String getDefType1() {
        return defType1;
    }

    public static String getDefType2() {
        return defType2;
    }



    public void setDef(int def) {
        this.def = def;
    }

    public static void setDefType1(String defType1) {
        Defender.defType1 = defType1;
    }

    public static void setDefType2(String defType2) {
        Defender.defType2 = defType2;
    }

    public static int getSpecial() {
        return special;
    }

    public void setSpecial(int special) {
        this.special = special;
    }

    @Override
    public String toString() {
        return "Defense: " + def +
                "\nSpecial defense: " + special +
                "\nType 1: " + defType1 +
                "\nType 2: " + defType2;
    }
}
