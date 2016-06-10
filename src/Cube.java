/**
 * @author kharunsamuel
 */
public class Cube {
    public Cube() {
        cube = new Face[6];
        for (int i = 0; i < 6; i++) {
            cube[i] = new Face(i);
        }
    }

    public void printCube() {
        System.out.println("");
        Block[] temp = cube[4].getFace();
        System.out.println("             - - -     ");
        System.out.println("            |" + temp[2].getColor() + " " + temp[1].getColor() + " " + temp[0].getColor() + "|    ");
        System.out.println("            |" + temp[5].getColor() + " " + temp[4].getColor() + " " + temp[3].getColor() + "|    ");
        System.out.println("            |" + temp[8].getColor() + " " + temp[7].getColor() + " " + temp[6].getColor() + "|    ");
        System.out.println(" - - - - - - - - - - - -");
        temp = cube[0].getFace();
        System.out.print("|" + temp[2].getColor() + " " + temp[1].getColor() + " " + temp[0].getColor());
        temp = cube[1].getFace();
        System.out.print("|" + temp[0].getColor() + " " + temp[3].getColor() + " " + temp[6].getColor());
        temp = cube[2].getFace();
        System.out.print("|" + temp[2].getColor() + " " + temp[1].getColor() + " " + temp[0].getColor());
        temp = cube[3].getFace();
        System.out.println("|" + temp[8].getColor() + " " + temp[5].getColor() + " " + temp[2].getColor() + "|");
        temp = cube[0].getFace();
        System.out.print("|" + temp[5].getColor() + " " + temp[4].getColor() + " " + temp[3].getColor());
        temp = cube[1].getFace();
        System.out.print("|" + temp[1].getColor() + " " + temp[4].getColor() + " " + temp[7].getColor());
        temp = cube[2].getFace();
        System.out.print("|" + temp[5].getColor() + " " + temp[4].getColor() + " " + temp[3].getColor());
        temp = cube[3].getFace();
        System.out.println("|" + temp[7].getColor() + " " + temp[4].getColor() + " " + temp[1].getColor() + "|");
        temp = cube[0].getFace();
        System.out.print("|" + temp[8].getColor() + " " + temp[7].getColor() + " " + temp[6].getColor());
        temp = cube[1].getFace();
        System.out.print("|" + temp[2].getColor() + " " + temp[5].getColor() + " " + temp[8].getColor());
        temp = cube[2].getFace();
        System.out.print("|" + temp[8].getColor() + " " + temp[7].getColor() + " " + temp[6].getColor());
        temp = cube[3].getFace();
        System.out.println("|" + temp[6].getColor() + " " + temp[3].getColor() + " " + temp[0].getColor() + "|");
        System.out.println(" - - - - - - - - - - - -");
        temp = cube[5].getFace();
        System.out.println("            |" + temp[6].getColor() + " " + temp[7].getColor() + " " + temp[8].getColor() + "|    ");
        System.out.println("            |" + temp[3].getColor() + " " + temp[4].getColor() + " " + temp[5].getColor() + "|    ");
        System.out.println("            |" + temp[0].getColor() + " " + temp[1].getColor() + " " + temp[2].getColor() + "|    ");
        System.out.println("             - - -     ");
        System.out.println("");
    }
    /*
    This is a diagram of the cube for reference and numbering
    
    Face
      
        -> Right
    0  1  2
    3  4  5
    6  7  8

    Cube

                    - - -
                   |     |
                   |  4  |
                   |     |
        - - - - - - - - - - - -
       |     |     |     |     |
       |  0  |  1  |  2  |  3  |
       |     |     |     |     |
        - - - - - - - - - - - -
                   |     |
                   |  5  |
                   |     |
                    - - -

                    - - -
                   |2 1 0|
                   |5 4 3|
                   |8 7 6|
        - - - - - - - - - - - -
       |2 1 0|0 3 6|2 1 0|8 5 2|
       |5 4 3|1 4 7|5 4 3|7 4 1|
       |8 7 6|2 5 8|8 7 6|6 3 0|
        - - - - - - - - - - - -
                   |6 7 8|
                   |3 4 5|
                   |0 1 2|
                    - - -
     For reference purposes we will be labeling the cube with the following
     5 - Front
     4 - Rear
     2 - Bottom
     0 - Top
     1 - Left
     3 - Right
     */

