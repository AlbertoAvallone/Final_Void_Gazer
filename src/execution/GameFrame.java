package execution;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import DBase.LevelLoad;
import execution.GameFrame;
import game_objects.Asteroide;
import game_objects.Vector2D;

public class GameFrame extends JFrame{
	private static final long serialVersionUID = 1L;

	private WinningPanel wp; /* winning panel */
	private GamePanel gp; /* game panel */
	private OriginPanel op; /* origin panel*/


	private MainPanel mp; /* main panel*/
	private SetUpPanel sup; /* set up panel */	
	private SelectLevelPanel slp; /* select level panel */


	private Properties prop; /*properties of each level*/
	private int level; /* number of the level */
	private BufferedImage logo, logo_small, victory; /* game logo, void gazer*/
	private AudioPlayer soundtrack;


	public GameFrame() {
		/* adding name*/
		super("Void Gazer");


		/* In order to avoid resize, adding listeners*/
		setResizable(false);
		setFocusable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 500);
		setVisible(true);

		/* creating the logo*/
		try {
			logo = ImageIO.read(getClass().getResource("image3.png"));
			logo_small = ImageIO.read(getClass().getResource("image3_bis.png"));
			victory = ImageIO.read(getClass().getResource("image12.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/* creating all the panel */
		op = new OriginPanel(this, logo);
		setOrigin(); /*setting the starting panel*/	


		/* generate sounds */
		soundtrack = new AudioPlayer("Dire_Space_Emergency_Royalty_Free_Dark_Chiptune_Music.wav");
		soundtrack.start();

		/* creating and setting the general properties*/
		prop = new Properties ();

		try {
			prop.load(new FileInputStream("VoidGazer.cfg"));
		} catch (FileNotFoundException e) {
			System.out.println("Error searching for properties");
		} catch (IOException e) {
			System.out.println("Error reading the properties");
		}

		/* check for default values */
		if (prop.getProperty("sounds") == null) {
			prop.setProperty("sounds", "on");
		}
		if (prop.getProperty("music" )== null) {
			prop.getProperty("music", "on");
		}
		if (prop.getProperty("music") == null) {
			prop.getProperty("music", "70");
		}
		if (prop.getProperty("pause") == null) {
			prop.setProperty("pause", "off");
		}
		if (prop.getProperty("fps") == null) {
			prop.setProperty("fps", "60");
		}
		if (prop.getProperty("writes") == null) {
			prop.setProperty("writes", "on");
		}


		/* creating other panels */
		mp = new MainPanel(this, logo_small);
		slp = new SelectLevelPanel(this, logo_small);
		sup = new SetUpPanel(this, prop);
		wp = new WinningPanel(this, victory);

		/*game panel will be create later after choosing the level*/


	}


	public void closeGamePanel() {
		System.out.println("Closing game panel");
		gp.getLev().reset();
		removeKeyListener(gp);
	}

	public void setGamePanel() {
		/* creating the game panel */
		gp = new GamePanel(level, this);

		/* preparation of the panel */
		gp.setProp(prop);
		gp.setBackground(Color.WHITE);
		setVisible(false);
		setContentPane(gp);
		setVisible(true);
		addKeyListener(gp);
		gp.init();
	}

	public void setWinningPanel() {
		wp.setBackground(Color.BLACK);
		setContentPane(wp);
		setVisible(true);
		addKeyListener(wp);
	}
	public void setSelectLevel() {
		setContentPane(slp);
		setVisible(true);
	}
	public void setOrigin() {
		setContentPane(op);
		setVisible(true);
		addKeyListener(op);
	}
	public void setMain() {
		setContentPane(mp);
		setVisible(true);
	}
	public void setSetUpPanel() {
		setContentPane(sup);
		setVisible(true);
	}



	/* getters and setters*/
	public AudioPlayer getSoundtrack() {
		return soundtrack;
	}


	public void setSoundtrack(AudioPlayer soundtrack) {
		this.soundtrack = soundtrack;
	}



	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	public Properties getProp() {
		return prop;
	}

	public void setProp(Properties prop) {
		this.prop = prop;
	}

}

class OriginPanel extends JPanel implements KeyListener{

	private static final long serialVersionUID = 1L;
	private GameFrame frame;
	private JPanel o, log;

	public OriginPanel(GameFrame frame, BufferedImage logo) {
		super();
		this.frame = frame;


		/* creating origin panel */
		setLayout(new BorderLayout());
		setFocusable(true);

		/* creating panel o, sub-panel of origin */
		o = new JPanel (new GridLayout(1, 3));
		o.setBackground(Color.BLACK);
		o.setForeground(Color.WHITE);

		/* Creating label in order to fill panel o */
		JLabel ini = new JLabel("Press 'd' to continue", SwingConstants.CENTER);
		ini.setBackground(Color.BLACK);
		ini.setForeground(Color.WHITE);

		/* filling o panel panel*/
		o.add(new JLabel());
		o.add(ini);
		o.add(new JLabel());

		add(o, BorderLayout.SOUTH);

		JLabel picLabel = new JLabel(new ImageIcon(logo));
		/* creating the center panel log*/
		log = new JPanel (new BorderLayout());
		log.setBackground(Color.BLACK);
		log.setForeground(Color.WHITE);
		log.add(picLabel, BorderLayout.CENTER);
		add(log, BorderLayout.CENTER);

	}


	@Override
	public void keyTyped(KeyEvent e) {
		/* nothing */
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 'D') {
			System.out.println("Let's go!");
			frame.removeKeyListener(this);
			frame.setMain();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		/*nothing */
	}




}

class MainPanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private GameFrame frame;
	private JButton exit;
	private JButton selectLevel, setup;
	private BufferedImage bi;
	public MainPanel(GameFrame frame, BufferedImage logo) {
		super();
		this.frame = frame;
		this.bi = logo;


		/* creating the Main GameFrame panel */
		System.out.println("I create the MainPanel");
		setLayout(new GridLayout(5, 3));
		setBackground(Color.BLACK);

		/*creating the first button */
		selectLevel = new JButton("Game");
		selectLevel.addActionListener(this);
		selectLevel.setBackground(Color.BLACK);
		selectLevel.setForeground(Color.WHITE);
		selectLevel.setBorderPainted(false);

		/*creating the second button */
		setup = new JButton ("Set Up");
		setup.addActionListener(this);
		setup.setBackground(Color.BLACK);
		setup.setForeground(Color.WHITE);
		setup.setBorderPainted(false);

		/*creating the third button */
		exit = new JButton("Exit");
		exit.addActionListener(this);
		exit.setBackground(Color.BLACK);
		exit.setForeground(Color.WHITE);
		exit.setBorderPainted(false);



		/* filling each line */

		add(new JLabel());
		add(new JLabel(new ImageIcon(bi)));
		add(new JLabel());


		add(new JLabel()); 
		add(selectLevel);
		add(new JLabel());

		add(new JLabel());
		add(setup);
		add(new JLabel());

		add(new JLabel()); 
		add(exit);
		add(new JLabel());

		add(new JLabel());
		add(new JLabel());
		add(new JLabel());


	}





	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == selectLevel) {
			System.out.println("I go to the selection of the levels");
			frame.setSelectLevel();;
			setVisible(true);
		}

