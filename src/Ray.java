public class Ray {

	private static int MAX_DEPTH = 3;

	private Vector3D origin;
	private Vector3D direction;
	private double intensity;
	private int depth;
	private Color3D color;

	public Ray() {
		color = Color3D.BLACK;
		depth = 0;
	}

	public void trace(Scene scene) {

		double closestIntersection = -10000;

		for (Surface s : scene.getSurfaces()) {

			if (s.intersects(this)
					&& s.getIntersectionPoint().getZ() > closestIntersection) {

				closestIntersection = s.getIntersectionPoint().getZ();

				if (s.getReflective() != null) {
					if (depth < MAX_DEPTH) {
						Ray reflection = new Ray();
						reflection.setDepth(depth + 1);
						reflection.setDirection(direction.subtract(
								s.getNormal()
										.scale(2 * direction.dotProduct(s
												.getNormal()))).normalize());
						reflection.setOrigin(s.getIntersectionPoint().add(
								reflection.direction.scale(0.01)));
						reflection.setColor(scene.getBackground());
						reflection.trace(scene);
						color = reflection.color.multiply(s.getReflective());
					}
				} else if (s.getRefractive() != null) {
					color = s.getRefractive();
				} else {

					boolean shadow = false;
					Ray shadowRay = new Ray();
					shadowRay.setOrigin(s.getIntersectionPoint().add(
							scene.getDirectionToLight().scale(0.01)));
					shadowRay.setDirection(scene.getDirectionToLight());

					for (Surface s1 : scene.getSurfaces()) {
						if (s1.intersects(shadowRay)) {
							shadow = true;
							color = Color3D.BLACK;
						}
					}

					if (!shadow) {
						Vector3D eye = origin
								.subtract(s.getIntersectionPoint()).normalize();
						Vector3D reflection = s
								.getNormal()
								.scale(2 * s.getNormal().dotProduct(
										scene.getDirectionToLight()))
								.subtract(scene.getDirectionToLight())
								.normalize();
						if (s.getSpecular() == null) {
							s.setSpecular(Color3D.BLACK);
							s.setPhongConstant(1);
						}
						color = s
								.getDiffuse()
								.multiply(scene.getAmbientLight())
								.add(scene
										.getLightColor()
										.multiply(
												s.getDiffuse()
														.scale(Math
																.max(0,
																		s.getNormal()
																				.dotProduct(
																						scene.getDirectionToLight())))
														.add(s.getSpecular()
																.scale(Math
																		.pow(Math
																				.max(0,
																						eye.dotProduct(reflection)),
																				s.getPhongConstant())))));
					}
				}
			}
		}
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

	public Color3D getColor() {
		return color;
	}

	public void setColor(Color3D color) {
		this.color = color;
	}
}
