/**
 * @author kharunsamuel
 
    Face

       0  1  2
       3  4  5
       6  7  8

 */
public class Face {
    public Face(int color) {
        face = new Block[9];
        for(int i = 0; i < 9; i++) {
            face[i] = new Block(color);
        }
    }
    public void setFace(Block[] newFace) {
        for(int i = 0; i < 9; i++) {
            face[i] = newFace[i];
        }
    }
    public void setTestFace() {
        for(int i = 0; i < 9; i++) {
            face[i].setColor(i);
        }
    }
    public void printFace() {
        for(int i = 1; i <= 9; i++) {
            System.out.print(face[i - 1].getColor() + " ");
            if(i % 3 == 0) System.out.println("");
        }
        System.out.println("");
    }
    public Block[] getFace() {
        return face;
    }
    public Block[] copyRow(int rowNumber) {
        Block[] row = new Block[3];
        switch(rowNumber) {
            case 0: row[0] = new Block(face[0].getColor());
                    row[1] = new Block(face[1].getColor());
                    row[2] = new Block(face[2].getColor());
                    break;

            case 1: row[0] = new Block(face[3].getColor());
                    row[1] = new Block(face[4].getColor());
                    row[2] = new Block(face[5].getColor());
                    break;
                    
            case 2: row[0] = new Block(face[6].getColor());
                    row[1] = new Block(face[7].getColor());
                    row[2] = new Block(face[8].getColor());
                    break;
            default: break;
        }
        return row;
    }
    public Block[] copyColumn(int columnNumber) {
        Block[] column;
        rotateLeft();
        switch(columnNumber) {
            case 0: column = copyRow(2);
                    break;
            case 1: column = copyRow(1);
                    break;
            case 2: column = copyRow(0);
                    break;
            default: column = copyRow(0);
                    System.out.println("Error in Copy Column");
                    break;

        }
        rotateRight();
        return column;
    }
    public void pasteRow(Block[] row, int rowNumber) {
        switch(rowNumber) {
            case 0: face[0] = new Block(row[0].getColor());
                    face[1] = new Block(row[1].getColor());
                    face[2] = new Block(row[2].getColor());
                    break;

            case 1: face[3] = new Block(row[0].getColor());
                    face[4] = new Block(row[1].getColor());
                    face[5] = new Block(row[2].getColor());
                    break;
                    
            case 2: face[6] = new Block(row[0].getColor());
                    face[7] = new Block(row[1].getColor());
                    face[8] = new Block(row[2].getColor());
                    break;
            default: break;
        } 
    }
    public void pasteColumn(Block[] column, int columnNumber) {
        rotateLeft();
        switch(columnNumber) {
            case 0: pasteRow(column, 2);
                    break;
            case 1: pasteRow(column, 1);
                    break;
            case 2: pasteRow(column, 0);
                    break;
            default: break;
        }
        rotateRight();
    }
    public void rotateRight() {
        Block temp = new Block(face[0].getColor());
        face[0].setColor(face[6].getColor());
        face[6].setColor(face[8].getColor());
        face[8].setColor(face[2].getColor());
        face[2].setColor(temp.getColor());
        Block temp1 = new Block(face[1].getColor());
        face[1].setColor(face[3].getColor());
        face[3].setColor(face[7].getColor());
        face[7].setColor(face[5].getColor());
        face[5].setColor(temp1.getColor());
    }

    public void rotateLeft() {
        rotateRight();
        rotateRight();
        rotateRight();
    }

    private Block[] face;
}
