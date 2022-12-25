import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import javax.swing.JOptionPane;






public class GobangCanvas extends Canvas {

	private static final long serialVersionUID = 1L;
	public static int[][] matrix = new int[15][15];
	public static final int LAndT = 20;
	public static final int OvalWEIDH = 12;
	public static final int GoWIDTH = 40;
	public static final int BLANK = 0;
	public static final int WHITE = 1;
	public static final int BLACK = 2;
	public static Boolean ISGO = true;
	public static int c = BLACK;
	
	
	private LinkedList<Point> pieces;
	public GobangCanvas(GobangFrame frame) {
		pieces = new LinkedList<Point>();
		this.setBackground(new Color(255,165,0));
		this.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()== MouseEvent.BUTTON1) {
					int x=e.getX(),y=e.getY();
					if ((x<=600-LAndT && x >= LAndT) && (y<=600-LAndT && y >= LAndT) && ISGO) {
						if((x-LAndT) % GoWIDTH >LAndT) {
							x = (x-LAndT)/GoWIDTH + 1;
						}
						else {
							x = (x-LAndT)/GoWIDTH;
						}
						if((y-LAndT) % GoWIDTH >LAndT) {
							y = (y-LAndT)/GoWIDTH + 1;
						}
						else {
							y = (y-LAndT)/GoWIDTH;
						}
						if (matrix[y][x] == BLANK) {
							matrix[y][x] = c;
							
							pieces.add(new Point(x,y));
							c = 3-c;
							repaint();
							if (judge(new Point(x,y))) {
								System.out.println("game over ");
								if(3-c == WHITE) {
									JOptionPane.showMessageDialog(frame, "白棋获胜", "消息提示",JOptionPane.WARNING_MESSAGE);
								}
								else {
									JOptionPane.showMessageDialog(frame, "黑棋获胜", "消息提示",JOptionPane.WARNING_MESSAGE);
								}
								ISGO = false;
								  
							};
						}
						
					}
					System.out.println(x+"\t"+ y);
					
				}
				
			}
		});
	}
	
	public Boolean judge(Point P) {
		int px,py;
		int count;
		int[][] v = {{0,1},{0,-1},{1,0},{-1,0},{1,1},{-1, -1},{-1, 1},{1,-1}};
		for(int i = 0;i < 8;i = i+2) {
			px = P.x;
			py = P.y;
			count = -1;
			while ((0<=py && py<15) &&((0<=px && px<15)) && matrix[py][px] == 3-c){
				count+=1;
				px += v[i][0];
				py += v[i][1];
			}
			px = P.x;
			py = P.y;
			while ((0<=py && py<15) &&((0<=px && px<15)) && matrix[py][px] == 3-c) {
				count ++;
				px += v[i+1][0];
				py += v[i+1][1];
			}
			if(count >= 5) {
				return true;
			}
		}
		return false;
		
	}
	
	private Image ib;
	private Graphics gb;
	public void update(Graphics g) {
		if(ib== null ) {
			ib = this.createImage(this.getWidth(),this.getHeight());
			gb = ib.getGraphics();
		}
		gb.setColor(this.getBackground());
		gb.fillRect(0, 0, ib.getWidth(this), ib.getHeight(this));
		paint(gb);
		g.drawImage(ib,0,0,this);
		super.update(g);
	}
	public void paint(Graphics g) {
		/*super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(2f));*/
		
		for (int i=0;i<=14;i++) {
			g.drawLine(LAndT,GoWIDTH*i+LAndT, 600-LAndT,GoWIDTH*i+LAndT);
			g.drawLine(GoWIDTH*i+LAndT, LAndT, GoWIDTH*i+LAndT, 600-LAndT);
		}
		g.fillOval(3*GoWIDTH+LAndT-OvalWEIDH/2, 3*GoWIDTH+LAndT-OvalWEIDH/2, OvalWEIDH, OvalWEIDH);
		g.fillOval(3*GoWIDTH+LAndT-OvalWEIDH/2, 11*GoWIDTH+LAndT-OvalWEIDH/2, OvalWEIDH, OvalWEIDH);
		g.fillOval(7*GoWIDTH+LAndT-OvalWEIDH/2, 7*GoWIDTH+LAndT-OvalWEIDH/2, OvalWEIDH, OvalWEIDH);
		g.fillOval(11*GoWIDTH+LAndT-OvalWEIDH/2, 3*GoWIDTH+LAndT-OvalWEIDH/2, OvalWEIDH, OvalWEIDH);
		g.fillOval(11*GoWIDTH+LAndT-OvalWEIDH/2, 11*GoWIDTH+LAndT-OvalWEIDH/2, OvalWEIDH, OvalWEIDH);
		
		for(int i = 0;i<pieces.size();i++) {
			Point P = pieces.get(i);
			if(matrix[P.y][P.x] == WHITE) {
				g.setColor(Color.white);
			}
			else if (matrix[P.y][P.x] == BLACK) {
				g.setColor(Color.black);
			}
			g.fillOval((P.x)*GoWIDTH+LAndT-(GoWIDTH-10)/2, (P.y)*GoWIDTH+LAndT-(GoWIDTH-10)/2, GoWIDTH-10, GoWIDTH-10);
		}
		
	}

	public void undo() {
		ISGO = true;
		if(pieces.size() != 0) {
			Point P = pieces.getLast();
		int px = P.x,py=P.y;
		pieces.removeLast();
		matrix[py][px] = BLANK;
		repaint();
		c = 3-c;
		}
		
	}

	public void restart() {
		ISGO = true;
		matrix = new int[15][15];
		pieces.clear();
		c=BLACK;
		repaint();
		
	}
}
