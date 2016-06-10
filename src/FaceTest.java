/**
 * @author kharunsamuel
 */
public class FaceTest {
    public static void main(String[] args) {
        Face face = new Face(1);
        face.setTestFace();
        Face face1 = new Face(1);
        face1.pasteColumn(face.copyRow(1), 0);
        face1.printFace();

    }
}
