public class Sphere extends Surface {

	private Vector3D center;
	private double radius;

	public Sphere(Vector3D center, double radius) {
		this.center = center;
		this.radius = radius;
	}

	@Override
	public boolean intersects(Ray ray) {
		return false;
	}
}
