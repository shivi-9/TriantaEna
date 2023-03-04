package cardsanyone;
import java.util.*;

public class Hand {
    // This class handles the hand of a particular player. 

    private ArrayList<GamePiece> cards = new ArrayList<GamePiece>(); // cards held by a particular player
    private String status = ""; // status of the hand/player
    private int betMoney = 0; 
    private Player player; // player associated with this hand
    private int handValue = 0; 
    private Scanner userInput = new Scanner(System.in);

    public Hand(String status, int betMoney, Player player){
        this.status = status;
        this.betMoney = betMoney;
        this.player = player;
    }

    // add the card to this hand and update the hand value
    public void addCard(GamePiece newCard){
        cards.add(newCard);
        handValue += Integer.valueOf(newCard.get_pieceValue());
    }

    public void setStatus(String status){
        this.status = status;
    }

    // set the bed to zero
    public void emptyBet(){
        this.betMoney = 0;
    }
    
    public void setBetMoney(int bm){
        if(bm > player.get_playerScore()){
            System.out.println("Sorry, you don't have this much balance\nPLease bet an amount smaller than " + player.get_playerScore());
            bm = Integer.valueOf(userInput.nextLine());
            setBetMoney(bm);
        }
        else if(bm <= 0){ 
            System.out.println("Sorry, you can't bet such small amount\nPlease bet an  approporiate amount");
            bm = Integer.valueOf(userInput.nextLine());
            setBetMoney(bm);
        }
        this.betMoney = bm;
    }

    public void set_handValue(int hv){
        this.handValue = hv;
    }

    public ArrayList<GamePiece> getcards(){
        return cards;
    }

    public String getStatus(){
        return status;
    }

    public int getBetMoney(){
        return betMoney;
    }

    public Player getPlayer(){
        return player;
    }

    public int get_handValue(){
        return handValue;
    }

    // empty the cards array list
    public void emptyCard(){
        this.cards.clear();
    }
}