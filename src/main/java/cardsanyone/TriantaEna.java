package cardsanyone;
import java.util.*;

public class TriantaEna extends Game {
    private int teamSize;
    Scanner userInput = new Scanner(System.in);


    public Boolean check_teamSize(int teamSize) {
        if (teamSize > 7 || teamSize < 2) {
            System.out.println("Please enter value in the above specified range\nLet's try again");
            return false;
        } else {
            this.teamSize = teamSize;
            return true;
        }
    }

    private int get_initCash(Scanner userInput) {
        System.out.println("Enter the amount that all the players will have in the begining of the game:");
        int cash;
        try {
            cash = Integer.parseInt(userInput.nextLine());
            return cash;
        } catch (Exception e) {
            Scanner userInput1 = new Scanner(System.in);
            System.out.println("Input is invalid!\nLets try again!");
            cash = get_initCash(userInput1);
            return cash;
        }
    }

    public Player[] initPlayer(Scanner userInput) {
        String playerName;
        Player[] playerObjArr = new Player[teamSize];
        int playerScore = get_initCash(userInput);
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

    private Hand[] initHand(Player playerObjArr[]) {
        Hand[] handObjArr = new Hand[playerObjArr.length];
        for (int i = 0; i < playerObjArr.length; i++) {
            handObjArr[i] = new Hand("playing", playerObjArr[i]);
        }
        return handObjArr;
    }

    private Deck[] initDeck() {
        Deck[] deckObjArr = new Deck[2];
        for (int i = 0; i < 2; i++) {
            deckObjArr[i] = new Deck();
            deckObjArr[i].initCards();
        }
        return deckObjArr;
    }

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

    private void displayBoard(Hand handObjArr[], Player currentPlayer) {
        int boardSize[] = {teamSize, handObjArr[0].getcards().size()};
        CardBoard boardObj = new CardBoard();
        boardObj.initBoard(boardSize);
        for (int i = 0; i < handObjArr.length; i++) {
            for (int j = 0; j < handObjArr[i].getcards().size(); j++) {
                int cell[] = {i, j + 1};
                int cell1[] = {i, j};
                if (j == 0) {
                    if (handObjArr[i].getStatus().equals("fold")) {
                        boardObj.boardInitCells("", handObjArr[i].getPlayer().get_playerName() + "-F", cell1);
                    } else if (handObjArr[i].getStatus().equals("bust")) {
                        boardObj.boardInitCells("", handObjArr[i].getPlayer().get_playerName() + "-B", cell1);
                    } else if (handObjArr[i].getStatus().equals("stand")) {
                        boardObj.boardInitCells("", handObjArr[i].getPlayer().get_playerName() + "-S", cell1);
                    } else { //playing player status
                        boardObj.boardInitCells("", handObjArr[i].getPlayer().get_playerName(), cell1);
                    }
                } else {
                    if (handObjArr[i].getcards().get(j).get_piecePossibleMove().equals("facedown") && !(handObjArr[i].getcards().get(j).get_pieceName().equals(currentPlayer.get_playerName()))) {
                        boardObj.boardInitCells("", "**", cell);
                    } else {
                        boardObj.boardInitCells("", handObjArr[i].getcards().get(j).get_pieceName(), cell);
                    }
                }
            }
        }
        boardObj.displayCurrentBoard();
    }

    public void letsPlay() {

        int card_value;
        int betAmount;
        int aceChoice;
        Boolean validBetArg;
        Scanner scObj = new Scanner(System.in);
        Boolean gameEnd = false;
        Boolean isPlayerBust;
        Boolean validAceChoice;
        String[] cardRank;

        String winners[];




        // initialise the player
        Player playerObjArr[] = initPlayer(userInput);

        Hand.init_hashmap();

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

        //Set the appropriate bank balance for each of the players
        for (int i = 0; i < playerObjArr.length; i++){
            if(playerObjArr[i].get_playerTeam().equals(Components.player)){
                handObjArr[i].setBetMoney(playerObjArr[i].get_playerScore());
            }
            else{
                handObjArr[i].setBetMoney(playerObjArr[i].get_playerScore());

            }
        }

        //Display the bet amounts
        for (int i = 0; i < playerObjArr.length; i++){
          System.out.println(playerObjArr[i].get_playerName()) ;
          System.out.println(playerObjArr[i].get_playerScore());
        }

        //Begin the first round of game
        while (!gameEnd) {
            //Loop for each player to draw the initial card
            for (int i = 0; i < playerObjArr.length; i++) {
                currentPlayer = playerObjArr[i];
                if (playerObjArr[i].get_playerTeam().equals(Components.player)) {
                    currentCard = randomCard(deckObjArr);
                }
                else
                {
                    System.out.println("Banker");
                    currentCard = randomCard(deckObjArr);
                    currentCard.setPiecePossibleMove("faceup");

                }
                System.out.println("player: " + i);
                handObjArr[i].addCard(currentCard);
                //System.out.println(currentCard.get_pieceName());
                handObjArr[i].print_cards_at_hand();
            }
            //Loop for each player to be given a choice to play or fold
            int play =0;
            for (int i = 0; i < playerObjArr.length; i++){
                Boolean validArg = false;
                if (playerObjArr[i].get_playerTeam().equals(Components.player)) {
                    System.out.println("Player "+i);
                    System.out.println("Press 0 if you want to Fold this game round ");
                    System.out.println("Press 1 if you want to play this game round ");
                    while(!validArg){
                        try{
                            int choice = scObj.nextInt();
                            if(choice==Components.playChoice){
                                handObjArr[i].setStatus(Components.playing);
                                validBetArg = false;
                                while(!validBetArg){
                                    try{
                                        System.out.println("Enter the Bet amount");
                                        betAmount = scObj.nextInt();
                                        if(betAmount >= 0 && betAmount<=playerObjArr[i].get_playerScore()){
                                            handObjArr[i].setBetMoney(betAmount);
                                            validBetArg = true;
                                        }
                                        else{
                                            System.out.println("You have $"+playerObjArr[i].get_playerScore() );
                                            System.out.println("Please bet an amount between 0 and "+playerObjArr[i].get_playerScore());
                                        }

                                    }
                                    catch(Exception e){
                                        System.out.println("You have $"+playerObjArr[i].get_playerScore() );
                                        System.out.println("Please bet an amount between 0 and "+playerObjArr[i].get_playerScore());
                                        scObj.next();

                                    }
                                }
                                play += 1;
                                validArg = true;
                            }
                            else if(choice==Components.foldChoice){
                                handObjArr[i].setStatus(Components.fold);
                                validArg = true;
                            }
                            else{
                                System.out.println("Input a valid choice!");
                            }
                        }
                        catch(Exception e){
                            System.out.println("Input a valid choice!");
                            scObj.next();
                        }
                    }
                }
                else{
                    currentCard = randomCard(deckObjArr);
                    handObjArr[i].addCard(currentCard);
                    System.out.println("banker");
                    handObjArr[i].print_cards_at_hand();
                }
            }
            System.out.println("Number of players playing excluding Bankers " +play);

            //Each playing player draw two additional cards
            for (int i = 0; i < playerObjArr.length; i++) {
                if (playerObjArr[i].get_playerTeam().equals(Components.player)) {
                    //So that the player draws two cards
                    for (int j = 0; j < 2; j++) {
                        currentCard = randomCard(deckObjArr);
                        handObjArr[i].addCard(currentCard);
                    }
                    //Check that everyone has the appropriate number card
                    System.out.println("Player " +i);
                    handObjArr[i].print_cards_at_hand();
                }
            }

            card_value = handObjArr[1].get_hand_total_value();
            System.out.println("Player 1 card value " + card_value);

            //Perpetual cycle of hit or stand
            Boolean keepHitting = true;
            while(keepHitting){
                for (int i = 0; i < playerObjArr.length; i++){
                    if (playerObjArr[i].get_playerTeam().equals(Components.player)){
                        if(handObjArr[i].getStatus().equals(Components.playing)){
                            {
                                Boolean validArg = false;

                                System.out.println("Player "+i);
                                System.out.println("Press 0 if you want to stand this turn ");
                                System.out.println("Press 1 if you want to hit this turn ");
                                while(!validArg){
                                    try{
                                        int choice = scObj.nextInt();
                                        if(choice==Components.standChoice){
                                            handObjArr[i].setStatus(Components.stand);
                                            break;
                                            }
                                        else if(choice==Components.hitChoice){
                                            currentCard = randomCard(deckObjArr);
                                            handObjArr[i].addCard(currentCard);
                                            handObjArr[i].print_cards_at_hand();
                                            isPlayerBust = checkPlayerBust(handObjArr[i]);
                                            if(isPlayerBust){
                                                System.out.println("Whoops! You're bust!");
                                                handObjArr[i].setStatus(Components.bust);
                                                break;
                                            }
                                            validArg = true;
                                            }
                                            else{System.out.println("Input a valid choice!");
                                            }
                                        }
                                        catch(Exception e){
                                            System.out.println("Input a valid choice!");
                                            scObj.next();
                                        }
                                    }

                            }

                        }
                    }
                    keepHitting = false;
                    //Check if there is any playing player remaining
                    for (int j = 0; j < playerObjArr.length; j++){
                        if(playerObjArr[i].get_playerTeam().equals(Components.player)){
                            if(handObjArr[i].getStatus().equals(Components.playing)){
                                keepHitting = true;
                            }
                        }
                    }

                }
            }

            gameEnd = true;

        }
        //Draw cards until it exceeds 27(for the banker)
        for (int i = 0; i < playerObjArr.length; i++){
            if(playerObjArr[i].get_playerTeam().equals(Components.banker)){
                card_value = handObjArr[i].get_hand_total_value();
                do{
                    currentCard = randomCard(deckObjArr);
                    handObjArr[i].addCard(currentCard);
                }while(card_value <=27);

            }
        }
       //update_win_status(handObjArr,playerObjArr);


    }

    private void update_win_status(Hand[] handObjArr, Player[] playerObjArr) {

        int[] winners = new int[7];
        int numWin = 0;
        int bankerValue = 0;
        for (int i = 0; i < playerObjArr.length; i++){
            if(playerObjArr[i].get_playerTeam().equals(Components.banker)){
                bankerValue = handObjArr[i].get_hand_total_value();
            }

        }
        int maxValue = bankerValue;
        for (int i = 0; i < playerObjArr.length; i++){
            if(playerObjArr[i].get_playerTeam().equals(Components.player)){
                if(handObjArr[i].get_hand_total_value() > maxValue){
                    if(handObjArr[i].get_hand_total_value() == maxValue){
                        System.out.println("Winner "+playerObjArr[i].get_playerName());
                        winners[numWin] = i;
                        numWin++;
                    }
                    else{
                        maxValue = handObjArr[i].get_hand_total_value();
                        System.out.println("Winner "+playerObjArr[i].get_playerName());
                        winners[numWin] = i;
                        numWin++;
                    }

                }
            }

        }
        if(maxValue!=bankerValue){
            System.out.println("Here are the updated player money");
            for(int j=0;j< winners.length;j++){
                playerObjArr[winners[j]].set_playerScore(playerObjArr[winners[j]].get_playerScore() + handObjArr[winners[j]].getBetMoney());
                System.out.println(playerObjArr[winners[j]].get_playerName());
                System.out.println(playerObjArr[winners[j]].get_playerScore());

        }
        }else{
            for (int i = 0; i < playerObjArr.length; i++){
                if(playerObjArr[i].get_playerTeam().equals(Components.player)){
                    bankerValue += handObjArr[i].getBetMoney();
                }
            }

        }
    }

    private Boolean checkPlayerBust(Hand hand) {
        Boolean isPlayerBust = false;
        int card_value =0;
        Hand.init_hashmap();
        if(hand.get_hand_total_value() > 31){
            isPlayerBust = true;
        }
        else{
            isPlayerBust = false;
        }

        return isPlayerBust;
    }
}