import java.util.Scanner;

public class Game {
    Scanner scanner = new Scanner(System.in);
    public void play(){
        System.out.println("Enter the size of the board:");
        int boardSize = Integer.parseInt(scanner.next());
        System.out.println("Enter the number of bombs:");
        int numberOfBombs = Integer.parseInt(scanner.next());
        printFakeBoard(boardSize);
        System.out.println("Enter your starting position in the style of 0,0:");
        String startPosition = scanner.next();
        Board board = new Board(boardSize,numberOfBombs,startPosition);
        int x = Integer.parseInt(startPosition.split(",")[0]);
        int y = Integer.parseInt(startPosition.split(",")[1]);
        board.reveal(x,y);
        board.printBoard();
        board.printCheatSheet();
    }

    private void printFakeBoard(int boardSize) {
        String dashes ="-----".repeat(boardSize);
        dashes = dashes.substring(0, dashes.length()-(int)(boardSize*.8));
        System.out.println(dashes);
        for(int i = 0; i < boardSize; i++){
            System.out.print("|");
            for (int j = 0; j < boardSize; j++){
                System.out.print(" ? |");
            }
            System.out.println("\n" + dashes);
        }
    }
}
