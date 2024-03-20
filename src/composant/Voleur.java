package composant;

import java.awt.Color;
import java.awt.Graphics;

public class Voleur extends Personnage implements Cloneable {

    static Voleur voleur=null;
    
    private Voleur(Intersection intersec){
        setIntersec(intersec);
    }

    public static Voleur getInstance(Intersection intersec){
        if (voleur==null) {
            voleur = new Voleur(intersec);
        }
        return voleur;
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        this.intersec.drawIntersec(g);
    }

    @Override
    public void setIntersec(Intersection intersec) {
        // TODO Auto-generated method stub
        //if (!intersec.isEstOccupePolice() && !intersec.isEstOccupeVoleurs()) {
            intersec.estOccupePolice = false;
            intersec.estOccupeVoleurs=true;
            this.intersec = intersec;    
        //}
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        Intersection cp_intersec = (Intersection) this.intersec.clone();
        Voleur cp_voleur = (Voleur) super.clone();
        cp_voleur.setIntersec(cp_intersec);
        return cp_voleur ;
    }
    
}
