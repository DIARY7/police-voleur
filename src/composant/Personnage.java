package composant;

import java.awt.Graphics;


public abstract class Personnage implements Cloneable {
    int typePerso; //0 raha police , 1 raha voleur
    Intersection intersec;
    
    //fonction non abstraite
    public int getTypePerso() {
        return typePerso;
    }
    public void setTypePerso(int typePerso) {
        this.typePerso = typePerso;
    }
    public Intersection getIntersec() {
        return intersec;
    }
    public abstract void setIntersec(Intersection intersec);


    //fonction abstraite
    public abstract void draw(Graphics g);
    @Override
    protected Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        Personnage cp_perso = (Personnage) super.clone();
        Intersection cp_intersection = (Intersection) intersec.clone();
        cp_perso.intersec = cp_intersection;
        return super.clone();
    }
}
