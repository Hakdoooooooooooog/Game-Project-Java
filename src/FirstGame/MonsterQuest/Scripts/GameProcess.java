package FirstGame.MonsterQuest.Scripts;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import FirstGame.MonsterQuest.MainWindow1;
import FirstGame.MonsterQuest.Characters.RPGEnemy.EnemySkills;
import FirstGame.MonsterQuest.Characters.RPGPlayer.PlayerSkills;

//Class GameProcess
public class GameProcess {
    boolean isDead;
    String winner;
    int turn = 1, maxTurn = 3, rechargeTurn = 2;

    MainWindow1 mw; // Calling MainWindow1 class to get the values of player and enemy stats.

    // Calling Player and enemy skill encapsulated class to store the values of player and enemy skills.
    PlayerSkills ps1;
    EnemySkills es1;

    Scanner ch;
    Random rand = new Random();
    Random rand2 = new Random();
    Random rand3 = new Random();

    public GameProcess(int[] pSkillDmg, int[] manaCost, int[] eSkillDmg, String[] pSkillName, String[] eSkillName,
            int newPLife, int newPMana, int newELife, int recharge) {
        ps1 = new PlayerSkills(pSkillDmg, manaCost, pSkillName, newPLife, newPMana, recharge);
        es1 = new EnemySkills(eSkillDmg, eSkillName, newELife);
    }

    public void battlefield(int eLife, int eAtkDmg, int eDef, int pLife, int pAtkDmg, int pDef, int pMana,
            String starterClass, String eClass) throws InterruptedException, IOException {
        boolean isRunning = true;
        new ProcessBuilder("powershell", "Clear-Host").inheritIO().start().waitFor();

        enemyStarterStats(eLife, eAtkDmg, eDef, es1.geteSkillDmg(), es1.geteSkillName(), eClass);
        playerStarterStats(pLife, pAtkDmg, pDef, pMana, ps1.getPlayerSkillDmg(), ps1.getManaCost(),
                ps1.getPlayerSkillName(), starterClass);

        while (isRunning) {
            if (isDead(ps1.getNewPlayerLife(), es1.getNewEnemyLife())) {
                isRunning = false;
                System.out.printf("\nThe winner for this round is: %s\n", winner);
            } else {
                if (turn <= 3) {
                    playerMove(es1.getNewEnemyLife(), eDef, pAtkDmg, ps1.getNewPlayerMana(), ps1.getRecharge(), ps1.getPlayerSkillDmg(), ps1.getManaCost(), ps1.getPlayerSkillName(), starterClass);
                    enemyMove(ps1.getNewPlayerLife(), pDef, eAtkDmg, es1.geteSkillDmg(), es1.geteSkillName(), eClass);
                    newEnemyStats(es1.getNewEnemyLife(), eAtkDmg, eDef, es1.geteSkillDmg(), es1.geteSkillName(), eClass);
                    newPlayerStats(ps1.getNewPlayerLife(), pAtkDmg, pDef, pMana, ps1.getNewPlayerMana(), ps1.getPlayerSkillDmg(), ps1.getManaCost(), ps1.getPlayerSkillName(), starterClass);
                } else {
                    turn = 0;
                    waitInterval();
                    new ProcessBuilder("powershell", "Clear-Host").inheritIO().start().waitFor();
                    newEnemyStats(es1.getNewEnemyLife(), eAtkDmg, eDef, es1.geteSkillDmg(), es1.geteSkillName(), eClass);
                    newPlayerStats(ps1.getNewPlayerLife(), pAtkDmg, pDef, pMana, ps1.getNewPlayerMana(), ps1.getPlayerSkillDmg(), ps1.getManaCost(), ps1.getPlayerSkillName(), starterClass);
                }
                turn++;
            }
        }
    }

