import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;



public class Gametable extends Canvas {
	public static final int TABLEWITH = 900;
	public static final int TABLEHEIGHT = 900;
	
	private Random rand = new Random();
	
	// ************ 导入图片 ***********
	private Image wall = Toolkit.getDefaultToolkit().getImage("F:\\java_project\\tank\\img\\wall.gif");
	private Image stone = Toolkit.getDefaultToolkit().getImage("F:\\java_project\\tank\\img\\steel.gif");
	private Image king = Toolkit.getDefaultToolkit().getImage("F:\\java_project\\tank\\img\\destory1.gif");
	
	
	private Image gass= Toolkit.getDefaultToolkit().getImage("F:\\java_project\\tank\\img\\grass.png");
	
	private static Tank P1 =new Tank(6, 24,1);
	private static Tank P2 =new Tank(24, 24,2);
	
	public static int p1num = 0;
	public static int p2num = 0;
	
	private Timer timer1;
	private Timer timer2;
	
	
	public static int[][] map = {{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 
			 					 { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 
								 { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1}, 
								 { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1}, 
								 { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1}, 
								 { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1}, 
								 { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1}, 
								 { 1, 1, 1, 1, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1}, 
								 { 1, 1, 1, 1, 2, 2, 2, 1, 1, 1, 1, 1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1}, 
								 { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1}, 
								 { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1}, 
								 { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1}, 
								 { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1, 0,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1}, 
								 { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1}, 
								 { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1}, 
								 { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1}, 
								 { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1, 0,-1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1}, 
								 { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 0, 1, 1}, 
								 { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 2, 2, 0, 1, 1}, 
								 { 1, 1, 1, 1, 1, 1, 0, 0, 2, 2, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1}, 
								 { 1, 1, 1, 1, 1, 1, 0, 0, 2, 2, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1}, 
								 { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1}, 
								 { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1}, 
								 { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1}, 
								 { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1}, 
								 { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1}, 
								 { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 8, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1}, 
								 { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1}, 
								 { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 
								 { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
	
	public static ArrayList<Point> gassList = new ArrayList<Point>();
	public static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	public static ArrayList<Tank> enemyTanks = new ArrayList<Tank>();
	
 	
	public Gametable() {
		enemyTanks.add(new Tank(rand.nextInt(15)+6, rand.nextInt(15)+6, 3));
		timer1 = new Timer(200, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i =0;i<bullets.size();i++) {
					bullets.get(i).move();
					if(bullets.get(i).getX() >= map.length || bullets.get(i).getY() >= map.length ||bullets.get(i).getX() < 0 || bullets.get(i).getY() < 0) {
						bullets.remove(i);
					}
					else {
						boolean s = true;
						for(int j = 0;j < enemyTanks.size();j++) {
							if(bullets.get(i).getX() <= enemyTanks.get(j).getX() + 1 &&bullets.get(i).getX() >= enemyTanks.get(j).getX() && bullets.get(i).getY() <= enemyTanks.get(j).getY() + 1 &&bullets.get(i).getY() >= enemyTanks.get(j).getY()) {
								enemyTanks.get(j).setAutoCode(7);
								bullets.remove(i);
								s =false;
								break;
							}
						}
						if(s) {
							switch (map[bullets.get(i).getY()][bullets.get(i).getX()]) {
						case 1:
							map[bullets.get(i).getY()][bullets.get(i).getX()] = 0;
							bullets.remove(i);
							break;
						case 2:
							bullets.remove(i);
							break;
						default:
							break;
					}
						}
						

					}
					
				}
				if(rand.nextInt(5) == 1) {
					 for(int i =0 ;i<enemyTanks.size();i++) {
						 	if(enemyTanks.get(i).state) {
						 		enemyTanks.get(i).setAutoCode(rand.nextInt(4)); 
						 	}
						}                                                          
				}
				repaint();
			}			
		});
		timer1.start();
		timer2 = new Timer(500, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(enemyTanks.size()<4) {
					enemyTanks.add(new Tank(rand.nextInt(15)+6, rand.nextInt(15)+6, 3));
				}
				 for(int i =0 ;i<enemyTanks.size();i++) {
					 	int x_t = enemyTanks.get(i).getX();
					 	int y_t = enemyTanks.get(i).getY();
						if(enemyTanks.get(i).autoMove()) {
							enemyTanks.remove(i);
							continue;
						}
						if(map[enemyTanks.get(i).getY()][enemyTanks.get(i).getX()] >=1 || map[enemyTanks.get(i).getY()+1][enemyTanks.get(i).getX()+1] >=1 || map[enemyTanks.get(i).getY()][enemyTanks.get(i).getX()+1] >=1 ||map[enemyTanks.get(i).getY()+1][enemyTanks.get(i).getX()] >=1) {
							enemyTanks.get(i).setX(x_t);enemyTanks.get(i).setY(y_t);
						}
					}                                                          
				repaint();
			}			
		});
		timer2.start();
		
		for(int i = 0;i<map.length;i++) {
			for(int j = 0;j<map[i].length;j++) {
				if(map[j][i] == -1) {
					gassList.add(new Point(j,i));
				}
			}
		}
		
		
		this.setBounds(0, 0, TABLEHEIGHT, TABLEHEIGHT);
		this.setBackground(Color.black);
		this.addKeyListener(new KeyAdapter()  {
			public void keyPressed(KeyEvent e) {
				Point P1_ = new Point(P1.getX(),P1.getY());
				Point P2_ = new Point(P2.getX(),P2.getY());
				switch (e.getKeyCode()) {
				// p1事件
				case KeyEvent.VK_UP:
					P1.changeAndMove(0);
					break;
				case KeyEvent.VK_DOWN:
					P1.changeAndMove(1);
					break;
				case KeyEvent.VK_LEFT:
					P1.changeAndMove(2);
					break;
				case KeyEvent.VK_RIGHT:
					P1.changeAndMove(3);
					break;
				// p2事件
				case KeyEvent.VK_W:
					P2.changeAndMove(0);
					break;
				case KeyEvent.VK_S:
					P2.changeAndMove(1);
					break;
				case KeyEvent.VK_A:
					P2.changeAndMove(2);
					break;
				case KeyEvent.VK_D:
					P2.changeAndMove(3);
					break;
				case KeyEvent.VK_SPACE:
					if(bullets.size() <=3) {
						bullets.add(new Bullet(P1.getX(), P1.getY(),P1.getSTATE()));
					}
					
					break;
				default:
					break;
				}
				if(map[P1.getY()][P1.getX()] >=1 || map[P1.getY()+1][P1.getX()+1] >=1 || map[P1.getY()][P1.getX()+1] >=1 ||map[P1.getY()+1][P1.getX()] >=1) {
					P1.setX(P1_.x);P1.setY(P1_.y); return;
				}
				if(map[P2.getY()][P2.getX()] >=1 || map[P2.getY()+1][P2.getX()+1] >=1 || map[P2.getY()][P2.getX()+1] >=1 ||map[P2.getY()+1][P2.getX()] >=1) {
					P2.setX(P2_.x);P2.setY(P2_.y); return;
				}
				repaint();
				
			}
		});
	}
	private Image ib;
	private Graphics gb;
	
