public abstract class Surface {

	private Color3D diffuse;
	private Color3D specular;
	private int phongConstant;
	private Color3D reflective;
	private double refractionIndex;
	private Color3D refractive;

	public abstract boolean intersects(Ray ray);

	public abstract Vector3D getIntersectionPoint();

	public abstract Vector3D getNormal();

	public Color3D getDiffuse() {
		return diffuse;
	}

	public Surface setDiffuse(Color3D diffuse) {
		this.diffuse = diffuse;
		return this;
	}

	public Color3D getSpecular() {
		return specular;
	}

	public Surface setSpecular(Color3D specular) {
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

	public Color3D getReflective() {
		return reflective;
	}

	public Surface setReflective(Color3D reflective) {
		this.reflective = reflective;
		return this;
	}

	public double getRefractionIndex() {
		return refractionIndex;
	}

	public Surface setRefractionIndex(double refractionIndex) {
		this.refractionIndex = refractionIndex;
		return this;
	}

	public Color3D getRefractive() {
		return refractive;
	}

	public Surface setRefractive(Color3D refractive) {
		this.refractive = refractive;
		return this;
	}
}
