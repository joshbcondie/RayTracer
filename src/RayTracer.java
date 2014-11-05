import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class RayTracer {

	private static final int VIEWPORT_SIZE = 500;

	public static void main(String[] args) throws FileNotFoundException {

		Scene[] scenes = new Scene[3];
		scenes[0] = new Scene();
		scenes[0].setFov(55 * Math.PI / 180);
		scenes[0].setLookFrom(1.2);
		scenes[0].setDirectionToLight(new Vector3D(0, 1, 0));
		scenes[0].setLightColor(new Color3D(1, 1, 1));
		scenes[0].setAmbientLight(new Color3D(0, 0, 0));
		scenes[0].setBackground(new Color3D(.2, .2, .2));

		scenes[0].addSurface(new Sphere(new Vector3D(0, .3, 0), .2)
				.setReflective(new Color3D(.75, .75, .75)));
		scenes[0].addSurface(new Triangle(new Vector3D(0, -.5, .5),
				new Vector3D(1, .5, 0), new Vector3D(0, -.5, -.5))
				.setDiffuse(new Color3D(0, 0, 1))
				.setSpecular(new Color3D(1, 1, 1)).setPhongConstant(4));
		scenes[0].addSurface(new Triangle(new Vector3D(0, -.5, .5),
				new Vector3D(0, -.5, -.5), new Vector3D(-1, .5, 0))
				.setDiffuse(new Color3D(1, 1, 0))
				.setSpecular(new Color3D(1, 1, 1)).setPhongConstant(4));

		scenes[1] = new Scene();
		scenes[1].setFov(28 * Math.PI / 180);
		scenes[1].setLookFrom(1);
		scenes[1].setDirectionToLight(new Vector3D(1, 0, 0));
		scenes[1].setLightColor(new Color3D(1, 1, 1));
		scenes[1].setAmbientLight(new Color3D(.1, .1, .1));
		scenes[1].setBackground(new Color3D(.2, .2, .2));

		scenes[1].addSurface(new Sphere(new Vector3D(.35, 0, -.1), .05)
				.setDiffuse(new Color3D(1, 1, 1))
				.setSpecular(new Color3D(1, 1, 1)).setPhongConstant(4));
		scenes[1].addSurface(new Sphere(new Vector3D(.2, 0, -.1), .075)
				.setDiffuse(new Color3D(1, 0, 0))
				.setSpecular(new Color3D(.5, 1, .5)).setPhongConstant(32));
		scenes[1].addSurface(new Sphere(new Vector3D(-.6, 0, 0), .3)
				.setDiffuse(new Color3D(0, 1, 0))
				.setSpecular(new Color3D(.5, 1, .5)).setPhongConstant(32));
		scenes[1].addSurface(new Triangle(new Vector3D(.3, -.3, -.4),
				new Vector3D(0, .3, -.1), new Vector3D(-.3, -.3, .2))
				.setDiffuse(new Color3D(0, 0, 1))
				.setSpecular(new Color3D(1, 1, 1)).setPhongConstant(32));
		scenes[1].addSurface(new Triangle(new Vector3D(-.2, .1, .1),
				new Vector3D(-.2, -.5, .2), new Vector3D(-.2, .1, -.3))
				.setDiffuse(new Color3D(1, 1, 0))
				.setSpecular(new Color3D(1, 1, 1)).setPhongConstant(4));

		scenes[2] = new Scene();
		scenes[2].setFov(28 * Math.PI / 180);
		scenes[2].setLookFrom(1);
		scenes[2].setDirectionToLight(new Vector3D(0, 0, 1));
		scenes[2].setLightColor(new Color3D(1, 1, 1));
		scenes[2].setAmbientLight(new Color3D(0, 0, 0));
		scenes[2].setBackground(new Color3D(.2, .2, .2));

		scenes[2].addSurface(new Sphere(new Vector3D(0, 0, -2), .3)
				.setDiffuse(new Color3D(0, 0, 1))
				.setSpecular(new Color3D(.5, .5, .5)).setPhongConstant(32));
		scenes[2].addSurface(new Sphere(new Vector3D(0, 1, -2), .3)
				.setDiffuse(new Color3D(1, 0, 0))
				.setSpecular(new Color3D(.5, .5, .5)).setPhongConstant(32));
		scenes[2].addSurface(new Sphere(new Vector3D(1, 0, -2), .3)
				.setDiffuse(new Color3D(0, 1, 0))
				.setSpecular(new Color3D(.5, .5, .5)).setPhongConstant(32));
		scenes[2].addSurface(new Triangle(new Vector3D(0, 3, -5), new Vector3D(
				-4, -4, -2), new Vector3D(4, -4, -4))
				.setReflective(new Color3D(0.8, 0.8, 0.8)));
		scenes[2].addSurface(new Sphere(new Vector3D(1, 0, -4), .5)
				.setReflective(new Color3D(0, 1, 0)));
		scenes[2].addSurface(new Sphere(new Vector3D(.55, .25, -1), .4)
				.setRefractionIndex(1.01).setRefractive(new Color3D(1, 1, 0)));

		Color[][] pixels = new Color[VIEWPORT_SIZE][VIEWPORT_SIZE];

		for (int z = 0; z < scenes.length; z++) {
			for (int y = 0; y < VIEWPORT_SIZE; y++) {
				for (int x = 0; x < VIEWPORT_SIZE; x++) {
					Ray ray = new Ray();
					ray.setOrigin(new Vector3D(0, 0, scenes[z].getLookFrom()));
					ray.setDirection(new Vector3D(
							(x - VIEWPORT_SIZE / 2 + 0.5), (VIEWPORT_SIZE / 2
									- y - 0.5), -VIEWPORT_SIZE / 2
									/ Math.tan(scenes[z].getFov())).normalize());
					ray.setColor(scenes[z].getBackground());
					ray.trace(scenes[z]);
					pixels[y][x] = ray.getColor().toColor();
				}
			}

			PrintWriter writer = new PrintWriter("image" + z + ".ppm");
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
}
