import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardTest {
    @Test
    public void noDuplicatesTest(){
        Board board = new Board(5,10,"0,0");
        Square[][] squareMatrix = board.getSquareMatrix();
        int count = 0;
        for(Square[] squareRow : squareMatrix){
            for(Square square : squareRow){
                if(square.isBomb){
                    count++;
                }
            }
        }
        Assertions.assertEquals(count,10);
    }

    @Test
    public void noBombAtStartPosition(){
        Board board = new Board(5,16,"1,1");
        Square[][] squareMatrix = board.getSquareMatrix();
        Assertions.assertFalse(squareMatrix[0][0].isBomb);
        Assertions.assertFalse(squareMatrix[0][1].isBomb);
        Assertions.assertFalse(squareMatrix[0][2].isBomb);
        Assertions.assertFalse(squareMatrix[1][0].isBomb);
        Assertions.assertFalse(squareMatrix[1][1].isBomb);
        Assertions.assertFalse(squareMatrix[1][2].isBomb);
        Assertions.assertFalse(squareMatrix[2][0].isBomb);
        Assertions.assertFalse(squareMatrix[2][1].isBomb);
        Assertions.assertFalse(squareMatrix[2][2].isBomb);
    }
}
