package cardsanyone;
import java.util.*;

public class TriantaEna extends Game{
    private int teamSize;

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

    private int get_initCash(Scanner userInput){
        System.out.println("Enter the amount that all the players will have in the begining of the game:");
        int cash;
        try {
            cash = Integer.parseInt(userInput.nextLine());
            return cash;
        }catch(Exception e){
            Scanner userInput1 = new Scanner(System.in);
            System.out.println("Input is invalid!\nLets try again!");
            cash = get_initCash(userInput1);
            return cash;
        }
    }

    public Player[] initPlayer(Scanner userInput){
        String playerName;
        Player[] playerObjArr = new Player[teamSize];
        int playerScore = get_initCash(userInput);
        System.out.println("Please enter the name of the banker:");
        playerName = userInput.nextLine();
        playerObjArr[0] = new Player("Banker", playerName, playerScore*3);
        for(int i=1; i<teamSize; i++){
            System.out.println("Enter player " + i + " name:");
            playerName = userInput.nextLine();
            playerObjArr[i] = new Player("Player", playerName, playerScore);
        }
        return playerObjArr;
    }

    private Hand[] initHand(Player playerObjArr[]){
        Hand[] handObjArr = new Hand[playerObjArr.length];
        for(int i=0; i<playerObjArr.length; i++){
            handObjArr[i] = new Hand("playing", playerObjArr[i]);
        }
        return handObjArr;
    }

    private Deck[] initDeck(){
        Deck[] deckObjArr = new Deck[2];
        for(int i=0; i<2; i++){
            deckObjArr[i] = new Deck();
            deckObjArr[i].initCards();
        }
        return deckObjArr;
    }

    private GamePiece randomCard(Deck deckObjArr[]){
        Random r = new Random();
        int rIndex = r.nextInt(52);
        if(deckObjArr[0].get_cardsInDeck()[rIndex].get_pieceKey().equals("0")){
            return deckObjArr[0].get_cardsInDeck()[rIndex]; 
        }
        else if(deckObjArr[1].get_cardsInDeck()[rIndex].get_pieceKey().equals("0")){
            return deckObjArr[1].get_cardsInDeck()[rIndex]; 
        }
        else{
            return randomCard(deckObjArr);
        }
    }
    
    private void displayBoard(Hand handObjArr[], Player currentPlayer){
        int boardSize[] = {teamSize, handObjArr[0].getcards().size()};
        CardBoard boardObj = new CardBoard();
        boardObj.initBoard(boardSize);
        for(int i=0; i<handObjArr.length ;i++){
            for(int j=0; j<handObjArr[i].getcards().size(); j++){
                int cell[] = {i,j+1};
                int cell1[] = {i,j};
                if(j==0){
                    if(handObjArr[i].getStatus().equals("fold")){
                        boardObj.boardInitCells("", handObjArr[i].getPlayer().get_playerName()+"-F", cell1);
                    }
                    else if(handObjArr[i].getStatus().equals("bust")){
                        boardObj.boardInitCells("", handObjArr[i].getPlayer().get_playerName()+"-B", cell1);
                    }
                    else if(handObjArr[i].getStatus().equals("stand")){
                        boardObj.boardInitCells("", handObjArr[i].getPlayer().get_playerName()+"-S", cell1);
                    }
                    else{ //playing player status
                        boardObj.boardInitCells("", handObjArr[i].getPlayer().get_playerName(), cell1);
                    }
                }    
                else{
                    if(handObjArr[i].getcards().get(j).get_piecePossibleMove().equals("facedown") && !(handObjArr[i].getcards().get(j).get_pieceName().equals(currentPlayer.get_playerName()))){
                        boardObj.boardInitCells("", "**", cell);
                    }
                    else{
                        boardObj.boardInitCells("", handObjArr[i].getcards().get(j).get_pieceName(), cell);
                    }
                }
            }
        }
        boardObj.displayCurrentBoard();
    }

    public void letsPlay(){
        Scanner userInput = new Scanner(System.in);

        // initialise the player
        Player playerObjArr[] = initPlayer(userInput);

        // initialise hand for each player
        Hand handObjArr[] = initHand(playerObjArr);
        // creating decks
        Deck deckObjArr[] = initDeck();

        //function to randomly generate card
        GamePiece currentCard = randomCard(deckObjArr);

        // playing
        Player currentPlayer = playerObjArr[0];
        //display board
        displayBoard(handObjArr, currentPlayer);

        
    }
}