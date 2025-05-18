package main;

import components.Attacker;
import components.Defender;
import interaction.Attack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Main
{
    static HashSet<String> phisicalATK = new HashSet<>();

    static HashSet<String> specialATK = new HashSet<>();
    static int minHit;
    static int maxHit;

    static String type1;

    static String type2;

    static Attacker attacker;

    static int atk;
    static int sAtk;
    static int level;

    static boolean stab;
    static double efectivity;

    static Defender defender;

    static int def;

    static int sDef;

    static String defType1;
    static String defType2;

    static HashMap<String, Attack> types = new HashMap<>();


    public static void main(String[] args) {
        String[] phisical = {"Normal", "Fighting", "Flying", "Ground", "Rock", "Bug", "Ghost", "Poison"};
        String[] special = {"Fire", "Water", "Electric", "Grass", "Ice", "Psychic", "Dragon"};

        phisicalATK.addAll(List.of(phisical));
        specialATK.addAll(List.of(special));

        setInteractions();

        description();

        createAttacker();

        createDefender();

        while (true)
        {
            createMenu();

            int option;

            try {
                option = readNumber();
            }catch (InputMismatchException e)
            {
                System.out.println("Invalid input. The option needs to be a integer.");
                continue;
            }
            switch (option)
            {
                case 1:
                    atack();
                    break;

                case 2:
                    changeAttacker();
                    break;

                case 3:
                    changeDefender();
                    break;

                case 0:
                    System.out.println("Thank you for using the program. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }


        }
    }

    private static void atack()
    {
        System.out.println("What is the attack power?");
        int power;
        try {
            power = readNumber();
        }catch (InputMismatchException e)
        {
            System.out.println("Invalid input. The option needs to be a integer.");
            return;
        }

        boolean comprobator = set1Type();
        if (!comprobator)
        {
            return;
        }

        if (phisicalATK.contains(type1))
        {
            stab = false;
            if (type1.equals(attacker.getAttackerType1()) || type1.equals(attacker.getAttackerType2()))
            {
                stab = true;
            }

            atk = Attacker.getAtk();

            def =  Defender.getDef();

            Attack attack = types.get(type1);

            defType1 = Defender.getDefType1();
            defType2 = Defender.getDefType2();

            efectivity = 1;

            if (attack.weak.contains(defType1))
            {
                efectivity = efectivity * 0.5;
            }

            if (attack.weak.contains(defType2))
            {
                efectivity = efectivity * 0.5;
            }

            if (attack.strong.contains(defType1))
            {
                efectivity = efectivity * 2;
            }

            if (attack.strong.contains(defType2))
            {
                efectivity = efectivity * 2;
            }

            if (attack.noDmg.contains(defType1)||attack.noDmg.contains(defType2))
            {
                efectivity = efectivity * 0;
            }

            level = Attacker.getLevel();

            minHit = Attack.minHit(stab, atk, def, efectivity, level, power);
            maxHit = Attack.maxHit(stab, atk, def, efectivity, level, power);


        }else if (specialATK.contains(type1))
        {
            stab = false;
            if (type1.equals(attacker.getAttackerType1()) || type1.equals(attacker.getAttackerType2()))
            {
                stab = true;
            }

            atk = Attacker.getSpecial();

            def =  Defender.getSpecial();

            Attack attack = types.get(type1);

            defType1 = Defender.getDefType1();
            defType2 = Defender.getDefType2();

            efectivity = 1;

            if (attack.weak.contains(defType1))
            {
                efectivity = efectivity * 0.5;
            }

            if (attack.weak.contains(defType2))
            {
                efectivity = efectivity * 0.5;
            }

            if (attack.strong.contains(defType1))
            {
                efectivity = efectivity * 2;
            }

            if (attack.strong.contains(defType2))
            {
                efectivity = efectivity * 2;
            }

            if (attack.noDmg.contains(defType1)||attack.noDmg.contains(defType2))
            {
                efectivity = efectivity * 0;
            }

            level = Attacker.getLevel();

            minHit = Attack.minHit(stab, atk, def, efectivity, level, power);
            maxHit = Attack.maxHit(stab, atk, def, efectivity, level, power);
        }

        System.out.println("The minimal damage is: " + minHit + "ps");
        System.out.println("The maximal damage is: " + maxHit + "ps");
    }

    private static void changeAttacker()
    {
        System.out.println("That's your Attacker:\n" + attacker.toString() );
        System.out.println("""
                What do tou whant change?
                1. All
                2. Attack
                3. Special Attack
                4. Types
                5. Level
                0. Exit""");

        int option;
        try {
            option = readNumber();
        }catch (InputMismatchException e)
        {
            System.out.println("Invalid input. The option needs to be a integer.");
            return;
        }
        switch (option)
        {
            case 1:
                createAttacker();
                break;

            case 2:
                System.out.println("Thats your attack?: " );

                try {
                    atk = readNumber();
                    if (atk<0)
                    {
                        System.out.println("You must enter a positive integer.");
                        return;
                    }
                }catch (InputMismatchException e)
                {
                    System.out.println("Invalid input. The option needs to be a integer.");
                    return;
                }

                attacker.setAtk(atk);
                break;

            case 3:
                System.out.println("Thats your special attack?: " );

                try {
                    sAtk = readNumber();
                    if (sAtk<0)
                    {
                        System.out.println("You must enter a positive integer.");
                        return;
                    }
                }catch (InputMismatchException e)
                {
                    System.out.println("Invalid input. The option needs to be a integer.");
                    return;
                }

                attacker.setSpecial(sAtk);
                break;

            case 4:
                setPkType();

                attacker.setAttackerType1(type1);
                attacker.setAttackerType2(type2);
                break;

            case 5:
                System.out.println("Thats your level?: " );

                try {
                    level = readNumber();
                    if (level<0)
                    {
                        System.out.println("You must enter a positive integer.");
                        return;
                    }
                }catch (InputMismatchException e)
                {
                    System.out.println("Invalid input. The option needs to be a integer.");
                    return;
                }

                attacker.setLevel(level);
                break;

            case 0:
                break;

            default:
                System.out.println("Invalid input. The option needs to be between 0 and 5.");
                break;
        }

    }

    private static void changeDefender()
    {
        System.out.println("That's your defender:\n" + defender.toString());
        System.out.println("""
                What do tou whant change?
                1. All
                2. Attack
                3. Special Attack
                4. Type
                0. Exit""");
        int option;
        try {
            option = readNumber();
        }catch (InputMismatchException e)
        {
            System.out.println("Invalid input. The option needs to be a integer.");
            return;
        }
        switch (option)
        {
            case 1:
                createDefender();
                break;

            case 2:
                System.out.println("Thats your defense?: " );

                try {
                    def = readNumber();
                    if (def<0)
                    {
                        System.out.println("You must enter a positive integer.");
                        return;
                    }
                }catch (InputMismatchException e)
                {
                    System.out.println("Invalid input. The option needs to be a integer.");
                    return;
                }

                defender.setDef(def);
                break;

            case 3:
                System.out.println("Thats your special attack?: " );

                try {
                    sDef = readNumber();
                    if (sDef<0)
                    {
                        System.out.println("You must enter a positive integer.");
                        return;
                    }
                }catch (InputMismatchException e)
                {
                    System.out.println("Invalid input. The option needs to be a integer.");
                    return;
                }

                defender.setSpecial(sDef);
                break;


            case 4:
                setPkType();

                defender.setDefType1(type1);
                defender.setDefType2(type2);
                break;

            case 0:
                break;

            default:
                System.out.println("Invalid input. The option needs to be between 0 and 4.");
                break;
        }
    }

    private static void createAttacker()
    {
        System.out.println("Let's create the attacker");

        System.out.println("What is the Pokemon attack?:");
        try {
            atk = readNumber();
            if (atk<0)
            {
                System.out.println("You must enter a positive integer.");
                return;
            }
        }catch (InputMismatchException e)
        {
            System.out.println("You must enter a integer.");
            return;
        }

        System.out.println("What is the Pokemon special attack?:");
        try {
            sAtk = readNumber();
            if (sAtk<0)
            {
                System.out.println("You must enter a positive integer.");
                return;
            }
        }catch (InputMismatchException e)
        {
            System.out.println("You must enter a integer");
            return;
        }

        System.out.println("What is the Pokemon level?:");
        try {
            level = readNumber();
            if (level<0)
            {
                System.out.println("You must enter a positive integer.");
                return;
            }
        }catch (InputMismatchException e)
        {
            System.out.println("You must enter a integer");
            return;
        }

        boolean comprobator = setPkType();
        if (!comprobator)
        {
            return;
        }

        attacker = new Attacker(atk, sAtk, level, type1, type2);
    }

    private static void createDefender()
    {
        System.out.println("Let's create the defender");

        System.out.println("What is the Pokemon defense?:");
        try {
            def = readNumber();
            if (def<0)
            {
                System.out.println("You must enter a positive integer.");
                return;
            }
        }catch (InputMismatchException e)
        {
            System.out.println("You must enter a integer");
            return;
        }

        System.out.println("What is the Pokemon special?:");
        try {
            sDef = readNumber();
            if (sDef<0)
            {
                System.out.println("You must enter a positive integer.");
                return;
            }
        }catch (InputMismatchException e)
        {
            System.out.println("You must enter a integer");
            return;
        }

        boolean comprobator = setPkType();
        if (!comprobator)
        {
            return;
        }

        defender = new Defender(def, sDef, defType1, defType2);

    }

    private static void createMenu()
    {
        System.out.println("""
                Select the action:
                1. Attack
                2. Change the attacker
                3. Change the defender
                0. Exit
                Select the action: """);
    }

    private static void description()
    {
        System.out.println("""
                This is a 1st Gen calculator atack program.
                In this proyect we only have the 1st Gen Pokemon types without critical hits.
                """);
    }

    private static int readNumber()
    {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    private static boolean set1Type()
    {
        System.out.println("""
                1.Bug           8.Grass         15.Water
                2.Dragon        9.Ground
                3.Electric      10.Ice
                4.Fighting      11.Normal
                5.Fire          12.Poison
                6.Flying        13.Psychic
                7.Ghost         14.Rock
                
                If you select other number the tipe will be normal.
                Select the type:""");

        int typeOption;
        try {
            typeOption = readNumber();
        }catch (InputMismatchException e)
        {
            System.out.println("You must enter a integer");
            return false;
        }

        switch (typeOption)
        {
            case 1:
                type1 = "Bug";
                break;

            case 2:
                type1 = "Dragon";
                break;

            case 3:
                type1 = "Electric";
                break;

            case 4:
                type1 = "Fighting";
                break;

            case 5:
                type1 = "Fire";
                break;

            case 6:
                type1 = "Flying";
                break;

            case 7:
                type1 = "Ghost";
                break;

            case 8:
                type1 = "Grass";
                break;

            case 9:
                type1 = "Ground";
                break;

            case 10:
                type1 = "Ice";
                break;

            case 12:
                type1 = "Poison";
                break;

            case 13:
                type1 = "Psychic";
                break;

            case 14:
                type1 = "Rock";
                break;

            case 15:
                type1 = "Water";
                break;

            default:
                type1 = "Normal";
                break;
        }
        return true;
    }

    private static void setInteractions()
    {
    String RUTA = "types_attack.txt";

    File file = new File(RUTA);
    if (!file.exists())
    {
        System.out.println("The file with the interactions does not exist");
    }

    try {
        int a;
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine())
        {
            type1 =  sc.nextLine();

            String[] strong = sc.nextLine().split(", ");
            strong[0] = strong[0].substring(1);
            if (!strong[0].equals("none-"))
            {
                a = strong.length;
                strong[a-1] = strong[a-1].substring(0, strong[a-1].length() - 1);
            }
            else
            {
                strong[0] = "none";
            }

            String[] weak = sc.nextLine().split(", ");
            weak[0] = weak[0].substring(1);
            if (!weak[0].equals("none-"))
            {
                a = weak.length;
                weak[a-1] = weak[a-1].substring(0,weak[a-1].length()-1);
            } else
            {
                weak[0] = "none";
            }

            String[] noDmg = sc.nextLine().split(", ");
            noDmg[0] = noDmg[0].substring(1);
            if (!noDmg[0].equals("none-"))
            {
                a = noDmg.length;
                noDmg[a-1] = noDmg[a-1].substring(0,noDmg[a-1].length()-1);
            }
            else
            {
                noDmg[0] = "none";
            }

            Attack attack = new Attack(type1);

            attack.weak.addAll(List.of(weak));
            attack.noDmg.addAll(List.of(noDmg));
            attack.strong.addAll(List.of(strong));

            types.put(type1, attack);
        }
    }catch (FileNotFoundException e)
    {
        System.out.println("The file with the interactions does not exist");
    }
}

    private static boolean setPkType()
    {
        System.out.println("""
                1.Bug           8.Grass         15.Water
                2.Dragon        9.Ground
                3.Electric      10.Ice
                4.Fighting      11.Normal
                5.Fire          12.Poison
                6.Flying        13.Psychic
                7.Ghost         14.Rock
                
                If you select other number the tipe will be normal.
                Select the 1st Pokemon type:""");

        int typeOption;
        try {
            typeOption = readNumber();
        }catch (InputMismatchException e)
        {
            System.out.println("You must enter a integer");
            return false;
        }

        switch (typeOption)
        {
            case 1:
                type1 = "Bug";
                break;

            case 2:
                type1 = "Dragon";
                break;

            case 3:
                type1 = "Electric";
                break;

            case 4:
                type1 = "Fighting";
                break;

            case 5:
                type1 = "Fire";
                break;

            case 6:
                type1 = "Flying";
                break;

            case 7:
                type1 = "Ghost";
                break;

            case 8:
                type1 = "Grass";
                break;

            case 9:
                type1 = "Ground";
                break;

            case 10:
                type1 = "Ice";
                break;

            case 12:
                type1 = "Poison";
                break;

            case 13:
                type1 = "Psychic";
                break;

            case 14:
                type1 = "Rock";
                break;

            case 15:
                type1 = "Water";
                break;

            default:
                type1 = "Normal";
                break;
        }

        System.out.println("""
                1.Bug           8.Grass         15.Water
                2.Dragon        9.Ground        0. No have other type
                3.Electric      10.Ice
                4.Fighting      11.Normal
                5.Fire          12.Poison
                6.Flying        13.Psychic
                7.Ghost         14.Rock
                
                If you select other number the Pokemon don´t have other type.
                Select the 2nd Pokemon type:""");

        try {
            typeOption = readNumber();
        }catch (InputMismatchException e)
        {
            System.out.println("You must enter a integer");
            return false;
        }

        switch (typeOption)
        {
            case 1:
                type2 = "Bug";
                break;

            case 2:
                type2 = "Dragon";
                break;

            case 3:
                type2 = "Electric";
                break;

            case 4:
                type2 = "Fighting";
                break;

            case 5:
                type2 = "Fire";
                break;

            case 6:
                type2 = "Flying";
                break;

            case 7:
                type2 = "Ghost";
                break;

            case 8:
                type2 = "Grass";
                break;

            case 9:
                type2 = "Ground";
                break;

            case 10:
                type2 = "Ice";
                break;

            case 12:
                type2 = "Poison";
                break;

            case 13:
                type2 = "Psychic";
                break;

            case 14:
                type2 = "Rock";
                break;

            case 15:
                type2 = "Water";
                break;

            default:
                type2 = "none";
                break;
        }

        return true;
    }

}
