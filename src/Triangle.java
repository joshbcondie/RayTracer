public class Triangle extends Surface {

	private Vector3D v1;
	private Vector3D v2;
	private Vector3D v3;
	private Vector3D normal;
	private double d;

	public Triangle(Vector3D v1, Vector3D v2, Vector3D v3) {
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
		normal = v2.subtract(v1).crossProduct(v3.subtract(v2)).normalize();
		d = -(v1.getX() * normal.getX() + v1.getY() * normal.getY() + v1.getZ()
				* normal.getZ());
	}

	@Override
	public boolean intersects(Ray ray) {
		double vd = normal.dotProduct(ray.getDirection());
		if (vd == 0)
			return false;
		if (vd > 0) {
			normal = new Vector3D(-normal.getX(), -normal.getY(),
					-normal.getZ());
			d = -(v1.getX() * normal.getX() + v1.getY() * normal.getY() + v1
					.getZ() * normal.getZ());
			vd = normal.dotProduct(ray.getDirection());
		}
		double v0 = -(normal.dotProduct(ray.getOrigin()) + d);
		double t = v0 / vd;
		if (t < 0)
			return false;
		Vector3D point = new Vector3D(ray.getOrigin().getX()
				+ ray.getDirection().getX() * t, ray.getOrigin().getY()
				+ ray.getDirection().getY() * t, ray.getOrigin().getZ()
				+ ray.getDirection().getZ() * t);
		if (v2.subtract(v1).dotProduct(point.subtract(v1)) >= 0
				&& v3.subtract(v2).dotProduct(point.subtract(v2)) >= 0
				&& v1.subtract(v3).dotProduct(point.subtract(v3)) >= 0)
			return true;
		return false;
	}
}
