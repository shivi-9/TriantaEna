package cardsanyone;

public class BoardCells {
    // this is the deck class
    private String cellPower = new String(); // will not be used for Trianta Ena
    private GamePiece cellValue; // will have associated game pieceName
    private int[] cellLocation = new int[2]; // i,j position on the board

    public BoardCells(String cellPower, GamePiece cellValue, int cellLocation[]){
        this.cellPower = cellPower; 
        this.cellValue = cellValue;
        for(int i = 0; i < cellLocation.length; i++){
            this.cellLocation[i] = cellLocation[i];
        }
    }

    public BoardCells(GamePiece cellValue, int cellLocation[]){
        this.cellPower = ""; 
        this.cellValue = cellValue;
        for(int i = 0; i < cellLocation.length; i++){
            this.cellLocation[i] = cellLocation[i];
        }
    }

    public int[] get_cellLocation(){
        return cellLocation;
    }

    public String get_cellPower(){
        return cellPower;
    }

    public GamePiece get_cellValue(){
        return cellValue;
    }

    // public void set_cellValue(int cellValue){
    //     this.cellValue = cellValue;
    // }
    
}