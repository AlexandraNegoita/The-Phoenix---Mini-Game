/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThePhoenixMiniGame;

public class Loot {
    private String name;
    private String description;
    private int prix;
    private boolean consommableEquipable;
    private int health;
    private int armor;
    private int ad;
    private int ap;
    private String img_path;
    private int numar;
    /*
        true - consommable
        false - equipable
    */
    public Loot(String img_path) {
        this.consommableEquipable=true;
        this.img_path=img_path;
    }

    public Loot(String name, String description, int prix, boolean consommableEquipable, int numar) {
        this.name = name;
        this.description = description;
        this.prix = prix;
        this.consommableEquipable=consommableEquipable;
        this.numar=numar;
    }

    public Loot(String name, String description, int prix, boolean consommableEquipable, int numar, int health, int armor, int ad, int ap ) {
        this.name = name;
        this.description = description;
        this.prix = prix;
        this.consommableEquipable = consommableEquipable;
        this.health = health;
        this.armor = armor;
        this.ad = ad;
        this.ap = ap;
        this.numar = numar;
    }
    

    public boolean isConsommableEquipable() {
        return consommableEquipable;
    }

    public void setConsommableEquipable(boolean consommableEquipable) {
        this.consommableEquipable = consommableEquipable;
    }
    
    public void setAttributesHpArmAdAp(int health, int armor, int ad, int ap){
        this.health=health;
        this.armor=armor;
        this.ad=ad;
        this.ap=ap;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
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
    
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
    public String getImage(){
        return img_path="\\ThePhoenixMiniGame\\materials\\sprites\\loot-"+this.numar+".png";
    }

    
}
