package composant;

import java.awt.Color;
import java.awt.Graphics;

public class Police extends Personnage implements Cloneable {

    public Police(Intersection intersec){
        this.typePerso=0;
        setIntersec(intersec);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        this.intersec.drawIntersec(g);    
    }

    @Override
    public void setIntersec(Intersection intersec) {
        // TODO Auto-generated method stub
            intersec.estOccupePolice = true;
            intersec.estOccupeVoleurs=false;
            this.intersec = intersec;    
            
    }

    //fonction dessin
    public void drawProcheVoleur(Graphics g){
        g.setColor(Color.RED);
        g.drawOval((int) this.getIntersec().getPosition().getX() , (int) this.getIntersec().getPosition().getY(), 30, 30);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        Intersection cp_intersec = (Intersection) this.intersec.clone();
        Police cp_police = (Police) super.clone();
        cp_police.setIntersec(cp_intersec);
        return cp_police;
    }
    
}
