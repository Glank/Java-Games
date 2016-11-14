import java.awt.*;
import java.util.prefs.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FroggerComponent extends JComponent implements KeyListener, Runnable {

    private FroggerLevelEngine engine;
    private boolean upPressed, downPressed, leftPressed, rightPressed;
    public static Vector<FroggerLevel> levels = new Vector();
    private int level = 0;
    //private JLabel l;
    private int life=5;
    private int score = 0;
    private int highscore = readPreference();
    private static final String HIGHSCORE = "highscore1";

    //creo playfrogger
    //PlayFrogger pf = new PlayFrogger();
    public FroggerComponent() {
        super();
        levelInit();
        engine = new FroggerLevelEngine(levels.get(level));
        setPreferredSize(new Dimension(engine.WIDTH, engine.HEIGHT));
        this.life=5;
        addKeyListener(this);
        Thread run = new Thread(this);
        run.start();
    }

    public void levelInit() {

         
        levels.add(new FroggerLevel(
                new int[]{1, 2, 1},
                new String[]{"  YYYY         L  ", " BB      RR     ", "    RR    MM     "}));
        levels.add(new FroggerLevel(
                new int[]{1, 5, 1},
                new String[]{"LLL       GGG      RRR", "RRR          MMM",
                    "RR    MM   LL    RR  RRR"}));

        levels.add(new FroggerLevel(
                new int[]{2, 1, 2, 1, 2},
                new String[]{"RR         L  ", "BB  CCCCCC   RR     ", "    RR    MM     ",
                    "MMM     MMM     ", "RR      L     "}));
        levels.add(new FroggerLevel(
                new int[]{1, 2, 1, 3, 1},
                new String[]{"LLL       MM      RRR    ", "RRR    GGGG    LL   MMM   ",
                    "RR    MM   LL    RR  RRR  ", "  LLLL      BBBB      MMM  ", "  MMMMM        LLLL     MMMM  "}));

        levels.add(new FroggerLevel(
                new int[]{1, 1, 1, 1, 1, 1, 1},
                new String[]{"RR     RR     RR     ", "B   B   B   B   B   ",
                    "MMM   MMM     MMM    ", "RR     RR     RR     ",
                    "Y   Y   B   B   B   B   ", "MMM   MMM     MMM    L ",
                    "   BBB    BBB L    BBB"}));

        levels.add(new FroggerLevel(
                new int[]{1, 1, 2, 2, 3, 2, 1},
                new String[]{"RR     LL     B   ", "LLL      BB   R    ",
                    "RRR      LL        ", "MMM        MM    ", "L          L      ",
                    "RR     L      M     ", "RRR    BL       "}));

        levels.add(new FroggerLevel(
                new int[]{1, 2, 3, 4, 5},
                new String[]{"BB  L    RRR    M", "RR B  MMM     L    ",
                    "MM     LL     BB     ", "M      L      BB      ",
                    "LL            "}));
        levels.add(new FroggerLevel(
                new int[]{2, 3, 1, 2, 3, 2},
                new String[]{"LLL       LLL       ", "   RRRR     RRRR     ",
                    "RR  BB     LL  MM   ", "LLL    BB     RR ", "MMMMM     RBRBR    LLLL    ", "    BBB   MMMM      LLLLL"}));


        levels.add(new FroggerLevel(
                new int[]{1, 3, 4, 3, 1, 2},
                new String[]{"MMM   LL     RR      ", "BBB       LL    RR    ",
                    "BB        LL         ", "BBB       MM    MM   ", "MMM  LL  B    R  L B "
                }));

    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(20);
            } catch (Exception ex) {
            }
            requestFocus();

            try {
                update();
            } catch (InterruptedException ex) {
                Logger.getLogger(FroggerComponent.class.getName()).log(Level.SEVERE, null, ex);
            }

            repaint();
        }
    }

    public void paint(Graphics g) {
        synchronized (g) {
            engine.draw(g);
        }
    }

    public void update() throws InterruptedException {
        if (upPressed) {
            engine.moveUp();
        }
        if (downPressed) {
            engine.moveDown();
        }
        if (leftPressed) {
            engine.moveLeft();
        }
        if (rightPressed) {
            engine.moveRight();
        }

        engine.update();

        if(life==0)
        {
            PlayFrogger.lab2.setText("♥♥♥♥♥");
            PlayFrogger.c.stop();
            JOptionPane.showMessageDialog(this, "Game Over","No more lifes",JOptionPane.ERROR_MESSAGE);
            score=0;
            downPressed=false;
            leftPressed=false;
            rightPressed=false;
            upPressed=false;
            level=0;
            life=5;
            engine = new FroggerLevelEngine(levels.get(level));
            PlayFrogger.c.inizio();
        }
        if (engine.getState() == FroggerState.WON) {
            level++;
            score += 300;
            if (level >= levels.size()) {
                JOptionPane.showMessageDialog(this, "You have beaten this game.");
                System.exit(0);
            }
            engine = new FroggerLevelEngine(levels.get(level));
            if (life>=5){
                String hearts="";
                for(int i=0;i<life;++i)
                {
                    hearts=hearts+"♥";
                    PlayFrogger.lab2.setText(hearts);
                }
            } else{
                ++life;
                String hearts="";
                for(int i=0;i<life;++i)
                {
                    hearts=hearts+"♥";
                    PlayFrogger.lab2.setText(hearts);
                }
            }
        }

        if (engine.getState() == FroggerState.HIT) {
            engine = new FroggerLevelEngine(levels.get(level));
            --life;
            String hearts="";
            for(int i=0;i<life;++i)
            {
                hearts=hearts+"♥";
                PlayFrogger.lab2.setText(hearts);
            }
        }
//                
//                if(levels.get(level))
//                {
//                
//                }
        if (level == 0) {
//    JLabel l = new JLabel();
//    l = pf.getLab();
//    l.setText("1");
            checkHighscore();
            PlayFrogger.lab1.setText("LEVEL 1 - Easy");
            PlayFrogger.lab3.setText("SCORE: " + Integer.toString(score));
            //PlayFrogger.lab1.setHorizontalAlignment(JLabel.CENTER);
            //PlayFrogger.lab1.setLocation(200,200);
            PlayFrogger.lab1.setLocation((369 - PlayFrogger.lab1.getWidth()) / 2, 5);
            PlayFrogger.lab1.setForeground(Color.BLUE);
//                            PlayFrogger.lab1.setFont(new Font("Courier New", Font.ITALIC, 12));
            PlayFrogger.lab3.setLocation((369 - PlayFrogger.lab3.getWidth()), 56);
            PlayFrogger.lab3.setForeground(Color.BLUE);

        } else if (level == 1) {
            checkHighscore();
            PlayFrogger.lab1.setText("LEVEL 2 - Easy");
            PlayFrogger.lab3.setText("SCORE: " + Integer.toString(score));
            //PlayFrogger.lab1.setHorizontalAlignment(JLabel.CENTER);
            //PlayFrogger.lab1.setLocation(200,200);
            PlayFrogger.lab1.setLocation((369 - PlayFrogger.lab1.getWidth()) / 2, 5);
            PlayFrogger.lab1.setForeground(Color.RED);
            PlayFrogger.lab3.setLocation((369 - PlayFrogger.lab3.getWidth()), 56);
            PlayFrogger.lab3.setForeground(Color.BLUE);
            

        } else if (level == 2) {
            checkHighscore();
            PlayFrogger.lab1.setText("LEVEL 3 - Medium");
            PlayFrogger.lab3.setText("SCORE: " + Integer.toString(score));
            //PlayFrogger.lab1.setHorizontalAlignment(JLabel.CENTER);
            //PlayFrogger.lab1.setLocation(200,200);
            PlayFrogger.lab1.setLocation((369 - PlayFrogger.lab1.getWidth()) / 2, 5);
            PlayFrogger.lab1.setForeground(Color.GREEN);
            PlayFrogger.lab3.setLocation((369 - PlayFrogger.lab3.getWidth()), 56);
            PlayFrogger.lab3.setForeground(Color.BLUE);

        } else if (level == 3) {
            checkHighscore();
            PlayFrogger.lab1.setText("LEVEL 4 - Medium");
            PlayFrogger.lab3.setText("SCORE: " + Integer.toString(score));
            //PlayFrogger.lab1.setHorizontalAlignment(JLabel.CENTER);
            //PlayFrogger.lab1.setLocation(200,200);
            PlayFrogger.lab1.setLocation((369 - PlayFrogger.lab1.getWidth()) / 2, 5);
            PlayFrogger.lab1.setForeground(Color.MAGENTA);
            PlayFrogger.lab3.setLocation((369 - PlayFrogger.lab3.getWidth()), 56);
            PlayFrogger.lab3.setForeground(Color.BLUE);

        } else if (level == 4) {
            checkHighscore();
            PlayFrogger.lab1.setText("LEVEL 5 - Medium");
            PlayFrogger.lab3.setText("SCORE: " + Integer.toString(score));
            //PlayFrogger.lab1.setHorizontalAlignment(JLabel.CENTER);
            //PlayFrogger.lab1.setLocation(200,200);
            PlayFrogger.lab1.setLocation((369 - PlayFrogger.lab1.getWidth()) / 2, 5);
            PlayFrogger.lab1.setForeground(Color.PINK);
            PlayFrogger.lab3.setLocation((369 - PlayFrogger.lab3.getWidth()), 56);
            PlayFrogger.lab3.setForeground(Color.BLUE);

        } else if (level == 5) {
            checkHighscore();
            PlayFrogger.lab1.setText("LEVEL 6 - Difficult");
            PlayFrogger.lab3.setText("SCORE: " + Integer.toString(score));
            //PlayFrogger.lab1.setHorizontalAlignment(JLabel.CENTER);
            //PlayFrogger.lab1.setLocation(200,200);
            PlayFrogger.lab1.setLocation((369 - PlayFrogger.lab1.getWidth()) / 2, 5);
            PlayFrogger.lab1.setForeground(Color.GRAY);
            PlayFrogger.lab3.setLocation((369 - PlayFrogger.lab3.getWidth()), 56);
            PlayFrogger.lab3.setForeground(Color.BLUE);

        }else if (level == 6) {
            checkHighscore();
            PlayFrogger.lab1.setText("LEVEL 7 - Difficult");
            PlayFrogger.lab3.setText("SCORE: " + Integer.toString(score));
            //PlayFrogger.lab1.setHorizontalAlignment(JLabel.CENTER);
            //PlayFrogger.lab1.setLocation(200,200);
            PlayFrogger.lab1.setLocation((369 - PlayFrogger.lab1.getWidth()) / 2, 5);
            PlayFrogger.lab1.setForeground(Color.GRAY);
            PlayFrogger.lab3.setLocation((369 - PlayFrogger.lab3.getWidth()), 56);
            PlayFrogger.lab3.setForeground(Color.BLUE);

        }else if (level == 7) {
            checkHighscore();
            PlayFrogger.lab1.setText("LEVEL 8 - Difficult");
            PlayFrogger.lab3.setText("SCORE: " + Integer.toString(score));
            //PlayFrogger.lab1.setHorizontalAlignment(JLabel.CENTER);
            //PlayFrogger.lab1.setLocation(200,200);
            PlayFrogger.lab1.setLocation((369 - PlayFrogger.lab1.getWidth()) / 2, 5);
            PlayFrogger.lab1.setForeground(Color.GRAY);
            PlayFrogger.lab3.setLocation((369 - PlayFrogger.lab3.getWidth()), 56);
            PlayFrogger.lab3.setForeground(Color.BLUE);

        }else if (level == 8) {
            checkHighscore();
            PlayFrogger.lab1.setText("LEVEL 9 - Impossible");
            PlayFrogger.lab3.setText("SCORE: " + Integer.toString(score));
            //PlayFrogger.lab1.setHorizontalAlignment(JLabel.CENTER);
            //PlayFrogger.lab1.setLocation(200,200);
            PlayFrogger.lab1.setLocation((369 - PlayFrogger.lab1.getWidth()) / 2, 5);
            PlayFrogger.lab1.setForeground(Color.GRAY);
            PlayFrogger.lab3.setLocation((369 - PlayFrogger.lab3.getWidth()), 56);
            PlayFrogger.lab3.setForeground(Color.BLUE);

        }
          
    }

    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
            downPressed = true;
        } else if (ke.getKeyCode() == KeyEvent.VK_UP) {
            upPressed = true;
        } else if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            leftPressed = true;
        } else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        } else if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
//			if(engine.getState() == FroggerState.HIT)
//				engine = new FroggerLevelEngine(levels.get(level));
//		}
        }

    }

    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
            downPressed = false;
        } else if (ke.getKeyCode() == KeyEvent.VK_UP) {
            upPressed = false;
        } else if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            leftPressed = false;
        } else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
    }

    public void keyTyped(KeyEvent ke) {
    }
    
    private void checkHighscore() {
        if (score > highscore) {
            highscore = score;

        }

        PlayFrogger.lab4.setText("HIGHSCORE: " + Integer.toString(highscore));
        PlayFrogger.lab4.setLocation((369 - PlayFrogger.lab4.getWidth()), 46);
        PlayFrogger.lab4.setForeground(Color.RED);
        savePreference(highscore);

    }

    public void savePreference(int highscore) {
        Preferences prefs = Preferences.userNodeForPackage(FroggerComponent.class);

        prefs.putInt(HIGHSCORE, highscore);
    }

    public int readPreference() {
        Preferences prefs = Preferences.userNodeForPackage(FroggerComponent.class);

        return prefs.getInt(HIGHSCORE, 0);
    }
}
