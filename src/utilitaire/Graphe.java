package utilitaire;


public class Graphe {
    String sommet;
    String[] sommetChildren;
    int value;
    
    //constructor
    public Graphe(String sommet, String[] sommetChildren){
        this.sommet=sommet;
        this.sommetChildren=sommetChildren;
    }
    public Graphe(String sommet, String[] sommetChildren, int value) {
        this.sommet = sommet;
        this.value = value;
        this.sommetChildren=sommetChildren;
    }
    public Graphe(){
        
    }

    //setters and getters
    
    public String getSommet() {
        return sommet;
    }
    public void setSommet(String sommet) {
        this.sommet = sommet;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public String[] getSommetChildren() {
        return sommetChildren;
    }
    public void setSommetChildren(String[] sommetChildren) {
        this.sommetChildren = sommetChildren;
    }
    
}
