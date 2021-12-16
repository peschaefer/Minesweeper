public class Square {

    int bombsAdjacent;
    boolean isBomb;
    boolean showing = false;
    String symbol = "?";
    String color;
    boolean flagged = false;

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
            symbol = "\u001B[31mX" + "\u001B[0m";
        } else{
            if(showing){
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

    @Override
    public String toString(){
        return String.valueOf(bombsAdjacent);
    }
}