		if (e.getSource() == exit ) {
			System.out.println("Closing the application");
			System.exit(0);
		} 
		if (e.getSource() == setup) {
			System.out.println("I go to the Set Up");
			frame.setSetUpPanel();	
			setVisible(true);
		}


	}





}

class SelectLevelPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	private GameFrame frame;
	private JButton Luno, Ldue, back, exit;
	private JPanel selector, ctrl;
	private JLabel en, m;
	private BufferedImage bi;


	public SelectLevelPanel(GameFrame frame, BufferedImage logo) {
		super();
		this.frame = frame;
		this.bi = logo;

		/* creating panel for Game */
		this.setLayout(new BorderLayout());
		setBackground(Color.black);

		/* create the first panel part of Game */
		selector = new JPanel(new GridLayout(4, 3));
		selector.setBackground(Color.black);

		/*create the buttons and add listener*/
		Luno= new JButton("Tutorial");
		Luno.addActionListener(this);
		Luno.setBackground(Color.BLACK);
		Luno.setForeground(Color.WHITE);
		Luno.setBorderPainted(false);

		Ldue= new JButton("Full Level");
		Ldue.addActionListener(this);
		Ldue.setBackground(Color.BLACK);
		Ldue.setForeground(Color.WHITE);
		Ldue.setBorderPainted(false);

		/*filling ppp panel */
		selector.add(new JLabel());
		selector.add(new JLabel(new ImageIcon(bi)));
		selector.add(new JLabel());

		selector.add(new JLabel());
		selector.add(Luno);
		selector.add(new JLabel());

		selector.add(new JLabel());
		selector.add(Ldue);
		selector.add(new JLabel());

		selector.add(new JLabel());
		selector.add(new JLabel());
		selector.add(new JLabel());

		/*add p to the center */
		add(selector, BorderLayout.CENTER);






		/*create ctrl panel */
		ctrl = new JPanel(new GridLayout(1, 4));

		/* create buttons */
		back = new JButton("Menù");
		back.addActionListener(this);
		back.setBackground(Color.BLACK);
		back.setForeground(Color.WHITE);
		back.setBorderPainted(false);


		exit = new JButton("Exit");
		exit.addActionListener(this);
		exit.setBackground(Color.BLACK);
		exit.setForeground(Color.WHITE);
		exit.setBorderPainted(false);

		/*fill ctrl */
		m = new JLabel("To go to menu:", SwingConstants.CENTER);
		m.setOpaque(true);
		m.setBackground(Color.BLACK);
		m.setForeground(Color.WHITE);

		ctrl.add(m);
		ctrl.add(back);

		en = new JLabel("To end:", SwingConstants.CENTER);
		en.setOpaque(true);
		en.setBackground(Color.BLACK);
		en.setForeground(Color.WHITE);

		ctrl.add(en);
		ctrl.add(exit);

		/*add ctrl to the south part of pp */
		add(ctrl, BorderLayout.SOUTH);



	}

	public void actionPerformed(ActionEvent e) {


		if (e.getSource() == exit) {
			System.out.println("I close the application");
			System.exit(0);
		} 

		if (e.getSource() == back) {
			System.out.println("Back to menu");
			frame.setMain();
		}
		if (e.getSource() == Luno) {
			System.out.println("I select the tutorial level");
			frame.setLevel(0);
			frame.setGamePanel();

		}
		if (e.getSource() == Ldue) {
			System.out.println("I select the full level");
			frame.setLevel(1);
			frame.setGamePanel();
		}

	}




}

