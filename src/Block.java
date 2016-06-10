/**
 * @author kharunsamuel
 */
public class Block {
    public Block(int blockColor) {
        color = blockColor;
    }
    public int getColor() {
        return color;
    }
    public void setColor(int newColor) {
        color = newColor;
    }
    private int color;
}
