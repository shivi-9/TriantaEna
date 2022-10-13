package cardsanyone;
import java.util.*;

public class Hand {
    private ArrayList<GamePiece> cards = new ArrayList<GamePiece>();
    private String status;
    private int betMoney;
    private Player player;



    public static HashMap<String, Integer> rank_map = new HashMap<>();



    public Hand(String status, Player player){
        this.status = status;
        this.player = player;

    }

    static public void init_hashmap() {
        String rank;
        Boolean doneFilling = false;
        int i = 0;
        while(!doneFilling){
            if(i==0){
                rank_map.put("A",11);

            }
            else if(i==1){
                rank_map.put("K",10);

            }
            else if(i>=2 && i<=10){
                rank_map.put(String.valueOf(i),i);

            }
            else if(i==11){
                rank_map.put("Q",10);

            }
            else if(i==12){
                rank_map.put("J",10);
            }
            i++;
            if(i>12){
                doneFilling = true;
            }

        }

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

    public void print_cards_at_hand(){
        for(int i=0;i<cards.size();i++){
            System.out.println(cards.get(i).get_pieceName()+" "+cards.get(i).get_piecePossibleMove());
        }
    }

    public int get_hand_total_value(){
        int sum_value = 0;

        for(int i=0;i<cards.size();i++){
            String[] rank_split = cards.get(i).get_pieceName().split(" ");
            sum_value += rank_map.get(rank_split[0]);
        }

        return sum_value;
    }

}