import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class RayTracer {

	private static final int SIZE = 500;
	private static final double FOV = 55;

	public static void main(String[] args) throws FileNotFoundException {

		Scene scene = new Scene();
		scene.setBackground(Color.BLACK);
		scene.addSurface(new Sphere(new Vector3D(0, .3, 0), .2)
				.setReflective(new Color(192, 192, 192)));
		scene.addSurface(new Triangle(new Vector3D(0, -.5, .5), new Vector3D(1,
				.5, 0), new Vector3D(0, -.5, -.5))
				.setDiffuse(new Color(0, 0, 255))
				.setSpecular(new Color(255, 255, 255)).setPhongConstant(4));
		scene.addSurface(new Triangle(new Vector3D(0, -.5, .5), new Vector3D(0,
				-.5, -.5), new Vector3D(-1, .5, 0))
				.setDiffuse(new Color(255, 255, 0))
				.setSpecular(new Color(255, 255, 255)).setPhongConstant(4));

		Color[][] pixels = new Color[SIZE][SIZE];

		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				Ray ray = new Ray();
				ray.setOrigin(new Vector3D(0, 0, SIZE / 2 / Math.tan(FOV / 2)));
				ray.setDirection(new Vector3D(x - SIZE / 2 + 0.5, SIZE / 2 - y
						- 0.5, 0).normalize());
				ray.setColor(scene.getBackground());
				ray.trace(scene);
				pixels[y][x] = ray.getColor();
			}
		}

		PrintWriter writer = new PrintWriter("image.ppm");
		writer.println("P6");
		writer.println(SIZE + " " + SIZE);
		writer.println(255);
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				writer.print(pixels[i][j].getRed() + " ");
				writer.print(pixels[i][j].getGreen() + " ");
				writer.println(pixels[i][j].getBlue());
			}
		}
		writer.close();
	}
}
