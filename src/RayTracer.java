import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class RayTracer {
	ArrayList<Triangle> tris;
	
	boolean[] keys = {false, false, false, false};//right, left, up, down
	
	ArrayList<Integer>[][][] grid;
	
	double w, h, res;
	
	double numBoxes, boxWidth;
	
	LightSource light;
	
	public RayTracer(double width, double height, double resolution, double numBoxes, LightSource light) {
		tris = new ArrayList<Triangle>();
		
		for (int i = 0; i < 1; i++) {
			tris.add(new Triangle(new Vector3(-2, -1, 3 + 3*i), new Vector3(1, -1, 3 + 3*i), new Vector3(-1, 2, 3 + 3*i), Color.BLUE));
			tris.add(new Triangle(new Vector3(-2, -1, 3 + 3*i), new Vector3(1, -1, 1 + 3*i), new Vector3(-1, 2, 3 + 3*i), Color.YELLOW));
			tris.add(new Triangle(new Vector3(1, -1, 1 + 3*i), new Vector3(1, -1, 3 + 3*i), new Vector3(-1, 2, 3 + 3*i), Color.GREEN));
		}
		
		w = width;
		h = height;
		res = resolution;
		
		this.numBoxes = numBoxes;
		
		this.light = light;
		
		//grid = new ArrayList<Integer>[numBoxes][numBoxes][numBoxes];
	}
	
	public void castRays(Graphics g, int screenW, int screenH) {
		Vector3 slope = new Vector3(0, 0, 1);
		double t = 0;
		
		double[] dists = new double[tris.size()];
		double lowest;
		int lowestI;
		
		for (double x = -w / 2; x < w / 2; x += res) {
			for (double y = -h / 2; y < h / 2; y += res) {
				slope.setX(x);
				slope.setY(y);
				
				g.setColor(Color.RED);
				int i = 0;
				for(Triangle tri: tris) {
					t = tri.planeConst / tri.normal.dot(slope);
					
					//System.out.println(x + " : " + y + " : " + slope.scale(t));
					
					if (tri.pointIn(slope.scale(t)) && t >= 0) {
						dists[i] = t;
					} else {
						dists[i] = -1;
					}
					i++;
				}

				lowest = Integer.MAX_VALUE;
				lowestI = 0;
				for (int j = 0; j < dists.length; j++) {
					if (dists[j] > 0 && dists[j] < lowest) {
						lowest = dists[j];
						lowestI = j;
					}
				}
				
				if (dists[lowestI] > 0) {
					lowest = light.multiplier(slope.scale(lowest), tris.get(lowestI).normal);
					lowest = 1 - (1 - lowest) * (1 - lowest);
					Color c = tris.get(lowestI).color;
					g.setColor(new Color((int) (c.getRed() * lowest), (int) (c.getGreen() * lowest), (int) (c.getBlue() * lowest)));
				} else
					g.setColor(Color.BLACK);
				
				g.fillRect((int) (screenW / 2 + x * screenW / w), (int) (screenH / 2 - y * screenH / h),
						(int) (res * screenW / w), (int) (res * screenH / h));
				
			}
		}
	}
	
	public void update() {
		if (keys[0]) {
			for (Triangle tri: tris)
				tri.rotateAroundY(0.1);
			
			//light.rotateAroundY(0.1);
		} else if (keys[1]) {
			for (Triangle tri: tris)
				tri.rotateAroundY(-0.1);
			
			//light.rotateAroundY(-0.1);
		}
		if (keys[2]) {
			for (Triangle tri: tris)
				tri.translate(new Vector3(0, 0, -0.1));
			
			//light.translate(new Vector3(0, 0, -0.1));
		} else if (keys[3]) {
			for (Triangle tri: tris)
				tri.translate(new Vector3(0, 0, 0.1));
			
			//light.translate(new Vector3(0, 0, 0.1));
		}
	}
	
	public void keyListen(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			keys[0] = true;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			keys[1] = true;
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			keys[2] = true;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			keys[3] = true;
		}
	}
	
	public void keyRelease(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			keys[0] = false;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			keys[1] = false;
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			keys[2] = false;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			keys[3] = false;
		}
	}
}
