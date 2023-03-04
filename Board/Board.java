package cardsanyone;

public abstract class Board {
    // abstract parent class for all the types of boards like a grid board for tick-tac-toe or monopoly
    // and a table type board for card games like BlackJack and Uno.

    public abstract void initBoard(int boardSize[]);
    public abstract int[] get_boardSize();
    public abstract void boardInitCells(String cellPower, String cellValue, int cellLocation[]);
    public abstract BoardCells[][] get_gameBoard();
    public abstract void displayCurrentBoard();

}