    /*
    Orient right will readjust the cube so that it will be as shown
                    - - -
                   |     |
                   |  3  |
                   |     |
        - - - - - - - - - - - -
       |     |     |     |     |
       |  0  |  4  |  2  |  5  |
       |     |     |     |     |
        - - - - - - - - - - - -
                   |     |
                   |  1  |
                   |     |
                    - - -
     */
    public void orientRight() {
        log += "Orient Right\n";
        Face temp = deepCopyFace(cube[5]);
        cube[5] = deepCopyFace(cube[1]);
        cube[1] = deepCopyFace(cube[4]);
        cube[4] = deepCopyFace(cube[3]);
        cube[3] = deepCopyFace(temp);
        cube[0].rotateLeft();
        cube[2].rotateRight();
    }
    public void unloggedOrientRight() {
        Face temp = deepCopyFace(cube[5]);
        cube[5] = deepCopyFace(cube[1]);
        cube[1] = deepCopyFace(cube[4]);
        cube[4] = deepCopyFace(cube[3]);
        cube[3] = deepCopyFace(temp);
        cube[0].rotateLeft();
        cube[2].rotateRight();
    }
    /*
    Counter to orientRight
     */
    public void orientLeft() {
        log += "Orient Left\n";
        orientRight();
        orientRight();
        orientRight();
    }
    /*
    Orient down will readjust the cube so that it will be as shown
                    - - -
                   |     |
                   | 4-2 |
                   |     |
        - - - - - - - - - - - -
       |     |     |     |     |
       | 0-4 |  1  | 2-5 |  3  |
       |     |     |     |     |
        - - - - - - - - - - - -
                   |     |
                   | 5-0 |
                   |     |
                    - - -
     */
    public void orientDown() {
        log += "Orient Down\n";
        Face temp = deepCopyFace(cube[5]);
        cube[5] = deepCopyFace(cube[0]);
        cube[0] = deepCopyFace(cube[4]);
        cube[4] = deepCopyFace(cube[2]);
        cube[2] = deepCopyFace(temp);
        cube[2].rotateRight();
        cube[2].rotateRight();
        cube[0].rotateRight();
        cube[0].rotateRight();
        cube[3].rotateLeft();
        cube[1].rotateRight();

    }
    /*
    Counter to orientDown
     */
    public void orientUp() {
        log += "Orient Up\n";
        orientDown();
        orientDown();
        orientDown();
    }
    //Correct
    public void right() {
        log += "Right\n";
        cube[3].rotateRight();
        Block[] temp = cube[0].copyColumn(2);
        cube[0].pasteColumn(cube[5].copyColumn(2), 2);
        Block[] temp25 = cube[2].copyColumn(0);
        reverseRow(temp25);
        cube[5].pasteColumn(temp25, 2);
        cube[2].pasteColumn(cube[4].copyColumn(0), 0);
        reverseRow(temp);
        cube[4].pasteColumn(temp, 0);
    }
    //Correct
    public void rightInverted() {
        log += "Right Inverted\n";
        right();
        right();
        right();
    }
    //Correct
    public void left() {
        log += "Left\n";
        cube[1].rotateRight();
        Block[] temp = cube[0].copyColumn(0);
        Block[] temp40 = cube[4].copyColumn(2);
        reverseRow(temp40);
        cube[0].pasteColumn(temp40, 0);
        cube[4].pasteColumn(cube[2].copyColumn(2), 2);
        Block[] temp52 = cube[5].copyColumn(0);
        reverseRow(temp52);
        cube[2].pasteColumn(temp52, 2);
        cube[5].pasteColumn(temp, 0);
    }
    //Correct
    public void leftInverted() {
        log += "Left Inverted\n";
        left();
        left();
        left();
    }
    //Correct
    public void down() {
        log += "Down\n";
        cube[2].rotateRight();
        Block[] temp = cube[1].copyRow(2);
        cube[1].pasteRow(cube[4].copyRow(2), 2);
        cube[4].pasteRow(cube[3].copyRow(2), 2);
        cube[3].pasteRow(cube[5].copyRow(2), 2);
        cube[5].pasteRow(temp, 2);
    }
    //Correct
    public void downInverted() {
        log += "Down Inverted\n";
        down();
        down();
        down();
    }
    //Correct
    public void up() {
        log += "Up\n";
        cube[0].rotateRight();
        Block[] temp = cube[1].copyRow(0);
        cube[1].pasteRow(cube[5].copyRow(0), 0);
        cube[5].pasteRow(cube[3].copyRow(0), 0);
        cube[3].pasteRow(cube[4].copyRow(0), 0);
        cube[4].pasteRow(temp, 0);
    }
    //Correct
    public void upInverted() {
        log += "Up Inverted\n";
        up();
        up();
        up();
    }

