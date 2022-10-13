package cardsanyone;

public class Deck {
    private GamePiece[] cardsInDeck = new GamePiece[52];

    enum cardState{
        faceUp, faceDown, inActive;
    }

    public void initCards(){
        String[] suits = {"H", "S", "C", "D"};
        int k=0;
        for(int i=0; i<4; i++){
            for(int j=0; j<13; j++){
                switch(j){
                    case 0:
                    cardsInDeck[k] = new GamePiece("A " + suits[i], cardState.faceDown.toString(), "1");
                    break;
                    case 10:
                    cardsInDeck[k] = new GamePiece("J " + suits[i], cardState.faceDown.toString(), String.valueOf(j+1));
                    break;
                    case 11:
                    cardsInDeck[k] = new GamePiece("Q " + suits[i], cardState.faceDown.toString(), String.valueOf(j+1));
                    break;
                    case 12:
                    cardsInDeck[k] = new GamePiece("K " + suits[i], cardState.faceDown.toString(), String.valueOf(j+1));
                    break;
                    default:
                    cardsInDeck[k] = new GamePiece(String.valueOf(j+1) + " " + suits[i], cardState.faceDown.toString(), String.valueOf(j+1)); 
                    break;  
                }
                k++;
            }
        }
    }

    public GamePiece[] get_cardsInDeck(){
        return cardsInDeck;
    }
}