    public void enemyStarterStats(int eLife, int eAtkDmg, int eDef, int[] eSkillDmg, String[] eSkillName,
            String eClass) {
        enemySkillSet(eSkillDmg, eSkillName, eClass);
        es1.setNewEnemyLife(eLife);

        waitInterval();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("            Enemy " + eClass + " has been appeared!!");

        waitInterval();
        System.out.println("===================Enemy Stats and Skills=================");
        System.out.printf("Enemy Name: %s\nEnemy's Life: %6d | Skill name: %s | Skill Dmg: %d\nEnemy's Atk: %7d | Skill name: %s | Skill Dmg: %d\nEnemy's Defense: %3d | Skill name: %s | Skill Dmg: %d\n",
                eClass, eLife, eSkillName[0], eSkillDmg[0], eAtkDmg, eSkillName[1], eSkillDmg[1], eDef, eSkillName[2],
                eSkillDmg[2]);
    }

    public void playerStarterStats(int pLife, int pAtkDmg, int pDef, int pMana, int[] pSkillDmg, int[] manaCost,
            String[] pSkillName, String starterClass) {
        playerSkillSet(pSkillDmg, manaCost, pSkillName, starterClass);
        ps1.setNewPlayerLife(pLife);
        ps1.setNewPlayerMana(pMana);
        ps1.setRecharge(100);

        waitInterval();
        System.out.println("\n==================Player Stats and Skills=================");
        System.out.printf("Player Class: %s\nLife: %9d | [0]Skill Name: %s | Skill Dmg: %d | Mana cost: %d\n",
                starterClass, pLife, pSkillName[0], pSkillDmg[0], manaCost[0]);
        System.out.printf("Attack Dmg: %3d | [1]Skill Name: %s | Skill Dmg: %d | Mana cost: %d\nDefense: %6d | [2]Skill Name: %s | Skill Dmg: %d | Mana cost: %d\n",
                pAtkDmg, pSkillName[1], pSkillDmg[1], manaCost[1], pDef, pSkillName[2], pSkillDmg[2], manaCost[2]);
        System.out.printf("Mana: %9d | [3]Skill Name: %s\n", pMana, pSkillName[3]);
    }

    public void newEnemyStats(int newELife, int eAtkDmg, int eDef, int[] eSkillDmg, String[] eSkillName,
            String eClass) {
        if (newELife <= 0) {
            es1.setNewEnemyLife(0);
        }

        waitInterval();
        System.out.println("===================Enemy Stats and Skills=================");
        System.out.printf("Enemy Name: %s\nEnemy's Life: %6d | Skill name: %s | Skill Dmg: %d\nEnemy's Atk: %7d | Skill name: %s | Skill Dmg: %d\nEnemy's Defense: %3d | Skill name: %s | Skill Dmg: %d\n",
                eClass, es1.getNewEnemyLife(), eSkillName[0], eSkillDmg[0], eAtkDmg, eSkillName[1], eSkillDmg[1], eDef, eSkillName[2], eSkillDmg[2]);
    }

    public void newPlayerStats(int newPLife, int pAtkDmg, int pDef, int oldPMana, int newPMana, int[] pSkillDmg,
            int[] manaCost, String[] pSkillName, String starterClass) {
        if (newPMana >= oldPMana) {
            ps1.setNewPlayerMana(oldPMana);
        } else if (newPLife <= 0) {
            ps1.setNewPlayerLife(0);
        }

        waitInterval();
        System.out.println("\n================Player Stats and Skills=================");
        System.out.printf("Player Class: %s\nLife: %9d | [0]Skill Name: %s | Skill Dmg: %d | Mana cost: %d\n",
                starterClass, ps1.getNewPlayerLife(), pSkillName[0], pSkillDmg[0], manaCost[0]);
        System.out.printf("Attack Dmg: %3d | [1]Skill Name: %s | Skill Dmg: %d | Mana cost: %d\nDefense: %6d | [2]Skill Name: %s | Skill Dmg: %d | Mana cost: %d\n",
                pAtkDmg, pSkillName[1], pSkillDmg[1], manaCost[1], pDef, pSkillName[2], pSkillDmg[2], manaCost[2]);
        System.out.printf("Mana: %9d | [3]Skill Name: %s\n", ps1.getNewPlayerMana(), pSkillName[3]);

    }

