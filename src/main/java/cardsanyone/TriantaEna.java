package cardsanyone;
import java.util.*;

public class TriantaEna implements Game {
    // This class extends the game class and handles everything about the trianta ena game.

    private int teamSize;
    private Scanner userInput = new Scanner(System.in);

    // set the size of the team based on the game
    public void set_teamSize() {
        System.out.println("How many players are going today?(Only 2-7 players can play this game)");
        try {
            this.teamSize = Integer.parseInt(userInput.nextLine());
            if (this.teamSize > 7 || this.teamSize < 2) {
                System.out.println("Please enter value in the above specified range\nLet's try again");
                set_teamSize();
            }
        }catch(Exception e){
            System.out.println("Input is invalid!\nLets try again!");
            set_teamSize();
        }
    }
    
    // function to set the initial cash for players
    private int get_initCash() {
        System.out.println("Enter the amount that all the players will have in the begining of the game:");
        int cash;
        try {
            cash = Integer.parseInt(userInput.nextLine());
            return cash;
        } catch (Exception e) {
            System.out.println("Input is invalid!\nLets try again!");
            cash = get_initCash();
            return cash;
        }
    }

    // initialises the players
    public Player[] initPlayer() {
        String playerName;
        Player[] playerObjArr = new Player[teamSize];
        int playerScore = get_initCash();
        System.out.println("Please enter the name of the banker:");
        playerName = userInput.nextLine();
        playerObjArr[0] = new Player("Banker", playerName, playerScore * 3);
        for (int i = 1; i < teamSize; i++) {
            System.out.println("Enter player " + i + " name:");
            playerName = userInput.nextLine();
            playerObjArr[i] = new Player("Player", playerName, playerScore);
        }
        return playerObjArr;
    }

    // initialise hand for each player
    private Hand[] initHand(Player playerObjArr[]) {
        Hand[] handObjArr = new Hand[playerObjArr.length];
        for (int i = 0; i < playerObjArr.length; i++) {
            handObjArr[i] = new Hand("playing", 0, playerObjArr[i]);
        }
        return handObjArr;
    }

    // initialise decks
    private Deck[] initDeck() {
        Deck[] deckObjArr = new Deck[2];
        String[] cardNames = new String[52];
        String[] cardValue = new String[52];
        String[] suits = {"H", "S", "C", "D"};
        for (int l = 0; l < 2; l++) {
            int k=0;
            deckObjArr[l] = new Deck(52);
            for(int i=0; i<4; i++){
                for(int j=0; j<13; j++){
                    if(j==0){
                        cardNames[k] = "A " + suits[i];
                        cardValue[k] = "11";
                        k++;
                    }
                    else if(j==10){
                        cardNames[k] = "J " + suits[i];
                        cardValue[k] = "10";
                        k++;
                    }
                    else if(j==11){
                        cardNames[k] = "Q " + suits[i];
                        cardValue[k] = "10";
                        k++;
                    }
                    else if(j==12){
                        cardNames[k] = "K " + suits[i];
                        cardValue[k] = "10";
                        k++;
                    }
                    else{
                        cardNames[k] = String.valueOf(j+1) + suits[i];
                        cardValue[k] = String.valueOf(j+1);
                        k++;
                    }
                }
            }
            deckObjArr[l].initCards(cardNames, cardValue);
        }
        return deckObjArr;
    }

    // set the variables in hand to zero/empty for subsequent rounds
    private Hand[] clearHand(Hand handObjArr[]){
        for(int i=0; i<handObjArr.length; i++){
            handObjArr[i].emptyCard();
            handObjArr[i].emptyBet();
            handObjArr[i].setStatus("playing");
            handObjArr[i].set_handValue(0);
        }
        return handObjArr;
    }

    // reshuffle the deck
    private Deck[] clearDeck(Deck deckObjArr[]){
        for(int i=0; i<2; i++){
            for(int j=0; j<52; j++){
                deckObjArr[i].get_cardsInDeck()[j].set_pieceKey("0");
            }
        }
        return deckObjArr;
    }

