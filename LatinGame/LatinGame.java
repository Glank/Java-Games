import java.net.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class LatinGame extends JFrame implements ActionListener
{
	JButton pictures  = new JButton("Pinacotheca");
	JButton declentions = new JButton("Declinationes");
	CardLayout mainLayout = new CardLayout();
	ImageIcon picture = new ImageIcon();
	JPanel main;
	JButton c1 = new JButton("C1");
	JButton c2 = new JButton("C2");
	JButton c3 = new JButton("C3");
	JButton c4 = new JButton("C4");
	JButton home = new JButton("Ad Domum");

	ArrayList<DeclintionChart> charts = new ArrayList();

	ArrayList<String> words = new ArrayList();
	ArrayList<String> pics = new ArrayList();
	ImageLoader imageLoader = new ImageLoader();
	JLabel question = new JLabel("");

	JPanel chartPanel;
	JPanel picturePanel;
	JPanel choicePanel;

	String mode = "pictures";
	String answer = "";

	public LatinGame(Applet a)
	{
		super();
		imageLoader.applet = a;
		main = (JPanel)getContentPane();
		main.setLayout(mainLayout);

		home.addActionListener(this);

		choicePanel = new JPanel(new GridLayout(1,4));
		choicePanel.add(c1);
		choicePanel.add(c2);
		choicePanel.add(c3);
		choicePanel.add(c4);
		c1.addActionListener(this);
		c2.addActionListener(this);
		c3.addActionListener(this);
		c4.addActionListener(this);

		JPanel selection = new JPanel(new FlowLayout());
		selection.add(pictures);
		selection.add(declentions);
		pictures.addActionListener(this);
		declentions.addActionListener(this);
		main.add("home",selection);
		mainLayout.first(main);

		picturePanel = new JPanel(new BorderLayout());
		picturePanel.add(new JLabel(picture), BorderLayout.CENTER);
		picturePanel.add(choicePanel, BorderLayout.NORTH);
		picturePanel.add(home, BorderLayout.SOUTH);
		main.add("pictures", picturePanel);

		pics.add("house.jpg");
		words.add("villa");

		pics.add("sword.jpg");
		words.add("gladius");

		pics.add("pulsat.jpg");
		words.add("pulsat");

		pics.add("heart.jpg");
		words.add("cor");

		pics.add("rose.jpg");
		words.add("rosa");

		pics.add("horse.jpg");
		words.add("equus");

		pics.add("sheep.jpg");
		words.add("ovis");

		pics.add("monkey.jpg");
		words.add("metus");

		pics.add("pilum.jpg");
		words.add("pilum");

		pics.add("army.jpg");
		words.add("exercitus");

		pics.add("wolf.jpg");
		words.add("lupus");

		pics.add("puppy.jpg");
		words.add("canis");

		/*pics.add("http://img.bxboy.net/vagina.jpg");
		words.add("theca");*/

		System.out.println("Loading Images...");
		for(int i = 0; i < pics.size(); i++)
		{
			try
			{
				imageLoader.load(pics.get(i));
			}
			catch(Exception ex)
			{
				System.err.println(ex);
				System.exit(1);
			}
		}

		System.out.println("Finished.");

		charts.add(new DeclintionChart());
		charts.add(new DeclintionChart());
		charts.add(new DeclintionChart());
		charts.add(new DeclintionChart());
		charts.add(new DeclintionChart());

		charts.get(0).add("hora", "nom. s.");
		charts.get(0).add("horam", "acc. s.");
		charts.get(0).add("horae", "gen. s.");
		charts.get(0).add("horae", "dat. s.");
		charts.get(0).add("horâ", "abl. s.");

		charts.get(0).add("horae", "nom. p.");
		charts.get(0).add("horae", "acc. p.");
		charts.get(0).add("horarum", "gen. p.");
		charts.get(0).add("horis", "dat. p.");
		charts.get(0).add("horis", "abl. p.");

		charts.get(1).add("servus", "nom. s.");
		charts.get(1).add("servum", "acc. s.");
		charts.get(1).add("servi", "gen. s.");
		charts.get(1).add("servo", "dat. s.");
		charts.get(1).add("servo", "abl. s.");

		charts.get(1).add("servi", "nom. p.");
		charts.get(1).add("servos", "acc. p.");
		charts.get(1).add("servorum", "gen. p.");
		charts.get(1).add("servis", "dat. p.");
		charts.get(1).add("servis", "abl. p.");

		charts.get(2).add("sol", "nom. s.");
		charts.get(2).add("solem", "acc. s.");
		charts.get(2).add("solis", "gen. s.");
		charts.get(2).add("soli", "dat. s.");
		charts.get(2).add("sole", "abl. s.");

		charts.get(2).add("soles", "nom. p.");
		charts.get(2).add("soles", "acc. p.");
		charts.get(2).add("solum", "gen. p.");
		charts.get(2).add("solibus", "dat. p.");
		charts.get(2).add("solibus", "abl. p.");

		charts.get(3).add("exercitus", "nom. s.");
		charts.get(3).add("exercitum", "acc. s.");
		charts.get(3).add("exercitûs", "gen. s.");
		charts.get(3).add("exercitui", "dat. s.");
		charts.get(3).add("exercitû", "abl. s.");

		charts.get(3).add("exercitûs", "nom. p.");
		charts.get(3).add("exercitûs", "acc. p.");
		charts.get(3).add("exercituum", "gen. p.");
		charts.get(3).add("exertitibus", "dat. p.");
		charts.get(3).add("exertitibus", "abl. p.");

		charts.get(4).add("diês", "nom. s.");
		charts.get(4).add("diem", "acc. s.");
		charts.get(4).add("diêi", "gen. s.");
		charts.get(4).add("diêi", "dat. s.");
		charts.get(4).add("diê", "abl. s.");

		charts.get(4).add("diês", "nom. p.");
		charts.get(4).add("diês", "acc. p.");
		charts.get(4).add("diêrum", "gen. p.");
		charts.get(4).add("diêbus", "dat. p.");
		charts.get(4).add("diêbus", "abl. p.");

		chartPanel = new JPanel(new BorderLayout());
		chartPanel.add(question, BorderLayout.NORTH);
		chartPanel.add(choicePanel, BorderLayout.CENTER);
		chartPanel.add(home, BorderLayout.SOUTH);
		main.add("chart", chartPanel);

		setSize(400,300);
		setTitle("Ludet Cum Verbis");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public LatinGame()
	{
		super();
		main = (JPanel)getContentPane();
		main.setLayout(mainLayout);

		home.addActionListener(this);

		choicePanel = new JPanel(new GridLayout(1,4));
		choicePanel.add(c1);
		choicePanel.add(c2);
		choicePanel.add(c3);
		choicePanel.add(c4);
		c1.addActionListener(this);
		c2.addActionListener(this);
		c3.addActionListener(this);
		c4.addActionListener(this);

		JPanel selection = new JPanel(new FlowLayout());
		selection.add(pictures);
		selection.add(declentions);
		pictures.addActionListener(this);
		declentions.addActionListener(this);
		main.add("home",selection);
		mainLayout.first(main);

		picturePanel = new JPanel(new BorderLayout());
		picturePanel.add(new JLabel(picture), BorderLayout.CENTER);
		picturePanel.add(choicePanel, BorderLayout.NORTH);
		picturePanel.add(home, BorderLayout.SOUTH);
		main.add("pictures", picturePanel);

		pics.add("house.jpg");
		words.add("villa");

		pics.add("sword.jpg");
		words.add("gladius");

		pics.add("pulsat.jpg");
		words.add("pulsat");

		pics.add("heart.jpg");
		words.add("cor");

		pics.add("rose.jpg");
		words.add("rosa");

		pics.add("horse.jpg");
		words.add("equus");

		pics.add("sheep.jpg");
		words.add("ovis");

		pics.add("monkey.jpg");
		words.add("metus");

		pics.add("pilum.jpg");
		words.add("pilum");

		pics.add("army.jpg");
		words.add("exercitus");

		pics.add("wolf.jpg");
		words.add("lupus");

		pics.add("puppy.jpg");
		words.add("canis");

		/*pics.add("http://img.bxboy.net/vagina.jpg");
		words.add("theca");*/

		System.out.println("Loading Images...");
		for(int i = 0; i < pics.size(); i++)
		{
			try
			{
				imageLoader.load((pics.get(i)));
			}
			catch(Exception ex)
			{
				System.err.println(ex);
				System.exit(1);
			}
		}

		System.out.println("Finished.");

		charts.add(new DeclintionChart());
		charts.add(new DeclintionChart());
		charts.add(new DeclintionChart());
		charts.add(new DeclintionChart());
		charts.add(new DeclintionChart());

		charts.get(0).add("hora", "nom. s.");
		charts.get(0).add("horam", "acc. s.");
		charts.get(0).add("horae", "gen. s.");
		charts.get(0).add("horae", "dat. s.");
		charts.get(0).add("horâ", "abl. s.");

		charts.get(0).add("horae", "nom. p.");
		charts.get(0).add("horae", "acc. p.");
		charts.get(0).add("horarum", "gen. p.");
		charts.get(0).add("horis", "dat. p.");
		charts.get(0).add("horis", "abl. p.");

		charts.get(1).add("servus", "nom. s.");
		charts.get(1).add("servum", "acc. s.");
		charts.get(1).add("servi", "gen. s.");
		charts.get(1).add("servo", "dat. s.");
		charts.get(1).add("servo", "abl. s.");

		charts.get(1).add("servi", "nom. p.");
		charts.get(1).add("servos", "acc. p.");
		charts.get(1).add("servorum", "gen. p.");
		charts.get(1).add("servis", "dat. p.");
		charts.get(1).add("servis", "abl. p.");

		charts.get(2).add("sol", "nom. s.");
		charts.get(2).add("solem", "acc. s.");
		charts.get(2).add("solis", "gen. s.");
		charts.get(2).add("soli", "dat. s.");
		charts.get(2).add("sole", "abl. s.");

		charts.get(2).add("soles", "nom. p.");
		charts.get(2).add("soles", "acc. p.");
		charts.get(2).add("solum", "gen. p.");
		charts.get(2).add("solibus", "dat. p.");
		charts.get(2).add("solibus", "abl. p.");

		charts.get(3).add("exercitus", "nom. s.");
		charts.get(3).add("exercitum", "acc. s.");
		charts.get(3).add("exercitûs", "gen. s.");
		charts.get(3).add("exercitui", "dat. s.");
		charts.get(3).add("exercitû", "abl. s.");

		charts.get(3).add("exercitûs", "nom. p.");
		charts.get(3).add("exercitûs", "acc. p.");
		charts.get(3).add("exercituum", "gen. p.");
		charts.get(3).add("exertitibus", "dat. p.");
		charts.get(3).add("exertitibus", "abl. p.");

		charts.get(4).add("diês", "nom. s.");
		charts.get(4).add("diem", "acc. s.");
		charts.get(4).add("diêi", "gen. s.");
		charts.get(4).add("diêi", "dat. s.");
		charts.get(4).add("diê", "abl. s.");

		charts.get(4).add("diês", "nom. p.");
		charts.get(4).add("diês", "acc. p.");
		charts.get(4).add("diêrum", "gen. p.");
		charts.get(4).add("diêbus", "dat. p.");
		charts.get(4).add("diêbus", "abl. p.");

		chartPanel = new JPanel(new BorderLayout());
		chartPanel.add(question, BorderLayout.NORTH);
		chartPanel.add(choicePanel, BorderLayout.CENTER);
		chartPanel.add(home, BorderLayout.SOUTH);
		main.add("chart", chartPanel);

		setSize(400,300);
		setTitle("Ludet Cum Verbis");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void choosePicture()
	{
		c1.setText(words.get((int)(Math.random()*words.size())));
		c2.setText(words.get((int)(Math.random()*words.size())));
		c3.setText(words.get((int)(Math.random()*words.size())));
		c4.setText(words.get((int)(Math.random()*words.size())));
		int pic = (int)(Math.random()*words.size());
		if(Math.random() < .25)
		{
			c1.setText(words.get(pic));
		}
		else if(Math.random() < .3333)
		{
			c2.setText(words.get(pic));
		}
		else if(Math.random() < .5)
		{
			c3.setText(words.get(pic));
		}
		else
		{
			c4.setText(words.get(pic));
		}
		//System.out.println(pics.get(pic));
		picture.setImage(imageLoader.getImage(pics.get(pic)));
		answer = words.get(pic);
	}

	public void chooseChart()
	{
		int chart = (int)(Math.random()*charts.size());
		c1.setText(charts.get(chart).chart.get((int)(Math.random()*charts.get(chart).chart.size())));
		c2.setText(charts.get(chart).chart.get((int)(Math.random()*charts.get(chart).chart.size())));
		c3.setText(charts.get(chart).chart.get((int)(Math.random()*charts.get(chart).chart.size())));
		c4.setText(charts.get(chart).chart.get((int)(Math.random()*charts.get(chart).chart.size())));
		int selected = (int)(Math.random()*charts.get(chart).chart.size());
		if(Math.random() < .25)
		{
			c1.setText(charts.get(chart).chart.get(selected));
		}
		else if(Math.random() < .3333)
		{
			c2.setText(charts.get(chart).chart.get(selected));
		}
		else if(Math.random() < .5)
		{
			c3.setText(charts.get(chart).chart.get(selected));
		}
		else
		{
			c4.setText(charts.get(chart).chart.get(selected));
		}
		question.setText("Change " + charts.get(chart).chart.get((int)(Math.random()*charts.get(chart).chart.size())) + " to " + charts.get(chart).type.get(selected));
		answer = charts.get(chart).chart.get(selected);
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == pictures)
		{
			mode = "pictures";
			picturePanel.add(choicePanel, BorderLayout.NORTH);
			picturePanel.add(home, BorderLayout.SOUTH);
			mainLayout.show(main,"pictures");
			choosePicture();
			pack();
			chartPanel.requestFocus();
			home.requestFocus();
		}
		if(ae.getSource() == declentions)
		{
			mode = "chart";
			chartPanel.add(question, BorderLayout.NORTH);
			chartPanel.add(choicePanel, BorderLayout.CENTER);
			chartPanel.add(home, BorderLayout.SOUTH);
			mainLayout.show(main,"chart");
			chooseChart();
			pack();
		}
		if(ae.getSource() == home)
		{
			setSize(400,300);
			mainLayout.show(main,"home");
		}
		if(ae.getSource() == c1)
		{
			if(c1.getText().equals(answer))
			{
				JOptionPane.showMessageDialog(this,"Bene!");
				nextQuestion();
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Minime.");
			}
		}
		if(ae.getSource() == c2)
		{
			if(c2.getText().equals(answer))
			{
				JOptionPane.showMessageDialog(this,"Bene!");
				nextQuestion();
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Minime.");
			}
		}
		if(ae.getSource() == c3)
		{
			if(c3.getText().equals(answer))
			{
				JOptionPane.showMessageDialog(this,"Bene!");
				nextQuestion();
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Minime.");
			}
		}
		if(ae.getSource() == c4)
		{
			if(c4.getText().equals(answer))
			{
				JOptionPane.showMessageDialog(this,"Bene!");
				nextQuestion();
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Minime.");
			}
		}
	}

	public void nextQuestion()
	{
		if(mode.equals("pictures"))
		{
			choosePicture();
			pack();
		}
		else
		{
			chooseChart();
			pack();
		}
	}

	public static void main(String[] args)
	{
		new LatinGame();
	}
}