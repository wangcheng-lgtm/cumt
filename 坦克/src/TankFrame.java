import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class TankFrame extends JFrame {
	public static final int WIDTH = 915;
	public static final int HEIGHT = 950;
	
	private Gametable gc;
	public TankFrame() {
		this.setLayout(null);
		this.setTitle("Ì¹¿Ë´óÕ½");
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds((d.width - WIDTH)/2, (d.height - HEIGHT)/2, WIDTH, HEIGHT);
		gc = new Gametable();
		this.add(gc);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
				
			}
			
		
		});
	}
}