    // pick a random card from deck
    private GamePiece randomCard(Deck deckObjArr[]) {
        Random r = new Random();
        int rIndex = r.nextInt(52);
        if (deckObjArr[0].get_cardsInDeck()[rIndex].get_pieceKey().equals("0")) {
            return deckObjArr[0].get_cardsInDeck()[rIndex];
        } else if (deckObjArr[1].get_cardsInDeck()[rIndex].get_pieceKey().equals("0")) {
            return deckObjArr[1].get_cardsInDeck()[rIndex];
        } else {
            return randomCard(deckObjArr);
        }
    }
    
    // draw first set of cards for all players
    private Hand[] drawCard1(Player playerObjArr[], Hand handObjArr[], Deck deckObjArr[]){
        GamePiece currentCard;
        for (int i = 0; i < playerObjArr.length; i++) {
            if(playerObjArr[i].get_playerScore() > 0){
                if (playerObjArr[i].get_playerTeam().equals("Player")) {
                    currentCard = randomCard(deckObjArr);
                    currentCard.set_pieceKey("1");
                }
                else {
                    currentCard = randomCard(deckObjArr);
                    currentCard.set_piecePossibleMove("faceUp");
                }
                handObjArr[i].addCard(currentCard);
            }
        }
        return handObjArr;
    }

    // display board
    private void beforeBoard(Hand handObjArr[]) {
        for(int i=0; i<handObjArr.length; i++){
            if(handObjArr[i].getPlayer().get_playerScore() > 0){
                int boardSize[] = {1, handObjArr[i].getcards().size()+1};
                CardBoard boardObj = new CardBoard();
                boardObj.initBoard(boardSize);
                int[] cell = new int[2];
                cell[0] = 0;
                if (handObjArr[i].getPlayer().get_playerTeam().equals("Banker")) {
                    cell[1] = 0;
                    boardObj.boardInitCells("", handObjArr[i].getPlayer().get_playerName() + "-$", cell);
                }
                else{
                    cell[1] = 0;
                    boardObj.boardInitCells("", handObjArr[i].getPlayer().get_playerName(), cell);
                }
                for(int j=0; j< handObjArr[i].getcards().size(); j++){
                    cell[1] = j+1;
                    if (handObjArr[i].getcards().get(j).get_piecePossibleMove().equals("faceDown")) {
                        boardObj.boardInitCells("", "**", cell);
                    } else {
                        boardObj.boardInitCells("", handObjArr[i].getcards().get(j).get_pieceName(), cell);
                    }
                }
                boardObj.displayCurrentBoard();
            }
        }
    }

    // function to ask whether a player wants to play or fold that round
    private Hand[] playOrFold(Player playerObjArr[], Hand handObjArr[], Deck deckObjArr[]){
        Boolean validBetArg;
        int betAmount;
        GamePiece currentCard;
        for (int i = 0; i < playerObjArr.length; i++){
            if(playerObjArr[i].get_playerScore() > 0){
                Boolean validArg = false;
                if (playerObjArr[i].get_playerTeam().equals("Player")) {
                    afterBoard(handObjArr, playerObjArr[i]);
                    while(!validArg){
                        System.out.println(playerObjArr[i].get_playerName() + ", do you want to play this round?(y/n)");
                        try{
                            String choice = userInput.nextLine();
                            if(choice.equals("y")){
                                handObjArr[i].setStatus("playing");
                                validBetArg = false;
                                while(!validBetArg){
                                    try{
                                        System.out.println("Enter the Bet amount:");
                                        betAmount = Integer.valueOf(userInput.nextLine());
                                        handObjArr[i].setBetMoney(betAmount);
                                        // playerObjArr[i].update_playerScore(-1*betAmount);
                                        validBetArg = true;
                                    } catch(Exception e){
                                        System.out.println("Invalid input");
                                    }
                                }
                                validArg = true;
                            }
                            else if(choice.equals("n")){
                                handObjArr[i].setStatus("fold");
                                validArg = true;
                            }
                            else{
                                System.out.println("Input a valid choice!");
                            }
                        }
                        catch(Exception e){
                            System.out.println("Input a valid choice!");
                        }
                    }
                }
                else{
                    currentCard = randomCard(deckObjArr);
                    currentCard.set_pieceKey("1");
                    handObjArr[i].addCard(currentCard);
                }
            }
        }
        return handObjArr;
    }

