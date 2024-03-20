package fenetre;

import java.awt.Dimension;

import javax.swing.JFrame;

public class MyFrame extends JFrame {
    
    public MyFrame(){
        this.setTitle("Police voleur");
        Panel panel = new Panel(this);
        this.getContentPane().add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.setResizable(false);
        this.setVisible(true);
        
        
    }
}
