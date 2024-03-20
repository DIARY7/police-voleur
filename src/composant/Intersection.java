package composant;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Intersection implements Cloneable {
    //attribut
    Point position;
    ArrayList<Point> voisins; // Asaina mandalo fonction d lasa intersection
    
    boolean estOccupePolice=false;
    boolean estOccupeVoleurs=false;
    
    
    //constructeur
    public Intersection(Point position, ArrayList<Point> voisins) {
        this.position = position;
        this.voisins = voisins;
    }
    public Intersection(){

    }
    public Intersection(Point position) {
        this.position = position;
    }

    //fonction dessin
    public void drawActif(Graphics g){
        g.setColor(Color.GREEN);
        g.drawOval((int) position.getX() , (int)position.getY(), 20, 20);
    }

    public void drawIntersec(Graphics g){
        g.fillOval((int) position.getX() , (int)position.getY(), 15, 15);
    }
    //setters and getters
    public Point getPosition() {
        return position;
    }
    public void setPosition(Point position) {
        this.position = position;
    }
    public ArrayList<Point> getVoisins() {
        return voisins;
    }
    public void setVoisins(ArrayList<Point> voisins) {
        this.voisins = voisins;
    }
    public boolean isEstOccupePolice() {
        return estOccupePolice;
    }
    public void setEstOccupePolice(boolean estOccupePolice) {
        this.estOccupePolice = estOccupePolice;
    }
    public boolean isEstOccupeVoleurs() {
        return estOccupeVoleurs;
    }
    public void setEstOccupeVoleurs(boolean estOccupeVoleurs) {
        this.estOccupeVoleurs = estOccupeVoleurs;
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        
        //Point cp_position = (Point) this.position.clone() ;
        ArrayList<Point> cp_voisins = new ArrayList<>();
        Point cp_position = new Point((int)position.getX(), (int)position.getY());

        for(int i=0;i< this.voisins.size();i++){
            //cp_voisins.add((Point) voisins.get(i).clone());
            cp_voisins.add(new Point( (int) ((Point) voisins.get(i)).getX() , (int)((Point) voisins.get(i)).getY()) );
            
        }
        Intersection cp_intersection = (Intersection) super.clone();
        cp_intersection.setPosition(cp_position);
        cp_intersection.setVoisins(cp_voisins);
        
        return cp_intersection;
    }
}