    // check if atleast a single player is playing that round
    private Boolean checkUnFold(Hand handObjArr[]){
        Boolean ret = false;
        for(int i=0; i<handObjArr.length; i++){
            if(handObjArr[i].getPlayer().get_playerTeam().equals("Player") && !(handObjArr[i].getStatus().equals("fold"))){
                ret = true;
            }
        }
        return ret;
    }

    // display board
    private void afterBoard(Hand handObjArr[], Player currentPlayer) {
        for(int i=0; i<handObjArr.length; i++){
            if(handObjArr[i].getPlayer().get_playerScore() > 0){
                int boardSize[] = {1, handObjArr[i].getcards().size()+1};
                CardBoard boardObj = new CardBoard();
                boardObj.initBoard(boardSize);
                int[] cell = new int[2];
                cell[0] = 0;
                cell[1] = 0;
                if (handObjArr[i].getStatus().equals("fold")) {
                    boardObj.boardInitCells("", handObjArr[i].getPlayer().get_playerName() + "-F", cell);
                } 
                else if (handObjArr[i].getStatus().equals("bust")) {
                    boardObj.boardInitCells("", handObjArr[i].getPlayer().get_playerName() + "-B", cell);
                } 
                else if (handObjArr[i].getStatus().equals("stand")) {
                    boardObj.boardInitCells("", handObjArr[i].getPlayer().get_playerName() + "-S", cell);
                }
                else if (handObjArr[i].getPlayer().get_playerTeam().equals("Banker")) {
                    boardObj.boardInitCells("", handObjArr[i].getPlayer().get_playerName() + "-$", cell);
                }
                else{
                    boardObj.boardInitCells("", handObjArr[i].getPlayer().get_playerName(), cell);
                }
                for(int j=0; j< handObjArr[i].getcards().size(); j++){
                    cell[1] = j+1;
                    if (handObjArr[i].getcards().get(j).get_piecePossibleMove().equals("faceDown") && !(handObjArr[i].getPlayer().get_playerName().equals(currentPlayer.get_playerName()))){ 
                        boardObj.boardInitCells("", "**", cell);
                    } else {
                        boardObj.boardInitCells("", handObjArr[i].getcards().get(j).get_pieceName(), cell);
                    }
                }
                boardObj.displayCurrentBoard();
            }
        }
    }

    // draw second round of cards for players
    private Hand[] drawCard2(Player playerObjArr[], Hand handObjArr[], Deck deckObjArr[]){
        GamePiece currentCard;
        for (int i = 0; i < playerObjArr.length; i++) {
            if(playerObjArr[i].get_playerScore() > 0){
                if (playerObjArr[i].get_playerTeam().equals("Player") && !(handObjArr[i].getStatus().equals("fold"))) {
                    //So that the player draws two cards
                    for (int j = 0; j < 2; j++) {
                        currentCard = randomCard(deckObjArr);
                        currentCard.set_pieceKey("1");
                        currentCard.set_piecePossibleMove("faceUp");
                        handObjArr[i].addCard(currentCard);
                    }
                    afterBoard(handObjArr, playerObjArr[i]);
                }
            }
        }
        return handObjArr;
    }

    // check if a player is bust or not
    private Boolean checkPlayerBust(Hand hand) {
        Boolean isPlayerBust = false;
        if(hand.get_handValue() > 31){
            isPlayerBust = true;
        }
        return isPlayerBust;
    }

