package composant;

import java.awt.Point;

public class IA {
    Board board;

    public IA(Board board){
        this.board = board;
    }
    public void coupIA() throws CloneNotSupportedException{
        System.out.println("Position voleur avant move-police:   "+this.board.getVoleur().getIntersec().getPosition().getX()+","+this.board.getVoleur().getIntersec().getPosition().getY()+"\n\n");
        Board board_reserves = (Board) this.board.clone();
        Police[] polices = board_reserves.getPolices();
        
        int max_decision = Integer.MIN_VALUE;
        int indicePolice = -1; // Le indice an'ilay police ho bougena
      
        int indiceVoisin = 0;

        for (int i = 0; i < polices.length ; i++) {
            for (int j = 0; j <  polices[i].getIntersec().getVoisins().size(); j++) {
                Board board_test = (Board) board_reserves.clone();
                Intersection intersec = (Intersection) board_test.getIntersec( (Point) board_test.getPolices()[i].getIntersec().getVoisins().get(j));
                board_test.getPolices()[i].getIntersec().setEstOccupePolice(false);
                if(!intersec.isEstOccupePolice() && !intersec.isEstOccupeVoleurs()){
                    board_test.getPolices()[i].setIntersec(intersec);
                    int v = miniMax( (Board) board_test.clone(), 2, false,0);        
                    
                    System.out.println("Police n° "+i+ " voisin n° " +j + " = "+v);
                    
                    if (max_decision < v) {
                        max_decision=v;
                        indicePolice = i;
                        indiceVoisin = j;
    
                    }
                }
            }
        }
        System.out.println("Num police"+indicePolice);
        System.out.println("Choix maximum = "+max_decision);
        this.board.getPolices()[indicePolice].getIntersec().setEstOccupePolice(false);
        
        this.board.getPolices()[indicePolice].setIntersec((Intersection) this.board.getIntersec( (Point) this.board.getPolices()[indicePolice].getIntersec().getVoisins().get(indiceVoisin)) );
    }
    public int miniMax(Board board_test,int profondeur,boolean estMax,int jerena){
       
        if (profondeur == 0 || board_test.gameOver()!=0 ) {
            
            return board_test.evaluateBoard();  
        }
        else if (estMax) {
            int v = Integer.MIN_VALUE;
            try {
                Police[] polices = board_test.getPolices();
                for (int i = 0; i < polices.length ; i++) {
                    
                    for (int j = 0; j <  polices[i].getIntersec().getVoisins().size(); j++) {
                        Board board_test1 = (Board) board_test.clone();
                        Intersection intersec = board_test1.getIntersec( polices[i].getIntersec().getVoisins().get(j));
                        if (!intersec.isEstOccupePolice() && !intersec.isEstOccupeVoleurs() ) {
                            board_test1.getPolices()[i].getIntersec().setEstOccupePolice(false);
                            board_test1.getPolices()[i].setIntersec(intersec);
                            int valeur_schema = miniMax( (Board) board_test1.clone(), profondeur-1, false,jerena+1);
                            v = Math.max(valeur_schema, v);    
                            board_test1.getPolices()[i].getIntersec().setEstOccupePolice(false);
                            
                        }
                        if (v == board_test1.getCOPS_WIN()) {
                            break;
                        }
                    }
                }

               
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println(e);
            }
            
            return v;   
        }
        else if(!estMax) {
            int v = Integer.MAX_VALUE;
            try {
                Voleur voleur = board_test.getVoleur();
                if (jerena==0) {
                    System.out.println("La liste des voisin Avant ");
                    for (int i = 0; i < voleur.getIntersec().getVoisins().size()  ; i++) {
                        System.out.println(( (Point) board_test.getVoleur().getIntersec().getVoisins().get(i)).getX()+","+( (Point) board_test.getVoleur().getIntersec().getVoisins().get(i)).getY()+")");        
                    }
                }
                
                for (int i = 0; i < voleur.getIntersec().getVoisins().size() ; i++) {
                    Board board_test1 = (Board) board_test.clone();
                    Intersection intersec = board_test1.getIntersec( (Point) voleur.getIntersec().getVoisins().get(i));
                    if (!intersec.isEstOccupePolice() && !intersec.isEstOccupeVoleurs() ) {
                        board_test1.getVoleur().getIntersec().setEstOccupeVoleurs(false);
                        board_test1.getVoleur().setIntersec(intersec);
                        int valeur_schema = miniMax((Board) board_test1.clone(), profondeur-1, true,jerena+1);
                        //board_test1.getVoleur().getIntersec().setEstOccupeVoleurs(false);
                        
                        v = Math.min(valeur_schema, v);
                        if (jerena==0) {
                            
                            System.out.println( "Police 0 coordonnee : "+ board_test1.getPolices()[0].getIntersec().getPosition().getX()+","+board_test1.getPolices()[0].getIntersec().getPosition().getY() + "  Coordonnee Voleur :("+board_test1.getVoleur().getIntersec().getPosition().getX()+","+board_test1.getVoleur().getIntersec().getPosition().getY()+") = "+v);
                        }
                        if (v == board_test1.getUSER_WIN()) {
                            break;
                        }
                    }
                }
                // if (jerena==0) {
                //     System.out.println("La liste des voisin Apres ");
                //     for (int i = 0; i < voleur.getIntersec().getVoisins().size()  ; i++) {
                //         System.out.println(( (Point) board_test.getVoleur().getIntersec().getVoisins().get(i)).getX()+","+( (Point) board_test.getVoleur().getIntersec().getVoisins().get(i)).getY()+")");        
                //     }
                // }
                  
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println(e);
            }
            return v; 
            
        }
        else{
            return 0;
        }
        
    }
}
