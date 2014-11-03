import java.awt.Color;

public class Color3D {

	private double red;
	private double green;
	private double blue;

	public Color3D(double red, double green, double blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public Color3D multiply(Color3D color) {
		return new Color3D(red * color.red, green * color.green, blue
				* color.blue).clamp();
	}

	public Color3D scale(double scale) {
		return new Color3D(red * scale, green * scale, blue * scale).clamp();
	}

	private Color3D clamp() {
		return new Color3D(Math.min(Math.max(0, red), 1), Math.min(
				Math.max(0, green), 1), Math.min(Math.max(0, blue), 1));
	}

	public Color toColor() {
		return new Color((float) red, (float) green, (float) blue);
	}
}
