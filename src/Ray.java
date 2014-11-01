import java.awt.Color;

public class Ray {

	private Vector3D origin;
	private Vector3D direction;
	private double intensity;
	private int depth;
	private Color color;

	public void trace(Scene scene) {

	}

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

	public double getIntensity() {
		return intensity;
	}

	public void setIntensity(double intensity) {
		this.intensity = intensity;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
