public class Sphere extends Surface {

	private Vector3D center;
	private double radius;
	private Vector3D intersectionPoint;
	private Vector3D normal;

	public Sphere(Vector3D center, double radius) {
		this.center = center;
		this.radius = radius;
	}

	@Override
	public Vector3D getIntersectionPoint() {
		return intersectionPoint;
	}

	@Override
	public Vector3D getNormal() {
		return normal;
	}

	@Override
	public boolean intersects(Ray ray) {
		double b = 2 * (ray.getDirection().getX() * ray.getOrigin().getX()
				- ray.getDirection().getX() * center.getX()
				+ ray.getDirection().getY() * ray.getOrigin().getY()
				- ray.getDirection().getY() * center.getY()
				+ ray.getDirection().getZ() * ray.getOrigin().getZ() - ray
				.getDirection().getZ() * center.getZ());
		double c = ray.getOrigin().getX() * ray.getOrigin().getX() - 2
				* ray.getOrigin().getX() * center.getX() + center.getX()
				* center.getX() + ray.getOrigin().getY()
				* ray.getOrigin().getY() - 2 * ray.getOrigin().getY()
				* center.getY() + center.getY() * center.getY()
				+ ray.getOrigin().getZ() * ray.getOrigin().getZ() - 2
				* ray.getOrigin().getZ() * center.getZ() + center.getZ()
				* center.getZ() - radius * radius;
		double d = b * b - 4 * c;
		if (d < 0)
			return false;
		double t = 0;
		double t0 = (-b - Math.sqrt(d)) / 2;
		double t1 = 0;
		if (t0 <= 0) {
			t1 = (-b + Math.sqrt(d)) / 2;
			if (t1 <= 0)
				return false;
			t = t1;
		} else {
			t = t1;
		}

		intersectionPoint = ray.getOrigin().add(ray.getDirection().scale(t));
		normal = intersectionPoint.subtract(center).normalize();

		return true;
	}
}
