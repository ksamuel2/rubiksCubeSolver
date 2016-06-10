/**
 * @author kharunsamuel
 */
public class CubeSolver {
    private class Pair {
        public Pair()
        {
            return;
        }
        public int getV1() {
            return v1;
        }
        public int getV2() {
            return v2;
        }
        public void setPair(int x, int y) {
            v1 = x;
            v2 = y;
        }
        private int v1;
        private int v2;
    }
    public CubeSolver(Cube unsolved) {
        cube = new Cube();
        cube.setCube(unsolved.getCube());
    }
    public String solve() {
        System.out.println("Scrambled");
        cube.clearLog();
        cube.printCube();
        solveTopCross();
        System.out.println("Top Cross Solved");
        cube.printCube();
        orientTopCross();
        System.out.println("Top Cross Oriented");
        cube.printCube();
        solveCorner();
        System.out.println("Top Face Solved");
        cube.printCube();
        solveSecondLayer();
        System.out.println("Second Layer Solved");
        cube.printCube();
        cube.orientDown();
        cube.orientDown();
        solveFinalCross();
        System.out.println("Final Cross Solved");
        cube.printCube();
        orientTopCross();
        System.out.println("Final Cross Oriented");
        cube.printCube();
        placeCorners();
        System.out.println("Corners Placed");
        cube.printCube();
        orientCorners();
        System.out.println("Solved! Solution written to log!");
        cube.printCube();
        return cube.getLog();
    }
    private void solveTopCross() {
        int color = cube.getColor(0, 4);
        while(!checkTopCrossSolved()) {
            int face = -1, block = -1;
            for(int i = 1; i < 6; i++) {
                for(int j = 1; j <= 7; j += 2) {
                    if(cube.getColor(i, j) == color) {
                        face = i;
                        block = j;
                        break;
                    }
                }
                if(face != -1) break;
            }
            if(face == 1) {
                cube.orientRight();
                face = 5;
            }
            if(face == 4) {
                cube.orientRight();
                cube.orientRight();
                face = 5;
            }
            if(face == 3) {

                cube.orientLeft();
                face = 5;
            }
            if(face == 5) {
                if (block == 1) {
                    cube.front();
                    block = 5;
                }
                if (block == 7) {
                    while (cube.getColor(0, 7) == color) cube.up();
                    cube.frontInverted();
                    block = 5;
                }
                if (block == 5) {
                    while (cube.getColor(0, 5) == color) cube.up();
                    cube.right();
                }
                if (block == 3) {
                    while (cube.getColor(0, 3) == color) cube.up();
                    cube.leftInverted();
                }
            }
            if(face == 2) {
                if(block == 5) {
                    cube.down();
                    cube.down();
                    block = 3;
                }
                if(block == 7) {
                    cube.down();
                    block = 3;
                }
                if(block == 1) {
                    cube.downInverted();
                    block = 3;
                }
                if(block == 3) {
                    while (cube.getColor(0, 5) == color) cube.up();
                    cube.right();
                    cube.right();
                }
            }
        }
    }
    private boolean checkTopCrossSolved() {
        int color = cube.getColor(0, 4);
        for(int i = 1; i <= 7; i += 2) {
            if(color != cube.getColor(0, i))
                return false;
        }
        return true;
    }
    private void orientTopCross() {
        int matches = checkTopCrossMatches();
        while(matches != 2 && matches != 4) {
            cube.up();
            matches = checkTopCrossMatches();
        }
        if(checkTopCrossMatches() == 4) return;
        while(cube.getColor(5, 1) == cube.getColor(5, 4)) cube.orientRight();
        if(cube.getColor(1, 1) != cube.getColor(1, 4)) cube.orientRight();
        if(cube.getColor(4, 1) != cube.getColor(4, 4)) {
            cube.orientRight();
            cube.right();
            cube.upInverted();
            cube.upInverted();
            cube.rightInverted();
            cube.upInverted();
            cube.right();
            cube.upInverted();
            cube.rightInverted();
            cube.upInverted();
            orientTopCross();
            return;
        }
        cube.right();
        cube.upInverted();
        cube.upInverted();
        cube.rightInverted();
        cube.upInverted();
        cube.right();
        cube.upInverted();
        cube.rightInverted();
        cube.upInverted();
    }
    private int checkTopCrossMatches() {
        int matches = 0;
        int color1 = cube.getColor(1, 1);
        int color2 = cube.getColor(1, 4);
        if(color1 == color2) matches++;
        color1 = cube.getColor(5, 1);
        color2 = cube.getColor(5, 4);
        if(color1 == color2) matches++;
        color1 = cube.getColor(3, 1);
        color2 = cube.getColor(3, 4);
        if(color1 == color2) matches++;
        color1 = cube.getColor(4, 1);
        color2 = cube.getColor(4, 4);
        if(color1 == color2) matches++;
        return matches;
    }
    private void solveCorner() {
        Pair pair;
        while((pair = findCorner(cube.getColor(0, 4))).getV1() != -1) {
            int face = pair.getV1();
            int block = pair.getV2();
            if(face == 0) {
                while(block != 8) {
                    cube.orientRight();
                    block += 2;
                    if(block == 4) block = 6;
                    if(block == 10) block = 0;
                }
                cube.rightInverted();
                cube.downInverted();
                cube.right();
                continue;
            }
            if(face != 2) {
                if(block == 0 || block == 2) {
                    if(face == 1) cube.orientRight();
                    if(face == 4) {
                        cube.orientRight();
                        cube.orientRight();
                    }
                    if(face == 3) cube.orientLeft();
                    if(block == 0) {
                        cube.left();
                        cube.downInverted();
                        cube.leftInverted();
                        continue;
                    }
                    if(block == 2) {
                        cube.rightInverted();
                        cube.down();
                        cube.right();
                        continue;
                    }
                }

            }
            if(face == 2) {
                while (block != 2) {
                    cube.down();
                    if (block == 0) block = 2;
                    if (block == 6) block = 0;
                    if (block == 8) block = 6;
                }
                int upCount = 0;
                while (cube.getColor(0, 8) == cube.getColor(0, 4)) {
                    cube.up();
                    upCount++;
                }
                cube.rightInverted();
                cube.downInverted();
                cube.right();
                for (int i = 0; i < upCount; i++) {
                    cube.upInverted();
                }
                continue;
            }
            if(face == 4) {
                cube.orientRight();
                cube.orientRight();
            }
            if(face == 3) {
                cube.orientLeft();
            }
            if(face == 1) {
                cube.orientRight();
            }
            if(block == 6) {
                while(cube.getColor(1, 8) != cube.getColor(1, 4)) {
                    cube.down();
                    cube.orientLeft();
                }
                cube.down();
                cube.left();
                cube.downInverted();
                cube.leftInverted();
            }
            if(block == 8) {
                cube.printCube();
                //int i = 0;
                while(cube.getColor(3, 6) != cube.getColor(3, 4)) {
                    cube.down();
                    cube.orientLeft();
                }
                cube.downInverted();
                cube.rightInverted();
                cube.down();
                cube.right();
            }
        }
    }
    private Pair findCorner(int color) {
        Pair pair = new Pair();
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j <= 8; j += 2) {
                if(j == 4) continue;
                if(cube.getColor(i, j) == color) {
                    int color1, color2;
                    boolean solved = false;
                    if(i == 0) {
                        solved = true;
                        if(j == 6) {
                            color1 = cube.getColor(1, 2);
                            color2 = cube.getColor(1, 4);
                            if(color1 != color2) solved = false;
                            color1 = cube.getColor(5, 0);
                            color2 = cube.getColor(5, 4);
                            if(color1 != color2) solved = false;
                        }
                        if(j == 8) {
                            color1 = cube.getColor(3, 0);
                            color2 = cube.getColor(3, 4);
                            if(color1 != color2) solved = false;
                            color1 = cube.getColor(5, 2);
                            color2 = cube.getColor(5, 4);
                            if(color1 != color2) solved = false;
                        }
                        if(j == 0) {
                            color1 = cube.getColor(4, 2);
                            color2 = cube.getColor(4, 4);
                            if(color1 != color2) solved = false;
                            color1 = cube.getColor(1, 0);
                            color2 = cube.getColor(1, 4);
                            if(color1 != color2) solved = false;
                        }
                        if(j == 2) {
                            color1 = cube.getColor(3, 2);
                            color2 = cube.getColor(3, 4);
                            if(color1 != color2) solved = false;
                            color1 = cube.getColor(4, 0);
                            color2 = cube.getColor(4, 4);
                            if(color1 != color2) solved = false;
                        }
                    }
                    if(solved == false) {
                        pair.setPair(i, j);
                        return pair;
                    }
                }
            }
        }
        pair.setPair(-1, -1);
        return pair;
    }
    private void solveSecondLayer() {
        Pair pair;
        int i = 0;
        while((pair = findSecondLayerPiece()).getV1() != -1) {
            i++;
            int face = pair.getV1();
            int block = pair.getV2();
            System.out.println("Face: " + face + "Block: " + block);
            cube.printCube();
            if(block == 5) {
                if(face == 1) cube.orientRight();
                if(face == 4) {
                    cube.orientRight();
                    cube.orientRight();
                }
                if(face == 3) cube.orientLeft();
                cube.rightInverted();
                cube.downInverted();
                cube.right();
                continue;
            }
            if(block == 7) {
                if(face == 1) cube.orientRight();
                if(face == 4) {
                    cube.orientRight();
                    cube.orientRight();
                }
                if(face == 3) cube.orientLeft();
                while(cube.getColor(5, 7) != cube.getColor(5, 4)) {
                    cube.down();
                    cube.orientLeft();
                }
                if(cube.getColor(2, 7) == cube.getColor(1, 4)) {
                    cube.down();
                    cube.left();
                    cube.downInverted();
                    cube.leftInverted();
                    cube.orientRight();
                    cube.downInverted();
                    cube.rightInverted();
                    cube.down();
                    cube.right();
                }
                else {
                    cube.downInverted();
                    cube.rightInverted();
                    cube.down();
                    cube.right();
                    cube.orientLeft();
                    cube.down();
                    cube.left();
                    cube.downInverted();
                    cube.leftInverted();
                }
            }

        }

    }
    private Pair findSecondLayerPiece() {
        Pair pair = new Pair();
        int color1, color2, color3, color4, color5;
        color1 = cube.getColor(1, 4);
        color2 = cube.getColor(2, 4);
        color3 = cube.getColor(3, 4);
        color4 = cube.getColor(4, 4);
        color5 = cube.getColor(5, 4);
        if(cube.getColor(1, 7) == color1 || cube.getColor(1, 7) == color3
            || cube.getColor(1, 7) == color4 || cube.getColor(1, 7) == color5) {
            if(cube.getColor(2, 5) != cube.getColor(2, 4)) {
                pair.setPair(1, 7);
                return pair;
            }
        }
        if(cube.getColor(3, 7) == color1 || cube.getColor(3, 7) == color3
            || cube.getColor(3, 7) == color4 || cube.getColor(3, 7) == color5) {
            if(cube.getColor(2, 3) != cube.getColor(2, 4)) {
                pair.setPair(3, 7);
                return pair;
            }
        }
        if(cube.getColor(4, 7) == color1 || cube.getColor(4, 7) == color3
            || cube.getColor(4, 7) == color4 || cube.getColor(4, 7) == color5) {
            if(cube.getColor(2, 1) != cube.getColor(2, 4)) {
                pair.setPair(4, 7);
                return pair;
            }
        }
        if(cube.getColor(5, 7) == color1 || cube.getColor(5, 7) == color3
            || cube.getColor(5, 7) == color4 || cube.getColor(5, 7) == color5) {
            if(cube.getColor(2, 7) != cube.getColor(2, 4)) {
                pair.setPair(5, 7);
                return pair;
            }
        }
        if(cube.getColor(1, 5) != color1 && cube.getColor(1, 5) != color2 && cube.getColor(5, 3) != color2)
        {
            pair.setPair(1, 5);
            return pair;
        }
        if(cube.getColor(5, 5) != color5 && cube.getColor(5, 5) != color2 && cube.getColor(3, 3) != color2)
        {
            pair.setPair(5, 5);
            return pair;
        }
        if(cube.getColor(3, 5) != color3 && cube.getColor(3, 5) != color2 && cube.getColor(4, 3) != color2)
        {
            pair.setPair(3, 5);
            return pair;
        }
        if(cube.getColor(4, 5) != color4 && cube.getColor(4, 5) != color2 && cube.getColor(1, 3) != color2)
        {
            pair.setPair(4, 5);
            return pair;
        }
        pair.setPair(-1, -1);
        return pair;
    }
    private void solveFinalCross() {
        int color = cube.getColor(0, 4);
        if(color == cube.getColor(0, 5) && color == cube.getColor(0, 3)
                && color == cube.getColor(0, 1) && color == cube.getColor(1, 7)) return;
        if(color == cube.getColor(0, 5) && color == cube.getColor(0, 3)) {
            cube.front();
            cube.right();
            cube.up();
            cube.rightInverted();
            cube.upInverted();
            cube.frontInverted();
            return;
        }
        if(color == cube.getColor(0, 1) && color == cube.getColor(1, 7)) {
            cube.orientRight();
            solveFinalCross();
            return;
        }
        for(int i = 0; i < 4; i++) {
            if(color == cube.getColor(0, 1) && color == cube.getColor(0, 3)) {
                cube.front();
                cube.up();
                cube.right();
                cube.upInverted();
                cube.rightInverted();
                cube.frontInverted();
                return;
            }
            cube.orientRight();
        }
        cube.front();
        cube.right();
        cube.up();
        cube.rightInverted();
        cube.upInverted();
        cube.frontInverted();
        solveFinalCross();
        return;
    }
    private void placeCorners() {
        while(!checkCornersPlaced()) {
            int i = 0;
            cube.printCube();
            while(i < 4) { //Orienting cube until one correct corner is in place

                int color1 = cube.getColor(3, 4);
                int color2 = cube.getColor(5, 4);
                int matches = 0;
                int corner = cube.getColor(0, 8);
                if(corner == color1 || corner == color2) matches++;
                corner = cube.getColor(5, 2);
                if(corner == color1 || corner == color2) matches++;
                corner = cube.getColor(3, 0);
                if(corner == color1 || corner == color2) matches++;
                System.out.println("Matches: " + matches);
                cube.printCube();
                if(matches == 2) break;
                cube.orientRight();
                i++;
                System.out.println(i);
            }
            cube.up();
            cube.right();
            cube.upInverted();
            cube.leftInverted();
            cube.up();
            cube.rightInverted();
            cube.upInverted();
            cube.left();
        }
    }
    private boolean checkCornersPlaced() {
        for(int i = 0; i < 4; i++) {
            int color1 = cube.getColor(3, 4);
            int color2 = cube.getColor(5, 4);
            int matches = 0;
            int corner = cube.getColor(0, 8);
            if(corner == color1 || corner == color2) matches++;
            corner = cube.getColor(5, 2);
            if(corner == color1 || corner == color2) matches++;
            corner = cube.getColor(3, 0);
            if(corner == color1 || corner == color2) matches++;
            if(matches != 2) return false;
            cube.unloggedOrientRight();
        }
        System.out.println("Confirmed");
        cube.unloggedOrientRight();
        return true;
    }
    private void orientCorners() {
        if(!checkSolved())
            while(checkFinalCorner()) cube.orientRight();
        while(!checkSolved()) {
            while(!checkFinalCorner()) {
                cube.rightInverted();
                cube.downInverted();
                cube.right();
                cube.down();
            }
            cube.up();
        }

    }
    private boolean checkFinalCorner() {
        if(cube.getColor(5, 2) != cube.getColor(5, 1)) return false;
        if(cube.getColor(3, 0) != cube.getColor(3, 1)) return false;
        if(cube.getColor(0, 8) != cube.getColor(0, 5)) return false;
        return true;
    }
    private boolean checkSolved() {
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 9; j++) {
                if(cube.getColor(i, j) != cube.getColor(i, 4)) return false;
            }
        }
        return true;
    }

    private Cube cube;
}
