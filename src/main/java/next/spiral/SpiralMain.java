package next.spiral;

import java.util.Scanner;

public class SpiralMain {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int rowCount = in.nextInt();
        int colCount = in.nextInt();

        SpiralArray spiralArray = new SpiralArray(rowCount, colCount);
        spiralArray.printArray();
    }
}
