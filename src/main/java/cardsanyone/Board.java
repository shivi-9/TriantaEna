package cardsanyone;

public class Board {
    //this is the board class
    private int[] boardSize = new int[2];
    private BoardCells[][] gameBoard;

    public Board(int boardSize[]){
        for(int i=0; i<boardSize.length; i++){
            this.boardSize[i] = boardSize[i];
        }
        this.gameBoard = new BoardCells[boardSize[0]][boardSize[1]];
    }

    public void boardInitCells(String cellPower, GamePiece cellValue){
        for(int i = 0; i < boardSize[0]; i++){
            for(int j = 0; j < boardSize[1]; j++){
                int[] temp = {i+1, j+1};
                gameBoard[i][j] = new BoardCells(cellPower, cellValue, temp);
            }
        }
    }

    // public void display_initBoard(){
    //     int temp = (String.valueOf(boardSize).length() * 2) + 1;
    //     for(int i = 0; i < boardSize; i++){
    //         for(int j = 0; j < boardSize; j++){
    //             System.out.print("+");
    //             for(int k = 0; k < temp; k++){
    //                 System.out.print("-");
    //             }
    //         }
    //         System.out.print("+\n");
    //         for(int j = 0; j < boardSize; j++){
    //             int temp1 = temp - (String.valueOf(i+1).length() + String.valueOf(j+1).length()) - 1;
    //             System.out.print("|" + gameBoard[i][j].get_cellLocation()[0] + "," + gameBoard[i][j].get_cellLocation()[1]);
    //             for(int k = 0; k < temp1; k++){
    //                 System.out.print(" ");
    //             }
    //         }
    //         System.out.print("|\n");
    //     }
    //     for(int j = 0; j < boardSize; j++){
    //         System.out.print("+");
    //         for(int k = 0; k < temp; k++){
    //             System.out.print("-");
    //         }
    //     }
    //     System.out.print("+\n");
    // }

    // public void displayUpdatedBoard(GamePiece gamePieceObjArr[]){
    //     int temp = (String.valueOf(boardSize).length() * 2) + 1;
    //     int x = 0;
    //     for(int i = 0; i < boardSize; i++){
    //         for(int j = 0; j < boardSize; j++){
    //             System.out.print("+");
    //             for(int k = 0; k < temp; k++){
    //                 System.out.print("-");
    //             }
    //         }
    //         System.out.print("+\n");
    //         for(int j = 0; j < boardSize; j++){
    //             int temp1 = temp - 1;
    //             for(int k = 0; k < gamePieceObjArr.length; k++){
    //                 if(gameBoard[i][j].get_cellValue() == gamePieceObjArr[k].get_pieceKey()){
    //                     x = k;
    //                 }
    //             }
    //             if(gamePieceObjArr[x].get_pieceName().equals("")){
    //                 System.out.print("| ");
    //             }
    //             else{
    //                 System.out.print("|" + gamePieceObjArr[x].get_pieceName());
    //             }   
    //             for(int k = 0; k < temp1; k++){
    //                 System.out.print(" ");
    //             }
    //         }
    //         System.out.print("|\n");
    //     }
    //     for(int j = 0; j < boardSize; j++){
    //         System.out.print("+");
    //         for(int k = 0; k < temp; k++){
    //             System.out.print("-");
    //         }
    //     }
    //     System.out.print("+\n");
    // }

}