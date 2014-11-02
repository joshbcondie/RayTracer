public class Triangle extends Surface {

	private Vector3D v1;
	private Vector3D v2;
	private Vector3D v3;
	private Vector3D normal;

	public Triangle(Vector3D v1, Vector3D v2, Vector3D v3) {
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
		normal = v2.subtract(v1).crossProduct(v3.subtract(v2)).normalize();
	}

	@Override
	public boolean intersects(Ray ray) {
		return false;
	}

}
