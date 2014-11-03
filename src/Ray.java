public class Ray {

	private Vector3D origin;
	private Vector3D direction;
	private double intensity;
	private int depth;
	private Color3D color;

	public Ray() {
		color = Color3D.BLACK;
	}

	public void trace(Scene scene) {
		for (Surface s : scene.getSurfaces()) {

			if (s.intersects(this)) {

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
					if (s.getReflective() != null) {
						color = s.getReflective();
					} else if (s.getRefractive() != null) {
						color = s.getRefractive();
					} else {
						color = s.getDiffuse();
					}
				}
				break;
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
