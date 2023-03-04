package cardsanyone;

public class Deck {
    // This class handles the decks and initialises each card/game piece for that particular deck.
    private int deckSize; // size of the deck
    private GamePiece[] cardsInDeck; // array of all the cards in that deck

    // Just to store all the card states in one place
    enum cardState{
        faceUp, faceDown, inActive;
    }

    public Deck(int deckSize){
        this.deckSize = deckSize;
    }

    // Initialises cards for a particular deck object
    public void initCards(String[] cardNames, String[] cardValue){
        cardsInDeck = new GamePiece[deckSize];    
        for(int i=0; i<deckSize; i++){
            cardsInDeck[i] = new GamePiece(cardNames[i], String.valueOf(cardState.faceDown), cardValue[i], "0");
        }
    }

    public GamePiece[] get_cardsInDeck(){
        return cardsInDeck;
    }
}
