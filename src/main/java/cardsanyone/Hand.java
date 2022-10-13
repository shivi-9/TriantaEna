package cardsanyone;
import java.util.*;

public class Hand {
    private ArrayList<GamePiece> cards = new ArrayList<GamePiece>();
    private String status;
    private int betMoney;
    private Player player;

    public Hand(String status, Player player){
        this.status = status;
        this.player = player;
    }

    public void addCard(GamePiece newCard){
        cards.add(newCard);
    }

    public ArrayList<GamePiece> getcards(){
        return cards;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public int getBetMoney(){
        return betMoney;
    }

    public void setBetMoney(int bm){
        this.betMoney = bm;
    }

    public Player getPlayer(){
        return player;
    }

}