    public void playerMove(int newELife, int eDef, int pAtkDmg, int newPMana, int rechargeMana, int[] pSkillDmg,
            int[] manaCost, String[] pSkillName, String starterClass) {
        int chooseSkill = 0, dmgOutput;

        int addDmg = pAtkDmg / 2;
        ch = new Scanner(System.in);

        try {
            System.out.print("\nChoose a skill move to fight against the enemy: ");
            chooseSkill = ch.nextInt();
        } catch (InputMismatchException ie) {
            ie.printStackTrace();
        }

        switch (chooseSkill) {
            case 0:
                dmgOutput = (pSkillDmg[0] + addDmg) - eDef;

                es1.setNewEnemyLife(newELife - dmgOutput);
                ps1.setNewPlayerMana(newPMana - manaCost[0]);
                System.out.printf("\nPlayer %s chose %s. \nEnemy take %d damage.\nPlayer lose %d mana.\n\n",
                        starterClass, pSkillName[0], dmgOutput, manaCost[0]);
                break;
            case 1:
                dmgOutput = (pSkillDmg[1] + addDmg) - eDef;

                es1.setNewEnemyLife(newELife - dmgOutput);
                ps1.setNewPlayerMana(newPMana - manaCost[1]);
                System.out.printf("\nPlayer %s chose %s. \nEnemy take %d damage.\nPlayer lose %d mana.\n\n",
                        starterClass, pSkillName[1], dmgOutput, manaCost[1]);
                break;
            case 2:
                dmgOutput = (pSkillDmg[2] + addDmg) - eDef;

                es1.setNewEnemyLife(newELife - dmgOutput);
                ps1.setNewPlayerMana(newPMana - manaCost[2]);
                System.out.printf("\nPlayer %s chose %s. \nEnemy take %d damage.\nPlayer lose %d mana.\n\n",
                        starterClass, pSkillName[2], dmgOutput, manaCost[2]);
                break;
            case 3:
                ps1.setNewPlayerMana(newPMana + rechargeMana);
                System.out.printf("\nPlayer %s chose %s.\nPlayer recharge %d mana successfully.\n\n", starterClass,
                        pSkillName[3], rechargeMana);
                break;
            default:
                System.out.println("Invalid input.\n");
        }
    }

    public void enemyMove(int newPLife, int pDef, int eAtkDmg, int[] eSkillDmg, String[] eSkillName, String eClass) {
        int enemychoose, dmgOutput, addAtk;

        addAtk = eAtkDmg / 2;

        if(maxTurn > 0){
            enemychoose = rand.nextInt(2);
            switch (enemychoose) {
                case 0:
                    dmgOutput = ((eSkillDmg[0] + addAtk) - pDef);
                    ps1.setNewPlayerLife(newPLife - dmgOutput);
                    System.out.printf("Enemy %s chose %s. \nPlayer take %d damage.\n\n", eClass, eSkillName[0], dmgOutput);
                    break;
                case 1:
                    dmgOutput = ((eSkillDmg[1] + addAtk) - pDef);
                    ps1.setNewPlayerLife(newPLife - dmgOutput);
                    System.out.printf("Enemy %s chose %s. \nPlayer take %d damage.\n\n", eClass, eSkillName[1], dmgOutput);
                    break;
                case 2:
                    dmgOutput = ((eSkillDmg[2] + addAtk) - pDef);
                    ps1.setNewPlayerLife(newPLife - dmgOutput);
                    System.out.printf("Enemy %s chose %s. \nPlayer take %d damage.\n\n", eClass, eSkillName[2], dmgOutput);
                    break;
                default:
                    System.out.println("\nEnemy did not attack.");
            }
            maxTurn -= 1;
        }else{
            if(rechargeTurn <= 0){
                System.out.println("Enemy will attack again after next turn.");
                rechargeTurn = rand2.nextInt(2);
                maxTurn = rand3.nextInt(1,5);
            }else{
                System.out.printf("Enemy will attack again after %d turn.\n", rechargeTurn);
                rechargeTurn -= 1;
            }
        }
    }

