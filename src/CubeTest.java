/**
 * @author kharunsamuel
 */
public class CubeTest {
    public static void main(String[] args) {
        Cube cube = new Cube();
        cube.back();
        cube.left();
        cube.down();
        cube.front();
        cube.up();
        cube.rightInverted();
        cube.downInverted();
        cube.back();
        cube.back();
        cube.up();
        cube.orientLeft();
        cube.front();
        cube.left();
        cube.orientRight();
        cube.downInverted();
        cube.backInverted();
        cube.right();
        cube.orientRight();
        cube.orientRight();
        cube.up();

        System.out.println("Shuffled");
        cube.printCube();
        cube.up();
        System.out.println("Right");
        cube.printCube();
    }
}
