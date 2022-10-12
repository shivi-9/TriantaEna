package cardsanyone;
import java.util.*;

public class TriantaEna {
    private int teamSize;

    enum cardState{
        faceUp, faceDown, inActive;
    }

    public Boolean check_teamSize(int teamSize){
        if(teamSize>7 || teamSize<2){
            System.out.println("Please enter value in the above specified range\nLet's try again");
            return false;
        }
        else{
            this.teamSize = teamSize;
            return true;
        }
    }

    private Player[] initPlayer(Scanner userInput){
        String playerName;
        Player[] playerObjArr = new Player[teamSize];
        System.out.println("**All the players will have $500 at the begining of this game and the Banker will have $1500.**\nPlease enter the name of the banker:");
        playerName = userInput.nextLine();
        playerObjArr[0] = new Player("Banker", playerName, 1500);
        for(int i=1; i<teamSize; i++){
            System.out.println("Enter player " + (i+1) + " name:");
            playerName = userInput.nextLine();
            playerObjArr[i] = new Player("Player", playerName, 500);
        }
        return playerObjArr;
    }

    private GamePiece[] initGamePiece(){
        GamePiece[] gamePieceObjArr = new GamePiece[52];
        String[] suits = {"hearts", "spade", "club", "diamond"};
        int k=0;
        for(int i=0; i<4; i++){
            for(int j=0; j<13; j++){
                switch(j){
                    case 0:
                    gamePieceObjArr[k] = new GamePiece("Ace " + suits[i], cardState.faceDown.toString(), "1");
                    break;
                    case 10:
                    gamePieceObjArr[k] = new GamePiece("Jack " + suits[i], cardState.faceDown.toString(), String.valueOf(j+1));
                    break;
                    case 11:
                    gamePieceObjArr[k] = new GamePiece("Queen " + suits[i], cardState.faceDown.toString(), String.valueOf(j+1));
                    break;
                    case 12:
                    gamePieceObjArr[k] = new GamePiece("King " + suits[i], cardState.faceDown.toString(), String.valueOf(j+1));
                    break;
                    default:
                    gamePieceObjArr[k] = new GamePiece(String.valueOf(j+1) + " " + suits[i], cardState.faceDown.toString(), String.valueOf(j+1)); 
                    break;  
                }
                k++;
            }
        }
        return gamePieceObjArr;
    }

    public void initBoard(GamePiece gamePieceObjArr[]){
        int boardSize[] = {52,2};
        Board boardObj = new Board(boardSize);
        for(int i=0;i<2;i++){
            for(int j=0;j<52;j++)
            boardObj.boardInitCells("",gamePieceObjArr[j]);

        }
        
    }

    public void letsPlay(){
        Scanner userInput = new Scanner(System.in);

        // initialise the player
        Player playerObjArr[] = initPlayer(userInput);
        
        // initialise the cards/gamePiece 
        GamePiece gamePieceObjArr[] = initGamePiece();

        //initialise board
        initBoard(gamePieceObjArr);


    }
}