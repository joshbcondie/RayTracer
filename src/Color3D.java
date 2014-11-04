import java.awt.Color;

public class Color3D {

	public static final Color3D BLACK = new Color3D(0, 0, 0);
	public static final Color3D WHITE = new Color3D(1, 1, 1);

	private double red;
	private double green;
	private double blue;

	public Color3D(double red, double green, double blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public Color3D add(Color3D color) {
		return new Color3D(red + color.red, green + color.green, blue
				+ color.blue).clamp();
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

	public double getRed() {
		return red;
	}

	public double getGreen() {
		return green;
	}

	public double getBlue() {
		return blue;
	}
}
