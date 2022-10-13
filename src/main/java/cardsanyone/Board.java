package cardsanyone;

public abstract class Board {
    
    public abstract void initBoard(int boardSize[]);
    public abstract int[] get_boardSize();
    public abstract void boardInitCells(String cellPower, String cellValue, int cellLocation[]);
    public abstract BoardCells[][] get_gameBoard();
    public abstract void displayCurrentBoard();

}