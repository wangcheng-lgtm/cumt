import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

public class Tank {
	private int x;
	private int y;
	private int statenum = 0;
	private int modcode;
	private int autocode;
	public boolean state = true;
	private ArrayList<Image> thistank = new ArrayList<Image>();
	private ArrayList<ArrayList<Image>> tanks = new ArrayList<ArrayList<Image>>();
	
	private static final int[][] v = {{0,-1},{0,1},{-1,0},{1,0}};

// ************ 导入图片 ***********
	// ***** p1tank ****
	private Image P1tankU = Toolkit.getDefaultToolkit().getImage("F:\\java_project\\tank\\img\\p1tankU.gif");
	private Image P1tankD= Toolkit.getDefaultToolkit().getImage("F:\\java_project\\tank\\img\\p1tankD.gif");
	private Image P1tankR= Toolkit.getDefaultToolkit().getImage("F:\\java_project\\tank\\img\\p1tankR.gif");
	private Image P1tankL= Toolkit.getDefaultToolkit().getImage("F:\\java_project\\tank\\img\\p1tankL.gif");
	private ArrayList<Image> p1tank = new ArrayList<Image>();
	// ***** p2tank ****
	private Image P2tankU = Toolkit.getDefaultToolkit().getImage("F:\\java_project\\tank\\img\\p2tankU.gif");
	private Image P2tankD= Toolkit.getDefaultToolkit().getImage("F:\\java_project\\tank\\img\\p2tankD.gif");
	private Image P2tankR= Toolkit.getDefaultToolkit().getImage("F:\\java_project\\tank\\img\\p2tankR.gif");
	private Image P2tankL= Toolkit.getDefaultToolkit().getImage("F:\\java_project\\tank\\img\\p2tankL.gif");
	private ArrayList<Image> p2tank = new ArrayList<Image>();
	// **** 敌方坦克1 ****
	private Image E3tankU = Toolkit.getDefaultToolkit().getImage("F:\\java_project\\tank\\img\\enemy1U.gif");
	private Image E3tankD= Toolkit.getDefaultToolkit().getImage("F:\\java_project\\tank\\img\\enemy1D.gif");
	private Image E3tankL= Toolkit.getDefaultToolkit().getImage("F:\\java_project\\tank\\img\\enemy1L.gif");
	private Image E3tankR= Toolkit.getDefaultToolkit().getImage("F:\\java_project\\tank\\img\\enemy1R.gif");
	private ArrayList<Image> E3tank = new ArrayList<Image>();
	// **** 敌方坦克2 ****
	private Image E4tankU = Toolkit.getDefaultToolkit().getImage("F:\\java_project\\tank\\img\\enemy2U.gif");
	private Image E4tankD= Toolkit.getDefaultToolkit().getImage("F:\\java_project\\tank\\img\\enemy2D.gif");
	private Image E4tankL= Toolkit.getDefaultToolkit().getImage("F:\\java_project\\tank\\img\\enemy2L.gif");
	private Image E4tankR= Toolkit.getDefaultToolkit().getImage("F:\\java_project\\tank\\img\\enemy2R.gif");
	private ArrayList<Image> E4tank = new ArrayList<Image>();
	// **** 爆炸效果 ****
	private Image boom1 =  Toolkit.getDefaultToolkit().getImage("F:\\java_project\\tank\\img\\blast1.gif");
	private Image boom2 =  Toolkit.getDefaultToolkit().getImage("F:\\java_project\\tank\\img\\blast2.gif");
	private Image boom3 =  Toolkit.getDefaultToolkit().getImage("F:\\java_project\\tank\\img\\blast3.gif");
	private Image boom4 =  Toolkit.getDefaultToolkit().getImage("F:\\java_project\\tank\\img\\blast4.gif");
	private Image boom5 =  Toolkit.getDefaultToolkit().getImage("F:\\java_project\\tank\\img\\blast5.gif");
	private Image boom6 =  Toolkit.getDefaultToolkit().getImage("F:\\java_project\\tank\\img\\blast6.gif");
	private Image boom7 =  Toolkit.getDefaultToolkit().getImage("F:\\java_project\\tank\\img\\blast7.gif");
	private Image boom8 =  Toolkit.getDefaultToolkit().getImage("F:\\java_project\\tank\\img\\blast8.gif");
	private ArrayList<Image> booms = new ArrayList<Image>();
	
	
	
	public Tank(int x,int y,int modcode){
		this.modcode = modcode;
		p1tank.add(P1tankU);p1tank.add(P1tankD);p1tank.add(P1tankL);p1tank.add(P1tankR);
		
		p2tank.add(P2tankU);p2tank.add(P2tankD);p2tank.add(P2tankL);p2tank.add(P2tankR);
		
		E3tank.add(E3tankU);E3tank.add(E3tankD);E3tank.add(E3tankL);E3tank.add(E3tankR);
		
		E4tank.add(E4tankU);E4tank.add(E4tankD);E4tank.add(E4tankL);E4tank.add(E4tankR);
		
		tanks.add(null);tanks.add(p1tank);tanks.add(p2tank);tanks.add(E3tank);tanks.add(E4tank);
		
		booms.add(boom1);booms.add(boom2);booms.add(boom3);booms.add(boom4);booms.add(boom5);booms.add(boom6);booms.add(boom7);booms.add(boom8);
		
		thistank  = tanks.get(modcode);
		this.y = y;
		this.x = x;
	}
	
	public void changeAndMove(int statenum) {
		this.statenum = statenum;
		this.x += v[statenum][0];
		this.y += v[statenum][1];
	}
	
	public boolean autoMove() {
		if(state) {
			this.x += v[autocode][0];
			this.y += v[autocode][1];
			return false;
		}
		else if(statenum != 0 ) {
			this.statenum --;
			return false;
		}
		else {
			return true;
		}
	}
	
	public void paint(Graphics g, Gametable t) {
		if(state) {
			g.drawImage(thistank.get(this.statenum),this.x*30, this.y*30,this.x*30+60, this.y*30+60, 0, 0, 60, 60, t);
		}
		else {
			g.drawImage(booms.get(this.statenum),this.x*30, this.y*30,this.x*30+booms.get(this.statenum).getWidth(t), this.y*30+booms.get(this.statenum).getHeight(t), 0, 0, booms.get(this.statenum).getWidth(t), booms.get(this.statenum).getHeight(t), t);
		}
	}
	
	public int getSTATE() {
		return this.statenum;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setAutoCode(int autocode) {
		if(autocode == 7) {
			this.state = false;
		}
		this.statenum = autocode;
		this.autocode = autocode;
	}


}
