
public class LightSource {
	Vector3 position;
	
	public LightSource(Vector3 position) {
		this.position = position;
	}
	
	public double multiplier(Vector3 point, Vector3 normal) {
		double mult = position.sub(point).dot(normal);
		mult /= normal.mag();
		mult /= position.sub(point).mag();
		return Math.abs(mult);
	}
	
	
	public void rotateAroundY(double theta) {
		position.rotateAroundY(theta);
	}
	
	public void translate(Vector3 trans) {
		position = position.add(trans);
	}
}
