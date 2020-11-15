import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Runner {
	
	JFrame jf;
	DrawPanel dp;
	
	public int w, h;
	
	RayTracer rt = new RayTracer(2, 2, 0.01, 10, new LightSource(new Vector3(0, 0, 0)
			));
	
	public static void main(String[] args) {
		new Runner().run();
	}
	
	private void run() {
		jf = new JFrame("TEST");
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		
		dp = new DrawPanel();
		jf.getContentPane().add(BorderLayout.CENTER, dp);
		
		jf.setSize(400, 420);
		jf.setLocation(375, 50);
		jf.setVisible(true);
		jf.setResizable(false);
		jf.addKeyListener(new KeyListen());
		
		w = jf.getWidth();
		h = jf.getHeight() - 20;
		
		while (true) {
			jf.repaint();
			rt.update();
			try {
				Thread.sleep(30);
			} catch (Exception exc) {}
		}
	}
	
	public class DrawPanel extends JPanel {
		public void paintComponent(Graphics g) {
			rt.castRays(g, 400, 400);
		}
	}
	
	public class KeyListen extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			rt.keyListen(e);
		}

		public void keyReleased(KeyEvent e) {
			rt.keyRelease(e);
		}
	}
	

}
