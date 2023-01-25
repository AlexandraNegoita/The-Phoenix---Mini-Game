
package ThePhoenixMiniGame;


public class PNJ {
    String description;
    private String name;
    private String interaction1;
    private String interaction2;
    private String interaction3;
    private final int number;
    private String splash_path;;
    private String img_map_path;

    public PNJ(String name,int number) {
        this.number = number;
        this.setImg_map_path();
        this.setSplash_path();
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public void setDescription(String s){
        this.description=s;
    }

    public void setInteraction1(String interaction1) {
        this.interaction1 = interaction1;
    }

    public void setInteraction2(String interaction2) {
        this.interaction2 = interaction2;
    }

    public void setInteraction3(String interaction3) {
        this.interaction3 = interaction3;
    }
    
    public String interaction1(){
        return interaction1;
    }
    
    public String interaction2(){
        return interaction2;
    }
     
    public String interaction3(){
        return interaction3;
    }
      
    public String getSplash_path() {
        return splash_path;
    }

    public void setSplash_path() {
        this.splash_path = "\\ThePhoenixMiniGame\\materials\\sprites\\pnj-"+this.number+".png";
    }

    public String getImg_map_path() {
        return img_map_path;
    }

    public void setImg_map_path() {
        this.img_map_path = "\\ThePhoenixMiniGame\\materials\\sprites\\pnj-"+this.number+"-icon.png";
    }
}
