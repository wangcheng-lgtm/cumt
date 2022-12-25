import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;




public class GobangFrame extends JFrame {
	public static final int FRAMEWIDTH = 635;
	public static final int FRAMEHEIGHT = 700;
	public static final int GAMEWIDTH = 600;
	
	private GobangCanvas gc;
	
	public GobangFrame() {
		this.setLayout(null);
		this.setTitle("五子棋");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("2.png"));
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
				
			}
			
		});
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds((d.width-FRAMEWIDTH)/2, (d.height-FRAMEHEIGHT)/2, FRAMEWIDTH, FRAMEHEIGHT);
		gc = new GobangCanvas(this);
		gc.setBounds(10,50,GAMEWIDTH,GAMEWIDTH);
		this.add(gc);
		JButton btundo = new JButton("悔棋");
		btundo.setBounds(500, 10, 100, 25);
		btundo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				gc.undo();
				
			}
		});
		JButton restartbuttonButton = new JButton("重新开始");
		restartbuttonButton.setBounds(20, 10, 100, 25);
		restartbuttonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gc.restart();
			}
		});
		this.add(btundo);
		this.add(restartbuttonButton);
		this.setResizable(false);
	}
}
