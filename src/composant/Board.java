package composant;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Board implements Cloneable {
    Intersection[] intersections;
    Police[] polices;
    Voleur voleur;
    int centerY,centerX;
    int USER_WIN = Integer.MIN_VALUE;
    int COPS_WIN = Integer.MAX_VALUE;
    public int getUSER_WIN() {
        return USER_WIN;
    }
    
    public int getCOPS_WIN() {
        return COPS_WIN;
    }
    
    Point arrive;
    boolean tour_user = true;
    boolean en_cours = true;
    IA intelligence;
    

    boolean a_commence=false;

    public Board(){
        getInitBoard();
        intelligence = new IA(this);
    }
    private void getInitBoard(){

        int width=1000;
        int height=700;
        
        centerX = width/2;
        centerY=height/2;
        
        Point haut_milieu_haut = new Point(centerX , centerY - (500/2));
        Point haut_milieu_bas = new Point(centerX , centerY - (500/2)+(150/2));
        Point haut_gauche = new Point(centerX-(150/2), centerY - (500/2));
        Point haut_droite = new Point(centerX+(150/2), centerY - (500/2));
        
        Point bas_milieu_bas = new Point(centerX , centerY + (500/2));
        Point bas_milieu_haut = new Point(centerX , centerY + (500/2)-(150/2));
        Point bas_gauche = new Point(centerX-(150/2), centerY + (500/2));
        Point bas_droite = new Point(centerX+(150/2), centerY + (500/2));
        
        Point gauche_milieu_bas = new Point(centerX-(500/2), centerY);
        Point gauche_milieu_haut = new Point(centerX-(500/2) + (150/2) , centerY);
        Point gauche_haut = new Point(centerX-(500/2), centerY+(150/2));
        Point gauche_bas = new Point(centerX-(500/2), centerY-(150/2));
        
        Point droite_milieu_bas = new Point(centerX+(500/2), centerY);
        Point droite_milieu_haut = new Point(centerX+(500/2) - (150/2) , centerY);
        Point droite_haut = new Point(centerX+(500/2), centerY-(150/2));
        Point droite_bas = new Point(centerX+(500/2), centerY+(150/2));
        
        Point centre_bas = new Point(centerX, centerY+ (150/2));
        Point centre_milieu = new Point(centerX, centerY);
        Point centre_haut = new Point(centerX, centerY- (150/2));
        Point centre_gauche = new Point(centerX - (150/2), centerY);
        Point centre_droite = new Point(centerX + (150/2), centerY);
        

        this.arrive = centre_milieu; 

        Point[] liste = {haut_milieu_haut,haut_milieu_bas,haut_gauche,haut_droite,bas_milieu_bas,bas_milieu_haut,bas_gauche,bas_droite,
            droite_milieu_bas , droite_milieu_haut,droite_haut,droite_bas,centre_bas,centre_milieu,centre_droite,centre_gauche,centre_haut,
        gauche_milieu_bas, gauche_milieu_haut, gauche_haut, gauche_bas };
        
        this.intersections = new Intersection[liste.length];
        for (int i = 0; i < liste.length; i++) {
            intersections[i] = this.setVoisins(liste[i],liste);
        }

       
        polices = new Police[3];
        polices[0] = new Police(getIntersec(centre_droite));
        polices[1] = new Police(getIntersec(centre_gauche));
        polices[2] = new Police(getIntersec(centre_haut));

        voleur = Voleur.getInstance(getIntersec(centre_milieu));
        
        //Test Piege gauche
        // polices = new Police[3];
        // polices[0] = new Police(getIntersec(centre_milieu));
        // polices[1] = new Police(getIntersec(droite_bas));
        // polices[2] = new Police(getIntersec(droite_haut));
        // voleur = Voleur.getInstance(getIntersec(centre_droite));
    }
    
    
    //fonction privé utilitaire
    private Intersection setVoisins(Point p,Point[] liste){
        Intersection intersec = new Intersection(p);
        ArrayList<Point> voisin = new ArrayList<>();

        for (int i = 0; i < liste.length; i++) {
            if (!p.equals(liste[i])) {
                if (p.distance(liste[i])==75 || p.distance(liste[i]) == 100 ||   ( (int) p.distance(liste[i])==247 && (p.getY()== centerY + (500/2)  || p.getX() == centerX + (500/2) || p.getY()== centerY - (500/2)  || p.getX() == centerX - (500/2)  ) )  ||  (int) p.distance(liste[i])==106  ) {
                    voisin.add(liste[i]);
                }
                    
            }
            
        }

        intersec.setVoisins(voisin);
        return intersec;
        
    }
    public Intersection getIntersec(Point p){
        for (int i = 0; i < intersections.length; i++) {
            if (intersections[i].position.equals(p)) {
                return intersections[i];
            }
        }
        return null;
    }
    
    /* fonction dessin */
    public void drawAllIntersec(Graphics g){
        g.setColor(Color.RED);
        for (int i = 0; i < intersections.length; i++) {

            g.fillOval((int) intersections[i].getPosition().getX(), (int) intersections[i].getPosition().getY(), 15, 15);
        }
    }
    public void drawAllPersonnage(Graphics g){
        voleur.draw(g);
        for (int i = 0; i < polices.length; i++) {
            polices[i].draw(g);
        }
    }
    public void drawVoisinLibre(Intersection p,Graphics g){
        ArrayList<Point> voisins = p.getVoisins();
        for (int i = 0; i < voisins.size() ; i++) {
            Intersection intersec = getIntersec(voisins.get(i));
            if (!intersec.isEstOccupePolice() && !intersec.isEstOccupeVoleurs()) {
                intersec.drawActif(g);
            }
        }
    }
    public void display_title(Graphics g,int width_panel){
        String text="";
        String text2 = "";
        if (this.isTour_user()) {
            text2 = "Police";
            text = "Voleur";
            g.setFont(new Font("Roboto", Font.BOLD, 30));
            g.setColor(Color.RED);
            FontMetrics fm = g.getFontMetrics();
            int x = (width_panel-fm.stringWidth(text)) /2;
            int y = 50;
            g.drawString(text, x, y);
            g.setColor(Color.BLUE);
        }
        else{
            text2 = "Au tour de l'IA";
            g.setColor(Color.BLUE);
            g.setFont(new Font("Roboto", Font.BOLD, 30));
            
        }
        FontMetrics fm = g.getFontMetrics();
        int x = (width_panel-fm.stringWidth(text2)) /2;
        int y = 650;
        g.drawString(text2, x, y);

    }
    

    public void drawWinRobert(Graphics g){
        g.setColor(Color.YELLOW);
        this.getVoleur().getIntersec().drawIntersec(g);
        String text = "Le Voleur a gagne";
        g.setFont(new Font("Roboto", Font.BOLD, 30));
        g.setColor(Color.ORANGE);
        FontMetrics fm = g.getFontMetrics();
        int x = (1000-fm.stringWidth(text)) /2;
        int y = 700/3;
        g.drawString(text, x, y);
        g.setColor(Color.BLUE);
    }
    public void drawWinCops(Graphics g){
        Police[] polices = this.getPolices();
        for (int i = 0; i < polices.length; i++) {
            polices[i].drawProcheVoleur(g);
        }
        g.setColor(Color.ORANGE);
        String text = "Les policie  ont gagne";
        g.setFont(new Font("Roboto", Font.BOLD, 30));
        FontMetrics fm = g.getFontMetrics();
        int x = (1000-fm.stringWidth(text)) /2;
        int y = 700/3;
        g.drawString(text, x, y);
        g.setColor(Color.BLUE);
    }

    //Regles du jeu

    public void checkGagnant(Graphics g){
        int gagnant = gameOver();
        if (a_commence) {
            if (gagnant!=0) {
                this.en_cours = false;
                if (gagnant==COPS_WIN) {
                    this.drawWinCops(g);
                }
                if(gagnant==USER_WIN) {
                    this.drawWinRobert(g);
                }
            }
        }
        
        
    }

    @SuppressWarnings("unlikely-arg-type")
    public int gameOver(){
        
        ArrayList<Point> voisin = this.getVoleur().getIntersec().getVoisins();
        int piege = 0;
        for (int i = 0; i < voisin.size(); i++) {
            if ( this.getIntersec(voisin.get(i)).isEstOccupePolice() ) {
                piege++;
            }
        }
        if (piege==voisin.size()) {
            System.out.println(" Oportunite gagne Gagne");
            return COPS_WIN;
        }
        //Rehefa mandresy ny voleur
        if (this.getVoleur().getIntersec().getPosition().getX()== centerX && this.getVoleur().getIntersec().getPosition().getY()==centerY) {
            System.out.println("Iny fa nisy resy");
            return this.USER_WIN;
        }
        
        return 0;
    }

    public int evaluateBoard(){
        //Rehefa akaiky d avantageux
       

        if (gameOver()!=0) {
            return gameOver();
        }
        int total_distance = 0;
        
        ArrayList<Point> voisin = this.getVoleur().getIntersec().getVoisins();
        
        int point_akaiky = 0;
        boolean misy_voisin=false;

        int point_alavitra=0;
        boolean alavitra=false;
        for (int i = 0; i < voisin.size(); i++) {
            if ( !this.getIntersec(voisin.get(i)).isEstOccupePolice() ) {
                
                point_akaiky-=100000;
                
            }
        }
        
        for (int i = 0; i < voisin.size(); i++) {
            if ( this.getIntersec(voisin.get(i)).isEstOccupePolice() ) {
                
                point_akaiky+=100000;
                misy_voisin=true;
            }
        }
        
        // if (misy_voisin) {
        //     return point_akaiky;
        // }

        for (int i = 0; i <  this.polices.length ; i++) {
            total_distance -= polices[i].getIntersec().getPosition().distance(this.getVoleur().getIntersec().getPosition())*15;   
        }
        total_distance+=point_akaiky;
        return total_distance;
    }

    //setters and getters
    public Intersection[] getIntersections() {
        return intersections;
    }
    public void setIntersections(Intersection[] intersections) {
        this.intersections = intersections;
    }
    public Police[] getPolices() {
        return polices;
    }
    public void setPolices(Police[] polices) {
        this.polices = polices;
    }
    public Voleur getVoleur() {
        return voleur;
    }
    public void setVoleur(Voleur voleur) {
        this.voleur = voleur;
    }
    public boolean isTour_user() {
        return tour_user;
    }
    public void setTour_user(boolean tour_user) {
        this.tour_user = tour_user;
    }
    public IA getIntelligence() {
        return intelligence;
    }
    public void setIntelligence(IA intelligence) {
        this.intelligence = intelligence;
    }
    public boolean isEn_cours() {
        return en_cours;
    }
    public void setEn_cours(boolean en_cours) {
        this.en_cours = en_cours;
    }
    public boolean isA_commence() {
        return a_commence;
    }
    public void setA_commence(boolean a_commence) {
        this.a_commence = a_commence;
    }
    //Pour conserver l'etat initial de l'objet
    @Override
	public Object clone() throws CloneNotSupportedException { // Le copy tokony ao @ board clone ihany ny intersection an'ilay voleur
        // TODO Auto-generated method stub
        Intersection[] cp_intersections = new Intersection[intersections.length] ;
        Police[] cp_polices= new Police[this.polices.length];
        Voleur cp_voleur = (Voleur) this.voleur.clone();
        Intersection inter_voleur = new Intersection();
        for (int i = 0; i < this.polices.length; i++) {
            cp_polices[i] = (Police) polices[i].clone();
            cp_polices[i].setIntersec( (Intersection) (polices[i].getIntersec().clone()));
        }

        for (int i = 0; i < intersections.length; i++) {
            if (intersections[i].isEstOccupeVoleurs()) {
                cp_intersections[i] = cp_voleur.getIntersec();
            }
            else if (intersections[i].isEstOccupePolice()) {
                int indicePolice = getIndicePolice(intersections[i]);
                cp_intersections[i] = cp_polices[indicePolice].getIntersec(); 
            }
            else{
                cp_intersections[i] = (Intersection) intersections[i].clone();
            }
        } 
        

        Board cp_Board = (Board) super.clone();
        cp_Board.setIntersections(cp_intersections);
        cp_Board.setVoleur(cp_voleur);
        cp_Board.setPolices(cp_polices);
        
        return cp_Board;
    }
    private int getIndicePolice(Intersection intersec){
        int indicePolice=0;
        for (int i = 0; i <this.polices.length; i++) {
            if (intersec.equals(polices[i].getIntersec())) {
                indicePolice = i;
                break;
            }
        }
        return indicePolice;
    }
}
