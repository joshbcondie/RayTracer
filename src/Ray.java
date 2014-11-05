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
				color = Color3D.BLACK;

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
						color = color.add(reflection.color.multiply(s
								.getReflective()));
					}
				}
				if (s.getRefractionIndex() != 0) {
					Ray refraction = new Ray();
					double quotient = 1.003 / s.getRefractionIndex();
					Vector3D normal = s.getNormal();
					if (s.getNormal().dotProduct(direction) > 0) {
						normal = normal.scale(-1);
						quotient = 1 / quotient;
					}
					double c1 = -normal.dotProduct(direction);
					double c2 = Math.sqrt(1 - quotient * quotient
							* (1 - c1 * c1));
					refraction.setDirection(direction.scale(quotient).add(
							normal.scale(quotient * c1 - c2)));
					refraction.setOrigin(s.getIntersectionPoint().add(
							refraction.direction.scale(0.01)));
					refraction.setColor(scene.getBackground());
					refraction.trace(scene);
					if (s.getRefractive() == null) {
						s.setRefractive(Color3D.WHITE);
					}
					color = color.add(refraction.color.multiply(s
							.getRefractive()));
				}
				if (s.getDiffuse() != null) {

					boolean shadow = false;
					Ray shadowRay = new Ray();
					shadowRay.setOrigin(s.getIntersectionPoint().add(
							scene.getDirectionToLight().scale(0.01)));
					shadowRay.setDirection(scene.getDirectionToLight());

					for (Surface s1 : scene.getSurfaces()) {
						if (s1.intersects(shadowRay)
								&& s1.getRefractionIndex() == 0) {
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
						color = color
								.add(s.getDiffuse()
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
																						s.getPhongConstant()))))));
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
