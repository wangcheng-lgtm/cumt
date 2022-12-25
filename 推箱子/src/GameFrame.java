import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	
	public static final int FRAMEWIDTH = 1200;
	public static final int FRAMEHEIGHT = 900;
	private GameCanvas gc;
	
	public GameFrame() {
		this.setLayout(null);
		this.setTitle("ÍÆÏä×Ó");
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds((d.width-FRAMEWIDTH)/2, (d.height - FRAMEHEIGHT)/2,FRAMEWIDTH, FRAMEHEIGHT);
		this.addWindowListener(new WindowAdapter() {	
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}	
		});
		gc = new GameCanvas(this);
		this.add(gc);
	}
}
