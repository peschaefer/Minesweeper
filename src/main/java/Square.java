import java.util.Stack;

public class Square {

    int bombsAdjacent;
    boolean isBomb;
    boolean showing = false;
    String symbol = "?";
    String color;
    boolean flagged = false;
    static int spacesFlagged = 0;
    static Stack<Integer> incorrectFlags = new Stack<>();

    public Square(){
        isBomb = false;
        bombsAdjacent = 0;
    }

    public void incrementBombsAdjacent(){
        bombsAdjacent++;
    }

    public void show(){
        showing = true;
        getColor();
        if (bombsAdjacent == 0){
            symbol = " ";
        } else {
            symbol = color + bombsAdjacent + "\u001B[0m";
        }
    }

    public void hide(){
        showing = false;
        symbol = "?";
    }

    public void flag(){
        flagged = !flagged;
        if(flagged){
            //flagging
            symbol = "\u001B[31mX" + "\u001B[0m";
            spacesFlagged++;
            if(!isBomb){
                incorrectFlags.push(1);
            }
        } else{
            //unflagging
            spacesFlagged--;
            if(!isBomb){
                incorrectFlags.pop();
            }
            if(showing){
                if(bombsAdjacent == 0){
                    symbol = " ";
                    return;
                }
                symbol = color + bombsAdjacent + "\u001B[0m";
            }else{
                symbol = "?";
            }
        }
    }

    public void getColor(){
        if(bombsAdjacent == 1){
            color = "\u001B[34m"; //blue
        }else if(bombsAdjacent == 2){
            color = "\u001B[32m"; //green
        }else if(bombsAdjacent == 3){
            color = "\u001B[33m"; //yellow
        }else{
            color = "\u001B[35m"; //purple
        }
    }

    public int getSpacesFlagged(){
        return spacesFlagged;
    }

    public boolean isCorrect(){
        return incorrectFlags.isEmpty();
    }

    @Override
    public String toString(){
        return String.valueOf(bombsAdjacent);
    }
}
