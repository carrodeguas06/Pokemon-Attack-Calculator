package interaction;

import java.util.HashSet;

public class Attack {

    private String type;

    public HashSet<String> weak = new HashSet<>();

    public HashSet<String> strong = new HashSet<>();

    public HashSet<String> noDmg = new HashSet<>();

    public Attack(String type) {
        this.type = type;
    }

    public static int minHit(boolean stab, int attack, int defense, double efectivity, int level, int pAtk) {
        double hit;
        int minHit;

        if (!stab)
        {
            hit = 0.01 * 1 * efectivity * 85 * (((0.2 * level + 1) * attack * pAtk)/(25*defense));
            minHit = Math.toIntExact(Math.round(hit));
        }
        else
        {
            hit = 0.01 * 1.5 * efectivity * 85 * (((0.2 * level + 1) * attack * pAtk)/(25*defense));
            minHit = Math.toIntExact(Math.round(hit));
        }

        return minHit;
    }

    public static int maxHit(boolean stab, int attack, int defense, double efectivity, int level, int pAtk) {
        double hit;
        int maxHit;

        if (!stab)
        {
            hit = 0.01 * 1 * efectivity * 100 * (((0.2 * level + 1) * attack * pAtk)/(25*defense));
            maxHit = Math.toIntExact(Math.round(hit));
        }
        else
        {
            hit = 0.01 * 1.5 * efectivity * 100 * (((0.2 * level + 1) * attack * pAtk)/(25*defense));
            maxHit = Math.toIntExact(Math.round(hit));
        }

        return maxHit;
    }
}