class SetUpPanel extends JPanel implements ActionListener, ChangeListener{

	private static final long serialVersionUID = 1L;
	private GameFrame frame;

	private JButton back, exit;
	private JCheckBox c1, c2;
	private JSlider sl1, sl2;
	private JPanel sliders, ctrl;
	private JLabel m, en;
	private Properties prop;



	public SetUpPanel(GameFrame frame, Properties prop) {
		this.frame = frame;
		this.prop = prop;

		/* creating Set Up panel */
		this.setLayout(new BorderLayout());

		/* creating sliders */
		sl1 = new JSlider(0, 100);
		sl1.setMajorTickSpacing(10);
		sl1.setPaintTicks(true);
		sl1.setPaintLabels(true);
		sl1.setBackground(Color.BLACK);
		sl1.setForeground(Color.WHITE);
		sl1.addChangeListener(this);
		sl1.setValue(50);

		sl2 = new JSlider(10, 100);
		sl2.setMajorTickSpacing(10);
		sl2.setPaintTicks(true);
		sl2.setPaintLabels(true);
		sl2.setBackground(Color.BLACK);
		sl2.setForeground(Color.WHITE);
		sl2.addChangeListener(this);
		sl2.setValue(60);

		/* creating label  and checkbox*/
		JLabel lb = new JLabel("Music", SwingConstants.CENTER);
		lb.setOpaque(true);
		lb.setBackground(Color.BLACK);
		lb.setForeground(Color.WHITE);

		JLabel lb1 = new JLabel("Game Speed", SwingConstants.CENTER);
		lb1.setOpaque(true);
		lb1.setBackground(Color.BLACK);
		lb1.setForeground(Color.WHITE);

		JLabel lb2 = new JLabel("Game Functions", SwingConstants.CENTER);
		lb2.setOpaque(true);
		lb2.setBackground(Color.BLACK);
		lb2.setForeground(Color.WHITE);	

		c1 = new JCheckBox ("Sound");
		c1.setOpaque(true);
		c1.addActionListener(this);
		c1.setBackground(Color.BLACK);
		c1.setForeground(Color.WHITE);

		c2 = new JCheckBox ("Game notes");
		c2.setOpaque(true);
		c2.addActionListener(this);
		c2.setBackground(Color.BLACK);
		c2.setForeground(Color.WHITE);

		/* creating and filling the central panel */
		sliders = new JPanel (new GridLayout(3, 5));


		sliders.add(new JLabel());
		sliders.add(lb);
		sliders.add(new JLabel());
		sliders.add(sl1);
		sliders.add(new JLabel());

		sliders.add(new JLabel());
		sliders.add(lb1);
		sliders.add(new JLabel());
		sliders.add(sl2);
		sliders.add(new JLabel());

		sliders.add(new JLabel());
		sliders.add(lb2);
		sliders.add(new JLabel());
		sliders.add(c1);
		sliders.add(c2);

		sliders.setBackground(Color.BLACK);
		sliders.setForeground(Color.WHITE);

		add(sliders, BorderLayout.CENTER);

		/*selecting both sounds and record as a default */
		c1.doClick();
		c2.doClick();

		ctrl = new JPanel(new GridLayout(1, 4));

		/* create buttons and labels for ctrl1 panel */
		back = new JButton("Menù");
		back.addActionListener(this);
		back.setBackground(Color.BLACK);
		back.setForeground(Color.WHITE);
		back.setBorderPainted(false);


		exit = new JButton("Exit");
		exit.addActionListener(this);
		exit.setBackground(Color.BLACK);
		exit.setForeground(Color.WHITE);
		exit.setBorderPainted(false);

		m = new JLabel("To go to menu:", SwingConstants.CENTER);
		m.setOpaque(true);
		m.setBackground(Color.BLACK);
		m.setForeground(Color.WHITE);

		ctrl.add(m);
		ctrl.add(back);

		en = new JLabel("To end:", SwingConstants.CENTER);
		en.setOpaque(true);
		en.setBackground(Color.BLACK);
		en.setForeground(Color.WHITE);

		ctrl.add(en);
		ctrl.add(exit);

		/* adding ctrl to the SetUpPanel*/
		add(ctrl, BorderLayout.SOUTH);

	}

