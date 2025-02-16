package FirstGame.MonsterQuest.Characters.RPGPlayer;

//Encapsulated Class Player Skills
public class PlayerSkills {

    private int[] pSkillDmg;
    private int[] manaCost;
    private String[] pSkillName;

    private int newPlayerLife, newPlayerMana, recharge;

    public PlayerSkills(int[] pSkillDmg, int[] manaCost, String[] pSkillName, int newPlayerLife, int newPlayerMana,
            int recharge) {
        this.pSkillDmg = pSkillDmg;
        this.manaCost = manaCost;
        this.pSkillName = pSkillName;
        this.newPlayerLife = newPlayerLife;
        this.newPlayerMana = newPlayerMana;
    }

    public int[] getPlayerSkillDmg() {
        return pSkillDmg;
    }

    public int[] getManaCost() {
        return manaCost;
    }

    public String[] getPlayerSkillName() {
        return pSkillName;
    }

    public int getNewPlayerLife() {
        return newPlayerLife;
    }

    public int getNewPlayerMana() {
        return newPlayerMana;
    }

    public int getRecharge() {
        return recharge;
    }

    public void setRecharge(int recharge) {
        this.recharge = recharge;
    }

    public void setNewPlayerLife(int newPlayerLife) {
        this.newPlayerLife = newPlayerLife;
    }

    public void setNewPlayerMana(int newPlayerMana) {
        this.newPlayerMana = newPlayerMana;
    }

    public void setPlayerSkillSet(int[] pSkillDmg, int[] manaCost, String[] pSkillName) {
        this.pSkillDmg = pSkillDmg;
        this.manaCost = manaCost;
        this.pSkillName = pSkillName;
    }
}