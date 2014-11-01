public class Ray {

	private Vector3D origin;
	private Vector3D direction;
	private double intensity;
	private int depth;
	// private Color color;

	public Vector3D getOrigin() {
		return origin;
	}

	public void setOrigin(Vector3D origin) {
		this.origin = origin;
	}

	public Vector3D getDirection() {
		return direction;
	}

	public void setDirection(Vector3D direction) {
		this.direction = direction;
	}
}