    // check if a player is hitting or standing
    private Hand[] hitOrStand(Player playerObjArr[], Hand handObjArr[], Deck deckObjArr[]){
        GamePiece currentCard;
        Boolean keepHitting = true;
        Boolean isPlayerBust;
        while(keepHitting){
            for (int i = 0; i < playerObjArr.length; i++){
                if(playerObjArr[i].get_playerScore() > 0){
                    if (playerObjArr[i].get_playerTeam().equals("Player")){
                        if(handObjArr[i].getStatus().equals("playing")){
                            Boolean validArg = false;
                            while(!validArg){
                                System.out.println(playerObjArr[i].get_playerName() + ", do you want to hit or stand?(Press h for Hit and s for Stand)");
                                try{
                                    String choice = userInput.nextLine();
                                    if(choice.equals("s")){
                                        handObjArr[i].setStatus("stand");
                                        validArg = true;
                                    }
                                    else if(choice.equals("h")){
                                        currentCard = randomCard(deckObjArr);
                                        currentCard.set_pieceKey("1");
                                        currentCard.set_piecePossibleMove("faceUp");
                                        handObjArr[i].addCard(currentCard);
                                        afterBoard(handObjArr, playerObjArr[i]);
                                        isPlayerBust = checkPlayerBust(handObjArr[i]);
                                        if(isPlayerBust){
                                            System.out.println("Whoops! You're bust!");
                                            handObjArr[i].setStatus("bust");
                                            // handObjArr[i].emptyBet();
                                        }
                                        validArg = true;
                                    }
                                    else{
                                        System.out.println("Input a valid choice!");
                                    }
                                }
                                catch(Exception e){
                                    System.out.println("Input a valid choice!");
                                }
                            }
                        }
                    }
                }
                keepHitting = false;
                //Check if there is any playing player remaining
                for (int j = 0; j < playerObjArr.length; j++){
                    if(playerObjArr[i].get_playerScore() > 0){
                        if(playerObjArr[i].get_playerTeam().equals("Player")){
                            if(handObjArr[i].getStatus().equals("playing")){
                                keepHitting = true;
                            }
                        }
                    }
                }
            }
        }
        return handObjArr;
    }

    // draw cards for banker
    private Hand[] bankerDraw(Player playerObjArr[], Hand handObjArr[], Deck deckObjArr[]){
        GamePiece currentCard;
        for (int i = 0; i < playerObjArr.length; i++){
            if(playerObjArr[i].get_playerTeam().equals("Banker")){
                while(true){
                    currentCard = randomCard(deckObjArr);
                    currentCard.set_piecePossibleMove("faceUp");
                    handObjArr[i].addCard(currentCard);
                    if(handObjArr[i].get_handValue() >= 27){
                        break;
                    }
                }
                afterBoard(handObjArr, playerObjArr[i]);
            }
        }
        return handObjArr;
    }

    // update money based on the result of that round
    private void displayUpdatedMoney(Player playerObjArr[]){
        System.out.println("Here's the balance of all players:");
        for(int i=0; i<playerObjArr.length; i++){
            System.out.println(playerObjArr[i].get_playerName()+" has $"+playerObjArr[i].get_playerScore());
        }
    }

    // calculating who won how much money
    private void update_win_status(Hand[] handObjArr, Player[] playerObjArr) {
        int banker=0;
        for (int i = 0; i < playerObjArr.length; i++){
            if(playerObjArr[i].get_playerTeam().equals("Banker")){
                banker = handObjArr[i].get_handValue();
            }
        }
        for (int i = 0; i < playerObjArr.length; i++){
            int bet=0;
            if(playerObjArr[i].get_playerScore() > 0){
                if(playerObjArr[i].get_playerTeam().equals("Player") && !(handObjArr[i].getStatus().equals("fold"))){
                    if(!(handObjArr[i].getStatus().equals(("bust")))){
                        if(handObjArr[i].get_handValue() > banker){
                            System.out.println("Player " + playerObjArr[i].get_playerName() + " won $" + String.valueOf(handObjArr[i].getBetMoney()*2));
                            playerObjArr[i].update_playerScore(handObjArr[i].getBetMoney());
                            bet = -1*handObjArr[i].getBetMoney();    
                        }
                        else if(handObjArr[i].get_handValue() == banker || handObjArr[i].get_handValue() < banker){
                            System.out.println("Player " + handObjArr[i].getPlayer().get_playerName() + " has\nBanker has won $" + String.valueOf(handObjArr[i].getBetMoney()));
                            playerObjArr[i].update_playerScore(-1*handObjArr[i].getBetMoney());
                            bet = handObjArr[i].getBetMoney();
                            if(handObjArr[i].getPlayer().get_playerScore() < 0){
                                System.out.println("Player " + handObjArr[i].getPlayer().get_playerName() + " has cashed out");
                            }
                        }
                    }
                    else if(handObjArr[i].getStatus().equals("bust")){
                        System.out.println(playerObjArr[i].get_playerName() + " was busted");
                        playerObjArr[i].update_playerScore(-1*handObjArr[i].getBetMoney());
                            bet = handObjArr[i].getBetMoney();
                    }
                }
            }
            for (int j = 0; j < playerObjArr.length; j++){
                if(playerObjArr[j].get_playerTeam().equals("Banker")){
                    handObjArr[j].getPlayer().update_playerScore(bet);
                    if(handObjArr[i].getPlayer().get_playerScore() < 0){
                        System.out.println("Banker has cashed out");
                    }
                }
            }
        }
        displayUpdatedMoney(playerObjArr);
    }
    
