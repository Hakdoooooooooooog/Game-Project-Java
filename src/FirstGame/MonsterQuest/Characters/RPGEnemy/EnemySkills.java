package FirstGame.MonsterQuest.Characters.RPGEnemy;

// Encapsulated Class Enemy Skills
public class EnemySkills {

    private int[] eSkillDmg;
    private String[] eSkillName;
    private int newEnemyLife;

    public EnemySkills(int[] eSkillDmg, String[] eSkillName, int newEnemyLife) {
        this.eSkillDmg = eSkillDmg;
        this.eSkillName = eSkillName;
        this.newEnemyLife = newEnemyLife;
    }

    public int[] geteSkillDmg() {
        return eSkillDmg;
    }

    public String[] geteSkillName() {
        return eSkillName;
    }

    public int getArraySizeSkill() {
        return eSkillDmg.length;
    }

    public int getNewEnemyLife() {
        return newEnemyLife;
    }

    public void setNewEnemyLife(int newEnemyLife) {
        this.newEnemyLife = newEnemyLife;
    }

    public void setEnemySkillSet(int[] eSkillDmg, String[] eSkillName) {
        this.eSkillDmg = eSkillDmg;
        this.eSkillName = eSkillName;
    }
}