	public void update(Graphics g) {
		if(ib == null) {
			ib = createImage(this.getSize().width, this.getSize().height);
			gb=ib.getGraphics();
		}
		gb.setColor(getBackground());
		gb.fillRect(0, 0, this.getSize().width, this.getSize().height);
		paint(gb);
		g.drawImage(ib, 0, 0, this);
	}
	
	public void paint(Graphics g) {
		if(bullets.size() != 0) {
			for(int i =0;i<bullets.size();i++) {
				bullets.get(i).paint(g, this);;
			}
		}
		
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[i].length;j++) {
				switch (map[i][j] ) {
				case 1:
					g.drawImage(wall,j*30, i*30,j*30+30, i*30+30, 0, 0, 30, 30, this);
					break;
				case 2:
					g.drawImage(stone,j*30, i*30,j*30+30, i*30+30, 0, 0, 30, 30, this);
					break;
				case 8:
					g.drawImage(king,j*30, i*30,j*30+60, i*30+60, 0, 0, 60, 60, this);
					break;
				default:
					break;
				}
				
				
			}
		}
		if(enemyTanks != null) {
			for(int i=0;i<enemyTanks.size();i++) {
				enemyTanks.get(i).paint(g, this);
			}
		}
		P1.paint(g, this);
		P2.paint(g, this);
		for(int i =0;i<gassList.size();i++) {
			g.drawImage(gass,gassList.get(i).y*30, gassList.get(i).x*30,gassList.get(i).y*30+60, gassList.get(i).x*30+60, 0, 0, 60, 60, this);
		}
	
	}
}
