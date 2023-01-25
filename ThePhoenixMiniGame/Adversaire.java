
package ThePhoenixMiniGame;

public class Adversaire {
    private String name;
    private int experience;
    private int health;
    private final int initialHealth;
    private int healthPercent=100;
    private int ap; //ability power
    private final int number;
    private Loot drop;
    private String splash_path;
    private String splash_path_attack;
    private String img_map_path;

    public Adversaire(int number, int hp) {
        this.number = number;
        this.initialHealth=hp;
        this.health=hp;
    }

    public Adversaire(String name,int experience, int initialHealth, int ap, int number, Loot drop) {
        this.name=name;
        this.experience = experience;
        this.initialHealth = initialHealth;
        this.health=initialHealth;
        this.ap = ap;
        this.number = number;
        this.drop = drop;
        this.setSplash_path();
        this.setSplash_path_attack();
        this.setImg_map_path();
    }

    public Loot getDrop() {
        return drop;
    }

    public void setDrop(Loot drop) {
        this.drop = drop;
    }

    public int getInitialHealth() {
        return initialHealth;
    }
    

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealthPercent() {
        return healthPercent;
    }

    public void setHealthPercent(int healthPercent) {
        this.healthPercent = healthPercent;
    }
    
    
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }

    public int getExperience() {
        return experience;
    }

    public int getHealth() {
        return health;
    }

    public int getAp() {
        return ap;
    }

    public String getSplash_path() {
        return splash_path;
    }

    public void setSplash_path() {
        this.splash_path = "\\ThePhoenixMiniGame\\materials\\sprites\\adv-"+this.number+".png";
    }

    public String getSplash_path_attack() {
        return splash_path_attack;
    }

    public void setSplash_path_attack() {
        this.splash_path_attack = "\\ThePhoenixMiniGame\\materials\\sprites\\adv-"+this.number+"-attack.png";
    }

    public String getImg_map_path() {
        return img_map_path;
    }

    public void setImg_map_path() {
        this.img_map_path = "\\ThePhoenixMiniGame\\materials\\sprites\\adv-"+this.number+"-icon.png";;
    }
     
    public void giveExperience(Personnage p){
        p.setExperience(p.getExperience()+this.experience);
    }

}