    // check if atleast 2 players/banker have money
    private Boolean checkPlayerMoney(Player playerObjArr[]){
        int count = 0;
        for(int i=0; i<playerObjArr.length; i++){
            if(playerObjArr[i].get_playerScore() > 0){
                count ++;
            }
        }
        if(count > 1){
            return true;
        }
        else{
            return false;
        }
    }

    private int findMax(int money[]){
        int max = money[0];
        int maxIn = 0;
        for(int i=0; i<money.length; i++){
            if(money[i] > max){
                max = money[i];
                maxIn = i;
            }
        }
        return maxIn;
    }

    //rotate the banker at the end of each round
    private Player[] rotateBanker(Player playerObjArr[]){
        int[] money = new int[playerObjArr.length];
        int maxIn;
        String choice;
        for(int i=0; i<playerObjArr.length; i++){
            money[i] = playerObjArr[i].get_playerScore();
            playerObjArr[i].set_playerTeam("Player");
        }
        while(true){
            maxIn = findMax(money);
            System.out.println(playerObjArr[maxIn].get_playerName() + ", do you want to be banker?(y/n)");
            choice = userInput.nextLine();
            if(choice.equals("y")){
                playerObjArr[maxIn].set_playerTeam("Banker");
                break;
            }
            else{
                money[maxIn] = 0;
            }
        }
        return playerObjArr;
    }

    public void letsPlay() {
        Boolean gameEnd = false;
        
        // set the team size based on game rules
        set_teamSize();

        // initialise the player
        Player playerObjArr[] = initPlayer();

        // initialise the hand for each player
        Hand handObjArr[] = initHand(playerObjArr);

        // creating decks
        Deck deckObjArr[] = initDeck();

        //Begin the first round of game
        while (!gameEnd) {
            // clear hand
            handObjArr = clearHand(handObjArr);
            
            // clear/reshuffle deck
            deckObjArr = clearDeck(deckObjArr);

            //function to draw the initial card
            handObjArr = drawCard1(playerObjArr, handObjArr, deckObjArr);
            
            //display board
            beforeBoard(handObjArr);

            // function for each player to be given a choice to play or fold
            handObjArr = playOrFold(playerObjArr, handObjArr, deckObjArr);
            
            //check if there is any player who hasn't folded
            if(checkUnFold(handObjArr)){
                //function for each playing player to draw two additional cards
                handObjArr = drawCard2(playerObjArr, handObjArr, deckObjArr);

                //Perpetual cycle of hit or stand
                handObjArr = hitOrStand(playerObjArr, handObjArr, deckObjArr);

                //Draw cards until it exceeds 27(for the banker)
                handObjArr = bankerDraw(playerObjArr, handObjArr, deckObjArr);

                // print the results of this round
                update_win_status(handObjArr,playerObjArr);

                // check if two or more players have money left
                if(checkPlayerMoney(playerObjArr)){
                    //rotate banker
                    playerObjArr = rotateBanker(playerObjArr);
                }
                else{
                    System.out.println("GameOver!");
                    gameEnd = true;
                }
            }
            else{
                System.out.println("GameOver!");
                gameEnd = true;
            }
        }
    }
}