package next.spiral;

import java.util.Optional;

public class SpiralElement {

    enum Direction {
        RIGHT(1,0), DOWN(0,1), LEFT(-1,0), UP(0,-1);

        private int dx, dy;
        Direction(int dx, int dy){
            this.dx = dx;
            this.dy = dy;
        }

        public Direction turn(){
            switch(this){
                case RIGHT : return DOWN;
                case DOWN : return LEFT;
                case LEFT : return UP;
                case UP : return RIGHT;
                default : return RIGHT;
            }
        }

        public int getNextRow(int row) {
            return row += dy;
        }

        public int getNextCol(int col) {
            return col += dx;
        }
    }

    private final SpiralElement prevElement;
    private final int num;
    private final int row;
    private final int col;
    private final Direction direction;

    public SpiralElement(SpiralElement prev) {
        this.prevElement = prev;
        this.num = prev.num;
        this.row = prev.row;
        this.col = prev.col;
        this.direction = prev.direction;
    }

    public SpiralElement(SpiralElement prev, int num, int row, int col, Direction direction) {
        this.prevElement = prev;
        this.num = num;
        this.row = row;
        this.col = col;
        this.direction = direction;
    }

    public SpiralElement getNextElement(int rowCount, int colCount) {
        int nextRow = direction.getNextRow(row);
        int nextCol = direction.getNextCol(col);
        if( 0 > nextRow || nextRow >= rowCount ||
                0 > nextCol || nextCol >= colCount) {
            Direction turn = direction.turn();
            return new SpiralElement(this, num + 1, turn.getNextRow(row), turn.getNextCol(col), turn);
        }
        return new SpiralElement(this, num + 1, nextRow, nextCol, direction);
    }

    public SpiralElement getNextElement(Direction direction) {
        return new SpiralElement(this, num + 1, direction.getNextRow(row), direction.getNextCol(col), direction);
    }

    public boolean isEqualsRowCol(int row, int col) {
        return this.row == row && this.col == col;
    }

    public Optional<SpiralElement> getPrevElement() {
        return Optional.ofNullable(prevElement);
    }

    public int getNum() {
        return num;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Direction getDirection() {
        return direction;
    }
}
