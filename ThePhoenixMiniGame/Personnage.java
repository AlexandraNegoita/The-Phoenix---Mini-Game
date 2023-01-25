
package ThePhoenixMiniGame;

import java.util.HashMap;

public class Personnage {
    private String name;
    private int level;
    private int experience;
    private int health=1000;
    private int initialHealth;
    private int healthPercent=100;
    private int ad; //attack damage
    private int ap; //ability power
    private int armor;
    private int index=0;
    HashMap<Integer, Loot> inventory=new HashMap<>();
    HashMap<Integer, Loot> equipped=new HashMap<>();
    private int index_eq=0;
    private final String img_map_path="\\ThePhoenixMiniGame\\materials\\sprites\\personnage-icon.png";
    private final String splash_path="\\ThePhoenixMiniGame\\materials\\sprites\\personnage.png";
    private final String splash_path_attack="\\ThePhoenixMiniGame\\materials\\sprites\\personnage-attack.png";
    private final String splash_path_ability="\\ThePhoenixMiniGame\\materials\\sprites\\personnage-ability.png";
    private final String splash_path_shield="\\ThePhoenixMiniGame\\materials\\sprites\\personnage-shield.png";

    public Personnage() {
    }
    
    public Personnage(String name) {
        this.name = name;
        this.level=0;
        this.experience=0;
    }

    public void setInitialHealth(int initialHealth) {
        this.initialHealth = initialHealth;
    }
    public void addToInventory(Loot loot){
        inventory.put(index, loot);
        index++;
    }
    public void setAttributesHpArmAdApArmor(int initialHealth, int armor, int ad, int ap){
        this.initialHealth=initialHealth;
        this.armor=armor;
        this.ad=ad;
        this.ap=ap;
        this.setHealthPercent(100*this.getHealth()/this.getInitialHealth());
    }

    public int getInitialHealth() {
        return initialHealth;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    }

    public int getHealth() {
        if(this.health<0) return 0;
        else return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealthPercent() {
        if(this.health<0) {
            return 0;
        }
        else {
            
            return this.healthPercent;
        }
    }

    public void setHealthPercent(int healthPercent) {
        this.healthPercent = healthPercent;
    }

    public int getAd() {
        return ad;
    }

    public void setAd(int ad) {
        this.ad = ad;
    }

    public int getAp() {
        return ap;
    }

    public void setAp(int ap) {
        this.ap = ap;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public String getSplash_path() {
        return splash_path;
    }

    public String getSplash_path_attack() {
        return splash_path_attack;
    }

    public String getSplash_path_ability() {
        return splash_path_ability;
    }

    public String getSplash_path_shield() {
        return splash_path_shield;
    }
    
    public String getSplash(){
        return splash_path;
    }

    public String getImg_map_path() {
        return img_map_path;
    }
    
    
    public void useItem(){
        inventory.remove(index-1);
        index--;
        this.setHealth(this.getHealth()+100);
        this.setHealthPercent(100*this.getHealth()/this.getInitialHealth());
    }
    
    public void equip(Loot loot){
            this.equipped.put(index_eq, loot);
            this.setAttributesHpArmAdApArmor(this.getInitialHealth()+loot.getHealth(), this.getArmor()+loot.getArmor(), this.getAd()+loot.getAd(), this.getAp()+loot.getAp());
            index_eq++;
    }
    }


