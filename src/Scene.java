import java.util.ArrayList;
import java.util.List;

public class Scene {

	private Vector3D directionToLight;
	private Color3D lightColor;
	private Color3D ambientLight;
	private Color3D background;
	private List<Surface> surfaces;

	public Scene() {
		surfaces = new ArrayList<>();
	}

	public Vector3D getDirectionToLight() {
		return directionToLight;
	}

	public void setDirectionToLight(Vector3D directionToLight) {
		this.directionToLight = directionToLight;
	}

	public Color3D getLightColor() {
		return lightColor;
	}

	public void setLightColor(Color3D lightColor) {
		this.lightColor = lightColor;
	}

	public Color3D getAmbientLight() {
		return ambientLight;
	}

	public void setAmbientLight(Color3D ambientLight) {
		this.ambientLight = ambientLight;
	}

	public Color3D getBackground() {
		return background;
	}

	public void setBackground(Color3D background) {
		this.background = background;
	}

	public void addSurface(Surface surface) {
		surfaces.add(surface);
	}

	public List<Surface> getSurfaces() {
		return surfaces;
	}
}
