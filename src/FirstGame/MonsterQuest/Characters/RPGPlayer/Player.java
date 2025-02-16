package FirstGame.MonsterQuest.Characters.RPGPlayer;

import java.util.ArrayList;

//Encapsulated Class Player
public class Player {

    private ArrayList<String> classes;

    private int pLife, pAtkDmg, pDef, pMana;
    private int pClassID;

    public Player(int pLife, int pAtkDmg, int pDef, int pMana, int pClassID) {
        this.pLife = pLife;
        this.pAtkDmg = pAtkDmg;
        this.pDef = pDef;
        this.pMana = pMana;
        this.pClassID = pClassID;

        classes = new ArrayList<>();
        classes.add("");
        classes.add("Knight");
        classes.add("Mage");
        classes.add("Tank");
    }

    public String getStarterClass() {
        return classes.get(pClassID);
    }

    public int getPClassesSize() {
        return classes.size();
    }

    public int getPLife() {
        return pLife;
    }

    public int getPAtkDmg() {
        return pAtkDmg;
    }

    public int getPDef() {
        return pDef;
    }

    public int getPMana() {
        return pMana;
    }

    public int getPClassID() {
        return pClassID;
    }

    public void setpClassID(int pClassID) {
        this.pClassID = pClassID;
    }

    public void setPlayerStats(int pLife, int pAtkDmg, int pDef, int pMana) {
        this.pLife = pLife;
        this.pAtkDmg = pAtkDmg;
        this.pDef = pDef;
        this.pMana = pMana;
    }

    public String toString() {
        System.out.println("\n========================Player Stats=======================");
        return String.format("Player Class: %s\nLife: %d\nAttack Damage: %d\nDefense: %d\nMana: %d", getStarterClass(),
                getPLife(), getPAtkDmg(), getPDef(), getPMana());
    }

}