import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Scene {

	private Color background;
	private List<Surface> surfaces;

	public Scene() {
		surfaces = new ArrayList<>();
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