	public void actionPerformed(ActionEvent e) {


		if (e.getSource() == exit) {
			System.out.println("Closing the application");
			System.exit(0);
		} 

		if (e.getSource() == back ) {
			System.out.println("Back to menu");
			frame.setMain();
		}

		if (c1.isSelected() == false) {
			prop.setProperty("sounds", "off");
		} 
		if (c1.isSelected() == true){
			prop.setProperty("sounds", "on");
		}


		if (c2.isSelected() == false) {
			prop.setProperty("writes", "off");
		} 
		if (c2.isSelected() == true){
			prop.setProperty("writes", "on");
		}


	}


	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == this.sl1) {
			System.out.printf("I modify the volume of the music at %d per cent\n", sl1.getValue());
			prop.setProperty("music", Integer.toString(sl1.getValue()));
			frame.getSoundtrack().setVolume((float)sl1.getValue()/100);
		}
		if (e.getSource() == this.sl2) {
			System.out.printf("I modify the game speed at %d fps\n", sl2.getValue());
			prop.setProperty("fps", Integer.toString(sl2.getValue()));
		}

	}

}

class WinningPanel extends JPanel implements KeyListener{
	private static final long serialVersionUID = 1L;
	private GameFrame frame;
	private JLabel l, win;
	private BufferedImage winning;


