public class Vector3D {

	private double x;
	private double y;
	private double z;

	public Vector3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3D normalize() {
		double length = Math.sqrt(x * x + y * y + z * z);
		return new Vector3D(x / length, y / length, z / length);
	}

	public Vector3D add(Vector3D vector) {
		return new Vector3D(x + vector.x, y + vector.y, z + vector.z);
	}

	public Vector3D subtract(Vector3D vector) {
		return new Vector3D(x - vector.x, y - vector.y, z - vector.z);
	}

	public Vector3D scale(double scale) {
		return new Vector3D(x * scale, y * scale, z * scale);
	}

	public double dotProduct(Vector3D vector) {
		return x * vector.x + y * vector.y + z * vector.z;
	}

	public Vector3D crossProduct(Vector3D vector) {
		return new Vector3D(y * vector.z - z * vector.y, z * vector.x - x
				* vector.z, x * vector.y - y * vector.x);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}
}
