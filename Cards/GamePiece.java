package cardsanyone;

public class GamePiece {
    // This class stores all the pieces required in that game. For example, king in chess, O in tick-tac-toe and cards
    // in a card game. Cards can be of many different types too like cards in UNO are different than used to play 
    // Blackjack.

    private String pieceName = ""; // Name of the piece
    private String piecePossibleMove = ""; // possible moves of that piece
    private String pieceValue = ""; // value of the piece
    private String pieceKey = ""; // whether a piece is held by a player or not

    public GamePiece(String pieceName, String piecePossibleMove, String pieceValue, String pieceKey){
        this.pieceName = pieceName;
        this.piecePossibleMove = piecePossibleMove;
        this.pieceValue = pieceValue;
        this.pieceKey = pieceKey;
    }

    public String get_pieceKey(){
        return pieceKey;
    }

    public String get_pieceName(){
        return pieceName;
    }

    public String get_piecePossibleMove(){
        return piecePossibleMove;
    }

    public String get_pieceValue(){
        return pieceValue;
    }

    public void set_pieceKey(String pk){
        this.pieceKey = pk;
    }
    
    public void set_piecePossibleMove(String pos){
        this.piecePossibleMove = pos;
    }

    public void set_pieceValue(String pv){
        this.pieceValue = pv;
    }
}