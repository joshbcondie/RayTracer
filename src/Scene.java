import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Scene {

	private Vector3D directionToLight;
	private Color lightColor;
	private Color ambientLight;
	private Color background;
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

	public Color getLightColor() {
		return lightColor;
	}

	public void setLightColor(Color lightColor) {
		this.lightColor = lightColor;
	}

	public Color getAmbientLight() {
		return ambientLight;
	}

	public void setAmbientLight(Color ambientLight) {
		this.ambientLight = ambientLight;
	}

	public Color getBackground() {
		return background;
	}

	public void setBackground(Color background) {
		this.background = background;
	}

	public void addSurface(Surface surface) {
		surfaces.add(surface);
	}

	public List<Surface> getSurfaces() {
		return surfaces;
	}
}
