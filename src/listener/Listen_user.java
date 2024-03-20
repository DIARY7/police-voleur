package listener;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import composant.Intersection;
import fenetre.Panel;
public class Listen_user implements MouseListener {
    Panel panel;
    public Listen_user(Panel panel){
        this.panel=panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        if (this.panel.getBoard().isEn_cours()) {
            if (this.panel.getBoard().isTour_user()) {
                move_user(e);
                    
            }
        }
        
    }
    private void move_user(MouseEvent e){
        try {
            int ecartX = 15;
            int ecartY = 15;
            Rectangle zone = new Rectangle(e.getX()-ecartX , e.getY()-ecartY , 30 , 30);
            this.panel.rectangle_test= zone;
            if (this.panel.getSelecter()==null) {
                if (zone.contains(this.panel.getBoard().getVoleur().getIntersec().getPosition())) {
                    
                    this.panel.setSelecter(this.panel.getBoard().getVoleur().getIntersec());   
                }    
            }
            else{
                for (int i = 0; i < this.panel.getSelecter().getVoisins().size() ; i++) {
                    Intersection intersec = this.panel.getBoard().getIntersec(this.panel.getSelecter().getVoisins().get(i));
                    if ( zone.contains(intersec.getPosition()) && !intersec.isEstOccupePolice() && !intersec.isEstOccupeVoleurs() ) {
                        
                        this.panel.getBoard().getVoleur().setIntersec(intersec);
                        this.panel.getBoard().setTour_user(false);
                        this.panel.getBoard().setA_commence(true);
                        break;   
                    }
                }
                this.panel.getSelecter().setEstOccupeVoleurs(false);
                if (!this.panel.getBoard().isTour_user()) {
                    this.panel.getBoard().getIntelligence().coupIA();
                    this.panel.getBoard().setTour_user(true);
                    
                }
                this.panel.setSelecter(null);
                
            }  
        } catch (Exception err) {
            // TODO: handle exception
            System.err.println(err.getMessage());
            
        } 
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    
}
