package FirstGame.MonsterQuest.Characters.RPGEnemy;

import java.util.ArrayList;

//Encapsulated Class Enemy
public class Enemy {
    // ===========Enemy Classes=============
    private ArrayList<String> enemySelector;

    // ===========Enemy Stats=============
    private int eLife, eAtkDmg, eDefense;
    private double eClassID;
    private String eClass;

    public Enemy(int eLife, int eAtkDmg, int eDefense, double eClassID) {
        this.eLife = eLife;
        this.eAtkDmg = eAtkDmg;
        this.eDefense = eDefense;
        this.eClassID = eClassID;

        enemySelector = new ArrayList<>();
        enemySelector.add("Skeleton");
        enemySelector.add("Guardian");
        enemySelector.add("God");
        enemySelector.add("Snake");

    }

    public String getEnemyClass() {
        eClass = enemySelector.get((int) eClassID);
        return eClass;
    }

    public int getELife() {
        return eLife;
    }

    public int getEAtkDmg() {
        return eAtkDmg;
    }

    public int getEDef() {
        return eDefense;
    }

    public double getEClassID() {
        eClassID = Math.random() * 4;
        return eClassID;
    }

    public void seteClassID(double eClassID) {
        this.eClassID = eClassID;
    }

    public void setEnemyStats(int eLife, int eAtkDmg, int eDefense, String eClass) {
        this.eLife = eLife;
        this.eAtkDmg = eAtkDmg;
        this.eDefense = eDefense;
        this.eClass = eClass;
    }
}