	public WinningPanel(GameFrame frame, BufferedImage victory) {
		this.frame = frame;

		this.winning = victory;

		this.setForeground(Color.WHITE);
		this.setLayout(new BorderLayout());

		l = new JLabel ("Press 'enter' to go to select levels", SwingConstants.CENTER);
		l.setBackground(Color.BLACK);
		l.setForeground(Color.WHITE);
		this.add(l, BorderLayout.SOUTH);

		/* creating the winning advise*/


		win = new JLabel (new ImageIcon(winning));
		this.add(win, BorderLayout.CENTER);
	}


	/* keyevents handler */
	@Override
	public void keyTyped(KeyEvent e) {
		/* nothing */
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			System.out.println("I go back to the selection of the levels");
			frame.removeKeyListener(this);
			frame.setSelectLevel();
		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.out.println("Going out from the game");

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		/* nothing */		
	}

}


class GamePanel extends JPanel implements Runnable, KeyListener{



	private static final long serialVersionUID = 1L;

	/* Values from GameFrame*/
	private GameFrame frame;
	private Properties prop;
	private int level;

	/* Values to extract the data from the DB*/
	private LevelLoad l; 
	private ResultSet nave, asteroidi, traguardo, border;
	private Level lev;

	private boolean bool_border, bool_ast;
	private boolean is_twisted, is_pressed, one_time, is_thrustOn;

	/* Data for counting time*/
	private long tEnd, tStart, tDelta;
	private long tSecond, tFirst, tDifference;
	private long sleepTime, skipTicks, nextTick; 

	/* Counter that contain the step number for the tutorial*/
	private int tutorial_steps;

	/* Graphic elements */
	private GraphicBorder gb;
	private GraphicAsteroidi a;
	private GraphicNave gn;
	private GraphicTraguardo gt;
	private ShipGraphicCoordinates sgc;

	/* iterator use to select asteroids*/
	private Iterator <Asteroide> it;

	/* resolution for the initialization*/
	private Resolution r;
	/* deaths counter*/
	private int deaths;

	/* sounds */

	private AudioPlayer clunk, boom;
	public GamePanel (int i, GameFrame frame) {
		super();
		/* setting the value passed from GameFrame*/
		this.frame = frame;
		this.level = i;
		this.tutorial_steps = 0;

		/* boolean indicators */
		this.bool_border = false;
		this.bool_ast = false;
		this.is_twisted = false;
		this.is_pressed = false;
		this.is_thrustOn = false;

		/* game time */
		this.tDifference = 0; 
		this.tFirst = 0;
		this.tSecond = 0;


		/* set to 0 the deaths count*/
		this.deaths = 0;

		/* sounds */

		clunk = new AudioPlayer("clunk.wav");
		boom = new AudioPlayer("8-_bit_explosion_sound_effect_SFX.wav");


		/* extracting data from the DB */
		l = new LevelLoad ();
		lev = new Level(this, l);


		System.out.println("Extracting the Resultset of Nave from the DB");
		this.nave = l.extractNave(level);
		lev.NaveLevel(nave);

		System.out.println("Extracting the Resultset of Asteroidi from DB");
		this.asteroidi = l.extractAsteroidi(level);
		lev.AsteroideLevel(asteroidi);


		System.out.println("Extracting the Traguardo from DB");
		this.traguardo = l.extractTraguardo(level);
		lev.TraguardoLevel(traguardo);


		System.out.println("Extracting the Border from DB");
		this.border = l.extractBorder(level);
		lev.BorderLevel(border);



	}


