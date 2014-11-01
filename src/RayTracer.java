public class RayTracer {

	private static final int SIZE = 500;
	private static final int FOV = 55;

	public static void main(String[] args) {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				Ray ray = new Ray();
				ray.setOrigin(new Vector3D(0, 0, 0));
				ray.setDirection(new Vector3D(0, 0, 0));
			}
		}
	}
}