    public void front() {
        log += "Front\n";
        cube[5].rotateRight();
        Block[] tempRow = cube[0].copyRow(2);
        Block[] row = cube[1].copyColumn(2);
        Block tempBlock = new Block(row[0].getColor());
        row[0].setColor(row[2].getColor());
        row[2].setColor(tempBlock.getColor());
        cube[0].pasteRow(row, 2);
        row = cube[2].copyRow(2);
        tempBlock.setColor(row[0].getColor());
        row[0].setColor(row[2].getColor());
        row[2].setColor(tempBlock.getColor());
        cube[1].pasteColumn(row, 2);
        cube[2].pasteRow(cube[3].copyColumn(0), 2);
        cube[3].pasteColumn(tempRow, 0);
    }

    public void frontInverted() {
        log += "Front Inverted\n";
        front();
        front();
        front();
    }
    //Correct
    public void back() {
        log += "Back\n";
        cube[4].rotateRight();
        Block[] tempRow = cube[1].copyColumn(0);
        Block[] row = cube[0].copyRow(0);
        Block tempBlock = new Block(row[0].getColor());
        row[0].setColor(row[2].getColor());
        row[2].setColor(tempBlock.getColor());
        cube[1].pasteColumn(row, 0);
        cube[0].pasteRow(cube[3].copyColumn(2), 0);
        cube[3].pasteColumn(cube[2].copyRow(0), 2);
        tempBlock.setColor(tempRow[0].getColor());
        tempRow[0].setColor(tempRow[2].getColor());
        tempRow[2].setColor(tempBlock.getColor());
        cube[2].pasteRow(tempRow, 0);
    }

    public void backInverted() {
        log += "Back Inverted\n";
        back();
        back();
        back();
    }
    public Face[] getCube() {
        return cube;
    }
    public void setCube(Face[] newCube) {
        for(int i = 0; i < 6; i++) {
            cube[i].setFace(newCube[i].getFace());
        }
    }
    private Face deepCopyFace(Face face) {
        Face newFace = new Face(-1);
        newFace.setFace(face.getFace());
        return newFace;
    }
    public int getColor(int face, int block) {
        return cube[face].getFace()[block].getColor();
    }
    private static void reverseRow(Block[] row) {
        Block tempBlock = new Block(row[0].getColor());
        row[0].setColor(row[2].getColor());
        row[2].setColor(tempBlock.getColor());
    }
    public void clearLog() {
        log = "";
    }
    public String getLog() {
        return log;
    }
    private Face[] cube;
    private String log;
}
