package cardsanyone;

public class GamePiece {
    private String pieceName = new String(); // name of the card
    private String piecePossibleMove = new String(); // whether card is faced up or down. Default value face down
    private String pieceValue = new String(); // value of the card
    private String pieceKey; // associated player ID

    public GamePiece(String pieceName, String piecePossibleMove, String pieceValue, String pieceKey){
        this.pieceName = pieceName;
        this.piecePossibleMove = piecePossibleMove;
        this.pieceValue = pieceValue;
        this.pieceKey = pieceKey;
    }

    public GamePiece(String pieceName, String piecePossibleMove, String pieceValue){
        this.pieceName = pieceName;
        this.piecePossibleMove = piecePossibleMove;
        this.pieceValue = pieceValue;
        this.pieceKey = "0";
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
}