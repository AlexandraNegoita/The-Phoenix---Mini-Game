
package ThePhoenixMiniGame;

import java.util.ArrayList;
import java.util.HashMap;

public class Carte {
    public int indexAleatoire(int min, int max){
        return (int)(Math.random()*(max-min)+min);
    }
    class Coordonees{
        int x;
        int y;
        private final int type_index=indexAleatoire(0,100);
        Coordonees(int x, int y){
            this.x=x;
            this.y=y;
        }
        private int getType_index(){
            return this.type_index;
        }
        @Override
        public boolean equals(Object a){
            return this.x==((Coordonees)a).x && this.y==((Coordonees)a).y;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash += 79 * hash + this.x;
            hash +=79 * hash + this.y;
            return hash;
        }
    }
    ArrayList<Terrain> terrain=new ArrayList();
    ArrayList<PNJ> pnj=new ArrayList();
    ArrayList<Adversaire> adversaire=new ArrayList();
    public HashMap generateMap(ArrayList<Terrain> terrain, ArrayList<PNJ> pnj, ArrayList<Adversaire> adversaire, Personnage personnaje,Loot loot,
int width, int length){
        HashMap<Coordonees,Object> map = new HashMap<>();
        for(int x=0;x<width;x++) {
            for(int y=0;y<length;y++) {
                
                Coordonees coord=new Coordonees(x,y);
                switch(coord.getType_index()){
                    default:
                        if(Math.random()>0.97){ //3% probabilitate de drop
                            map.put(coord, loot);
                        }
                        else{
                            map.put(coord, null);
                        }
                        break;
                    case 10:case 15:case 20:case 25:case 30:case 35:case 40:case 45:case 50:case 55:case 60:case 65:case 70:case 75:case 80:case 85:case 90:
                        int numar=indexAleatoire(0,terrain.size());
                        map.put(coord, terrain.get(numar));
                        break;
                    case 3:
                        int nPNJ=indexAleatoire(0,pnj.size());
                        if(pnj.size()==0){
                            int numarPNJ=indexAleatoire(0,terrain.size());
                            map.put(coord, terrain.get(numarPNJ));
                            break;
                        }
                        map.put(coord, pnj.get(nPNJ));
                        pnj.remove(nPNJ);
                        
                        break;
                    case 2:case 4:case 6:case 8:case 12:
                        int nADV=indexAleatoire(0,adversaire.size());
                        if(adversaire.size()==0){
                            int numarADV=indexAleatoire(0,terrain.size());
                            map.put(coord, terrain.get(numarADV));
                            break;
                        }
                        map.put(coord, adversaire.get(nADV));
                        adversaire.remove(nADV);
                }
            }
        }
        return map;
    }
}
