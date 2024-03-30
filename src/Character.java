public abstract class Character implements Fighter{
    private String name;
    private int health;
    private int strength;
    private int dexterity;
    private int level;
    private int experience;
    private int gold;

    private final int xpBase = 300;
    private final double xpMult = 0.1;

    public Character(String name, int health, int strength, int dexterity, int level, int experience, int gold) {
        this.name = name;
        this.health = health; 
        this.strength = strength;
        this.dexterity = dexterity;
        this.level = level;
        this.experience = experience;
        this.gold = gold;
    }

    @Override
    public int attack() {
        int randomValue = getRandomValue();
        if (dexterity > randomValue) {
            System.out.println("Критический удар!");
            return strength * 2;
        } else if (dexterity * 3 > randomValue) {
            return strength;
        } else {
            return  0;
        }
    }

    private int getNextLvl(){
        if (level == 1){
            return xpBase;
        } else {
            return (int)(xpBase * (level - 1) + xpBase * (level - 1) * xpMult);
        }
    }

    private void shouldLevelUp(){
        while (experience >= getNextLvl()){
            experience -= getNextLvl();
            level++;
            levelUp();
        }
    }

    protected void levelUp(){
        health += 10;
        strength += 3;
        dexterity += 2;
        level++;

        System.out.println(name + " получил новый уровень " + level + "!");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
        shouldLevelUp();
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
    private int getRandomValue() {
        return (int) (Math.random() * 100);
    }
    @Override
    public String toString() {
        return String.format("%s Health:%d", name, health);
    }
}
