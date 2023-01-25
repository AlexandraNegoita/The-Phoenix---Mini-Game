
package ThePhoenixMiniGame;
public class Terrain {
    private int numar;
    private String description;

    public Terrain(int numar) {
        this.numar=numar;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public void setDescription(String s){
        this.description=s;
    }

    public String getImage(){
         return "\\ThePhoenixMiniGame\\materials\\sprites\\terrain-"+this.numar+".jpg";
    }
}