	public void init() {
		/* initialization of graphic data*/
		r = new Resolution(this.getWidth(), this.getHeight());
		this.sgc = new ShipGraphicCoordinates (lev.getN(), r);
		this.gn = new GraphicNave(lev.getN(), sgc);
		this.gt = new GraphicTraguardo(sgc, lev.getT());
		this.gb = new GraphicBorder(sgc, lev.getB());
		this.a = new GraphicAsteroidi(lev.getA(), sgc);




		this.tFirst= System.currentTimeMillis() / 1000;
		this.tSecond = this.tFirst;
		/*starting new thread event*/
		new Thread(this).start();

	}

	@Override
	public void run() {
		/*setting max priority for run*/
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		/*pause of the game*/
		nextTick = System.nanoTime();
		prop.setProperty("pause", "off");
		while(true) {
			/* pause */
			while(prop.getProperty("pause").equals("on")) {
				try {
					Thread.sleep(100);
					nextTick = System.nanoTime();
				} catch (InterruptedException e) {
				}
			}

			/* game speed*/
			skipTicks = (long) (Math.pow(10, 9) / Integer.parseInt(prop.getProperty("fps")));
			nextTick += skipTicks;
			sleepTime = nextTick - System.nanoTime();
			if (sleepTime > 0) {
				try {
					Thread.sleep(sleepTime / 1000000);
				} catch (InterruptedException e) {
				}
			}

			/* updating each gameobject*/
			lev.getN().update();
			it = lev.getA().iterator();
			while(it.hasNext()) {
				it.next().update(); 
			}
			lev.getT().update();
			lev.getB().update();


			/* controlling if there is a collision */
			/* count down for bool_border */
			if (bool_border == true) {
				this.tEnd = System.currentTimeMillis();
				tDelta = tEnd - tStart;
				if (tDelta >= 2000) {
					bool_border = false;
				}
			}

			/* count down for the asteroids*/
			if (bool_ast == true) {
				this.tEnd = System.currentTimeMillis();
				tDelta = tEnd - tStart;
				if (tDelta >= 2000) {
					bool_ast = false;				
				}
			}



			/* collisions */
			it = lev.getA().iterator();
			while(it.hasNext()) {
				if (lev.getN().collision(it.next())) {
					System.out.println("Collision!");

					if (prop.getProperty("sounds").equals("on")) {
						this.boom.setAudio();
					}

					++this.deaths;
					this.bool_ast = true;
					this.tStart = System.currentTimeMillis();
					lev.reset();
				}
			}

			if (lev.getN().collision(lev.getT())) {
				System.out.println("Good job!");
				prop.setProperty("pause", "on");
				lev.reset();
				this.removeKeyListener(this);
				frame.setWinningPanel();
			}


			if (lev.getN().collisionOrizz(lev.getB())) {
				System.out.println("You are at the border!");
				lev.getN().setSpeedY(-lev.getN().getSpeedY());

				if (prop.getProperty("sounds").equals("on")) {
					this.clunk.setAudio();
				}


				this.bool_border = true;
				this.tStart = System.currentTimeMillis();
			}
			else if (lev.getN().collisionVert(lev.getB())) {
				System.out.println("You are at the border!");
				lev.getN().setSpeedX(-lev.getN().getSpeedX());

				if (prop.getProperty("sounds").equals("on")) {
					this.clunk.setAudio();
				}


				this.bool_border = true;
				this.tStart = System.currentTimeMillis();
			}

			/* repaint everything*/
			repaint();

		}



	}



