import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

import javax.swing.JOptionPane;


public class GameCanvas extends Canvas {
	private Image cbImage0 = Toolkit.getDefaultToolkit().getImage("0.png");
	private Image cbImage1 = Toolkit.getDefaultToolkit().getImage("1.png");
	private Image cbImage2 = Toolkit.getDefaultToolkit().getImage("2.png");
	private Image cbImage3 = Toolkit.getDefaultToolkit().getImage("3.png");
	private Image cbImage5 = Toolkit.getDefaultToolkit().getImage("5.png");
	public static final int CANVASWIDTH = 1200;
	public static final int CANVASHEIGHT = 800;
	public static Point my = new Point(10,5);
	public static int[][] map1={{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
								{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
								{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
								{1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
								{1,0,0,0,0,4,4,4,0,3,0,1,0,0,0},
								{1,0,3,3,3,5,5,5,0,3,0,1,0,0,0},
								{1,0,0,0,0,4,4,4,0,3,0,1,0,0,0},
								{1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
								{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
								{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
	
	public static int[][] map2={{1,1,1,1,1,1,0,0,0,0},
								{1,0,0,0,0,1,0,0,0,0},
								{1,0,0,3,0,1,0,0,0,0},
								{1,0,0,0,0,1,0,0,0,0},
								{1,0,0,3,0,1,1,1,1,1},
								{1,0,0,0,0,0,4,4,4,1},
								{1,0,0,3,1,0,0,0,0,1},
								{1,0,0,0,1,0,0,0,0,1},
								{1,0,0,0,1,0,0,0,0,1},
								{1,1,1,1,1,1,1,1,1,1}};
	
	public static int[][] map3={{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
								{1,0,0,0,1,0,0,0,0,0,0,0,0,0,1},
								{1,0,0,3,1,0,0,0,0,0,0,0,0,0,1},
								{1,0,0,0,1,0,0,4,0,0,1,1,1,1,1},
								{1,0,0,3,1,0,0,0,0,0,3,0,0,0,1},
								{1,0,0,0,0,0,4,1,0,0,0,0,0,0,1},
								{1,0,0,3,1,0,0,1,0,0,1,1,1,0,1},
								{1,0,0,0,1,0,0,1,0,0,0,0,0,0,1},
								{1,0,0,0,1,0,4,1,0,0,1,0,0,4,1},
								{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
	public static LinkedList<int[][]> M = new LinkedList<int[][]>();
	public static int[][] map;

	public GameCanvas(GameFrame frame) {
		M.add(map3);
		M.add(map2);
		M.add(map1);
		map = M.getLast();
		System.out.println(map.length);
		M.removeLast();
		//this.setBackground(Color.green);
		this.setBounds(0, 60, CANVASWIDTH, CANVASHEIGHT);
		this.addKeyListener(new KeyAdapter() {
			
	
			public void keyPressed(KeyEvent e) {
				int x = my.x,y=my.y;
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					my.y -=1; 
					break;
				case KeyEvent.VK_DOWN:
					my.y +=1;
					break;
				case KeyEvent.VK_RIGHT:
					my.x += 1;
					break;
				case KeyEvent.VK_LEFT:
					my.x -= 1;
					break;
				default:
					break;
				}
				switch (map[my.y][my.x]) {
				case 1:
					my.x = x;
					my.y = y;
					break;
				case 3:
					Point t = new Point(my.x,my.y);
					int bx = t.x,by = t.y;
					t.x+= t.x - x;
					t.y += t.y - y;
					if(map[t.y][t.x] == 1 || map[t.y][t.x] == 3|| map[t.y][t.x] == 5) {
						t.x =bx;
						t.y =by;
						my.x = x;
						my.y = y;
					}
					else if(map[t.y][t.x] == 4){
						map[by][bx] = 0;
						map[t.y][t.x]=5;
					}
					else {
						map[by][bx] = 0;
						map[t.y][t.x]=3;
					}
					break;
				case 5:
					Point r = new Point(my.x,my.y);
					int rx = r.x,ry = r.y;
					r.x+= r.x - x;
					r.y += r.y - y;
					if(map[r.y][r.x] == 1 || map[r.y][r.x] == 3|| map[r.y][r.x] == 5) {
						r.x =rx;
						r.y =ry;
						my.x = x;
						my.y = y;
					}
					else if(map[r.y][r.x] == 0){
						map[ry][rx] = 4;
						map[r.y][r.x]=3;
					}
					else {
						map[ry][rx] = 4;
						map[r.y][r.x]=5;
					}
					break;
				default:
					break;
				}
				
				repaint();
				if(judge()) {
					JOptionPane.showMessageDialog(frame, "Win!", "消息提示",JOptionPane.WARNING_MESSAGE);
					map = M.getLast();
					M.removeLast();
					repaint();
				}
				
				
			}
		});
	}
	public void paint(Graphics g) {
		
		
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(3f));
		g.drawImage(cbImage0,0, 0,CANVASWIDTH, CANVASHEIGHT, 0, 0, cbImage0.getWidth(this), cbImage0.getHeight(this), this);
		for(int i = 0;i<map.length;i++) {
			for(int j = 0;j<map[i].length;j++) {
				g.setColor(Color.gray);
				switch (map[i][j]) {
				case -1:
					g.setColor(Color.black);
					g.drawRect(j*80, i*80, 80, 80);
					break;
				case 1:
					/*g.fillRect(j*80, i*80, 80, 80);;
					g.setColor(Color.black);
					g.drawRect(j*80, i*80, 80, 80);*/
					g.drawImage(cbImage1,j*80, i*80,j*80+80, i*80+80, 0, 0, 80, 80, this);
					break;
				/*case 2:
					g.setColor(new Color(205,90,12));
					g.fillRect(j*80, i*80, 80, 80);
					break;*/
				case 3:
						/*g.setColor(new Color(0,0,0));
						g.fillRect(j*80, i*80, 80, 80);*/
					g.drawImage(cbImage2,j*80, i*80,j*80+80, i*80+80, 0, 0, 80, 80, this);
					break;
				case 4:
					g.setColor(Color.red);
					g.fillOval(j*80+20, i*80+20, 40, 40);
					break;
				case 5:
					/*g.setColor(Color.red);
					g.fillRect(j*80, i*80, 80, 80);*/
					g.drawImage(cbImage5,j*80, i*80,j*80+80, i*80+80, 0, 0, 80, 80, this);

				default:
					break;
				}
				
				
				
			}
		}
		/*g.setColor(Color.blue);
		g.fillRect(my.x*80,my.y*80, 80, 80);*/
		g.drawImage(cbImage3,my.x*80, my.y*80,my.x*80+80, my.y*80+80, 0, 0, 80, 80, this);
		
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
	
	public boolean judge() {
		for(int i = 0;i<map.length;i++) {
			for(int j = 0;j<map[i].length;j++) {
				if(map[i][j] == 4) {
					return false;
				}
			}
			
		}
		return true;
	}

}
