import java.awt.*;


public class Bullet {
	private static final Image bullet  = Toolkit.getDefaultToolkit().getImage("F:\\java_project\\tank\\img\\tankmissile.gif");
	private int x,y,mvnum;
	private static int[][] v = {{0,-1},{0,1},{-1,0},{1,0}};
	
	public Bullet(int x_,int y_,int n) {
		x = x_;
		y = y_;
		mvnum = n;
}
	public void paint(Graphics g,Gametable t) {
		g.drawImage(bullet, x*30+21, y*30+21, x*30+38, y*30+38, 0, 0, 17, 17, t);
	}
	public void move() {
		x += v[mvnum][0];
		y += v[mvnum][1];
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}