	public void paintComponent(Graphics g) {
		/* painting components previously modified*/
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		gn.draw(g);
		sgc.update();
		gt.draw(g);
		gb.draw(g);
		a.draw(g);

		if(is_thrustOn == true)
			gn.thrustGraphicOn(g);

		/* displaying some phrase on screen */
		g.setColor(Color.WHITE);

		if (prop.get("writes") == "on") {
			/* when the player hit an asteroid or the border */

			if(this.bool_border) {
				g.drawString("You hit the border!", (this.getWidth() / 2 ) - 50, this.getHeight() - 20);
			}
			if (this.bool_ast) {
				g.drawString("You hit an asteroid!", (this.getWidth() / 2 ) - 50, this.getHeight() - 10);
			}

			/* coordinates */
			g.drawString("Coordinates: " + "(" + (int) (lev.getN().getX() - lev.getN_o().getX()) + ", "+ (int) (lev.getN().getY()  - lev.getN_o().getY()) +")", 20, this.getHeight() - 10);

			/* deaths count*/
			g.drawString("Deaths:" + this.deaths, this.getWidth() - 120, 20);

			/* teasing messages*/
			if (this.deaths >= 100)
				g.drawString("(what a noob)", this.getWidth() - 120, 30);
			if (tDifference >= 3600)
				g.drawString("More than 1 hour? You are sick man ...", 20, 20);

			/* timer */

			if ((level == 0 && tutorial_steps >= 4) || level > 0 && is_pressed == true) {
				tSecond = System.currentTimeMillis()/ 1000;
				if (one_time == false) {
					tFirst = tSecond;
					one_time = true;
				}
			} 

			tDifference = tSecond - tFirst;
			if (tDifference < 60) {
				g.drawString("Timer:" + tDifference + "s", this.getWidth() - 120, this.getHeight() - 10);
			}
			else if (tDifference >= 60 && tDifference < 3600){
				g.drawString("Timer:" + (tDifference/60)+ "min "+ (tDifference%60) + "s", this.getWidth() - 120, this.getHeight() - 10);
			}
			else if (tDifference >= 3600){
				g.drawString("Timer:"+ (tDifference / 3600) + "h " + ((tDifference/60) % 60) + "min "+ (tDifference%60) + "s", this.getWidth() - 120, this.getHeight() - 10);
			}

		}



		/* if it's the tutorial */
		if (level == 0) {
			if (tutorial_steps == 0) {
				g.drawString("Welcome to Void Gazer, you are in Space now", (this.getWidth() / 2 ) - 100, this.getHeight() - 40);
				g.drawString("and the white circle is the only light you have", (this.getWidth() / 2 ) - 100, this.getHeight() - 30);
				g.drawString("to see what is around you. If you want to go back to menu", (this.getWidth() / 2 ) - 100, this.getHeight() - 20);
				g.drawString("you can press 'esc' whenever you want (Press 'q' to go on)", (this.getWidth() / 2 ) - 100, this.getHeight() - 10);
			}
			else if (tutorial_steps == 1) {
				prop.setProperty("pause", "off");
				g.drawString("<= This is where you are", (this.getWidth() / 2 ) - 50, this.getHeight() - 20);
				g.drawString("(Press 'q' to go on)", (this.getWidth() / 2 ) - 50, this.getHeight() - 10);
			}
			else if (tutorial_steps == 2) {
				g.drawString("This is the timer of your game session =>", (this.getWidth() / 2 ) - 100, this.getHeight() - 20);
				g.drawString("Both can be removed in the settings (Press 'q' to go on)", (this.getWidth() / 2 ) - 100, this.getHeight() - 10);
			}
			else if (tutorial_steps == 3) {
				g.drawString("You are free to move now but you can come back here", (this.getWidth() / 2 ) - 100, this.getHeight() - 30);
				g.drawString("pressing the button 'enter' whenever you want.", (this.getWidth() / 2 ) - 100, this.getHeight() - 20);
				g.drawString("Press left and right arrows to tilt the ship", (this.getWidth() / 2 ) - 100, this.getHeight() - 10);
			} 
			else if (is_twisted == true && tutorial_steps == 4) {
				g.drawString("Now, to move, press 'space'... press it", (this.getWidth() / 2 ) - 80, this.getHeight() - 30);
				g.drawString("carefully, in Space you can't stop.", (this.getWidth() / 2 ) - 80, this.getHeight() - 20);
				g.drawString("I suggest to go right, near (200, 0).", (this.getWidth() / 2 ) - 80, this.getHeight() - 10);
			}
			else if ( Vector2D.distance(lev.getA().get(0).getV_pos(), lev.getN().getV_pos()) + 20 <= (lev.getN().getLightRadius() + lev.getA().get(0).getRadius()) ) {
				if(tutorial_steps == 5) {
					lev.getN().setSpeedX(0);
					lev.getN().setSpeedY(0);
					++tutorial_steps;
				}
				g.drawString("You see the black circle? It's an asteroid,", (this.getWidth() / 2 ) - 100, this.getHeight() - 30);
				g.drawString("this is not moving, but others can.", (this.getWidth() / 2 ) - 100, this.getHeight() - 20);
				g.drawString("Try to go right, near (650, 0), avoiding it.", (this.getWidth() / 2 ) - 100, this.getHeight() - 10);
			}

			else if (Vector2D.distance(lev.getN().getV_pos(), lev.getT().getVertici()[1]) <= (lev.getN().getLightRadius())
					&&
					Vector2D.distance(lev.getN().getV_pos(), lev.getT().getVertici()[2]) <= (lev.getN().getLightRadius())
					&&
					Vector2D.distance(lev.getN().getV_pos(), lev.getT().getVertici()[3]) <= (lev.getN().getLightRadius())
					) {

				if(tutorial_steps == 6) {
					lev.getN().setSpeedX(0);
					lev.getN().setSpeedY(0);
					++tutorial_steps;
				}
				g.drawString("This is the end of the level,", (this.getWidth() / 2 ) - 50, this.getHeight() - 20);
				g.drawString("you have to hit it to win.", (this.getWidth() / 2 ) - 50, this.getHeight() - 10);

			}

		}


	}



