package utilitaire;
import java.util.ArrayList;

public class Minimax {
    
    static ArrayList<Graphe> liste;

    public Minimax(ArrayList<Graphe> list){   
        liste = list;
       
    }

    //Objectif hi-retourner valeur optimale an'ilay arbre
    public  static int miniMax(Graphe g,int profondeur,boolean estMax){
        //etat=10 ny valeur terminal an'ilay etat , le izy mandresy
        System.out.println("sommet: "+ g.sommet +" profondeur= "+profondeur);
        if (profondeur==0 || g.value==10 ) {
            return g.value;    
        }
        else if (estMax) {
            int v = Integer.MIN_VALUE;
            ArrayList<Graphe> enfants = getChildren(g);
            for (int i = 0; i < enfants.size(); i++) {
                int valeur_graphe = miniMax(enfants.get(i), profondeur-1, false);
                enfants.get(i).setValue(valeur_graphe);
                v = Math.max(v,valeur_graphe );
            }
            
            return v;
        }
        else{
            int v = Integer.MAX_VALUE;
            ArrayList<Graphe> enfants = getChildren(g) ;
            for (int i = 0; i < enfants.size(); i++) {
                int valeur_graphe = miniMax(enfants.get(i), profondeur-1, true);
                enfants.get(i).setValue(valeur_graphe);
                v = Math.min(v,valeur_graphe );
            }
            
            return v;
        }
    }
    public static ArrayList<Graphe> getChildren(Graphe g){
        ArrayList<Graphe> children = new ArrayList<>();
        for (int i = 0; i < liste.size(); i++) {
            for (int j = 0; j < g.sommetChildren.length; j++) {
                if (liste.get(i).getSommet().compareToIgnoreCase(g.sommetChildren[j])==0 ) {
                    
                    children.add(liste.get(i));
                }
            }
        }
        return children;
    }
    

}
