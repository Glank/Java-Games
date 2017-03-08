package frogger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Fabio
 */
public class Cronometro {
    private JFrame form;  
    private JLabel label;  
    private JPanel panelButtons;  
    
    private Timer timer;  
    private long startTime; 
    public Cronometro() {  
    form = new JFrame("Time");  
    form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    form.setSize(220, 140);  
    form.setResizable(false);
    
    label = new JLabel("0:00:00.0");  
    label.setFont (new Font("SansSerif", Font.BOLD, 30));  
    label.setHorizontalAlignment(JLabel.CENTER);  
   panelButtons = new JPanel(new GridLayout(1, 2));  
//    panelButtons.add(buttonStart);  
//    panelButtons.add(buttonStop);  
    form.add(label, BorderLayout.CENTER);  
    form.add(panelButtons, BorderLayout.SOUTH);  
    timer = new Timer(100, new ActionListener() {  
        public void actionPerformed(ActionEvent e) {  
            long diffTime = System.currentTimeMillis() - startTime;  
            int decSeconds = (int)(diffTime % 1000 / 100);  
            int seconds = (int)(diffTime / 1000 % 60);  
            int minutes = (int)(diffTime / 60000 % 60);  
            int hours = (int)(diffTime / 3600000);  
            String s = String.format("%d:%02d:%02d.%d", hours, minutes, seconds, decSeconds);  
            label.setText(s);  
        }  
    });  
     
    
    form.setVisible (true);  
}  
    
    public void inizio()
            
    {
    
        startTime = System.currentTimeMillis();  
            timer.start();  
           
    }
    
    public void stop()
    {
            timer.stop();  
               
    }
    
    
}
