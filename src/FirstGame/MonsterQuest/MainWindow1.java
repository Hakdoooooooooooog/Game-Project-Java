package FirstGame.MonsterQuest;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import FirstGame.MonsterQuest.Characters.RPGEnemy.Enemy;
import FirstGame.MonsterQuest.Characters.RPGPlayer.Player;
import FirstGame.MonsterQuest.Scripts.GameProcess;

//Class MainWindow1
public class MainWindow1 {

    // =======================Initializing Player skill set array value and size========================
    int[] pSkillDmg = { 0, 0, 0 };
    int[] manaCost = { 0, 0, 0 };
    String[] pSkillName = { "", "", "", "" };
    // ========================Initializing Enemy skill set array value and size========================
    int[] eSkillDmg = { 0, 0, 0 };
    String[] eSkillName = { "", "", "" };

    String starterClass, eClass; // variable for storing Player and Enemy classes

    // =======================Player and Enemy Encapsulated Class=========================
    Player p1;
    Enemy e1;

    GameProcess gp; // Calling GameProcess class
    Scanner in; // Initiating Scanner class

    public MainWindow1(int eLife, int eAtkDmg, int eDef, int pLife, int pAtkDmg, int pDef, int pMana,
            int pClassID, double eClassID, String starterClass, String eClass) {

        this.starterClass = starterClass;
        this.eClass = eClass;

        p1 = new Player(pLife, pAtkDmg, pDef, pMana, pClassID);
        e1 = new Enemy(eLife, eAtkDmg, eDef, eClassID);
    }

    public void startGame() {
        boolean confirming = true;
        String choose;

        System.out.println("============================================================");
        System.out.println("|                      Monster Quest                       |");
        System.out.println("============================================================");
        choosePlayerChar();
        playerStats(p1.getPLife(), p1.getPAtkDmg(), p1.getPDef(), p1.getPMana(), starterClass);

        while (confirming) {
            in = new Scanner(System.in);

            System.out.print("\nDo you want to choose this class? Yes or no? ");
            choose = in.nextLine();
            if (choose.equalsIgnoreCase("Yes")) {
                confirming = false;
                gp = new GameProcess(pSkillDmg, manaCost, eSkillDmg, pSkillName, eSkillName, 0, 0, 0, 0); // Player Skill and Enemy skill set values
                enemyStats(e1.getELife(), e1.getEAtkDmg(), e1.getEDef(), e1.getEnemyClass());
                try {
                    gp.battlefield(e1.getELife(), e1.getEAtkDmg(), e1.getEDef(), p1.getPLife(), p1.getPAtkDmg(), 
                                    p1.getPDef(), p1.getPMana(), starterClass, eClass);
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            } else if (choose.equalsIgnoreCase("No")) {
                choosePlayerChar();
                playerStats(p1.getPLife(), p1.getPAtkDmg(), p1.getPDef(), p1.getPMana(),
                        starterClass);
            } else {
                System.out.println("Invalid option.");
            }
        }
    }

    public String enemyRandomizer() {
        e1.seteClassID(e1.getEClassID());
        eClass = e1.getEnemyClass();
        return eClass;
    }

    public void choosePlayerChar() {
        boolean validityClass = true;
        in = new Scanner(System.in);

        do {
        	try {
        		System.out.print("Please select a character\n[1]Knight\n[2]Mage\n[3]Tank\n--> ");
                p1.setpClassID(in.nextInt());
                if (p1.getPClassID() > 0 && p1.getPClassID() < p1.getPClassesSize()) {
                    starterClass = p1.getStarterClass();
                    enemyRandomizer();
                    validityClass = false;
                } else {
                    System.out.println("Invalid choice.");
                }
        	}catch (InputMismatchException ie) {
        		ie.printStackTrace();
        	}
        } while (validityClass);
    }

    public void playerStats(int pLife, int pAtkDmg, int pDef, int pMana, String starterClass) {
        switch (starterClass) {
            case "Knight":
                pLife = 2000;
                pAtkDmg = 40;
                pDef = 50;
                pMana = 300;
                p1.setPlayerStats(pLife, pAtkDmg, pDef, pMana);
                System.out.println(p1.toString());
                break;
            case "Mage":
                pLife = 1250;
                pAtkDmg = 100;
                pDef = 50;
                pMana = 500;
                p1.setPlayerStats(pLife, pAtkDmg, pDef, pMana);
                System.out.println(p1.toString());
                break;
            case "Tank":
                pLife = 3000;
                pAtkDmg = 40;
                pDef = 150;
                pMana = 200;
                p1.setPlayerStats(pLife, pAtkDmg, pDef, pMana);
                System.out.println(p1.toString());
                break;
            default:
                System.out.println("Default!");
        }
    }

    public void enemyStats(int eLife, int eAtkDmg, int eDef, String eClass) {
        switch (eClass) {
            case "Skeleton":
                eLife = 1250;
                eAtkDmg = 30;
                eDef = 20;
                e1.setEnemyStats(eLife, eAtkDmg, eDef, eClass);
                break;
            case "Guardian":
                eLife = 2000;
                eAtkDmg = 50;
                eDef = 50;
                e1.setEnemyStats(eLife, eAtkDmg, eDef, eClass);
                break;
            case "God":
                eLife = 3000;
                eAtkDmg = 70;
                eDef = 100;
                e1.setEnemyStats(eLife, eAtkDmg, eDef, eClass);
                break;
            case "Snake":
                eLife = 1000;
                eAtkDmg = 100;
                eDef = 20;
                e1.setEnemyStats(eLife, eAtkDmg, eDef, eClass);
                break;
            default:
                System.out.println("Default!");
        }
    }
}