    public void playerSkillSet(int[] pSkillDmg, int[] manaCost, String[] pSkillName, String starterClass) {
        switch (starterClass) {
            case "Knight":
                pSkillDmg[0] = 200;
                pSkillDmg[1] = 220;
                pSkillDmg[2] = 50;

                manaCost[0] = 30;
                manaCost[1] = 45;
                manaCost[2] = 20;

                pSkillName[0] = "Sword Slash";
                pSkillName[1] = "Lightning Sword Split";
                pSkillName[2] = "Guard";
                pSkillName[3] = "Recharge Mana";

                ps1.setPlayerSkillSet(pSkillDmg, manaCost, pSkillName);
                break;
            case "Mage":
                pSkillDmg[0] = 320;
                pSkillDmg[1] = 100;
                pSkillDmg[2] = 120;

                manaCost[0] = 100;
                manaCost[1] = 50;
                manaCost[2] = 30;

                pSkillName[0] = "Meteor Strike";
                pSkillName[1] = "Earth Barrage";
                pSkillName[2] = "Lightning Strike";
                pSkillName[3] = "Recharge Mana";

                ps1.setPlayerSkillSet(pSkillDmg, manaCost, pSkillName);
                break;
            case "Tank":
                pSkillDmg[0] = 100;
                pSkillDmg[1] = 250;
                pSkillDmg[2] = 75;

                manaCost[0] = 30;
                manaCost[1] = 120;
                manaCost[2] = 55;

                pSkillName[0] = "Shield Bash";
                pSkillName[1] = "Obsidian Shield Strike";
                pSkillName[2] = "Sword Strike";
                pSkillName[3] = "Recharge Mana";

                ps1.setPlayerSkillSet(pSkillDmg, manaCost, pSkillName);
                break;
            default:
                System.out.println("Default!");
        }
    }

    public void enemySkillSet(int[] eSkillDmg, String[] eSkillName, String eClass) {
        switch (eClass) {
            case "Skeleton":
                eSkillDmg[0] = 110;
                eSkillDmg[1] = 60;
                eSkillDmg[2] = 130;

                eSkillName[0] = "Angry Bone Strike";
                eSkillName[1] = "Calcium Stabilize";
                eSkillName[2] = "Flying Headbutt";
                es1.setEnemySkillSet(eSkillDmg, eSkillName);
                break;
            case "Guardian":
                eSkillDmg[0] = 100;
                eSkillDmg[1] = 70;
                eSkillDmg[2] = 50;

                eSkillName[0] = "Strong Punch";
                eSkillName[1] = "Headbutt";
                eSkillName[2] = "Iron Shield Bash";
                es1.setEnemySkillSet(eSkillDmg, eSkillName);
                break;
            case "God":
                eSkillDmg[0] = 120;
                eSkillDmg[1] = 70;
                eSkillDmg[2] = 200;

                eSkillName[0] = "Light Beam";
                eSkillName[1] = "Thunderbolt";
                eSkillName[2] = "God's Judgement";
                es1.setEnemySkillSet(eSkillDmg, eSkillName);
                break;
            case "Snake":
                eSkillDmg[0] = 120;
                eSkillDmg[1] = 100;
                eSkillDmg[2] = 75;

                eSkillName[0] = "Venomous fang slash";
                eSkillName[1] = "Armored tail strike";
                eSkillName[2] = "Venom spit";
                es1.setEnemySkillSet(eSkillDmg, eSkillName);
                break;
            default:
                System.out.println("Default!");
        }
    }

    public boolean isDead(int newPLife, int newELife) {
        int dead = 0;

        if (newPLife == dead && newELife != dead) {
            winner = "Enemy!";
            isDead = true;
        } else if (newPLife != dead && newELife == dead) {
            winner = "Player!";
            isDead = true;
        } else {
            isDead = false;
        }
        return isDead;
    }

    public void waitInterval() {
        try {
            Thread.sleep(750);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}