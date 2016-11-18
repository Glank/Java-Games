
import frogger.Cronometro;
import javax.swing.*;
import java.awt.*;

public class PlayFrogger extends JFrame {

    public static JLabel lab1;
    public static JLabel lab2;
    public static JLabel lab3;
    public static JLabel lab4;
    public static Cronometro c;

    public PlayFrogger() {
        setSize(310,800);
        lab1 = new JLabel("LEVEL");
        lab1.setFont(new Font("Courier New", Font.ITALIC, 18));
        lab2=new JLabel("♥♥♥♥♥");
        lab3 = new JLabel("SCORE");
        lab3.setFont(new Font("Courier New", Font.TYPE1_FONT, 12));
        lab4 = new JLabel("HIGHSCORE");
        lab4.setFont(new Font("Courier New", Font.TYPE1_FONT, 12));
        c=new Cronometro();
        
        c.inizio();
        GridLayout g=new GridLayout(2,2);
        getContentPane().setLayout(g);
        add(lab1);
        add(lab2);
        add(lab3);
        add(lab4);
        add(new FroggerComponent());
        setResizable(false);
        setLocationRelativeTo(null);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
         //lab1.setLocation(75,40);
    }

    public static void main(String[] args) {
        new PlayFrogger();
    }
     
}
