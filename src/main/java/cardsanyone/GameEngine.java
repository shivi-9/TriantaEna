package cardsanyone;
import java.util.*;

public class GameEngine {
    private static int set_teamSize(Scanner userInput){
        System.out.println("How many players are going today?");
        int teamSize;
        try {
            teamSize = Integer.parseInt(userInput.nextLine());
            return teamSize;
        }catch(Exception e){
            Scanner userInput1 = new Scanner(System.in);
            System.out.println("Input is invalid!\nLets try again!");
            teamSize = set_teamSize(userInput1);
            return teamSize;
        }
    }

    public static void beginTriantaEna(Scanner userInput){
        int teamSize;
        System.out.println("Let's create the teams");
        teamSize = set_teamSize(userInput); // will ask user to input the number of players
        TriantaEna triObj = new TriantaEna();
        while(!triObj.check_teamSize(teamSize)){
            teamSize = set_teamSize(userInput);
        };
        triObj.letsPlay(); // will check if the number of players is allowed by the Trianta Ena game or not
        // Team teamObjArr[] = new Team[oncObj.teamSize]; 
        // //Initiating objects of class Team for each team
        // for(int i = 0; i < teamObjArr.length; i++){
        //     if(i == 0){
        //         teamObjArr[i] = new Team(teamSize,"Order");
        //     }
        //     else{
        //         teamObjArr[i] = new Team(teamSize,"Chaos");
        //     }
        //     System.out.println("We are creating Team " + teamObjArr[i].get_teamName());
        //     teamObjArr[i].createTeam(userInput); //This function will create teams and initiate the players of that team by calling the contructor of Player class
        // }
        // //Initiating the game pieces for this game
        // GamePiece gamePieceObjArr[] = new GamePiece[3];
        // gamePieceObjArr[0] = new GamePiece("", "", "", 0);
        // gamePieceObjArr[1] = new GamePiece("O", "", "O", 1);
        // gamePieceObjArr[2] = new GamePiece("X", "", "X", 2);
        // System.out.println("Let's set up the board now!");
        // int boardSize = oncObj.input_boardSize(userInput);
        // Board boardObj = new Board(boardSize);
        // boardObj.boardInitCells("", 0); //Initiating cells associated to the board
        // boardObj.display_initBoard();
        // System.out.println("Now, lets play!");
        // Player[] currentPlayers = new Player[2]; //Array of players who are playing currently
        // int i=0;
        // int flag=0;
        // while(flag==0 && i<teamSize){
        //     //Clearing the board
        //     for(int j=0;j<boardSize;j++){
        //         for(int k=0;k<boardSize;k++){
        //             boardObj.gameBoard[j][k].set_cellValue(0); 
        //         }
        //     }
        //     currentPlayers[0] = teamObjArr[0].playerObjArr[i];
        //     currentPlayers[1] = teamObjArr[1].playerObjArr[i];
        //     System.out.println(currentPlayers[0].get_playerName() + " from Team " + currentPlayers[0].get_playerTeam() + " vs " + currentPlayers[1].get_playerName() + " from Team " + currentPlayers[1].get_playerTeam());
        //     oncObj.letsplay(currentPlayers, userInput, boardObj, gamePieceObjArr);
        //     System.out.println("Score so far");
        //     displayScore(teamObjArr);
        //     System.out.println("Do you wanna continue the game?\nPress n to exit the game or any other key to contiue:");
        //     if(userInput.nextLine().toLowerCase().equals("n")){
        //         System.out.println("Final Score Summary");
        //         displayScore(teamObjArr);
        //         System.out.println("Bye!");
        //         flag=1;
        //         break;
        //     }
        //     else{
        //         if(i == teamSize-1){
        //             i=0;
        //         }
        //         else{
        //             i++;
        //         }
        //     }
        // }
    }
    
    public static void main(String args[]){ 
        Scanner userInput = new Scanner(System.in);
        //Welcome Message
        System.out.println("Welcome!\nLet's play Trianta Ena."); //Welcome Message
        beginTriantaEna(userInput); 
    }
}
