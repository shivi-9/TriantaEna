package cardsanyone;

public class CardBoard extends Board{
    // This class is the subclass for board class. It handles functions specific to a card game's board.

    private int[] boardSize = new int[2]; // Height & width of board as an array of length 2.
    private BoardCells[][] gameBoard; // 2D array of board cells

    // Initialise the board
    public void initBoard(int boardSize[]){
        for(int i=0; i<boardSize.length; i++){
            this.boardSize[i] = boardSize[i];
        }
        this.gameBoard = new BoardCells[boardSize[0]][boardSize[1]];
    }

    // Initialise board cells for a particular board object.
    public void boardInitCells(String cellPower, String cellValue, int cellLocation[]){
        for(int j = 0; j < boardSize[1]; j++){
            gameBoard[cellLocation[0]][cellLocation[1]] = new BoardCells(cellPower, cellValue, cellLocation);
        }
    }

    public int[] get_boardSize(){
        return boardSize;
    }

    public BoardCells[][] get_gameBoard(){
        return gameBoard;
    }

    // Print the current board
    public void displayCurrentBoard(){
        for(int j=0; j<boardSize[1]; j++){
            System.out.print(gameBoard[boardSize[0]-1][j].get_cellValue() + "\t");
        }
        System.out.print("\n");
    }
}



