public class Triangle extends Surface {

	private Vector3D v1;
	private Vector3D v2;
	private Vector3D v3;
	private Vector3D intersectionPoint;
	private Vector3D normal;

	public Triangle(Vector3D v1, Vector3D v2, Vector3D v3) {
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
		normal = v2.subtract(v1).crossProduct(v3.subtract(v2)).normalize();
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
		double a = v1.getX() - v2.getX();
		double b = v1.getY() - v2.getY();
		double c = v1.getZ() - v2.getZ();
		double d = v1.getX() - v3.getX();
		double e = v1.getY() - v3.getY();
		double f = v1.getZ() - v3.getZ();
		double g = ray.getDirection().getX();
		double h = ray.getDirection().getY();
		double i = ray.getDirection().getZ();
		double j = v1.getX() - ray.getOrigin().getX();
		double k = v1.getY() - ray.getOrigin().getY();
		double l = v1.getZ() - ray.getOrigin().getZ();

		double M = a * (e * i - h * f) + b * (g * f - d * i) + c
				* (d * h - e * g);
		double t = -(f * (a * k - j * b) + e * (j * c - a * l) + d
				* (b * l - k * c))
				/ M;
		if (t < 0) {
			return false;
		}

		double gamma = (i * (a * k - j * b) + h * (j * c - a * l) + g
				* (b * l - k * c))
				/ M;
		if (gamma < 0 || gamma > 1) {
			return false;
		}

		double beta = (j * (e * i - h * f) + k * (g * f - d * i) + l
				* (d * h - e * g))
				/ M;
		if (beta < 0 || beta > 1 - gamma) {
			return false;
		}

		intersectionPoint = new Vector3D(ray.getOrigin().getX() + t
				* ray.getDirection().getX(), ray.getOrigin().getY() + t
				* ray.getDirection().getY(), ray.getOrigin().getZ() + t
				* ray.getDirection().getZ());

		if (normal.dotProduct(ray.getDirection()) > 0) {
			normal = new Vector3D(-normal.getX(), -normal.getY(),
					-normal.getZ());
		}

		return true;
	}
}
