
public class Vector3 {
	double[] vec = new double[3];
	
	public Vector3 (double x, double y, double z) {
		vec[0] = x;
		vec[1] = y;
		vec[2] = z;
	}
	
	public double dot(Vector3 vec2) {
		return vec2.x() * vec[0] + vec2.y() * vec[1] + vec2.z() * vec[2];
	}
	
	public Vector3 cross(Vector3 vec2) {
		return new Vector3(vec[1] * vec2.z() - vec[2] * vec2.y(),
				vec[2] * vec2.x() - vec[0] * vec2.z(),
				vec[0] * vec2.y() - vec[1] * vec2.x());
	}
	
	public double mag() {
		return Math.sqrt(vec[0] * vec[0] + vec[1] * vec[1] + vec[2] * vec[2]);
	}
	
	public double magSquared() {
		return vec[0] * vec[0] + vec[1] * vec[1] + vec[2] * vec[2];
	}
	
	public Vector3 sub(Vector3 vec2) {
		return new Vector3(vec[0] - vec2.x(), vec[1] - vec2.y(), vec[2] - vec2.z());
	}
	
	public Vector3 add(Vector3 vec2) {
		return new Vector3(vec[0] + vec2.x(), vec[1] + vec2.y(), vec[2] + vec2.z());
	}
	
	public Vector3 scale(double scalar) {
		return new Vector3(vec[0] * scalar, vec[1] * scalar, vec[2] * scalar);
	}
	
	public Vector3 unit() {
		return scale(1 / mag());
	}
	
	public void rotateAroundY(double theta) {
		double s = Math.sin(theta);
		double c = Math.cos(theta);
		
		double holdX = vec[0];
		
		vec[0] = vec[0] * c - vec[2] * s;
		vec[2] = s * holdX + c * vec[2];
	}
	
	public void setX(double x) {
		vec[0] = x;
	}
	
	public void setY(double y) {
		vec[1] = y;
	}
	
	public void setZ(double z) {
		vec[2] = z;
	}
	
	public double x() {
		return vec[0];
	}
	
	public double y() {
		return vec[1];
	}
	
	public double z() {
		return vec[2];
	}
	
	public String toString() {
		return "<" + vec[0] + ", " + vec[1] + ", " + vec[2] + ">";
	}
}
