package next.spiral;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static next.spiral.SpiralElement.Direction.RIGHT;

public class SpiralArray {

    private static final int START_NUMBER = 1;

    private int rowCount;
    private int colCount;
    private int size;
    private Map<Integer, SpiralElement> elementMap;

    public SpiralArray(int rowCount, int colCount) {
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.size = rowCount * colCount;
        this.elementMap = new HashMap<>();
        IntStream.rangeClosed(START_NUMBER, size)
                .forEach(i -> elementMap.put(i, null));
        fillElementMap();
    }

    private void fillElementMap() {
        elementMap.put(START_NUMBER, new SpiralElement(null, START_NUMBER, 0, 0, RIGHT));

        IntStream.range(START_NUMBER, START_NUMBER + size)
                .boxed()
                .map(elementMap::get)
                .map(SpiralElement::getPrevElement)
                .map(this::getCurrentSpiralElement)
                .forEach(this::putCurrentElementToMap);

        elementMap.remove(START_NUMBER + size);
    }

    private void putCurrentElementToMap(SpiralElement current) {
        elementMap.put(current.getNum(), current);
        elementMap.put(current.getNum() + 1, new SpiralElement(current));
    }

    private SpiralElement getCurrentSpiralElement(Optional<SpiralElement> prevOptional) {
        if (!prevOptional.isPresent()) {
            return elementMap.get(START_NUMBER);
        }
        SpiralElement prev = prevOptional.get();
        SpiralElement current = prev.getNextElement(rowCount, colCount);
        if (contains(current.getRow(), current.getCol())) {
            current = prev.getNextElement(prev.getDirection().turn());
        }
        return current;
    }

    private boolean contains(int row, int col) {
        for (SpiralElement value : elementMap.values()) {
            if(value != null) {
                if (value.isEqualsRowCol(row, col))
                    return true;
            }
        }
        return false;
    }

    public void printArray() {
        printArray(elementMapToArray());
    }

    private int[][] elementMapToArray() {
        int[][] result = new int[rowCount][colCount];
        Stream.of(result).forEach(row -> Arrays.fill(row, 0));
        elementMap.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .forEach(element -> result[element.getRow()][element.getCol()] = element.getNum());

        return result;
    }

    private void printArray(int[][] array){
        for(int i=0; i<array.length; i++){
            for(int j=0; j<array[i].length; j++)
                System.out.print(array[i][j] + "\t");
            System.out.println();
        }
    }
}