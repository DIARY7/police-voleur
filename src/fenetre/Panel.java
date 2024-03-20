package fenetre;

import javax.swing.JPanel;

import composant.Board;
import composant.Intersection;
import composant.Police;
import listener.Listen_user;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Panel extends JPanel {
    MyFrame fen;
    int width;
    int height;
    int centerX;
    int centerY;
    Board board;
    Intersection selecter = null;
    public Rectangle rectangle_test;
    

    public Panel(MyFrame fen){
        board = new Board();
        this.fen=fen;
        width=1000;
        height=700;
        this.setPreferredSize(new Dimension(this.width, this.height));
        this.setBackground(Color.BLACK);
        centerX = this.width/2;
        centerY=this.height/2;
        this.addMouseListener(new Listen_user(this));
    }

    //setters and getters
    public Intersection getSelecter() {
        return selecter;
    }
    public void setSelecter(Intersection selecter) {
        this.selecter = selecter;
    }
    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
    

    public void paint(Graphics g){
        super.paint(g);
        this.board.display_title(g,this.getWidth());
        g.setColor(Color.WHITE);
        g.drawOval(centerX - (150/2), centerY - (150/2), 150, 150); //Centre
        g.drawOval(centerX - (500/2) , centerY - (500/2) , 500, 500); // Grand cercle
        
        Point centre_haut = new Point( centerX , centerY - (500/2) );
        Point centre_bas = new Point(centerX, centerY +(500/2));
        g.drawLine(centerX, centerY - (500/2), centerX, centerY+(500/2)); // Verticale
        
        Point centre_gauche = new Point( centerX-(500/2) , centerY);
        Point centre_droite = new Point(centerX+(500/2), centerY);
        g.drawLine(centerX-(500/2), centerY,  centerX+(500/2), centerY); //Horizontale
        
        g.drawArc( (int) centre_haut.getX()-(150/2), (int) centre_haut.getY()-(150/2), 150, 150, 185, 170); //Arc haut
        g.drawArc( (int) centre_bas.getX()-(150/2), (int) centre_bas.getY()-(150/2), 150, 150, 5, 170); //Arc bas
        g.drawArc( (int) centre_gauche.getX()-(150/2), (int) centre_gauche.getY()-(150/2), 150, 150, 90+185, 170); //Arc gauche
        g.drawArc( (int) centre_droite.getX()-(150/2), (int) centre_droite.getY()-(150/2), 150, 150, 95, 175); //Arc droite
        
        board.drawAllPersonnage(g);
        
        if (selecter!=null) {
            board.drawVoisinLibre(this.selecter, g);
        }
        if (rectangle_test!=null) {
            g.setColor(Color.ORANGE);
            g.drawRect( (int) rectangle_test.getLocation().getX(), (int) rectangle_test.getLocation().getY(), (int) rectangle_test.getWidth(), (int)rectangle_test.getHeight());
        }
       try {
        Thread.sleep(100);
        } catch (Exception e) {
            // TODO: handle exception
        }
        this.board.checkGagnant(g);

        repaint();
    }
    
}
