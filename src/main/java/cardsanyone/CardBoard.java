package cardsanyone;

public class CardBoard extends Board{
    private int[] boardSize = new int[2];
    private BoardCells[][] gameBoard;

    public void initBoard(int boardSize[]){
        for(int i=0; i<boardSize.length; i++){
            this.boardSize[i] = boardSize[i];
        }
        this.gameBoard = new BoardCells[boardSize[0]][boardSize[1]];
    }

    public int[] get_boardSize(){
        return boardSize;
    }

    public void boardInitCells(String cellPower, String cellValue, int cellLocation[]){
        for(int i = 0; i < boardSize[0]; i++){
            for(int j = 0; j < boardSize[1]; j++){
                gameBoard[cellLocation[0]][cellLocation[1]] = new BoardCells(cellPower, cellValue, cellLocation);
            }
        }
    }

    public BoardCells[][] get_gameBoard(){
        return gameBoard;
    }

    public void displayCurrentBoard(){
        for(int i=0; i<boardSize[0]; i++){
            for(int j=0; j<boardSize[1]; j++){
                if(j==0){
                    System.out.print(gameBoard[i][j].get_cellValue() + "\t");
                }
                System.out.print(gameBoard[i][j] + "\t");
            }
            System.out.print("\n");
        }
    }
}



