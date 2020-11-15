import java.awt.Color;

public class Triangle {
	Vector3 p1, p2, p3;
	
	Vector3 side12, side13;
	
	Vector3 normal;
	
	double planeConst;
	
	Color color;
	
	static final double precision = 0.01;
	
	public Triangle(Vector3 p1, Vector3 p2, Vector3 p3, Color color) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		
		this.color = color;
		
		side12 = p2.sub(p1);
		side13 = p3.sub(p1);
		
		normal = side12.cross(side13);
		
		planeConst = normal.dot(p1);
	}
	
	public boolean pointIn(Vector3 point) {
		//sum areas from point to each vertex
		//should add to total area
		double area = normal.mag() / 2;
		
		
		Vector3 v1 = point.sub(p1);
		Vector3 v2 = point.sub(p2);
		Vector3 v3 = point.sub(p3);
		
		double area12 = v1.cross(v2).mag() / 2;
		double area23 = v2.cross(v3).mag() / 2;
		double area31 = v3.cross(v1).mag() / 2;
		
		return (Math.abs(area12 + area23 + area31 - area) < precision);
		
	}
	
	public void rotateAroundY(double theta) {
		p1.rotateAroundY(theta);
		p2.rotateAroundY(theta);
		p3.rotateAroundY(theta);
		
		side12 = p2.sub(p1);
		side13 = p3.sub(p1);
		
		normal = side12.cross(side13);
		
		planeConst = normal.dot(p1);
	}
	
	public void translate(Vector3 trans) {
		p1 = p1.add(trans);
		p2 = p2.add(trans);
		p3 = p3.add(trans);
		
		planeConst = normal.dot(p1);
	}
}
