import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class RayTracer {

	private static final double WINDOW_SIZE = 2;
	private static final int VIEWPORT_SIZE = 500;
	private static final double FOV = 55 * Math.PI / 180;
	private static final double LOOK_FROM = 1.2;

	public static void main(String[] args) throws FileNotFoundException {

		Scene scene = new Scene();
		scene.setDirectionToLight(new Vector3D(0, 1, 0));
		scene.setLightColor(new Color3D(1, 1, 1));
		scene.setAmbientLight(new Color3D(0, 0, 0));
		scene.setBackground(new Color3D(.2, .2, .2));
		scene.addSurface(new Sphere(new Vector3D(0, .3, 0), .2)
				.setReflective(new Color3D(.75, .75, .75)));
		scene.addSurface(new Triangle(new Vector3D(0, -.5, .5), new Vector3D(1,
				.5, 0), new Vector3D(0, -.5, -.5))
				.setDiffuse(new Color3D(0, 0, 1))
				.setSpecular(new Color3D(1, 1, 1)).setPhongConstant(4));
		scene.addSurface(new Triangle(new Vector3D(0, -.5, .5), new Vector3D(0,
				-.5, -.5), new Vector3D(-1, .5, 0))
				.setDiffuse(new Color3D(1, 1, 0))
				.setSpecular(new Color3D(1, 1, 1)).setPhongConstant(4));

		Color[][] pixels = new Color[VIEWPORT_SIZE][VIEWPORT_SIZE];

		for (int y = 0; y < VIEWPORT_SIZE; y++) {
			for (int x = 0; x < VIEWPORT_SIZE; x++) {
				Ray ray = new Ray();
				ray.setOrigin(new Vector3D(0, 0, LOOK_FROM + WINDOW_SIZE / 2
						/ Math.tan(FOV / 2)));
				ray.setDirection(new Vector3D((x - VIEWPORT_SIZE / 2 + 0.5)
						* WINDOW_SIZE / VIEWPORT_SIZE,
						(VIEWPORT_SIZE / 2 - y - 0.5) * WINDOW_SIZE
								/ VIEWPORT_SIZE, -WINDOW_SIZE / 2
								/ Math.tan(FOV / 2)).normalize());
				ray.setColor(scene.getBackground());
				ray.trace(scene);
				pixels[y][x] = ray.getColor().toColor();
			}
		}

		PrintWriter writer = new PrintWriter("image.ppm");
		writer.println("P3");
		writer.println(VIEWPORT_SIZE + " " + VIEWPORT_SIZE);
		writer.println(255);
		for (int i = 0; i < VIEWPORT_SIZE; i++) {
			for (int j = 0; j < VIEWPORT_SIZE; j++) {
				writer.print(pixels[i][j].getRed() + " ");
				writer.print(pixels[i][j].getGreen() + " ");
				writer.println(pixels[i][j].getBlue());
			}
		}
		writer.close();
	}
}