	/* keytyped events*/

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

		if ( (level == 0 && tutorial_steps >= 3) || level != 0) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				/* turning left the ship*/
				if(lev.getN().getpitch() != 2) {
					lev.getN().setpitch(lev.getN().getpitch() + 0.1);
				} else {
					lev.getN().setpitch(0.0);
				}
				/* setting is_twisted for the tutorial */
				is_twisted = true;
				if (tutorial_steps == 3)
					++tutorial_steps;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				/* turning right the ship*/
				if(lev.getN().getpitch() != 0) {
					lev.getN().setpitch(lev.getN().getpitch() - 0.1);
				} else {
					lev.getN().setpitch(2.0);
				}

				/* setting is_twisted for the tutorial */
				is_twisted = true;
				if (tutorial_steps == 3)
					++tutorial_steps;
			}

			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				/* moving forward the ship*/
				lev.getN().thrustOn();
				is_thrustOn = true;
				lev.getN().update();
				if (tutorial_steps == 4)
					++tutorial_steps;
				is_pressed = true;	
			}
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				System.out.println("Restart");
				lev.reset();
			}
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				System.out.println("Exit from game");
				this.removeKeyListener(this);
				frame.setMain();
				frame.removeKeyListener(this);
				frame.closeGamePanel();
			}


		}

		if (level == 0 && tutorial_steps < 3) {
			if (e.getKeyCode() == KeyEvent.VK_Q) {
				System.out.println("Next tutorial step");
				++this.tutorial_steps;
			}
		}

	}



	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			/* stop turning left the ship*/
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			/* stop turning right the ship*/
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			/* stop moving forward the ship*/
			is_thrustOn = false;
		}
	}


	/* getter and setter of the properties */
	public Properties getProp() {
		return prop;
	}

	public void setProp(Properties prop) {
		this.prop = prop;
	}

	public Level getLev() {
		return lev;
	}


	public void setLev(Level lev) {
		this.lev = lev;
	}
}
