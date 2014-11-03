import java.awt.Color;

public abstract class Surface {

	private Color diffuse;
	private Color specular;
	private int phongConstant;
	private Color reflective;
	private Color refractive;

	public abstract boolean intersects(Ray ray);

	public abstract Vector3D getIntersectionPoint();

	public abstract Vector3D getNormal();

	public Color getDiffuse() {
		return diffuse;
	}

	public Surface setDiffuse(Color diffuse) {
		this.diffuse = diffuse;
		return this;
	}

	public Color getSpecular() {
		return specular;
	}

	public Surface setSpecular(Color specular) {
		this.specular = specular;
		return this;
	}

	public int getPhongConstant() {
		return phongConstant;
	}

	public Surface setPhongConstant(int phongConstant) {
		this.phongConstant = phongConstant;
		return this;
	}

	public Color getReflective() {
		return reflective;
	}

	public Surface setReflective(Color reflective) {
		this.reflective = reflective;
		return this;
	}

	public Color getRefractive() {
		return refractive;
	}

	public Surface setRefractive(Color refractive) {
		this.refractive = refractive;
		return this;
	}
}
