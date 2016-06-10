/**
 * @author kharunsamuel
 */
public class CubeSolverTest {
    public static void main(String[] args) {
        Cube cube = new Cube();
        cube.left();
        cube.down();
        cube.front();
        cube.up();
        cube.rightInverted();
        cube.back();
        cube.downInverted();
        cube.back();
        cube.back();
        cube.right();
        cube.up();
        cube.backInverted();
        cube.downInverted();
        cube.leftInverted();
        CubeSolver solver = new CubeSolver(cube);
        solver.solve();
    }
}
